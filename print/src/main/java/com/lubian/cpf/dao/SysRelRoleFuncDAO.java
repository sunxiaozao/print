package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysRelRoleFunc;

/**
 * SysRelRoleFuncDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysRelRoleFuncDAO {


	public int deleteByRoleId(Object role_id);

	public int countFindAll();
	
	public SysRelRoleFunc findByPK( SysRelRoleFunc obj );
	public List freeFind( SysRelRoleFunc obj );
	public int countFreeFind( SysRelRoleFunc obj );
	public List freeFind( SysRelRoleFunc obj, String orderByColName );
	public List freeFind( SysRelRoleFunc obj, int pageIndex, int pageSize );
	public List freeFind( SysRelRoleFunc obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysRelRoleFunc>  objColl);
	public void save( SysRelRoleFunc vo );
	public void insert( SysRelRoleFunc vo );
	public void update( SysRelRoleFunc vo );
	public void removeObjectTree( SysRelRoleFunc obj );
	public void remove( SysRelRoleFunc vo );
	public boolean exists( SysRelRoleFunc vo );
}
