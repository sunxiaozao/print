package com.lubian.cpf.service.sys;


import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysUserMessage;

public interface UserMessageService {
	public SysUserMessage findByPK(int messageId);
	public SysUserMessage findByPK( SysUserMessage obj );
	public PageModel freeFind( SysUserMessage obj );
	public void save( SysUserMessage vo );
	public void insert( SysUserMessage vo );
	public void update( SysUserMessage vo );
	public void remove( SysUserMessage vo );

}
