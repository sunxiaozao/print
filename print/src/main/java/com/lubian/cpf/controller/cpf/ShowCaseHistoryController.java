package com.lubian.cpf.controller.cpf;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.nil.NilCipher;
import com.lubian.cpf.common.util.BrowserUtils;
import com.lubian.cpf.common.util.Encrypt;
import com.lubian.cpf.common.util.PDFUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.controller.AdminController;
import com.lubian.cpf.service.AdminService;
import com.lubian.cpf.service.cpf.CdfNotebookService;
import com.lubian.cpf.service.cpf.CpfCaseFolderService;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfShareService;
import com.lubian.cpf.service.sys.SysDepartmentService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.vo.CdfNotebook;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfShare;
import com.lubian.cpf.vo.CpfSharing;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@Controller
@RequestMapping("/member/share")
public class ShowCaseHistoryController {
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
	private CpfShareService cpfShareService;// 分享

	@Autowired
	private CdfNotebookService notebookService;
	@Autowired
	private AdminService adminService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 远程医生登陆
	 * 
	 * @param request
	 * @param caseHistoryId
	 * @param model
	 * @return
	 */
	@RequestMapping("/noLogin/{caseHistoryId}/{shareId}/{pass}")
	public String noLogin(HttpServletRequest request, Model model, @PathVariable("caseHistoryId") Integer caseHistoryId, @PathVariable("shareId") Integer shareId, @PathVariable("pass") String pass) {
		/*
		 * if ("L".equals(request.getParameter("login"))) { model.addAttribute("error", "请填写查看密码!"); }
		 */
		if ("N".equals(request.getParameter("login"))) {
			model.addAttribute("error", "请检查密码是否正确!");
		}
		model.addAttribute("caseHistoryId", caseHistoryId);
		model.addAttribute("pass", pass);
		model.addAttribute("shareId", shareId);

		return "/jsp/member/share/showCase/pass";
		// return "tiles.member.share.pass";
	}

	/**
	 * 检验远程医生登陆信息
	 * 
	 * @param request
	 * @param caseHistoryId
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkRemote")
	@ResponseBody
	public Result checkRemote(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);

		String pass = request.getParameter("pass");
		String inputPass = request.getParameter("inputPassword");

		if (pass.equals(inputPass)) {
			String shareId = request.getParameter("shareId");
			boolean flag = cpfShareService.searchByIdStr(shareId);
			if (flag) {
				result.setSuccess(true);
			} else {
				result.setError("分享已过期");
			}
		} else {
			result.setError("密码错误!请重新输入");
		}
		return result;
	}

	/**
	 * 显示病例资料
	 * 
	 * @param request
	 * @param caseHistoryId
	 * @param model
	 * @return
	 */
	@RequestMapping("/show")
	public String showCaseHistory(HttpServletRequest request, Model model) {
		// Result result = Result.createResult().setSuccess(false);

		String caseHistoryIdStr = request.getParameter("caseHistoryId");
		Integer caseHistoryId = 0;
		if (!StringUtils.isBlank(caseHistoryIdStr)) {
			caseHistoryId = Integer.parseInt(caseHistoryIdStr);
		}
		String shareIdStr = request.getParameter("shareId");
		Integer shareId = 0;
		if (!StringUtils.isBlank(shareIdStr)) {
			shareId = Integer.parseInt(shareIdStr);
		}
		String pass = request.getParameter("pass");

		CpfCaseHistory caseHistory = new CpfCaseHistory();
		caseHistory.setId(caseHistoryId);
		caseHistory = caseHistoryService.findByPK(caseHistory);

		CpfCaseFolder folder = new CpfCaseFolder();
		if (null != caseHistory) {
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
				doctor.setId(caseHistory.getHospitalId());
				doctor = doctorService.findByPK(doctor);
				model.addAttribute("doctor", doctor);// 获得医生
			}

			model.addAttribute("caseHistory", caseHistory);

			model.addAttribute("caseTypeMap", Enums.getCaseTypeMap());
			model.addAttribute("folder", folder);
		}
		model.addAttribute("caseHistoryId", caseHistoryId);
		model.addAttribute("shareId", shareId);
		model.addAttribute("pass", pass);
		// 更新分享表的是否查看字段
		CpfShare updShare = new CpfShare();
		/*
		 * updShare.setCaseHistoryId(caseHistoryId); updShare.setStatus(Enums.isYesOrIsNo.IS_NO); updShare = cpfShareService.findByCommand(updShare); if (null!=updShare) {
		 * 
		 * }
		 */
		updShare.setId(shareId);
		updShare.setStatus(Enums.isYesOrIsNo.IS_YES);
		cpfShareService.update(updShare);

		model.addAttribute("urls", NilCipher.EncryptionUrl(caseHistory));
		// 更新分享表的是否查看字段
		/*
		 * CpfShare updShare = new CpfShare(); updShare.setCaseHistoryId(caseHistoryId); updShare.setStatus(Enums.isYesOrIsNo.IS_NO); updShare = cpfShareService.findByCommand(updShare); updShare.setId(updShare.getId()); updShare.setStatus(Enums.isYesOrIsNo.IS_YES); cpfShareService.update(updShare);
		 */
		String[] path = null;
		if (caseHistory.getNilUrl() != null && caseHistory.getNilUrl().length() > 0) {
			path = caseHistory.getNilUrl().split(",");
			model.addAttribute("path", path);
		}

