package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysUser;

/**
 * SysUserDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysUserDAO {


	public int countFindAll();
	
	public SysUser findByPK( SysUser obj );
	public List freeFind( SysUser obj );
	public int countFreeFind( SysUser obj );
	public List freeFind( SysUser obj, String orderByColName );
	public List freeFind( SysUser obj, int pageIndex, int pageSize );
	public List freeFind( SysUser obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysUser>  objColl);
	public void save( SysUser vo );
	public void insert( SysUser vo );
	public void update( SysUser vo );
	public void removeObjectTree( SysUser obj );
	public void remove( SysUser vo );
	public boolean exists( SysUser vo );
}
