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
import com.lubian.cpf.dao.SysRelRoleFuncDAO;
import com.lubian.cpf.vo.SysRelRoleFunc;

@Repository
public class SysRelRoleFuncDAOImpl extends BaseSqlMapDao implements SysRelRoleFuncDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_REL_ROLE_FUNC.CountFindAllSYS_REL_ROLE_FUNC", null );
        return ret.intValue();
	}

	public SysRelRoleFunc findByPK(SysRelRoleFunc obj) {
		Object ret = queryForObject("SYS_REL_ROLE_FUNC.FindByPKSYS_REL_ROLE_FUNC", obj );
		if(ret !=null)
			return (SysRelRoleFunc)ret;
		else 
			return null;
	}

	public List freeFind(SysRelRoleFunc obj) {
		return queryForList("SYS_REL_ROLE_FUNC.FreeFindSYS_REL_ROLE_FUNC", obj );
	}

	public int countFreeFind(SysRelRoleFunc obj) {
        Integer ret = (Integer) queryForObject("SYS_REL_ROLE_FUNC.CountFreeFindSYS_REL_ROLE_FUNC", obj );
        return ret.intValue();
	}

	public List freeFind(SysRelRoleFunc obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_REL_ROLE_FUNC.FreeFindSYS_REL_ROLE_FUNC", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysRelRoleFunc obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysRelRoleFunc){
	    	_hashmap = ((SysRelRoleFunc)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_REL_ROLE_FUNC.FreeFindSYS_REL_ROLE_FUNCOrdered", _hashmap);
	}

	public List freeFind(SysRelRoleFunc obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysRelRoleFunc){
	    	_hashmap = ((SysRelRoleFunc)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_REL_ROLE_FUNC.FreeFindSYS_REL_ROLE_FUNCOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysRelRoleFunc> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysRelRoleFunc oneObj = (SysRelRoleFunc)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysRelRoleFunc vo) {
        SysRelRoleFunc obj = (SysRelRoleFunc) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysRelRoleFunc obj) {

		obj.fixup();
        insert("SYS_REL_ROLE_FUNC.InsertSYS_REL_ROLE_FUNC", obj );
	}

	public void update(SysRelRoleFunc obj) {

		obj.fixup();
		update( "SYS_REL_ROLE_FUNC.UpdateSYS_REL_ROLE_FUNC", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysRelRoleFunc obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_REL_ROLE_FUNC.DeleteSYS_REL_ROLE_FUNC", obj );

	}

	public void removeObjectTree(SysRelRoleFunc obj) {

        obj.fixup();
        SysRelRoleFunc oldObj = ( SysRelRoleFunc )queryForObject("SYS_REL_ROLE_FUNC.FindByPKSYS_REL_ROLE_FUNC", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_REL_ROLE_FUNC.DeleteSYS_REL_ROLE_FUNC", oldObj );

	}

	public boolean exists(SysRelRoleFunc vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysRelRoleFunc obj = (SysRelRoleFunc) vo;

        keysFilled = keysFilled && ( null != obj.getRoleFuncId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_REL_ROLE_FUNC.CountSYS_REL_ROLE_FUNC", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


	public int deleteByRoleId(Object role_id){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "role_id", role_id );
		int retInt=update( "SYS_REL_ROLE_FUNC.DeleteByRoleIdSYS_REL_ROLE_FUNC", _hashmap );
		return retInt;
	}

}
