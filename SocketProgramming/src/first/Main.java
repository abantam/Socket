package first;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	final int PORT_NUMBER = 1024;

	public static void main(String[] args) {
		new Main().start();
	}

	void start() {

		//クライアントプログラムの作成
		Socket MyClient = null;
		try {
			MyClient = new Socket("HostName", PORT_NUMBER);
		}catch(IOException e) {

		}

		//サーバープログラムの作成
		ServerSocket MyService = null;
		try {
			MyService = new ServerSocket(PORT_NUMBER);
		}catch(IOException e) {

		}

		//クライアントの要求を受け付けるソケットの作成
		Socket serviceSocket = null;
		try {
			serviceSocket = MyService.accept();
		}catch(IOException e) {

		}

		//クライアント側でサーバーからのデータを受け取る入力ストリームを作成
		DataInputStream Clientinput = null;
		try {
			Clientinput = new DataInputStream(MyClient.getInputStream());
		}catch(IOException e) {

		}

		//サーバー側でクライアントからのデータを受け取る入力ストリームを作成
		DataInputStream Serverinput = null;
		try {
			Serverinput = new DataInputStream(serviceSocket.getInputStream());
		}catch(IOException e) {

		}

		//クライアント側でサーバーへデータを送るための出力ストリームを作成
		PrintStream Clientoutput = null;
		try {
			Clientoutput = new PrintStream(MyClient.getOutputStream());
		}catch(IOException e) {

		}

		//テキストを画面に出力
		DataOutputStream output;
		try {
			output = new DataOutputStream(MyClient.getOutputStream());
		}catch(IOException e) {

		}

		//サーバー側でクライアントへデータを送るための出力ストリームを作成
		DataOutputStream Serveroutput = null;
		try {
			Serveroutput = new DataOutputStream(serviceSocket.getOutputStream());
		}catch(IOException e) {

		}

		//クライアントプログラムのソケットをクローズ
		try {
			Clientoutput.close();
			Clientinput.close();
			MyClient.close();
		}catch(IOException e) {

		}

		//サーバープログラムのソケットをクローズ
		try {
			Serveroutput.close();
			Serverinput.close();
			serviceSocket.close();
			MyService.close();
		}catch(IOException e) {

		}
	}

}
