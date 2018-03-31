package com.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.catalina.tribes.util.Arrays;

public class Hash {
	private final static String ALGO = "SHA-256";
	
	public static byte[] hash(String data, byte[] salt){
		byte[] hashedData = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance(ALGO);
			md.update(salt);
			md.update(data.getBytes());
			hashedData = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hashedData;
	}
	
	public static boolean compare(byte[] hashedData, String data, byte[] salt){
		boolean equal = false;
		byte[] hashedData2 = hash(data, salt);
		
		//check if hashData is same with hashData2
		equal = Arrays.equals(hashedData, hashedData2);
		
		return equal;
	}
}
