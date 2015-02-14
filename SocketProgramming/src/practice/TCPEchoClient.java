package practice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class TCPEchoClient {

	public static void main(String[] args) throws IOException {

		//引数の数が正しいことを調べる
		if((args.length < 2) || (args.length > 3)) {
			throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");
		}

		//サーバー名またはIPアドレス
		String server = args[0];

		//デフォルトの文字エンコードを方式を使って入力Stringをバイトに変換する
		byte[] byteBuffer = args[1].getBytes();

		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

		//サーバーの指定されたポートに接続するソケットを作成する
		Socket socket = new Socket(server, servPort);
		System.out.println("Connected to server...sending echo string");

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		//エンコードされた文字列をサーバーに送信する
		out.write(byteBuffer);

		//サーバーから同じ文字列を受信する
		int totalBytesRcvd = 0;//これまでに受信した合計バイト数
		int bytesRcvd;//前回の読み込みで受信したバイト数

		while(totalBytesRcvd < byteBuffer.length) {
			if((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1) {
				throw new SocketException("Connection closed prematurely");
			}
			totalBytesRcvd += bytesRcvd;
		}

		System.out.println("Received: " + new String(byteBuffer));

		socket.close();
	}

}
