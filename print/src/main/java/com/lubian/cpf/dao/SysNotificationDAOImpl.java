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
import com.lubian.cpf.dao.SysNotificationDAO;
import com.lubian.cpf.vo.SysNotification;

@Repository
public class SysNotificationDAOImpl extends BaseSqlMapDao implements SysNotificationDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_NOTIFICATION.CountFindAllSYS_NOTIFICATION", null );
        return ret.intValue();
	}

	public SysNotification findByPK(SysNotification obj) {
		Object ret = queryForObject("SYS_NOTIFICATION.FindByPKSYS_NOTIFICATION", obj );
		if(ret !=null)
			return (SysNotification)ret;
		else 
			return null;
	}

	public List freeFind(SysNotification obj) {
		return queryForList("SYS_NOTIFICATION.FreeFindSYS_NOTIFICATION", obj );
	}

	public int countFreeFind(SysNotification obj) {
        Integer ret = (Integer) queryForObject("SYS_NOTIFICATION.CountFreeFindSYS_NOTIFICATION", obj );
        return ret.intValue();
	}

	public List freeFind(SysNotification obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_NOTIFICATION.FreeFindSYS_NOTIFICATION", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysNotification obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysNotification){
	    	_hashmap = ((SysNotification)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_NOTIFICATION.FreeFindSYS_NOTIFICATIONOrdered", _hashmap);
	}

	public List freeFind(SysNotification obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysNotification){
	    	_hashmap = ((SysNotification)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_NOTIFICATION.FreeFindSYS_NOTIFICATIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysNotification> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysNotification oneObj = (SysNotification)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysNotification vo) {
        SysNotification obj = (SysNotification) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysNotification obj) {

		obj.fixup();
        insert("SYS_NOTIFICATION.InsertSYS_NOTIFICATION", obj );
	}

	public void update(SysNotification obj) {

		obj.fixup();
		update( "SYS_NOTIFICATION.UpdateSYS_NOTIFICATION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysNotification obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_NOTIFICATION.DeleteSYS_NOTIFICATION", obj );

	}

	public void removeObjectTree(SysNotification obj) {

        obj.fixup();
        SysNotification oldObj = ( SysNotification )queryForObject("SYS_NOTIFICATION.FindByPKSYS_NOTIFICATION", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_NOTIFICATION.DeleteSYS_NOTIFICATION", oldObj );

	}

	public boolean exists(SysNotification vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysNotification obj = (SysNotification) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_NOTIFICATION.CountSYS_NOTIFICATION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
