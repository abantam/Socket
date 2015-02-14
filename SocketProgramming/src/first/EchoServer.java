package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {

		//ソケットや入出力用のストリームの宣言
		ServerSocket echoServer = null;
		String line;
		BufferedReader is;
		PrintStream os;
		Socket clientSocket = null;

		//ポート9999番を開く
		try {
			echoServer = new ServerSocket(9999);
		}catch(IOException e) {

		}

		//クライアントからの要求を受けるソケットを開く
		try {
			clientSocket = echoServer.accept();
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());

			//クライアントからのメッセージを町、受け取ったメッセージをそのまま返す
			while(true) {
				line = is.readLine();
				os.println(line);
			}
		}catch(IOException e) {

		}
	}

}
