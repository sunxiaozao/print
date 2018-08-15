package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfShare;

/**
 * CpfShareDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfShareDAO {


	public List searchShareByEmailMobile(Object email,Object mobile,Object status);
	public int countSearchShareByEmailMobile(Object email,Object mobile,Object status);
	public List searchShareByEmailMobile(Object email,Object mobile,Object status , int pageIndex, int pageSize);

	public List searchByIdStr(Object idStr,Object status);
	public int countSearchByIdStr(Object idStr,Object status);
	public List searchByIdStr(Object idStr,Object status , int pageIndex, int pageSize);

	public List searchByEmailOrMobile(Object emailStr,Object mobileStr,Object status);
	public int countSearchByEmailOrMobile(Object emailStr,Object mobileStr,Object status);
	public List searchByEmailOrMobile(Object emailStr,Object mobileStr,Object status , int pageIndex, int pageSize);

	public int countFindAll();
	
	public CpfShare findByPK( CpfShare obj );
	public List freeFind( CpfShare obj );
	public int countFreeFind( CpfShare obj );
	public List freeFind( CpfShare obj, String orderByColName );
	public List freeFind( CpfShare obj, int pageIndex, int pageSize );
	public List freeFind( CpfShare obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfShare>  objColl);
	public void save( CpfShare vo );
	public void insert( CpfShare vo );
	public void update( CpfShare vo );
	public void removeObjectTree( CpfShare obj );
	public void remove( CpfShare vo );
	public boolean exists( CpfShare vo );
}
