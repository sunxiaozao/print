package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfCaseHistoryApply;

/**
 * CpfCaseHistoryApplyDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryApplyDAO {


	public int countFindAll();
	
	public CpfCaseHistoryApply findByPK( CpfCaseHistoryApply obj );
	public List freeFind( CpfCaseHistoryApply obj );
	public int countFreeFind( CpfCaseHistoryApply obj );
	public List freeFind( CpfCaseHistoryApply obj, String orderByColName );
	public List freeFind( CpfCaseHistoryApply obj, int pageIndex, int pageSize );
	public List freeFind( CpfCaseHistoryApply obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfCaseHistoryApply>  objColl);
	public void save( CpfCaseHistoryApply vo );
	public void insert( CpfCaseHistoryApply vo );
	public void update( CpfCaseHistoryApply vo );
	public void removeObjectTree( CpfCaseHistoryApply obj );
	public void remove( CpfCaseHistoryApply vo );
	public boolean exists( CpfCaseHistoryApply vo );
}
