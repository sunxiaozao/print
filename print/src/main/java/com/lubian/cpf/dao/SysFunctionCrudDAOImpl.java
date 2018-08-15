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
import com.lubian.cpf.dao.SysFunctionCrudDAO;
import com.lubian.cpf.vo.SysFunctionCrud;

@Repository
public class SysFunctionCrudDAOImpl extends BaseSqlMapDao implements SysFunctionCrudDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_CRUD.CountFindAllSYS_FUNCTION_CRUD", null );
        return ret.intValue();
	}

	public SysFunctionCrud findByPK(SysFunctionCrud obj) {
		Object ret = queryForObject("SYS_FUNCTION_CRUD.FindByPKSYS_FUNCTION_CRUD", obj );
		if(ret !=null)
			return (SysFunctionCrud)ret;
		else 
			return null;
	}

	public List freeFind(SysFunctionCrud obj) {
		return queryForList("SYS_FUNCTION_CRUD.FreeFindSYS_FUNCTION_CRUD", obj );
	}

	public int countFreeFind(SysFunctionCrud obj) {
        Integer ret = (Integer) queryForObject("SYS_FUNCTION_CRUD.CountFreeFindSYS_FUNCTION_CRUD", obj );
        return ret.intValue();
	}

	public List freeFind(SysFunctionCrud obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_FUNCTION_CRUD.FreeFindSYS_FUNCTION_CRUD", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysFunctionCrud obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysFunctionCrud){
	    	_hashmap = ((SysFunctionCrud)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_FUNCTION_CRUD.FreeFindSYS_FUNCTION_CRUDOrdered", _hashmap);
	}

	public List freeFind(SysFunctionCrud obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysFunctionCrud){
	    	_hashmap = ((SysFunctionCrud)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_FUNCTION_CRUD.FreeFindSYS_FUNCTION_CRUDOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysFunctionCrud> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysFunctionCrud oneObj = (SysFunctionCrud)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysFunctionCrud vo) {
        SysFunctionCrud obj = (SysFunctionCrud) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysFunctionCrud obj) {

		obj.fixup();
        insert("SYS_FUNCTION_CRUD.InsertSYS_FUNCTION_CRUD", obj );
	}

	public void update(SysFunctionCrud obj) {

		obj.fixup();
		update( "SYS_FUNCTION_CRUD.UpdateSYS_FUNCTION_CRUD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysFunctionCrud obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_FUNCTION_CRUD.DeleteSYS_FUNCTION_CRUD", obj );

	}

	public void removeObjectTree(SysFunctionCrud obj) {

        obj.fixup();
        SysFunctionCrud oldObj = ( SysFunctionCrud )queryForObject("SYS_FUNCTION_CRUD.FindByPKSYS_FUNCTION_CRUD", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_FUNCTION_CRUD.DeleteSYS_FUNCTION_CRUD", oldObj );

	}

	public boolean exists(SysFunctionCrud vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysFunctionCrud obj = (SysFunctionCrud) vo;

        keysFilled = keysFilled && ( null != obj.getRelId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_FUNCTION_CRUD.CountSYS_FUNCTION_CRUD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
