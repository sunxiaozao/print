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
import com.lubian.cpf.dao.CpfCaseHistoryApplyDAO;
import com.lubian.cpf.vo.CpfCaseHistoryApply;

@Repository
public class CpfCaseHistoryApplyDAOImpl extends BaseSqlMapDao implements CpfCaseHistoryApplyDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY_APPLY.CountFindAllCPF_CASE_HISTORY_APPLY", null );
        return ret.intValue();
	}

	public CpfCaseHistoryApply findByPK(CpfCaseHistoryApply obj) {
		Object ret = queryForObject("CPF_CASE_HISTORY_APPLY.FindByPKCPF_CASE_HISTORY_APPLY", obj );
		if(ret !=null)
			return (CpfCaseHistoryApply)ret;
		else 
			return null;
	}

	public List freeFind(CpfCaseHistoryApply obj) {
		return queryForList("CPF_CASE_HISTORY_APPLY.FreeFindCPF_CASE_HISTORY_APPLY", obj );
	}

	public int countFreeFind(CpfCaseHistoryApply obj) {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY_APPLY.CountFreeFindCPF_CASE_HISTORY_APPLY", obj );
        return ret.intValue();
	}

	public List freeFind(CpfCaseHistoryApply obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_CASE_HISTORY_APPLY.FreeFindCPF_CASE_HISTORY_APPLY", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfCaseHistoryApply obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistoryApply){
	    	_hashmap = ((CpfCaseHistoryApply)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_CASE_HISTORY_APPLY.FreeFindCPF_CASE_HISTORY_APPLYOrdered", _hashmap);
	}

	public List freeFind(CpfCaseHistoryApply obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistoryApply){
	    	_hashmap = ((CpfCaseHistoryApply)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_CASE_HISTORY_APPLY.FreeFindCPF_CASE_HISTORY_APPLYOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfCaseHistoryApply> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfCaseHistoryApply oneObj = (CpfCaseHistoryApply)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfCaseHistoryApply vo) {
        CpfCaseHistoryApply obj = (CpfCaseHistoryApply) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfCaseHistoryApply obj) {

		obj.fixup();
        insert("CPF_CASE_HISTORY_APPLY.InsertCPF_CASE_HISTORY_APPLY", obj );
	}

	public void update(CpfCaseHistoryApply obj) {

		obj.fixup();
		update( "CPF_CASE_HISTORY_APPLY.UpdateCPF_CASE_HISTORY_APPLY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfCaseHistoryApply obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_CASE_HISTORY_APPLY.DeleteCPF_CASE_HISTORY_APPLY", obj );

	}

	public void removeObjectTree(CpfCaseHistoryApply obj) {

        obj.fixup();
        CpfCaseHistoryApply oldObj = ( CpfCaseHistoryApply )queryForObject("CPF_CASE_HISTORY_APPLY.FindByPKCPF_CASE_HISTORY_APPLY", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_CASE_HISTORY_APPLY.DeleteCPF_CASE_HISTORY_APPLY", oldObj );

	}

	public boolean exists(CpfCaseHistoryApply vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfCaseHistoryApply obj = (CpfCaseHistoryApply) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_CASE_HISTORY_APPLY.CountCPF_CASE_HISTORY_APPLY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
