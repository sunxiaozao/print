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
import com.lubian.cpf.dao.CpfSharingDAO;
import com.lubian.cpf.vo.CpfSharing;

@Repository
public class CpfSharingDAOImpl extends BaseSqlMapDao implements CpfSharingDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_SHARING.CountFindAllCPF_SHARING", null );
        return ret.intValue();
	}

	public CpfSharing findByPK(CpfSharing obj) {
		Object ret = queryForObject("CPF_SHARING.FindByPKCPF_SHARING", obj );
		if(ret !=null)
			return (CpfSharing)ret;
		else 
			return null;
	}

	public List freeFind(CpfSharing obj) {
		return queryForList("CPF_SHARING.FreeFindCPF_SHARING", obj );
	}

	public int countFreeFind(CpfSharing obj) {
        Integer ret = (Integer) queryForObject("CPF_SHARING.CountFreeFindCPF_SHARING", obj );
        return ret.intValue();
	}

	public List freeFind(CpfSharing obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_SHARING.FreeFindCPF_SHARING", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfSharing obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfSharing){
	    	_hashmap = ((CpfSharing)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_SHARING.FreeFindCPF_SHARINGOrdered", _hashmap);
	}

	public List freeFind(CpfSharing obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfSharing){
	    	_hashmap = ((CpfSharing)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_SHARING.FreeFindCPF_SHARINGOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfSharing> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfSharing oneObj = (CpfSharing)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfSharing vo) {
        CpfSharing obj = (CpfSharing) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfSharing obj) {

		obj.fixup();
        insert("CPF_SHARING.InsertCPF_SHARING", obj );
	}

	public void update(CpfSharing obj) {

		obj.fixup();
		update( "CPF_SHARING.UpdateCPF_SHARING", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfSharing obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_SHARING.DeleteCPF_SHARING", obj );

	}

	public void removeObjectTree(CpfSharing obj) {

        obj.fixup();
        CpfSharing oldObj = ( CpfSharing )queryForObject("CPF_SHARING.FindByPKCPF_SHARING", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_SHARING.DeleteCPF_SHARING", oldObj );

	}

	public boolean exists(CpfSharing vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfSharing obj = (CpfSharing) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_SHARING.CountCPF_SHARING", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
