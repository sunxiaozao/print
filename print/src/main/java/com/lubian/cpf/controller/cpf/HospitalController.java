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
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.vo.CfgDict;
import com.lubian.cpf.vo.SysHospital;

@NeedLogin
@Controller
@NeedAdminPrivilege
@RequestMapping("/admin/hospital")
public class HospitalController {

	private Logger log = Logger.getLogger(ParaController.class);
	@Autowired
	private DictService dictService;

	@Autowired
	private SysHospitalService hospitalService;

	@RequestMapping("/list")
	public String getDictList(HttpServletRequest request, PageModel pm, Model model, SysHospital hostpital) {

		if (StringUtils.isBlank(hostpital.getName())) {
			hostpital.setName(null);
		}
		if (StringUtils.isBlank(hostpital.getHospitalId())) {
			hostpital.setHospitalId(null);
		}

		pm = hospitalService.freeFind(hostpital);
		model.addAttribute("pm", pm);
		CfgDict dict = new CfgDict();
		dict.setType(Enums.DictType.HOSPITAL_TYPE);
		List<CfgDict> list = dictService.getAllCfgDict(dict);
		Map<String, String> mp = new HashMap<String, String>();
		for (CfgDict object : list) {
			mp.put(object.getShortCode(), object.getLongName());
		}
		model.addAttribute("hostpital", hostpital);
		model.addAttribute("mp", mp);
		model.addAttribute("dicts", list);
		return "tiles.hospital.hospitalList";
	}

	@RequestMapping("/delete/{hospitalId}")
	public String deleteDict(HttpServletRequest request, Model model, @PathVariable("hospitalId") Integer hospitalId) {
		try {
			SysHospital hostpital = new SysHospital();
			hostpital.setId(hospitalId);

			hospitalService.delete(hostpital);
			SysLogsUtil.saveLogs("医院管理", "删除医院信息:" + hostpital.getName() + hostpital.getDescription(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/hospital/list";
	}

	@RequestMapping("/edit/{hospitalId}")
	public String editDict(HttpServletRequest request, Model model, @PathVariable("hospitalId") Integer hospitalId) {
		String addFlag = request.getParameter("add");
		if ("Y".equals(addFlag)) {
			model.addAttribute("error", "数据添加成功");
		}

		SysHospital hospital = new SysHospital();
		hospital.setId(hospitalId);
		try {
			hospital = hospitalService.findByPK(hospital);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("hospital", hospital);
		SysHospital h = new SysHospital();
		Map map = hospitalService.getHospitalMap(h);
		model.addAttribute("map", map);
		CfgDict dict = new CfgDict();
		dict.setType(Enums.DictType.HOSPITAL_TYPE);
		List list = dictService.getAllCfgDict(dict);
		model.addAttribute("dicts", list);

		return "tiles.hospital.hospitalEdit";
	}

	@ResponseBody
	@RequestMapping("/saveEdit")
	public Result saveEditDict(HttpServletRequest request, Model model, SysHospital hostpital) {
		Result result = Result.createResult().setSuccess(false);
		try {
			hostpital.setCreator(SessionUtil.getUser(request).getUserName());
			hostpital.setCreateDate(new Date());
			hospitalService.update(hostpital);
			result.setSuccess(true);
			SysLogsUtil.saveLogs("医院管理", "修改医院信息:" + hostpital.getName() + hostpital.getDescription(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return result;
	}

	@RequestMapping("/add")
	public String addDict(HttpServletRequest request, Model model) {
		CfgDict dict = new CfgDict();
		dict.setType(Enums.DictType.HOSPITAL_TYPE);
		List list = dictService.getAllCfgDict(dict);

		SysHospital hospital = new SysHospital();
		Map map = hospitalService.getHospitalMap(hospital);
		model.addAttribute("map", map);
		model.addAttribute("dicts", list);
		return "tiles.hospital.hospitalAdd";
	}

	@RequestMapping("/saveAdd")
	public String saveInsertDict(HttpServletRequest request, Model model, SysHospital hostpital) {
		try {

			hostpital.setCreator(SessionUtil.getUser(request).getUserName());
			hostpital.setCreateDate(new Date());
			hospitalService.insert(hostpital);
			SysLogsUtil.saveLogs("医院管理", "新增医院信息:" + hostpital.getName() + hostpital.getDescription(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/hospital/edit/" + hostpital.getId() + "?add=Y";
	}

}