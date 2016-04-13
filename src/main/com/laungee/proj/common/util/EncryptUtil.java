package com.laungee.proj.common.util;


import java.security.*;

import javax.crypto.Cipher;

import javax.crypto.SecretKey;

import javax.crypto.SecretKeyFactory;

import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * 字符串加,解密.
 *
 * 
 */

public class EncryptUtil {

	private static final String PASSWORD_CRYPT_KEY = "88888888";

	private final static String DES = "DES";

	/**
	 * 
	 * 加密
	 * 
	 * @param src
	 *            数据源
	 * 
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * 
	 * @return 返回加密后的数据
	 * 
	 * @throws Exception
	 * 
	 */

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源

		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建DESKeySpec对象

		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec转换成

		// 一个SecretKey对象

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作

		Cipher cipher = Cipher.getInstance(DES);

		// 用密匙初始化Cipher对象

		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		// 现在，获取数据并加密

		// 正式执行加密操作

		return cipher.doFinal(src);

	}

	/**
	 * 
	 * 解密
	 * 
	 * @param src
	 *            数据源
	 * 
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * 
	 * @return 返回解密后的原始数据
	 * 
	 * @throws Exception
	 * 
	 */

	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源

		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建一个DESKeySpec对象

		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成

		// 一个SecretKey对象

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作

		Cipher cipher = Cipher.getInstance(DES);

		// 用密匙初始化Cipher对象

		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		// 现在，获取数据并解密

		// 正式执行解密操作

		return cipher.doFinal(src);

	}

	/**
	 * 
	 * 密码解密
	 * 
	 * @param data
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */

	public final static String decrypt(String data) {

		try {

			return new String(decrypt(hex2byte(data.getBytes()),

			PASSWORD_CRYPT_KEY.getBytes()));

		} catch (Exception e) {

		}

		return null;

	}

	/**
	 * 
	 * 密码加密
	 * 
	 * @param password
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */

	public final static String encrypt(String password) {

		try {

			return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY
					.getBytes()));
		} catch (Exception e) {

		}

		return null;

	}

	/**
	 * 
	 * 二行制转字符串
	 * 
	 * @param b
	 * 
	 * @return
	 * 
	 */

	public static String byte2hex(byte[] b) {

		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {

			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1)

				hs = hs + "0" + stmp;

			else

				hs = hs + stmp;

		}

		return hs.toUpperCase();

	}

	public static byte[] hex2byte(byte[] b) {

		if ((b.length % 2) != 0)

			throw new IllegalArgumentException("长度不是偶数");

		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {

			String item = new String(b, n, 2);

			b2[n / 2] = (byte) Integer.parseInt(item, 16);

		}

		return b2;
	}
	/**
	 * 加密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 */
	public final static String encrypt(String data, String key) {
		try {
			return byte2hex(encrypt(data.getBytes(), key.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * md5加密
	 * @param s
	 *            数据源
	 * @return 返回加密后的数据
	 */
	public final static String md5(String s) {   
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };   
		try {   
			byte[] strTemp = s.getBytes();   
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");   
			mdTemp.update(strTemp);   
			byte[] md = mdTemp.digest();   
			int j = md.length;   
			char str[] = new char[j * 2];   
			int k = 0;   
			for (int i = 0; i < j; i++) {   
				byte byte0 = md[i];   
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];   
				str[k++] = hexDigits[byte0 & 0xf];   
			}   
			return new String(str);
		} catch (Exception e) {   
			return null;   
		}   
	}
	/**
	 * md5加密
	 * @param s
	 *            数据源
	 * @return 返回加密后的数据
	 */
	public final static String md5(String s, String ecode) {   
		if(ecode==null || "".equals(ecode)){
			return md5(s);
		} 
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };   
		try {   
			byte[] strTemp = s.getBytes(ecode);   
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");   
			mdTemp.update(strTemp);   
			byte[] md = mdTemp.digest();   
			int j = md.length;   
			char str[] = new char[j * 2];   
			int k = 0;   
			for (int i = 0; i < j; i++) {   
				byte byte0 = md[i];   
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];   
				str[k++] = hexDigits[byte0 & 0xf];   
			}   
			return new String(str);
		} catch (Exception e) {   
			return null;   
		}
	}
	/**
	 * BASE64编码
	 * @param s
	 *            数据源
	 * @return 返回编码后的数据
	 */
	public final static String encode64(String s) {  
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			byte[] b = s.getBytes("UTF-8");
	        return encoder.encode(b); 
		} catch (Exception e) {   
			return null;   
		}   
	}
	/**
	 * BASE64编码
	 * @param s
	 *            数据源
	 * @param ecode
	 *            数据源编码格式
	 * @return 返回编码后的数据
	 */
	public final static String encode64(String s, String ecode) { 
		if(ecode==null || "".equals(ecode)){
			return encode64(s);
		} 
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			byte[] b = s.getBytes(ecode);
	        return encoder.encode(b); 
		} catch (Exception e) {   
			return null;   
		}   
	}
	/**
	 * BASE64解码
	 * @param s
	 *            数据源
	 * @return 返回解码后的数据
	 */
	public final static String decode64(String s) {  
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(s);  
			for(int i=0;i<b.length;++i){  
				if(b[i]<0){//调整异常数据  
					b[i]+=256;  
	            }  
	        }
	        return new String(b,"UTF-8"); 
		} catch (Exception e) {   
			return null;   
		}   
	}
	/**
	 * BASE64解码
	 * @param s
	 *            数据源
	 * @param ecode
	 *            数据源编码格式
	 * @return 返回解码后的数据
	 */
	public final static String decode64(String s, String ecode) { 
		if(ecode==null || "".equals(ecode)){
			return decode64(s);
		} 
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(s);  
			for(int i=0;i<b.length;++i){  
				if(b[i]<0){//调整异常数据  
					b[i]+=256;  
	            }  
	        }
	        return new String(b,ecode); 
		} catch (Exception e) {   
			return null;   
		}   
	} 
//	 测试用例，不需要传递任何参数，直接执行即可。
	public static void main(String[] args) {
		String str1 = "";
		String str3 = "123456";
		String str = encrypt(str3);
		String strr = encrypt(str1);
		System.out.println("加密Str3 is : " + str);
		System.out.println("outStr4 is : " + decrypt("A9EAD4B349DB8B201E12E2201A9AFD99"));
		System.out.println("为空时 is : " + decrypt(strr));
	}

}
