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
import com.lubian.cpf.dao.SysDepartmentDAO;
import com.lubian.cpf.vo.SysDepartment;

@Repository
public class SysDepartmentDAOImpl extends BaseSqlMapDao implements SysDepartmentDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_DEPARTMENT.CountFindAllSYS_DEPARTMENT", null );
        return ret.intValue();
	}

	public SysDepartment findByPK(SysDepartment obj) {
		Object ret = queryForObject("SYS_DEPARTMENT.FindByPKSYS_DEPARTMENT", obj );
		if(ret !=null)
			return (SysDepartment)ret;
		else 
			return null;
	}

	public List freeFind(SysDepartment obj) {
		return queryForList("SYS_DEPARTMENT.FreeFindSYS_DEPARTMENT", obj );
	}

	public int countFreeFind(SysDepartment obj) {
        Integer ret = (Integer) queryForObject("SYS_DEPARTMENT.CountFreeFindSYS_DEPARTMENT", obj );
        return ret.intValue();
	}

	public List freeFind(SysDepartment obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_DEPARTMENT.FreeFindSYS_DEPARTMENT", obj, pageIndex, pageSize );
	}
		
	public List freeFind(SysDepartment obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysDepartment){
	    	_hashmap = ((SysDepartment)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SYS_DEPARTMENT.FreeFindSYS_DEPARTMENTOrdered", _hashmap);
	}

	public List freeFind(SysDepartment obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof SysDepartment){
	    	_hashmap = ((SysDepartment)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SYS_DEPARTMENT.FreeFindSYS_DEPARTMENTOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<SysDepartment> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	SysDepartment oneObj = (SysDepartment)it.next();
				save( oneObj );
            }
        }
	}

	public void save(SysDepartment vo) {
        SysDepartment obj = (SysDepartment) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(SysDepartment obj) {

		obj.fixup();
        insert("SYS_DEPARTMENT.InsertSYS_DEPARTMENT", obj );
	}

	public void update(SysDepartment obj) {

		obj.fixup();
		update( "SYS_DEPARTMENT.UpdateSYS_DEPARTMENT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(SysDepartment obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "SYS_DEPARTMENT.DeleteSYS_DEPARTMENT", obj );

	}

	public void removeObjectTree(SysDepartment obj) {

        obj.fixup();
        SysDepartment oldObj = ( SysDepartment )queryForObject("SYS_DEPARTMENT.FindByPKSYS_DEPARTMENT", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "SYS_DEPARTMENT.DeleteSYS_DEPARTMENT", oldObj );

	}

	public boolean exists(SysDepartment vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SysDepartment obj = (SysDepartment) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SYS_DEPARTMENT.CountSYS_DEPARTMENT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
