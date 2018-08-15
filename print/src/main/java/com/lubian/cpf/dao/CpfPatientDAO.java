package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfPatient;

/**
 * CpfPatientDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfPatientDAO {


	public int countFindAll();
	
	public CpfPatient findByPK( CpfPatient obj );
	public List freeFind( CpfPatient obj );
	public int countFreeFind( CpfPatient obj );
	public List freeFind( CpfPatient obj, String orderByColName );
	public List freeFind( CpfPatient obj, int pageIndex, int pageSize );
	public List freeFind( CpfPatient obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfPatient>  objColl);
	public void save( CpfPatient vo );
	public void insert( CpfPatient vo );
	public void update( CpfPatient vo );
	public void removeObjectTree( CpfPatient obj );
	public void remove( CpfPatient vo );
	public boolean exists( CpfPatient vo );
}
