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

public class getblockinfotimestamp {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		
		

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
System.out.println(hash +" "+timestamp);
//	                    log.info(
//	                            timestamp + " " +
//	                                    "Tx count: " + transactionCount + ", " +
//	                                    "Hash: " + hash + ", " +
//	                                    "Parent hash: " + parentHash
//	                    );
	                    countDownLatch.countDown();
	                }, Throwable::printStackTrace);

	        countDownLatch.await(10, TimeUnit.SECONDS);
	        subscription.dispose();
	    
	}
	
	



}
