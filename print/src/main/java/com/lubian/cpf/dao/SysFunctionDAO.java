package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysFunction;

/**
 * SysFunctionDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysFunctionDAO {


	public List getFunctionByRoleAndCategory(Object role_id,Object category_id);
	public int countGetFunctionByRoleAndCategory(Object role_id,Object category_id);
	public List getFunctionByRoleAndCategory(Object role_id,Object category_id , int pageIndex, int pageSize);

	public int countFindAll();
	
	public SysFunction findByPK( SysFunction obj );
	public List freeFind( SysFunction obj );
	public int countFreeFind( SysFunction obj );
	public List freeFind( SysFunction obj, String orderByColName );
	public List freeFind( SysFunction obj, int pageIndex, int pageSize );
	public List freeFind( SysFunction obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysFunction>  objColl);
	public void save( SysFunction vo );
	public void insert( SysFunction vo );
	public void update( SysFunction vo );
	public void removeObjectTree( SysFunction obj );
	public void remove( SysFunction vo );
	public boolean exists( SysFunction vo );
}
