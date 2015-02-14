package practice;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoClientTimeout {

	private static final int TIMEOUT = 3000;//再送のタイムアウト（ミリ秒）
	private static final int MAXTRIES = 5;//再送回数の上限

	public static void main(String[] args) throws IOException {
		//引数の数が正しいかどうかを調べる
		if((args.length < 2) || (args.length > 3)) {
			throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");
		}

		//サーバーのアドレス
		InetAddress serverAddress = InetAddress.getByName(args[0]);

		//デフォルトのエンコード方式を使って引数のStringをバイトに変換する
		byte[] bytesToSend = args[1].getBytes();

		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

		DatagramSocket socket = new DatagramSocket();

		//受信待機時間の最大値（ミリ秒）
		socket.setSoTimeout(TIMEOUT);

		//送信パケット
		DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, servPort);

		//受信パケット
		DatagramPacket receivePacket = new DatagramPacket(new byte [bytesToSend.length], bytesToSend.length);

		//パケットが紛失した場合は再送が必要
		int tries = 0;

		boolean receivedResponse = false;

		do {
			//エコー文字列を送信する
			socket.send(sendPacket);

			try {
				//エコー応答の受信を試みる
				socket.receive(receivePacket);

				//送信元をチェックする
				if(!receivePacket.getAddress().equals(serverAddress)) {
					throw new IOException("Received packet from an unknown source");
				}

				receivedResponse = true;

			//何も受け取っていない
			}catch(InterruptedIOException e) {
				tries += 1;
				System.out.println("Timed out, " + (MAXTRIES - tries) + "more tries...");
			}
		}while((!receivedResponse) && (tries < MAXTRIES));
			if(receivedResponse) {
				System.out.println("Received: " + new String(receivePacket.getData()));
			}else {
				System.out.println("No response -- giving up");
			}
			socket.close();
	}

}
