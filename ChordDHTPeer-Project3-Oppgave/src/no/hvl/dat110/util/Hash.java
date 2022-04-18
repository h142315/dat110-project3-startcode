package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint;
	private static MessageDigest messageDigest;
	
	public static BigInteger hashOf(String entity) {		
		
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		
		// we use MD5 with 128 bits digest
		try {
			// compute the hash of the input 'entity'
			messageDigest = MessageDigest.getInstance("MD5");
			byte[] msgDigest = messageDigest.digest(entity.getBytes());
			
			// convert the hash into hex format
			String hex = toHex(msgDigest);
			
			// convert the hex into BigInteger
			hashint = new BigInteger(hex, 16);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
				
		// return the BigInteger
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		// Task: compute the address size of MD5
		// get the digest length
		// compute the number of bits = digest length * 8
		int bitsize = bitSize();
		
		// compute the address size = 2 ^ number of bits
		BigInteger base = new BigInteger("2");
		BigInteger address = base.pow(bitsize);
		
		// return the address size
		return address;
	}
	
	public static int bitSize() {
		
		int digestlen = 0;
		
		// find the digest length
		try {
			// getting the messageDigest for MD5.
			messageDigest = MessageDigest.getInstance("MD5");
			
			// using the getDigestLength-method to get length
			digestlen = messageDigest.getDigestLength();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
