package com.lubian.cpf.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class FileUtil {
	
	public static String readFile(String fPath){
		
		return "";
	}
	
	public static void checkFolder(String path){
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
	}	
	
	/**
	 * 获取地址路径中的文件名
	 * @param fullImg
	 * @return
	 */
	public static String getFileName(String fullImg){
		if(StringUtils.isBlank(fullImg))return "";
		if(fullImg.indexOf("/")!=-1){
			return fullImg.substring(fullImg.lastIndexOf("/")+1,fullImg.length());
		}
		return fullImg;
	}
		
	/**
	 * 获取图片内容，base64编码
	 * @param filePath
	 * @return
	 */
	public static String getBase64File(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			byte[] fileBytes = new byte[fis.available()];
			fis.read(fileBytes);
			String fileString = new String(Base64.encodeBase64(fileBytes));
			fis.close();
			return fileString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param path 图片最终被保存的路径
	 * @param content base64编码过的图像字符串
	 * @return
	 */
	public static boolean saveBase64Pic(String path, String content){
		try {
			byte[] b = Base64.decodeBase64(content.trim().getBytes());
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(b);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	/**
	 * 如果上传文件名重复，处理文件名
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static File getUniqueFile(String path,String fileName){
		File tf = new File(path,fileName);
		if(!tf.exists())return tf;
    	String pname = fileName.substring(0,fileName.lastIndexOf('.'));
    	String suffix = fileName.substring(fileName.lastIndexOf('.'));
  		int i = 1;
		while (true) {
			String tempName = pname + i + suffix;
			tf = new File(path,tempName);
			if(!tf.exists())return tf;
			i++;
		}
	}
	
	
	/**
	 * 功 能: 删除指定的文件 参 数: 指定绝对路径的文件名 strFileName 返回值: 如果删除成功true否则false;
	 * 
	 * @param strFileName
	 * @return
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);

		if (!fileDelete.exists() || !fileDelete.isFile()) {
			return false;
		}

		return fileDelete.delete();
	}
	
	/**
	 * 取得指定目录下的所有文件列表，包括子目录.
	 * 
	 * @param baseDir
	 *            File 指定的目录
	 * @return 包含java.io.File的List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getSubFiles(File baseDir) {
		List ret = new ArrayList();
		File[] tmp = baseDir.listFiles();
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].isFile())
				ret.add(tmp[i]);
			if (tmp[i].isDirectory())
				ret.addAll(getSubFiles(tmp[i]));
		}
		return ret;
	}
}
