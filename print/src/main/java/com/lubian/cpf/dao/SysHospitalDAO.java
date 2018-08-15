package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysHospital;

/**
 * SysHospitalDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysHospitalDAO {


	public List searchByIdOrParentId(Object id,Object parentId);
	public int countSearchByIdOrParentId(Object id,Object parentId);
	public List searchByIdOrParentId(Object id,Object parentId , int pageIndex, int pageSize);

	public int countFindAll();
	
	public SysHospital findByPK( SysHospital obj );
	public List freeFind( SysHospital obj );
	public int countFreeFind( SysHospital obj );
	public List freeFind( SysHospital obj, String orderByColName );
	public List freeFind( SysHospital obj, int pageIndex, int pageSize );
	public List freeFind( SysHospital obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysHospital>  objColl);
	public void save( SysHospital vo );
	public void insert( SysHospital vo );
	public void update( SysHospital vo );
	public void removeObjectTree( SysHospital obj );
	public void remove( SysHospital vo );
	public boolean exists( SysHospital vo );
}
