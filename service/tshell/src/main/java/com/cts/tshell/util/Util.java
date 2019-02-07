package com.cts.tshell.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

}
