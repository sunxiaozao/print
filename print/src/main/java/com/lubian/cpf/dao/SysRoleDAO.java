package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysRole;

/**
 * SysRoleDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysRoleDAO {


	public int countFindAll();
	
	public SysRole findByPK( SysRole obj );
	public List freeFind( SysRole obj );
	public int countFreeFind( SysRole obj );
	public List freeFind( SysRole obj, String orderByColName );
	public List freeFind( SysRole obj, int pageIndex, int pageSize );
	public List freeFind( SysRole obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysRole>  objColl);
	public void save( SysRole vo );
	public void insert( SysRole vo );
	public void update( SysRole vo );
	public void removeObjectTree( SysRole obj );
	public void remove( SysRole vo );
	public boolean exists( SysRole vo );
}
