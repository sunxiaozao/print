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
import com.lubian.cpf.dao.SysMailJobDAO;
import com.lubian.cpf.vo.SysMailJob;

@Repository
public class SysMailJobDAOImpl extends BaseSqlMapDao implements SysMailJobDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_MAIL_JOB.CountFindAllSYS_MAIL_JOB", null );
        return ret.intValue();
	}

	public SysMailJob findByPK(SysMailJob obj) {
		Object ret = queryForObject("SYS_MAIL_JOB.FindByPKSYS_MAIL_JOB", obj );
		if(ret !=null)
			return (SysMailJob)ret;
		else 
			return null;
	}

	public List freeFind(SysMailJob obj) {
		return queryForList("SYS_MAIL_JOB.FreeFindSYS_MAIL_JOB", obj );
	}

	public int countFreeFind(SysMailJob obj) {
        Integer ret = (Integer) queryForObject("SYS_MAIL_JOB.CountFreeFindSYS_MAIL_JOB", obj );
        return ret.intValue();
	}

	public List freeFind(SysMailJob obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_MAIL_JOB.FreeFindSYS_MAIL_JOB", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysMailJob obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysMailJob){
	    	_hashmap = ((SysMailJob)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_MAIL_JOB.FreeFindSYS_MAIL_JOBOrdered", _hashmap);
	}

	public List freeFind(SysMailJob obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysMailJob){
	    	_hashmap = ((SysMailJob)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_MAIL_JOB.FreeFindSYS_MAIL_JOBOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysMailJob> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysMailJob oneObj = (SysMailJob)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysMailJob vo) {
        SysMailJob obj = (SysMailJob) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysMailJob obj) {

		obj.fixup();
        insert("SYS_MAIL_JOB.InsertSYS_MAIL_JOB", obj );
	}

	public void update(SysMailJob obj) {

		obj.fixup();
		update( "SYS_MAIL_JOB.UpdateSYS_MAIL_JOB", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysMailJob obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_MAIL_JOB.DeleteSYS_MAIL_JOB", obj );

	}

	public void removeObjectTree(SysMailJob obj) {

        obj.fixup();
        SysMailJob oldObj = ( SysMailJob )queryForObject("SYS_MAIL_JOB.FindByPKSYS_MAIL_JOB", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_MAIL_JOB.DeleteSYS_MAIL_JOB", oldObj );

	}

	public boolean exists(SysMailJob vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysMailJob obj = (SysMailJob) vo;

        keysFilled = keysFilled && ( null != obj.getMailJobId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_MAIL_JOB.CountSYS_MAIL_JOB", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
