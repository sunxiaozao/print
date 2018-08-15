package com.lubian.cpf.controller.cfg;

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
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.cfg.DictService;
import com.lubian.cpf.vo.CfgDict;


@NeedLogin
@Controller
@NeedAdminPrivilege
@RequestMapping("/admin/dict")
public class DictController {

	private Logger log = Logger.getLogger(ParaController.class);
	@Autowired
	private DictService dictService ;
	
	@RequestMapping("/list")
	public String getDictList(HttpServletRequest request,PageModel pm , Model model, CfgDict dict) {
		if(StringUtils.isBlank(dict.getType())) {
			String searchType = (String)request.getSession().getAttribute("searchType") ;
			if(!StringUtils.isBlank(searchType)) {
				dict.setType(searchType) ;
			}
			else {
				dict.setType(Enums.DictType.USER_TYPE
						) ;
			}
		}
		pm = dictService.listCfgDict(dict) ;
		model.addAttribute("pm", pm) ;
		model.addAttribute("sysDict", dict) ;
		model.addAttribute("sysDictTypeMap",Enums.getDictTypeMap()) ;
		request.getSession().setAttribute("searchType", dict.getType()) ;
		return "tiles.admin.cfgDictList" ;
	}
	
	@RequestMapping("/delete/{dictId}")
	public String deleteDict(HttpServletRequest request, Model model, @PathVariable("dictId") Integer dictId) {
		try {
			CfgDict dict = new CfgDict();
			dict.setDictId(dictId);
			dictService.deleteCfgDict(dict) ;
			SysLogsUtil.saveLogs("数据字典管理", "删除字典信息:"+dict.getShortCode()+dict.getLongName(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/dict/list" ;
	}
	
	@RequestMapping("/edit/{dictId}")
	public String editDict(HttpServletRequest request, Model model, @PathVariable("dictId") Integer dictId) {
		String addFlag = request.getParameter("add") ;
		if("Y".equals(addFlag)) {
			model.addAttribute("error", "数据添加成功") ;
		}
		CfgDict dict = new CfgDict();
		dict.setDictId(dictId);
		try {
			dict = dictService.findCfgDict(dict) ;
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("sysDict", dict) ;
		model.addAttribute("sysDictTypeMap",Enums.getDictTypeMap()) ;
		return "tiles.admin.cfgDictEdit" ;
	}
	
	@ResponseBody
	@RequestMapping("/saveEdit")
	public Result saveEditDict(HttpServletRequest request, Model model, CfgDict dict) {
		Result result = Result.createResult().setSuccess(false) ;
		try {
			dictService.updateCfgDict(dict) ;
			result.setSuccess(true) ;
			SysLogsUtil.saveLogs("数据字典管理", "更新字典信息:"+dict.getShortCode()+dict.getLongName(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return result ;
	}
	
	@RequestMapping("/add")
	public String addDict(HttpServletRequest request, Model model, CfgDict dict) {
		model.addAttribute("sysDictTypeMap",Enums.getDictTypeMap()) ;
		String searchType = (String)request.getSession().getAttribute("searchType") ;
		dict.setType(searchType);
		model.addAttribute("sysDict", dict);
		return "tiles.admin.cfgDictAdd" ;
	}
	
	@RequestMapping("/saveAdd")
	public String saveInsertDict(HttpServletRequest request, Model model, CfgDict dict) {
		try {
			dictService.addCfgDict(dict) ;
			SysLogsUtil.saveLogs("数据字典管理", "新增字典信息:"+dict.getShortCode()+dict.getLongName(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/dict/edit/" + dict.getDictId() + "?add=Y";
	}
	
	@RequestMapping("/checkShortCode")
	@ResponseBody
	public Result checkShortCode(CfgDict dict) {
		Result result = Result.createResult().setSuccess(false) ;
		List dicts = dictService.getAllCfgDict(dict) ;
		if(dicts != null && dicts.size() > 0) {
			result.setSuccess(true) ;
		}
		return result ;
	}
}