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
import com.lubian.cpf.dao.SysHospitalDAO;
import com.lubian.cpf.vo.SysHospital;

@Repository
public class SysHospitalDAOImpl extends BaseSqlMapDao implements SysHospitalDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_HOSPITAL.CountFindAllSYS_HOSPITAL", null );
        return ret.intValue();
	}

	public SysHospital findByPK(SysHospital obj) {
		Object ret = queryForObject("SYS_HOSPITAL.FindByPKSYS_HOSPITAL", obj );
		if(ret !=null)
			return (SysHospital)ret;
		else 
			return null;
	}

	public List freeFind(SysHospital obj) {
		return queryForList("SYS_HOSPITAL.FreeFindSYS_HOSPITAL", obj );
	}

	public int countFreeFind(SysHospital obj) {
        Integer ret = (Integer) queryForObject("SYS_HOSPITAL.CountFreeFindSYS_HOSPITAL", obj );
        return ret.intValue();
	}

	public List freeFind(SysHospital obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_HOSPITAL.FreeFindSYS_HOSPITAL", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysHospital obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysHospital){
	    	_hashmap = ((SysHospital)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_HOSPITAL.FreeFindSYS_HOSPITALOrdered", _hashmap);
	}

	public List freeFind(SysHospital obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysHospital){
	    	_hashmap = ((SysHospital)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_HOSPITAL.FreeFindSYS_HOSPITALOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysHospital> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysHospital oneObj = (SysHospital)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysHospital vo) {
        SysHospital obj = (SysHospital) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysHospital obj) {

		obj.fixup();
        insert("SYS_HOSPITAL.InsertSYS_HOSPITAL", obj );
	}

	public void update(SysHospital obj) {

		obj.fixup();
		update( "SYS_HOSPITAL.UpdateSYS_HOSPITAL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysHospital obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_HOSPITAL.DeleteSYS_HOSPITAL", obj );

	}

	public void removeObjectTree(SysHospital obj) {

        obj.fixup();
        SysHospital oldObj = ( SysHospital )queryForObject("SYS_HOSPITAL.FindByPKSYS_HOSPITAL", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_HOSPITAL.DeleteSYS_HOSPITAL", oldObj );

	}

	public boolean exists(SysHospital vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysHospital obj = (SysHospital) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_HOSPITAL.CountSYS_HOSPITAL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


	public List searchByIdOrParentId(Object id,Object parentId){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "id", id );
		_hashmap.put( "parentId", parentId );
		return queryForList( "SYS_HOSPITAL.SearchByIdOrParentIdSYS_HOSPITAL", _hashmap );
	}

	public int countSearchByIdOrParentId(Object id,Object parentId){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "id", id );
		_hashmap.put( "parentId", parentId );
		Integer retInt=(Integer)queryForObject("SYS_HOSPITAL.CountSearchByIdOrParentIdSYS_HOSPITAL", _hashmap );
		return retInt.intValue();
	}

	public List searchByIdOrParentId(Object id,Object parentId , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "id", id );
		_hashmap.put( "parentId", parentId );
		return getPaginatedList( "SYS_HOSPITAL.SearchByIdOrParentIdSYS_HOSPITAL", _hashmap, pageIndex, pageSize );
	}

}
