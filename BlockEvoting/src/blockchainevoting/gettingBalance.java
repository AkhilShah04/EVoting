package blockchainevoting;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

public class gettingBalance {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		// connect to node
		Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));  // defaults to http://localhost:8545/

		// send asynchronous requests to get balance
		
		String accountnum="0x60b6358188642dede7a38eaf4e5f30305b1f0c56";
		
		EthGetBalance ethGetBalance = web3
		  .ethGetBalance(accountnum, DefaultBlockParameterName.LATEST)
		  .sendAsync()
		  .get();
		//EthGetBalance balanceWei = web3.ethGetBalance(accountnum, DefaultBlockParameterName.LATEST).send();
		BigInteger wei = ethGetBalance.getBalance();
		
		System.out.println("balance is wei "+wei);
		BigDecimal balanceInEther = Convert.fromWei(wei.toString(), Unit.ETHER);
		System.out.println("balance is decimal  "+balanceInEther);
		
	}
	public static String getbalanceofuser(String userid) throws InterruptedException, ExecutionException
	{
		Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));  // defaults to http://localhost:8545/

		// send asynchronous requests to get balance
		
		String accountnum=userid;
		
		EthGetBalance ethGetBalance = web3
		  .ethGetBalance(accountnum, DefaultBlockParameterName.LATEST)
		  .sendAsync()
		  .get();
		//EthGetBalance balanceWei = web3.ethGetBalance(accountnum, DefaultBlockParameterName.LATEST).send();
		BigInteger wei = ethGetBalance.getBalance();
		
		System.out.println("balance is wei "+wei);
		BigDecimal balanceInEther = Convert.fromWei(wei.toString(), Unit.ETHER);
		System.out.println("balance is decimal  "+balanceInEther);
		
		return String.valueOf(balanceInEther);
	}

}
