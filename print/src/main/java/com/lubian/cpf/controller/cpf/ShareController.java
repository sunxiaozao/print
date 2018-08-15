package com.lubian.cpf.controller.cpf;

import java.util.Date;
import java.util.LinkedHashMap;

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
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.sms.SMSSendService;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfPatientRelationUserService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.cpf.CpfShareService;
import com.lubian.cpf.service.sys.SysMailJobService;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfShare;
import com.lubian.cpf.vo.SysMailJob;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/member")
public class ShareController {
	private Logger log = Logger.getLogger(ShareController.class);

	@Autowired
	private CpfShareService cpfShareService;// 分享

	@Autowired
	private SysMailJobService mailJobService;

	@Autowired
	private CpfPatientService patientService;

	@Autowired
	private SMSSendService sMSSendService;// 发送信息

	@Autowired
	private CpfCaseHistoryService cpfCaseHistoryService;// 病历资料

	@Autowired
	private CpfPatientRelationUserService pService;

	/**
	 * 查看分享详情
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/share/detail/{caseHistoryId}")
	public String detail(HttpServletRequest request, Model model, @PathVariable("caseHistoryId") Integer caseHistoryId) {

		CpfShare vo = new CpfShare();
		vo.setCaseHistoryId(caseHistoryId);
		PageModel pm = cpfShareService.freeFind(vo);
		model.addAttribute("pm", pm);

		model.addAttribute("caseHistoryId", caseHistoryId);
		model.addAttribute("typeMap", Enums.shareTypeMap());
		model.addAttribute("viewStatuMap", Enums.viewStatuMap());
		// 病人集合
		model.addAttribute("patientMap", patientService.findPatientMap(new CpfPatient()));
		// 共分享次数
		CpfShare countAllShare = new CpfShare();
		countAllShare.setCaseHistoryId(caseHistoryId);
		model.addAttribute("countAllShare", cpfShareService.freeCount(countAllShare));
		// 第一次分享时间
		CpfShare firstShare = new CpfShare();
		firstShare = cpfShareService.findByOrder(countAllShare);
		Date createTime = null;
		if (null != firstShare) {
			createTime = firstShare.getCreateTime();
		}
		model.addAttribute("createTime", createTime);
		// 病历夹名称
		CpfCaseHistory cch = new CpfCaseHistory();
		cch.setId(caseHistoryId);
		cch = cpfCaseHistoryService.findByPK(cch);
		model.addAttribute("caseName", cch.getCaseName());
		// 已被查看次数
		/*
		 * CpfShare countShareByStatu=new CpfShare(); countShareByStatu.setCaseHistoryId(caseHistoryId); countShareByStatu.setStatus(Enums.isYesOrIsNo.IS_YES);
		 */
		countAllShare.setStatus(Enums.isYesOrIsNo.IS_YES);
		model.addAttribute("countShareByStatu", cpfShareService.freeCount(countAllShare));

