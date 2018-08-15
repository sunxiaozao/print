package com.lubian.cpf.controller.cpf;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.controller.AdminController;
import com.lubian.cpf.service.cpf.CpfCaseFolderAuthorizationService;
import com.lubian.cpf.service.cpf.CpfCaseFolderService;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfPatientRelationUserService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.sys.SysDepartmentService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseFolderAuthorization;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/member")
public class DossierController {
	private Logger log = Logger.getLogger(AdminController.class);

	@Autowired
	private CpfCaseFolderService cpfCaseFolderService; // 病历夹表

	@Autowired
	private SysDepartmentService sysDepartmentService; // 科室表

	@Autowired
	private SysHospitalService sysHospitalService; // 医院表

	@Autowired
	private CpfPatientService cpfPatientService; // 病人表

	@Autowired
	private CpfCaseFolderAuthorizationService cpfCaseFolderAuthorizationService; // 病历夹授权表

	@Autowired
	private SysDoctorService sysDoctorService; // 医生表

	@Autowired
	private CpfCaseHistoryService cpfCaseHistoryService; // 病历资料表

	@Autowired
	private CpfPatientRelationUserService pService;

	/**
	 * 病人病历夹查询
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/folder/list")
	public String getFolder(HttpServletRequest request, Model model, PageModel pm) {

		// 新增病历夹结果
		if ("Y".equals(request.getParameter("saveAdd"))) {
			model.addAttribute("error", "病历夹添加成功!");
		}
		
		if ("N".equals(request.getParameter("saveAdd"))) {
			model.addAttribute("error", "病历夹添加失败!");
		}
		// 新增病历夹结果
		if ("Y".equals(request.getParameter("saveEdit"))) {
			model.addAttribute("error", "病历夹添加成功!");
		}
		if ("N".equals(request.getParameter("saveEdit"))) {
			model.addAttribute("error", "病历夹添加失败!");
		}
		if ("M".equals(request.getParameter("isOrNo"))) {
			model.addAttribute("error", "删除失败!该病历夹下存在病例资料请勿删除!");
		}
		if ("Y".equals(request.getParameter("M"))) {
			model.addAttribute("error", "病历夹删除成功!");
		}
		if ("N".equals(request.getParameter("M"))) {
			model.addAttribute("error", "病历夹删除失败");
		}

		CpfPatient patient = SessionUtil.getPatient(request);
		String patientIds = pService.freeFind(null, patient.getId());

		if (patientIds != null) {// 病人id不为空
			CpfCaseFolder cpfCaseFolder = new CpfCaseFolder();
			cpfCaseFolder.setPatientId_instr(patientIds);
			cpfCaseFolder.setStatus(Enums.caseFolderStatus.NORMAL);
			pm = cpfCaseFolderService.freeFind(cpfCaseFolder);
			model.addAttribute("pm", pm);
		}

		// 得到科室名称
		SysDepartment department = new SysDepartment();
		Map<Integer, String> departmentMap = sysDepartmentService.getDepartmentMap(department);
		model.addAttribute("departmentsMap", departmentMap); // 科室map集合

		// 得到医院名称
		SysHospital hospital = new SysHospital();
		Map<Integer, String> hospitalMap = sysHospitalService.getHospitalMap(hospital);
		model.addAttribute("hospitalMap", hospitalMap); // 医院map集合
		model.addAttribute("patient", patient); // 医院map集合

		return "tiles.member.folder";
	}

	@RequestMapping("/folder/addshow")
	public String addFoladerShow(HttpServletRequest request, Model model, CpfCaseFolder cpfCaseFolder) {

		model.addAttribute("hospitalMp", sysHospitalService.getHospitalMap(new SysHospital()));
		model.addAttribute("type", "add");
		return "/jsp/member/folder/caseFolderAdd";

	}

	/**
	 * 病人新增病历夹
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/folder/add")
	public String addFolder(HttpServletRequest request, Model model, CpfCaseFolder cpfCaseFolder) {

		CpfPatient patient = SessionUtil.getPatient(request);
		String str = request.getParameter("hospital");

		Integer hospital = 0;
		if (str != null) {
			hospital = Integer.parseInt(str);
		}
		cpfCaseFolder.setHospitalId(hospital);
		String name = patient.getUserName();
		cpfCaseFolder.setPatientId(patient.getId());
		cpfCaseFolder.setCreateTime(new Date());
		cpfCaseFolder.setCreator(name);
		cpfCaseFolder.setStatus(Enums.caseFolderStatus.NORMAL); // 病历夹状态
		cpfCaseFolder.setType("0"); // 病历夹类型
		cpfCaseFolderService.insert(cpfCaseFolder);
		SysLogsUtil.saveLogs("病历夹管理", "病人：" + name + "增加《" + cpfCaseFolder.getFolderName() + "》成功", request);

		return "redirect:/member/folder/list?saveAdd=Y";
	}

	/**
	 * 跳转到修改病历夹页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/folder/edit/{id}")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
		// 得到该病例夹
		CpfCaseFolder cpfCaseFolder = new CpfCaseFolder();
		cpfCaseFolder.setId(id);
		cpfCaseFolder = cpfCaseFolderService.findByPK(cpfCaseFolder);
		model.addAttribute("caseFolder", cpfCaseFolder);

		model.addAttribute("hospitalMp", sysHospitalService.getHospitalMap(new SysHospital()));
		return "/jsp/member/folder/caseFolderEdit";
	}

	/**
	 * 修改病历夹
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/folder/saveEdit")
	public String saveEditFolder(HttpServletRequest request, Model model, CpfCaseFolder cpfCaseFolder) {

		CpfPatient patient = SessionUtil.getPatient(request);

		String name = patient.getUserName();

		String str = request.getParameter("hospital");
		String type = "N";
		try {
			Integer hospital = 0;
			if (str != null) {
				hospital = Integer.parseInt(str);
			}

			cpfCaseFolder.setPatientId(patient.getId());

			cpfCaseFolder.setHospitalId(hospital);
			cpfCaseFolder.setModifyTime(new Date());
			cpfCaseFolder.setModifier(name);
			cpfCaseFolder.setStatus(Enums.caseFolderStatus.NORMAL); // 病历夹状态
			cpfCaseFolder.setType("0"); // 病历夹类型
			cpfCaseFolderService.update(cpfCaseFolder);
			SysLogsUtil.saveLogs("病历夹管理", "病人：" + name + "修改《" + cpfCaseFolder.getFolderName() + "》成功", request);
			type = "Y";
		} catch (Exception e) {
			SysLogsUtil.saveLogs("病历夹管理", "病人：" + name + "修改《" + cpfCaseFolder.getFolderName() + "》失败", request);
			e.printStackTrace();
		}
		return "redirect:/member/folder/list?saveEdit=" + type;

	}

	/**
	 * 授权管理
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/folder/impower/{id}/{patientId}")
	public String impower(HttpServletRequest request, Model model, PageModel pm, @PathVariable("id") Integer id, @PathVariable("patientId") Integer patientId) {

		if ("Y".equals(request.getParameter("del"))) {
			model.addAttribute("error", "取消授权成功!");
		}
		if ("N".equals(request.getParameter("del"))) {
			model.addAttribute("error", "取消授权成功!");
		}
		if ("Y".equals(request.getParameter("add"))) {
			model.addAttribute("error", "新增授权成功!");
		}

		// 得到该病例夹
		CpfCaseFolder cpfCaseFolder = new CpfCaseFolder();
		cpfCaseFolder.setId(id);
		cpfCaseFolder = cpfCaseFolderService.findByPK(cpfCaseFolder);
		model.addAttribute("caseFolder", cpfCaseFolder);
		// 得到科室名称
		SysDepartment department = new SysDepartment();
		Map<Integer, String> departmentMap = sysDepartmentService.getDepartmentMap(department);
		model.addAttribute("departmentAllMap", departmentMap); // 科室map集合

		// 得到医院名称
		SysHospital hospital = new SysHospital();
		Map<Integer, String> hospitalMap = sysHospitalService.getHospitalMap(hospital);
		model.addAttribute("hospitalMap", hospitalMap); // 医院map集合

		SysDoctor doctor = new SysDoctor();
		Map<String, String> doctorMap = sysDoctorService.findDoctorMap(doctor);
		model.addAttribute("sysDoctorMap", doctorMap); // 医生map集合

		CpfCaseFolderAuthorization authorization = new CpfCaseFolderAuthorization();
		authorization.setFolderId(id);
		pm = cpfCaseFolderAuthorizationService.conditionFind(authorization);

		model.addAttribute("pm", pm);
		model.addAttribute("patientId", patientId);
		return "tiles.member.folder.caseFolderImpower";
	}

	/**
	 * 添加授权显示
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @param patientId
	 * @return
	 */
	@RequestMapping("/authorization/addshow/{id}/{patientId}")
	public String addAuthorizationShow(HttpServletRequest request, Model model, @PathVariable("id") Integer id, @PathVariable("patientId") Integer patientId) {
		// 得到该病例夹
		CpfCaseFolder cpfCaseFolder = new CpfCaseFolder();
		cpfCaseFolder.setId(id);
		cpfCaseFolder = cpfCaseFolderService.findByPK(cpfCaseFolder);
		model.addAttribute("caseFolder", cpfCaseFolder);
		model.addAttribute("patientId", patientId);
		model.addAttribute("experiedMap", Enums.getExperiedMap());

		List<SysHospital> hospitals = sysHospitalService.findList(new SysHospital());
		List<SysDepartment> departments = sysDepartmentService.findList(new SysDepartment());
		String doctorIds = cpfCaseFolderAuthorizationService.conditionFind(id);
		List<SysDoctor> doctors = sysDoctorService.idNotInSearch(doctorIds);
		model.addAttribute("hospitals", hospitals);
		model.addAttribute("departments", departments);
		model.addAttribute("doctors", doctors);

		return "/jsp/member/folder/authorizationAdd";

	}

