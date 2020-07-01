package Encyption;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
public class StrongAES 
{
	public static String key="Bar12345";
    public void run() 
    {
        try 
        {
            String text = "Hello World";
            String key = "Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            
            System.err.println(new String(encrypted));
            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(encrypted));
            System.err.println(decrypted);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
    public static String Encrypting(String datain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
    {
    	
    	  String text = datain;
    	  Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
          Cipher cipher = Cipher.getInstance("AES");
          // encrypt the text
          cipher.init(Cipher.ENCRYPT_MODE, aesKey);
          byte[] encrypted = cipher.doFinal(text.getBytes());
       String encdata=new String(encrypted);
	return encdata;
    	
    }
  
    public static void main(String[] args) 
    {
        StrongAES app = new StrongAES();
        app.run();
    }
}