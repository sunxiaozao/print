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
import com.lubian.cpf.dao.CfgDictDAO;
import com.lubian.cpf.vo.CfgDict;

@Repository
public class CfgDictDAOImpl extends BaseSqlMapDao implements CfgDictDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CFG_DICT.CountFindAllCFG_DICT", null );
        return ret.intValue();
	}

	public CfgDict findByPK(CfgDict obj) {
		Object ret = queryForObject("CFG_DICT.FindByPKCFG_DICT", obj );
		if(ret !=null)
			return (CfgDict)ret;
		else 
			return null;
	}

	public List freeFind(CfgDict obj) {
		return queryForList("CFG_DICT.FreeFindCFG_DICT", obj );
	}

	public int countFreeFind(CfgDict obj) {
        Integer ret = (Integer) queryForObject("CFG_DICT.CountFreeFindCFG_DICT", obj );
        return ret.intValue();
	}

	public List freeFind(CfgDict obj, int pageIndex, int pageSize) {
		return getPaginatedList("CFG_DICT.FreeFindCFG_DICT", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CfgDict obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CfgDict){
	    	_hashmap = ((CfgDict)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CFG_DICT.FreeFindCFG_DICTOrdered", _hashmap);
	}

	public List freeFind(CfgDict obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CfgDict){
	    	_hashmap = ((CfgDict)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CFG_DICT.FreeFindCFG_DICTOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CfgDict> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CfgDict oneObj = (CfgDict)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CfgDict vo) {
        CfgDict obj = (CfgDict) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CfgDict obj) {

		obj.fixup();
        insert("CFG_DICT.InsertCFG_DICT", obj );
	}

	public void update(CfgDict obj) {

		obj.fixup();
		update( "CFG_DICT.UpdateCFG_DICT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CfgDict obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CFG_DICT.DeleteCFG_DICT", obj );

	}

	public void removeObjectTree(CfgDict obj) {

        obj.fixup();
        CfgDict oldObj = ( CfgDict )queryForObject("CFG_DICT.FindByPKCFG_DICT", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CFG_DICT.DeleteCFG_DICT", oldObj );

	}

	public boolean exists(CfgDict vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CfgDict obj = (CfgDict) vo;

        keysFilled = keysFilled && ( null != obj.getDictId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CFG_DICT.CountCFG_DICT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
