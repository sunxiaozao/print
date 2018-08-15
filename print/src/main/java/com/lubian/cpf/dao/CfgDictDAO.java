package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CfgDict;

/**
 * CfgDictDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CfgDictDAO {


	public int countFindAll();
	
	public CfgDict findByPK( CfgDict obj );
	public List freeFind( CfgDict obj );
	public int countFreeFind( CfgDict obj );
	public List freeFind( CfgDict obj, String orderByColName );
	public List freeFind( CfgDict obj, int pageIndex, int pageSize );
	public List freeFind( CfgDict obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CfgDict>  objColl);
	public void save( CfgDict vo );
	public void insert( CfgDict vo );
	public void update( CfgDict vo );
	public void removeObjectTree( CfgDict obj );
	public void remove( CfgDict vo );
	public boolean exists( CfgDict vo );
}
