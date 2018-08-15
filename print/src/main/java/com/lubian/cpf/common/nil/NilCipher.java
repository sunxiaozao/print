package com.lubian.cpf.common.nil;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.constant.Enums.Relation;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;

import freemarker.template.SimpleDate;

/**
 * nil加密类
 * 
 * @author Administrator
 * 
 */
public class NilCipher {

	public NilCipher(String key) throws Exception {
		secretKeySpec = new SecretKeySpec(getKeyBytes(key), encryptionAlgorithm);
		ivParameterSpec = new IvParameterSpec(new byte[16]);
	}

	public String encrypt(String text) throws Exception {
		return Base64.encodeBase64String(encrypt(text
				.getBytes(characterEncoding)));
	}

	public byte[] encrypt(byte[] textBytes) throws Exception {
		Cipher cipher = Cipher.getInstance(cipherTransformation);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		return cipher.doFinal(textBytes);
	}

	private byte[] getKeyBytes(String key) throws Exception {
		MessageDigest md = MessageDigest.getInstance(messageDigestAlgorithm);
		byte[] bytes = key.getBytes(characterEncoding);
		md.update(bytes, 0, bytes.length);
		byte[] digest = md.digest();
		byte[] keyBytes = new byte[16];
		System.arraycopy(digest, 0, keyBytes, 0,
				Math.min(digest.length, keyBytes.length));
		return keyBytes;
	}

	private SecretKeySpec secretKeySpec;
	private IvParameterSpec ivParameterSpec;
	private final String characterEncoding = "UTF-8";
	private final String encryptionAlgorithm = "AES";
	private final String cipherTransformation = "AES/CBC/PKCS5Padding";
	private final String messageDigestAlgorithm = "SHA-1";

	/**
	 * nil加密
	 * 
	 * @param str
	 *            标记
	 * @param strEncryption
	 *            要加密的字符串
	 * @return
	 */
	public static String Encryption(String str, String strEncryption) {

		try {
			return new NilCipher(str).encrypt(strEncryption);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String EncryptionUrl(String str, String strEncryption) {

		try {
			String blob = new NilCipher(str).encrypt(strEncryption);

			return URLEncoder.encode(blob, "UTF-8");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String EncryptionUrl(CpfCaseHistory caseHistory) {
		
			StringBuffer sf = new StringBuffer();
			if (caseHistory.getStudy() != null) {
				String study = "study=" + caseHistory.getStudy();
				sf.append(study);
			}
			if (caseHistory.getPid() != null) {
				String pid = "pid=" + caseHistory.getPid();
				sf.append("&").append(pid);
			}

			if (sf.length() > 0) {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String tm = "tm=" + df.format(new Date());
				sf.append("&").append(tm);
				String str;
				try {
					String s = new NilCipher("xiayutech").encrypt(sf.toString());
					
					String url = "http://182.92.97.156:8080/DirectLink.aspx?cp="
							+ URLEncoder.encode(s, "UTF-8");
					return url;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		

		return null;

	}

	public static void main(String[] args) {
		//Study instance UID
		String study_ = "1.2.124.113532.36.59504.16340.20101208.74214.8149689";
		//Patient Id
		String pid_ = "3911359";
		// 1.3.46.670589.50.2.37439649373699487810.2341317950497587535
		// 580-621-7202
		//MR , yukaiming
		//1.3.12.2.1107.5.2.36.40225.30000014103000060420300000001
		//01173319
		
		//CT: carotid stent & Stenox
		// 1.3.46.670589.33.1.29243307633847137346.30715459181923854168
		// BR64
		
		//US ,gatti claudio
		//1.2.124.113532.36.59504.16340.20101208.74214.8149689
		//3911359
		
		StringBuffer sf = new StringBuffer();
		
		String study = "study=" + study_;
		sf.append(study);
	
	
		String pid = "pid=" + pid_;
		sf.append("&").append(pid);
	

	if (sf.length() > 0) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String tm = "tm=" + df.format(new Date());
		sf.append("&").append(tm);
		String str;
		try {
			String s = new NilCipher("xiayutech").encrypt(sf.toString());
			System.out.println(s);
			String url = "http://182.92.97.156:8080/DirectLink.aspx?cp="
					+ URLEncoder.encode(s, "UTF-8");
			System.out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	}
}
