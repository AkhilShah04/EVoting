package blockchainevoting;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

public class creatingaccount {
	
	public static void main(String args[]) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException
		{
		Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));
		String walletFileName = WalletUtils.generateFullNewWalletFile("123456",new File("E:\\privateBlockchain\\keystore\\"));
		System.out.println(walletFileName);
		String[] fetchAddress=walletFileName.split("--");

		String getAddress = fetchAddress[fetchAddress.length-1].split("\\.")[0];
		
		System.out.println("address "+getAddress);
//		EthGetBalance balanceWei = web3.ethGetBalance(getAddress, DefaultBlockParameterName.LATEST).send();
//		System.out.println("balance in wei: " + balanceWei);
//		BigDecimal balanceInEther = Convert.fromWei(balanceWei.getBalance().toString(), Unit.ETHER);
//		System.out.println("balance in ether: " + balanceInEther);
		}
	
	public static String createblockchainaccount(String passsowrd) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException
	{
		Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));
		String walletFileName = WalletUtils.generateFullNewWalletFile(passsowrd,new File(config.keypath));
		System.out.println(walletFileName);
		String[] fetchAddress=walletFileName.split("--");

		String getAddress = fetchAddress[fetchAddress.length-1].split("\\.")[0];
		getAddress=getAddress+"@SP"+walletFileName;
		System.out.println("address "+getAddress);
		return getAddress;
	}
}
