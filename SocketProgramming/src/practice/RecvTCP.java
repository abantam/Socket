package practice;

import java.net.ServerSocket;
import java.net.Socket;

public class RecvTCP {

	public static void main(String args[]) throws Exception {

		//引数の数が正しいかどうか調べる
		if(args.length != 1) {
			throw new IllegalArgumentException("Parameter(s): <Port>");
		}

		//受信ポート
		int port = Integer.parseInt(args[0]);

		ServerSocket servSock = new ServerSocket(port);
		Socket clntSock = servSock.accept();

		//テキストエンコードされた見積もりを受信する
		ItemQuoteDecoder decoder = new ItemQuoteDecoderText();
		ItemQuote quote = decoder.decode(clntSock.getInputStream());
		System.out.println("Received Text Encoded Quote");
		System.out.println(quote);

		//単価に１０セント追加し、バイナリエンコードで見積もりを送り返す
		ItemQuoteEncoder encoder = new ItemQuoteEncoderBin();
		quote.unitPrice += 10; //単価に１０セント追加する
		System.out.println("Sending (binary)...");
		clntSock.getOutputStream().write(encoder.encode(quote));

		clntSock.close();
		servSock.close();
	}

}
