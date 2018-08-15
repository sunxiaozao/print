package com.lubian.cpf.service.sys;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysLogsDAO;
import com.lubian.cpf.vo.SysLogs;
import com.lubian.cpf.vo.SysUser;

@Service
public class SysLogsServiceImpl implements SysLogsService {
	private static final Logger log = Logger.getLogger(SysLogsServiceImpl.class);
	@Autowired
	private SysLogsDAO sysLogsDAO;

	public PageModel getSysLogs(SysLogs sysLogs) {

		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = sysLogsDAO.countFreeFind(sysLogs);
		pm.setTotal(total);
		// order by createdDt;
		List<SysLogs> datas = sysLogsDAO.freeFind(sysLogs, pageIndex, pageSize, "CDATE DESC");
		pm.setDatas(datas);
		return pm;
	}

	public void remove(SysLogs vo) {
		sysLogsDAO.remove(vo);
	}

	public void saveLogs(String functionName, String desc, HttpServletRequest request) {
		SysUser user = SessionUtil.getUser(request);
		String userName = null;
		SysLogs sysLogs = new SysLogs();
		if (user != null) {
			userName = StringUtils.isBlank(user.getRealName()) ? user.getUserName() : user.getUserName() + "(" + user.getRealName() + ")";
			sysLogs.setUserId(user.getUserId());
		} else {
			
		}

		sysLogs.setUserName(userName);
		sysLogs.setIp(request.getRemoteAddr());
		sysLogs.setCdate(new Date());
		sysLogs.setFunctionName(functionName);
		String description = desc;
		sysLogs.setDescription(description);
		sysLogsDAO.insert(sysLogs);
	}
}
