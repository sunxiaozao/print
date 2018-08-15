package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysDepartment;

/**
 * SysDepartmentDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysDepartmentDAO {


	public int countFindAll();
	
	public SysDepartment findByPK( SysDepartment obj );
	public List freeFind( SysDepartment obj );
	public int countFreeFind( SysDepartment obj );
	public List freeFind( SysDepartment obj, String orderByColName );
	public List freeFind( SysDepartment obj, int pageIndex, int pageSize );
	public List freeFind( SysDepartment obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysDepartment>  objColl);
	public void save( SysDepartment vo );
	public void insert( SysDepartment vo );
	public void update( SysDepartment vo );
	public void removeObjectTree( SysDepartment obj );
	public void remove( SysDepartment vo );
	public boolean exists( SysDepartment vo );
}
