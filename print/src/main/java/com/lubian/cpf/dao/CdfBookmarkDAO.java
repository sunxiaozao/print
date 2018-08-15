package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CdfBookmark;

/**
 * CdfBookmarkDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CdfBookmarkDAO {


	public int countFindAll();
	
	public CdfBookmark findByPK( CdfBookmark obj );
	public List freeFind( CdfBookmark obj );
	public int countFreeFind( CdfBookmark obj );
	public List freeFind( CdfBookmark obj, String orderByColName );
	public List freeFind( CdfBookmark obj, int pageIndex, int pageSize );
	public List freeFind( CdfBookmark obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CdfBookmark>  objColl);
	public void save( CdfBookmark vo );
	public void insert( CdfBookmark vo );
	public void update( CdfBookmark vo );
	public void removeObjectTree( CdfBookmark obj );
	public void remove( CdfBookmark vo );
	public boolean exists( CdfBookmark vo );
}
