package com.lubian.cpf.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {
		
	public static void addCookie(HttpServletResponse response, String cookieName, String value) {
		Cookie cook1 =new Cookie(cookieName, value);
		cook1.setPath("/");
		cook1.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cook1);
	}
	
	public static void removeCookie(HttpServletResponse response, String cookieName) {
		Cookie cooku =new Cookie(cookieName,null);
		cooku.setPath("/");
		cooku.setMaxAge(0);
		response.addCookie(cooku);
	}
}
