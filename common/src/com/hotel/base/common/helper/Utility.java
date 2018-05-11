package com.hotel.base.common.helper;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author pkGupta
 * 
 */
public class Utility
{
	public static final String MD5 = "MD5";

	/**
	 * 
	 * @param password String
	 * @return String
	 * @throws RuntimeException
	 */
	public static String getMd5(String password) throws RuntimeException
	{
		String md5String = null;
		try
		{
			byte[] passwordBytes = password.getBytes();
			MessageDigest algorithm = MessageDigest.getInstance(MD5);
			algorithm.reset();
			algorithm.update(passwordBytes);
			byte messageDigest[] = algorithm.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
			{
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			md5String = hexString.toString();
		} catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("a");
		}
		return md5String;
	}
}
