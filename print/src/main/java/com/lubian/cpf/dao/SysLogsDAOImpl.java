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
import com.lubian.cpf.dao.SysLogsDAO;
import com.lubian.cpf.vo.SysLogs;

@Repository
public class SysLogsDAOImpl extends BaseSqlMapDao implements SysLogsDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_LOGS.CountFindAllSYS_LOGS", null );
        return ret.intValue();
	}

	public SysLogs findByPK(SysLogs obj) {
		Object ret = queryForObject("SYS_LOGS.FindByPKSYS_LOGS", obj );
		if(ret !=null)
			return (SysLogs)ret;
		else 
			return null;
	}

	public List freeFind(SysLogs obj) {
		return queryForList("SYS_LOGS.FreeFindSYS_LOGS", obj );
	}

	public int countFreeFind(SysLogs obj) {
        Integer ret = (Integer) queryForObject("SYS_LOGS.CountFreeFindSYS_LOGS", obj );
        return ret.intValue();
	}

	public List freeFind(SysLogs obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_LOGS.FreeFindSYS_LOGS", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysLogs obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysLogs){
	    	_hashmap = ((SysLogs)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_LOGS.FreeFindSYS_LOGSOrdered", _hashmap);
	}

	public List freeFind(SysLogs obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysLogs){
	    	_hashmap = ((SysLogs)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_LOGS.FreeFindSYS_LOGSOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysLogs> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysLogs oneObj = (SysLogs)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysLogs vo) {
        SysLogs obj = (SysLogs) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysLogs obj) {

		obj.fixup();
        insert("SYS_LOGS.InsertSYS_LOGS", obj );
	}

	public void update(SysLogs obj) {

		obj.fixup();
		update( "SYS_LOGS.UpdateSYS_LOGS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysLogs obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_LOGS.DeleteSYS_LOGS", obj );

	}

	public void removeObjectTree(SysLogs obj) {

        obj.fixup();
        SysLogs oldObj = ( SysLogs )queryForObject("SYS_LOGS.FindByPKSYS_LOGS", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_LOGS.DeleteSYS_LOGS", oldObj );

	}

	public boolean exists(SysLogs vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysLogs obj = (SysLogs) vo;

        keysFilled = keysFilled && ( null != obj.getLogId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_LOGS.CountSYS_LOGS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
