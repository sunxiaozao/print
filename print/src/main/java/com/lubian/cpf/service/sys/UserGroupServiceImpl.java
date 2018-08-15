package com.lubian.cpf.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysUserGroupDAO;
import com.lubian.cpf.vo.SysUserGroup;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	
	@Autowired
    private SysUserGroupDAO groupDAO;
	public PageModel getGroupList(SysUserGroup group) {
	    
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
	    int total = groupDAO.countFreeFind(group);
	    pm.setTotal(total);
	    List<SysUserGroup> datas = groupDAO.freeFind(group, pageIndex, pageSize);
	    pm.setDatas(datas);
		return pm;
	}
    
	@Override
	public void delete(SysUserGroup vo) {
		groupDAO.remove(vo);
	}

	@Override
	public List getAllGroupList() {
		return groupDAO.freeFind(new SysUserGroup());
	}

	@Override
	public void insert(SysUserGroup vo) {
		groupDAO.insert(vo);
		
	}

	@Override
	public void update(SysUserGroup vo) {
		groupDAO.update(vo);
	}

	@Override
	public SysUserGroup findByPK(SysUserGroup vo) {
		return groupDAO.findByPK(vo);
	}
}
