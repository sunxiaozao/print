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
import com.lubian.cpf.dao.SysMailJobItemDAO;
import com.lubian.cpf.vo.SysMailJobItem;

@Repository
public class SysMailJobItemDAOImpl extends BaseSqlMapDao implements SysMailJobItemDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_MAIL_JOB_ITEM.CountFindAllSYS_MAIL_JOB_ITEM", null );
        return ret.intValue();
	}

	public SysMailJobItem findByPK(SysMailJobItem obj) {
		Object ret = queryForObject("SYS_MAIL_JOB_ITEM.FindByPKSYS_MAIL_JOB_ITEM", obj );
		if(ret !=null)
			return (SysMailJobItem)ret;
		else 
			return null;
	}

	public List freeFind(SysMailJobItem obj) {
		return queryForList("SYS_MAIL_JOB_ITEM.FreeFindSYS_MAIL_JOB_ITEM", obj );
	}

	public int countFreeFind(SysMailJobItem obj) {
        Integer ret = (Integer) queryForObject("SYS_MAIL_JOB_ITEM.CountFreeFindSYS_MAIL_JOB_ITEM", obj );
        return ret.intValue();
	}

	public List freeFind(SysMailJobItem obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_MAIL_JOB_ITEM.FreeFindSYS_MAIL_JOB_ITEM", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysMailJobItem obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysMailJobItem){
	    	_hashmap = ((SysMailJobItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_MAIL_JOB_ITEM.FreeFindSYS_MAIL_JOB_ITEMOrdered", _hashmap);
	}

	public List freeFind(SysMailJobItem obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysMailJobItem){
	    	_hashmap = ((SysMailJobItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_MAIL_JOB_ITEM.FreeFindSYS_MAIL_JOB_ITEMOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysMailJobItem> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysMailJobItem oneObj = (SysMailJobItem)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysMailJobItem vo) {
        SysMailJobItem obj = (SysMailJobItem) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysMailJobItem obj) {

		obj.fixup();
        insert("SYS_MAIL_JOB_ITEM.InsertSYS_MAIL_JOB_ITEM", obj );
	}

	public void update(SysMailJobItem obj) {

		obj.fixup();
		update( "SYS_MAIL_JOB_ITEM.UpdateSYS_MAIL_JOB_ITEM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysMailJobItem obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_MAIL_JOB_ITEM.DeleteSYS_MAIL_JOB_ITEM", obj );

	}

	public void removeObjectTree(SysMailJobItem obj) {

        obj.fixup();
        SysMailJobItem oldObj = ( SysMailJobItem )queryForObject("SYS_MAIL_JOB_ITEM.FindByPKSYS_MAIL_JOB_ITEM", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_MAIL_JOB_ITEM.DeleteSYS_MAIL_JOB_ITEM", oldObj );

	}

	public boolean exists(SysMailJobItem vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysMailJobItem obj = (SysMailJobItem) vo;

        keysFilled = keysFilled && ( null != obj.getMailItemId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_MAIL_JOB_ITEM.CountSYS_MAIL_JOB_ITEM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
