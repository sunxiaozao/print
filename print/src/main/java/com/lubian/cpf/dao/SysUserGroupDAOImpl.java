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
import com.lubian.cpf.dao.SysUserGroupDAO;
import com.lubian.cpf.vo.SysUserGroup;

@Repository
public class SysUserGroupDAOImpl extends BaseSqlMapDao implements SysUserGroupDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_USER_GROUP.CountFindAllSYS_USER_GROUP", null );
        return ret.intValue();
	}

	public SysUserGroup findByPK(SysUserGroup obj) {
		Object ret = queryForObject("SYS_USER_GROUP.FindByPKSYS_USER_GROUP", obj );
		if(ret !=null)
			return (SysUserGroup)ret;
		else 
			return null;
	}

	public List freeFind(SysUserGroup obj) {
		return queryForList("SYS_USER_GROUP.FreeFindSYS_USER_GROUP", obj );
	}

	public int countFreeFind(SysUserGroup obj) {
        Integer ret = (Integer) queryForObject("SYS_USER_GROUP.CountFreeFindSYS_USER_GROUP", obj );
        return ret.intValue();
	}

	public List freeFind(SysUserGroup obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_USER_GROUP.FreeFindSYS_USER_GROUP", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysUserGroup obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUserGroup){
	    	_hashmap = ((SysUserGroup)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_USER_GROUP.FreeFindSYS_USER_GROUPOrdered", _hashmap);
	}

	public List freeFind(SysUserGroup obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUserGroup){
	    	_hashmap = ((SysUserGroup)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_USER_GROUP.FreeFindSYS_USER_GROUPOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysUserGroup> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysUserGroup oneObj = (SysUserGroup)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysUserGroup vo) {
        SysUserGroup obj = (SysUserGroup) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysUserGroup obj) {

		obj.fixup();
        insert("SYS_USER_GROUP.InsertSYS_USER_GROUP", obj );
	}

	public void update(SysUserGroup obj) {

		obj.fixup();
		update( "SYS_USER_GROUP.UpdateSYS_USER_GROUP", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysUserGroup obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_USER_GROUP.DeleteSYS_USER_GROUP", obj );

	}

	public void removeObjectTree(SysUserGroup obj) {

        obj.fixup();
        SysUserGroup oldObj = ( SysUserGroup )queryForObject("SYS_USER_GROUP.FindByPKSYS_USER_GROUP", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_USER_GROUP.DeleteSYS_USER_GROUP", oldObj );

	}

	public boolean exists(SysUserGroup vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysUserGroup obj = (SysUserGroup) vo;

        keysFilled = keysFilled && ( null != obj.getGroupId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_USER_GROUP.CountSYS_USER_GROUP", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
