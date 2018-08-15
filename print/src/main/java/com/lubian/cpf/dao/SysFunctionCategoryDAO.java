package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysFunctionCategory;

/**
 * SysFunctionCategoryDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysFunctionCategoryDAO {


	public List getFirstMenuByRoleId(Object role_id);
	public int countGetFirstMenuByRoleId(Object role_id);
	public List getFirstMenuByRoleId(Object role_id , int pageIndex, int pageSize);

	public List getSecondMenuByRoleIdAndCateogryId(Object role_id,Object parent_id);
	public int countGetSecondMenuByRoleIdAndCateogryId(Object role_id,Object parent_id);
	public List getSecondMenuByRoleIdAndCateogryId(Object role_id,Object parent_id , int pageIndex, int pageSize);

	public List getSecondMenuByRoleId(Object role_id);
	public int countGetSecondMenuByRoleId(Object role_id);
	public List getSecondMenuByRoleId(Object role_id , int pageIndex, int pageSize);

	public int countFindAll();
	
	public SysFunctionCategory findByPK( SysFunctionCategory obj );
	public List freeFind( SysFunctionCategory obj );
	public int countFreeFind( SysFunctionCategory obj );
	public List freeFind( SysFunctionCategory obj, String orderByColName );
	public List freeFind( SysFunctionCategory obj, int pageIndex, int pageSize );
	public List freeFind( SysFunctionCategory obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysFunctionCategory>  objColl);
	public void save( SysFunctionCategory vo );
	public void insert( SysFunctionCategory vo );
	public void update( SysFunctionCategory vo );
	public void removeObjectTree( SysFunctionCategory obj );
	public void remove( SysFunctionCategory vo );
	public boolean exists( SysFunctionCategory vo );
}
