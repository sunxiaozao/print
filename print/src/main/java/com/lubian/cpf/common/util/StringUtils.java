package com.lubian.cpf.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	static final String KEY = "hehzddh";

	public static List<Object> getDiffrent1(List list){
		for (int i = 0; i < list.size() - 1; i++) {                            
	         for (int j = list.size() - 1; j > i; j--) {                       
	              if (list.get(j)==list.get(i)) {
	            	  list.remove(j);
	              }
	        }
	    }
		return list;
	}
	/**
	 * 返回list1 中和list2 不相同的部分
	 * @param list1
	 * @param list2
	 * @return list1中和list中不同的部分
	 */
	public static List<Integer> getDiffrent(List<Integer> list1,List<Integer> list2){
		
		Map<Integer, Integer> map=new HashMap<Integer, Integer>(list1.size()+list2.size());
		List<Integer> diff=new ArrayList<Integer>();
		List<Integer> maxList=list1;
		List<Integer> minList=list2;
		for (Integer string : maxList) {
			map.put(string, 1);
			
		}
		for (Integer string : minList) {
			Integer cc=map.get(string);
			if (null!=cc) {
				//移除相同的部分
				map.remove(string);
				continue;
			}
		}
		for (Map.Entry<Integer, Integer> entry:map.entrySet()) {
			if (entry.getValue()==1) {
				diff.add(entry.getKey());
			}
		}
		return diff;
	}

	
	/**
	 * 查询该字符串中是否存在某字符
	 * @param strings
	 * @param str
	 * @return
	 */
	public static boolean checkeStr(String strings,String str) {
		if (strings.indexOf(str)<=0) {
			return false;
		}
		return true;
	}
	
	/**
	 * concat array String  by 任何符号
	 * @param strings
	 * @param str
	 * @return
	 */
	public static String[] getStringArrayByChar(String strings,String str) {
		String[] list = strings.split(str);
		for (int i=0; i<list.length; i++) {
			if(list[i].contains("'"))
				list[i] = list[i].replace("'", "");
		}
		return list;
	}
	
	/**
	 * 去除list 中的重复值（商户名字补全）
	 * @param list
	 * @return
	 */
	public static List<String> removeDuplicate(List<String> list) {
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}
	
	public static String getUtfStr(String str) {
		if (str == null)
			return null;
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static String encodeURL(String str) {
		String text = "";
		try {
			text = URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;
	}

	public static String decodeURL(String str) {
		String text = "";
		try {
			text = URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	
	public static String nullToEmpty(String str)
	{
		return str != null ? str : "";
	}
	
	public static boolean isBlank(String str) {
		return str == null || "".equals(str);
	}

	public static String simpleEncrypt(String str) {
		StringBuffer sb = new StringBuffer(str);

		int lenStr = str.length();
		int lenKey = KEY.length();

		for (int i = 0, j = 0; i < lenStr; i++, j++) {
			if (j >= lenKey)
				j = 0;

			sb.setCharAt(i, (char) (str.charAt(i) ^ KEY.charAt(j)));
		}

		return sb.toString();
	}

	public static String simpleDecrypt(String str) {
		return simpleEncrypt(str);
	}

	public static String encrypt(String str) {
		return md5(str);
	}

	public static String md5(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(source.getBytes("UTF-8"));
			return getString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 将字符串中的全角空格转换成半角空格
	public static final String fullToHalfSpace(String ss) {
		byte[] t = ss.getBytes();
		for (int i = 0; i < t.length; i++) {
			if (t[i] == -95 && t[i + 1] == -95) {
				t[i] = 32;
				if (i + 1 == t.length - 1) {
					t[i + 1] = 0;
				} else {
					for (int j = i + 1; j + 1 < t.length; j++) {
						t[j] = t[j + 1];
						if (j + 1 == t.length - 1)
							t[j + 1] = 32;
					}
				}

			}
		}
		return new String(t);
	}
	
	//获取六位随机数字
	public static final int getRandomSixNUM(){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for(int i = 0; i < 6; i++)
            result = result * 10 + array[i];
        //System.out.println(result);
        return result;
	}

	/**
	 * 随机生成6位字符数组
	 * 
	 * @return rands
	 */
	public static String getRandomSixString() {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int len = 6;
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
	
	/**
	 * 把逗号分隔的字符串变成sql的in查询的字符串(a,b -> 'a','b')
	 * @param input
	 * @return
	 */
	public static String convertInStr(List list){
		if(null == list || list.size()==0)
			return null;
		String[] arr = new String[list.size()];
		list.toArray(arr);
		if(arr.length==1){
			return "'"+arr[0]+"'";
		}
		Set set = new HashSet();
		StringBuffer buf = new StringBuffer();
		for(int i=0; i<arr.length; i++){
			if(StringUtils.isBlank(arr[i])
					|| set.contains(arr[i]))continue;
			if(buf.length()>0)buf.append(",");
			buf.append("'").append(arr[i]).append("'");
			set.add(arr[i]);
		}
		return buf.toString();
	}
	
	/**
	 * 把逗号分隔的number变成sql的in查询的字符串(a,b)
	 * @param input
	 * @return
	 */
	public static String convertInInt(List list){
		if(null == list || list.size()==0)
			return null;
		Integer[] arr = new Integer[list.size()];
		list.toArray(arr);
		if(arr.length==1){
			return arr[0]+"";
		}
		Set set = new HashSet();
		StringBuffer buf = new StringBuffer();
		for(int i=0; i<arr.length; i++){
			if(set.contains(arr[i]))continue;
			if(buf.length()>0)buf.append(",");
			buf.append(arr[i]);
			set.add(arr[i]);
		}
		return buf.toString();
	}
	
	private static String getString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			String hex = Integer.toHexString((int) 0x00FF & b);
			if (hex.length() == 1) {
				sb.append("0");
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	
	/** 
	* 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF 
	* @param src0 byte 
	* @param src1 byte 
	* @return byte 
	*/  
	public static byte uniteBytes(byte src0, byte src1) {  
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();  
		_b0 = (byte) (_b0 << 4);  
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();  
		byte ret = (byte) (_b0 ^ _b1);  
		return ret;
	} 
	
	/** 
	* 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9} 
	* @param src String 
	* @return byte[] 
	*/  
	public static byte[] hexString2Bytes(String src) {
		byte[] ret = new byte[src.length()/2];  
		byte[] tmp = src.getBytes();  
		for (int i = 0; i < src.length()/2; i++) {  
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);  
		}  
		return ret;  
	}  
	   
	public static int toInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static double toDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static long toLong(String str) {
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int toInt(Object str) {
		return toInt((String) str);
	}

	public static boolean toBoolean(Object str) {
		try {
			return Boolean.parseBoolean((String) str);
		} catch (Exception e) {
			return false;
		}
	}

	public static String buildUrl(Map<String, Object> map) {
		StringBuilder sbr = new StringBuilder();
		if (map == null || map.isEmpty()) {
			return sbr.toString();
		}
		Iterator<String> it = map.keySet().iterator();
		Object obj = null;
		String key = null;

		boolean first = true;
		while (it.hasNext()) {
			key = it.next();
			obj = map.get(key);
			if (first) {
				first = false;
			} else {
				sbr.append("&");
			}
			if (obj != null) {
				sbr.append(key).append("=").append(obj);
			}
		}
		return sbr.toString();

	}

	public static boolean checkIdList(String ids) {
		if (StringUtils.isBlank(ids)) {
			return false;
		} else {
			String[] idList = ids.split(",");
			for (String id : idList) {
				if (!id.matches("['a-zA-Z-\\d]*")) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * concat string array by ,
	 * 
	 * @param ids
	 * @return
	 */
	public static String getArrayString(String[] ids) {
		if (ids != null) {
			StringBuilder sbr = null;
			for (String id : ids) {
				if (sbr == null) {
					sbr = new StringBuilder();
				} else {
					sbr.append(",");
				}
				sbr.append(id);
			}
			if (sbr != null) {
				return sbr.toString();
			}
		}
		return null;
	}

	/**
	 * concat String array by 'a',
	 * 
	 * @param ids
	 * @return
	 */
	public static String getArrayString2(String[] ids) {
		if (ids != null) {
			StringBuilder sbr = null;
			for (String id : ids) {
				if (sbr == null) {
					sbr = new StringBuilder();
				} else {
					sbr.append(",");
				}
				sbr.append("'");
				sbr.append(id);
				sbr.append("'");
			}
			if (sbr != null) {
				return sbr.toString();
			}
		}
		return null;
	}
	
	/**
	 * concat array String  by "'a','b'",
	 * 
	 * @param ids
	 * @return
	 */
	public static String[] getStringArray(String ids) {
		String[] list = ids.split(",");
		for (int i=0; i<list.length; i++) {
			if(list[i].contains("'"))
				list[i] = list[i].replace("'", "");
		}
		return list;
	}
	

	public static List<Long> strToList(String str) {
		List<Long> idList = new ArrayList<Long>();
		if (!StringUtils.isBlank(str)) {
			String[] idx = str.split(",");
			for (String id : idx) {
				long pid = StringUtils.toLong(id);
				if (pid > 0) {
					idList.add(pid);
				}
			}

		}

		return idList;
	}
	
	/***
	* 
	* @param content 内容String
	* @param p >0 .位数
	* @return @tale:
	* @purpose：得到相应位数已过滤html、script、style 标签的内容
	*/
	public static String getNoHTMLString(String content,int p){
	  
		if(null==content) return "";
		if(0==p) return "";
	  
		java.util.regex.Pattern p_script; 
		java.util.regex.Matcher m_script; 
		java.util.regex.Pattern p_style; 
		java.util.regex.Matcher m_style; 
		java.util.regex.Pattern p_html; 
		java.util.regex.Matcher m_html; 
	        
		try { 
	         String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
	         //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> } 
	         String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
	         //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> } 
	         String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 
	           
	         p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	         m_script = p_script.matcher(content); 
	         content = m_script.replaceAll(""); //过滤script标签

	         p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	         m_style = p_style.matcher(content); 
	         content = m_style.replaceAll(""); //过滤style标签 
	           
	         p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	         m_html = p_html.matcher(content); 
	         
	         content = m_html.replaceAll(""); //过滤html标签 
	         
	         content = content.replaceAll("&nbsp;", "");
	         content = content.replaceAll(" ", "");
	     	}catch(Exception e) { 
	                   return "";
	     	} 
	  
	     	if(content.length()>p){
	     		content = content.substring(0, p)+"...";
	   }
	  
	   return content;
	}
	
    public static String encodeStr(String plainText) throws UnsupportedEncodingException{  
        byte[] b = plainText.getBytes();
        String s = new sun.misc.BASE64Encoder().encode(b);
        return s;  
    }  
    
    public static String decodeStr(String encodeStr) throws IOException{
    	byte[] b = new sun.misc.BASE64Decoder().decodeBuffer(encodeStr.trim());
        String s=new String(b);  
        return s;  
    }  
    	
	/**
	 * 把库表字段名称变为java中驼峰名称，如year_month -> yearMonth
	 * @param name
	 * @return
	 */
	public static String getCellName(String name){
		if(StringUtils.isBlank(name) || name.indexOf("_") == -1)return name;
		StringBuffer ret = new StringBuffer();
		String temp;
		String[] ns = name.split("_");		
		for(int i=0; i<ns.length; i++){
			temp = ns[i];
			if(i==0){
				ret.append(temp.toLowerCase());
			}else{
				ret.append(temp.substring(0,1).toUpperCase());
				if(temp.length()>1){
					ret.append(temp.substring(1).toLowerCase());
				}	
			}			
		}
		return ret.toString();
	}
	
	// 获取MAC地址的方法
	public static String getMACAddress(InetAddress ia) throws Exception {
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

		// 下面代码是把mac地址拼装成String
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}

		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
	}
	
    public static String byte2hex(byte[] src) {  
        StringBuilder stringBuilder = new StringBuilder("");  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        for (int i = 0; i < src.length; i++) {  
            int v = src[i] & 0xFF;  
            String hv = Integer.toHexString(v);  
            if (hv.length() < 2) {  
                stringBuilder.append(0);  
            }  
            stringBuilder.append(hv);  
        }  
        return stringBuilder.toString();  
    }  

    public static String getUtf8Code(String val){
    	if(val==null)return "";
    	try {
			return "\\u" + StringUtils.byte2hex(val.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
    }
    
	/**
	 * isNumeric: Validate a number using Java regex. This method checks if the
	 * input string contains all numeric characters.
	 * 
	 * @param email
	 *            String. Number to validate
	 * @return boolean: true if the input is all numeric, false otherwise.
	 */

	public static boolean isNumeric(String number) {
		boolean isValid = false;

		/*
		 * Number: A numeric value will have following format: ^[-+]?: Starts
		 * with an optional "+" or "-" sign. [0-9]*: May have one or more
		 * digits. \\.? : May contain an optional "." (decimal point) character.
		 * [0-9]+$ : ends with numeric digit.
		 */

		// Initialize reg ex for numeric data.
		String expression = "^[-+]?[0-9]*\\.?[0-9]+$";
		CharSequence inputStr = number;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}
    
	public static void main(String args[]) {
		String asdf = "把";
		try {
			asdf = new String(asdf.getBytes("UTF-8"));
			asdf = URLEncoder.encode(asdf, "UTF-8");  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(asdf);
	}
	
}
