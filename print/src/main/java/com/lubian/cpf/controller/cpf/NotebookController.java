package com.lubian.cpf.controller.cpf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;
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

import com.lowagie.tools.encrypt_pdf;
import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.Upload;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.controller.AdminController;
import com.lubian.cpf.service.cpf.CdfNotebookService;
import com.lubian.cpf.service.cpf.CpfCaseFolderService;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfPatientRelationUserService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.cpf.CpfRelationService;
import com.lubian.cpf.service.cpf.CpfReplyService;
import com.lubian.cpf.service.sys.SysDepartmentService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.vo.CdfNotebook;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfReply;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/notebook")
public class NotebookController {
	private Logger log = Logger.getLogger(AdminController.class);

	@Autowired
	private SysDoctorService doctorService;

	@Autowired
	private CdfNotebookService notebookService;

	@Autowired
	private CpfReplyService replyService;

	@Autowired
	private CpfPatientService patientService;
	@Autowired
	private CpfPatientRelationUserService pService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping("/doctor/list/{caseHistoryId}/{patientId}")
	public String showDoctor(HttpServletRequest request, Model model,
			@PathVariable("caseHistoryId") Integer caseHistoryId,
			@PathVariable("patientId") Integer patientId) {

		SysUser user = SessionUtil.getUser(request);
		String userType = user.getUserType();
		// 得到病人信息
		Integer doctorId = 0;
		SysDoctor sysDoctor = doctorService
				.findDoctorByUserId(user.getUserId());// 获得医生信息
		if (null != sysDoctor) {
			doctorId = sysDoctor.getId();
		}

		CdfNotebook notebook = new CdfNotebook();
		notebook.setCaseHistoryId(caseHistoryId);

		// 病历资料为-1时，查询该医生所以的留言（分类汇总中留言汇总）
		if (-1 == caseHistoryId) {
			String status = request.getParameter("status");
			notebook.setStatus(status);
			notebook.setCaseHistoryId(null);
			notebook.setPatientId(null);
			if (userType.equals(Enums.UserType.DOCTOR)) {
				notebook.setDoctorId(doctorId);
			} else if (userType.equals(Enums.UserType.SUPER_DOCTOR)) {
				String ids = doctorService.doctorIdsByHospital(sysDoctor
						.getId());
				notebook.setDoctorId_instr(ids);
			}

		}

		Map map = notebookService.find(notebook);
		List<CpfReply> replies = null;
		if (map != null) {
			List<CdfNotebook> notebooks = (List<CdfNotebook>) map
					.get("notebooks");
			model.addAttribute("notebooks", notebooks);
			String notebookIds = (String) map.get("ids");
			replies = replyService.find(notebookIds);
		}
		model.addAttribute("caseHistoryId", caseHistoryId);
		model.addAttribute("patientId", patientId);
		model.addAttribute("replies", replies);
		return "/jsp/doctor/notebook/notebook";
	}

	@RequestMapping("/patient/list/{caseHistoryId}")
	public String showPatient(HttpServletRequest request, Model model,
			@PathVariable("caseHistoryId") Integer caseHistoryId) {

		CdfNotebook notebook = new CdfNotebook();
		notebook.setCaseHistoryId(caseHistoryId);
		Map map = notebookService.find(notebook);
		List<CpfReply> replies = null;
		if (map != null) {
			List<CdfNotebook> notebooks = (List<CdfNotebook>) map
					.get("notebooks");
			model.addAttribute("notebooks", notebooks);
			String notebookIds = (String) map.get("ids");
			replies = replyService.find(notebookIds);
		}
		model.addAttribute("caseHistoryId", caseHistoryId);
		model.addAttribute("replies", replies);
		return "/jsp/member/notebook/notebook";
	}

	/**
	 * 病人统计留言信息
	 * 
	 * @param request
	 * @param model
	 * @param viewStatu
	 * @return
	 */
	@RequestMapping("/member/subtotal/{viewStatu}")
	public String showNotebook(HttpServletRequest request, Model model,
			@PathVariable("viewStatu") String viewStatu) {
		
		CpfPatient patient = SessionUtil.getPatient(request);
		Map map = null;
		String patientIds =  pService.freeFind(null, patient.getId());
	

		if (patientIds != null) {
			map = notebookService.find(null, patientIds, viewStatu);
		}

		List<CpfReply> replies = null;
		if (map != null) {
			List<CdfNotebook> notebooks = (List<CdfNotebook>) map
					.get("notebooks");
			model.addAttribute("notebooks", notebooks);
			String notebookIds = (String) map.get("ids");
			replies = replyService.find(notebookIds);
		}
		model.addAttribute("replies", replies);
		return "/jsp/member/notebook/notebook";
	}

	@RequestMapping("/add/{caseHistoryId}/{patientId}")
	public String add(HttpServletRequest request, CdfNotebook notebook,
			@PathVariable("caseHistoryId") Integer caseHistoryId,
			@PathVariable("patientId") Integer patientId) {
		SysDoctor doctor = new SysDoctor();
		doctor.setUserId(SessionUtil.getUser(request).getUserId());
		doctor = doctorService.findDoctor(doctor);
		notebook.setCreateTime(new Date());// 留言时间
		notebook.setDoctorId(doctor.getId());
		// notebook.setStatus(Enums.isYesOrIsNo.IS_YES);
		notebook.setCaseHistoryId(caseHistoryId);
		notebook.setPatientId(patientId);
		notebook.setReplyCount(0);
		notebookService.insert(notebook);
		return "redirect:/notebook/doctor/list/" + caseHistoryId + "/"
				+ patientId;
	}

	@RequestMapping("/addreply/{notebookId}/{doctorId}/{caseHistoryId}")
	public String addReply(HttpServletRequest request, CpfReply reply,
			@PathVariable("notebookId") Integer notebookId,
			@PathVariable("doctorId") Integer doctorId,
			@PathVariable("caseHistoryId") Integer caseHistoryId) {

		CdfNotebook notebook=new CdfNotebook();
		notebook.setId(notebookId);
		notebook=notebookService.findByPK(notebook);
		reply.setReplyTime(new Date());// 留言时间
		reply.setDoctorId(doctorId);
		reply.setStatus(Enums.isYesOrIsNo.IS_YES);
		reply.setNotebookId(notebookId);
		reply.setPatientId(notebook.getPatientId());
		reply.setReplyCount(0);
		replyService.insert(reply);
		return "redirect:/notebook/patient/list/" + caseHistoryId;
	}

}
