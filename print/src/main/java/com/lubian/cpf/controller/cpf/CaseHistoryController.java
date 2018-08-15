package com.lubian.cpf.controller.cpf;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.nil.NilCipher;
import com.lubian.cpf.common.util.BrowserUtils;
import com.lubian.cpf.common.util.Config;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.Dowload;
import com.lubian.cpf.common.util.JsonUtil;
import com.lubian.cpf.common.util.PDFUtil;
import com.lubian.cpf.common.util.ReadDCM;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.SwfUtil;
import com.lubian.cpf.common.util.Upload;
import com.lubian.cpf.common.util.ZipUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.controller.AdminController;
import com.lubian.cpf.service.cpf.CpfCaseFolderService;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfDownloadService;
import com.lubian.cpf.service.cpf.CpfPatientRelationUserService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.cpf.CpfShareService;
import com.lubian.cpf.service.sys.SysDepartmentService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfDownload;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfShare;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/member/casehistory")
public class CaseHistoryController {
	private Logger log = Logger.getLogger(AdminController.class);

	@Autowired
	private CpfCaseFolderService cpfCaseFolderService; // 病历夹表

	@Autowired
	private SysHospitalService sysHospitalService; // 医院表

	@Autowired
	private SysDepartmentService departmentService;

	@Autowired
	private SysDoctorService doctorService;

	@Autowired
	private CpfCaseHistoryService caseHistoryService;

	@Autowired
	private CpfDownloadService downloadService;

	@Autowired
	private CpfShareService cpfShareService;// 分享

	@Autowired
	private CpfPatientRelationUserService psService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/***************************** 修改病历资料 *****************************/
	@RequestMapping("/show/update/{id}/{type}")
	public String showUpdate(@PathVariable("id") Integer id, HttpServletRequest request, Model model, @PathVariable("type") String type) {
		CpfCaseHistory caseHistory = new CpfCaseHistory();
		caseHistory.setId(id);
		caseHistory = caseHistoryService.findByPK(caseHistory);
		CpfPatient patient = SessionUtil.getPatient(request);// 获得当前登录的病人
		CpfCaseFolder caseFolder = new CpfCaseFolder();
		String patientIds = psService.freeFind(null, patient.getId());
		caseFolder.setPatientId_instr(patientIds);
		caseFolder.setStatus(Enums.isYesOrIsNo.IS_YES);// 查询没有有效的病历夹
		List<CpfCaseFolder> caseFolders = cpfCaseFolderService.find(caseFolder);// 病历夹
		model.addAttribute("caseFolders", caseFolders);
		model.addAttribute("patient", patient);
		model.addAttribute("caseTypeMap", Enums.getCaseTypeMap()); // 病例资料map集合
		model.addAttribute("caseHistory", caseHistory);
		model.addAttribute("type", type);

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("hospitalId", caseHistory.getHospitalId());
		map.put("departmentId", caseHistory.getDepartmentId());
		map.put("doctorId", caseHistory.getDoctorId());
		model.addAttribute("array", JsonUtil.getJsonString4JavaPOJO(map));

		return "tiles.member.casehistory.update";
	}

	@RequestMapping("/update/{type}")
	public String update(HttpServletRequest request, Model model, CpfCaseHistory caseHistory, @PathVariable("type") String type) {
		CpfPatient patient = SessionUtil.getPatient(request);

		caseHistoryService.update(caseHistory);
		SysLogsUtil.saveLogs("修改病例资料", patient.getUserName() + "修改病例资料" + caseHistory.getCaseName(), request);

		if ("F".equals(type)) {
			return "redirect:/member/folderShow/list?up=Y";
		}
		return "redirect:/member/casehistory/list?up=Y&patientId=" + patient.getId();
	}

	/*************************** 添加医院病历资料 ******************************/
	/**
	 * 显示上传文件界面
	 * 
	 * @return
	 */
	@RequestMapping("/addfile")
	public String addFile() {
		return "/jsp/admin/casehistory/addfile";
	}

