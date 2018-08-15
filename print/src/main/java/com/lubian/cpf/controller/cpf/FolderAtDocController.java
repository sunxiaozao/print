package com.lubian.cpf.controller.cpf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.controller.AdminController;
import com.lubian.cpf.service.cpf.CpfCaseFolderAuthorizationService;
import com.lubian.cpf.service.cpf.CpfCaseFolderService;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.sys.SysDepartmentService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseFolderAuthorization;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/doctor")
public class FolderAtDocController {
	private Logger log = Logger.getLogger(AdminController.class);

	@Autowired
	private SysDoctorService sysDoctorService; // 医生表

	@Autowired
	private CpfCaseFolderAuthorizationService cpfCaseFolderAuthorizationService; // 病历夹授权表

	@Autowired
	private CpfCaseHistoryService cpfCaseHistoryService; // 病历资料表

	@Autowired
	private CpfPatientService cpfPatientService; // 病人表

	@Autowired
	private CpfCaseFolderService cpfCaseFolderService; // 病历夹表

	@Autowired
	private SysDepartmentService sysDepartmentService; // 科室表

	/**
	 * 病人的病历夹方式显示
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/folder/list")
	public String getFolder(HttpServletRequest request, Model model,
			PageModel pm, CpfCaseHistory cpfCaseHistory) {

		// 1获得医生信息

		SysUser user = SessionUtil.getUser(request);
		SysDoctor sysDoctor = new SysDoctor();
		sysDoctor.setUserId(user.getUserId());
		sysDoctor = sysDoctorService.findDoctor(sysDoctor);
		// 2获得权限信息
		CpfCaseFolderAuthorization cpfCaseFolderAuthorization = new CpfCaseFolderAuthorization();

		// 登入者为超级医生，置空查询条件
		if (user != null && user.getUserType().equals(Enums.UserType.DOCTOR)) {
			cpfCaseFolderAuthorization
					.setDoctorEq(sysDoctor.getId().toString());
		}
		cpfCaseFolderAuthorization.setHospitalId(sysDoctor.getHospitalId());
		String folderIds=cpfCaseFolderAuthorizationService.conditionFindIds(cpfCaseFolderAuthorization);
		
		if(folderIds!=null){
			// 3获得病历夹信息
			CpfCaseFolder cpfCaseFolder=new CpfCaseFolder();
			cpfCaseFolder.setStatus(Enums.caseFolderStatus.NORMAL);//查看没有删除的
			cpfCaseFolder.setId_instr(folderIds);
			Map folderMap=cpfCaseFolderService.findFolder(cpfCaseFolder);
			String folderId=(String) folderMap.get("folderIds");
			String patientIds=(String) folderMap.get("patientIds");
			
			
			
			CpfPatient patient=new CpfPatient();
		
			patient.setId_instr(patientIds);
			List<CpfPatient> lCpfPatients=cpfPatientService.findList(patient);
			model.addAttribute("patients", lCpfPatients);
			model.addAttribute("folders", folderMap.get("list"));
			
			// 4获得病例信息
			
			if(cpfCaseHistory.getFolderId()==null){
				cpfCaseHistory.setFolderId_instr(folderId);
			}
			
			pm=cpfCaseHistoryService.freeFind(cpfCaseHistory);
			
			model.addAttribute("pm", pm);

			// 就诊类别
			Map CaseTypeMap = Enums.getCaseTypeMap();
			model.addAttribute("CaseTypeMap", CaseTypeMap);
			model.addAttribute("cpfCaseHistory", cpfCaseHistory);
		}
		

		return "tiles.doctor.folder";
	}

	/**
	 * 选中胶片，诊断书等，右侧显示
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/folder/selectDetail")
	public String selectDetail(HttpServletRequest request, Model model,
			PageModel pm) {
		// 得到登陆医生
		SysUser user = SessionUtil.getUser(request);
		SysDoctor sysDoctor = sysDoctorService.findDoctorByUserId(user
				.getUserId());

		// 得到病人id
		String patientId = request.getParameter("patientId");
		CpfPatient patient = new CpfPatient();
		patient.setId(Integer.parseInt(patientId));
		patient = cpfPatientService.findByPK(patient);

		// 得到科室名称
		String details = request.getParameter("details");
		String folder = request.getParameter("folderId");
		if (null != details && !details.equals("")) {
			CpfCaseHistory cpfCaseHistory = new CpfCaseHistory();
			// cpfCaseHistory.setPatientId(patient.getId());
			cpfCaseHistory.setFolderId(Integer.parseInt(folder));
			cpfCaseHistory.setCaseType(details);

			// 医生的限制
			if (user.getUserType().equals(Enums.UserType.DOCTOR)) {// 普通医生
				// cpfCaseHistory.setDoctorId(sysDoctor.getId());
			} else if (user.getUserType().equals(Enums.UserType.SUPER_DOCTOR)) {// 超级医生
				cpfCaseHistory.setDoctorId(null);
				cpfCaseHistory.setHospitalId(sysDoctor.getHospitalId());
			}

			pm = cpfCaseHistoryService.freeFind(cpfCaseHistory);
		}

		if (null != pm) {
			model.addAttribute("pm", pm);
		}

		// 就诊类别
		Map CaseTypeMap = Enums.getCaseTypeMap();
		model.addAttribute("CaseTypeMap", CaseTypeMap);
		model.addAttribute("details", details);
		model.addAttribute("folderId", folder);
		model.addAttribute("patientId", patient.getId());
		return "/jsp/doctor/caseFolder/caseFolder_table";
	}

}
