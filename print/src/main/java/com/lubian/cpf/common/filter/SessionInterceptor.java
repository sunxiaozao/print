package com.lubian.cpf.common.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lubian.cpf.common.annotation.NeedAdminPrivilege;
import com.lubian.cpf.common.annotation.NeedDoctorLogin;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.vo.SysUser;


public class SessionInterceptor extends HandlerInterceptorAdapter {
	private Logger log =Logger.getLogger(SessionInterceptor.class);
		
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
				
		if (handler.getClass().getAnnotation(NeedLogin.class) != null) {
			if (SessionUtil.getUser(request) == null) {
				try {
					response.sendRedirect(request.getContextPath()+"/admin/needLogin");			
				} catch (Exception e) {
					e.printStackTrace();
					log.debug("SessionInterceptor error.");
				}
				return false;
			}
		}
		
		if (handler.getClass().getAnnotation(NeedDoctorLogin.class) != null) {
			SysUser userInfo=SessionUtil.getUser(request);
			if(userInfo==null || !userInfo.getUserType().equals(Enums.UserType.DOCTOR)
					&& !userInfo.getUserType().equals(Enums.UserType.SUPER_DOCTOR)
					&& !userInfo.getUserType().equals(Enums.UserType.REMOTE_DOCOR)){
				try {
					response.sendRedirect(request.getContextPath()+"/admin/needDoctorPrivilege");
				} catch (Exception e) {
					e.printStackTrace();
					log.debug("SessionInterceptor error.");
				}
				return false;
			}
		}
		
		if (handler.getClass().getAnnotation(NeedAdminPrivilege.class) != null) {
			SysUser userInfo=SessionUtil.getUser(request);
		    	if(userInfo==null || !userInfo.getUserType().equals(Enums.UserType.ADMIN)){
					try {
						response.sendRedirect(request.getContextPath()+"/admin/needAdminPrivilege");
					} catch (Exception e) {	}
					return false;
		    	}
		}
		
		//处理左侧菜单选中信息
		this.processSelectedMenu(request);

		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		if (request.getAttribute("NEED_MEM_LOGIN") != null) {
			try {
				modelAndView.setViewName("tiles.login");
			} catch (Exception e) {
			}
		}
	}
	
	private Map<String, String> processCookieMap(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		Cookie[] cookies = request.getCookies();
		if(cookies==null)
			return map;
		for(Cookie cookie : cookies){
			map.put(cookie.getName(), cookie.getValue());
		}
		return map;
	}
	
	/**
	 * 设置当前选中的菜单目录code
	 * @param request
	 */
	private void processSelectedMenu(HttpServletRequest request) {
		try {
			Map<String, String> cookieMap = this.processCookieMap(request);
			String code = cookieMap.get(Constant.COOKIE_MENU_CODE);
			if(!StringUtils.isBlank(code)){
				String[] sp = code.split("_");
				request.setAttribute(Constant.SEL_MENU_CAT, Integer.parseInt(sp[0]));
				request.setAttribute(Constant.SEL_MENU_ITEM, Integer.parseInt(sp[1]));
			}else{
				request.setAttribute(Constant.SEL_MENU_CAT, 0);
				request.setAttribute(Constant.SEL_MENU_ITEM, 0);
			}
		} catch (Exception ignore) {
		}
	}
}
