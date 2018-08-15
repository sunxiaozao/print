package com.lubian.cpf.service.sys;

import java.util.List;

import com.lubian.cpf.vo.SysRelRoleFunc;


public interface RelRoleFuncService {
	
	public void delete(SysRelRoleFunc vo);
	
	public void deleteByRoleId(Integer roleId);
	
	public void insert(SysRelRoleFunc vo);
	
	public List<SysRelRoleFunc> freeFind( SysRelRoleFunc obj );
	
	public SysRelRoleFunc findByPK( SysRelRoleFunc obj );
	
}
