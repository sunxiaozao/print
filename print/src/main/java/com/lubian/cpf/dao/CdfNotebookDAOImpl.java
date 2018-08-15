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
import com.lubian.cpf.dao.CdfNotebookDAO;
import com.lubian.cpf.vo.CdfNotebook;

@Repository
public class CdfNotebookDAOImpl extends BaseSqlMapDao implements CdfNotebookDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CDF_NOTEBOOK.CountFindAllCDF_NOTEBOOK", null );
        return ret.intValue();
	}

	public CdfNotebook findByPK(CdfNotebook obj) {
		Object ret = queryForObject("CDF_NOTEBOOK.FindByPKCDF_NOTEBOOK", obj );
		if(ret !=null)
			return (CdfNotebook)ret;
		else 
			return null;
	}

	public List freeFind(CdfNotebook obj) {
		return queryForList("CDF_NOTEBOOK.FreeFindCDF_NOTEBOOK", obj );
	}

	public int countFreeFind(CdfNotebook obj) {
        Integer ret = (Integer) queryForObject("CDF_NOTEBOOK.CountFreeFindCDF_NOTEBOOK", obj );
        return ret.intValue();
	}

	public List freeFind(CdfNotebook obj, int pageIndex, int pageSize) {
		return getPaginatedList("CDF_NOTEBOOK.FreeFindCDF_NOTEBOOK", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CdfNotebook obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CdfNotebook){
	    	_hashmap = ((CdfNotebook)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CDF_NOTEBOOK.FreeFindCDF_NOTEBOOKOrdered", _hashmap);
	}

	public List freeFind(CdfNotebook obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CdfNotebook){
	    	_hashmap = ((CdfNotebook)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CDF_NOTEBOOK.FreeFindCDF_NOTEBOOKOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CdfNotebook> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CdfNotebook oneObj = (CdfNotebook)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CdfNotebook vo) {
        CdfNotebook obj = (CdfNotebook) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CdfNotebook obj) {

		obj.fixup();
        insert("CDF_NOTEBOOK.InsertCDF_NOTEBOOK", obj );
	}

	public void update(CdfNotebook obj) {

		obj.fixup();
		update( "CDF_NOTEBOOK.UpdateCDF_NOTEBOOK", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CdfNotebook obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CDF_NOTEBOOK.DeleteCDF_NOTEBOOK", obj );

	}

	public void removeObjectTree(CdfNotebook obj) {

        obj.fixup();
        CdfNotebook oldObj = ( CdfNotebook )queryForObject("CDF_NOTEBOOK.FindByPKCDF_NOTEBOOK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CDF_NOTEBOOK.DeleteCDF_NOTEBOOK", oldObj );

	}

	public boolean exists(CdfNotebook vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CdfNotebook obj = (CdfNotebook) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CDF_NOTEBOOK.CountCDF_NOTEBOOK", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
