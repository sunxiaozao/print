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
import com.lubian.cpf.dao.CpfCaseHistoryOthersDAO;
import com.lubian.cpf.vo.CpfCaseHistoryOthers;

@Repository
public class CpfCaseHistoryOthersDAOImpl extends BaseSqlMapDao implements CpfCaseHistoryOthersDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY_OTHERS.CountFindAllCPF_CASE_HISTORY_OTHERS", null );
        return ret.intValue();
	}

	public CpfCaseHistoryOthers findByPK(CpfCaseHistoryOthers obj) {
		Object ret = queryForObject("CPF_CASE_HISTORY_OTHERS.FindByPKCPF_CASE_HISTORY_OTHERS", obj );
		if(ret !=null)
			return (CpfCaseHistoryOthers)ret;
		else 
			return null;
	}

	public List freeFind(CpfCaseHistoryOthers obj) {
		return queryForList("CPF_CASE_HISTORY_OTHERS.FreeFindCPF_CASE_HISTORY_OTHERS", obj );
	}

	public int countFreeFind(CpfCaseHistoryOthers obj) {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY_OTHERS.CountFreeFindCPF_CASE_HISTORY_OTHERS", obj );
        return ret.intValue();
	}

	public List freeFind(CpfCaseHistoryOthers obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_CASE_HISTORY_OTHERS.FreeFindCPF_CASE_HISTORY_OTHERS", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfCaseHistoryOthers obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistoryOthers){
	    	_hashmap = ((CpfCaseHistoryOthers)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_CASE_HISTORY_OTHERS.FreeFindCPF_CASE_HISTORY_OTHERSOrdered", _hashmap);
	}

	public List freeFind(CpfCaseHistoryOthers obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistoryOthers){
	    	_hashmap = ((CpfCaseHistoryOthers)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_CASE_HISTORY_OTHERS.FreeFindCPF_CASE_HISTORY_OTHERSOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfCaseHistoryOthers> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfCaseHistoryOthers oneObj = (CpfCaseHistoryOthers)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfCaseHistoryOthers vo) {
        CpfCaseHistoryOthers obj = (CpfCaseHistoryOthers) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfCaseHistoryOthers obj) {

		obj.fixup();
        insert("CPF_CASE_HISTORY_OTHERS.InsertCPF_CASE_HISTORY_OTHERS", obj );
	}

	public void update(CpfCaseHistoryOthers obj) {

		obj.fixup();
		update( "CPF_CASE_HISTORY_OTHERS.UpdateCPF_CASE_HISTORY_OTHERS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfCaseHistoryOthers obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_CASE_HISTORY_OTHERS.DeleteCPF_CASE_HISTORY_OTHERS", obj );

	}

	public void removeObjectTree(CpfCaseHistoryOthers obj) {

        obj.fixup();
        CpfCaseHistoryOthers oldObj = ( CpfCaseHistoryOthers )queryForObject("CPF_CASE_HISTORY_OTHERS.FindByPKCPF_CASE_HISTORY_OTHERS", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_CASE_HISTORY_OTHERS.DeleteCPF_CASE_HISTORY_OTHERS", oldObj );

	}

	public boolean exists(CpfCaseHistoryOthers vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfCaseHistoryOthers obj = (CpfCaseHistoryOthers) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_CASE_HISTORY_OTHERS.CountCPF_CASE_HISTORY_OTHERS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