	/**
	 * 添加授权
	 * 
	 * @param request
	 * @param model
	 * @param cpfCaseFolder
	 * @return
	 */
	@RequestMapping("/authorization/add")
	public String addAuthorizationr(HttpServletRequest request, Model model, CpfCaseFolderAuthorization sAuthorization) {

		CpfPatient patient = SessionUtil.getPatient(request);

		String name = patient.getUserName();

		String[] ids = request.getParameterValues("ids");
		for (String string : ids) {
			String[] id = string.split(",");
			CpfCaseFolderAuthorization c = new CpfCaseFolderAuthorization();
			c.setCreateTime(new Date());
			c.setCreator(name);
			c.setFolderId(sAuthorization.getFolderId());
			c.setPatientId(sAuthorization.getPatientId());
			c.setExperiod(sAuthorization.getExperiod());
			c.setHospitalId(Integer.parseInt(id[0]));
			c.setDepartmentId(Integer.parseInt(id[1]));
			c.setDoctor(id[2]);
			cpfCaseFolderAuthorizationService.insert(c);
			SysLogsUtil.saveLogs("病历夹管理", "病人：" + name + "增加《一条授权》成功", request);
		}

		return "redirect:/member/folder/impower/" + sAuthorization.getFolderId() + "/" + sAuthorization.getPatientId() + "?add=Y";
	}

