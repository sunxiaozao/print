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
import com.lubian.cpf.dao.CpfRelationDAO;
import com.lubian.cpf.vo.CpfRelation;

@Repository
public class CpfRelationDAOImpl extends BaseSqlMapDao implements CpfRelationDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_RELATION.CountFindAllCPF_RELATION", null );
        return ret.intValue();
	}

	public CpfRelation findByPK(CpfRelation obj) {
		Object ret = queryForObject("CPF_RELATION.FindByPKCPF_RELATION", obj );
		if(ret !=null)
			return (CpfRelation)ret;
		else 
			return null;
	}

	public List freeFind(CpfRelation obj) {
		return queryForList("CPF_RELATION.FreeFindCPF_RELATION", obj );
	}

	public int countFreeFind(CpfRelation obj) {
        Integer ret = (Integer) queryForObject("CPF_RELATION.CountFreeFindCPF_RELATION", obj );
        return ret.intValue();
	}

	public List freeFind(CpfRelation obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_RELATION.FreeFindCPF_RELATION", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfRelation obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfRelation){
	    	_hashmap = ((CpfRelation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_RELATION.FreeFindCPF_RELATIONOrdered", _hashmap);
	}

	public List freeFind(CpfRelation obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfRelation){
	    	_hashmap = ((CpfRelation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_RELATION.FreeFindCPF_RELATIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfRelation> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfRelation oneObj = (CpfRelation)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfRelation vo) {
        CpfRelation obj = (CpfRelation) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfRelation obj) {

		obj.fixup();
        insert("CPF_RELATION.InsertCPF_RELATION", obj );
	}

	public void update(CpfRelation obj) {

		obj.fixup();
		update( "CPF_RELATION.UpdateCPF_RELATION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfRelation obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_RELATION.DeleteCPF_RELATION", obj );

	}

	public void removeObjectTree(CpfRelation obj) {

        obj.fixup();
        CpfRelation oldObj = ( CpfRelation )queryForObject("CPF_RELATION.FindByPKCPF_RELATION", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_RELATION.DeleteCPF_RELATION", oldObj );

	}

	public boolean exists(CpfRelation vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfRelation obj = (CpfRelation) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_RELATION.CountCPF_RELATION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
