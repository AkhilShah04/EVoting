package blockchainevoting;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import io.reactivex.disposables.Disposable;

public class getRawTransactiondata {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

		// TODO Auto-generated method stub
		// connect to node
		Web3j web3j = Web3j.build(new HttpService("http://localhost:8380"));  // defaults to http://localhost:8545/

		// send asynchronous requests to get balance
		
//		String accountnum="0x57a751ff0a996ea3560a4869c984e25f3c6217c3";
//		
//		//web3.
//		//0x76aeb69eb9ed125f30b9a6709cbda795c859f2bcfde286c8f5a512f82a76bf88
//		List<EthBlock.TransactionResult> txs = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock().getTransactions();
//		System.out.println(txs);
//		txs.forEach(tx -> {
//		  EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) tx.get();
//
//		  System.out.println(transaction.getFrom());
//		});
//		
		//Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/......."));
		//Block block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
		//System.out.println(block.getNumber().toString());
		
		//System.out.println(web3j.ethG);
		//Transaction ethTransaction1=web3j.ethGetTransactionByHash("0x76aeb69eb9ed125f30b9a6709cbda795c859f2bcfde286c8f5a512f82a76bf88").send().getTransaction().get();
		
		//System.out.println(ethTransaction1);
		String transactionhash= "0xa4169b9bcb1ab780abc17925ecaed30c59bb2ea4b34ad8652387e2aa04d413c6";
//		web3j.ethGetBlockByHash("", false).send().getBlock().getTimestamp();
		EthTransaction ethTransaction = web3j.ethGetTransactionByHash(
				transactionhash ).send();
		
		Transaction transaction = ethTransaction.getTransaction().get();
		System.out.println(transaction.getTo());//0x57a751ff0a996ea3560a4869c984e25f3c6217c3
		System.out.println(transaction.getBlockHash()+" "+transaction.getFrom());//0x80694931cdfb7eec7e874ec05f27f3b0eb7a9009abbcc9fc8090b62002bf12f4 0xe5b2b2ce5130fca7817da53cb2b859568f0614db
		System.out.println(transaction.getGasRaw());//0x5208
		System.out.println(transaction.getNonceRaw());//0x2
		System.out.println(transaction.getHash());//0xff668fb1b0c138fe3b9613d7d3b9f771de1f51f090ef026993cd6fb955edc18e
		System.out.println(transaction.getInput());//0x
		System.out.println(transaction.getInput());//0x
		System.out.println(transaction.getChainId());//null
		System.out.println(transaction.getBlockNumberRaw());//0x1dd
//		System.out.println(transaction.getBlockNumber());//
		
//		System.out.println("Time when transaction occured "+web3j.ethGetBlockByHash(transactionhash, true).send().getBlock().getTimestamp());
		
		//System.out.println("Time when transaction occured "+web3j.ethGetBlockByHash(transactionhash, false).send().getBlock().getHash());
		
//		String fromdb=web3j.dbGetString("str1", "str2").send().getResult().toString();
//		System.out.println(fromdb);
		
		//web3j.
		String hexString = transaction.getInput();

		// Remove the prefix 0x 
		if(hexString.startsWith("0x")) {
		    hexString = hexString.substring(2);
//		    System.out.println();
		}

		// Convert Base16 into a byte array 
		byte[] byteArray = new BigInteger(hexString, 16).toByteArray();

		// Pass the byte array to a String will re-encode it in ASCII.
		String asciiString = new String(byteArray);

		System.out.println(hexString+"="+asciiString);
		
		
		//System.out.println(transaction.);//0x1dd
		System.out.println(transaction.getCreates());//null
		System.out.println(transaction.getGasPriceRaw());//0x3b9aca00
		
		System.out.println(transaction.getR());//0xc069260ddbdb1989c323d63eaf1d7e60368d695938993b19f960e04d509c9d18
		System.out.println(transaction.getRaw());//null
		System.out.println(transaction.getValue());//1000000000000000000
		
		
		BigDecimal balanceInEther = Convert.fromWei(transaction.getValue().toString(), Unit.ETHER);
		System.out.println(balanceInEther);//1
		System.out.println(transaction.getBlockNumber());//1212
		
		String datatobe[]=asciiString.split("@");
		
         System.out.println("timestamp is "+datatobe[1]);
		 
		System.out.println(transaction.getNonce());//5 times transaction done by sender to this number 
	}
	
	  void blockInfoExample() throws Exception {
		  int COUNT=10;
			Web3j web3j = Web3j.build(new HttpService("http://localhost:8280"));  // defaults to http://localhost:8545/

	        CountDownLatch countDownLatch = new CountDownLatch(COUNT);

//	        log.info("Waiting for " + COUNT + " transactions...");
	        Disposable subscription = web3j.blockFlowable(true)
	                .take(COUNT)
	                .subscribe(ethBlock -> {
	                    EthBlock.Block block = ethBlock.getBlock();
	                    LocalDateTime timestamp = Instant.ofEpochSecond(
	                            block.getTimestamp()
	                                    .longValueExact()).atZone(ZoneId.of("UTC")).toLocalDateTime();
	                    int transactionCount = block.getTransactions().size();
	                    String hash = block.getHash();
	                    String parentHash = block.getParentHash();

//	                    log.info(
//	                            timestamp + " " +
//	                                    "Tx count: " + transactionCount + ", " +
//	                                    "Hash: " + hash + ", " +
//	                                    "Parent hash: " + parentHash
//	                    );
	                    countDownLatch.countDown();
	                }, Throwable::printStackTrace);

	        countDownLatch.await(10, TimeUnit.MINUTES);
	        subscription.dispose();
	    }

}