		if (Enums.Relation.LOCAL.equals(caseHistory.getSource()) && !Enums.Relation.TEMPORARY_DATA.equals(caseHistory.getSource())) {
			if (path != null && path.length > 0) {
				String suffix = path[0].substring(path[0].lastIndexOf(".")).toLowerCase();
				if (".pdf".equals(suffix)) {
					model.addAttribute("type", "pdf");
					if (BrowserUtils.isTrue(request)) {
						return "tiles.member.share.showpdf";
					}else{
						String url=null;
						if (path.length == 1) {// 如果是一个直接下载
							url = path[0];
								model.addAttribute("url", url);
							return "/jsp/member/share/showCase/showpdf2";
						} else {
							url = PDFUtil.mergePdfFiles(path, Constant.PDF_PATH, caseHistory.getId() + "-" + caseHistory.getPatientId() + ".pdf");// 合并
							model.addAttribute("url", url);
							return "/jsp/member/share/showCase/showpdf2";
						}
					}
					
				}
			}

			return "tiles.member.share.show";
		} else {
			return "tiles.member.share.shownil";
		}

	}

	/**
	 * 处理文件可路径
	 * 
	 * @param caseHistory
	 * @return
	 */
	private String[] url(CpfCaseHistory caseHistory) {
		if (caseHistory != null) {

			String type = caseHistory.getCaseType();

			String path = null;

			if (Enums.CaseType.FILM.equals(type)) {// 胶片
				path = caseHistory.getPicUrl();
			} else if (Enums.CaseType.DIAGNOSIS.equals(type)) {// 诊断报告
				path = caseHistory.getReportUrl();
			} else if (Enums.CaseType.REQUISITION.equals(type)) {// 申请书
				path = caseHistory.getApplyUrl();
			} else if (Enums.CaseType.TEST_REPORT.equals(type)) {// 化验单
				path = caseHistory.getCheckFormUrl();
			} else if (Enums.CaseType.OTHER.equals(type)) {// 其他
				path = caseHistory.getOthers();
			}
			if (path != null && !"".equals(path)) {
				return StringUtils.getStringArrayByChar(path, ",");
			}
		}
		return null;

	}

	/**************** 注册账号 *****************/

	@RequestMapping("/info/{shareId}")
	public String info(Model model, @PathVariable("shareId") Integer shareId) {

		CpfShare share = new CpfShare();
		share.setId(shareId);
		share = cpfShareService.findByPK(share);

		model.addAttribute("sexMap", Enums.getSexMap());
		if (share != null) {
			model.addAttribute("mobile", share.getMobile());

		}
		model.addAttribute("shareId", shareId);
		return "/jsp/member/share/info/doctorAdd";
	}

	@RequestMapping("/saveAdd")
	public String saveInsertDict(HttpServletRequest request, Model model, SysDoctor doctor) {
		try {
			doctorService.savePerfect(doctor, request);
			String shareId = request.getParameter("shareId");
			CpfSharing sharing = new CpfSharing();
			sharing.setShareId(Integer.parseInt(shareId));
			sharing.setDoctorId(doctor.getId());
			sharing.setCreateTime(new Date());
			cpfShareService.saveSharing(sharing);
			SysLogsUtil.saveLogs("完善个人信息", "完善个人信息" + doctor.getFullname() + doctor.getTitle(), request);

			SysLogsUtil.saveLogs("关联分享", "关联分享" + doctor.getFullname(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/doctor/index";
	}

	/********************* 留言 **********************/
	@RequestMapping("/notebook/{caseHistoryId}/{patientId}")
	public String notebook(@PathVariable("caseHistoryId") Integer caseHistoryId, @PathVariable("patientId") Integer patientId, Model model) {
		model.addAttribute("caseHistoryId", caseHistoryId);
		model.addAttribute("patientId", patientId);
		return "/jsp/member/share/info/notebook";
	}

	@ResponseBody
	@RequestMapping("/savenotebook")
	public Result saveNotebook(CdfNotebook notebook) {
		Result result = new Result().setSuccess(false);
		notebook.setCreateTime(new Date());
		try {
			notebookService.insert(notebook);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/************* 关联账号 *****************/
	@RequestMapping("/sharing/{shareid}")
	public String sharing(@PathVariable("shareid") Integer shareid, Model model) {
		model.addAttribute("shareid", shareid);
		return "/jsp/member/share/info/sharing";
	}

	@ResponseBody
	@RequestMapping("/saveSharing")
	public Result saveSharing(SysUser user, HttpServletRequest request) {
		Result result = new Result().setSuccess(false);

		user.setPassword(Encrypt.md5(user.getPassword()));
		SysUser newUser = adminService.adminLogin(user.getUserName(), user.getPassword());

		if (newUser != null) {
			SysDoctor doctor = doctorService.findDoctorByUserId(newUser.getUserId());
			if (doctor != null) {
				String shareid = request.getParameter("shareid");
				CpfSharing sharing = new CpfSharing();
				sharing.setShareId(Integer.parseInt(shareid));
				sharing.setDoctorId(doctor.getId());
				if (cpfShareService.findSharing(sharing)) {
					sharing.setCreateTime(new Date());
					cpfShareService.saveSharing(sharing);
					SysLogsUtil.saveLogs("关联分享", "关联分享" + doctor.getFullname(), request);
					result.setSuccess(true);
				} else {
					result.setError("该分享已经关联过了,请勿重复关联");
				}

			} else {
				result.setError("用户名密码密码错误!请重新输入");
			}

		} else {
			result.setError("用户名密码密码错误!请重新输入");
		}
		return result;
	}
}