	/**
	 * 读取表头信息
	 * 
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 */
	@RequestMapping("/readfile")
	public String readFiel(HttpServletRequest request, Model model, @RequestParam("file") MultipartFile file) {
		try {

			String time = DateUtil.getNowDateString();
			String uploadPath = Config.getProperty("personal_dcm_path") + time + "/";
			// 1上传
			String path = uploadPath + Upload.upload(request, file, uploadPath);// 上传附件路径
			// 2解压
			List<String> fileList = ZipUtil.unZipFile(path, uploadPath);
			File zipFile = new File(path);
			if (zipFile.exists()) {
				zipFile.delete();
			}

			if (fileList.size() < 0) {
				return "redirect:/member/casehistory/list?add=N";
			}

			CpfPatient patient = SessionUtil.getPatient(request);// 获得当前登录的病人
			CpfCaseFolder caseFolder = new CpfCaseFolder();
			String patientIds = psService.freeFind(null, patient.getId());
			caseFolder.setPatientId_instr(patientIds);
			caseFolder.setStatus(Enums.isYesOrIsNo.IS_YES);// 查询没有有效的病历夹
			List<CpfCaseFolder> caseFolders = cpfCaseFolderService.find(caseFolder);// 病历夹
			model.addAttribute("caseFolders", caseFolders);
			model.addAttribute("patient", patient);
			model.addAttribute("caseTypeMap", Enums.getCaseTypeMap()); // 病例资料map集合
			CpfCaseHistory caseHistorys = null;

			for (String file2 : fileList) {
				CpfCaseHistory caseHistory = ReadDCM.read(new File(file2));
				if (caseHistory != null) {

					CpfCaseHistory caseHistor = caseHistoryService.find(caseHistory.getStudy(), caseHistory.getPid(), patientIds, Enums.isYesOrIsNo.IS_YES);
					if (caseHistor == null) {
						caseHistory.setPatientId(patient.getId());
						caseHistory.setCreator(patient.getUserName());
						caseHistoryService.insert(caseHistory);

					}
					if (caseHistorys == null) {
						caseHistorys = caseHistory;
					}
				}
			}

			if (caseHistorys == null) {
				return "redirect:/member/casehistory/list?add=N";
			}
			model.addAttribute("caseHistory", caseHistorys);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/member/casehistory/list?add=N";
		}
		return "tiles.member.casehistory";
	}

	/**
	 * 病人添加病例资料
	 * 
	 * @param request
	 * @param model
	 * @param caseHistory
	 * @param file
	 * @return
	 */
	@RequestMapping("/saveCaseHistory")
	public String saveCaseHistory(HttpServletRequest request, Model model, CpfCaseHistory caseHistory) {
		CpfPatient patient = SessionUtil.getPatient(request);

		caseHistory.setPatientId(patient.getId());
		caseHistoryService.update(caseHistory);
		SysLogsUtil.saveLogs("添加病例资料", "添加病例资料" + caseHistory.getCaseName(), request);
		return "redirect:/member/casehistory/list?add=Y&patientId=" + caseHistory.getPatientId();
	}

	/***************************** 添加本地病历资料 *****************************/

	@RequestMapping("/addLocal")
	public String addLocal(Model model, HttpServletRequest request) {
		CpfPatient patient = SessionUtil.getPatient(request);// 获得当前登录的病人
		CpfCaseFolder caseFolder = new CpfCaseFolder();
		String patientIds = psService.freeFind(null, patient.getId());
		caseFolder.setPatientId_instr(patientIds);
		caseFolder.setStatus(Enums.isYesOrIsNo.IS_YES);// 查询没有有效的病历夹
		List<CpfCaseFolder> caseFolders = cpfCaseFolderService.find(caseFolder);// 病历夹
		model.addAttribute("caseFolders", caseFolders);
		model.addAttribute("patient", patient);
		model.addAttribute("type", "add");
		model.addAttribute("caseTypeMap", Enums.getCaseTypeMap()); // 病例资料map集合
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("hospitalId", 9999);
		map.put("departmentId", 9999);
		map.put("doctorId", 9999);
		model.addAttribute("array", JsonUtil.getJsonString4JavaPOJO(map));
		return "tiles.member.casehistory.local";
	}

