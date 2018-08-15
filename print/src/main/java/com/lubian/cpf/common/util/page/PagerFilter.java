package com.lubian.cpf.common.util.page;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.lubian.cpf.common.constant.Constant;


public class PagerFilter implements Filter {
	public static final String PAGE_SIZE_NAME = "ps";

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		SystemContext.setOffset(getOffset(httpRequest));
		SystemContext.setPagesize(getPageSize(httpRequest));
		try {
			chain.doFilter(request, response);
		} finally {
			// 清空ThreadLocal中的值
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	public int getOffset(HttpServletRequest request) {
		int offset = 0;
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (NumberFormatException ignore) {
		}
		return offset;
	}

	// 设置每页显示多少条记录
	public int getPageSize(HttpServletRequest httpRequest) {
		// 首先判断request中是否有pagesize参数，如果有这个参数，证明客户端正在请求改变每页显示的行数
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

		// 判断当前session中是否有pagesize的值
		Integer pagesize = (Integer) httpRequest.getSession().getAttribute(PAGE_SIZE_NAME);
		if (pagesize == null) {
			Integer page_size = Constant.DEFAULT_PAGESIZE;// 这里没有用try拦截异常,以为在它的上层拦截了异常.此处进行了每页记录数可配置的扩展功能.;

			httpRequest.getSession().setAttribute(PAGE_SIZE_NAME, page_size);
			return page_size;
		}

		return pagesize;
	}
}