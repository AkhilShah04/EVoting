package blockchainevoting;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class CreatingFingerprintBlock {

	
	public static void main(String args[]) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException
		{
		Web3j web3 = Web3j.build(new HttpService("http://localhost:8280"));
		
		String password="Rk1SACAyMAAAAAD2AAABQAHgAMUAxQEAAAAeJEC3AJVsO0DvAKbmLkDHAMbpGkDQAMZnGkBzAMh4SEB1ANz4O0BLAN2LSEDaAN9sIUBxAOb4NEDNAO3pNEBXAPWJZECzAPziO0C8AQZdO0BrAQ57O0D4AR5VIUB2ASFrLkDzASLWGkBwATnsNEA8AU/DSEBeAZTbNECSAZlmZID6AI5mIYC6AJ/tLoCLAKFzSIDjAKVnO4BdAL0DSIDTAORtJ4CIAOZuNIDtAPjhSIBhAR8GGoBxATFxIYBKATcxGoC5AVFZZIB0AVnPZIDOAWZeZIC8AZ5tNAAA";
		String walletFileName = WalletUtils.generateFullNewWalletFile(password,new File("E:\\privateBlockchain\\keystore\\"));
		System.out.println(walletFileName);
		String[] fetchAddress=walletFileName.split("--");

		String getAddress = fetchAddress[fetchAddress.length-1].split("\\.")[0];
		
		System.out.println("address "+getAddress);
//		EthGetBalance balanceWei = web3.ethGetBalance(getAddress, DefaultBlockParameterName.LATEST).send();
//		System.out.println("balance in wei: " + balanceWei);
//		BigDecimal balanceInEther = Convert.fromWei(balanceWei.getBalance().toString(), Unit.ETHER);
//		System.out.println("balance in ether: " + balanceInEther);
		}
	
	

}
