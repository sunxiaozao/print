package com.lubian.cpf.controller.cpf;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lubian.cpf.common.Result;
import com.lubian.cpf.common.annotation.NeedDoctorLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.cpf.CdfBookmarkService;
import com.lubian.cpf.service.cpf.CdfFavoriteService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.vo.CdfBookmark;
import com.lubian.cpf.vo.CpfShare;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysUser;

@NeedDoctorLogin
@Controller
@RequestMapping("/bookmark")
public class BookmarkController {
	private Logger log = Logger.getLogger(BookmarkController.class);

	@Autowired
	private SysDoctorService doctorService;
	@Autowired
	private CdfBookmarkService bookmarkService;
	@Autowired
	private CdfFavoriteService favoriteService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping("/list")
	public String subtotal(HttpServletRequest request, Model model, CdfBookmark bookmark, PageModel pm) {
		if ("Y".equals(request.getParameter("add"))) {
			model.addAttribute("error", "收藏夹添加成功!");
		}
		if ("Y".equals(request.getParameter("update"))) {
			model.addAttribute("error", "收藏夹修改成功!");
		}
		if ("Y".equals(request.getParameter("del"))) {
			model.addAttribute("error", "收藏夹删除成功!");
		}
		if ("N".equals(request.getParameter("del"))) {
			model.addAttribute("error", "删除失败!该收藏夹下有收藏的病例资料!请勿!");
		}

		SysUser user = SessionUtil.getUser(request);
		SysDoctor doctor = doctorService.findDoctorByUserId(user.getUserId());
		bookmark.setDoctorId(doctor.getId());
		pm = bookmarkService.freeFind(bookmark);
		model.addAttribute("pm", pm);
		model.addAttribute("bookmark", bookmark);
		
		return "tiles.doctor.bookmark";
	}

	/**
	 * 添加收藏夾
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "/jsp/doctor/bookmark/bookmarkAdd";
	}

	/**
	 * 修改病例夹
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit/{id}")
	public String edit(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
		
		
		CdfBookmark bookmark = new CdfBookmark();
		bookmark.setId(id);
		bookmark.setName("name");
		bookmark = bookmarkService.findByPK(bookmark);
		
		
		
		model.addAttribute("bookmark", bookmark);
		return "/jsp/doctor/bookmark/bookmarkEdit";
	}

	/**
	 * 添加收藏夾
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	public String save(CdfBookmark bookmark, HttpServletRequest request) {
		SysUser user = SessionUtil.getUser(request);
		SysDoctor doctor = doctorService.findDoctorByUserId(user.getUserId());

		bookmark.setDoctorId(doctor.getId());
		bookmark.setCreateTime(new Date());
		bookmarkService.insert(bookmark);
		SysLogsUtil.saveLogs("添加收藏夹", "添加收藏夹:" + user.getUserName() + "添加" + bookmark.getName(), request);
		return "redirect:/bookmark/list?add=Y";
	}

	/**
	 * 添加收藏夾
	 * 
	 * @return
	 */
	@RequestMapping("/update")
	public String update(CdfBookmark bookmark, HttpServletRequest request) {
		SysUser user = SessionUtil.getUser(request);
		bookmark.setCreateTime(new Date());
		bookmarkService.update(bookmark);
		SysLogsUtil.saveLogs("修改收藏夹", "修改收藏夹:" + user.getUserName() + "修改" + bookmark.getName(), request);
		return "redirect:/bookmark/list?update=Y";
	}

	/**
	 * 删除收藏夹
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/del/{id}")
	public String delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		SysUser user = SessionUtil.getUser(request);
		
		
		
		if(favoriteService.count(id)){
			CdfBookmark bookmark = new CdfBookmark();
			bookmark.setId(id);
			
			
			bookmarkService.delete(bookmark);
			SysLogsUtil.saveLogs("删除收藏夹", "删除收藏夹:" + user.getUserName() + "删除" + bookmark.getName(), request);
			return "redirect:/bookmark/list?del=Y";
		}
		return "redirect:/bookmark/list?del=N";
		
	}

}