		// return "/jsp/member/share/detail";
		return "tiles.member.share.detail";
	}

	/**
	 * 病人 我的分享
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/share/list")
	public String subtotal(HttpServletRequest request, Model model, CpfShare vo) {

		if ("Y".equals(request.getParameter("share"))) {
			model.addAttribute("error", "您已将病历分享了给了 用户：" + request.getParameter("email") + request.getParameter("mobile"));
			vo.setEmail(null);
			vo.setMobile(null);
		}
		if ("Y".equals(request.getParameter("delete"))) {
			model.addAttribute("error", "删除成功!");
		}
		if ("Y".equals(request.getParameter("cancel"))) {
			model.addAttribute("error", "取消成功!");
		}

		CpfPatient patient = SessionUtil.getPatient(request);
		// 查询条件为空时，置空该条件
		if (StringUtils.isBlank(vo.getEmail())) {
			vo.setEmail(null);
		}
		if (StringUtils.isBlank(vo.getMobile())) {
			vo.setMobile(null);
		}
		String patientIds = pService.freeFind(null, patient.getId());

		if (patientIds != null) {// 病人id不为空
			vo.setPatientId_instr(patientIds);
			PageModel pm = cpfShareService.freeFind(vo);
			model.addAttribute("pm", pm);
		}

		model.addAttribute("typeMap", Enums.shareTypeMap());
		model.addAttribute("viewStatuMap", Enums.viewStatuMap());

		return "tiles.member.share.list";
	}

	/**
	 * 调转到分享页面
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/share/{caseHistoryId}")
	public String toShare(HttpServletRequest request, Model model, @PathVariable("caseHistoryId") Integer caseHistoryId) {
		CpfShare vo = new CpfShare();
		vo.setCaseHistoryId(caseHistoryId);
		vo = cpfShareService.findByCommand(vo);

		model.addAttribute("cpfShare", vo);
		model.addAttribute("caseHistoryId", caseHistoryId);
		String viewPass = StringUtils.getRandomSixNUM() + "";
		viewPass = viewPass.length() == 6 ? viewPass : viewPass + 0;
		// 查看密码


		model.addAttribute("experiedMap", Enums.getExperiedMap());
		model.addAttribute("viewPass", viewPass);
		// 病历资料路径
		model.addAttribute("viewUrl", "http://www.yunjiaopian.com.cn//" + StringUtils.getRandomSixString() + ".html");

		return "/jsp/member/share/share";
		// return "tiles.member.share";
	}

	/**
	 * 分享
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/saveShare")
	@ResponseBody
	public Result savaShare(HttpServletRequest request, Model model, CpfShare vo) {
		Result result = Result.createResult().setSuccess(false);
		StringBuffer content = new StringBuffer();// 发送内容
		try {
			CpfPatient patient = SessionUtil.getPatient(request);
			// 填充分享表
			CpfCaseHistory caseHistory = new CpfCaseHistory();
			caseHistory.setId(vo.getCaseHistoryId());
			caseHistory = cpfCaseHistoryService.findByPK(caseHistory);
			vo.setCreator(patient.getId());
			vo.setCreateTime(new Date());
			vo.setPatientId(caseHistory.getPatientId());
			// vo.setPatientId(user.getUserId());
			// vo.setCaseHistoryId(caseHistoryId);
			vo.setStatus(Enums.isYesOrIsNo.IS_NO);// 查看状态
			cpfShareService.insert(vo);
			
			if (!StringUtils.isBlank(vo.getEmail())) {
				
				// 拼接发送内容
				content.append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;" + vo.getContent()+"<br/>");
				content.append("病人" + patient.getUserName() + "分享了一份病历资料给您，查看地址为");
				//http://www.yunjiaopian.com.cn/member/share/noLogin/caseHistoryId/shareId/
				content.append("<a href='" + Constant.ADDRESS + "/" + vo.getCaseHistoryId() + "/" + vo.getId() + "/" + StringUtils.md5(vo.getPassword()) + "' " + ">" + vo.getUrl() + "</a>" + "，查看密码为" + vo.getPassword());
				// 向邮件表插入一条记录，后台计划会定时检索邮件表顺序发送邮件
				SysMailJob mailJob = new SysMailJob();
				mailJob.setToEmailStr(vo.getEmail());// 只接受逗号分隔的多个email地址，此处注意修改
				mailJob.setSubject("夏宇科技微医通");
				mailJob.setContent(content.toString());
				mailJob.setMailType(Enums.MailType.SEND_NOW);// 立即发送(最长延迟一分钟)
				mailJob.setMailStatus(Enums.MailJobStatus.INIT);// 初始化邮件状态
				// 邮件模板，customize则没有模板直接发送content内容,可以在WEB-INF/ftl中新增邮件模板，
				// 然后在MailService中针对模板进行业务处理参数设置
				mailJob.setBisType(Enums.MailBizType.SHARE);
				mailJobService.insert(mailJob);
			}
			String mobile = vo.getMobile();
			if (!StringUtils.isBlank(mobile)) {
				
				// 发送短信
				StringBuffer mobileContent = new StringBuffer();
				mobileContent.append("【厦门新夏宇科技有限公司】");
				mobileContent.append("病人" + patient.getUserName() + "分享了一份病历资料给您，并留言：“"+vo.getContent()+"”,查看地址为");
				mobileContent.append(Constant.ADDRESS + "/" + vo.getCaseHistoryId() + "/" + vo.getId() + "/" + StringUtils.md5(vo.getPassword()));
				mobileContent.append("，查看密码为" + vo.getPassword());
				sMSSendService.sendSMS(patient.getId(), mobile, mobileContent.toString());
			}
			
			Integer caseHistoryId = 0;
			if (null != vo) {
				caseHistoryId = vo.getCaseHistoryId();
			}

			// 更新病历资料表
			CpfCaseHistory cch = new CpfCaseHistory();
			cch.setId(caseHistoryId);
			cch.setModifier(patient.getUserName());
			cch.setModifyTime(new Date());
			Integer shareCount = 0;
			if (null != cch.getShareCount()) {
				shareCount = cch.getShareCount();
			}
			cch.setShareCount(shareCount + 1);
			cch.setShareStatus(Enums.isYesOrIsNo.IS_YES);
			cpfCaseHistoryService.update(cch);
			
			model.addAttribute("userName", patient.getUserName());
			model.addAttribute("url", vo.getUrl());
			model.addAttribute("pass", vo.getPassword());

			SysLogsUtil.saveLogs("分享", "用户" + patient.getUserName() + "分享了病历资料", request);

			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
			result.setDataValue("msg", e.getMessage());
		}
		// return "redirect:/member/share/list?share=Y&email="+emails;
		return result;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @param posId
	 * @return
	 */
	@RequestMapping("/deleteShare/{id}")
	public String deleteShare(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
		SysUser user = new SysUser();
		try {
			CpfShare vo = new CpfShare();
			vo.setId(id);
			cpfShareService.delete(vo);
			SysLogsUtil.saveLogs("分享", "用户" + user.getUserName() + "删除了" + id + "病历资料", request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/member/share/list?delete=Y";
	}

	/**
	 * 取消分享
	 * 
	 * @param request
	 * @param model
	 * @param posId
	 * @return
	 */
	@RequestMapping("/cancelShare/{id}")
	public String cancelShare(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
		SysUser user = new SysUser();
		try {
			CpfShare vo = new CpfShare();
			vo.setId(id);
			// cpfShareService.delete(vo);
			vo.setStatus(Enums.isYesOrIsNo.IS_NO);
			cpfShareService.update(vo);
			SysLogsUtil.saveLogs("分享", "用户" + user.getUserName() + "取消了" + id + "病历资料分享", request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/member/share/list?cancel=Y";
	}
}
