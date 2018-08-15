package com.lubian.cpf.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysOrgDAO;
import com.lubian.cpf.vo.SysOrg;

@Service
public class UserOrgServiceImpl implements UserOrgService {
	
	@Autowired
    private SysOrgDAO orgDAO;
	public PageModel getOrgList(SysOrg org) {
	    
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
	    int total = orgDAO.countFreeFind(org);
	    pm.setTotal(total);
	    List<SysOrg> datas = orgDAO.freeFind(org, pageIndex, pageSize);
	    pm.setDatas(datas);
		return pm;
	}

	@Override
	public void delete(SysOrg vo) {
		orgDAO.remove(vo);
	}

	@Override
	public List getAllOrgList() {
		return orgDAO.freeFind(new SysOrg());
	}

	@Override
	public void insert(SysOrg vo) {
		orgDAO.insert(vo);
		
	}

	@Override
	public void update(SysOrg vo) {
		orgDAO.update(vo);
	}

	@Override
	public SysOrg findByPK(SysOrg vo) {
		return orgDAO.findByPK(vo);
	}

}
