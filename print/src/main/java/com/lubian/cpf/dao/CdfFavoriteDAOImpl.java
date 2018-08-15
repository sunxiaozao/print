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
import com.lubian.cpf.dao.CdfFavoriteDAO;
import com.lubian.cpf.vo.CdfFavorite;

@Repository
public class CdfFavoriteDAOImpl extends BaseSqlMapDao implements CdfFavoriteDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CDF_FAVORITE.CountFindAllCDF_FAVORITE", null );
        return ret.intValue();
	}

	public CdfFavorite findByPK(CdfFavorite obj) {
		Object ret = queryForObject("CDF_FAVORITE.FindByPKCDF_FAVORITE", obj );
		if(ret !=null)
			return (CdfFavorite)ret;
		else 
			return null;
	}

	public List freeFind(CdfFavorite obj) {
		return queryForList("CDF_FAVORITE.FreeFindCDF_FAVORITE", obj );
	}

	public int countFreeFind(CdfFavorite obj) {
        Integer ret = (Integer) queryForObject("CDF_FAVORITE.CountFreeFindCDF_FAVORITE", obj );
        return ret.intValue();
	}

	public List freeFind(CdfFavorite obj, int pageIndex, int pageSize) {
		return getPaginatedList("CDF_FAVORITE.FreeFindCDF_FAVORITE", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CdfFavorite obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CdfFavorite){
	    	_hashmap = ((CdfFavorite)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CDF_FAVORITE.FreeFindCDF_FAVORITEOrdered", _hashmap);
	}

	public List freeFind(CdfFavorite obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CdfFavorite){
	    	_hashmap = ((CdfFavorite)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CDF_FAVORITE.FreeFindCDF_FAVORITEOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CdfFavorite> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CdfFavorite oneObj = (CdfFavorite)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CdfFavorite vo) {
        CdfFavorite obj = (CdfFavorite) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CdfFavorite obj) {

		obj.fixup();
        insert("CDF_FAVORITE.InsertCDF_FAVORITE", obj );
	}

	public void update(CdfFavorite obj) {

		obj.fixup();
		update( "CDF_FAVORITE.UpdateCDF_FAVORITE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CdfFavorite obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CDF_FAVORITE.DeleteCDF_FAVORITE", obj );

	}

	public void removeObjectTree(CdfFavorite obj) {

        obj.fixup();
        CdfFavorite oldObj = ( CdfFavorite )queryForObject("CDF_FAVORITE.FindByPKCDF_FAVORITE", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CDF_FAVORITE.DeleteCDF_FAVORITE", oldObj );

	}

	public boolean exists(CdfFavorite vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CdfFavorite obj = (CdfFavorite) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CDF_FAVORITE.CountCDF_FAVORITE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
