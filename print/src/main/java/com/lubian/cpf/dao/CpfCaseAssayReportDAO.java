package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfCaseAssayReport;

/**
 * CpfCaseAssayReportDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseAssayReportDAO {


	public int countFindAll();
	
	public CpfCaseAssayReport findByPK( CpfCaseAssayReport obj );
	public List freeFind( CpfCaseAssayReport obj );
	public int countFreeFind( CpfCaseAssayReport obj );
	public List freeFind( CpfCaseAssayReport obj, String orderByColName );
	public List freeFind( CpfCaseAssayReport obj, int pageIndex, int pageSize );
	public List freeFind( CpfCaseAssayReport obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfCaseAssayReport>  objColl);
	public void save( CpfCaseAssayReport vo );
	public void insert( CpfCaseAssayReport vo );
	public void update( CpfCaseAssayReport vo );
	public void removeObjectTree( CpfCaseAssayReport obj );
	public void remove( CpfCaseAssayReport vo );
	public boolean exists( CpfCaseAssayReport vo );
}
