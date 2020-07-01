package blockchainevoting;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import Database.DatabaseConnection;

public class CreatePartyAccount {
	
	public static void main(String args[]) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException
	{
		DatabaseConnection dbcon=new DatabaseConnection();
		dbcon.dbconnection();
	
		 HashMap<String, String> usenameWithPartyname=new HashMap<>();
		 usenameWithPartyname.put("bjp.jpg","" );
		 usenameWithPartyname.put("bsp.jpg","" );
		 usenameWithPartyname.put("congress.jpg", "");
		 usenameWithPartyname.put("shivsena.jpg","" );
		 
		 
		
		 for (Map.Entry<String, String> entry : usenameWithPartyname.entrySet()) {
			   
			 String key=entry.getKey();
			 
			 String allaccount= createblockchainaccount(key);
			 
				String accountalldet[]=allaccount.split("@SP");
	        	String account=accountalldet[0];
	        	String keystorepath=accountalldet[1];
	        	
	        	String queryupdate="insert into partyaccount values ('"+key+"','0x"+account+"','"+keystorepath+"')";
	        	System.out.println(queryupdate);
	        	dbcon.getUpdate(queryupdate);
			 
			}

		
	}

public static String createblockchainaccount(String passsowrd) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException
{
	Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));
	String walletFileName = WalletUtils.generateFullNewWalletFile(passsowrd,new File(config.keypath));
	System.out.println(walletFileName);
	String[] fetchAddress=walletFileName.split("--");

	String getAddress = fetchAddress[fetchAddress.length-1].split("\\.")[0];
	getAddress=getAddress+"@SP"+walletFileName;
	
	
	
	System.out.println(passsowrd+" address "+getAddress);
	return getAddress;
}
}
