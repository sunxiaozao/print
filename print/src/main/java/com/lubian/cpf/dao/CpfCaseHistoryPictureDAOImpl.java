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
import com.lubian.cpf.dao.CpfCaseHistoryPictureDAO;
import com.lubian.cpf.vo.CpfCaseHistoryPicture;

@Repository
public class CpfCaseHistoryPictureDAOImpl extends BaseSqlMapDao implements CpfCaseHistoryPictureDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY_PICTURE.CountFindAllCPF_CASE_HISTORY_PICTURE", null );
        return ret.intValue();
	}

	public CpfCaseHistoryPicture findByPK(CpfCaseHistoryPicture obj) {
		Object ret = queryForObject("CPF_CASE_HISTORY_PICTURE.FindByPKCPF_CASE_HISTORY_PICTURE", obj );
		if(ret !=null)
			return (CpfCaseHistoryPicture)ret;
		else 
			return null;
	}

	public List freeFind(CpfCaseHistoryPicture obj) {
		return queryForList("CPF_CASE_HISTORY_PICTURE.FreeFindCPF_CASE_HISTORY_PICTURE", obj );
	}

	public int countFreeFind(CpfCaseHistoryPicture obj) {
        Integer ret = (Integer) queryForObject("CPF_CASE_HISTORY_PICTURE.CountFreeFindCPF_CASE_HISTORY_PICTURE", obj );
        return ret.intValue();
	}

	public List freeFind(CpfCaseHistoryPicture obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_CASE_HISTORY_PICTURE.FreeFindCPF_CASE_HISTORY_PICTURE", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfCaseHistoryPicture obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistoryPicture){
	    	_hashmap = ((CpfCaseHistoryPicture)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_CASE_HISTORY_PICTURE.FreeFindCPF_CASE_HISTORY_PICTUREOrdered", _hashmap);
	}

	public List freeFind(CpfCaseHistoryPicture obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseHistoryPicture){
	    	_hashmap = ((CpfCaseHistoryPicture)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_CASE_HISTORY_PICTURE.FreeFindCPF_CASE_HISTORY_PICTUREOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfCaseHistoryPicture> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfCaseHistoryPicture oneObj = (CpfCaseHistoryPicture)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfCaseHistoryPicture vo) {
        CpfCaseHistoryPicture obj = (CpfCaseHistoryPicture) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfCaseHistoryPicture obj) {

		obj.fixup();
        insert("CPF_CASE_HISTORY_PICTURE.InsertCPF_CASE_HISTORY_PICTURE", obj );
	}

	public void update(CpfCaseHistoryPicture obj) {

		obj.fixup();
		update( "CPF_CASE_HISTORY_PICTURE.UpdateCPF_CASE_HISTORY_PICTURE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfCaseHistoryPicture obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_CASE_HISTORY_PICTURE.DeleteCPF_CASE_HISTORY_PICTURE", obj );

	}

	public void removeObjectTree(CpfCaseHistoryPicture obj) {

        obj.fixup();
        CpfCaseHistoryPicture oldObj = ( CpfCaseHistoryPicture )queryForObject("CPF_CASE_HISTORY_PICTURE.FindByPKCPF_CASE_HISTORY_PICTURE", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_CASE_HISTORY_PICTURE.DeleteCPF_CASE_HISTORY_PICTURE", oldObj );

	}

	public boolean exists(CpfCaseHistoryPicture vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfCaseHistoryPicture obj = (CpfCaseHistoryPicture) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_CASE_HISTORY_PICTURE.CountCPF_CASE_HISTORY_PICTURE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
