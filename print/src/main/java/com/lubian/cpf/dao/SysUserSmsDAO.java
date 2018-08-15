package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysUserSms;

/**
 * SysUserSmsDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysUserSmsDAO {


	public int countFindAll();
	
	public SysUserSms findByPK( SysUserSms obj );
	public List freeFind( SysUserSms obj );
	public int countFreeFind( SysUserSms obj );
	public List freeFind( SysUserSms obj, String orderByColName );
	public List freeFind( SysUserSms obj, int pageIndex, int pageSize );
	public List freeFind( SysUserSms obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysUserSms>  objColl);
	public void save( SysUserSms vo );
	public void insert( SysUserSms vo );
	public void update( SysUserSms vo );
	public void removeObjectTree( SysUserSms obj );
	public void remove( SysUserSms vo );
	public boolean exists( SysUserSms vo );
}
