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
import com.lubian.cpf.dao.CpfCaseFolderAuthorizationDAO;
import com.lubian.cpf.vo.CpfCaseFolderAuthorization;

@Repository
public class CpfCaseFolderAuthorizationDAOImpl extends BaseSqlMapDao implements CpfCaseFolderAuthorizationDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_CASE_FOLDER_AUTHORIZATION.CountFindAllCPF_CASE_FOLDER_AUTHORIZATION", null );
        return ret.intValue();
	}

	public CpfCaseFolderAuthorization findByPK(CpfCaseFolderAuthorization obj) {
		Object ret = queryForObject("CPF_CASE_FOLDER_AUTHORIZATION.FindByPKCPF_CASE_FOLDER_AUTHORIZATION", obj );
		if(ret !=null)
			return (CpfCaseFolderAuthorization)ret;
		else 
			return null;
	}

	public List freeFind(CpfCaseFolderAuthorization obj) {
		return queryForList("CPF_CASE_FOLDER_AUTHORIZATION.FreeFindCPF_CASE_FOLDER_AUTHORIZATION", obj );
	}

	public int countFreeFind(CpfCaseFolderAuthorization obj) {
        Integer ret = (Integer) queryForObject("CPF_CASE_FOLDER_AUTHORIZATION.CountFreeFindCPF_CASE_FOLDER_AUTHORIZATION", obj );
        return ret.intValue();
	}

	public List freeFind(CpfCaseFolderAuthorization obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_CASE_FOLDER_AUTHORIZATION.FreeFindCPF_CASE_FOLDER_AUTHORIZATION", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfCaseFolderAuthorization obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseFolderAuthorization){
	    	_hashmap = ((CpfCaseFolderAuthorization)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_CASE_FOLDER_AUTHORIZATION.FreeFindCPF_CASE_FOLDER_AUTHORIZATIONOrdered", _hashmap);
	}

	public List freeFind(CpfCaseFolderAuthorization obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfCaseFolderAuthorization){
	    	_hashmap = ((CpfCaseFolderAuthorization)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_CASE_FOLDER_AUTHORIZATION.FreeFindCPF_CASE_FOLDER_AUTHORIZATIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfCaseFolderAuthorization> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfCaseFolderAuthorization oneObj = (CpfCaseFolderAuthorization)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfCaseFolderAuthorization vo) {
        CpfCaseFolderAuthorization obj = (CpfCaseFolderAuthorization) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfCaseFolderAuthorization obj) {

		obj.fixup();
        insert("CPF_CASE_FOLDER_AUTHORIZATION.InsertCPF_CASE_FOLDER_AUTHORIZATION", obj );
	}

	public void update(CpfCaseFolderAuthorization obj) {

		obj.fixup();
		update( "CPF_CASE_FOLDER_AUTHORIZATION.UpdateCPF_CASE_FOLDER_AUTHORIZATION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfCaseFolderAuthorization obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_CASE_FOLDER_AUTHORIZATION.DeleteCPF_CASE_FOLDER_AUTHORIZATION", obj );

	}

	public void removeObjectTree(CpfCaseFolderAuthorization obj) {

        obj.fixup();
        CpfCaseFolderAuthorization oldObj = ( CpfCaseFolderAuthorization )queryForObject("CPF_CASE_FOLDER_AUTHORIZATION.FindByPKCPF_CASE_FOLDER_AUTHORIZATION", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_CASE_FOLDER_AUTHORIZATION.DeleteCPF_CASE_FOLDER_AUTHORIZATION", oldObj );

	}

	public boolean exists(CpfCaseFolderAuthorization vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfCaseFolderAuthorization obj = (CpfCaseFolderAuthorization) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_CASE_FOLDER_AUTHORIZATION.CountCPF_CASE_FOLDER_AUTHORIZATION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


	public List conditionFind(Object folderId,Object hospitalId,Object departmentId,Object doctor,Object patientId_instr,Object addSQL){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "folderId", folderId );
		_hashmap.put( "hospitalId", hospitalId );
		_hashmap.put( "departmentId", departmentId );
		_hashmap.put( "doctor", doctor );
		_hashmap.put( "patientId_instr", patientId_instr );
		_hashmap.put( "addSQL", addSQL );
		return queryForList( "CPF_CASE_FOLDER_AUTHORIZATION.ConditionFindCPF_CASE_FOLDER_AUTHORIZATION", _hashmap );
	}

	public int countConditionFind(Object folderId,Object hospitalId,Object departmentId,Object doctor,Object patientId_instr,Object addSQL){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "folderId", folderId );
		_hashmap.put( "hospitalId", hospitalId );
		_hashmap.put( "departmentId", departmentId );
		_hashmap.put( "doctor", doctor );
		_hashmap.put( "patientId_instr", patientId_instr );
		_hashmap.put( "addSQL", addSQL );
		Integer retInt=(Integer)queryForObject("CPF_CASE_FOLDER_AUTHORIZATION.CountConditionFindCPF_CASE_FOLDER_AUTHORIZATION", _hashmap );
		return retInt.intValue();
	}

	public List conditionFind(Object folderId,Object hospitalId,Object departmentId,Object doctor,Object patientId_instr,Object addSQL , int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		_hashmap.put( "folderId", folderId );
		_hashmap.put( "hospitalId", hospitalId );
		_hashmap.put( "departmentId", departmentId );
		_hashmap.put( "doctor", doctor );
		_hashmap.put( "patientId_instr", patientId_instr );
		_hashmap.put( "addSQL", addSQL );
		return getPaginatedList( "CPF_CASE_FOLDER_AUTHORIZATION.ConditionFindCPF_CASE_FOLDER_AUTHORIZATION", _hashmap, pageIndex, pageSize );
	}

}
