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
import com.lubian.cpf.dao.SysOrgDAO;
import com.lubian.cpf.vo.SysOrg;

@Repository
public class SysOrgDAOImpl extends BaseSqlMapDao implements SysOrgDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_ORG.CountFindAllSYS_ORG", null );
        return ret.intValue();
	}

	public SysOrg findByPK(SysOrg obj) {
		Object ret = queryForObject("SYS_ORG.FindByPKSYS_ORG", obj );
		if(ret !=null)
			return (SysOrg)ret;
		else 
			return null;
	}

	public List freeFind(SysOrg obj) {
		return queryForList("SYS_ORG.FreeFindSYS_ORG", obj );
	}

	public int countFreeFind(SysOrg obj) {
        Integer ret = (Integer) queryForObject("SYS_ORG.CountFreeFindSYS_ORG", obj );
        return ret.intValue();
	}

	public List freeFind(SysOrg obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_ORG.FreeFindSYS_ORG", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysOrg obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysOrg){
	    	_hashmap = ((SysOrg)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_ORG.FreeFindSYS_ORGOrdered", _hashmap);
	}

	public List freeFind(SysOrg obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysOrg){
	    	_hashmap = ((SysOrg)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_ORG.FreeFindSYS_ORGOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysOrg> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysOrg oneObj = (SysOrg)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysOrg vo) {
        SysOrg obj = (SysOrg) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysOrg obj) {

		obj.fixup();
        insert("SYS_ORG.InsertSYS_ORG", obj );
	}

	public void update(SysOrg obj) {

		obj.fixup();
		update( "SYS_ORG.UpdateSYS_ORG", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysOrg obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_ORG.DeleteSYS_ORG", obj );

	}

	public void removeObjectTree(SysOrg obj) {

        obj.fixup();
        SysOrg oldObj = ( SysOrg )queryForObject("SYS_ORG.FindByPKSYS_ORG", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_ORG.DeleteSYS_ORG", oldObj );

	}

	public boolean exists(SysOrg vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysOrg obj = (SysOrg) vo;

        keysFilled = keysFilled && ( null != obj.getOrgId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_ORG.CountSYS_ORG", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
