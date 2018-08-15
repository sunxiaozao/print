package com.lubian.cpf.controller.sys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.lubian.cpf.common.annotation.NeedAdminPrivilege;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.service.sys.UserGroupService;
import com.lubian.cpf.service.sys.UserMessageItemService;
import com.lubian.cpf.service.sys.UserMessageService;
import com.lubian.cpf.service.sys.UserOrgService;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.SysOrg;
import com.lubian.cpf.vo.SysUser;
import com.lubian.cpf.vo.SysUserGroup;
import com.lubian.cpf.vo.SysUserMessage;
import com.lubian.cpf.vo.SysUserMessageItem;

@NeedLogin
@NeedAdminPrivilege
@Controller
@RequestMapping("/admin/message")
public class UserMessageController {

	private Logger log =Logger.getLogger(UserMessageController.class);
	@Autowired
	private UserMessageService userMessageService ;
	@Autowired
	private UserMessageItemService userMessageItemService ;
	@Autowired
	private UserService userService;
	@Autowired
	private UserOrgService orgService;
	@Autowired
	private UserGroupService groupService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("/list")
	public String getUserMessageList(HttpServletRequest request, Model model,PageModel pm, SysUserMessage userMessage) {
	
		if(StringUtils.isBlank(request.getParameter("userType"))){
			userMessage.setUserType(null);
		}
		if(StringUtils.isBlank(request.getParameter("isGenerated"))){
			userMessage.setIsGenerated(null);
		}
		userMessage.setCreateDtTo(DateUtil.addOneDay(userMessage.getCreateDtTo()));
		pm = userMessageService.freeFind(userMessage) ;
	    model.addAttribute("pm", pm) ;
	    model.addAttribute("userTypeMap", Enums.getUserTypeMap()) ;
	    request.getSession().setAttribute("userMessageSearch", userMessage);
		
	    List<SysOrg> orgList=orgService.getAllOrgList();
	    Map<Integer,String> orgMap=new HashMap<Integer,String>();
	    for(SysOrg info:orgList){  
	    	orgMap.put(info.getOrgId(),info.getOrgName());
	    }
	    model.addAttribute("orgMap", orgMap);
	    
	    List<SysUserGroup> groupList=groupService.getAllGroupList();
	    Map<Integer,String> groupMap=new HashMap<Integer,String>();
	    for(SysUserGroup info:groupList){
	    	groupMap.put(info.getGroupId(),info.getGroupName());
	    }
	    model.addAttribute("groupMap", groupMap);
	    
//	    List<SysUser> userList=userService.freeFind(new SysUser());
//	    Map<Integer,String> userMap=new HashMap<Integer,String>();
//	    for(SysUser info:userList){
//	    	userMap.put(info.getUserId(), info.getUserName());
//	    }
//	    model.addAttribute("userMap", map);
	    
		return "tiles.admin.userMessageList" ;
	}
	
	@RequestMapping("/ret")
	public String retUserMessageList(HttpServletRequest request, Model model, PageModel pm) {
		SysUserMessage userMessage = (SysUserMessage)request.getSession().getAttribute("userMessageSearch");
		if(userMessage==null){
			userMessage = new SysUserMessage();
		}
		return this.getUserMessageList(request, model, pm, userMessage);
	}
	
	@RequestMapping("/add")
	public String addUserMessage(HttpServletRequest request, Model model, SysUserMessage userMessage) {
		
		List<SysOrg> orgList=orgService.getAllOrgList();
	    Map<Integer,String> orgMap=new HashMap<Integer,String>();
	    for(SysOrg info:orgList){  
	    	orgMap.put(info.getOrgId(),info.getOrgName());
	    }
	    model.addAttribute("orgMap", orgMap);
	    
	    List<SysUserGroup> groupList=groupService.getAllGroupList();
	    Map<Integer,String> groupMap=new HashMap<Integer,String>();
	    for(SysUserGroup info:groupList){
	    	groupMap.put(info.getGroupId(),info.getGroupName());
	    }
	    model.addAttribute("groupMap", groupMap);
		model.addAttribute("userTypeMap", Enums.getUserTypeMap()) ;
		return "tiles.admin.userMessageAdd" ;
	}
	