	/**
	 * 取消授权
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @param folder
	 * @param patientId
	 * @return
	 */
	@RequestMapping("/authorization/cancel/{id}/{folder}/{patientId}")
	public String cancelAuthorization(HttpServletRequest request, Model model, @PathVariable("id") Integer id, @PathVariable("folder") Integer folder, @PathVariable("patientId") Integer patientId) {

		CpfCaseFolderAuthorization cAuthorization = new CpfCaseFolderAuthorization();
		cAuthorization.setId(id);
		cAuthorization = cpfCaseFolderAuthorizationService.findByPK(cAuthorization);

		SysDoctor doctor = new SysDoctor();
		doctor.setId(Integer.parseInt(cAuthorization.getDoctor()));
		doctor = sysDoctorService.findDoctor(doctor);
		SysUser user = SessionUtil.getUser(request);
		CpfPatient patient = SessionUtil.getPatient(request);

		String name = null;
		if (user != null) {
			name = user.getUserName();
		} else {
			name = patient.getUserName();
		}

		String type = "N";
		try {
			cpfCaseFolderAuthorizationService.delete(cAuthorization);
			SysLogsUtil.saveLogs("授权管理", "病人：" + name + "取消了对《医生" + doctor.getFullname() + "》的授权成功", request);
			type = "Y";
		} catch (Exception e) {
			SysLogsUtil.saveLogs("授权管理", "病人：" + name + "取消了对《医生" + doctor.getFullname() + "》的授权失败", request);
			e.printStackTrace();
		}
		return "redirect:/member/folder/impower/" + folder + "/" + patientId + "?del=" + type;

	}

