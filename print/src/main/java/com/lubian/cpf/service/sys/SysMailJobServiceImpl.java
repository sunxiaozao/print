package com.lubian.cpf.service.sys;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysMailJobDAO;
import com.lubian.cpf.vo.SysMailJob;

@Service
public class SysMailJobServiceImpl implements SysMailJobService {
	@Autowired
	private SysMailJobDAO sysMailJobDAO;

	public SysMailJob findByPK(SysMailJob vo) {
		return sysMailJobDAO.findByPK(vo);
	}

	public PageModel freeFind(SysMailJob vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = sysMailJobDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<SysMailJob> list = sysMailJobDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(SysMailJob vo) {
		sysMailJobDAO.insert(vo);
	}

	public void update(SysMailJob vo) {
		sysMailJobDAO.update(vo);
	}
	
	public void delete(SysMailJob vo) {
		sysMailJobDAO.remove(vo);
	}
}
