package com.lubian.cpf.controller.sys;

import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedAdminPrivilege;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.sys.UserOrgService;
import com.lubian.cpf.vo.SysOrg;


@NeedLogin
@NeedAdminPrivilege
@Controller
@RequestMapping("/admin/org")
public class OrgController {

	private Logger logger = Logger.getLogger(OrgController.class);
	@Autowired
	private UserOrgService orgService;

	/**
	 * 显示所有的组织机构信息
	 * 
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/list")
	public String getOrgList(HttpServletRequest request, Model model, PageModel pm) {

		SysOrg org = new SysOrg();
		// 获取模糊查询组织机构名称的条件
		String orgName = request.getParameter("orgName");
		model.addAttribute("orgName", orgName);
		if (!"".equals(orgName) && null != orgName) {
			org.setOrgName(orgName);
		}
		// 调用服务层分页的方法,需要传入SysOrg类的对象
		pm = orgService.getOrgList(org);
		if (pm != null) {
			model.addAttribute("pm", pm);
		}

		List<SysOrg> list = orgService.getAllOrgList();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (SysOrg info : list) {
			map.put(info.getOrgId(), info.getOrgName());
		}
		model.addAttribute("orgMap", map);
		return "tiles.admin.orgList";
	}

	//@RequestMapping("saveEditOrg")
	public String saveEditOrg(HttpServletRequest request, Model model, SysOrg org) {
		try {
			orgService.update(org);
			SysLogsUtil.saveLogs("组织机构管理", "更新组织结构信息:"+org.getOrgName(), request);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/org/list";
	}

	@ResponseBody
	@RequestMapping("/saveEdit")
	public Result saveEditOrgNotReturn(HttpServletRequest request, Model model, SysOrg org) {

		Result result = Result.createResult().setSuccess(false);
		try {
			this.saveEditOrg(request, model, org);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("error", e.getMessage());
			return result;
		}
		result.setSuccess(true);
		return result;
	}

	@RequestMapping("/add")
	public String addOrg(HttpServletRequest request, Model model) {

		List<SysOrg> list = orgService.getAllOrgList();
		model.addAttribute("list", list);
		return "tiles.admin.orgAdd";
	}

	@RequestMapping("/saveAdd")
	public String saveInsertOrg(HttpServletRequest request, Model model, SysOrg org) {
		try {
			org.setCreateBy(SessionUtil.getUser(request).getUserName());
			org.setCreateDt(new Date());
			orgService.insert(org);
			SysLogsUtil.saveLogs("组织机构管理", "新增组织结构:"+org.getOrgName(), request);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/org/edit/" + org.getOrgId() + "?add=Y";
	}

	// 根据编号查询单一的组织机构信息
	@RequestMapping("/edit/{orgId}")
	public String findOrg(HttpServletRequest request, Model model, @PathVariable("orgId") Integer orgId) {

		if ("Y".equals(request.getParameter("add"))) {
			model.addAttribute("error", "新增组织机构保存成功");
		}
		SysOrg org = new SysOrg();
		org.setOrgId(orgId);
		org = orgService.findByPK(org);
		if (org != null) {
			model.addAttribute("org", org);
		}

		List<SysOrg> list = orgService.getAllOrgList();
		model.addAttribute("parentIdlist", list);
		return "tiles.admin.orgEdit";
	}

	@RequestMapping("/delete/{orgId}")
	public String deleteOrg(HttpServletRequest request, Model model, @PathVariable("orgId") Integer orgId) {
		try {
			SysOrg org = new SysOrg();
			org.setOrgId(orgId);
			orgService.delete(org);
			SysLogsUtil.saveLogs("组织机构管理", "删除组织结构:"+org.getOrgName(), request);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/org/list";
	}
}
