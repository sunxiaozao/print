package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CdfNotebook;

/**
 * CdfNotebookDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CdfNotebookDAO {


	public int countFindAll();
	
	public CdfNotebook findByPK( CdfNotebook obj );
	public List freeFind( CdfNotebook obj );
	public int countFreeFind( CdfNotebook obj );
	public List freeFind( CdfNotebook obj, String orderByColName );
	public List freeFind( CdfNotebook obj, int pageIndex, int pageSize );
	public List freeFind( CdfNotebook obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CdfNotebook>  objColl);
	public void save( CdfNotebook vo );
	public void insert( CdfNotebook vo );
	public void update( CdfNotebook vo );
	public void removeObjectTree( CdfNotebook obj );
	public void remove( CdfNotebook vo );
	public boolean exists( CdfNotebook vo );
}
