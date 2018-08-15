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
import com.lubian.cpf.dao.CpfCaseFolderDAO;
import com.lubian.cpf.vo.CpfCaseFolder;

@Repository
public class CpfCaseFolderDAOImpl extends BaseSqlMapDao implements CpfCaseFolderDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_CASE_FOLDER.CountFindAllCPF_CASE_FOLDER", null );
        return ret.intValue();
	}

	public CpfCaseFolder findByPK(CpfCaseFolder obj) {
		Object ret = queryForObject("CPF_CASE_FOLDER.FindByPKCPF_CASE_FOLDER", obj );
		if(ret !=null)
			return (CpfCaseFolder)ret;
		else 
			return null;
	}

	public List freeFind(CpfCaseFolder obj) {
		return queryForList("CPF_CASE_FOLDER.FreeFindCPF_CASE_FOLDER", obj );
	}

	public int countFreeFind(CpfCaseFolder obj) {
        Integer ret = (Integer) queryForObject("CPF_CASE_FOLDER.CountFreeFindCPF_CASE_FOLDER", obj );
        return ret.intValue();
	}

	public List freeFind(CpfCaseFolder obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_CASE_FOLDER.FreeFindCPF_CASE_FOLDER", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfCaseFolder obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseFolder){
	    	_hashmap = ((CpfCaseFolder)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_CASE_FOLDER.FreeFindCPF_CASE_FOLDEROrdered", _hashmap);
	}

	public List freeFind(CpfCaseFolder obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseFolder){
	    	_hashmap = ((CpfCaseFolder)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_CASE_FOLDER.FreeFindCPF_CASE_FOLDEROrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfCaseFolder> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfCaseFolder oneObj = (CpfCaseFolder)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfCaseFolder vo) {
        CpfCaseFolder obj = (CpfCaseFolder) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfCaseFolder obj) {

		obj.fixup();
        insert("CPF_CASE_FOLDER.InsertCPF_CASE_FOLDER", obj );
	}

	public void update(CpfCaseFolder obj) {

		obj.fixup();
		update( "CPF_CASE_FOLDER.UpdateCPF_CASE_FOLDER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfCaseFolder obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_CASE_FOLDER.DeleteCPF_CASE_FOLDER", obj );

	}

	public void removeObjectTree(CpfCaseFolder obj) {

        obj.fixup();
        CpfCaseFolder oldObj = ( CpfCaseFolder )queryForObject("CPF_CASE_FOLDER.FindByPKCPF_CASE_FOLDER", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_CASE_FOLDER.DeleteCPF_CASE_FOLDER", oldObj );

	}

	public boolean exists(CpfCaseFolder vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfCaseFolder obj = (CpfCaseFolder) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_CASE_FOLDER.CountCPF_CASE_FOLDER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
