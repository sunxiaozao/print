package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysMailJobItem;

/**
 * SysMailJobItemDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysMailJobItemDAO {


	public int countFindAll();
	
	public SysMailJobItem findByPK( SysMailJobItem obj );
	public List freeFind( SysMailJobItem obj );
	public int countFreeFind( SysMailJobItem obj );
	public List freeFind( SysMailJobItem obj, String orderByColName );
	public List freeFind( SysMailJobItem obj, int pageIndex, int pageSize );
	public List freeFind( SysMailJobItem obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysMailJobItem>  objColl);
	public void save( SysMailJobItem vo );
	public void insert( SysMailJobItem vo );
	public void update( SysMailJobItem vo );
	public void removeObjectTree( SysMailJobItem obj );
	public void remove( SysMailJobItem vo );
	public boolean exists( SysMailJobItem vo );
}
