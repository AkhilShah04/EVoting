package blockchainevoting;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Sign;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
//to do transaction on different account
public class rawtranscationhash {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        new rawtranscationhash().run();
    }

    private void run() throws Exception {
        // We start by creating a new web3j instance to connect to remote nodes on the network.
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8380"));
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "abcd",
                        "E:\\privateBlockchain\\keystore\\UTC--2019-11-11T09-39-38.582689000Z--e5b2b2ce5130fca7817da53cb2b859568f0614db");
        log.info("Credentials loaded");
        log.info("Sending Ether ..");
        
       
        
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
       String blockdate=df.format(dateobj);
        System.out.println(web3j.ethAccounts().send().getAccounts());
        
    
        
        RawTransactionManager rawTransactionManager =  new RawTransactionManager(web3j, credentials, 4, 1000 * 15);
        EthSendTransaction send = rawTransactionManager.sendTransaction(BigInteger.valueOf(1000000000000L), BigInteger.valueOf(100000L), "0x57a751ff0a996ea3560a4869c984e25f3c6217c3", ASCIItoHEX("sending you money@"+blockdate), BigInteger.valueOf(2));
        String hash = send.getTransactionHash();
        System.out.println(hash);
//        TransactionReceipt transferReceipt1 = Transfer.sendFunds(
//                web3j, credentials,
//                "0x57a751ff0a996ea3560a4869c984e25f3c6217c3",  // you can put any address here
//                BigDecimal.valueOf(3), Convert.Unit.FINNEY)  // 1 wei = 10^-18 Ether
//                .sendAsync().get();
        
//        System.out.println( transferReceipt.getTransactionHash());
//        log.info("Transaction complete : "
//                + transferReceipt.getTransactionHash());
    }

    public static String ASCIItoHEX(String ascii)  { 
        // Initialize final String 
        String hex = ""; 

        // Make a loop to iterate through 
        // every character of ascii string 
        for (int i = 0; i < ascii.length(); i++) { 

            // take a char from 
            // position i of string 
            char ch = ascii.charAt(i); 

            // cast char to integer and 
            // find its ascii value 
            int in = (int)ch; 

            // change this ascii value 
            // integer to hexadecimal value 
            String part = Integer.toHexString(in); 

            // add this hexadecimal value 
            // to final string. 
            hex += part; 
        } 
        // return the final string hex 
        return hex; 
    } 
}
