package blockchainevoting;

import java.io.IOException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class getAccountList {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		   Web3j web3j = Web3j.build(new HttpService("http://localhost:8280"));
		   
		   System.out.println(web3j.ethAccounts().send().getAccounts().get(3));
	}

}
