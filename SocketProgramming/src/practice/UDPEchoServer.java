package practice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {

	private static final int ECHOMAX = 255;//エコーデータグラムの最大サイズ

	public static void main(String[] args) throws IOException {
		//引数のリストが正しいかどうか調べる
		if(args.length != 1) {
			throw new IllegalArgumentException("Parameters: <Port>");
		}

		int servPort = Integer.parseInt(args[0]);

		DatagramSocket socket = new DatagramSocket(servPort);
		DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);

		//繰り返し実行され、データグラムを受信してエコーする
		for(;;) {
			//クライアントからパケットを受信する
			socket.receive(packet);

			System.out.println("Handling client at " + packet.getAddress().getHostAddress() + " on port " + packet.getPort());

			//同じパケットをクライアントに送り返す
			socket.send(packet);

			//バッファが縮小しないように長さをリセットする
			packet.setLength(ECHOMAX);
		}
		/*この部分には到達しない*/
	}

}
