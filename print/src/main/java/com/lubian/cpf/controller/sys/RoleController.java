package com.lubian.cpf.controller.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.sys.FunctionCrudService;
import com.lubian.cpf.service.sys.FunctionService;
import com.lubian.cpf.service.sys.RelRoleFuncService;
import com.lubian.cpf.service.sys.RoleService;
import com.lubian.cpf.vo.SysFunction;
import com.lubian.cpf.vo.SysFunctionCrud;
import com.lubian.cpf.vo.SysRelRoleFunc;
import com.lubian.cpf.vo.SysRole;


@NeedLogin
@NeedAdminPrivilege
@Controller
@RequestMapping("/admin/role")
public class RoleController {

	private Logger logger = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private RelRoleFuncService relRoleFuncService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private FunctionCrudService functionCrudService;

	@RequestMapping("/add")
	public String addRole(HttpServletRequest request, Model model) {
		List<SysFunction> function = functionService.getFuctionList(new SysFunction());
		model.addAttribute("function", function);
		
		this.setFunctionList(model);
		return "tiles.admin.roleAdd";
	}

	@RequestMapping("/saveAdd")
	public String saveInsertRole(HttpServletRequest request, Model model) {

		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		SysRole role = new SysRole();
		role.setRoleName(roleName);
		role.setRoleDesc(roleDesc);
		role.setCreateDt(new Date());
		roleService.insert(role);
		// role = roleService.findPK(role);
		String[] functionCrudRelId = request.getParameterValues("functionCrudRelId");
		if (null != functionCrudRelId) {
			SysRelRoleFunc relRoleFunc = new SysRelRoleFunc();
			relRoleFunc.setRoleId(role.getRoleId());
			// 插入所选的值权限值
			for (int i = 0; i < functionCrudRelId.length; i++) {
				// 从functionCrud中根据主键查询url
				SysFunctionCrud functionCrud = new SysFunctionCrud();
				functionCrud.setRelId(Integer.valueOf(functionCrudRelId[i]));
				functionCrud = functionCrudService.findByPK(functionCrud);
				relRoleFunc.setRelId(Integer.valueOf(functionCrudRelId[i]));
				relRoleFunc.setFunctionCrudUrl(functionCrud.getUrl());
				relRoleFuncService.insert(relRoleFunc);
			}
		}
		SysLogsUtil.saveLogs("角色管理", "新增角色:"+roleName, request);
		return "redirect:/admin/role/edit/" + role.getRoleId() + "?add=Y";
	}

	@RequestMapping("/list")
	public String getRoleList(HttpServletRequest request, Model model, PageModel pm) {

		SysRole role = new SysRole();
		String roleName = request.getParameter("roleName");
		model.addAttribute("roleName", roleName);
		if (!"".equals(roleName) && null != roleName) {
			role.setRoleName(roleName);
		}
		pm = roleService.getRoleList(role);
		model.addAttribute("pm", pm);
		return "tiles.admin.roleList";
	}

	@RequestMapping("/delete/{roleId}")
	public String deleteRole(HttpServletRequest request, @PathVariable("roleId") Integer roleId) {
		SysRole role = new SysRole();
		role.setRoleId(roleId);
		roleService.delete(role);
		SysLogsUtil.saveLogs("角色管理", "删除角色:"+role.getRoleName(), request);
		return "redirect:/admin/role/list";
	}

	@ResponseBody   
	@RequestMapping("/saveEdit")
	public Result saveEditRoleNotReturn(HttpServletRequest request, Model model) {

		Result result = Result.createResult().setSuccess(false);
		try {
			this.saveEditRole(request, model);
		} catch (Exception e) {
			logger.error(e);
			model.addAttribute("error", e.getMessage());
			return result;
		}
		result.setSuccess(true);
		return result;
	}

