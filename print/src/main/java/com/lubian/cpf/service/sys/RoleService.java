package com.lubian.cpf.service.sys;


import java.util.List;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysRole;

public interface RoleService {
	public PageModel getRoleList(SysRole role);
	
	public List getAllRoleList();
	
	public void delete(SysRole vo);
	
	public SysRole findByPK(SysRole vo) ;
	
	public void update(SysRole vo);
	
	public void insert( SysRole vo);
}
