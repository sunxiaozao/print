package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfRelation;

/**
 * CpfRelationDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfRelationDAO {


	public int countFindAll();
	
	public CpfRelation findByPK( CpfRelation obj );
	public List freeFind( CpfRelation obj );
	public int countFreeFind( CpfRelation obj );
	public List freeFind( CpfRelation obj, String orderByColName );
	public List freeFind( CpfRelation obj, int pageIndex, int pageSize );
	public List freeFind( CpfRelation obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfRelation>  objColl);
	public void save( CpfRelation vo );
	public void insert( CpfRelation vo );
	public void update( CpfRelation vo );
	public void removeObjectTree( CpfRelation obj );
	public void remove( CpfRelation vo );
	public boolean exists( CpfRelation vo );
}
