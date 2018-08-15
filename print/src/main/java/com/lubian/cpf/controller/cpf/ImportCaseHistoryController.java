package com.lubian.cpf.controller.cpf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.controller.AdminController;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/impor")
public class ImportCaseHistoryController {
	private Logger log = Logger.getLogger(AdminController.class);



	@Autowired
	private CpfCaseHistoryService caseHistoryService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping("/show")
	public String importCasehistory() {
		return "/jsp/doctor/import/import";
	}

	@RequestMapping("/save")
	public String save(HttpServletRequest request, Model model,
			CpfCaseHistory caseHistory, @RequestParam("file") MultipartFile file) {
		SysUser user = SessionUtil.getUser(request);
		Map<String,List<CpfCaseHistory>> m=caseHistoryService.imports(file, user);
		SysLogsUtil.saveLogs("导入病例资料", "导入病例资料", request);
		List<CpfCaseHistory> success=m.get("success");
		List<CpfCaseHistory> failure=m.get("failure");
		model.addAttribute("success", success);
		model.addAttribute("successCount", success.size());
		model.addAttribute("failure", failure);
		model.addAttribute("failureCount", failure.size());
		model.addAttribute("f", "f");
		model.addAttribute("caseTypeMap", Enums.getCaseTypeMap());
		model.addAttribute("relationMap", Enums.getRelationMap());
		return "/jsp/doctor/import/import";
	}

}