	@RequestMapping("/saveLocal")
	public String saveCaseHistory(HttpServletRequest request, Model paramModel, CpfCaseHistory caseHistory, @RequestParam("file") MultipartFile[] file) {
		System.out.println(Thread.currentThread().getName() + ":");
		String str = Upload.upload(request, file, Constant.ROOT_PATH + "images/import/");
		caseHistory.setNilUrl(str);

		CpfPatient patient = SessionUtil.getPatient(request);// 获得当前登录的病人
		caseHistory.setPatientId(patient.getId());
		caseHistory.setCreateTime(new Date());
		caseHistory.setCreator(patient.getUserName());
		caseHistory.setSource(Enums.Relation.LOCAL);
		caseHistory.setViewStatus(Enums.isYesOrIsNo.IS_NO);
		caseHistory.setRelateStatus(Enums.isYesOrIsNo.IS_YES);
		caseHistoryService.insert(caseHistory);
		paramModel.addAttribute("sexMap", Enums.getSexMap());
		SysLogsUtil.saveLogs("添加病例资料", "添加病例资料" + caseHistory.getCaseName(), request);
		return "redirect:/member/casehistory/list?add=Y&patientId=" + caseHistory.getPatientId();
	}

	/**
	 * 病人查看病例资料
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @param caseHistory
	 * @return
	 */
	@RequestMapping("/list")
	public String CaseHistory(HttpServletRequest request, Model model, PageModel pm, CpfCaseHistory caseHistory) {

		String addFlag = request.getParameter("add");
		if ("Y".equals(addFlag)) {
			model.addAttribute("error", "病例资料添加成功!");
		}
		if ("N".equals(request.getParameter("add"))) {
			model.addAttribute("error", "病例资料添加失败!");
		}
		if ("Y".equals(request.getParameter("addFlag"))) {
			model.addAttribute("error", "病例资料关联成功!");
		}
		if ("Y".equals(request.getParameter("del"))) {
			model.addAttribute("error", "删除成功!");
		}
		if ("Y".equals(request.getParameter("up"))) {
			model.addAttribute("error", "修改成功!");
		}

		CpfPatient patient = SessionUtil.getPatient(request);// 获得当前登录的病人信息

		String patientIds = psService.freeFind(null, patient.getId());
		if (patientIds != null) {// 病人id不为空

			caseHistory.setRelateStatus(Enums.isYesOrIsNo.IS_YES);
			caseHistory.setPatientId_instr(patientIds);
			caseHistory.setRelateStatus(Enums.isYesOrIsNo.IS_YES);
			pm = caseHistoryService.freeFind(caseHistory);
		}

		SysHospital hospital = new SysHospital();
		Map hospitalMap = sysHospitalService.getHospitalMap(hospital);// 获得医院信息
		SysDepartment department = new SysDepartment();
		Map deMap = departmentService.getDepartmentMap(department);// 获得科室信息
		model.addAttribute("pm", pm);
		model.addAttribute("hospitalMap", hospitalMap);
		model.addAttribute("deMap", deMap);
		model.addAttribute("patient", patient);
		model.addAttribute("caseHistory", caseHistory);
		model.addAttribute("caseTypeMap", Enums.getCaseTypeMap());// 获取病例资料类型

		return "tiles.member.casehistory.list";
	}

	/**
	 * 删除病历资料
	 * 
	 * @param request
	 * @param model
	 * @param caseHistoryId
	 * @return
	 */
	@RequestMapping("/delete/{caseHistoryId}")
	public String update(HttpServletRequest request, Model model, @PathVariable("caseHistoryId") Integer caseHistoryId) {
		CpfCaseHistory caseHistory = new CpfCaseHistory();
		caseHistory.setId(caseHistoryId);
		caseHistory.setRelateStatus(Enums.isYesOrIsNo.IS_NO);
		caseHistoryService.update(caseHistory);
		SysLogsUtil.saveLogs("删除病例资料", "删除病例资料" + caseHistory.getCaseName(), request);
		return "redirect:/member/casehistory/list?del=Y";
	}

