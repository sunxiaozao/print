package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysUserMessageItem;

/**
 * SysUserMessageItemDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysUserMessageItemDAO {


	public int countFindAll();
	
	public SysUserMessageItem findByPK( SysUserMessageItem obj );
	public List freeFind( SysUserMessageItem obj );
	public int countFreeFind( SysUserMessageItem obj );
	public List freeFind( SysUserMessageItem obj, String orderByColName );
	public List freeFind( SysUserMessageItem obj, int pageIndex, int pageSize );
	public List freeFind( SysUserMessageItem obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysUserMessageItem>  objColl);
	public void save( SysUserMessageItem vo );
	public void insert( SysUserMessageItem vo );
	public void update( SysUserMessageItem vo );
	public void removeObjectTree( SysUserMessageItem obj );
	public void remove( SysUserMessageItem vo );
	public boolean exists( SysUserMessageItem vo );
}
