package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysMailJob;

/**
 * SysMailJobDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysMailJobDAO {


	public int countFindAll();
	
	public SysMailJob findByPK( SysMailJob obj );
	public List freeFind( SysMailJob obj );
	public int countFreeFind( SysMailJob obj );
	public List freeFind( SysMailJob obj, String orderByColName );
	public List freeFind( SysMailJob obj, int pageIndex, int pageSize );
	public List freeFind( SysMailJob obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysMailJob>  objColl);
	public void save( SysMailJob vo );
	public void insert( SysMailJob vo );
	public void update( SysMailJob vo );
	public void removeObjectTree( SysMailJob obj );
	public void remove( SysMailJob vo );
	public boolean exists( SysMailJob vo );
}