	@RequestMapping("/saveAdd")
	public String saveAddUserMessage(HttpServletRequest request, Model model, SysUserMessage userMessage) {
		try {
			userMessage.setCreateDt(new Date()) ;
			userMessage.setCreateBy(SessionUtil.getUser(request).getUserName()) ;
			userMessageService.insert(userMessage) ;
			SysLogsUtil.saveLogs("消息管理", "新增消息:"+userMessage.getContent(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/message/edit/" + userMessage.getMessageId() + "?add=Y";
	}
	
	@RequestMapping("/delete/{messageId}")
	public String deleteUserMessage(HttpServletRequest request, Model model, @PathVariable("messageId") Integer messageId) {
		try {
			SysUserMessage userMessage = new SysUserMessage();
			userMessage.setMessageId(messageId);
			userMessageService.remove(userMessage) ;
			SysLogsUtil.saveLogs("消息管理", "删除消息:"+userMessage.getContent(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/message/ret" ;
	}
	
	@ResponseBody
	@RequestMapping("/publish")
	public Result publishUserMessage(HttpServletRequest request, Model model, SysUserMessage userMessage) {
		Result result = Result.createResult().setSuccess(false);
		try {
			userMessage = userMessageService.findByPK(userMessage) ;
			userMessage.setIsGenerated("Y") ;
			userMessage.setModifyBy(SessionUtil.getUser(request).getUserName());
			userMessage.setModifyDt(new Date());
			
			
			SysUser user=new SysUser();
			//userType为A所有会员,B为特定机构,C为特定用户组,D为特定会员
			if(userMessage.getUserType().equals(Enums.UMUserType.ALLMEMBER)){
				//user.setUserType(Enums.UserType.MEMBER);
			}else if(userMessage.getUserType().equals(Enums.UMUserType.ORG)){
				user.setOrgId(userMessage.getOrgId());
			}else if(userMessage.getUserType().equals(Enums.UMUserType.GROUP)){
				user.setGroupId(userMessage.getGroupId());	
			}else if(userMessage.getUserType().equals(Enums.UMUserType.ONEMEMBER)){
//				user.set;
			}
			List<SysUser> list= userService.freeFind(user);
			for(SysUser info:list){				
			    SysUserMessageItem userMessageItem=new SysUserMessageItem();
			    userMessageItem.setIsRead("N");
			    userMessageItem.setToUserId(info.getUserId());
			    userMessageItem.setToUserName(info.getUserName());
			    userMessageItem.setMessageId(userMessage.getMessageId());
			    userMessageItem.setCreateBy(SessionUtil.getUser(request).getUserName());
			    userMessageItem.setCreateDt(new Date());			    
				userMessageItemService.insert(userMessageItem);
				
			}
			
			userMessage.setMsgCount(list.size());
			userMessageService.update(userMessage) ;
			result.setSuccess(true) ;
			SysLogsUtil.saveLogs("消息管理", "发布消息:"+userMessage.getContent(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return result;
	}

	@RequestMapping("/edit/{messageId}")
	public String editUserMessage(HttpServletRequest request, Model model, @PathVariable("messageId") Integer messageId) {
       
		if("Y".equals(request.getParameter("add"))){
			model.addAttribute("error","新增消息保存成功");
		}
		
		SysUserMessage userMessage = new SysUserMessage();
		userMessage.setMessageId(messageId);
		userMessage=userMessageService.findByPK(userMessage);
		if(userMessage != null){
		   model.addAttribute("userMessage", userMessage);
		}
		model.addAttribute("userTypeMap", Enums.getUserTypeMap()) ;
		
		List<SysOrg> orgList=orgService.getAllOrgList();
	    Map<Integer,String> orgMap=new HashMap<Integer,String>();
	    for(SysOrg info:orgList){  
	    	orgMap.put(info.getOrgId(),info.getOrgName());
	    }
	    model.addAttribute("orgMap", orgMap);
	    
	    List<SysUserGroup> groupList=groupService.getAllGroupList();
	    Map<Integer,String> groupMap=new HashMap<Integer,String>();
	    for(SysUserGroup info:groupList){
	    	groupMap.put(info.getGroupId(),info.getGroupName());
	    }
	    model.addAttribute("groupMap", groupMap);
	   return "tiles.admin.userMessageEdit";
	}
	
	//@RequestMapping("/saveEdit")
	public String saveEditUserMessage(HttpServletRequest request, Model model,SysUserMessage userMessage){
		
		userMessage.setModifyBy(SessionUtil.getUser(request).getUserName());
		userMessage.setModifyDt(new Date());
		try {
			userMessageService.update(userMessage);
			SysLogsUtil.saveLogs("消息管理", "更新消息:"+userMessage.getContent(), request);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/message/ret";
	}
	
	@ResponseBody   
	@RequestMapping("/saveEdit")
	public Result saveEditUserMessageNotReturn(HttpServletRequest request, Model model,SysUserMessage userMessage) {
                
		Result result = Result.createResult().setSuccess(false);
		try {
			this.saveEditUserMessage(request, model,userMessage);
		} catch (Exception e) {
			log.error(e);
			model.addAttribute("error", e.getMessage());
			return result;
		}
		result.setSuccess(true);
		return result;
	}
	
}