	/**
	 * 病人病历夹方式显示
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/folderShow/list")
	public String folderShow(HttpServletRequest request, Model model, PageModel pm, CpfCaseHistory cpfHistory) {

		CpfPatient patient = SessionUtil.getPatient(request);

		if ("Y".equals(request.getParameter("deleteFolder"))) {
			model.addAttribute("error", "病例夹删除成功!");
		}
		if ("Y".equals(request.getParameter("up"))) {
			model.addAttribute("error", "病例资料修改成功!");
		}

		String patientIds = pService.freeFind(null, patient.getId());

		if (patientIds != null) {// 病人id不为空
			CpfCaseFolder cpfCaseFolder = new CpfCaseFolder();
			cpfCaseFolder.setPatientId_instr(patientIds);
			cpfCaseFolder.setStatus(Enums.caseFolderStatus.NORMAL);
			List<CpfCaseFolder> list = cpfCaseFolderService.freeFindList(cpfCaseFolder);
			model.addAttribute("list", list);
			// 就诊类别
			Map CaseTypeMap = Enums.getCaseTypeMap();
			model.addAttribute("CaseTypeMap", CaseTypeMap);

			if (null != cpfHistory.getCaseType() && !"".equals(cpfHistory.getCaseType())) {
				CpfCaseHistory cpfCaseHistory = new CpfCaseHistory();

				cpfCaseHistory.setPatientId_instr(patientIds);
				cpfCaseHistory.setFolderId(cpfHistory.getFolderId());
				cpfCaseHistory.setCaseType(cpfHistory.getCaseType());
				cpfCaseHistory.setRelateStatus(Enums.caseFolderStatus.NORMAL);
				pm = cpfCaseHistoryService.freeFind(cpfCaseHistory);
				model.addAttribute("details", cpfHistory.getCaseType());
				model.addAttribute("folderId", cpfHistory.getFolderId());
			}
			else if (null != cpfHistory.getFolderId() && !"".equals(cpfHistory.getFolderId())) {
				CpfCaseHistory cpfCaseHistory = new CpfCaseHistory();

				cpfCaseHistory.setPatientId_instr(patientIds);
				cpfCaseHistory.setFolderId(cpfHistory.getFolderId());

				cpfCaseHistory.setRelateStatus(Enums.caseFolderStatus.NORMAL);
				pm = cpfCaseHistoryService.freeFind(cpfCaseHistory);
				model.addAttribute("details", cpfHistory.getCaseType());
				model.addAttribute("folderId", cpfHistory.getFolderId());
			}
			
			else {
				CpfCaseHistory cpfCaseHistory = new CpfCaseHistory();
				cpfCaseHistory.setPatientId_instr(patientIds);
				cpfCaseHistory.setRelateStatus(Enums.caseFolderStatus.NORMAL);
				pm = cpfCaseHistoryService.freeFind(cpfCaseHistory);
			}

			model.addAttribute("pm", pm);
			model.addAttribute("patient", patient);
		}

		return "tiles.member.folderShow";
	}

	/**
	 * 删除病历夹（病历夹管理）
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/folder/delete/{id}")
	public String delete(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
		// 得到该病例夹
		CpfCaseFolder cpfCaseFolder = new CpfCaseFolder();
		cpfCaseFolder.setId(id);
		cpfCaseFolder = cpfCaseFolderService.findByPK(cpfCaseFolder);
		try {
			// 得到该病例夹下的病历资料
			CpfCaseHistory caseHistory = new CpfCaseHistory();
			caseHistory.setFolderId(cpfCaseFolder.getId());
			int count = cpfCaseHistoryService.count(caseHistory);
			if (count > 0) {
				return "redirect:/member/folder/list?isOrNo=M";
			} else {
				cpfCaseFolder.setStatus(Enums.caseFolderStatus.NO_NORMAL);
				cpfCaseFolderService.update(cpfCaseFolder);
				SysLogsUtil.saveLogs("病历夹管理", "删除病历夹成功", request);
				return "redirect:/member/folder/list?isOrNo=Y";
			}
		} catch (Exception e) {
			SysLogsUtil.saveLogs("病历夹管理", "删除病历夹失败", request);
			e.printStackTrace();
		}
		return "redirect:/member/folder/list?isOrNo=N";
	}

	/**
	 * 授权保存
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/folder/saveImpower")
	public Result saveImpower(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);
		SysUser user = SessionUtil.getUser(request);

		// 病历夹id
		String folderId = request.getParameter("folderId");
		Integer id = Integer.parseInt(folderId);

		// 病历夹id
		String selectHospitalStr = request.getParameter("selectHospital");
		Integer selectHospital = Integer.parseInt(selectHospitalStr);

		// 得到已选科室
		String selectDepartmentId = request.getParameter("selectDepartValue");
		String[] selectId = selectDepartmentId.split(",");

		// 得到已选医生
		String selectDoctorId = request.getParameter("selectDoctorValue");
		String[] selectDocId = selectDoctorId.split(",");

		// 得到病人id
		CpfPatient patient = new CpfPatient();
		patient.setUserId(user.getUserId());
		patient = cpfPatientService.findPatientByUserId(patient);

		// 权限期限
		String experiod = request.getParameter("experiod");
		Integer anchor = 0; // 是否进行授权添加
		try {
			if (null != selectId && selectId.length != 0) {
				for (int j = 0; j < selectId.length; j++) {
					if (null != selectDocId && selectDocId.length != 0) {
						for (int i = 0; i < selectDocId.length; i++) {
							String[] selDocId = selectDocId[i].split("-"); // selectDocId内存的是《科室id-医生id》
							if (Integer.parseInt(selDocId[0]) == Integer.parseInt(selectId[j])) {
								/* anchor+=1; */
								SysDoctor sysDoctor = new SysDoctor();

