package com.lubian.cpf.common.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lubian.cpf.common.constant.Constant;

public class ApplicationStartListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//得到项目绝对路径
		Constant.ROOT_PATH = arg0.getServletContext().getRealPath("/");
		
	}
	
}
