package com.lubian.cpf.controller.cpf;

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
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.controller.cfg.ParaController;
import com.lubian.cpf.service.cfg.DictService;
import com.lubian.cpf.service.sys.SysDepartmentService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.vo.CfgDict;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysHospital;

@NeedLogin
@Controller
@NeedAdminPrivilege
@RequestMapping("/admin/department")
public class DepartmentController {

	private Logger log = Logger.getLogger(ParaController.class);

	@Autowired
	private DictService dictService;

	@Autowired
	private SysHospitalService hospitalService;

	@Autowired
	private SysDepartmentService departmentService;

	@RequestMapping("/list")
	public String getDictList(HttpServletRequest request, PageModel pm,
			Model model, SysDepartment department) {

		if (StringUtils.isBlank(department.getDepartmentName())) {
			department.setDepartmentName(null);
		}

		pm = departmentService.freeFind(department);
		model.addAttribute("pm", pm);
		CfgDict dict = new CfgDict();
		dict.setType(Enums.DictType.DEPARTMENT_TYPE);
		List<CfgDict> list = dictService.getAllCfgDict(dict);
		Map<String, String> mp = new HashMap<String, String>();
		for (CfgDict object : list) {
			mp.put(object.getShortCode(), object.getLongName());
		}
		SysHospital hostpital = new SysHospital();
		hostpital.setId(department.getHospitalId());
		hostpital = hospitalService.findByPK(hostpital);
		model.addAttribute("hostpital", hostpital);
		model.addAttribute("department", department);
		model.addAttribute("mp", mp);
		model.addAttribute("dicts", list);
		return "tiles.department.departmentList";
	}

	@RequestMapping("/delete/{departmentId}/{hospitalId}")
	public String deleteDict(HttpServletRequest request, Model model,
			@PathVariable("departmentId") Integer departmentId,
			@PathVariable("hospitalId") Integer hospitalId) {

		try {

			SysDepartment department = new SysDepartment();
			department = new SysDepartment();
			department.setId(departmentId);
			departmentService.delete(department);
			SysLogsUtil.saveLogs(
					"科室管理",
					"删除科室信息:" + department.getDepartmentName()
							+ department.getDescription(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/department/list?hospitalId=" + hospitalId;
	}

	@RequestMapping("/edit/{departmentId}")
	public String editDict(HttpServletRequest request, Model model,
			@PathVariable("departmentId") Integer departmentId) {
		String addFlag = request.getParameter("add");
		if ("Y".equals(addFlag)) {
			model.addAttribute("error", "数据添加成功");
		}

		SysDepartment department = new SysDepartment();
		department.setId(departmentId);
		SysHospital hospital = new SysHospital();
		try {
			department = departmentService.findByPK(department);
			hospital.setId(department.getHospitalId());
			hospital = hospitalService.findByPK(hospital);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("hospital", hospital);
		model.addAttribute("department", department);

		CfgDict dict = new CfgDict();
		dict.setType(Enums.DictType.DEPARTMENT_TYPE);
		List list = dictService.getAllCfgDict(dict);
		model.addAttribute("dicts", list);

		return "tiles.department.departmentEdit";
	}

	@ResponseBody
	@RequestMapping("/saveEdit")
	public Result saveEditDict(HttpServletRequest request, Model model,
			SysDepartment department) {
		Result result = Result.createResult().setSuccess(false);
		try {
			department.setCreator(SessionUtil.getUser(request).getUserName());
			department.setCreateDate(new Date());
			departmentService.update(department);
			result.setSuccess(true);
			SysLogsUtil.saveLogs(
					"科室管理",
					"修改科室信息:" + department.getDepartmentName()
							+ department.getDescription(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return result;
	}

	@RequestMapping("/add/{hospitalId}")
	public String addDict(HttpServletRequest request, Model model,
			@PathVariable("hospitalId") Integer hospitalId) {
		CfgDict dict = new CfgDict();
		dict.setType(Enums.DictType.DEPARTMENT_TYPE);
		List list = dictService.getAllCfgDict(dict);
		model.addAttribute("dicts", list);

		SysHospital hostpital = new SysHospital();
		hostpital.setId(hospitalId);
		hostpital = hospitalService.findByPK(hostpital);
		model.addAttribute("hostpital", hostpital);
		return "tiles.department.departmentAdd";
	}

	@RequestMapping("/saveAdd")
	public String saveInsertDict(HttpServletRequest request, Model model,
			SysDepartment department) {
		try {

			department.setCreator(SessionUtil.getUser(request).getUserName());
			department.setCreateDate(new Date());

			departmentService.insert(department);

			SysLogsUtil.saveLogs(
					"科室管理",
					"新增科室信息:" + department.getDepartmentName()
							+ department.getDescription(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/department/edit/" + department.getId()
				+ "?add=Y";
	}

}