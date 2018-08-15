package com.lubian.cpf.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.lubian.cpf.common.constant.Constant;

public class Dowload {

	/**
	 * 下载excel
	 * 
	 * @param response
	 * @param request
	 * @param url
	 * @throws Exception
	 */
	public static void download(HttpServletResponse response, HttpServletRequest request, String path) throws Exception {
		File file = new File(path);
		InputStream input = FileUtils.openInputStream(file);
		byte[] data = IOUtils.toByteArray(input);
		String fileName = URLEncoder.encode(file.getName(), "UTF-8");
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
		IOUtils.closeQuietly(input);
		File f = new File(path);
		if (f.exists())
			f.delete();
	}

	/**
	 * 下载文件
	 * 
	 * @param response
	 * @param request
	 * @param url
	 * @throws Exception
	 */
	public static void load(HttpServletResponse response, HttpServletRequest request, String url) throws Exception {
		File file = new File(url);
		InputStream input = FileUtils.openInputStream(file);
		byte[] data = IOUtils.toByteArray(input);
		String fileName = URLEncoder.encode(file.getName(), "UTF-8");
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
		IOUtils.closeQuietly(input);
	}

	  
    

	/**
	 * 文件下载
	 * 
	 * @param path
	 *            文件路径
	 * @param fileName
	 *            新文件名(可选,如果填写不要加文件后缀名)
	 * @param response
	 * @return
	 */
	public static HttpServletResponse download(String path, String newFileName, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String fileName = file.getName();
			// 文件后缀名
			String ext = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
			// 文件重命名
			fileName = (newFileName != null && !"".equals(newFileName)) ? newFileName
					+ ext
					: fileName;
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}

	/**
	 * 文件下载
	 * 
	 * @param path
	 *            文件路径
	 * @param fileName
	 *            文件名(必填,不加文件后缀)
	 * @param response
	 * @throws FileNotFoundException
	 */
	public static void downloadLocal(String path, String fileName, HttpServletResponse response) throws FileNotFoundException {
		// 下载本地文件
		fileName = fileName
				+ path.substring(path.lastIndexOf(".")).toLowerCase();
		// 读到流中
		InputStream inStream = new FileInputStream(path);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载网络文件
	 * 
	 * @param urlPath
	 *            url地址
	 * @param response
	 * @throws MalformedURLException
	 */
	public static void downloadNet(String urlPath, HttpServletResponse response) throws MalformedURLException {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;

		URL url = new URL(urlPath);

		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream("D:/abc.gif");
			byte[] buffer = new byte[1204];

			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件下载(在线打开方式)
	 * 
	 * @param filePath
	 *            文件下载
	 * @param response
	 *            是否在线打开方式(true:是,false:否)
	 * @param isOnLine
	 */
	public static void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) {
		try {
			File f = new File(filePath);
			if (!f.exists()) {
				response.sendError(404, "File not found!");
				return;
			}
			BufferedInputStream br = new BufferedInputStream(
					new FileInputStream(f));
			byte[] buf = new byte[1024];
			int len = 0;

			response.reset(); // 非常重要
			if (isOnLine) { // 在线打开方式
				URL u = new URL("file:///" + filePath);
				response.setContentType(u.openConnection().getContentType());
				response.setHeader("Content-Disposition", "inline; filename="
						+ f.getName());
				// 文件名应该编码成UTF-8
			} else { // 纯下载方式
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + f.getName());
			}
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			br.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
