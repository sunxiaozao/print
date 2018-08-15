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
import com.lubian.cpf.dao.SysFeedbackDAO;
import com.lubian.cpf.vo.SysFeedback;

@Repository
public class SysFeedbackDAOImpl extends BaseSqlMapDao implements SysFeedbackDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_FEEDBACK.CountFindAllSYS_FEEDBACK", null );
        return ret.intValue();
	}

	public SysFeedback findByPK(SysFeedback obj) {
		Object ret = queryForObject("SYS_FEEDBACK.FindByPKSYS_FEEDBACK", obj );
		if(ret !=null)
			return (SysFeedback)ret;
		else 
			return null;
	}

	public List freeFind(SysFeedback obj) {
		return queryForList("SYS_FEEDBACK.FreeFindSYS_FEEDBACK", obj );
	}

	public int countFreeFind(SysFeedback obj) {
        Integer ret = (Integer) queryForObject("SYS_FEEDBACK.CountFreeFindSYS_FEEDBACK", obj );
        return ret.intValue();
	}

	public List freeFind(SysFeedback obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_FEEDBACK.FreeFindSYS_FEEDBACK", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysFeedback obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysFeedback){
	    	_hashmap = ((SysFeedback)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_FEEDBACK.FreeFindSYS_FEEDBACKOrdered", _hashmap);
	}

	public List freeFind(SysFeedback obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysFeedback){
	    	_hashmap = ((SysFeedback)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_FEEDBACK.FreeFindSYS_FEEDBACKOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysFeedback> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysFeedback oneObj = (SysFeedback)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysFeedback vo) {
        SysFeedback obj = (SysFeedback) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysFeedback obj) {

		obj.fixup();
        insert("SYS_FEEDBACK.InsertSYS_FEEDBACK", obj );
	}

	public void update(SysFeedback obj) {

		obj.fixup();
		update( "SYS_FEEDBACK.UpdateSYS_FEEDBACK", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysFeedback obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_FEEDBACK.DeleteSYS_FEEDBACK", obj );

	}

	public void removeObjectTree(SysFeedback obj) {

        obj.fixup();
        SysFeedback oldObj = ( SysFeedback )queryForObject("SYS_FEEDBACK.FindByPKSYS_FEEDBACK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_FEEDBACK.DeleteSYS_FEEDBACK", oldObj );

	}

	public boolean exists(SysFeedback vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysFeedback obj = (SysFeedback) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_FEEDBACK.CountSYS_FEEDBACK", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
