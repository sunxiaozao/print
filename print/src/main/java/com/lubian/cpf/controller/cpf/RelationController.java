package com.lubian.cpf.controller.cpf;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.lubian.cpf.common.util.CookieUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.AdminService;
import com.lubian.cpf.service.cpf.CpfPatientRelationUserService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.cpf.CpfRelationService;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfRelation;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/member")
public class RelationController {
	private Logger log = Logger.getLogger(RelationController.class);

	@Autowired
	private CpfRelationService cpfRelationService;// 关注

	@Autowired
	private CpfPatientService cpfPatientService;// 病人表

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;
	@Autowired
	private CpfPatientRelationUserService pService;

	/**
	 * 模拟登陆
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param user
	 * @return
	 */
	/*
	 * @RequestMapping("/simulate/{mark}")
	 * 
	 * @ResponseBody public Result simulate(HttpServletRequest request,
	 * HttpServletResponse response, Model model, SysUser
	 * user,@PathVariable("mark") Integer mark) {
	 * 
	 * HttpSession session = request.getSession();
	 * 
	 * Result result = Result.createResult().setSuccess(false);
	 * 
	 * SysUser userSelf = SessionUtil.getUser(request);
	 * 
	 * String userName=request.getParameter("userName"); String
	 * password=request.getParameter("password");
	 * 
	 * // 执行验证 user = adminService.adminLogin(userName, password);
	 * 
	 * if (user == null) { result.setError("用户名或密码错误!"); return result; }
	 * 
	 * if (1==mark) {//切换到被关注者 SessionUtil.setUser(request, user);
	 * userService.updateLastLogin(user.getUserId()); result.setSuccess(true);
	 * result.setDataValue("user", user); session.setAttribute("userSelfName",
	 * userSelf.getUserName()); session.setAttribute("userSelfPass",
	 * userSelf.getPassword()); }else if (0==mark) {//切换回登录人
	 * SessionUtil.setUser(request, userSelf);
	 * userService.updateLastLogin(userSelf.getUserId());
	 * result.setSuccess(true); result.setDataValue("user", userSelf); }
	 * 
	 * session.setAttribute("simulateMark", mark);
	 * 
	 * 
	 * // 取当前用户菜单 Map menuMap = adminService.getMenus(user);
	 * request.getSession().setAttribute(Constant.USER_MENUS, menuMap);
	 * 
	 * // 设置菜单选中(选中主页) request.setAttribute(Constant.SEL_MENU_CAT, 0);
	 * request.setAttribute(Constant.SEL_MENU_ITEM, 0);
	 * CookieUtil.addCookie(response, Constant.COOKIE_MENU_CODE, "0_0");
	 * 
	 * SysLogsUtil.saveLogs("用户登录", "用户" + user.getUserName() + "登录了后台系统",
	 * request); return result; }
	 */
	/**
	 * 模拟登陆 切换回登录人
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/simulate/0")
	@ResponseBody
	public Result simulate(HttpServletRequest request,
			HttpServletResponse response, Model model, SysUser user) {
		HttpSession session = request.getSession();
		Result result = Result.createResult().setSuccess(false);

		// 自己的登录信息
		CpfPatient userSelf = (CpfPatient) session.getAttribute("userSelf");

		if (userSelf == null) {
			result.setError("用户名或密码错误!");
			return result;
		} else {
			SessionUtil.setPatient(request, userSelf);
			result.setSuccess(true);
			result.setDataValue("user", null);
		}

		// session.removeAttribute("simulateMark");
		session.setAttribute("simulateMark", 0);

		// 取当前用户菜单
		Map menuMap = adminService.getMenus(user);
		request.getSession().setAttribute(Constant.USER_MENUS, menuMap);

		// 设置菜单选中(选中主页)
		request.setAttribute(Constant.SEL_MENU_CAT, 0);
		request.setAttribute(Constant.SEL_MENU_ITEM, 0);
		CookieUtil.addCookie(response, Constant.COOKIE_MENU_CODE, "0_0");

		SysLogsUtil.saveLogs("用户登录", "用户" + userSelf.getUserName() + "登录了后台系统",
				request);
		return result;
	}

	/**
	 * 模拟登陆 切换到被关注者
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/simulate/1")
	@ResponseBody
	public Result simulateOfOther(HttpServletRequest request,
			HttpServletResponse response, Model model, SysUser user) {
		HttpSession session = request.getSession();
		Result result = Result.createResult().setSuccess(false);

		// 自己的登录信息
		CpfPatient userSelf = SessionUtil.getPatient(request);
		session.setAttribute("userSelf", userSelf);

		// 被关注人的name和password
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		CpfPatient patient = adminService.patientLogin(userName,
				password);
		if (patient == null) {
			result.setError("用户名或密码错误!");
			return result;
		} else {
			SessionUtil.setPatient(request, patient);
			result.setSuccess(true);
			result.setDataValue("user", null);
		}
		

		result.setSuccess(true);

		session.setAttribute("simulateMark", 1);

		// 取当前用户菜单
		Map menuMap = adminService.getMenus(user);
		request.getSession().setAttribute(Constant.USER_MENUS, menuMap);

		// 设置菜单选中(选中主页)
		request.setAttribute(Constant.SEL_MENU_CAT, 0);
		request.setAttribute(Constant.SEL_MENU_ITEM, 0);
		CookieUtil.addCookie(response, Constant.COOKIE_MENU_CODE, "0_0");

		SysLogsUtil.saveLogs("用户登录", "用户" + userSelf.getUserName() + "登录了"
				+ user.getUserName() + "的账号", request);
		return result;
	}

	/**
	 * 病人 我的关注
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/relation/list")
	public String relation(HttpServletRequest request, Model model) {
		if ("Y".equals(request.getParameter("add"))) {
			model.addAttribute("error", "关注成功!");
		}
		if ("Y".equals(request.getParameter("cancel"))) {
			model.addAttribute("error", "取消成功!");
		}
		if ("Y".equals(request.getParameter("pass"))) {
			model.addAttribute("error", "已通过!");
		}
		CpfPatient patient = SessionUtil.getPatient(request);
		String patientIds = pService.freeFind(null, patient.getId());

		if (patientIds != null) {// 病人id不为空
			// 我的关注
			CpfRelation vo = new CpfRelation();
			vo.setPatientId_instr(patientIds);
			// vo.setStatus(Enums.relationStatus.REGULAR);//状态为 正常A
			PageModel pm = cpfRelationService.freeFind(vo);
			model.addAttribute("pm", pm);

			// 关注我的
			CpfRelation otherVo = new CpfRelation();
			// otherVo.setStatus(Enums.relationStatus.PASS);//状态为已关注
			otherVo.setRelatePatientId(patient.getId());
			PageModel otherPm = cpfRelationService.freeFind(otherVo);
			model.addAttribute("otherPm", otherPm);

			model.addAttribute("relationStatuMap", Enums.relationStatuMap());
		}

		return "tiles.member.relation.list";
	}

	/**
	 * 通过关注请求
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/relation/savePass/{selectValue}")
	public String savaPass(HttpServletRequest request, Model model,
			@PathVariable("selectValue") String selectValue) {
		try {
			CpfPatient patient=SessionUtil.getPatient(request);

			// 被选中的需要通过的关注请求
			// String passSelectValues=request.getParameter("passSelectVals");
			String[] selectArray = null;
			if (selectValue != null && !"".equals(selectValue)) {
				selectArray = selectValue.split(",");
			}
			String password = patient.getPassword();
			cpfRelationService.updRelationByIds(selectArray, password);

			SysLogsUtil.saveLogs("关注", "用户" + patient.getUserName() + "通过了"
					+ "条请求", request);

		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/member/relation/list?pass=Y";
	}

	/**
	 * 调转到添加关注页面
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/relation/add")
	public String toRelation(HttpServletRequest request, Model model) {

		CpfRelation vo = new CpfRelation();
		vo = cpfRelationService.findByPK(vo);

		model.addAttribute("relationUserMap", Enums.userTypeMap());
		model.addAttribute("userName", request.getParameter("userName"));
		model.addAttribute("typeVal", request.getParameter("typeVal"));
		model.addAttribute("applyReason", request.getParameter("applyReason"));

		// return "/jsp/member/relation/relationAdd";
		return "tiles.member.relation.add";
	}

	/**
	 * 保存新增关注
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/relation/saveAdd")
	@ResponseBody
	public Result saveAdd(HttpServletRequest request, Model model,
			CpfRelation vo) {
		Result result = Result.createResult().setSuccess(false);
		try {
			CpfPatient patient = SessionUtil.getPatient(request);
			
			vo.setPatientId(patient.getId());
			vo.setApplyTime(new Date());
			vo.setStatus(Enums.relationStatus.CONFIG);// 查看状态为待确认
			vo.setPassword("");// 密码为空

			cpfRelationService.insert(vo);

			SysLogsUtil.saveLogs(
					"关注",
					"用户" + patient.getUserName() + "关注了病人"
							+ vo.getRelatePatientId(), request);

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
	 * 关注
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/saveRelation")
	@ResponseBody
	public Result savaRelation(HttpServletRequest request, Model model,
			CpfRelation vo) {
		Result result = Result.createResult().setSuccess(false);
		try {
			SysUser user = SessionUtil.getUser(request);

			vo.setPatientId(user.getUserId());
			// vo.setCaseHistoryId(caseHistoryId);
			vo.setStatus(Enums.isYesOrIsNo.IS_YES);// 查看状态

			cpfRelationService.insert(vo);

			SysLogsUtil.saveLogs(
					"关注",
					"用户" + user.getUserName() + "关注了病人"
							+ vo.getRelatePatientId(), request);

			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e);
			// model.addAttribute("error", e.getMessage());
			// result.setDataValue("msg", e.getMessage());
		}
		// return "redirect:/member/share/list?share=Y&email="+emails;
		return result;
	}

	/**
	 * 取消
	 * 
	 * @param request
	 * @param model
	 * @param posId
	 * @return
	 */
	@RequestMapping("/cancelRelation/{id}")
	public String cancelRelation(HttpServletRequest request, Model model,
			@PathVariable("id") Integer id) {
		SysUser user = SessionUtil.getUser(request);
		try {
			CpfRelation vo = new CpfRelation();
			vo.setId(id);
			cpfRelationService.delete(vo);
			SysLogsUtil.saveLogs("关注", "用户" + user.getUserName() + "取消了对" + id
					+ "病人的关注", request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/member/relation/list?cancel=Y";
	}

	@RequestMapping("/checkRelation")
	@ResponseBody
	public Result checkRelation(HttpServletRequest request, Model model) {
		Result result = Result.createResult().setSuccess(false);
		
		CpfPatient patient=SessionUtil.getPatient(request);
		try {
			String data = request.getParameter("userName").toString().trim();
			String type = request.getParameter("typeVal").toString().trim();
			// 被关注的人的id
			Integer relatePatientId = cpfPatientService.checkUserReturnId(data,
					type);
			CpfRelation vo=new CpfRelation();
			vo.setPatientId(relatePatientId);
			if (-1==relatePatientId) {
				result.setDataValue("msg", "此用户不存在！");
			}else if (cpfRelationService.findByCommand(vo)) {
				result.setDataValue("msg", "您已经关注过此用户！");
			}else if (data.equals(patient.getUserName()) 
					|| data.equals(patient.getEmail()) 
					|| data.equals(patient.getMobile())
					|| data.equals(patient.getIcId())) {
				result.setDataValue("msg", "抱歉，您不能关注自己！");
			}else{
				result.setDataValue("msg", "此用户可以被关注！");
			}


			result.setDataValue("relatePatientId", relatePatientId);

			result.setSuccess(true);

		} catch (Exception e) {
			log.error(e);
		}

		return result;
	}

}
