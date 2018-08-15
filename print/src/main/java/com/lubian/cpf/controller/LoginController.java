package com.lubian.cpf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.CookieUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.service.AdminService;
import com.lubian.cpf.service.job.MailService;
import com.lubian.cpf.service.sys.RelRoleFuncService;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.SysUser;

@Controller
@RequestMapping("/admin")
public class LoginController {
	private Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;


	@RequestMapping("/needLogin")
	public String needLogin(Model model, HttpServletRequest request) {
		String type = request.getParameter("add");
		if ("Y".equals(type)) {
			model.addAttribute("error", "注册成功!");

		} else if ("N".equals(type)) {
			model.addAttribute("error", "注册失败!");
		}

		return "/jsp/admin/login/login";
	}

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/needPatientLogin")
	public String needPatientLogin(Model model) {
		return "/jsp/admin/login/patientlogin";
	}

	@RequestMapping("/needAdminPrivilege")
	public String needAdminPrivilege(Model model) {
		return "/jsp/admin/login/login";
	}

	@RequestMapping("/needDoctorPrivilege")
	public String needDoctorPrivilege(Model model) {
		return "/jsp/admin/login/login";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		return "/jsp/admin/login/login";
	}

	@RequestMapping("/register")
	public String register(HttpServletRequest request, Model model) {
		model.addAttribute("loginType", Enums.getLoginTypeMap());// 登录类型列表
		return "/jsp/admin/login/register";
	}

	@RequestMapping("/backpassword")
	public String backPassword(HttpServletRequest request, Model model) {
		return "/jsp/admin/login/backpassword";
	}

	@RequestMapping("/signIn")
	@ResponseBody
	public Result signin(HttpServletRequest request,
			HttpServletResponse response, Model model, SysUser user) {
		Result result = Result.createResult().setSuccess(false);

		SysUser newUser;
		// 验证码验证
		// String captchText = request.getParameter("captchaText");
		// if (!validateCaptcha(request, captchText)) {
		// result.setError("验证码错误");
		// return result;
		// }

		// 执行验证
		newUser = adminService.adminLogin(user.getUserName(),
				user.getPassword());

		if (newUser == null) {
			
		} else {
			newUser.setAccessToken(StringUtils.getRandomSixString()
					+ StringUtils.getRandomSixString());
			SessionUtil.setUser(request, newUser);
			userService.updateLastLogin(newUser.getUserId(),
					newUser.getAccessToken());
			result.setSuccess(true);
			result.setDataValue("user", newUser);

			// 取当前用户菜单
			Map menuMap = adminService.getMenus(newUser);
			request.getSession().setAttribute(Constant.USER_MENUS, menuMap);

			// 设置菜单选中(选中主页)
			request.setAttribute(Constant.SEL_MENU_CAT, 0);
			request.setAttribute(Constant.SEL_MENU_ITEM, 0);
			CookieUtil.addCookie(response, Constant.COOKIE_MENU_CODE, "0_0");

			SysLogsUtil.saveLogs("用户登录", "用户" + user.getUserName() + "登录了后台系统",
					request);
		}

		return result;
	}


	/**
	 * Validate the captcha response
	 */
	protected boolean validateCaptcha(HttpServletRequest request,
			String inputText) {
		String captchaId = (String) request.getSession().getAttribute(
				Constants.KAPTCHA_SESSION_KEY);
		log.debug("Validating captcha response: '" + inputText + "'");
		if (!StringUtils.isBlank(inputText)
				&& inputText.equalsIgnoreCase(captchaId))
			return true;
		return false;
	}

	@RequestMapping("/checkusernameonly")
	public void checkUserNameOnly(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("fieldValue").toString().trim();

		String result;
		if (!userService.checkUserNameExistance(userName)) {
			result = "[\"username\",true]";
		} else {
			result = "[\"username\",false]";
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

	@RequestMapping("/checkuseronly/{type}")
	public void checkUserOnly(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("type") String type)
			throws ServletException, IOException {

		String data = request.getParameter("fieldValue").toString().trim();
		SysUser user = SessionUtil.getUser(request);
		boolean falg = false;

		String result = null;
		if (Enums.LoginType.IC.equals(type)) {// 判断输入的是什么类型 IC
			result = "[\"ic\"," + falg + "]";
		} else if (Enums.LoginType.EMAIL.equals(type)) {// Email
			result = "[\"email\"," + falg + "]";

		} else if (Enums.LoginType.MOBILE.equals(type)) {// 手机
			result = "[\"mobile\"," + falg + "]";
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

	

}
