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
import com.lubian.cpf.dao.SysDoctorDAO;
import com.lubian.cpf.vo.SysDoctor;

@Repository
public class SysDoctorDAOImpl extends BaseSqlMapDao implements SysDoctorDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_DOCTOR.CountFindAllSYS_DOCTOR", null );
        return ret.intValue();
	}

	public SysDoctor findByPK(SysDoctor obj) {
		Object ret = queryForObject("SYS_DOCTOR.FindByPKSYS_DOCTOR", obj );
		if(ret !=null)
			return (SysDoctor)ret;
		else 
			return null;
	}

	public List freeFind(SysDoctor obj) {
		return queryForList("SYS_DOCTOR.FreeFindSYS_DOCTOR", obj );
	}

	public int countFreeFind(SysDoctor obj) {
        Integer ret = (Integer) queryForObject("SYS_DOCTOR.CountFreeFindSYS_DOCTOR", obj );
        return ret.intValue();
	}

	public List freeFind(SysDoctor obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_DOCTOR.FreeFindSYS_DOCTOR", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysDoctor obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysDoctor){
	    	_hashmap = ((SysDoctor)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_DOCTOR.FreeFindSYS_DOCTOROrdered", _hashmap);
	}

	public List freeFind(SysDoctor obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysDoctor){
	    	_hashmap = ((SysDoctor)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_DOCTOR.FreeFindSYS_DOCTOROrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysDoctor> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysDoctor oneObj = (SysDoctor)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysDoctor vo) {
        SysDoctor obj = (SysDoctor) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysDoctor obj) {

		obj.fixup();
        insert("SYS_DOCTOR.InsertSYS_DOCTOR", obj );
	}

	public void update(SysDoctor obj) {

		obj.fixup();
		update( "SYS_DOCTOR.UpdateSYS_DOCTOR", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysDoctor obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_DOCTOR.DeleteSYS_DOCTOR", obj );

	}

	public void removeObjectTree(SysDoctor obj) {

        obj.fixup();
        SysDoctor oldObj = ( SysDoctor )queryForObject("SYS_DOCTOR.FindByPKSYS_DOCTOR", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_DOCTOR.DeleteSYS_DOCTOR", oldObj );

	}

	public boolean exists(SysDoctor vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysDoctor obj = (SysDoctor) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_DOCTOR.CountSYS_DOCTOR", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


	public List idNotInSearch(Object idStr){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "idStr", idStr );
		return queryForList( "SYS_DOCTOR.IdNotInSearchSYS_DOCTOR", _hashmap );
	}

	public int countIdNotInSearch(Object idStr){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "idStr", idStr );
		Integer retInt=(Integer)queryForObject("SYS_DOCTOR.CountIdNotInSearchSYS_DOCTOR", _hashmap );
		return retInt.intValue();
	}

	public List idNotInSearch(Object idStr , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "idStr", idStr );
		return getPaginatedList( "SYS_DOCTOR.IdNotInSearchSYS_DOCTOR", _hashmap, pageIndex, pageSize );
	}

}
