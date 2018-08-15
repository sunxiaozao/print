package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfCaseHistoryOthers;

/**
 * CpfCaseHistoryOthersDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryOthersDAO {


	public int countFindAll();
	
	public CpfCaseHistoryOthers findByPK( CpfCaseHistoryOthers obj );
	public List freeFind( CpfCaseHistoryOthers obj );
	public int countFreeFind( CpfCaseHistoryOthers obj );
	public List freeFind( CpfCaseHistoryOthers obj, String orderByColName );
	public List freeFind( CpfCaseHistoryOthers obj, int pageIndex, int pageSize );
	public List freeFind( CpfCaseHistoryOthers obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfCaseHistoryOthers>  objColl);
	public void save( CpfCaseHistoryOthers vo );
	public void insert( CpfCaseHistoryOthers vo );
	public void update( CpfCaseHistoryOthers vo );
	public void removeObjectTree( CpfCaseHistoryOthers obj );
	public void remove( CpfCaseHistoryOthers vo );
	public boolean exists( CpfCaseHistoryOthers vo );
}
