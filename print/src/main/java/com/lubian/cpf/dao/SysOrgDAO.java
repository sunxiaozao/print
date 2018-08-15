package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysOrg;

/**
 * SysOrgDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysOrgDAO {


	public int countFindAll();
	
	public SysOrg findByPK( SysOrg obj );
	public List freeFind( SysOrg obj );
	public int countFreeFind( SysOrg obj );
	public List freeFind( SysOrg obj, String orderByColName );
	public List freeFind( SysOrg obj, int pageIndex, int pageSize );
	public List freeFind( SysOrg obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysOrg>  objColl);
	public void save( SysOrg vo );
	public void insert( SysOrg vo );
	public void update( SysOrg vo );
	public void removeObjectTree( SysOrg obj );
	public void remove( SysOrg vo );
	public boolean exists( SysOrg vo );
}