	//@RequestMapping("saveEditRole")
	public String saveEditRole(HttpServletRequest request, Model model) {

		Integer roleId = Integer.valueOf(request.getParameter("roleId"));
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		SysRole role = new SysRole();
		role.setRoleId(roleId);
		role.setRoleName(roleName);
		role.setRoleDesc(roleDesc);
		role.setModifyDt(new Date());
		roleService.update(role);// 更新该角色的基本信息
		String[] functionCrudRelId = request.getParameterValues("functionCrudRelId");
		if (null != functionCrudRelId) {
			// 若选择权限不为空，查出所有该角色的权限信息
			SysRelRoleFunc relRoleFunc = new SysRelRoleFunc();
			relRoleFunc.setRoleId(roleId);
			// 先删除数据库中所有的
			relRoleFuncService.deleteByRoleId(roleId);
			// 再插入所选的值权限值
			for (int i = 0; i < functionCrudRelId.length; i++) {
				SysFunctionCrud functionCrud = new SysFunctionCrud();
				functionCrud.setRelId(Integer.valueOf(functionCrudRelId[i]));
				functionCrud = functionCrudService.findByPK(functionCrud);
				relRoleFunc.setFunctionCrudUrl(functionCrud.getUrl());
				relRoleFunc.setRelId(functionCrud.getRelId());
				relRoleFuncService.insert(relRoleFunc);
			}
		}
		SysLogsUtil.saveLogs("角色管理", "更新角色:"+role.getRoleName(), request);
		return "redirect:/admin/role/list";
	}

	// 查询某一角色信息
	@RequestMapping("/edit/{roleId}")
	public String findRole(HttpServletRequest request, Model model, @PathVariable("roleId") Integer roleId) {
		String add = request.getParameter("add");
		if ("Y".equals(add)) {
			model.addAttribute("error", "新增角色保存成功");
		}
		SysRole role = new SysRole();
		role.setRoleId(roleId);
		role = roleService.findByPK(role);
		model.addAttribute("role", role);

		HashMap map = new HashMap();
		for (SysRelRoleFunc rel : (ArrayList<SysRelRoleFunc>) role.getRelRoleFuncList()) {
			map.put(rel.getRelId(), rel);
		}
		model.addAttribute("relRoleFuncMap", map);

		List<SysFunction> function = functionService.getFuctionList(new SysFunction());
		model.addAttribute("function", function);
		
		this.setFunctionList(model);
		return "tiles.admin.roleEdit";
	}

	// 某一角色详细信息
	@RequestMapping("/view/{roleId}")
	public String viewRole(HttpServletRequest request, Model model, @PathVariable("roleId") Integer roleId) {
		SysRole role = new SysRole();
		role.setRoleId(roleId);
		role = roleService.findByPK(role);
		model.addAttribute("role", role);

		HashMap map = new HashMap();
		for (SysRelRoleFunc rel : (ArrayList<SysRelRoleFunc>) role.getRelRoleFuncList()) {
			map.put(rel.getRelId(), rel);
		}
		model.addAttribute("relRoleFuncMap", map);

		List<SysFunction> function = functionService.getFuctionList(new SysFunction());
		model.addAttribute("function", function);
		
		this.setFunctionList(model);
		return "tiles.admin.roleView";
		
	}
	
	private void setFunctionList(Model model){
		SysFunction func = new SysFunction();
		//权限管理
		func.setCategoryId(11);
		List<SysFunction> priveledgeList = functionService.getFuctionList(func);
		model.addAttribute("priveledgeList", priveledgeList);
		//系统管理
		func.setCategoryId(12);
		List<SysFunction> sysList = functionService.getFuctionList(func);
		model.addAttribute("sysList", sysList);
		//结算业务
		func.setCategoryId(13);
		List<SysFunction> settleList = functionService.getFuctionList(func);
		model.addAttribute("settleList", settleList);
		//银行数据
		func.setCategoryId(14);
		List<SysFunction> bankList = functionService.getFuctionList(func);
		model.addAttribute("bankList", bankList);
		//接口管理
		func.setCategoryId(15);
		List<SysFunction> intefaceList = functionService.getFuctionList(func);
		model.addAttribute("intefaceList", intefaceList);
		//报表管理
		func.setCategoryId(16);
		List<SysFunction> reportList = functionService.getFuctionList(func);
		model.addAttribute("reportList", reportList);
		//商户系统
		func.setCategoryId(17);
		List<SysFunction> agencyList = functionService.getFuctionList(func);
		model.addAttribute("agencyList", agencyList);
	}
}
