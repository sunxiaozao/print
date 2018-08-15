package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysUserMessage;

/**
 * SysUserMessageDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysUserMessageDAO {


	public int countFindAll();
	
	public SysUserMessage findByPK( SysUserMessage obj );
	public List freeFind( SysUserMessage obj );
	public int countFreeFind( SysUserMessage obj );
	public List freeFind( SysUserMessage obj, String orderByColName );
	public List freeFind( SysUserMessage obj, int pageIndex, int pageSize );
	public List freeFind( SysUserMessage obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysUserMessage>  objColl);
	public void save( SysUserMessage vo );
	public void insert( SysUserMessage vo );
	public void update( SysUserMessage vo );
	public void removeObjectTree( SysUserMessage obj );
	public void remove( SysUserMessage vo );
	public boolean exists( SysUserMessage vo );
}
