package com.lubian.cpf.controller.cpf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.lubian.cpf.common.util.Encrypt;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.controller.cfg.ParaController;
import com.lubian.cpf.service.cfg.DictService;
import com.lubian.cpf.service.sys.SysDepartmentService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.CfgDict;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@NeedAdminPrivilege
@RequestMapping("/admin/doctor")
public class DoctorsController {

	private Logger log = Logger.getLogger(ParaController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private SysHospitalService hospitalService;

	@Autowired
	private SysDepartmentService departmentService;

	@Autowired
	private SysDoctorService doctorService;

	/**
	 * 显示医生列表
	 * 
	 * @param request
	 * @param pm
	 * @param model
	 * @param doctor
	 * @return
	 */
	@RequestMapping("/list")
	public String getDictList(HttpServletRequest request, PageModel pm,
			Model model, SysDoctor doctor) {
		pm = doctorService.freeFind(doctor);
		model.addAttribute("pm", pm);
		SysDepartment department = new SysDepartment();
		department.setId(doctor.getDepartmentId());
		department = departmentService.findByPK(department);// 获取所在信息
		model.addAttribute("department", department);
		model.addAttribute("doctor", doctor);
		model.addAttribute("sexMap", Enums.getSexMap());// 设置性别信息
		return "tiles.doctor.doctorList";
	}

	@RequestMapping("/delete/{doctorId}/{departmentId}")
	public String deleteDict(HttpServletRequest request, Model model,
			@PathVariable("doctorId") Integer doctorId,
			@PathVariable("departmentId") Integer departmentId) {

		try {

			SysDoctor doctor = new SysDoctor();
			doctor.setId(doctorId);
			doctorService.delete(doctor);
			SysUser user = new SysUser();
			user.setUserId(doctor.getUserId());
			userService.deleteUser(user);
			SysLogsUtil.saveLogs("医生管理", "删除删除信息:" + doctor.getFullname()
					+ doctor.getTitle(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/doctor/list?departmentId=" + departmentId;
	}

	@RequestMapping("/edit/{doctorID}")
	public String editDict(HttpServletRequest request, Model model,
			@PathVariable("doctorID") Integer doctorID) {
		String addFlag = request.getParameter("add");
		if ("Y".equals(addFlag)) {
			model.addAttribute("error", "数据添加成功");
		}else if("N".equals(addFlag)){
			model.addAttribute("error", "数据修改成功");
		}
		SysDoctor doctor = new SysDoctor();
		doctor.setId(doctorID);
		doctor = doctorService.findByPK(doctor);// 获取医生信息
		SysDepartment department = new SysDepartment();
		department.setId(doctor.getDepartmentId());
		department = departmentService.findByPK(department);// 获取科室信息
		SysHospital hostpital = new SysHospital();
		hostpital.setId(doctor.getHospitalId());
		hostpital = hospitalService.findByPK(hostpital);// 获取医院信息

		SysUser user = new SysUser();
		user.setUserId(doctor.getUserId());

		user = userService.findByPK(user);// 获得用户信息

		String type = checheLoginType(user.getAccountType());// 获得当前登录类型
		model.addAttribute("type", type);
		model.addAttribute("user", user);
		model.addAttribute("hostpital", hostpital);
		model.addAttribute("doctor", doctor);
		model.addAttribute("department", department);
		model.addAttribute("sexMap", Enums.getSexMap());// 性别信息
		model.addAttribute("loginType", Enums.getLoginTypeMap());// 登录类型列表
		return "tiles.doctor.doctorEdit";
	}

	

	@RequestMapping("/saveEdit")
	public String saveEditDict(HttpServletRequest request, Model model,
			SysDoctor doctor) {

		try {
			doctorService.update(doctor, request);
			SysLogsUtil.saveLogs("医生管理", "修改医生信息:" + doctor.getFullname()
					+ doctor.getTitle(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/doctor/edit/" + doctor.getId() + "?add=N";
	}

	@RequestMapping("/add/{departmentId}")
	public String addDict(HttpServletRequest request, Model model,
			@PathVariable("departmentId") Integer departmentId) {

		SysDepartment department = new SysDepartment();
		department.setId(departmentId);
		department = departmentService.findByPK(department);
		SysHospital hostpital = new SysHospital();
		hostpital.setId(department.getHospitalId());
		hostpital = hospitalService.findByPK(hostpital);
		model.addAttribute("hostpital", hostpital);
		model.addAttribute("department", department);
		model.addAttribute("sexMap", Enums.getSexMap());
		model.addAttribute("loginType", Enums.getLoginTypeMap());
		return "tiles.doctor.doctorAdd";
	}

	@RequestMapping("/saveAdd")
	public String saveInsertDict(HttpServletRequest request, Model model,
			SysDoctor doctor) {
		try {
			doctorService.save(doctor, request);

			SysLogsUtil.saveLogs("医生管理", "新增医生信息:" + doctor.getFullname()
					+ doctor.getTitle(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/doctor/edit/" + doctor.getId() + "?add=Y";
	}

	/**
	 * 判断登录类型
	 * 
	 * @param type
	 * @return
	 */
	private String checheLoginType(String type) {
		if (null != type && !"".equals(type)) {
			if (type.indexOf(Enums.LoginType.IC) > -1) {
				return Enums.LoginType.IC;
			} else if (type.indexOf(Enums.LoginType.EMAIL) > -1) {
				return Enums.LoginType.EMAIL;
			} else if (type.indexOf(Enums.LoginType.MOBILE) > -1) {
				return Enums.LoginType.MOBILE;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}