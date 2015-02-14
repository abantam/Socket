package pmtud;

//ノードを表すクラス

import java.net.Inet4Address;
import java.net.Socket;

public class Node {

	Socket socket;
	Inet4Address address;//IPアドレスを表す
	
	public Node() {
		socket = new Socket(); 
	}

}
