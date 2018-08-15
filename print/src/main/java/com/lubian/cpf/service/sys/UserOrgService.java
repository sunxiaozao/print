package com.lubian.cpf.service.sys;

import java.util.List;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysOrg;


public interface UserOrgService {
	
	public PageModel getOrgList(SysOrg org);
	
	public List getAllOrgList();
	
	public void delete(SysOrg vo);
	
	public SysOrg findByPK(SysOrg vo) ;
	
	public void update(SysOrg vo); 
	
	public void insert( SysOrg vo);
}
