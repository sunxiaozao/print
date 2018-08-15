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
import com.lubian.cpf.dao.SysUserMessageItemDAO;
import com.lubian.cpf.vo.SysUserMessageItem;

@Repository
public class SysUserMessageItemDAOImpl extends BaseSqlMapDao implements SysUserMessageItemDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_USER_MESSAGE_ITEM.CountFindAllSYS_USER_MESSAGE_ITEM", null );
        return ret.intValue();
	}

	public SysUserMessageItem findByPK(SysUserMessageItem obj) {
		Object ret = queryForObject("SYS_USER_MESSAGE_ITEM.FindByPKSYS_USER_MESSAGE_ITEM", obj );
		if(ret !=null)
			return (SysUserMessageItem)ret;
		else 
			return null;
	}

	public List freeFind(SysUserMessageItem obj) {
		return queryForList("SYS_USER_MESSAGE_ITEM.FreeFindSYS_USER_MESSAGE_ITEM", obj );
	}

	public int countFreeFind(SysUserMessageItem obj) {
        Integer ret = (Integer) queryForObject("SYS_USER_MESSAGE_ITEM.CountFreeFindSYS_USER_MESSAGE_ITEM", obj );
        return ret.intValue();
	}

	public List freeFind(SysUserMessageItem obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_USER_MESSAGE_ITEM.FreeFindSYS_USER_MESSAGE_ITEM", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysUserMessageItem obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUserMessageItem){
	    	_hashmap = ((SysUserMessageItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_USER_MESSAGE_ITEM.FreeFindSYS_USER_MESSAGE_ITEMOrdered", _hashmap);
	}

	public List freeFind(SysUserMessageItem obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUserMessageItem){
	    	_hashmap = ((SysUserMessageItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_USER_MESSAGE_ITEM.FreeFindSYS_USER_MESSAGE_ITEMOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysUserMessageItem> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysUserMessageItem oneObj = (SysUserMessageItem)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysUserMessageItem vo) {
        SysUserMessageItem obj = (SysUserMessageItem) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysUserMessageItem obj) {

		obj.fixup();
        insert("SYS_USER_MESSAGE_ITEM.InsertSYS_USER_MESSAGE_ITEM", obj );
	}

	public void update(SysUserMessageItem obj) {

		obj.fixup();
		update( "SYS_USER_MESSAGE_ITEM.UpdateSYS_USER_MESSAGE_ITEM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysUserMessageItem obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_USER_MESSAGE_ITEM.DeleteSYS_USER_MESSAGE_ITEM", obj );

	}

	public void removeObjectTree(SysUserMessageItem obj) {

        obj.fixup();
        SysUserMessageItem oldObj = ( SysUserMessageItem )queryForObject("SYS_USER_MESSAGE_ITEM.FindByPKSYS_USER_MESSAGE_ITEM", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_USER_MESSAGE_ITEM.DeleteSYS_USER_MESSAGE_ITEM", oldObj );

	}

	public boolean exists(SysUserMessageItem vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysUserMessageItem obj = (SysUserMessageItem) vo;

        keysFilled = keysFilled && ( null != obj.getMessageItemId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_USER_MESSAGE_ITEM.CountSYS_USER_MESSAGE_ITEM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
