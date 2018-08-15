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
import com.lubian.cpf.dao.CdfBookmarkDAO;
import com.lubian.cpf.vo.CdfBookmark;

@Repository
public class CdfBookmarkDAOImpl extends BaseSqlMapDao implements CdfBookmarkDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CDF_BOOKMARK.CountFindAllCDF_BOOKMARK", null );
        return ret.intValue();
	}

	public CdfBookmark findByPK(CdfBookmark obj) {
		Object ret = queryForObject("CDF_BOOKMARK.FindByPKCDF_BOOKMARK", obj );
		if(ret !=null)
			return (CdfBookmark)ret;
		else 
			return null;
	}

	public List freeFind(CdfBookmark obj) {
		return queryForList("CDF_BOOKMARK.FreeFindCDF_BOOKMARK", obj );
	}

	public int countFreeFind(CdfBookmark obj) {
        Integer ret = (Integer) queryForObject("CDF_BOOKMARK.CountFreeFindCDF_BOOKMARK", obj );
        return ret.intValue();
	}

	public List freeFind(CdfBookmark obj, int pageIndex, int pageSize) {
		return getPaginatedList("CDF_BOOKMARK.FreeFindCDF_BOOKMARK", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CdfBookmark obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CdfBookmark){
	    	_hashmap = ((CdfBookmark)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CDF_BOOKMARK.FreeFindCDF_BOOKMARKOrdered", _hashmap);
	}

	public List freeFind(CdfBookmark obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CdfBookmark){
	    	_hashmap = ((CdfBookmark)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CDF_BOOKMARK.FreeFindCDF_BOOKMARKOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CdfBookmark> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CdfBookmark oneObj = (CdfBookmark)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CdfBookmark vo) {
        CdfBookmark obj = (CdfBookmark) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CdfBookmark obj) {

		obj.fixup();
        insert("CDF_BOOKMARK.InsertCDF_BOOKMARK", obj );
	}

	public void update(CdfBookmark obj) {

		obj.fixup();
		update( "CDF_BOOKMARK.UpdateCDF_BOOKMARK", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CdfBookmark obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CDF_BOOKMARK.DeleteCDF_BOOKMARK", obj );

	}

	public void removeObjectTree(CdfBookmark obj) {

        obj.fixup();
        CdfBookmark oldObj = ( CdfBookmark )queryForObject("CDF_BOOKMARK.FindByPKCDF_BOOKMARK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CDF_BOOKMARK.DeleteCDF_BOOKMARK", oldObj );

	}

	public boolean exists(CdfBookmark vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CdfBookmark obj = (CdfBookmark) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CDF_BOOKMARK.CountCDF_BOOKMARK", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
