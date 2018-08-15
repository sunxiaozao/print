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
import com.lubian.cpf.dao.CpfCaseAssayReportDAO;
import com.lubian.cpf.vo.CpfCaseAssayReport;

@Repository
public class CpfCaseAssayReportDAOImpl extends BaseSqlMapDao implements CpfCaseAssayReportDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_CASE_ASSAY_REPORT.CountFindAllCPF_CASE_ASSAY_REPORT", null );
        return ret.intValue();
	}

	public CpfCaseAssayReport findByPK(CpfCaseAssayReport obj) {
		Object ret = queryForObject("CPF_CASE_ASSAY_REPORT.FindByPKCPF_CASE_ASSAY_REPORT", obj );
		if(ret !=null)
			return (CpfCaseAssayReport)ret;
		else 
			return null;
	}

	public List freeFind(CpfCaseAssayReport obj) {
		return queryForList("CPF_CASE_ASSAY_REPORT.FreeFindCPF_CASE_ASSAY_REPORT", obj );
	}

	public int countFreeFind(CpfCaseAssayReport obj) {
        Integer ret = (Integer) queryForObject("CPF_CASE_ASSAY_REPORT.CountFreeFindCPF_CASE_ASSAY_REPORT", obj );
        return ret.intValue();
	}

	public List freeFind(CpfCaseAssayReport obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_CASE_ASSAY_REPORT.FreeFindCPF_CASE_ASSAY_REPORT", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfCaseAssayReport obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseAssayReport){
	    	_hashmap = ((CpfCaseAssayReport)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_CASE_ASSAY_REPORT.FreeFindCPF_CASE_ASSAY_REPORTOrdered", _hashmap);
	}

	public List freeFind(CpfCaseAssayReport obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseAssayReport){
	    	_hashmap = ((CpfCaseAssayReport)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_CASE_ASSAY_REPORT.FreeFindCPF_CASE_ASSAY_REPORTOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfCaseAssayReport> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfCaseAssayReport oneObj = (CpfCaseAssayReport)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfCaseAssayReport vo) {
        CpfCaseAssayReport obj = (CpfCaseAssayReport) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfCaseAssayReport obj) {

		obj.fixup();
        insert("CPF_CASE_ASSAY_REPORT.InsertCPF_CASE_ASSAY_REPORT", obj );
	}

	public void update(CpfCaseAssayReport obj) {

		obj.fixup();
		update( "CPF_CASE_ASSAY_REPORT.UpdateCPF_CASE_ASSAY_REPORT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfCaseAssayReport obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_CASE_ASSAY_REPORT.DeleteCPF_CASE_ASSAY_REPORT", obj );

	}

	public void removeObjectTree(CpfCaseAssayReport obj) {

        obj.fixup();
        CpfCaseAssayReport oldObj = ( CpfCaseAssayReport )queryForObject("CPF_CASE_ASSAY_REPORT.FindByPKCPF_CASE_ASSAY_REPORT", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_CASE_ASSAY_REPORT.DeleteCPF_CASE_ASSAY_REPORT", oldObj );

	}

	public boolean exists(CpfCaseAssayReport vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfCaseAssayReport obj = (CpfCaseAssayReport) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_CASE_ASSAY_REPORT.CountCPF_CASE_ASSAY_REPORT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
