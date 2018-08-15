package com.lubian.cpf.service.sys;

import javax.servlet.http.HttpServletRequest;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysLogs;

public interface SysLogsService {	
	public PageModel getSysLogs(SysLogs sysLogs);	
	public void remove( SysLogs vo );
	public void saveLogs(String functionName, String desc, HttpServletRequest request);
}
