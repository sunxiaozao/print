package com.lubian.cpf.controller.sys;

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

import com.lubian.cpf.common.annotation.NeedAdminPrivilege;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.sys.SysLogsService;
import com.lubian.cpf.vo.SysLogs;

@NeedLogin
@NeedAdminPrivilege
@Controller
@RequestMapping("/admin/syslog")
public class SysLogsController {
	private Logger log =Logger.getLogger(SysLogsController.class);
	@Autowired
	private SysLogsService sysLogsService;	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("/list")
	public String getSysLogsList(HttpServletRequest request, Model model,SysLogs sysLogs){
		sysLogs.setCdateTo(DateUtil.addOneDay(sysLogs.getCdateTo()));
		PageModel pm = sysLogsService.getSysLogs(sysLogs);
		model.addAttribute("pm", pm);
		request.getSession().setAttribute("sysLogsSearch", sysLogs);
		return "tiles.admin.sysLogs";
	}
	
	@RequestMapping("/ret")
	public String retSysLogsList(HttpServletRequest request, Model model) {
		SysLogs sysLogs = (SysLogs)request.getSession().getAttribute("sysLogsSearch");
		if(sysLogs==null){
			sysLogs = new SysLogs();
		}
		return this.getSysLogsList(request, model, sysLogs);
	}
	
	@RequestMapping("/delete/{logId}")
	public String deleteSysLogs(HttpServletRequest request, Model model, @PathVariable("logId") Integer logId) {
		SysLogs sysLog = new SysLogs();
	    sysLog.setLogId(logId);
		sysLogsService.remove(sysLog);
		return "redirect:/admin/syslog/ret";
	}
}
