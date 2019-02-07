package com.cts.tshell.bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
public class Util {

	public static String encryptToMD5(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(input.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++)
		sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		String encryptedPassword = "";
		encryptedPassword = sb.toString();
		return encryptedPassword;
	}

	public static int generateSignupOTP() {
		Random rand = new Random();
		return rand.nextInt(899999) + 100000;
	}

	public static int generateOTP() {
		Random ramd = new Random();
		return ramd.nextInt(899999)+100000;
	}
}


