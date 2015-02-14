package practice;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {

	public static void main(String[] args) {

		//ローカルホストの名前とIPアドレスを取得する
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("Local Host:");
			System.out.println("\t" + address.getHostName());
			System.out.println("\t" + address.getHostAddress());
		}catch(UnknownHostException e) {
			System.out.println("Unable to determine this host's address");
		}

		for(int i = 0; i < args.length; i++) {
			//コマンドラインで指定されたホストの名前とアドレスを取得する
			try {
				InetAddress[] addressList = InetAddress.getAllByName(args[i]);
				System.out.println(args[i] + ":");
				//最初の名前を出力する。配列に少なくとも1つのエントリが含まれていることを想定
				System.out.println("\t" + addressList[0].getHostName());
				for(int j = 0; j < addressList.length; j++) {
					System.out.println("\t" + addressList[j].getHostAddress());
				}
			}catch(UnknownHostException e) {
				System.out.println("Unable to find address for " + args[i]);
			}
		}
	}

}
