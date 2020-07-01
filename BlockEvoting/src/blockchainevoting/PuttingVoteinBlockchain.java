package blockchainevoting;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import Database.DatabaseConnection;

public class PuttingVoteinBlockchain {
	 private static final Logger log = LoggerFactory.getLogger(PuttingVoteinBlockchain.class);

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String from="0x0521c8d47b39b0c12a8595d19a0684815a64233c";
		String to="0xda5a8d1a45661dea99bafbe6a68f9c8841c911d5";
		
		DatabaseConnection dbcon;
		dbcon=new DatabaseConnection();
	     dbcon.dbconnection();
		
		String password="";
		String keystorepath="";
		String qry="Select * from userentry where useraccount='"+from+"'";
		ResultSet rs=dbcon.getResultSet(qry);
		while(rs.next())
		{
			password=rs.getString("encpassword");
			keystorepath=rs.getString("keystorepath");
		}
		
		
		
		
		
		  new PuttingVoteinBlockchain().run(from,to,password,keystorepath);
		
		
	}
	
	public static void puttingvotefromuser(String from1,String to1) throws Exception
	{
		

		String from=from1;
		String to=to1;
		
		DatabaseConnection dbcon;
		dbcon=new DatabaseConnection();
	     dbcon.dbconnection();
		
		String password="";
		String keystorepath="";
		String qry="Select * from userentry where useraccount='"+from+"'";
		ResultSet rs=dbcon.getResultSet(qry);
		while(rs.next())
		{
			password=rs.getString("encpassword");
			keystorepath=rs.getString("keystorepath");
		}
		
		
		
		
		
		  new PuttingVoteinBlockchain().run(from,to,password,keystorepath);
		
	}
	
	 private void run(String from,String to, String password, String keystorepath) throws Exception {
	        // We start by creating a new web3j instance to connect to remote nodes on the network.
		
	        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
	        log.info("Connected to Ethereum client version: "
	                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
	        Credentials credentials =
	                WalletUtils.loadCredentials(
	                		password,
	                        config.keypath+keystorepath);
	        log.info("Credentials loaded");
	        log.info("Sending Ether ..");
	        
	        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
	        
	        web3j.dbPutHex("hi", "hi1", "hi2");
	        web3j.dbPutString("str1", "str1", "str1");
	        System.out.println(web3j.ethAccounts().send().getAccounts());
	        
	        TransactionReceipt transferReceipt = Transfer.sendFunds(
	                web3j, credentials,
	               to,  // you can put any address here
	                BigDecimal.valueOf(1), Convert.Unit.ETHER)  // 1 wei = 10^-18 Ether
	                .sendAsync().get();
	        
	        String message = "\\x19Ethereum Signed Message:\n";

	        byte[] data = message.getBytes();

	        Sign.SignatureData signature = Sign.signMessage(data, credentials.getEcKeyPair());
	        
//	        TransactionReceipt transferReceipt1 = Transfer.sendFunds(
//	                web3j, credentials,
//	                "0x57a751ff0a996ea3560a4869c984e25f3c6217c3",  // you can put any address here
//	                BigDecimal.valueOf(3), Convert.Unit.FINNEY)  // 1 wei = 10^-18 Ether
//	                .sendAsync().get();
	        
	        System.out.println( transferReceipt.getTransactionHash());
	        log.info("Transaction complete : "
	                + transferReceipt.getTransactionHash());
	    
	 }
}
