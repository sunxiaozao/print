package com.lubian.cpf.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedAdminPrivilege;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.common.util.StringUtils;

@NeedAdminPrivilege
@Controller
@RequestMapping("/admin/patient")
public class PatientController {
	private Logger log =Logger.getLogger(PatientController.class);
	@Autowired
	private CpfPatientService cpfPatientService;
	
	@RequestMapping("/list")
	public String getUserList(HttpServletRequest request, Model model, PageModel pm, CpfPatient vo) {
		pm=cpfPatientService.freeFind(vo);
		model.addAttribute("pm", pm);
		model.addAttribute("patientSearch", vo);
		model.addAttribute("sexMap", Enums.getSexMap());
		return "tiles.admin.patientList";
	}
	
	@RequestMapping("/savePassChange")
	@ResponseBody
	public Result savePassChange(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);
		CpfPatient patient=new CpfPatient();
		
		try {
			String patientIdStr = request.getParameter("patientId");
			Integer patientId=0;
			if (!StringUtils.isBlank(patientIdStr)) {
				patientId=Integer.parseInt(patientIdStr);
			}
			patient.setId(patientId);
			patient=cpfPatientService.findByPK(patient);
			
			//String oldPassword = request.getParameter("oldPassword");
			String password = request.getParameter("password");
			/*if (!oldPassword.equals(patient.getPassword())) {
				result.setError("旧密码验证错误！");
				return result;
			}*/
			patient.setId(patientId);
			patient.setPassword(password);
			cpfPatientService.update(patient);
			SysLogsUtil.saveLogs("修改密码", "用户" + patient.getUserName() + "修改了密码",
					request);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return result;
	}

	@RequestMapping("/changePass/{patientId}")
	public String changePass(HttpServletRequest request, Model model,@PathVariable("patientId") Integer patientId) {
		//SysUser user = SessionUtil.getUser(request);
		
		/*CpfPatient patient=new CpfPatient();
		patient.setId(patientId);
		patient=cpfPatientService.findByPK(patient);*/
	
		model.addAttribute("patientId", patientId);
		return "/jsp/admin/patient/updatePass";
	}
	
/*	@RequestMapping("/edit/{patientId}")
	public String edit(HttpServletRequest request, Model model, @PathVariable("patientId") Integer patientId) {
		return "tiles.admin.patientEdit";
	}
	
	@ResponseBody
	@RequestMapping("/saveEdit")
	public Result saveEditGroupNotReturn(HttpServletRequest request, Model model,CpfPatient vo) {
		Result result = Result.createResult().setSuccess(false);
		try {
			//this.saveEditGroup(request, model, group);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
			return result;
		}
		result.setSuccess(true);
		return result;
	}
	*/

}
