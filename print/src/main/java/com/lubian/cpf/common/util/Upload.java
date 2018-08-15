package com.lubian.cpf.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.constant.Constant;

public class Upload {
	private static Logger log = Logger.getLogger(Upload.class);
	private static String FILE_UPLOAD_PATH = "/upload/";

	/**
	 * 处理文件上传
	 * 
	 * @param request
	 * @param realPath
	 * @param file
	 * @return
	 * @throws Exception
	 */

	public static List handleUpload(HttpServletRequest request,
			MultipartFile file) throws Exception {
		String fileDir = Config.getProperty(Constant.UPLOAD_PATH);
		String filePath = FILE_UPLOAD_PATH;
		if (!StringUtils.isBlank(fileDir)) {
			filePath = fileDir;
			if (!filePath.endsWith("/")) {
				filePath += "/";
			}
		}
		List retList = new ArrayList();
		try {

			String virPath = filePath;
			String path = virPath; // 获取本地存储路径
			if (file != null) {
				String fileName = UUID.randomUUID().toString();
				String fName = file.getOriginalFilename();
				File localFile = new File(path, fileName);
				if (!localFile.exists()) {
					localFile.mkdirs();
				} else {
					localFile = getUniqueFile(path, fileName);
					fileName = localFile.getName();
				}
				// 将上传的文件写入新建的文件中
				file.transferTo(localFile);
				// 构造返回的文件名字符串
				retList.add(fileName);
				retList.add(fName);
			}
			return retList;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}

	}

	public static String upload(HttpServletRequest request, MultipartFile file)
			throws Exception {
		String fileDir = Config.getProperty(Constant.UPLOAD_PATH);
		String filePath = FILE_UPLOAD_PATH;
		if (!StringUtils.isBlank(fileDir)) {
			filePath = fileDir;
			if (!filePath.endsWith("/")) {
				filePath += "/";
			}
		}

		try {

			String virPath = filePath;
			String path = virPath; // 获取本地存储路径
			String url = "";
			if (file != null) {
				String fileName = file.getOriginalFilename();
				File localFile = new File(path, fileName);
				if (!localFile.exists()) {
					localFile.createNewFile();
				} else {
					localFile = getUniqueFile(path, fileName);
					fileName = localFile.getName();
				}
				// 将上传的文件写入新建的文件中
				file.transferTo(localFile);
				// 构造返回的文件名字符串
				url = fileName;
			}
			return path + url;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}

	}

	public static String upload(HttpServletRequest request, MultipartFile file,
			String path) {
		String fileName = null;
		try {
			if (file != null&&file.getSize()>0) {
				String name = file.getOriginalFilename();
				fileName = UUID.randomUUID().toString()
						+ name.substring(name.lastIndexOf("."));
				File localFile = new File(path, fileName);
				if (!localFile.exists()) {
					localFile.mkdirs();
				} else {
					localFile = getUniqueFile(path, fileName);
					fileName = localFile.getName();
				}
				// 将上传的文件写入新建的文件中
				file.transferTo(localFile);
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		return fileName;
	}

	public static String upload(HttpServletRequest request,
			MultipartFile[] file, String path) {

		if (file != null && file.length > 0) {
			String url=null;
			for (MultipartFile multipartFile : file) {
				if(multipartFile.getSize()>0){
					String paths=Constant.IMPORT_PATH
							+ upload(request, multipartFile, path);
					paths = paths.replace("\\", "/");
					String fileName = paths.substring(paths.lastIndexOf("/"), paths.lastIndexOf(".") - 1);
					String swf=(Constant.ROOT_PATH + "up" + fileName + ".swf").replace("\\", "/");
					/*new SwfUtil(Constant.ROOT_PATH+paths,swf).start();*/
					if(url!=null){
						url+=","+paths;
					}else{
						url=paths;
					}
					
				}
				
			}

			return url;
		} else {
			return null;
		}

	}
	

	/**
	 * 如果上传文件名重复，处理文件名
	 * 
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static File getUniqueFile(String path, String fileName) {
		String pname = fileName.substring(0, fileName.lastIndexOf('.'));
		String suffix = fileName.substring(fileName.lastIndexOf('.'));
		int i = 1;
		while (true) {
			String tempName = pname + i + suffix;
			File tf = new File(path, tempName);
			if (!tf.exists())
				return tf;
			i++;
		}
	}
}
