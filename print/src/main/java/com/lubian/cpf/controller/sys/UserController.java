package com.lubian.cpf.controller.sys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedAdminPrivilege;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.Encrypt;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.AdminService;
import com.lubian.cpf.service.sys.RoleService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.service.sys.UserGroupService;
import com.lubian.cpf.service.sys.UserOrgService;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysOrg;
import com.lubian.cpf.vo.SysUser;
import com.lubian.cpf.vo.SysUserGroup;

@NeedLogin
@NeedAdminPrivilege
@Controller
@RequestMapping("/admin/user")
public class UserController {
	private Logger log = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserOrgService orgService;
	@Autowired
	private UserGroupService groupService;

	@Autowired
	private SysDoctorService doctorService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("/list")
	public String getUserList(HttpServletRequest request, Model model, PageModel pm, SysUser user) {
		String add = request.getParameter("add");
		if ("Y".equals(add)) {
			model.addAttribute("error", "新增用户保存成功");
		} else if ("N".equals(add)) {
			model.addAttribute("error", "用户信息修改成功");
		}

		if ("Y".equals(request.getParameter("import"))) {
			model.addAttribute("error", "医生导入成功,成功导入" + request.getParameter("success") + "条,失败" + request.getParameter("failure") + "条");
		}

		user.setLastLoginDtTo(DateUtil.addOneDay(user.getLastLoginDtTo()));
		pm = userService.getAdminUserList(user);
		request.getSession().setAttribute("userSearch", user);
		model.addAttribute("pm", pm);

		List roleList = roleService.getAllRoleList();
		model.addAttribute("roleList", roleList);

		List<SysUserGroup> groupLst = groupService.getAllGroupList();
		Map<Integer, String> groupMap = new HashMap<Integer, String>();
		for (SysUserGroup info : groupLst) {
			groupMap.put(info.getGroupId(), info.getGroupName());
		}
		model.addAttribute("groupMap", groupMap);

		List<SysOrg> orgList = orgService.getAllOrgList();
		Map<Integer, String> orgMap = new HashMap<Integer, String>();
		for (SysOrg info : orgList) {
			orgMap.put(info.getOrgId(), info.getOrgName());
		}
		model.addAttribute("orgMap", orgMap);

		Map map = Enums.getUserType();
		model.addAttribute("map", map);
		model.addAttribute("patienttemp", Constant.EXCEL_PATIENT_DATA_PATH);
		model.addAttribute("doctortemp", Constant.EXCEL_DOCTOR_DATA_PATH);
		return "tiles.admin.userList";
	}

	@RequestMapping("/ret")
	public String retUserList(HttpServletRequest request, Model model, PageModel pm) {
		SysUser user = (SysUser) request.getSession().getAttribute("userSearch");
		if (user == null) {
			user = new SysUser();
		}
		return this.getUserList(request, model, pm, user);
	}

	@RequestMapping("/delete/{userId}")
	public String deleteUser(HttpServletRequest request, @PathVariable("userId") Integer userId) {
		SysUser user = new SysUser();
		user.setUserId(userId);
		user = userService.findByPK(user);
		userService.deleteUser(user);
		SysLogsUtil.saveLogs("用户管理", "删除用户：" + user.getUserName(), request);
		return "redirect:/admin/user/ret";
	}

	@RequestMapping("/audit/{userId}")
	public String auditUser(HttpServletRequest request, @PathVariable("userId") Integer userId) {
		SysUser user = new SysUser();
		user.setUserId(userId);
		user = userService.findByPK(user);
		user.setEnabled(1);
		userService.updateUser(user);
		SysLogsUtil.saveLogs("用户管理", "审核用户：" + user.getUserName(), request);
		return "redirect:/admin/user/ret";
	}

	// 查询某一用户信息
	@RequestMapping("/edit/{userId}")
	public String editUser(HttpServletRequest request, Model model, @PathVariable("userId") Integer userId) {
		String add = request.getParameter("add");
		if ("Y".equals(add)) {
			model.addAttribute("error", "新增用户保存成功");
		}
		SysUser user = new SysUser();
		user.setUserId(userId);
		user = userService.findByPK(user);
		model.addAttribute("user", user);

		Map map = Enums.getUserType();
		model.addAttribute("map", map);

		if (Enums.UserType.SUPER_DOCTOR.equals(user.getUserType()) || Enums.UserType.DOCTOR.equals(user.getUserType())) {
			model.addAttribute("type", user.getUserType());
			SysDoctor doctor = doctorService.findDoctorByUserId(user.getUserId());
			model.addAttribute("hospital", doctor.getHospitalId());
			model.addAttribute("department", doctor.getDepartmentId());
			model.addAttribute("title", doctor.getTitle());
		}

		List roleList = roleService.getAllRoleList();
		model.addAttribute("roleList", roleList);
		List<SysOrg> orgList = orgService.getAllOrgList();
		model.addAttribute("orgList", orgList);
		List<SysUserGroup> groupList = groupService.getAllGroupList();
		model.addAttribute("groupList", groupList);

		return "tiles.admin.userEdit";
	}

