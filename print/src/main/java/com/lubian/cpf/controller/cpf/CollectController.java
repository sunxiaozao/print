package com.lubian.cpf.controller.cpf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedDoctorLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.cpf.CdfBookmarkService;
import com.lubian.cpf.service.cpf.CdfFavoriteService;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.vo.CdfBookmark;
import com.lubian.cpf.vo.CdfFavorite;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysUser;

@NeedDoctorLogin
@Controller
@RequestMapping("/doctor")
public class CollectController {
	private Logger log = Logger.getLogger(CollectController.class);

	@Autowired
	private CdfFavoriteService cdfFavoriteService;// 分享

	@Autowired
	private CpfPatientService cpfPatientService;// 病人表

	@Autowired
	private SysDoctorService sysDoctorService;// 医生表

	@Autowired
	private CpfCaseHistoryService caseHistoryService;

	@Autowired
	private CdfBookmarkService bookmarkService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 医生 我的收藏
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/collect/list")
	public String collect(HttpServletRequest request, Model model, CdfFavorite vo, CpfCaseHistory caseHistory) {
		SysUser user = SessionUtil.getUser(request);
		/*
		 * if(user==null || !user.getUserType().equals(Enums.UserType.DOCTOR)){ return "redirect:/admin/login"; }
		 */
		if ("Y".equals(request.getParameter("add"))) {
			model.addAttribute("error", "收藏成功！ ");
		}
		if ("Y".equals(request.getParameter("delete"))) {
			model.addAttribute("error", "删除成功!");
		}
		// CpfCaseHistory caseHistory=new CpfCaseHistory();
		// CdfFavorite vo=new CdfFavorite();
		// 得到医生信息
		SysDoctor sysDoctor = sysDoctorService.findDoctorByUserId(user.getUserId());// 获得医生信息

		CdfBookmark bookmark = new CdfBookmark();
		bookmark.setDoctorId(sysDoctor.getId());
		List<CdfBookmark> bookmarks = bookmarkService.freeFindList(bookmark);
		model.addAttribute("bookmarks", bookmarks);
		// 得到该医生的收藏记录
		/*
		 * String ids = cdfFavoriteService.idsByDocotrId(sysDoctor.getId()); if (ids != null) { caseHistory.setDoctorId_instr(ids); }else{ caseHistory.setDoctorId_instr("-1"); }
		 */
		String ids = cdfFavoriteService.caseIdByDocotrId(sysDoctor.getId(), vo.getBookmarkId());
		if (ids != null) {
			// caseHistory.setDoctorId_instr(ids);
			// caseHistory.setId(52);
			caseHistory.setId_instr(ids);
		} else {
			caseHistory.setId_instr("-1");
		}
		// 查询条件为空时，置空该条件
		String patientName = request.getParameter("patientName");
		String sign = request.getParameter("sign");
		Integer patientId = 0;

		if (null != sign && sign.equals("ne")) {// 搜索
			if (!StringUtils.isBlank(patientName)) {
				String patientIds = cpfPatientService.checkUserReturnId(patientName);// 类型为默认
				// caseHistory.setPatientId(null);
				caseHistory.setPatientId_instr(patientIds);
				if (patientIds == null) {
					caseHistory.setPatientId(patientId);
				}
			}
			caseHistory.setCaseType(null);
			caseHistory.setItem(null);
			caseHistory.setTechnician(null);
		} else {// 高级搜索
				// 病人id
			if (!StringUtils.isBlank(patientName)) {
				patientId = cpfPatientService.checkUserReturnId(patientName, "");// 类型为默认
				if (patientId != null) {
					caseHistory.setPatientId(patientId);

				}
			}
		}

		if (vo.getFavoriteTimeTo() != null) {
			caseHistory.setCaseDateTo(vo.getFavoriteTimeTo());
		}
		if (vo.getFavoriteTimeFrom() != null) {
			caseHistory.setCaseDateFrom(vo.getFavoriteTimeFrom());
		}
		if (StringUtils.isBlank(caseHistory.getCaseType())) {
			caseHistory.setCaseType(null);
		}
		if (StringUtils.isBlank(caseHistory.getItem())) {
			caseHistory.setItem(null);
		}
		if (StringUtils.isBlank(caseHistory.getTechnician())) {
			caseHistory.setTechnician(null);
		}

		PageModel pm = caseHistoryService.freeFind(caseHistory);
		model.addAttribute("pm", pm);

		model.addAttribute("caseTypeMap", Enums.getCaseTypeMap());// 获取病例资料类型
		model.addAttribute("patientName", patientName);

		model.addAttribute("searchCollect", vo);
		model.addAttribute("searchcaseHistory", caseHistory);

		return "tiles.doctor.collect.list";
	}

