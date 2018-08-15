package com.lubian.cpf.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.vo.SysUser;

public class SessionUtil {

	public static SysUser getUser(HttpSession session) {
		if (session != null
				&& session.getAttribute(Constant.USER_SESSION) != null) {
			return (SysUser) session.getAttribute(Constant.USER_SESSION);
		}
		return null;
	}

	public static SysUser getUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null
				&& session.getAttribute(Constant.USER_SESSION) != null) {
			return (SysUser) session.getAttribute(Constant.USER_SESSION);
		}
		return null;
	}

	public static void removeUser(HttpSession session) {
		if (session != null
				&& session.getAttribute(Constant.USER_SESSION) != null) {
			session.setAttribute(Constant.USER_SESSION, null);
		}
	}

	public static void removeUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null
				&& session.getAttribute(Constant.USER_SESSION) != null) {
			session.setAttribute(Constant.USER_SESSION, null);
		}
	}

	public static void setUser(HttpServletRequest request, SysUser SysUser) {
		HttpSession session = request.getSession(true);
		session.setAttribute(Constant.USER_SESSION, SysUser);
	}
	
	

	

	

	

	
	

}
