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
import com.lubian.cpf.dao.CpfCaseHistoryDAO;
import com.lubian.cpf.vo.CpfCaseHistory;

@Repository
public class CpfCaseHistoryDAOImpl extends BaseSqlMapDao implements CpfCaseHistoryDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY.CountFindAllCPF_CASE_HISTORY", null );
        return ret.intValue();
	}

	public CpfCaseHistory findByPK(CpfCaseHistory obj) {
		Object ret = queryForObject("CPF_CASE_HISTORY.FindByPKCPF_CASE_HISTORY", obj );
		if(ret !=null)
			return (CpfCaseHistory)ret;
		else 
			return null;
	}

	public List freeFind(CpfCaseHistory obj) {
		return queryForList("CPF_CASE_HISTORY.FreeFindCPF_CASE_HISTORY", obj );
	}

	public int countFreeFind(CpfCaseHistory obj) {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY.CountFreeFindCPF_CASE_HISTORY", obj );
        return ret.intValue();
	}

	public List freeFind(CpfCaseHistory obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_CASE_HISTORY.FreeFindCPF_CASE_HISTORY", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfCaseHistory obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistory){
	    	_hashmap = ((CpfCaseHistory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_CASE_HISTORY.FreeFindCPF_CASE_HISTORYOrdered", _hashmap);
	}

	public List freeFind(CpfCaseHistory obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistory){
	    	_hashmap = ((CpfCaseHistory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_CASE_HISTORY.FreeFindCPF_CASE_HISTORYOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfCaseHistory> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfCaseHistory oneObj = (CpfCaseHistory)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfCaseHistory vo) {
        CpfCaseHistory obj = (CpfCaseHistory) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfCaseHistory obj) {

		obj.fixup();
        insert("CPF_CASE_HISTORY.InsertCPF_CASE_HISTORY", obj );
	}

	public void update(CpfCaseHistory obj) {

		obj.fixup();
		update( "CPF_CASE_HISTORY.UpdateCPF_CASE_HISTORY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfCaseHistory obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_CASE_HISTORY.DeleteCPF_CASE_HISTORY", obj );

	}

	public void removeObjectTree(CpfCaseHistory obj) {

        obj.fixup();
        CpfCaseHistory oldObj = ( CpfCaseHistory )queryForObject("CPF_CASE_HISTORY.FindByPKCPF_CASE_HISTORY", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_CASE_HISTORY.DeleteCPF_CASE_HISTORY", oldObj );

	}

	public boolean exists(CpfCaseHistory vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfCaseHistory obj = (CpfCaseHistory) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_CASE_HISTORY.CountCPF_CASE_HISTORY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


	public List getDistinctHospitalCount(Object patientId,Object createTime){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "patientId", patientId );
		_hashmap.put( "createTime", createTime );
		return queryForList( "CPF_CASE_HISTORY.GetDistinctHospitalCountCPF_CASE_HISTORY", _hashmap );
	}

	public int countGetDistinctHospitalCount(Object patientId,Object createTime){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "patientId", patientId );
		_hashmap.put( "createTime", createTime );
		Integer retInt=(Integer)queryForObject("CPF_CASE_HISTORY.CountGetDistinctHospitalCountCPF_CASE_HISTORY", _hashmap );
		return retInt.intValue();
	}

	public List getDistinctHospitalCount(Object patientId,Object createTime , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "patientId", patientId );
		_hashmap.put( "createTime", createTime );
		return getPaginatedList( "CPF_CASE_HISTORY.GetDistinctHospitalCountCPF_CASE_HISTORY", _hashmap, pageIndex, pageSize );
	}

	public int updateRelateStatus(Object relateStatus,Object patientId,Object folderId,Object idStr){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "relateStatus", relateStatus );
		_hashmap.put( "patientId", patientId );
		_hashmap.put( "folderId", folderId );
		_hashmap.put( "idStr", idStr );
		int retInt=update( "CPF_CASE_HISTORY.UpdateRelateStatusCPF_CASE_HISTORY", _hashmap );
		return retInt;
	}

}
