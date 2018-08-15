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
import com.lubian.cpf.dao.SysUserDAO;
import com.lubian.cpf.vo.SysUser;

@Repository
public class SysUserDAOImpl extends BaseSqlMapDao implements SysUserDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_USER.CountFindAllSYS_USER", null );
        return ret.intValue();
	}

	public SysUser findByPK(SysUser obj) {
		Object ret = queryForObject("SYS_USER.FindByPKSYS_USER", obj );
		if(ret !=null)
			return (SysUser)ret;
		else 
			return null;
	}

	public List freeFind(SysUser obj) {
		return queryForList("SYS_USER.FreeFindSYS_USER", obj );
	}

	public int countFreeFind(SysUser obj) {
        Integer ret = (Integer) queryForObject("SYS_USER.CountFreeFindSYS_USER", obj );
        return ret.intValue();
	}

	public List freeFind(SysUser obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_USER.FreeFindSYS_USER", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysUser obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUser){
	    	_hashmap = ((SysUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_USER.FreeFindSYS_USEROrdered", _hashmap);
	}

	public List freeFind(SysUser obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUser){
	    	_hashmap = ((SysUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_USER.FreeFindSYS_USEROrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysUser> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysUser oneObj = (SysUser)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysUser vo) {
        SysUser obj = (SysUser) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysUser obj) {

		obj.fixup();
        insert("SYS_USER.InsertSYS_USER", obj );
	}

	public void update(SysUser obj) {

		obj.fixup();
		update( "SYS_USER.UpdateSYS_USER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysUser obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_USER.DeleteSYS_USER", obj );

	}

	public void removeObjectTree(SysUser obj) {

        obj.fixup();
        SysUser oldObj = ( SysUser )queryForObject("SYS_USER.FindByPKSYS_USER", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_USER.DeleteSYS_USER", oldObj );

	}

	public boolean exists(SysUser vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysUser obj = (SysUser) vo;

        keysFilled = keysFilled && ( null != obj.getUserId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_USER.CountSYS_USER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
