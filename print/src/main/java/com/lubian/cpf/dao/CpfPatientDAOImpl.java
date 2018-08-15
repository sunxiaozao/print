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
import com.lubian.cpf.dao.CpfPatientDAO;
import com.lubian.cpf.vo.CpfPatient;

@Repository
public class CpfPatientDAOImpl extends BaseSqlMapDao implements CpfPatientDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_PATIENT.CountFindAllCPF_PATIENT", null );
        return ret.intValue();
	}

	public CpfPatient findByPK(CpfPatient obj) {
		Object ret = queryForObject("CPF_PATIENT.FindByPKCPF_PATIENT", obj );
		if(ret !=null)
			return (CpfPatient)ret;
		else 
			return null;
	}

	public List freeFind(CpfPatient obj) {
		return queryForList("CPF_PATIENT.FreeFindCPF_PATIENT", obj );
	}

	public int countFreeFind(CpfPatient obj) {
        Integer ret = (Integer) queryForObject("CPF_PATIENT.CountFreeFindCPF_PATIENT", obj );
        return ret.intValue();
	}

	public List freeFind(CpfPatient obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_PATIENT.FreeFindCPF_PATIENT", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfPatient obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfPatient){
	    	_hashmap = ((CpfPatient)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_PATIENT.FreeFindCPF_PATIENTOrdered", _hashmap);
	}

	public List freeFind(CpfPatient obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfPatient){
	    	_hashmap = ((CpfPatient)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_PATIENT.FreeFindCPF_PATIENTOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfPatient> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfPatient oneObj = (CpfPatient)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfPatient vo) {
        CpfPatient obj = (CpfPatient) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfPatient obj) {

		obj.fixup();
        insert("CPF_PATIENT.InsertCPF_PATIENT", obj );
	}

	public void update(CpfPatient obj) {

		obj.fixup();
		update( "CPF_PATIENT.UpdateCPF_PATIENT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfPatient obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_PATIENT.DeleteCPF_PATIENT", obj );

	}

	public void removeObjectTree(CpfPatient obj) {

        obj.fixup();
        CpfPatient oldObj = ( CpfPatient )queryForObject("CPF_PATIENT.FindByPKCPF_PATIENT", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_PATIENT.DeleteCPF_PATIENT", oldObj );

	}

	public boolean exists(CpfPatient vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfPatient obj = (CpfPatient) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_PATIENT.CountCPF_PATIENT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
