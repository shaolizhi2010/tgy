package com.tgy.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
 
	
	public static String toMD5(String msg){
		if(msg==null){
			return "";
		}
		msg = msg+ P.getProperty("authToken");
		BigInteger b = null;
		try {
			b = new BigInteger(MessageDigest.getInstance("md5").digest(msg.getBytes()));
			return b.toString();
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
		
	}
	 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println( toMD5("abc"));

	}

}
