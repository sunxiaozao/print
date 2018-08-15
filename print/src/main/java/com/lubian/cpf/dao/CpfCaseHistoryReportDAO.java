package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfCaseHistoryReport;

/**
 * CpfCaseHistoryReportDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryReportDAO {


	public int countFindAll();
	
	public CpfCaseHistoryReport findByPK( CpfCaseHistoryReport obj );
	public List freeFind( CpfCaseHistoryReport obj );
	public int countFreeFind( CpfCaseHistoryReport obj );
	public List freeFind( CpfCaseHistoryReport obj, String orderByColName );
	public List freeFind( CpfCaseHistoryReport obj, int pageIndex, int pageSize );
	public List freeFind( CpfCaseHistoryReport obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfCaseHistoryReport>  objColl);
	public void save( CpfCaseHistoryReport vo );
	public void insert( CpfCaseHistoryReport vo );
	public void update( CpfCaseHistoryReport vo );
	public void removeObjectTree( CpfCaseHistoryReport obj );
	public void remove( CpfCaseHistoryReport vo );
	public boolean exists( CpfCaseHistoryReport vo );
}
