package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysUserGroup;

/**
 * SysUserGroupDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysUserGroupDAO {


	public int countFindAll();
	
	public SysUserGroup findByPK( SysUserGroup obj );
	public List freeFind( SysUserGroup obj );
	public int countFreeFind( SysUserGroup obj );
	public List freeFind( SysUserGroup obj, String orderByColName );
	public List freeFind( SysUserGroup obj, int pageIndex, int pageSize );
	public List freeFind( SysUserGroup obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysUserGroup>  objColl);
	public void save( SysUserGroup vo );
	public void insert( SysUserGroup vo );
	public void update( SysUserGroup vo );
	public void removeObjectTree( SysUserGroup obj );
	public void remove( SysUserGroup vo );
	public boolean exists( SysUserGroup vo );
}
