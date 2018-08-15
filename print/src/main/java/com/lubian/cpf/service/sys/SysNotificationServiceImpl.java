package com.lubian.cpf.service.sys;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysNotificationDAO;
import com.lubian.cpf.vo.SysNotification;

@Service
public class SysNotificationServiceImpl implements SysNotificationService {
	@Autowired
	private SysNotificationDAO sysNotificationDAO;

	public SysNotification findByPK(SysNotification vo) {
		return sysNotificationDAO.findByPK(vo);
	}

	public PageModel freeFind(SysNotification vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = sysNotificationDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<SysNotification> list = sysNotificationDAO.freeFind(vo, pageIndex, pageSize, "is_proceed desc, create_dt desc");
		pm.setDatas(list);
		return pm;
	}

	public int countUnProceedNoti() {
		SysNotification vo = new SysNotification();
		vo.setIsProceed("N");
		return sysNotificationDAO.countFreeFind(vo);
	}
	
	public void insert(SysNotification vo) {
		sysNotificationDAO.insert(vo);
	}

	public void update(SysNotification vo) {
		sysNotificationDAO.update(vo);
	}
	
	public void delete(SysNotification vo) {
		sysNotificationDAO.remove(vo);
	}
	
	public void insertNotification(String functionName, String desc) {
		SysNotification noti = new SysNotification();
		noti.setCreateDt(new Date());
		noti.setFunctionName(functionName);
		noti.setIsProceed("N");
		noti.setDescription(desc);
		sysNotificationDAO.insert(noti);
	}
}
