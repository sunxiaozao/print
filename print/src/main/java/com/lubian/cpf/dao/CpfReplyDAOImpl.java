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
import com.lubian.cpf.dao.CpfReplyDAO;
import com.lubian.cpf.vo.CpfReply;

@Repository
public class CpfReplyDAOImpl extends BaseSqlMapDao implements CpfReplyDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CPF_REPLY.CountFindAllCPF_REPLY", null );
        return ret.intValue();
	}

	public CpfReply findByPK(CpfReply obj) {
		Object ret = queryForObject("CPF_REPLY.FindByPKCPF_REPLY", obj );
		if(ret !=null)
			return (CpfReply)ret;
		else 
			return null;
	}

	public List freeFind(CpfReply obj) {
		return queryForList("CPF_REPLY.FreeFindCPF_REPLY", obj );
	}

	public int countFreeFind(CpfReply obj) {
        Integer ret = (Integer) queryForObject("CPF_REPLY.CountFreeFindCPF_REPLY", obj );
        return ret.intValue();
	}

	public List freeFind(CpfReply obj, int pageIndex, int pageSize) {
		return getPaginatedList("CPF_REPLY.FreeFindCPF_REPLY", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CpfReply obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfReply){
	    	_hashmap = ((CpfReply)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CPF_REPLY.FreeFindCPF_REPLYOrdered", _hashmap);
	}

	public List freeFind(CpfReply obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CpfReply){
	    	_hashmap = ((CpfReply)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CPF_REPLY.FreeFindCPF_REPLYOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CpfReply> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpfReply oneObj = (CpfReply)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CpfReply vo) {
        CpfReply obj = (CpfReply) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CpfReply obj) {

		obj.fixup();
        insert("CPF_REPLY.InsertCPF_REPLY", obj );
	}

	public void update(CpfReply obj) {

		obj.fixup();
		update( "CPF_REPLY.UpdateCPF_REPLY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CpfReply obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CPF_REPLY.DeleteCPF_REPLY", obj );

	}

	public void removeObjectTree(CpfReply obj) {

        obj.fixup();
        CpfReply oldObj = ( CpfReply )queryForObject("CPF_REPLY.FindByPKCPF_REPLY", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CPF_REPLY.DeleteCPF_REPLY", oldObj );

	}

	public boolean exists(CpfReply vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpfReply obj = (CpfReply) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CPF_REPLY.CountCPF_REPLY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


}
