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
import com.lubian.cpf.dao.SysUserSmsDAO;
import com.lubian.cpf.vo.SysUserSms;

@Repository
public class SysUserSmsDAOImpl extends BaseSqlMapDao implements SysUserSmsDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_USER_SMS.CountFindAllSYS_USER_SMS", null );
        return ret.intValue();
	}

	public SysUserSms findByPK(SysUserSms obj) {
		Object ret = queryForObject("SYS_USER_SMS.FindByPKSYS_USER_SMS", obj );
		if(ret !=null)
			return (SysUserSms)ret;
		else 
			return null;
	}

	public List freeFind(SysUserSms obj) {
		return queryForList("SYS_USER_SMS.FreeFindSYS_USER_SMS", obj );
	}

	public int countFreeFind(SysUserSms obj) {
        Integer ret = (Integer) queryForObject("SYS_USER_SMS.CountFreeFindSYS_USER_SMS", obj );
        return ret.intValue();
	}

	public List freeFind(SysUserSms obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_USER_SMS.FreeFindSYS_USER_SMS", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysUserSms obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUserSms){
	    	_hashmap = ((SysUserSms)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_USER_SMS.FreeFindSYS_USER_SMSOrdered", _hashmap);
	}

	public List freeFind(SysUserSms obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysUserSms){
	    	_hashmap = ((SysUserSms)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_USER_SMS.FreeFindSYS_USER_SMSOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysUserSms> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysUserSms oneObj = (SysUserSms)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysUserSms vo) {
        SysUserSms obj = (SysUserSms) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysUserSms obj) {

		obj.fixup();
        insert("SYS_USER_SMS.InsertSYS_USER_SMS", obj );
	}

	public void update(SysUserSms obj) {

		obj.fixup();
		update( "SYS_USER_SMS.UpdateSYS_USER_SMS", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysUserSms obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_USER_SMS.DeleteSYS_USER_SMS", obj );

	}

	public void removeObjectTree(SysUserSms obj) {

        obj.fixup();
        SysUserSms oldObj = ( SysUserSms )queryForObject("SYS_USER_SMS.FindByPKSYS_USER_SMS", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_USER_SMS.DeleteSYS_USER_SMS", oldObj );

	}

	public boolean exists(SysUserSms vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysUserSms obj = (SysUserSms) vo;

        keysFilled = keysFilled && ( null != obj.getSmsId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_USER_SMS.CountSYS_USER_SMS", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
