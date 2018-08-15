package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfCaseFolder;

/**
 * CpfCaseFolderDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseFolderDAO {


	public int countFindAll();
	
	public CpfCaseFolder findByPK( CpfCaseFolder obj );
	public List freeFind( CpfCaseFolder obj );
	public int countFreeFind( CpfCaseFolder obj );
	public List freeFind( CpfCaseFolder obj, String orderByColName );
	public List freeFind( CpfCaseFolder obj, int pageIndex, int pageSize );
	public List freeFind( CpfCaseFolder obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfCaseFolder>  objColl);
	public void save( CpfCaseFolder vo );
	public void insert( CpfCaseFolder vo );
	public void update( CpfCaseFolder vo );
	public void removeObjectTree( CpfCaseFolder obj );
	public void remove( CpfCaseFolder vo );
	public boolean exists( CpfCaseFolder vo );
}
