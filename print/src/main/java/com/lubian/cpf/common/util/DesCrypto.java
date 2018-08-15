package com.lubian.cpf.common.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesCrypto {
	public final static String DEFAULT_KEY = "PKCS5Pad";
	protected final static String DES = "DES";
	private static DesCrypto _instance = null;
	
    public static DesCrypto getInstance() {
        if (_instance == null) {
            _instance = new DesCrypto();  
        }
        return _instance;  
    }
    
	public String getKey(){
		return DEFAULT_KEY;
	}
	
    public String encrypt(String src) {
        try {
            return byte2hex(encrypt(getKey().getBytes(), src.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }  
    
    public String decrypt(String src) {
        try {
            return new String(decrypt(getKey().getBytes(), hex2byte(src.getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }  
    
    /** 
     * encrypt
     * @param key
     * @param src
     * @return
     * @throws Exception 
     */
    private byte[] encrypt(byte[] key, byte[] src) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey sk = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, sk, sr);
        return cipher.doFinal(src);
    }  
    
    /** 
     * decrypt
     * @param key
     * @param src
     * @return
     * @throws Exception 
     */  
    private byte[] decrypt(byte[] key, byte[] src) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey sk = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, sk, sr);
        return cipher.doFinal(src);  
    }  
    
    /** 
     * @param b 
     * @return 
     */  
    public String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp;  
  
        for (byte aB : b) {  
            stmp = (Integer.toHexString(aB & 0XFF));  
            if (stmp.length() == 1) {  
                hs = hs + "0" + stmp;  
            } else {  
                hs = hs + stmp;  
            }  
        }    
        return hs.toLowerCase();  
    }  
        
    /**
     * @param b 
     * @return 
     */  
    public byte[] hex2byte(byte[] b) {  
        if ((b.length % 2) != 0) {  
            throw new IllegalArgumentException("invalid length,should be even");  
        }  
        byte[] b2 = new byte[b.length / 2];  
        for (int n = 0; n < b.length; n += 2) {  
            String item = new String(b, n, 2);  
            b2[n / 2] = (byte) Integer.parseInt(item, 16);  
        }  
        return b2;  
    }  
	
	public static void main(String args[]) {
		String str = "12";
		System.out.println(DesCrypto.getInstance().encrypt(str));
		System.out.println(DesCrypto.getInstance().decrypt(DesCrypto.getInstance().encrypt(str)));
		System.out.println("\\u"+DesCrypto.getInstance().byte2hex("把".getBytes()));
	}
}
