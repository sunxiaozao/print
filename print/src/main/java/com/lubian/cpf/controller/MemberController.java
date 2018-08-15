package com.lubian.cpf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
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
import com.lubian.cpf.common.util.CookieUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.AdminService;
import com.lubian.cpf.service.cpf.CdfNotebookService;
import com.lubian.cpf.service.cpf.CpfCaseFolderService;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfPatientRelationUserService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.cpf.CpfShareService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.CdfNotebook;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfPatientRelationUser;
import com.lubian.cpf.vo.CpfShare;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/member")
public class MemberController {
	private Logger log = Logger.getLogger(MemberController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;

	@Autowired
	private CpfPatientService patientService;

	@Autowired
	private CdfNotebookService cdfNotebookService;// 留言

	@Autowired
	private CpfCaseFolderService cpfCaseFolderService;// 病历夹

	@Autowired
	private CpfShareService cpfShareService;// 分享

	@Autowired
	private CpfCaseHistoryService cpfCaseHistoryService;// 病历资料

	@Autowired
	private CpfPatientRelationUserService pService;
	@Autowired
	private SysHospitalService hospitalService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model, PageModel pm) {
		SysUser user = SessionUtil.getUser(request);
		CpfPatient patient = SessionUtil.getPatient(request);
		Integer pictureCountNewest = 0;// 最新胶片
		Integer pictureCount = 0;// 胶片统计
		Integer reportCountNewest = 0;// 最新诊断报告
		Integer reportCount = 0;// 诊断报告统计
		Integer assayCountNewest = 0;// 最新化验单
		Integer assayCount = 0;// 化验单统计
		Integer applyCountNewest = 0;// 最新申请单
		Integer applyCount = 0;// 申请单统计
		Integer folderCountNewest = 0;// 最新病历夹
		Integer folderCount = 0;// 病历夹统计
		Integer notebookCountNewest = 0;// 最新留言
		Integer notebookCount = 0;// 留言统计
		Integer shareCountNewest = 0;// 最新分享
		Integer shareCount = 0;// 分享统计
		Integer caseCount = 1;// 医院汇总
		try {

			String patientIds = null;
			if (user != null) {// 通过微医通登录
				patientIds = pService.freeFind(user.getUserId(), null);

			} else {// 病人通过医院方式
				patientIds = pService.freeFind(null, patient.getId());
			}

			if (patientIds != null) {// 病人id不为空
				pictureCountNewest = cpfCaseHistoryService.subtotalByCommand(
						patientIds, Enums.CaseType.FILM,
						Enums.isYesOrIsNo.IS_NO);// 最新胶片
				pictureCount = cpfCaseHistoryService.subtotalByCommand(
						patientIds, Enums.CaseType.FILM, null);// 胶片统计

				reportCountNewest = cpfCaseHistoryService.subtotalByCommand(
						patientIds, Enums.CaseType.REQUISITION,
						Enums.isYesOrIsNo.IS_NO);// 最新诊断报告
				reportCount = cpfCaseHistoryService.subtotalByCommand(
						patientIds, Enums.CaseType.REQUISITION, null);// 诊断报告统计

				assayCountNewest = cpfCaseHistoryService.subtotalByCommand(
						patientIds, Enums.CaseType.TEST_REPORT,
						Enums.isYesOrIsNo.IS_NO);// 最新化验单
				assayCount = cpfCaseHistoryService.subtotalByCommand(
						patientIds, Enums.CaseType.TEST_REPORT, null);// 化验单统计

				applyCountNewest = cpfCaseHistoryService.subtotalByCommand(
						patientIds, Enums.CaseType.DIAGNOSIS,
						Enums.isYesOrIsNo.IS_NO);// 最新申请单
				applyCount = cpfCaseHistoryService.subtotalByCommand(
						patientIds, Enums.CaseType.DIAGNOSIS, null);// 申请单统计

				folderCountNewest = cpfCaseFolderService.subtotalOfFolder(null,
						patientIds, Enums.isYesOrIsNo.IS_NO);// 最新病历夹
				folderCount = cpfCaseFolderService.subtotalOfFolder(null,
						patientIds, Enums.isYesOrIsNo.IS_YES);// 最新病历夹
				notebookCountNewest = cdfNotebookService.subtotalPatient(null,
						patientIds, Enums.isYesOrIsNo.IS_NO);// 最新留言
				notebookCount = cdfNotebookService.subtotalPatient(null,
						patientIds, Enums.isYesOrIsNo.IS_YES);// 留言统计

				shareCountNewest = cpfShareService.subtotalPatient(null,
						patientIds, Enums.isYesOrIsNo.IS_NO);// 最新分享
				shareCount = cpfShareService.subtotalPatient(null, patientIds,
						Enums.isYesOrIsNo.IS_YES);// 分享统计
				String[] ids = patientIds.split(",");
				if (ids != null) {
					caseCount = ids.length;// 就诊医院统计
				}

			}
			model.addAttribute("pictureCountNewest", pictureCountNewest);// 最新胶片
			model.addAttribute("pictureCount", pictureCount);// 胶片统计
			model.addAttribute("reportCountNewest", reportCountNewest);// 最新诊断报告
			model.addAttribute("reportCount", reportCount);// 诊断报告统计
			model.addAttribute("assayCountNewest", assayCountNewest);// 最新化验单
			model.addAttribute("assayCount", assayCount);// 化验单统计
			model.addAttribute("applyCountNewest", applyCountNewest);// 最新申请单
			model.addAttribute("applyCount", applyCount);// 申请单统计
			model.addAttribute("folderCountNewest", folderCountNewest);// 最新病历夹
			model.addAttribute("folderCount", folderCount);// 病历夹统计
			model.addAttribute("notebookCountNewest", notebookCountNewest);// 最新留言
			model.addAttribute("notebookCount", notebookCount);// 留言统计
			model.addAttribute("shareCountNewest", shareCountNewest);// 最新分享
			model.addAttribute("shareCount", shareCount);// 分享统计
			model.addAttribute("caseCount", caseCount);// 医院汇总
		} catch (Exception e) {

			log.error(e);
		}

		return "tiles.member.index";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		SessionUtil.removeUser(request);
		SessionUtil.removePatient(request);
		request.getSession().invalidate();
		CookieUtil.removeCookie(response, Constant.COOKIE_MENU_CODE);
		return "/jsp/admin/login/login";
	}

