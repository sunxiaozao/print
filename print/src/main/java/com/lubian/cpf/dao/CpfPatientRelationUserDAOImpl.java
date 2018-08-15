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
import com.lubian.cpf.dao.CpfPatientRelationUserDAO;
import com.lubian.cpf.vo.CpfPatientRelationUser;

@Repository
public class CpfPatientRelationUserDAOImpl extends BaseSqlMapDao implements CpfPatientRelationUserDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_PATIENT_RELATION_USER.CountFindAllCPF_PATIENT_RELATION_USER", null );
        return ret.intValue();
	}

	public CpfPatientRelationUser findByPK(CpfPatientRelationUser obj) {
		Object ret = queryForObject("CPF_PATIENT_RELATION_USER.FindByPKCPF_PATIENT_RELATION_USER", obj );
		if(ret !=null)
			return (CpfPatientRelationUser)ret;
		else 
			return null;
	}

	public List freeFind(CpfPatientRelationUser obj) {
		return queryForList("CPF_PATIENT_RELATION_USER.FreeFindCPF_PATIENT_RELATION_USER", obj );
	}

	public int countFreeFind(CpfPatientRelationUser obj) {
        Integer ret = (Integer) queryForObject("CPF_PATIENT_RELATION_USER.CountFreeFindCPF_PATIENT_RELATION_USER", obj );
        return ret.intValue();
	}

	public List freeFind(CpfPatientRelationUser obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_PATIENT_RELATION_USER.FreeFindCPF_PATIENT_RELATION_USER", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfPatientRelationUser obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfPatientRelationUser){
	    	_hashmap = ((CpfPatientRelationUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_PATIENT_RELATION_USER.FreeFindCPF_PATIENT_RELATION_USEROrdered", _hashmap);
	}

	public List freeFind(CpfPatientRelationUser obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfPatientRelationUser){
	    	_hashmap = ((CpfPatientRelationUser)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_PATIENT_RELATION_USER.FreeFindCPF_PATIENT_RELATION_USEROrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfPatientRelationUser> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfPatientRelationUser oneObj = (CpfPatientRelationUser)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfPatientRelationUser vo) {
        CpfPatientRelationUser obj = (CpfPatientRelationUser) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfPatientRelationUser obj) {

		obj.fixup();
        insert("CPF_PATIENT_RELATION_USER.InsertCPF_PATIENT_RELATION_USER", obj );
	}

	public void update(CpfPatientRelationUser obj) {

		obj.fixup();
		update( "CPF_PATIENT_RELATION_USER.UpdateCPF_PATIENT_RELATION_USER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfPatientRelationUser obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_PATIENT_RELATION_USER.DeleteCPF_PATIENT_RELATION_USER", obj );

	}

	public void removeObjectTree(CpfPatientRelationUser obj) {

        obj.fixup();
        CpfPatientRelationUser oldObj = ( CpfPatientRelationUser )queryForObject("CPF_PATIENT_RELATION_USER.FindByPKCPF_PATIENT_RELATION_USER", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_PATIENT_RELATION_USER.DeleteCPF_PATIENT_RELATION_USER", oldObj );

	}

	public boolean exists(CpfPatientRelationUser vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfPatientRelationUser obj = (CpfPatientRelationUser) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_PATIENT_RELATION_USER.CountCPF_PATIENT_RELATION_USER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
