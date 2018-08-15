package com.lubian.cpf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedAdminPrivilege;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.CookieUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.AdminService;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.SysUser;


@NeedAdminPrivilege
@Controller
@RequestMapping("/admin")
public class AdminController {
	private Logger log =Logger.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model,PageModel pm){
		SysUser user = SessionUtil.getUser(request);
		if(user==null || !user.getUserType().equals(Enums.UserType.ADMIN)){
			return "redirect:/admin/login";
		}
				
		return "tiles.admin.index";
	}
		
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model){
		SessionUtil.removeUser(request);
		request.getSession().invalidate();
		CookieUtil.removeCookie(response, Constant.COOKIE_MENU_CODE);
		return "/jsp/admin/login/login";
	}
	
	@RequestMapping("/savePassChange")
	@ResponseBody
	public Result savePassChange(HttpServletRequest request, Model model){
		Result result = Result.createResult().setSuccess(false);
		SysUser user = SessionUtil.getUser(request);
		try{			
			String oldPassword = request.getParameter("oldPassword");
			String password = request.getParameter("password");
			if(!oldPassword.equals(user.getPassword())){
				result.setError("旧密码验证错误！");
				return result;
			}
			user.setPassword(password);
			this.adminService.updatePassword(user);
			SysLogsUtil.saveLogs("修改密码", "用户"+user.getUserName()+"修改了密码", request);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return result ;
	}
	
	@RequestMapping("/changePass")
	public String changePass(HttpServletRequest request, Model model){
		SysUser user = SessionUtil.getUser(request);
		model.addAttribute("user", user);
		return "/jsp/admin/updatePassword";
	}
	
	@RequestMapping("/saveUserInfoChange")
	@ResponseBody
	public Result saveUserInfoChange(HttpServletRequest request, Model model, SysUser newUser ){
		Result result = Result.createResult().setSuccess(false);
		SysUser user = SessionUtil.getUser(request);
		try{
			newUser.setUserId(user.getUserId());
			userService.updateUser(newUser);
			SysLogsUtil.saveLogs("修改个人信息", "用户"+user.getUserName()+"修改了个人信息", request);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return result ;
	}
	
	@RequestMapping("/changeUserInfo")
	public String changeUserInfo(HttpServletRequest request, Model model){
		SysUser user = SessionUtil.getUser(request);
		model.addAttribute("user", user);
		return "/jsp/admin/updateUserInfo";
	}
	
	
	
	
	
}