	/**
	 * 选择关联病历资料的病历夹
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @param caseHistoryId
	 * @return
	 */
	@RequestMapping("/relation/{caseHistoryId}/{type}")
	public String relation(HttpServletRequest request, Model model, HttpServletResponse response, @PathVariable("type") String type, @PathVariable("caseHistoryId") String caseHistoryId) {

		CpfPatient patient = SessionUtil.getPatient(request);// 获得当前登录的病人信息

		String patientIds = psService.freeFind(null, patient.getId());

		CpfCaseFolder caseFolder = new CpfCaseFolder();
		caseFolder.setPatientId_instr(patientIds);
		caseFolder.setStatus(Enums.isYesOrIsNo.IS_YES);
		List<CpfCaseFolder> caseFolders = cpfCaseFolderService.find(caseFolder);// 病历夹
		model.addAttribute("caseFolders", caseFolders);
		model.addAttribute("caseHistoryId", caseHistoryId);
		model.addAttribute("type", type);
		model.addAttribute("patientId", patient.getId());
		return "/jsp/admin/casehistory/relation";
	}

	/**
	 * 关联病历资料
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("/relation")
	public String saverelation(HttpServletRequest request, Model model, HttpServletResponse response) {

		String patientId = request.getParameter("patientId");
		String ids = request.getParameter("caseHistoryId");
		String folderId = request.getParameter("folderId");
		caseHistoryService.updateRelateStatus(Enums.isYesOrIsNo.IS_YES, Integer.parseInt(patientId), Integer.parseInt(folderId), ids);
		SysLogsUtil.saveLogs("关联病例资料", "关联病例资料" + patientId, request);
		return "redirect:/member/casehistory/list?addFlag=Y";
	}

	/**
	 * 下载病例资料
	 * 
	 * @param caseHistoryId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/download/{caseHistoryId}")
	public void download(@PathVariable("caseHistoryId") Integer caseHistoryId, HttpServletRequest request, HttpServletResponse response) {
		try {
			CpfPatient patient = SessionUtil.getPatient(request);// 获得当前登录的病人信息
			CpfCaseHistory caseHistory = new CpfCaseHistory();
			caseHistory.setId(caseHistoryId);
			caseHistory = caseHistoryService.findByPK(caseHistory);// 获得病例资料id
			boolean flag = false;
			String path = null;
			String parentPath = null;
			if (!Enums.Relation.LOCAL.equals(caseHistory.getSource())) {
				File fiel = new File(caseHistory.getNilUrl());
				// 记录下载内容
				path = fiel.getParent() + ".zip";
				parentPath = fiel.getParent();
			} else {
				String[] imgPath = null;
				if (caseHistory.getNilUrl() != null && caseHistory.getNilUrl().length() > 0) {
					imgPath = caseHistory.getNilUrl().split(",");
				}

				if (imgPath != null && imgPath.length > 0) { // 判断是否有附件存在
					String suffix = imgPath[0].substring(imgPath[0].lastIndexOf(".")).toLowerCase();
					if (".pdf".equals(suffix)) {// 判断是否是pdf
						if (imgPath.length == 1) {// 如果是一个直接下载
							path = imgPath[0];
							flag = true;
						} else {
							path = PDFUtil.mergePdfFiles(imgPath, Constant.PDF_PATH);// 合并
						}
					} else {
						List<String> str = new ArrayList<String>();
						str.add("病人姓名: " + patient.getFullname()); // 病人名称
						up(caseHistory, str);
						path = PDFUtil.getPDF(Constant.PDF_PATH, str, imgPath);
					}
				} else {
					List<String> str = new ArrayList<String>();

					str.add("病人姓名: " + patient.getFullname()); // 病人名称
					up(caseHistory, str);
					path = PDFUtil.getPDF(Constant.PDF_PATH, str, imgPath);
				}
			}

			// 执行下载

			CpfDownload download = new CpfDownload();
			download.setPatientId(caseHistory.getPatientId());
			download.setType(caseHistory.getCaseType());
			download.setDownTime(new Date());
			download.setDownUrl(path);
			downloadService.insert(download);

			if (!Enums.Relation.LOCAL.equals(caseHistory.getSource())) {
				ZipUtil.zipFolder(parentPath, path);
				Dowload.load(response, request, path);
				File fi = new File(path);
				fi.delete();
			} else {

				Dowload.load(response, request, Constant.ROOT_PATH + "/" + path);
				File fi = new File(Constant.ROOT_PATH + "/" + path);
				fi.delete();

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 下载辅助类
	 * 
	 * @param caseHistory
	 * @param str
	 */
	public void up(CpfCaseHistory caseHistory, List<String> str) {
		if (caseHistory.getHospitalId() != null // 就诊医院
				&& !"".equals(caseHistory.getHospitalId())) {
			SysHospital hospital = new SysHospital();
			hospital.setId(caseHistory.getHospitalId());
			hospital = sysHospitalService.findByPK(hospital);
			str.add("就诊医院: " + hospital.getName());
		}
		if (caseHistory.getDepartmentId() != null // 就诊科室
				&& !"".equals(caseHistory.getDepartmentId())) {
			SysDepartment department = new SysDepartment();
			department.setId(caseHistory.getDepartmentId());
			department = departmentService.findByPK(department);
			str.add("就诊科室: " + department.getDepartmentName());
		}
		if (caseHistory.getDoctorId() != null // 就诊医生
				&& !"".equals(caseHistory.getDoctorId())) {
			SysDoctor doctor = new SysDoctor();
			doctor.setId(caseHistory.getDoctorId());
			doctor = doctorService.findByPK(doctor);
			str.add("就诊医生: " + doctor.getFullname());
		}
		CpfCaseFolder folder = new CpfCaseFolder();
		folder.setId(caseHistory.getFolderId());
		folder = cpfCaseFolderService.findByPK(folder);
		if (folder != null) {
			str.add("病例所在文件夹: " + folder.getFolderName()); // 所属文件夹
		}
		str.add("病例类别: " + Enums.getCaseTypeMap().get(caseHistory.getCaseType()));// 病例类别
		str.add("病例名称: " + caseHistory.getCaseName());// 病例名称
		str.add("就诊类别: " + caseHistory.getCatgory());// 就诊类别
		str.add("检查项目: " + caseHistory.getItem());// 检查项目
		String data = DateUtil.getDateStr(caseHistory.getCaseDate(), "yyyy-MM-dd");// 就诊时间
		str.add("就诊时间: " + data);
		str.add("执行技师: " + caseHistory.getTechnician());// 执行技师
		str.add("临床诊断: " + caseHistory.getDescription());// 临床诊断
		str.add("住院号: " + caseHistory.getHospitalNo());// 住院号
		str.add("床号: " + caseHistory.getBedNo());// 床号
		str.add("病人性质: " + caseHistory.getPatientCatgory());// 病人性质
	}

