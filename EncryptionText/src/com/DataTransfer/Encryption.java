package com.DataTransfer;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;



public class Encryption {
	
	
	private static final int AES_KEY_SIZE =256;
	private static Cipher cipher;

	public static void main(String[] args) {
		
		
		String data="Hello World";
		
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
	        keyGen.init(AES_KEY_SIZE);
	        SecretKey secretKey = keyGen.generateKey();
	        
	        String encryption = encryption(data,secretKey);
	        System.out.println(encryption);
	        String decryption = decryption(encryption,secretKey);
	        System.out.println(decryption);
	    			
		} catch (Exception e) {
			System.out.println(e);
		} 

	}
	
	
	public static String encryption(String data, SecretKey secretKey) {
		
		String encryption=null;
		
		try {
		cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encrypted=cipher.doFinal(data.getBytes());
		encryption = Base64.getEncoder().encodeToString(encrypted);
				
		}
		catch(Exception e){
			System.out.println(e);
			
		}
		return encryption;
		
	}
	
	public static String decryption(String enc, SecretKey key) {
		String original=null;
		try {
			
			byte[] encByte=Base64.getDecoder().decode(enc);
			cipher=Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decrypted = cipher.doFinal(encByte);
			original=new String(decrypted);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		
		
		return original;
	}
	
	

	

}
