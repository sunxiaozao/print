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
import com.lubian.cpf.dao.CpfDownloadDAO;
import com.lubian.cpf.vo.CpfDownload;

@Repository
public class CpfDownloadDAOImpl extends BaseSqlMapDao implements CpfDownloadDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_DOWNLOAD.CountFindAllCPF_DOWNLOAD", null );
        return ret.intValue();
	}

	public CpfDownload findByPK(CpfDownload obj) {
		Object ret = queryForObject("CPF_DOWNLOAD.FindByPKCPF_DOWNLOAD", obj );
		if(ret !=null)
			return (CpfDownload)ret;
		else 
			return null;
	}

	public List freeFind(CpfDownload obj) {
		return queryForList("CPF_DOWNLOAD.FreeFindCPF_DOWNLOAD", obj );
	}

	public int countFreeFind(CpfDownload obj) {
        Integer ret = (Integer) queryForObject("CPF_DOWNLOAD.CountFreeFindCPF_DOWNLOAD", obj );
        return ret.intValue();
	}

	public List freeFind(CpfDownload obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_DOWNLOAD.FreeFindCPF_DOWNLOAD", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfDownload obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfDownload){
	    	_hashmap = ((CpfDownload)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_DOWNLOAD.FreeFindCPF_DOWNLOADOrdered", _hashmap);
	}

	public List freeFind(CpfDownload obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfDownload){
	    	_hashmap = ((CpfDownload)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_DOWNLOAD.FreeFindCPF_DOWNLOADOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfDownload> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfDownload oneObj = (CpfDownload)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfDownload vo) {
        CpfDownload obj = (CpfDownload) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfDownload obj) {

		obj.fixup();
        insert("CPF_DOWNLOAD.InsertCPF_DOWNLOAD", obj );
	}

	public void update(CpfDownload obj) {

		obj.fixup();
		update( "CPF_DOWNLOAD.UpdateCPF_DOWNLOAD", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfDownload obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_DOWNLOAD.DeleteCPF_DOWNLOAD", obj );

	}

	public void removeObjectTree(CpfDownload obj) {

        obj.fixup();
        CpfDownload oldObj = ( CpfDownload )queryForObject("CPF_DOWNLOAD.FindByPKCPF_DOWNLOAD", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_DOWNLOAD.DeleteCPF_DOWNLOAD", oldObj );

	}

	public boolean exists(CpfDownload vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfDownload obj = (CpfDownload) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_DOWNLOAD.CountCPF_DOWNLOAD", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