	@RequestMapping("/show")
	public String show(HttpServletRequest request, Model model) {

		String hid = request.getParameter("hid");
		CpfCaseHistory caseHistory = new CpfCaseHistory();
		if (hid != null) {
			caseHistory.setId(Integer.parseInt(hid));
		}
		caseHistory = caseHistoryService.findByPK(caseHistory);
		if (!Enums.Relation.LOCAL.equals(caseHistory.getSource())) {
			String url = NilCipher.EncryptionUrl(caseHistory);
			model.addAttribute("url", url);// 医院Map
			return "/jsp/admin/casehistory/shownil";
		}
		CpfCaseFolder folder = new CpfCaseFolder();
		folder.setId(caseHistory.getFolderId());
		folder = cpfCaseFolderService.findByPK(folder);// 获得所属病历夹

		if (caseHistory.getHospitalId() != null && !"".equals(caseHistory.getHospitalId())) {
			SysHospital hospital = new SysHospital();
			hospital.setId(caseHistory.getHospitalId());
			hospital = sysHospitalService.findByPK(hospital);
			model.addAttribute("hospital", hospital);// 获得医院
		}
		if (caseHistory.getDepartmentId() != null && !"".equals(caseHistory.getDepartmentId())) {
			SysDepartment department = new SysDepartment();
			department.setId(caseHistory.getDepartmentId());
			department = departmentService.findByPK(department);
			model.addAttribute("department", department);// 获得科室
		}
		if (caseHistory.getDoctorId() != null && !"".equals(caseHistory.getDoctorId())) {
			SysDoctor doctor = new SysDoctor();
			doctor.setId(caseHistory.getDoctorId());
			doctor = doctorService.findByPK(doctor);
			model.addAttribute("doctor", doctor);// 获得医生
		}
		model.addAttribute("caseHistory", caseHistory);
		String[] path = null;
		if (caseHistory.getNilUrl() != null && caseHistory.getNilUrl().length() > 0) {
			path = caseHistory.getNilUrl().split(",");
		}

		model.addAttribute("caseTypeMap", Enums.getCaseTypeMap());
		model.addAttribute("folder", folder);

		if (path != null && path.length > 0) {
			String suffix = path[0].substring(path[0].lastIndexOf(".")).toLowerCase();
			if (".pdf".equals(suffix)) {
				String url = null;
				if (path.length == 1) {// 如果是一个直接下载
					url = path[0];

				} else {
					url = PDFUtil.mergePdfFiles(path, Constant.PDF_PATH, caseHistory.getId() + "-" + caseHistory.getPatientId() + ".pdf");// 合并

				}
				model.addAttribute("url", url);
				if (BrowserUtils.isTrue(request)) {
					return "/jsp/admin/casehistory/pdf";
				} else {
					return "/jsp/admin/casehistory/pdf2";
				}

			}
		}

		// 更新是否查看字段
		caseHistory.setViewStatus(Enums.isYesOrIsNo.IS_YES);
		caseHistoryService.update(caseHistory);
		// 更新分享表查看状态
		String shareIdStr = request.getParameter("shareId");
		Integer shareId = 0;
		CpfShare share = new CpfShare();
		if (!StringUtils.isBlank(shareIdStr)) {
			shareId = Integer.parseInt(shareIdStr);
		}
		share.setId(shareId);
		share.setStatus(Enums.isYesOrIsNo.IS_YES);
		cpfShareService.update(share);
		model.addAttribute("path", path);
		return "/jsp/admin/casehistory/show";

	}

