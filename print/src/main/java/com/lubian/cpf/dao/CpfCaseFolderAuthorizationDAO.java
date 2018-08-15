package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfCaseFolderAuthorization;

/**
 * CpfCaseFolderAuthorizationDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseFolderAuthorizationDAO {


	public List conditionFind(Object folderId,Object hospitalId,Object departmentId,Object doctor,Object patientId_instr,Object addSQL);
	public int countConditionFind(Object folderId,Object hospitalId,Object departmentId,Object doctor,Object patientId_instr,Object addSQL);
	public List conditionFind(Object folderId,Object hospitalId,Object departmentId,Object doctor,Object patientId_instr,Object addSQL , int pageIndex, int pageSize);

	public int countFindAll();
	
	public CpfCaseFolderAuthorization findByPK( CpfCaseFolderAuthorization obj );
	public List freeFind( CpfCaseFolderAuthorization obj );
	public int countFreeFind( CpfCaseFolderAuthorization obj );
	public List freeFind( CpfCaseFolderAuthorization obj, String orderByColName );
	public List freeFind( CpfCaseFolderAuthorization obj, int pageIndex, int pageSize );
	public List freeFind( CpfCaseFolderAuthorization obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfCaseFolderAuthorization>  objColl);
	public void save( CpfCaseFolderAuthorization vo );
	public void insert( CpfCaseFolderAuthorization vo );
	public void update( CpfCaseFolderAuthorization vo );
	public void removeObjectTree( CpfCaseFolderAuthorization obj );
	public void remove( CpfCaseFolderAuthorization vo );
	public boolean exists( CpfCaseFolderAuthorization vo );
}
