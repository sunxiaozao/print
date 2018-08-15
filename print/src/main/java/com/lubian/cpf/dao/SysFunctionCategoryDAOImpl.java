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
import com.lubian.cpf.dao.SysFunctionCategoryDAO;
import com.lubian.cpf.vo.SysFunctionCategory;

@Repository
public class SysFunctionCategoryDAOImpl extends BaseSqlMapDao implements SysFunctionCategoryDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_CATEGORY.CountFindAllSYS_FUNCTION_CATEGORY", null );
        return ret.intValue();
	}

	public SysFunctionCategory findByPK(SysFunctionCategory obj) {
		Object ret = queryForObject("SYS_FUNCTION_CATEGORY.FindByPKSYS_FUNCTION_CATEGORY", obj );
		if(ret !=null)
			return (SysFunctionCategory)ret;
		else 
			return null;
	}

	public List freeFind(SysFunctionCategory obj) {
		return queryForList("SYS_FUNCTION_CATEGORY.FreeFindSYS_FUNCTION_CATEGORY", obj );
	}

	public int countFreeFind(SysFunctionCategory obj) {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_CATEGORY.CountFreeFindSYS_FUNCTION_CATEGORY", obj );
        return ret.intValue();
	}

	public List freeFind(SysFunctionCategory obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_FUNCTION_CATEGORY.FreeFindSYS_FUNCTION_CATEGORY", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysFunctionCategory obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysFunctionCategory){
	    	_hashmap = ((SysFunctionCategory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_FUNCTION_CATEGORY.FreeFindSYS_FUNCTION_CATEGORYOrdered", _hashmap);
	}

	public List freeFind(SysFunctionCategory obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysFunctionCategory){
	    	_hashmap = ((SysFunctionCategory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_FUNCTION_CATEGORY.FreeFindSYS_FUNCTION_CATEGORYOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysFunctionCategory> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysFunctionCategory oneObj = (SysFunctionCategory)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysFunctionCategory vo) {
        SysFunctionCategory obj = (SysFunctionCategory) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysFunctionCategory obj) {

		obj.fixup();
        insert("SYS_FUNCTION_CATEGORY.InsertSYS_FUNCTION_CATEGORY", obj );
	}

	public void update(SysFunctionCategory obj) {

		obj.fixup();
		update( "SYS_FUNCTION_CATEGORY.UpdateSYS_FUNCTION_CATEGORY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysFunctionCategory obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_FUNCTION_CATEGORY.DeleteSYS_FUNCTION_CATEGORY", obj );

	}

	public void removeObjectTree(SysFunctionCategory obj) {

        obj.fixup();
        SysFunctionCategory oldObj = ( SysFunctionCategory )queryForObject("SYS_FUNCTION_CATEGORY.FindByPKSYS_FUNCTION_CATEGORY", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_FUNCTION_CATEGORY.DeleteSYS_FUNCTION_CATEGORY", oldObj );

	}

	public boolean exists(SysFunctionCategory vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysFunctionCategory obj = (SysFunctionCategory) vo;

        keysFilled = keysFilled && ( null != obj.getCategoryId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CATEGORY.CountSYS_FUNCTION_CATEGORY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


	public List getFirstMenuByRoleId(Object role_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		return queryForList( "SYS_FUNCTION_CATEGORY.GetFirstMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap );
	}

	public int countGetFirstMenuByRoleId(Object role_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CATEGORY.CountGetFirstMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap );
		return retInt.intValue();
	}

	public List getFirstMenuByRoleId(Object role_id , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		return getPaginatedList( "SYS_FUNCTION_CATEGORY.GetFirstMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap, pageIndex, pageSize );
	}

	public List getSecondMenuByRoleIdAndCateogryId(Object role_id,Object parent_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		_hashmap.put( "parent_id", parent_id );
		return queryForList( "SYS_FUNCTION_CATEGORY.GetSecondMenuByRoleIdAndCateogryIdSYS_FUNCTION_CATEGORY", _hashmap );
	}

	public int countGetSecondMenuByRoleIdAndCateogryId(Object role_id,Object parent_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		_hashmap.put( "parent_id", parent_id );
		Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CATEGORY.CountGetSecondMenuByRoleIdAndCateogryIdSYS_FUNCTION_CATEGORY", _hashmap );
		return retInt.intValue();
	}

	public List getSecondMenuByRoleIdAndCateogryId(Object role_id,Object parent_id , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		_hashmap.put( "parent_id", parent_id );
		return getPaginatedList( "SYS_FUNCTION_CATEGORY.GetSecondMenuByRoleIdAndCateogryIdSYS_FUNCTION_CATEGORY", _hashmap, pageIndex, pageSize );
	}

	public List getSecondMenuByRoleId(Object role_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		return queryForList( "SYS_FUNCTION_CATEGORY.GetSecondMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap );
	}

	public int countGetSecondMenuByRoleId(Object role_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CATEGORY.CountGetSecondMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap );
		return retInt.intValue();
	}

	public List getSecondMenuByRoleId(Object role_id , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		return getPaginatedList( "SYS_FUNCTION_CATEGORY.GetSecondMenuByRoleIdSYS_FUNCTION_CATEGORY", _hashmap, pageIndex, pageSize );
	}

}