	@RequestMapping("/savePassChange")
	@ResponseBody
	public Result savePassChange(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);
		CpfPatient patient=new SessionUtil().getPatient(request);
		try {
			String oldPassword = request.getParameter("oldPassword");
			String password = request.getParameter("password");
			if (!oldPassword.equals(patient.getPassword())) {
				result.setError("旧密码验证错误！");
				return result;
			}
			patient.setPassword(password);
			patientService.update(patient);
			SysLogsUtil.saveLogs("修改密码", "用户" + patient.getUserName() + "修改了密码",
					request);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return result;
	}

	@RequestMapping("/changePass")
	public String changePass(HttpServletRequest request, Model model) {
		SysUser user = SessionUtil.getUser(request);
		model.addAttribute("user", user);
		return "/jsp/admin/updateMemberPassword";
	}

	@RequestMapping("/saveUserInfoChange")
	@ResponseBody
	public Result saveUserInfoChange(HttpServletRequest request, Model model,
			SysUser newUser) {
		Result result = Result.createResult().setSuccess(false);
		SysUser user = SessionUtil.getUser(request);
		try {
			newUser.setUserId(user.getUserId());
			userService.updateUser(newUser);
			SysLogsUtil.saveLogs("修改个人信息", "用户" + user.getUserName()
					+ "修改了个人信息", request);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return result;
	}

	@RequestMapping("/changeUserInfo")
	public String changeUserInfo(HttpServletRequest request, Model model) {
		SysUser user = SessionUtil.getUser(request);
		model.addAttribute("user", user);
		return "/jsp/admin/updateUserInfo";
	}

	/**
	 * 显示设置主页风格页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/homestyle")
	public String homeStyle(HttpServletRequest request, Model model) {
		
		CpfPatient patient = SessionUtil.getPatient(request);
		
		model.addAttribute("key", patient.getHomeFlag());
		model.addAttribute("homeFlagMap", Enums.getHomeFlag());

		return "/jsp/admin/homestyle";
	}

	/**
	 * 修改主页风格
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/homeFlag")
	public String homeFlagMap(HttpServletRequest request, Model model) {
	
		CpfPatient patient = SessionUtil.getPatient(request);

		String homeFlag = request.getParameter("homeFlag");
		patient.setHomeFlag(homeFlag);
		patientService.update(patient);

		SysLogsUtil.saveLogs("修改个人信息", "用户" + patient.getUserName() + "修改了主页风格",
				request);
		return "redirect:/member/distribution";
	}

	/**
	 * 主页分发器
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/distribution")
	public String distribution(HttpServletRequest request) {
		CpfPatient patient = SessionUtil.getPatient(request);

		if (patient.getFullname() == null || patient.getIcId() == null) {

			return "redirect:/member/info?state=N";// 汇总统计
		} else {

			String homeFlag = patient.getHomeFlag();
			if (Enums.HomeFlag.SUBTOTAL.equals(homeFlag)) {// 汇总统计
				return "redirect:/member/index";
			}
			if (Enums.HomeFlag.CASE_DATA.equals(homeFlag)) {// 病例资料
				return "redirect:/member/casehistory/list";
			}
			if (Enums.HomeFlag.DOSSIER.equals(homeFlag)) {// 病历夹
				return "redirect:/member/folderShow/list";
			} else {
				return "redirect:/member/index";// 汇总统计
			}
		}
	}

	/**
	 * 跳转修改个人中心
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/info")
	public String UserInfo(HttpServletRequest request, Model model) {
		String state=request.getParameter("state");
		if("N".equals(state)){
			model.addAttribute("error", "请完善个人信息");
		}
		if("Y".equals(request.getParameter("add"))){
			model.addAttribute("error", "修改成功!");
		}
		CpfPatient patient=SessionUtil.getPatient(request);
		hospitalService.getHospitalMap(new SysHospital());
		model.addAttribute("patient", patient);
		model.addAttribute("sexMap", Enums.getSexMap());
		model.addAttribute("hospitalMap", hospitalService.getHospitalMap(new SysHospital()));
		model.addAttribute("email", checkLogingType(patient.getIcLoginFlag(), Enums.LoginType.EMAIL));
		model.addAttribute("ic", checkLogingType(patient.getIcLoginFlag(), Enums.LoginType.IC));
		model.addAttribute("mobile", checkLogingType(patient.getIcLoginFlag(), Enums.LoginType.MOBILE));
		return "tiles.member.info";
	}

	/**
	 * 完善个人信息
	 * 
	 * @param request
	 * @param model
	 * @param patient
	 * @param file
	 * @return
	 */
	@RequestMapping("/updateUserInfo")
	public String updateUserInfo(HttpServletRequest request, Model model,
			CpfPatient patient, @RequestParam("file") MultipartFile file) {

		patientService.update(request, patient, file);
		model.addAttribute("sexMap", Enums.getSexMap());
		SysLogsUtil.saveLogs("完善个人信息", "用户" + patient.getFullname() + "完善个人信息",
				request);
		return "redirect:/member/info/?add=Y";
	}

	@RequestMapping("/checkcardid")
	public void checkCardId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SysUser user = SessionUtil.getUser(request);
		String data = request.getParameter("fieldValue").toString().trim();
		CpfPatient patient = new CpfPatient();
		patient.setCardIdEq(data);
		boolean flag = patientService.findPatientByCardId(patient, user);
		String result = "[\"cardId\"," + flag + "]";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();

	}

	private boolean checkLogingType(String type, String loginType) {
		if (!StringUtils.isBlank(type)) {
			if (type.indexOf(loginType) > -1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
