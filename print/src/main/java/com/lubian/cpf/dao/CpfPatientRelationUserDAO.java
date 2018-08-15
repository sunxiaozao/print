package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfPatientRelationUser;

/**
 * CpfPatientRelationUserDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfPatientRelationUserDAO {


	public int countFindAll();
	
	public CpfPatientRelationUser findByPK( CpfPatientRelationUser obj );
	public List freeFind( CpfPatientRelationUser obj );
	public int countFreeFind( CpfPatientRelationUser obj );
	public List freeFind( CpfPatientRelationUser obj, String orderByColName );
	public List freeFind( CpfPatientRelationUser obj, int pageIndex, int pageSize );
	public List freeFind( CpfPatientRelationUser obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfPatientRelationUser>  objColl);
	public void save( CpfPatientRelationUser vo );
	public void insert( CpfPatientRelationUser vo );
	public void update( CpfPatientRelationUser vo );
	public void removeObjectTree( CpfPatientRelationUser obj );
	public void remove( CpfPatientRelationUser vo );
	public boolean exists( CpfPatientRelationUser vo );
}
