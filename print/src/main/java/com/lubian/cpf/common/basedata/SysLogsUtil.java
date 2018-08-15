package com.lubian.cpf.common.basedata;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.lubian.cpf.common.util.spring.SpringContextUtil;
import com.lubian.cpf.service.sys.SysLogsService;

public class SysLogsUtil {
	private static Logger log =Logger.getLogger(SysLogsUtil.class);
	
	public static SysLogsService sysLogsService = null;

	private synchronized static void init() {
		sysLogsService = (SysLogsService) SpringContextUtil.getBean(SysLogsService.class);
	}

	static {
		init();
	}

	public static void saveLogs(String functionName, String desc, HttpServletRequest request) {
		try{
			sysLogsService.saveLogs(functionName, desc, request);
		}catch(Exception e){
			log.error(e);
		}

	}
}
