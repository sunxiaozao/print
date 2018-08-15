package com.lubian.cpf.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedDoctorLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.CookieUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.AdminService;
import com.lubian.cpf.service.cpf.CpfCaseFolderAuthorizationService;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.sys.SysDepartmentService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@NeedDoctorLogin
@Controller
@RequestMapping("/doctor")
public class DoctorController {
	private Logger log = Logger.getLogger(DoctorController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;

	@Autowired
	private CpfCaseFolderAuthorizationService cFolderAuthorizationService;// 病历夹权限

	@Autowired
	private CpfCaseHistoryService caseHistoryService;

	@Autowired
	private SysDoctorService doctorService;

	@Autowired
	private SysHospitalService hospitalService;

	@Autowired
	private SysDepartmentService departmentService;

	@Autowired
	private SysDepartmentService sysDepartmentService; // 科室表

	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model, PageModel pm, CpfCaseHistory caseHistory) {
		SysUser user = SessionUtil.getUser(request);

		// 若选中科室
		Integer departmentIdInt = 0;
		String departmentId = request.getParameter("departmentId");
		if (null != departmentId && !departmentId.equals("")) {
			departmentIdInt = Integer.parseInt(departmentId);
			model.addAttribute("departmentId", departmentIdInt);
		}

		SysDoctor doctor = new SysDoctor();
		doctor.setUserId(user.getUserId());
		doctor = doctorService.findDoctor(doctor);// 获得医生信息
		// CpfCaseFolderAuthorization vo = new CpfCaseFolderAuthorization();
		// 判断用户是医生还是超级医生
		if (0 == departmentIdInt) {// 没有选择科室时，病历资料的科室id 为登陆医生的所属科室id
			//caseHistory.setDepartmentId(doctor.getDepartmentId());// 科室
			caseHistory.setDepartmentId(null);// 科室
		} else {
			caseHistory.setDepartmentId(departmentIdInt);// 科室
		}
		if (null != doctor) {
			if (user.getUserType().equals(Enums.UserType.DOCTOR)) {// 普通医生
				/*
				 * vo.setDoctorEq(doctor.getId().toString()); String folderIds = cFolderAuthorizationService.getIds(vo); if (folderIds != null && !"".equals(folderIds)) { caseHistory.setFolderId_instr(folderIds);// 病历夹ID集合0 //caseHistory.setViewStatusEq(Enums.isYesOrIsNo.IS_YES);查看状态是是否查看，不是能否查看 pm = caseHistoryService.freeFind(caseHistory); }
				 */
				caseHistory.setHospitalId(doctor.getHospitalId());
				caseHistory.setHospitalId(doctor.getHospitalId());
				caseHistory.setSource(Enums.Relation.TEMPORARY_DATA);
				pm = caseHistoryService.freeFind(caseHistory);
			} else if (user.getUserType().equals(Enums.UserType.SUPER_DOCTOR)) {
				List<SysHospital> hospitals=hospitalService.findFind(doctor.getHospitalId());
				String ids=getIDs(hospitals);
				
				//caseHistory.setSource(Enums.Relation.TEMPORARY_DATA);
				
				if(caseHistory.getHospitalId()==null){
					caseHistory.setHospitalId_instr(ids);
				}
				
				pm = caseHistoryService.freeFind(caseHistory);
				
				model.addAttribute("hospitals", hospitals);
			}
		}

		// CpfCaseHistory caseHistory = new CpfCaseHistory();

		HttpSession session = request.getSession();
		session.setAttribute("departMap", sysDepartmentService.getDepMapByIds(doctor.getId(), user.getUserType(), null));

		model.addAttribute("exceltemp", Constant.EXCEL_CASE_DATA_PATH);

		model.addAttribute("pm", pm);
		model.addAttribute("caseHistory", caseHistory);
		model.addAttribute("caseTypeMap", Enums.getCaseTypeMap());// 获取病例资料类型
		model.addAttribute("RelationMap", Enums.getRelation());// 获取病例资料类型

		if (user.getUserType().equals(Enums.UserType.SUPER_DOCTOR)) {
			return "tiles.sdoctor.index";
		}
		return "tiles.doctor.index";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
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
		SysUser user = SessionUtil.getUser(request);
		try {
			String oldPassword = request.getParameter("oldPassword");
			String password = request.getParameter("password");
			if (!oldPassword.equals(user.getPassword())) {
				result.setError("旧密码验证错误！");
				return result;
			}
			user.setPassword(password);

			String type = user.getAccountType();
			if (type != null) {
				if (type.length() == 1 && type.indexOf(Enums.LoginType.INITIAL) > -1) {
					user.setAccountType("D");
				} else if (type.length() > 1) {
					if (type.indexOf(Enums.LoginType.INITIAL) == 0) {
						user.setAccountType(type.substring(type.indexOf(Enums.LoginType.INITIAL) + 2));
					} else if (type.indexOf(Enums.LoginType.INITIAL) == type.length() - 1) {
						user.setAccountType(type.substring(0, type.indexOf(Enums.LoginType.INITIAL) - 1));
					} else {
						user.setAccountType(type.substring(0, type.indexOf(Enums.LoginType.INITIAL)) + type.substring(type.indexOf(Enums.LoginType.INITIAL) + 2));
					}
				}

			}

			this.adminService.updatePassword(user);
			SysLogsUtil.saveLogs("修改密码", "用户" + user.getUserName() + "修改了密码", request);
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
		return "/jsp/admin/updateDoctorPassword";
	}

	@RequestMapping("/saveUserInfoChange")
	@ResponseBody
	public Result saveUserInfoChange(HttpServletRequest request, Model model, SysUser newUser) {
		Result result = Result.createResult().setSuccess(false);
		SysUser user = SessionUtil.getUser(request);
		try {
			newUser.setUserId(user.getUserId());
			userService.updateUser(newUser);
			SysLogsUtil.saveLogs("修改个人信息", "用户" + user.getUserName() + "修改了个人信息", request);
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

	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, Model model) {
		String addFlag = request.getParameter("add");
		if ("Y".equals(addFlag)) {
			model.addAttribute("error", "数据添加成功");
		}

		SysDoctor doctor = new SysDoctor();
		doctor.setUserId(SessionUtil.getUser(request).getUserId());
		doctor = doctorService.findDoctor(doctor);// 获取医生信息
		SysDepartment department = new SysDepartment();
		department.setId(doctor.getDepartmentId());
		department = departmentService.findByPK(department);// 获取科室信息
		SysHospital hostpital = new SysHospital();
		hostpital.setId(doctor.getHospitalId());
		hostpital = hospitalService.findByPK(hostpital);// 获取医院信息

		SysUser user = new SysUser();
		user.setUserId(doctor.getUserId());

		user = userService.findByPK(user);// 获得用户信息

		String type = checheLoginType(user.getAccountType());// 获得当前登录类型
		model.addAttribute("type", type);
		model.addAttribute("user", user);
		model.addAttribute("hostpital", hostpital);
		model.addAttribute("doctor", doctor);
		model.addAttribute("department", department);
		model.addAttribute("sexMap", Enums.getSexMap());// 性别信息
		model.addAttribute("loginType", Enums.getLoginTypeMap());// 登录类型列表
		return "tiles.doctor.edit";
	}

	@RequestMapping("/saveEdit")
	public String saveEditDict(HttpServletRequest request, Model model, SysDoctor doctor) {

		try {
			doctorService.update(doctor, request);
			SysLogsUtil.saveLogs("医生管理", "修改医生信息:" + doctor.getFullname() + doctor.getTitle(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/doctor/edit" + "?add=Y";
	}

	/**
	 * 判断登录类型
	 * 
	 * @param type
	 * @return
	 */
	private String checheLoginType(String type) {
		if (null != type && !"".equals(type)) {
			if (type.indexOf(Enums.LoginType.IC) > -1) {
				return Enums.LoginType.IC;
			} else if (type.indexOf(Enums.LoginType.EMAIL) > -1) {
				return Enums.LoginType.EMAIL;
			} else if (type.indexOf(Enums.LoginType.MOBILE) > -1) {
				return Enums.LoginType.MOBILE;
			} else {
				return null;
			}
		} else {
			return null;
		}
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
		SysUser user = SessionUtil.getUser(request);
		SysDoctor doctor = new SysDoctor();
		doctor = doctorService.findDoctorByUserId(user.getUserId());
		model.addAttribute("key", doctor.getHomeFlag());
		model.addAttribute("homeFlagMap", Enums.getHomeFlag());

		return "/jsp/admin/doctorhomestyle";
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
		SysUser user = SessionUtil.getUser(request);
		SysDoctor doctor = new SysDoctor();
		doctor = doctorService.findDoctorByUserId(user.getUserId());

		String homeFlag = request.getParameter("homeFlag");
		doctor.setHomeFlag(homeFlag);
		doctorService.update(doctor);

		SysLogsUtil.saveLogs("修改个人信息", "用户" + user.getUserName() + "修改了主页风格", request);
		return "redirect:/doctor/distribution";
	}

	/**
	 * 主页分发器
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/distribution")
	public String distribution(HttpServletRequest request, Model model) {
		SysUser user = SessionUtil.getUser(request);
		SysDoctor doctor = new SysDoctor();
		doctor = doctorService.findDoctorByUserId(user.getUserId());
		String uString = user.getAccountType();
		if (uString != null && uString.indexOf(Enums.LoginType.INITIAL) > -1) {
			model.addAttribute("error", "您的密码为初始密码，请您及时修改！");
		}

		String homeFlag = doctor.getHomeFlag();
		if (Enums.HomeFlag.SUBTOTAL.equals(homeFlag)) {
			return "forward:/doctor/subtotal";
		}
		if (Enums.HomeFlag.CASE_DATA.equals(homeFlag)) {
			return "forward:/doctor/index";
		}
		if (Enums.HomeFlag.DOSSIER.equals(homeFlag)) {
			return "forward:/doctor/folder/list";
		} else {
			return "forward:/doctor/index";
		}
	}

	private String getIDs(List<SysHospital> list) {
		String ids = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ids += list.get(i).getId();
				if (i < list.size() - 1) {
					ids += ",";
				}
			}
		}
		return ids;
	}

}
