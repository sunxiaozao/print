package com.lubian.cpf.dao;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.lubian.cpf.common.BaseSqlMapDao;
import com.lubian.cpf.dao.CpfCaseHistoryReportDAO;
import com.lubian.cpf.vo.CpfCaseHistoryReport;

@Repository
public class CpfCaseHistoryReportDAOImpl extends BaseSqlMapDao implements CpfCaseHistoryReportDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY_REPORT.CountFindAllCPF_CASE_HISTORY_REPORT", null );
        return ret.intValue();
	}

	public CpfCaseHistoryReport findByPK(CpfCaseHistoryReport obj) {
		Object ret = queryForObject("CPF_CASE_HISTORY_REPORT.FindByPKCPF_CASE_HISTORY_REPORT", obj );
		if(ret !=null)
			return (CpfCaseHistoryReport)ret;
		else 
			return null;
	}

	public List freeFind(CpfCaseHistoryReport obj) {
		return queryForList("CPF_CASE_HISTORY_REPORT.FreeFindCPF_CASE_HISTORY_REPORT", obj );
	}

	public int countFreeFind(CpfCaseHistoryReport obj) {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY_REPORT.CountFreeFindCPF_CASE_HISTORY_REPORT", obj );
        return ret.intValue();
	}

	public List freeFind(CpfCaseHistoryReport obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_CASE_HISTORY_REPORT.FreeFindCPF_CASE_HISTORY_REPORT", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfCaseHistoryReport obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistoryReport){
	    	_hashmap = ((CpfCaseHistoryReport)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_CASE_HISTORY_REPORT.FreeFindCPF_CASE_HISTORY_REPORTOrdered", _hashmap);
	}

	public List freeFind(CpfCaseHistoryReport obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistoryReport){
	    	_hashmap = ((CpfCaseHistoryReport)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_CASE_HISTORY_REPORT.FreeFindCPF_CASE_HISTORY_REPORTOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfCaseHistoryReport> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfCaseHistoryReport oneObj = (CpfCaseHistoryReport)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfCaseHistoryReport vo) {
        CpfCaseHistoryReport obj = (CpfCaseHistoryReport) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfCaseHistoryReport obj) {

		obj.fixup();
        insert("CPF_CASE_HISTORY_REPORT.InsertCPF_CASE_HISTORY_REPORT", obj );
	}

	public void update(CpfCaseHistoryReport obj) {

		obj.fixup();
		update( "CPF_CASE_HISTORY_REPORT.UpdateCPF_CASE_HISTORY_REPORT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfCaseHistoryReport obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_CASE_HISTORY_REPORT.DeleteCPF_CASE_HISTORY_REPORT", obj );

	}

	public void removeObjectTree(CpfCaseHistoryReport obj) {

        obj.fixup();
        CpfCaseHistoryReport oldObj = ( CpfCaseHistoryReport )queryForObject("CPF_CASE_HISTORY_REPORT.FindByPKCPF_CASE_HISTORY_REPORT", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_CASE_HISTORY_REPORT.DeleteCPF_CASE_HISTORY_REPORT", oldObj );

	}

	public boolean exists(CpfCaseHistoryReport vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfCaseHistoryReport obj = (CpfCaseHistoryReport) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_CASE_HISTORY_REPORT.CountCPF_CASE_HISTORY_REPORT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
