package com.lubian.cpf.controller.cpf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.cfg.DictService;
import com.lubian.cpf.service.cpf.CdfNotebookService;
import com.lubian.cpf.service.cpf.CpfCaseFolderService;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfPatientRelationUserService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.cpf.CpfShareService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.vo.CfgDict;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfShare;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
public class SubtotalController {
	private Logger log = Logger.getLogger(SubtotalController.class);

	@Autowired
	private CdfNotebookService cdfNotebookService;// 留言

	@Autowired
	private CpfCaseHistoryService cpfCaseHistoryService;// 病历资料表

	@Autowired
	private CpfPatientService cpfPatientService;// 病人表

	@Autowired
	private CpfCaseFolderService cpfCaseFolderService;// 病历夹

	@Autowired
	private CpfShareService cpfShareService;// 分享

	@Autowired
	private SysHospitalService sysHospitalService;// 医院

	@Autowired
	private SysDoctorService sysDoctorService; // 医生表

	@Autowired
	private CpfPatientService patientService;
	@Autowired
	private DictService dictService;
	@Autowired
	private CpfPatientRelationUserService pService;

	/**
	 * 汇总查看 就诊医院(病人)
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/member/subtotal/hospitals")
	public String hospitals(HttpServletRequest request, Model model) {
		CpfPatient patient = SessionUtil.getPatient(request);

		List<CpfPatient> pList = null;

		String patientIds = pService.freeFind(null, patient.getId());

		if (patientIds != null) {// 病人id不为空
			CpfPatient p = new CpfPatient();
			p.setId_instr(patientIds);
			pList = cpfPatientService.findList(p);

		}

		CfgDict dict = new CfgDict();
		dict.setType(Enums.DictType.HOSPITAL_TYPE);
		List<CfgDict> list = dictService.getAllCfgDict(dict);
		Map<String, String> mp = new HashMap<String, String>();
		for (CfgDict object : list) {
			mp.put(object.getShortCode(), object.getLongName());
		}
		model.addAttribute("mp", mp);
		model.addAttribute("caseCount", pList);
		return "/jsp/member/subtotal/hospital";
	}

	/**
	 * 汇总查看 病历夹(病人)
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/member/subtotal/folders/{viewStatu}")
	public String folders(HttpServletRequest request, Model model, @PathVariable("viewStatu") String viewStatu) {
		CpfPatient patient = SessionUtil.getPatient(request);

		List<CpfCaseFolder> folderCount = null;
		String patientIds = pService.freeFind(null, patient.getId());

		if (patientIds != null) {// 病人id不为空
			folderCount = cpfCaseFolderService.subtotalOfFolderList(null, patientIds, viewStatu);
		}

		model.addAttribute("folderCount", folderCount);

		return "/jsp/member/subtotal/folder";
	}

	/**
	 * 汇总查看 病人详情(医生)
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/doctor/subtotal/patients")
	public String patients(HttpServletRequest request, Model model) {
		SysUser user = SessionUtil.getUser(request);
		// 医生合计病人数量
		List<CpfPatient> patientCount = cpfPatientService.subtotalOfPatient(user);

		model.addAttribute("patientCount", patientCount);

		return "/jsp/doctor/subtotal/patient";
	}

	/**
	 * 汇总查看 分享详情(医生)
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/doctor/subtotal/share")
	public String shares(HttpServletRequest request, Model model, CpfShare vo) {
		SysUser user = SessionUtil.getUser(request);

		String userType = user.getUserType();
		SysDoctor doctor = new SysDoctor();
		

		doctor=sysDoctorService.findDoctorByUserId(user.getUserId());
		
		if ("".equals(user.getEmail()) || "" == user.getEmail()) {
			doctor.setEmail(null);
		}else{
			doctor.setMobile(user.getMobile());
		}
		if ("".equals(user.getMobile()) || "" == user.getMobile()) {
			doctor.setMobile(null);
		}else{
			
			doctor.setEmail(user.getEmail());
		}

		String type=Enums.isYesOrIsNo.IS_NO.equals(vo.getStatus())?Enums.isYesOrIsNo.IS_NO:null;
		
		PageModel pm = cpfShareService.findtShare( doctor,type);

		model.addAttribute("pm", pm);
		model.addAttribute("typeMap", Enums.shareTypeMap());
		model.addAttribute("viewStatuMap", Enums.viewStatuMap());

		return "tiles.doctor.share.list";
	}

	/**
	 * 汇总查看 留言详情
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	/*
	 * @RequestMapping("/doctor/subtotal/notebooks/{viewStatu}") public String notebooks(HttpServletRequest request, Model model,
	 * 
	 * @PathVariable("viewStatu") String viewStatu ) { SysUser user = SessionUtil.getUser(request);
	 * 
	 * Map map = new HashMap(); CdfNotebook vo=new CdfNotebook(); //判断用户是病人还是医生 if (user!=null) { if(user.getUserType().equals(Enums.UserType.MEMBER)){ CpfPatient patient = (CpfPatient) request.getSession().getAttribute(Constant.PATIENT_SESSION); vo.setPatientId(patient.getId()); }else if(user.getUserType().equals(Enums.UserType.DOCTOR)){//普通医生 SysDoctor sysDoctor = new SysDoctor(); sysDoctor.setUserId(SessionUtil.getUser(request).getUserId()); sysDoctor = sysDoctorService.findDoctor(sysDoctor);// 获得医生信息 vo.setDoctorId(sysDoctor.getId()); }else if(user.getUserType().equals(Enums.UserType.SUPER_DOCTOR)){//超级医生 //登入者为超级医生，置空查询条件 vo.setPatientId(null); vo.setDoctorId(null); } }
	 * 
	 * // 最新 是否查看 if (viewStatu.equals(Enums.isYesOrIsNo.IS_NO)) {// 未读 vo.setStatus(Enums.isYesOrIsNo.IS_NO); } else if (viewStatu.equals(Enums.isYesOrIsNo.IS_YES)) {// 共计 vo.setStatus(null); }
	 * 
	 * map=cdfNotebookService.subtotalOfMap(vo);
	 * 
	 * List<CpfReply> replies = null; if (map != null) { List<CdfNotebook> notebooks = (List<CdfNotebook>) map.get("notebooks"); model.addAttribute("notebooks", notebooks); String notebookIds = (String) map.get("ids"); replies = replyService.find(notebookIds); } model.addAttribute("replies", replies);
	 * 
	 * return "/jsp/doctor/subtotal/notebook"; }
	 */
	/**
	 * 医生 分类汇总
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/doctor/subtotal")
	public String subtotal(HttpServletRequest request, Model model, PageModel pm) {
		try {
			SysUser user = SessionUtil.getUser(request);
			String userType = user.getUserType();
			SysDoctor doctor = new SysDoctor();
			

			doctor=sysDoctorService.findDoctorByUserId(user.getUserId());
			
			if ("".equals(user.getEmail()) || "" == user.getEmail()) {
				doctor.setEmail(null);
			}else{
				doctor.setMobile(user.getMobile());
			}
			if ("".equals(user.getMobile()) || "" == user.getMobile()) {
				doctor.setMobile(null);
			}else{
				
				doctor.setEmail(user.getEmail());
			}
	
			
			// 医生合计报告数量
			int reportCount = cpfCaseHistoryService.subtotalByDoctor(doctor, Enums.CaseType.REQUISITION);
			// 医生合计化验单数量
			int assayCount = cpfCaseHistoryService.subtotalByDoctor(doctor, Enums.CaseType.TEST_REPORT);
			// 医生合计胶片数量
			int pictureCount = cpfCaseHistoryService.subtotalByDoctor(doctor, Enums.CaseType.FILM);
			// 医生合计申请单数量
			int applyCount = cpfCaseHistoryService.subtotalByDoctor(doctor, Enums.CaseType.DIAGNOSIS);

			model.addAttribute("reportCount", reportCount);
			model.addAttribute("assayCount", assayCount);
			model.addAttribute("pictureCount", pictureCount);
			model.addAttribute("applyCount", applyCount);

			// 医生合计病人数量
			List<CpfPatient> patientCount = cpfPatientService.subtotalOfPatient(user);
			model.addAttribute("patientCount", patientCount.size());

			SysDoctor sysDoctor = new SysDoctor();
			sysDoctor.setUserId(user.getUserId());
			sysDoctor = sysDoctorService.findDoctorByUserId(user.getUserId());
			Integer doctorId = 0;
			if (null != sysDoctor) {
				doctorId = sysDoctor.getId();
			}

			// 医生最新留言数量
			Integer notebookCountNewest = cdfNotebookService.subtotal(doctorId, Enums.isYesOrIsNo.IS_NO, userType);
			model.addAttribute("notebookCountNewest", notebookCountNewest);
			// 医生合计留言数量
			Integer notebookCount = cdfNotebookService.subtotal(doctorId, Enums.isYesOrIsNo.IS_YES, userType);
			model.addAttribute("notebookCount", notebookCount);

			// 病人最新分享数量
			Integer shareCountNewest = cpfShareService.countShare(doctor, Enums.isYesOrIsNo.IS_NO);
			model.addAttribute("shareCountNewest", shareCountNewest);
			// 病人合计分享数量
			Integer shareCount = cpfShareService.countShare(doctor, null);
			model.addAttribute("shareCount", shareCount);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "tiles.doctor.subtotal";
	}

	@RequestMapping("/doctor/subtotal/list/{type}")
	public String subtotalList(HttpServletRequest request, Model model, PageModel pm,@PathVariable("type")String type){
		SysUser user = SessionUtil.getUser(request);
		SysDoctor doctor = new SysDoctor();
		doctor=sysDoctorService.findDoctorByUserId(user.getUserId());
		if ("".equals(user.getEmail()) || "" == user.getEmail()) {
			doctor.setEmail(null);
		}else{
			doctor.setMobile(user.getMobile());
		}
		if ("".equals(user.getMobile()) || "" == user.getMobile()) {
			doctor.setMobile(null);
		}else{
			
			doctor.setEmail(user.getEmail());
		}
		String ids=cpfCaseHistoryService.subtotalByid(doctor, type);
		if(ids!=null&&ids.length()>0){
			CpfCaseHistory caseHistory=new CpfCaseHistory();
			caseHistory.setId_instr(ids);
			caseHistory.setCaseType(type);
			pm = cpfCaseHistoryService.freeFind(caseHistory);
		}
		
		model.addAttribute("pm", pm);
		model.addAttribute("type", type);
		
		
		return "tiles.doctor.subtotal.list";
	}
	
}
