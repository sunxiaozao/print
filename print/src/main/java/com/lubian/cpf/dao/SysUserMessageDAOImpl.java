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
import com.lubian.cpf.dao.SysUserMessageDAO;
import com.lubian.cpf.vo.SysUserMessage;

@Repository
public class SysUserMessageDAOImpl extends BaseSqlMapDao implements SysUserMessageDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_USER_MESSAGE.CountFindAllSYS_USER_MESSAGE", null );
        return ret.intValue();
	}

	public SysUserMessage findByPK(SysUserMessage obj) {
		Object ret = queryForObject("SYS_USER_MESSAGE.FindByPKSYS_USER_MESSAGE", obj );
		if(ret !=null)
			return (SysUserMessage)ret;
		else 
			return null;
	}

	public List freeFind(SysUserMessage obj) {
		return queryForList("SYS_USER_MESSAGE.FreeFindSYS_USER_MESSAGE", obj );
	}

	public int countFreeFind(SysUserMessage obj) {
        Integer ret = (Integer) queryForObject("SYS_USER_MESSAGE.CountFreeFindSYS_USER_MESSAGE", obj );
        return ret.intValue();
	}

	public List freeFind(SysUserMessage obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_USER_MESSAGE.FreeFindSYS_USER_MESSAGE", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysUserMessage obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUserMessage){
	    	_hashmap = ((SysUserMessage)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_USER_MESSAGE.FreeFindSYS_USER_MESSAGEOrdered", _hashmap);
	}

	public List freeFind(SysUserMessage obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUserMessage){
	    	_hashmap = ((SysUserMessage)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_USER_MESSAGE.FreeFindSYS_USER_MESSAGEOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysUserMessage> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysUserMessage oneObj = (SysUserMessage)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysUserMessage vo) {
        SysUserMessage obj = (SysUserMessage) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysUserMessage obj) {

		obj.fixup();
        insert("SYS_USER_MESSAGE.InsertSYS_USER_MESSAGE", obj );
	}

	public void update(SysUserMessage obj) {

		obj.fixup();
		update( "SYS_USER_MESSAGE.UpdateSYS_USER_MESSAGE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysUserMessage obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_USER_MESSAGE.DeleteSYS_USER_MESSAGE", obj );

	}

	public void removeObjectTree(SysUserMessage obj) {

        obj.fixup();
        SysUserMessage oldObj = ( SysUserMessage )queryForObject("SYS_USER_MESSAGE.FindByPKSYS_USER_MESSAGE", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_USER_MESSAGE.DeleteSYS_USER_MESSAGE", oldObj );

	}

	public boolean exists(SysUserMessage vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysUserMessage obj = (SysUserMessage) vo;

        keysFilled = keysFilled && ( null != obj.getMessageId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_USER_MESSAGE.CountSYS_USER_MESSAGE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
