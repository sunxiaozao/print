package com.lubian.cpf.controller.cfg;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedAdminPrivilege;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.cfg.ParaService;
import com.lubian.cpf.vo.CfgPara;

@NeedLogin
@NeedAdminPrivilege
@Controller
@RequestMapping("/admin/para")
public class ParaController {
	private Logger log = Logger.getLogger(ParaController.class);
	@Autowired
	private ParaService sysParaService;

	@RequestMapping("/list")
	public String getParaList(HttpServletRequest request, Model model, PageModel pm, CfgPara sysPara) {
		if (StringUtils.isBlank(sysPara.getParentCode())) {
			String searchParentCode = (String) request.getSession().getAttribute("searchParentCode");
			if (!StringUtils.isBlank(searchParentCode)) {
				sysPara.setParentCode(searchParentCode);
			} else {
				sysPara.setParentCode("sys");
			}
		}
		pm = sysParaService.freeFind(sysPara);
		model.addAttribute("pm", pm);
		// 添加类别于map中
		this.getSysParaType(request, model);
		// 页面选择项确定
		model.addAttribute("sysPara", sysPara);
		request.getSession().setAttribute("searchParentCode", sysPara.getParentCode());
		return "tiles.admin.sysParaList";
	}

	// 删除
	@RequestMapping("/delete")
	public String deleteSysPara(HttpServletRequest request, Model model) {
		String paraCode = request.getParameter("c");
		CfgPara sysPara = new CfgPara();
		sysPara.setParaCode(paraCode);
		sysPara = this.sysParaService.findByPK(sysPara);
		sysParaService.remove(sysPara);
		request.getSession().setAttribute("searchParentCode", sysPara.getParentCode());
		SysLogsUtil.saveLogs("参数管理", "删除参数:"+sysPara.getParaCode()+sysPara.getParaName(), request);
		return "redirect:/admin/para/list";
	}

	// 添加页面
	@RequestMapping("/add")
	public String addSyspara(HttpServletRequest request, Model model) {
		CfgPara sysPara = new CfgPara();
		String searchParentCode = (String) request.getSession().getAttribute("searchParentCode");
		sysPara.setParentCode(searchParentCode);
		model.addAttribute("sysPara", sysPara);
		// 添加类别于map中
		this.getSysParaType(request, model);
		return "tiles.admin.sysParaAdd";
	}

	// 添加记录
	@RequestMapping("/saveAdd")
	public String insertSysPara(HttpServletRequest request, Model model, CfgPara sysPara){
		try {
			// 以主键判断此记录是否存在
			CfgPara para = new CfgPara();
			para.setParaCode(sysPara.getParaCode());
			if (!sysParaService.exists(para)) {
				sysParaService.insert(sysPara);
				request.getSession().setAttribute("searchParentCode", sysPara.getParentCode());
				SysLogsUtil.saveLogs("参数管理", "新增参数:"+sysPara.getParaCode()+sysPara.getParaName(), request);
			}
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
			// 添加类别于map中
			this.getSysParaType(request, model);
			model.addAttribute("sysPara", sysPara);
			request.getSession().setAttribute("searchParentCode", sysPara.getParentCode());
			return "tiles.admin.sysParaAdd";
		}
		return "redirect:/admin/para/list";
	}

	// 修改页面
	@RequestMapping("/edit")
	public String editSysPara(HttpServletRequest request, Model model) {
		String paraCode = request.getParameter("c");
		CfgPara sysPara = new CfgPara();
		sysPara.setParaCode(paraCode);
		sysPara = sysParaService.findByPK(sysPara);
		model.addAttribute("sysPara", sysPara);
		// 添加类别于map中
		this.getSysParaType(request, model);
		return "tiles.admin.editSysPara";
	}

	// 修改页面
	//@RequestMapping("/saveEdit")
	public String saveEditSysPara(HttpServletRequest request, Model model, CfgPara sysPara) {
		try {
			sysParaService.update(sysPara);
			request.getSession().setAttribute("searchParentCode", sysPara.getParentCode());
			SysLogsUtil.saveLogs("参数管理", "更新参数:"+sysPara.getParaCode()+sysPara.getParaName(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
			// 添加类别于map中
			this.getSysParaType(request, model);
			model.addAttribute("sysPara", sysPara);
			request.getSession().setAttribute("searchParentCode", sysPara.getParentCode());
			return "tiles.admin.editSysPara";
		}
		return "redirect:/admin/para/list";
	}
	
	@ResponseBody
	@RequestMapping("/saveEdit")
	public Result saveEditGroupNotReturn(HttpServletRequest request, Model model, CfgPara sysPara) {

		Result result = Result.createResult().setSuccess(false);
		try {
			this.saveEditSysPara(request, model, sysPara);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
			return result;
		}
		result.setSuccess(true);
		return result;
	}

	// 添加类别于map中
	private void getSysParaType(HttpServletRequest request, Model model) {
		Map<String, String> sysParaTypeMap = (Map<String, String>) new ListOrderedMap();
		List<CfgPara> list = this.sysParaService.getParaTypeList();
		for (CfgPara para : list) {
			sysParaTypeMap.put(para.getParaCode(), para.getParaName());
		}
		model.addAttribute("sysParaTypeMap", sysParaTypeMap);
	}

}
