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
import com.lubian.cpf.dao.SysRoleDAO;
import com.lubian.cpf.vo.SysRole;

@Repository
public class SysRoleDAOImpl extends BaseSqlMapDao implements SysRoleDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_ROLE.CountFindAllSYS_ROLE", null );
        return ret.intValue();
	}

	public SysRole findByPK(SysRole obj) {
		Object ret = queryForObject("SYS_ROLE.FindByPKSYS_ROLE", obj );
		if(ret !=null)
			return (SysRole)ret;
		else 
			return null;
	}

	public List freeFind(SysRole obj) {
		return queryForList("SYS_ROLE.FreeFindSYS_ROLE", obj );
	}

	public int countFreeFind(SysRole obj) {
        Integer ret = (Integer) queryForObject("SYS_ROLE.CountFreeFindSYS_ROLE", obj );
        return ret.intValue();
	}

	public List freeFind(SysRole obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_ROLE.FreeFindSYS_ROLE", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysRole obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysRole){
	    	_hashmap = ((SysRole)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_ROLE.FreeFindSYS_ROLEOrdered", _hashmap);
	}

	public List freeFind(SysRole obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysRole){
	    	_hashmap = ((SysRole)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_ROLE.FreeFindSYS_ROLEOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysRole> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysRole oneObj = (SysRole)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysRole vo) {
        SysRole obj = (SysRole) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysRole obj) {

		obj.fixup();
        insert("SYS_ROLE.InsertSYS_ROLE", obj );
	}

	public void update(SysRole obj) {

		obj.fixup();
		update( "SYS_ROLE.UpdateSYS_ROLE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysRole obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_ROLE.DeleteSYS_ROLE", obj );

	}

	public void removeObjectTree(SysRole obj) {

        obj.fixup();
        SysRole oldObj = ( SysRole )queryForObject("SYS_ROLE.FindByPKSYS_ROLE", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_ROLE.DeleteSYS_ROLE", oldObj );

	}

	public boolean exists(SysRole vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysRole obj = (SysRole) vo;

        keysFilled = keysFilled && ( null != obj.getRoleId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_ROLE.CountSYS_ROLE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
