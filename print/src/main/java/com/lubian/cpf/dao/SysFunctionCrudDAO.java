package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysFunctionCrud;

/**
 * SysFunctionCrudDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysFunctionCrudDAO {


	public int countFindAll();
	
	public SysFunctionCrud findByPK( SysFunctionCrud obj );
	public List freeFind( SysFunctionCrud obj );
	public int countFreeFind( SysFunctionCrud obj );
	public List freeFind( SysFunctionCrud obj, String orderByColName );
	public List freeFind( SysFunctionCrud obj, int pageIndex, int pageSize );
	public List freeFind( SysFunctionCrud obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysFunctionCrud>  objColl);
	public void save( SysFunctionCrud vo );
	public void insert( SysFunctionCrud vo );
	public void update( SysFunctionCrud vo );
	public void removeObjectTree( SysFunctionCrud obj );
	public void remove( SysFunctionCrud vo );
	public boolean exists( SysFunctionCrud vo );
}
