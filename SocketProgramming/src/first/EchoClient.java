package first;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String[] args) {

		//ソケットや入出力用のストリームの宣言
		Socket echoSocket = null;
		DataOutputStream os = null;
		BufferedReader is = null;

		//ポート9999番を開く
		try {
			echoSocket = new Socket("localhost", 9999);
			os = new DataOutputStream(echoSocket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		}catch(UnknownHostException e) {

		}catch(IOException e) {

		}

		//サーバーにメッセージを送る
		if(echoSocket != null && os != null && is != null) {
			try {
				//メッセージを送る
				os.writeBytes("HELLO\n");

				//サーバーからのメッセージを受け取り画面に表示
				String responseLine;
				if((responseLine = is.readLine()) != null) {
					System.out.println("Server: " + responseLine);
				}

				//開いたソケットなどをクローズ
				os.close();
				is.close();
				echoSocket.close();
			}catch(UnknownHostException e) {

			}catch(IOException e) {

			}
		}
	}

}
