package com.lubian.cpf.service.sys;

import java.util.List;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysUserGroup;


public interface UserGroupService {
	
	public PageModel getGroupList(SysUserGroup group);
	
	public List getAllGroupList();
	
	public void delete(SysUserGroup vo);
	
	public SysUserGroup findByPK(SysUserGroup vo) ;
	
	public void update(SysUserGroup vo); 
	
	public void insert( SysUserGroup vo);
}
