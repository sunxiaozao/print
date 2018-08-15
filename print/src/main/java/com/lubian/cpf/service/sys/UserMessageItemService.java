package com.lubian.cpf.service.sys;

import java.util.List;

import com.lubian.cpf.vo.SysUserMessageItem;

public interface UserMessageItemService {
	public void remove(SysUserMessageItem vo);
	public void insert( SysUserMessageItem vo );
	public List getItemList(SysUserMessageItem vo);
	public void remove(int itemId);
	public void setItemRead(SysUserMessageItem vo);
}
