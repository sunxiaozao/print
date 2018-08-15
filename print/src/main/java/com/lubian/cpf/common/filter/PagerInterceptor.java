package com.lubian.cpf.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lubian.cpf.common.constant.Constant;


public class PagerInterceptor extends HandlerInterceptorAdapter {
	public static final String PAGE_SIZE_NAME = "pageSize";
	public static final String PAGE_OFFSET = "pageIndex";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		request.setAttribute(PAGE_SIZE_NAME,getPagesize(request));
		request.setAttribute(PAGE_OFFSET, getOffset(request));
		
		return true;
	}
	
	private int getOffset(HttpServletRequest request) {
		int offset = 1;
		try {
			offset = Integer.parseInt(request.getParameter(PAGE_OFFSET));
		} catch (Exception ignore) {
		}
		return offset;
	}

	private int getPagesize(HttpServletRequest httpRequest) {
		//首先判断request中是否有pagesize参数，如果有这个参数，证明客户端正在请求改变每页显示的行数
		String psvalue = httpRequest.getParameter(PAGE_SIZE_NAME);
		if (psvalue != null && !psvalue.trim().equals("")) {
			Integer ps = 0;
			try {
				ps = Integer.parseInt(psvalue);
			} catch (Exception e) {
			}
			if (ps != 0) {
				httpRequest.getSession().setAttribute(PAGE_SIZE_NAME, ps);
			}
		}

		//判断当前session中是否有pagesize的值
		Integer pagesize = (Integer) httpRequest.getSession().getAttribute(PAGE_SIZE_NAME);
		if (pagesize == null) {
			Integer page_size = Constant.DEFAULT_PAGESIZE;// 这里没有用try拦截异常,以为在它的上层拦截了异常.此处进行了每页记录数可配置的扩展功能.
			httpRequest.getSession().setAttribute(PAGE_SIZE_NAME, page_size);
			return page_size;
		}
		return pagesize;
	}
}
