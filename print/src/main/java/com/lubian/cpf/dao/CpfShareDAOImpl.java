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
import com.lubian.cpf.dao.CpfShareDAO;
import com.lubian.cpf.vo.CpfShare;

@Repository
public class CpfShareDAOImpl extends BaseSqlMapDao implements CpfShareDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_SHARE.CountFindAllCPF_SHARE", null );
        return ret.intValue();
	}

	public CpfShare findByPK(CpfShare obj) {
		Object ret = queryForObject("CPF_SHARE.FindByPKCPF_SHARE", obj );
		if(ret !=null)
			return (CpfShare)ret;
		else 
			return null;
	}

	public List freeFind(CpfShare obj) {
		return queryForList("CPF_SHARE.FreeFindCPF_SHARE", obj );
	}

	public int countFreeFind(CpfShare obj) {
        Integer ret = (Integer) queryForObject("CPF_SHARE.CountFreeFindCPF_SHARE", obj );
        return ret.intValue();
	}

	public List freeFind(CpfShare obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_SHARE.FreeFindCPF_SHARE", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfShare obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfShare){
	    	_hashmap = ((CpfShare)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_SHARE.FreeFindCPF_SHAREOrdered", _hashmap);
	}

	public List freeFind(CpfShare obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfShare){
	    	_hashmap = ((CpfShare)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_SHARE.FreeFindCPF_SHAREOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfShare> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfShare oneObj = (CpfShare)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfShare vo) {
        CpfShare obj = (CpfShare) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfShare obj) {

		obj.fixup();
        insert("CPF_SHARE.InsertCPF_SHARE", obj );
	}

	public void update(CpfShare obj) {

		obj.fixup();
		update( "CPF_SHARE.UpdateCPF_SHARE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfShare obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_SHARE.DeleteCPF_SHARE", obj );

	}

	public void removeObjectTree(CpfShare obj) {

        obj.fixup();
        CpfShare oldObj = ( CpfShare )queryForObject("CPF_SHARE.FindByPKCPF_SHARE", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_SHARE.DeleteCPF_SHARE", oldObj );

	}

	public boolean exists(CpfShare vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfShare obj = (CpfShare) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_SHARE.CountCPF_SHARE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


	public List searchShareByEmailMobile(Object email,Object mobile,Object status){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "email", email );
		_hashmap.put( "mobile", mobile );
		_hashmap.put( "status", status );
		return queryForList( "CPF_SHARE.SearchShareByEmailMobileCPF_SHARE", _hashmap );
	}

	public int countSearchShareByEmailMobile(Object email,Object mobile,Object status){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "email", email );
		_hashmap.put( "mobile", mobile );
		_hashmap.put( "status", status );
		Integer retInt=(Integer)queryForObject("CPF_SHARE.CountSearchShareByEmailMobileCPF_SHARE", _hashmap );
		return retInt.intValue();
	}

	public List searchShareByEmailMobile(Object email,Object mobile,Object status , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "email", email );
		_hashmap.put( "mobile", mobile );
		_hashmap.put( "status", status );
		return getPaginatedList( "CPF_SHARE.SearchShareByEmailMobileCPF_SHARE", _hashmap, pageIndex, pageSize );
	}

	public List searchByIdStr(Object idStr,Object status){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "idStr", idStr );
		_hashmap.put( "status", status );
		return queryForList( "CPF_SHARE.SearchByIdStrCPF_SHARE", _hashmap );
	}

	public int countSearchByIdStr(Object idStr,Object status){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "idStr", idStr );
		_hashmap.put( "status", status );
		Integer retInt=(Integer)queryForObject("CPF_SHARE.CountSearchByIdStrCPF_SHARE", _hashmap );
		return retInt.intValue();
	}

	public List searchByIdStr(Object idStr,Object status , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "idStr", idStr );
		_hashmap.put( "status", status );
		return getPaginatedList( "CPF_SHARE.SearchByIdStrCPF_SHARE", _hashmap, pageIndex, pageSize );
	}

	public List searchByEmailOrMobile(Object emailStr,Object mobileStr,Object status){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "emailStr", emailStr );
		_hashmap.put( "mobileStr", mobileStr );
		_hashmap.put( "status", status );
		return queryForList( "CPF_SHARE.SearchByEmailOrMobileCPF_SHARE", _hashmap );
	}

	public int countSearchByEmailOrMobile(Object emailStr,Object mobileStr,Object status){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "emailStr", emailStr );
		_hashmap.put( "mobileStr", mobileStr );
		_hashmap.put( "status", status );
		Integer retInt=(Integer)queryForObject("CPF_SHARE.CountSearchByEmailOrMobileCPF_SHARE", _hashmap );
		return retInt.intValue();
	}

	public List searchByEmailOrMobile(Object emailStr,Object mobileStr,Object status , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "emailStr", emailStr );
		_hashmap.put( "mobileStr", mobileStr );
		_hashmap.put( "status", status );
		return getPaginatedList( "CPF_SHARE.SearchByEmailOrMobileCPF_SHARE", _hashmap, pageIndex, pageSize );
	}

}
