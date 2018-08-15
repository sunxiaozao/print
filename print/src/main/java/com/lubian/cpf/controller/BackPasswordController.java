package com.lubian.cpf.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.controller.AdminController;
import com.lubian.cpf.service.sys.SysMailJobService;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.SysMailJob;
import com.lubian.cpf.vo.SysUser;

@Controller
@RequestMapping("/admin")
public class BackPasswordController {
	private Logger log = Logger.getLogger(AdminController.class);
	
	@Autowired
	private SysMailJobService mailJobService;
	
	@Autowired
	private UserService userService;


	/**
	 * 检验邮箱是否存在
	 * 
	 * @param request
	 * @param caseHistoryId
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkFindPass")
	@ResponseBody
	public Result checkFindPass(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);

		String inputEmail = request.getParameter("email");
		SysUser user=userService.getUserByEmail(inputEmail);
		
		if (null==user) {
			result.setDataValue("msg", "0");//邮箱不存在
		} else {
			result.setDataValue("msg", "1");
		}

		result.setSuccess(true);

		return result;
	}
	/**
	 * 找回密码 邮件发送
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/sendEmail")
	@ResponseBody
	public Result sendEmail(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);
		StringBuffer content=new StringBuffer();//发送内容
		try {

			String email=request.getParameter("email");
			
			//向邮件表插入一条记录，后台计划会定时检索邮件表顺序发送邮件
			SysMailJob mailJob = new SysMailJob();
			mailJob.setToEmailStr(email);//只接受逗号分隔的多个email地址，此处注意修改
			mailJob.setSubject("夏宇科技微医通");
			mailJob.setContent(content.toString());
			mailJob.setMailType(Enums.MailType.SEND_NOW);//立即发送(最长延迟一分钟)
			mailJob.setMailStatus(Enums.MailJobStatus.INIT);//初始化邮件状态
			
			//邮件模板，customize则没有模板直接发送content内容,可以在WEB-INF/ftl中新增邮件模板，
			//然后在MailService中针对模板进行业务处理参数设置
			mailJob.setBisType(Enums.MailBizType.FIND_PASSWORD);
			mailJobService.insert(mailJob);
			
			//SysLogsUtil.saveLogs("找回密码", "用户"+user.getUserName()+"发送找回密码请求", request);
			
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
			result.setDataValue("msg", e.getMessage());
		}
		//return "redirect:/member/share/list?share=Y&email="+emails;
		return result;
	}
	
	/**
	 * 找回密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/findPass")
	public String findPass(HttpServletRequest request, Model model) {
		String email=request.getParameter("email");
		model.addAttribute("email", email);
		return "/jsp/admin/login/passEdit";
	}
	
	/**
	 * 找回密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/updPass")
	@ResponseBody
	public Result updPass(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);
		String password=request.getParameter("inputPassword");
		String againPassword=request.getParameter("againPassword");
		String email=request.getParameter("email");
		//通过邮箱得到此用户
		SysUser user=userService.getUserByEmail(email);
		if (null!=user) {
			if (password.equals(againPassword)) {
				user.setUserId(user.getUserId());
				user.setPassword(password);
				userService.updateUser(user);
			}else{
				result.setDataValue("msg", "两次密码不一致！");
			}
		}
		result.setSuccess(true);
		return result;
	}


	
}
