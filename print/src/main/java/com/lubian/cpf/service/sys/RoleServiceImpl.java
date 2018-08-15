package com.lubian.cpf.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysRoleDAO;
import com.lubian.cpf.vo.SysRole;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private SysRoleDAO roleDAO;
	@Autowired
	private RelRoleFuncService relRoleFuncService;
	
	public PageModel getRoleList(SysRole role){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = roleDAO.countFreeFind(role);
		pm.setTotal(total);
		List<SysRole> datas = roleDAO.freeFind(role, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	
	@Override
	public List getAllRoleList(){
		return roleDAO.freeFind(new SysRole());
	}

	@Override
	public void delete(SysRole vo) {
		
		roleDAO.remove(vo);
		relRoleFuncService.deleteByRoleId(vo.getRoleId());
		//deal with users associated with this role
		
	}

	public SysRole findByPK(SysRole vo) {
	
		return roleDAO.findByPK(vo);
		
	}

	public void update(SysRole vo) {
		
		roleDAO.update(vo);
	}

	public void insert(SysRole vo) {
		
		roleDAO.insert(vo);
		
	}
}
