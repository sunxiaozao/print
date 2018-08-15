package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysLogs;

/**
 * SysLogsDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysLogsDAO {


	public int countFindAll();
	
	public SysLogs findByPK( SysLogs obj );
	public List freeFind( SysLogs obj );
	public int countFreeFind( SysLogs obj );
	public List freeFind( SysLogs obj, String orderByColName );
	public List freeFind( SysLogs obj, int pageIndex, int pageSize );
	public List freeFind( SysLogs obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysLogs>  objColl);
	public void save( SysLogs vo );
	public void insert( SysLogs vo );
	public void update( SysLogs vo );
	public void removeObjectTree( SysLogs obj );
	public void remove( SysLogs vo );
	public boolean exists( SysLogs vo );
}
