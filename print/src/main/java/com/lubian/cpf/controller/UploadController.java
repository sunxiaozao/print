package com.lubian.cpf.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedDoctorLogin;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.Config;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.Upload;
import com.lubian.cpf.common.util.ZipUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.vo.SysUser;

/**
 * 文件上传类
 * 
 * 图片/upload/img 文件/upload/file 区别在于存放位置不同
 * 
 * 
 */
@NeedDoctorLogin
@Controller
public class UploadController {

	private Logger log = Logger.getLogger(UploadController.class);
	private static String FILE_UPLOAD_PATH = "/upload/";

	@Autowired
	private SysDoctorService doctorService;


	/**
	 * 超级医生 上传docm文件
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/doctor/ftp")
	public String uploadDocm(HttpServletRequest request, Model model, PageModel pm) {
		SysUser user = SessionUtil.getUser(request);
		if (user == null || !user.getUserType().equals(Enums.UserType.SUPER_DOCTOR)) {
			return "redirect:/doctor/login";
		}

		return "tiles.doctor.ftp";
	}

	/**
	 * 处理文件上传
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/upload")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Result result = Result.createResult().setSuccess(false);
		try {
			// 医院名称
			SysUser user = SessionUtil.getUser(request);
			//String hospitalName = sysHospitalService.getHospitalName(user);
			// 当前时间 如20151231
			String time = DateUtil.getNowDateString();

			String path = Config.getProperty(Constant.DOCM_UPLOAD_PATH);
			//path += hospitalName + "/" + time + "/";

			List pathList = this.handleUpload(request, path);

			File f = new File(path + pathList.get(0).toString());
			if (f.exists()) {
				f.delete();
			}

			result.setDataValue("pathList", pathList);
			result.setSuccess(true);

		} catch (Exception e) {
			log.error(e);
			result.setError(e.getMessage());
		}
		response.getWriter().print(JSONObject.fromObject(result).toString());
	}

	
	
	
	
	
	
	
	
	
	/**
	 * 读取表头信息
	 * 
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 */
	@RequestMapping("/read")
	public String readFiel(HttpServletRequest request, Model model, @RequestParam("file") MultipartFile file) {
	
			try {
				String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
				// 医院名称
							SysUser user = SessionUtil.getUser(request);
							String hospitalName = "";
							// 当前时间 如20151231
							String time = DateUtil.getNowDateString();
				
				String uploadPath = Config.getProperty(Constant.DOCM_UPLOAD_PATH)+hospitalName + "/" + time + "/";;
				// 1上传
				String path = uploadPath + Upload.upload(request, file, uploadPath);// 上传附件路径
				// 2解压
				ZipUtil.unZipFile(path, uploadPath);
				File zipFile = new File(path);
				if (zipFile.exists()) {
					zipFile.delete();
				}
				// 3读取文件
				List<String> fileList = getFiel(uploadPath + fileName);
				if(fileList!=null&&fileList.size()>0){
					model.addAttribute("error", "上传成功!");
				}
				
				
				model.addAttribute("fileList", fileList);
				model.addAttribute("count", fileList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		return "tiles.doctor.ftp";
	}
	
		public List<String> getFiel(String path) {
			List<String> l = new ArrayList<String>();
			File file = new File(path);
			File[] files = file.listFiles();
			if (files != null) {

				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						l.addAll(getFiel(files[i].getAbsolutePath()));
					} else {
						String strFileName = files[i].getName().toLowerCase();

						l.add(strFileName);
					}
				}
			}else{
				l.add(path);
			}

			return l;
		}
	
	
	
	
	
	
	/**
	 * 处理文件上传
	 * 
	 * @param request
	 * @param realPath
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private List handleUpload(HttpServletRequest request, String path) throws Exception {
		// 设置上下方文
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

		List retList = new ArrayList();
		// 检查form是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			try {

				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 由CommonsMultipartFile继承而来,拥有上面的方法.
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						String fileName = file.getOriginalFilename();
						String fName = fileName;
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
						// 上传成功后，进行解压
						//
						int suffix = fileName.lastIndexOf(".");// .的下标
						String unZipFileName = fileName.substring(0, suffix);// 去除后缀名
						String suffixString = fileName.substring(suffix);// 得到后缀
						if (suffixString.equals(".zip")) {
							ZipUtil.unZipFile(path + fileName, path);
						}
					}
				}
				return retList;
			} catch (Exception e) {
				log.error(e);
				throw e;
			}
		}
		return retList;
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

	/**
	 * 得到年周yyw格式的字符串，供生成生成上传目录使用
	 * 
	 * @return
	 */
	public static String getDateStrByWeek() {
		SimpleDateFormat format = new SimpleDateFormat("yyw");
		return format.format(Calendar.getInstance().getTime());
	}

	/**
	 * 得到年月yyMM格式的字符串，供生成生成上传目录使用
	 * 
	 * @return
	 */
	public static String getDateStrByMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyMM");
		return format.format(Calendar.getInstance().getTime());
	}
}