	/**
	 * 查询医院
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/hospitals")
	@ResponseBody
	public Result getHospitalList(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);
		SysHospital hospital = new SysHospital();
		try {
			List<SysHospital> hospitals = sysHospitalService.findList(hospital);
			result.setSuccess(true);
			result.setDataValue("list", hospitals);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 查询科室
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/department/{hospitalId}")
	@ResponseBody
	public Result getDepartmentList(HttpServletRequest request, Model model, @PathVariable("hospitalId") Integer hospitalId) {
		Result result = Result.createResult().setSuccess(false);
		SysDepartment department = new SysDepartment();
		department.setHospitalId(hospitalId);
		try {
			List<SysDepartment> departments = departmentService.findList(department);
			result.setSuccess(true);
			result.setDataValue("list", departments);
		} catch (Exception e) {

			log.error(e);
		}
		return result;
	}

	/**
	 * 查询医生
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/doctor/{departmentId}")
	@ResponseBody
	public Result getdoctorList(HttpServletRequest request, Model model, @PathVariable("departmentId") Integer departmentId) {
		Result result = Result.createResult().setSuccess(false);
		SysDoctor doctor = new SysDoctor();
		doctor.setDepartmentId(departmentId);
		try {
			List<SysDoctor> doctors = doctorService.findFindByDoctorList(doctor);
			result.setSuccess(true);
			result.setDataValue("list", doctors);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}
}
