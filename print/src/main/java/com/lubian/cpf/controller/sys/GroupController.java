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
import com.lubian.cpf.service.sys.UserGroupService;
import com.lubian.cpf.vo.SysUserGroup;


@NeedLogin
@NeedAdminPrivilege
@Controller
@RequestMapping("/admin/group")
public class GroupController {

	private Logger logger = Logger.getLogger(GroupController.class);
	@Autowired
	private UserGroupService groupService;

	/**
	 * @param request
	 * @param model
	 * @param pm
	 * @return
	 */
	@RequestMapping("/list")
	public String getGroupList(HttpServletRequest request, Model model, PageModel pm) {

		SysUserGroup group = new SysUserGroup();
		String groupName = request.getParameter("groupName");
		model.addAttribute("groupName", groupName);
		if (!"".equals(groupName) && null != groupName) {
			group.setGroupName(groupName);
		}
		pm = groupService.getGroupList(group);
		if (pm != null) {
			model.addAttribute("pm", pm);
		}
		List<SysUserGroup> list = groupService.getAllGroupList();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (SysUserGroup info : list) {
			map.put(info.getGroupId(), info.getGroupName());
		}
		model.addAttribute("groupMap", map);
		return "tiles.admin.groupList";
	}

	//@RequestMapping("saveEditGroup")
	public String saveEditGroup(HttpServletRequest request, Model model, SysUserGroup group) {
		try {
			groupService.update(group);
			SysLogsUtil.saveLogs("组管理", "更新组信息:"+group.getGroupName(), request);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/group/list";
	}

	@ResponseBody
	@RequestMapping("/saveEdit")
	public Result saveEditGroupNotReturn(HttpServletRequest request, Model model, SysUserGroup group) {

		Result result = Result.createResult().setSuccess(false);
		try {
			this.saveEditGroup(request, model, group);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("error", e.getMessage());
			return result;
		}
		result.setSuccess(true);
		return result;
	}

	@RequestMapping("/add")
	public String addGroup(HttpServletRequest request, Model model) {
		List<SysUserGroup> list = groupService.getAllGroupList();
		model.addAttribute("list", list);
		return "tiles.admin.groupAdd";
	}

	@RequestMapping("/saveAdd")
	public String saveInsertGroup(HttpServletRequest request, Model model, SysUserGroup group) {
		try {
			group.setCreateBy(SessionUtil.getUser(request).getUserName());
			group.setCreateDt(new Date());
			groupService.insert(group);
			SysLogsUtil.saveLogs("组管理", "新增组信息:"+group.getGroupName(), request);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/group/edit/" + group.getGroupId() + "?add=Y";
	}

	@RequestMapping("/edit/{groupId}")
	public String findGroup(HttpServletRequest request, Model model, @PathVariable("groupId") Integer groupId) {

		if ("Y".equals(request.getParameter("add"))) {
			model.addAttribute("error", "新增组保存成功");
		}
		SysUserGroup group = new SysUserGroup();
		group.setGroupId(groupId);
		group = groupService.findByPK(group);
		if (group != null) {
			model.addAttribute("group", group);
		}

		List<SysUserGroup> list = groupService.getAllGroupList();
		model.addAttribute("parentIdlist", list);
		return "tiles.admin.groupEdit";
	}

	@RequestMapping("/delete/{groupId}")
	public String deleteGroup(HttpServletRequest request, Model model, @PathVariable("groupId") Integer groupId) {

		try {
			SysUserGroup group = new SysUserGroup();
			group.setGroupId(groupId);
			groupService.delete(group);
			SysLogsUtil.saveLogs("组管理", "删除组信息:"+group.getGroupName(), request);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/group/list";
	}
}
