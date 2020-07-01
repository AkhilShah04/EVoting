package blockchainevoting;

import java.math.BigDecimal;
import java.sql.ResultSet;
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

public class AssignBalanceToUser {
	 private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		DatabaseConnection dbcon;
		dbcon=new DatabaseConnection();
	     dbcon.dbconnection();
		ArrayList<String> dataofuser=new ArrayList<>();
		
		String qry="Select * from userentry";
		ResultSet rs=dbcon.getResultSet(qry);
		while(rs.next())
		{
			dataofuser.add(rs.getString("useraccount"));
			
			
		}
		
		
		
		  new AssignBalanceToUser().run(dataofuser);
		
		
		
	}
	 private void run(ArrayList<String> dataofuser) throws Exception {
	        // We start by creating a new web3j instance to connect to remote nodes on the network.
		 for(String k:dataofuser) {
	        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
	        log.info("Connected to Ethereum client version: "
	                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
	        Credentials credentials =
	                WalletUtils.loadCredentials(
	                        "abcd",
	                        config.keypath+"UTC--2019-11-11T09-39-38.582689000Z--e5b2b2ce5130fca7817da53cb2b859568f0614db");
	        log.info("Credentials loaded");
	        log.info("Sending Ether ..");
	        
	        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
	        
	        web3j.dbPutHex("hi", "hi1", "hi2");
	        web3j.dbPutString("str1", "str1", "str1");
	        System.out.println(web3j.ethAccounts().send().getAccounts());
	        
	        TransactionReceipt transferReceipt = Transfer.sendFunds(
	                web3j, credentials,
	               k,  // you can put any address here
	                BigDecimal.valueOf(2), Convert.Unit.ETHER)  // 1 wei = 10^-18 Ether
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
}
