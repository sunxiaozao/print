package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysNotification;

/**
 * SysNotificationDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysNotificationDAO {


	public int countFindAll();
	
	public SysNotification findByPK( SysNotification obj );
	public List freeFind( SysNotification obj );
	public int countFreeFind( SysNotification obj );
	public List freeFind( SysNotification obj, String orderByColName );
	public List freeFind( SysNotification obj, int pageIndex, int pageSize );
	public List freeFind( SysNotification obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysNotification>  objColl);
	public void save( SysNotification vo );
	public void insert( SysNotification vo );
	public void update( SysNotification vo );
	public void removeObjectTree( SysNotification obj );
	public void remove( SysNotification vo );
	public boolean exists( SysNotification vo );
}