								System.out.println(selDocId[1]);

								sysDoctor.setId(Integer.parseInt(selDocId[1]));
								sysDoctor = sysDoctorService.findByPK(sysDoctor);
								CpfCaseFolderAuthorization cpfCaseFolderAuthorization = new CpfCaseFolderAuthorization();
								cpfCaseFolderAuthorization.setPatientId(patient.getId());
								cpfCaseFolderAuthorization.setFolderId(id);
								cpfCaseFolderAuthorization.setHospitalId(sysDoctor.getHospitalId());
								cpfCaseFolderAuthorization.setDepartmentId(sysDoctor.getDepartmentId());
								cpfCaseFolderAuthorization.setDoctor(selDocId[1]);
								CpfCaseFolderAuthorization authorization = cpfCaseFolderAuthorizationService.getCaseFolderAuthor(cpfCaseFolderAuthorization);
								if (null != authorization) { // 该病人的病历夹已经给该医生赋了权限
									if (null != experiod && !experiod.equals("")) {
										authorization.setExperiod(experiod);
									}
									Map experiedMap = Enums.getExperiedMap();
									Date newDate = new Date();
									GregorianCalendar cal = new GregorianCalendar();
									cal.setTime(authorization.getCreateTime());
									cal.add(Calendar.MONTH, Integer.parseInt(experiedMap.get(authorization.getExperiod()).toString())); // 到期时间
									Date date = cal.getTime();
									if (date.getTime() <= newDate.getTime()) { // 有授权单已过期
										authorization.setModifyTime(new Date());
										authorization.setModifier(patient.getId().toString());
										cpfCaseFolderAuthorizationService.insert(authorization);
										anchor += 1;
									}
								} else {// 该病人的病历夹没有给该医生赋了权限
									if (null != experiod && !experiod.equals("")) {
										cpfCaseFolderAuthorization.setExperiod(experiod);
									}
									cpfCaseFolderAuthorization.setCreateTime(new Date());
									cpfCaseFolderAuthorization.setCreator(patient.getId().toString());
									cpfCaseFolderAuthorizationService.insert(cpfCaseFolderAuthorization);
									anchor += 1;
								}
							}
						}

					}

				}
			}
			if (anchor != 0) {
				SysLogsUtil.saveLogs("病历夹管理", "病人：" + patient.getUserName() + "成功修改病历夹", request);
				result.setSuccess(true);
				result.setDataValue("message", "授权成功");
			} else {
				result.setDataValue("message", "请选择要授权的医生");
			}
		} catch (Exception e) {
			result.setDataValue("message", "授权失败");
			SysLogsUtil.saveLogs("病历夹管理", "病人：" + patient.getUserName() + "修改病历夹失败", request);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 复选框方式删除病历夹
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/folder/deleteFolderBox")
	public Result deleteFolder(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);

		String selectFolderValue = request.getParameter("selectFolderValue");

		String[] selectFolder = null;
		if (null != selectFolderValue && !selectFolderValue.equals(",")) {
			selectFolder = selectFolderValue.split(",");
		}

		// 得到该病例夹下的病历资料
		CpfCaseHistory caseHistory = new CpfCaseHistory();
		caseHistory.setFolderId_instr(selectFolderValue);
		List<CpfCaseHistory> caseList = cpfCaseHistoryService.freeFindLsit(caseHistory);
		if (null != caseList && caseList.size() > 0) {
			result.setDataValue("msg", "该病历夹下有病历资料，请先删除病历资料!");
			result.setSuccess(false);
			return result;
		}

		if (null != selectFolder && selectFolder.length != 0) {
			for (int i = 0; i < selectFolder.length; i++) {
				Integer folderId = Integer.parseInt(selectFolder[i]);
				// 得到该病例夹
				CpfCaseFolder cpfCaseFolder = new CpfCaseFolder();
				cpfCaseFolder.setId(folderId);
				cpfCaseFolder = cpfCaseFolderService.findByPK(cpfCaseFolder);

				cpfCaseFolder.setStatus(Enums.caseFolderStatus.NO_NORMAL);

				try {
					cpfCaseFolderService.update(cpfCaseFolder);
					SysLogsUtil.saveLogs("病历夹管理", "删除病历夹成功", request);
					result.setSuccess(true);
				} catch (Exception e) {
					result.setSuccess(false);
					result.setDataValue("msg", "删除病历夹失败");
					SysLogsUtil.saveLogs("病历夹管理", "删除病历夹失败", request);
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	// 病历资料删除
	@RequestMapping("/deleteHistory/{caseHistoryId}")
	public String deleteHistory(HttpServletRequest request, Model model, @PathVariable("caseHistoryId") Integer caseHistoryId) {
		CpfCaseHistory caseHistory = new CpfCaseHistory();
		caseHistory.setId(caseHistoryId);
		caseHistory.setViewStatus(Enums.caseFolderStatus.NO_NORMAL);
		try {
			cpfCaseHistoryService.update(caseHistory);
			SysLogsUtil.saveLogs("病历夹方式查询", "删除病历资料失败", request);
			return "redirect:/member/folderShow/list?deleteHistory=Y";
		} catch (Exception e) {
			SysLogsUtil.saveLogs("病历夹方式查询", "删除病历资料失败", request);
			e.printStackTrace();
		}
		return "redirect:/member/folderShow/list?deleteHistory=N";
	}
}
