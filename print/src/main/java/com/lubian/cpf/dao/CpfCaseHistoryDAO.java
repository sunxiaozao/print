package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfCaseHistory;

/**
 * CpfCaseHistoryDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryDAO {


	public List getDistinctHospitalCount(Object patientId,Object createTime);
	public int countGetDistinctHospitalCount(Object patientId,Object createTime);
	public List getDistinctHospitalCount(Object patientId,Object createTime , int pageIndex, int pageSize);

	public int updateRelateStatus(Object relateStatus,Object patientId,Object folderId,Object idStr);

	public int countFindAll();
	
	public CpfCaseHistory findByPK( CpfCaseHistory obj );
	public List freeFind( CpfCaseHistory obj );
	public int countFreeFind( CpfCaseHistory obj );
	public List freeFind( CpfCaseHistory obj, String orderByColName );
	public List freeFind( CpfCaseHistory obj, int pageIndex, int pageSize );
	public List freeFind( CpfCaseHistory obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfCaseHistory>  objColl);
	public void save( CpfCaseHistory vo );
	public void insert( CpfCaseHistory vo );
	public void update( CpfCaseHistory vo );
	public void removeObjectTree( CpfCaseHistory obj );
	public void remove( CpfCaseHistory vo );
	public boolean exists( CpfCaseHistory vo );
}