	@RequestMapping("/collect/add/{caseId}/{cassType}")
	public String add(HttpServletRequest request, Model model, @PathVariable("caseId") Integer caseId, @PathVariable("cassType") String cassType) {
		SysUser user = SessionUtil.getUser(request);

		SysDoctor sysDoctor = sysDoctorService.findDoctorByUserId(user.getUserId());// 获得医生信息

		CdfBookmark bookmark = new CdfBookmark();
		bookmark.setDoctorId(sysDoctor.getId());
		List<CdfBookmark> bookmarks = bookmarkService.freeFindList(bookmark);
		model.addAttribute("bookmarks", bookmarks);
		model.addAttribute("caseId", caseId);
		model.addAttribute("type", cassType);
		return "/jsp/doctor/collect/save";
	}

	/**
	 * 收藏
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/saveCollect")
	public String savaCollect(HttpServletRequest request, Model model,CdfFavorite vo) {
		Result result = Result.createResult().setSuccess(false);
		try {

			// 得到医生信息
			SysDoctor sysDoctor = new SysDoctor();
			sysDoctor.setUserId(SessionUtil.getUser(request).getUserId());
			sysDoctor = sysDoctorService.findDoctor(sysDoctor);// 获得医生信息

			
			SysUser user = SessionUtil.getUser(request);

			vo.setFavoriteTime(new Date());
			vo.setDoctorId(sysDoctor.getId());
			vo.setReason("理由");
			cdfFavoriteService.insert(vo);

			// 更新病历资料表
			CpfCaseHistory cch = new CpfCaseHistory();
			cch.setId(vo.getId());
			cch.setModifier(user.getUserName());
			cch.setModifyTime(new Date());
			Integer favoriteCount = 0;
			if (null != cch.getFavoriteCount()) {
				favoriteCount = cch.getFavoriteCount();
			}
			cch.setFavoriteCount(favoriteCount + 1);
			cch.setFavoriteStatus(Enums.isYesOrIsNo.IS_YES);
			caseHistoryService.update(cch);

			result.setSuccess(true);

			SysLogsUtil.saveLogs("收藏", "用户" + user.getUserName() + "收藏了" + vo.getCaseId() + "病历资料", request);

		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		
		return "redirect:/doctor/collect/list?add=Y";
	}

	/**
	 * 查看
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/collect/detail/{id}")
	public String detail(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
		try {
			CdfFavorite vo = new CdfFavorite();
			vo.setId(id);
			cdfFavoriteService.findByPK(vo);

			model.addAttribute("favorite", vo);

		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "/jsp/doctor/collect/detail";
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @param posId
	 * @return
	 */
	@RequestMapping("/deleteCollect/{id}")
	public String deleteCollect(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
		SysUser user = new SysUser();
		try {
			CdfFavorite vo = new CdfFavorite();
			vo.setCaseId(id);
			cdfFavoriteService.deleteCollect(vo);

			// 更新病历资料表
			CpfCaseHistory cch = new CpfCaseHistory();
			cch.setId(id);
			cch.setModifier(user.getUserName());
			cch.setModifyTime(new Date());
			Integer favoriteCount = 0;
			if (null != cch.getFavoriteCount()) {
				favoriteCount = cch.getFavoriteCount();
			}
			cch.setFavoriteCount(favoriteCount - 1);
			cch.setFavoriteStatus(Enums.isYesOrIsNo.IS_NO);
			caseHistoryService.update(cch);

			SysLogsUtil.saveLogs("收藏", "用户" + user.getUserName() + "删除了" + id + "收藏", request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/doctor/collect/list?delete=Y";
	}

}