	@RequestMapping("saveEditUser")
	public String saveEditUser(HttpServletRequest request, Model model, SysUser user) {

	
		if (user.getPassword() != null && !"".equals(user.getPassword())) {
			user.setPassword(Encrypt.md5(user.getPassword()));
		}else{
			user.setPassword(null);
		}
		userService.updateUser(user);
		SysLogsUtil.saveLogs("用户管理", "更新用户信息：" + user.getUserName(), request);
		return "redirect:/admin/user/list/?add=N";
	}

	@RequestMapping("/saveEditMember")
	public String saveEditMember(HttpServletRequest request, Model model, SysUser user) {

		userService.UpdateMember(user);
		SysLogsUtil.saveLogs("用户管理", "更新用户信息：" + user.getUserName(), request);
		return "redirect:/admin/user/list/?add=N";
	}

	@RequestMapping("/saveEditDoctor")
	public String saveEditDoctor(HttpServletRequest request, Model model, SysUser user) {

		userService.UpdateDoctor(user,request);
		SysLogsUtil.saveLogs("用户管理", "更新用户信息：" + user.getUserName(), request);
		return "redirect:/admin/user/list/?add=N";
	}

	@RequestMapping("/add")
	public String addUser(HttpServletRequest request, Model model, SysUser user) {
		List roleList = roleService.getAllRoleList();
		model.addAttribute("roleList", roleList);
		if (StringUtils.isBlank(user.getUserType())) {
			user.setUserType("A");
		}
		model.addAttribute("user", user);
		Map userTypeMap = Enums.getUserTypes();

		userTypeMap.remove(Enums.UserType.MEMBER);
		model.addAttribute("userTypeMap", userTypeMap);

		return "tiles.admin.userAdd";
	}

	@RequestMapping("/saveAdd")
	public String saveInsertUser(HttpServletRequest request, Model model, SysUser user) {
		try {
			// 检查用户名是否已经被使用
			if (userService.getUserByUserName(user.getUserName()) != null) {
				model.addAttribute("error", "该用户名已经存在，请重新输入");
				return this.addUser(request, model, user);
			}

			user.setPassword(StringUtils.md5(user.getPassword()));
			userService.insert(user);
			SysLogsUtil.saveLogs("用户管理", "创建用户：" + user.getUserName(), request);
		} catch (Exception e) {
			log.error(e);
		}
		return "redirect:/admin/user/list/?add=Y";
	}

	/*
	 * @RequestMapping("/saveMember") public String savetUser(HttpServletRequest request, Model model, SysUser user) { try { // 检查用户名是否已经被使用 SysLogsUtil.saveLogs("用户管理", "创建病人用户用户：" + user.getUserName(), request); userService.save(user); } catch (Exception e) { log.error(e); } return "redirect:/admin/user/list/?add=Y"; }
	 */
	@RequestMapping("/saveDoctor")
	public String savetDoctor(HttpServletRequest request, Model model, SysUser user) {
		try {

			SysLogsUtil.saveLogs("用户管理", "创建医生用户用户：" + user.getUserName(), request);
			userService.saveDocer(user, request);
		} catch (Exception e) {
			log.error(e);
		}
		return "redirect:/admin/user/list/?add=Y";
	}

	@RequestMapping("/activate")
	@ResponseBody
	public Result activateMember(HttpServletRequest request, Model model, SysUser user) {
		Result reslut = Result.createResult().setSuccess(false);
		if (user.getUserId() == null)
			return reslut;
		user.setEnabled(1);
		userService.updateUser(user);
		reslut.setSuccess(true);
		return reslut;
	}

	@RequestMapping("/changeUserPass")
	@ResponseBody
	public Result changeUserPass(HttpServletRequest request, Model model, SysUser user) {
		Result result = Result.createResult().setSuccess(false);
		try {
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmPassword");
			if (password != null && !password.equals(confirmPassword)) {
				result.setError("确认密码和新密码必须相同");
				return result;
			}
			user.setPassword(password);
			this.adminService.updatePassword(user);
			SysLogsUtil.saveLogs("用户管理", "修改用户密码：" + user.getUserName(), request);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	@RequestMapping("/import")
	public String userImport() {
		return "/jsp/admin/user/import/import";
	}

	
}
