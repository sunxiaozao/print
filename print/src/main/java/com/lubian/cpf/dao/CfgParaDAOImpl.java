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
import com.lubian.cpf.dao.CfgParaDAO;
import com.lubian.cpf.vo.CfgPara;

@Repository
public class CfgParaDAOImpl extends BaseSqlMapDao implements CfgParaDAO {


	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CFG_PARA.CountFindAllCFG_PARA", null );
        return ret.intValue();
	}

	public CfgPara findByPK(CfgPara obj) {
		Object ret = queryForObject("CFG_PARA.FindByPKCFG_PARA", obj );
		if(ret !=null)
			return (CfgPara)ret;
		else 
			return null;
	}

	public List freeFind(CfgPara obj) {
		return queryForList("CFG_PARA.FreeFindCFG_PARA", obj );
	}

	public int countFreeFind(CfgPara obj) {
        Integer ret = (Integer) queryForObject("CFG_PARA.CountFreeFindCFG_PARA", obj );
        return ret.intValue();
	}

	public List freeFind(CfgPara obj, int pageIndex, int pageSize) {
		return getPaginatedList("CFG_PARA.FreeFindCFG_PARA", obj, pageIndex, pageSize );
	}
		
	public List freeFind(CfgPara obj, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CfgPara){
	    	_hashmap = ((CfgPara)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CFG_PARA.FreeFindCFG_PARAOrdered", _hashmap);
	}

	public List freeFind(CfgPara obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap _hashmap = new HashMap();
	    if(obj instanceof CfgPara){
	    	_hashmap = ((CfgPara)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CFG_PARA.FreeFindCFG_PARAOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(Collection<CfgPara> objColl) {
        if ( objColl != null ) {
            Iterator it = objColl.iterator();
            while ( it.hasNext() ) {
            	CfgPara oneObj = (CfgPara)it.next();
				save( oneObj );
            }
        }
	}

	public void save(CfgPara vo) {
        CfgPara obj = (CfgPara) vo;

        if(exists( obj ) ) {
            update( obj );
        } else {
            insert( obj );
        }
	}

	public void insert(CfgPara obj) {

		obj.fixup();
        insert("CFG_PARA.InsertCFG_PARA", obj );
	}

	public void update(CfgPara obj) {

		obj.fixup();
		update( "CFG_PARA.UpdateCFG_PARA", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(CfgPara obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete( "CFG_PARA.DeleteCFG_PARA", obj );

	}

	public void removeObjectTree(CfgPara obj) {

        obj.fixup();
        CfgPara oldObj = ( CfgPara )queryForObject("CFG_PARA.FindByPKCFG_PARA", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete( "CFG_PARA.DeleteCFG_PARA", oldObj );

	}

	public boolean exists(CfgPara vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CfgPara obj = (CfgPara) vo;

        keysFilled = keysFilled && ( null != obj.getParaCode() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CFG_PARA.CountCFG_PARA", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	


	public List getParaType(){
		HashMap _hashmap = new HashMap();
		return queryForList( "CFG_PARA.GetParaTypeCFG_PARA", _hashmap );
	}

	public int countGetParaType(){
		HashMap _hashmap = new HashMap();
		Integer retInt=(Integer)queryForObject("CFG_PARA.CountGetParaTypeCFG_PARA", _hashmap );
		return retInt.intValue();
	}

	public List getParaType(  int pageIndex, int pageSize){
		HashMap _hashmap = new HashMap();
		return getPaginatedList( "CFG_PARA.GetParaTypeCFG_PARA", _hashmap, pageIndex, pageSize );
	}

}
