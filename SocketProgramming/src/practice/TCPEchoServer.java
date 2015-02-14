package practice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {

	private static final int BUFSIZE = 32;//受信バッファのサイズ

	public static void main(String[] args) throws IOException {

		//引数の数が正しいかどうか調べる
		if(args.length != -1) {
			throw new IllegalArgumentException("Parameters: <Port>");
		}

		int servPort = Integer.parseInt(args[0]);

		//クライアントの接続要求を受け付けるサーバーソケットを作成する
		ServerSocket servSock = new ServerSocket(servPort);

		//受信したメッセージのサイズ
		int recvMsgSize;

		//受信バッファ
		byte[] byteBuffer = new byte[BUFSIZE];

		//繰り返し実行され、接続を受け付けて処理する
		for(;;) {
			//クライアント接続を取得する
			Socket clntSock = servSock.accept();

			System.out.println("Handling client at" + clntSock.getInetAddress().getHostAddress() + " on port " + clntSock.getPort());

			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();

			//クライアントが接続をクローズし、－1が返されるまで受信する
			while((recvMsgSize = in.read(byteBuffer)) != -1) {
				out.write(byteBuffer, 0, recvMsgSize);
			}

			clntSock.close();
		}
	}

}
