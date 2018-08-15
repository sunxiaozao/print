package com.lubian.cpf.service.sys;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysMailJob;

/**
 * SysMailJobService
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysMailJobService {
	public SysMailJob findByPK( SysMailJob vo );
	public PageModel freeFind( SysMailJob vo );	
	public void insert( SysMailJob vo );
	public void update( SysMailJob vo );
	public void delete( SysMailJob vo );
}
