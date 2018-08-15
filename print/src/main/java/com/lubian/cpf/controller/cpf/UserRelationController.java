package com.lubian.cpf.controller.cpf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.lubian.cpf.vo.CpfPatientRelationUser;
import com.lubian.cpf.vo.CpfRelation;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/member")
public class UserRelationController {
	private Logger log = Logger.getLogger(UserRelationController.class);

	@Autowired
	private CpfPatientRelationUserService reService;
	@Autowired
	private CpfPatientService patientService;

	/**
	 * 查询关联的对象
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/relationPatient")
	public String relationPatient(HttpServletRequest request, Model model) {
		CpfPatient patient = SessionUtil.getPatient(request);
		List<CpfPatientRelationUser> list = new ArrayList<CpfPatientRelationUser>();
		CpfPatientRelationUser pru = new CpfPatientRelationUser();
	
			pru.setPatientId(patient.getId());
			model.addAttribute("id", patient.getId());
		
		list = reService.freeFindList(pru);
		model.addAttribute("list", list);
		
		return "/jsp/member/info/relation";
	}

	/**
	 * 关联病人
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/relationUser")
	public Result relationUser(HttpServletRequest request) {
		Result result = new Result().setSuccess(false);
		String cardNo = request.getParameter("cardNo");
		String password = request.getParameter("password");
		CpfPatient patient = new CpfPatient();
		patient = patientService.find(cardNo, password);// 查询病人
		if (patient != null) {// 如果病人存在
			SysUser user = SessionUtil.getUser(request);
			CpfPatient pCpfPatient = SessionUtil.getPatient(request);
			CpfPatientRelationUser pru = new CpfPatientRelationUser();
			pru.setRelationPatientId(patient.getId());

			if (user != null) {// 如果是微医通用户登录
				pru.setUserId(user.getUserId());

			} else {
				if (!pCpfPatient.getId().equals(patient.getId())) {// 是否关联的是自己
					pru.setPatientId(pCpfPatient.getId());// 医院登录
				} else {
					result.setError("关联失败!用户不能自己关联自己!");
					return result;
				}

			}

			if (reService.count(pru)) {// 如果没有关联过,进行关联
				pru.setCreateDt(new Date());
				reService.insert(pru);
				result.setSuccess(true);
				
				
				
				if(""==patient.getIcId()||null==patient.getIcId()){
					patient.setIcId("");
				}
				if(""==patient.getFullname()||null==patient.getFullname()){
					patient.setFullname("");
				}
				
				
				
				
				result.setDataValue("patient", patient);
				result.setDataValue("rId", pru.getId());
			} else {// 如果已经关联过提示已经关联
				result.setError("关联失败!该卡号已经关联!请勿重复关联!");
			}

		} else {// 如果不存在提示关联失败
			result.setError("关联失败!卡号或密码错误!");
		}
		return result;
	}

	/**
	 * 删除关联
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/relationDel/{id}")
	public Result relationDel(HttpServletRequest request,
			@PathVariable("id") Integer id) {
		Result result = new Result().setSuccess(false);
		CpfPatientRelationUser pru = new CpfPatientRelationUser();
		pru.setId(id);
		try {
			reService.delete(pru);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
