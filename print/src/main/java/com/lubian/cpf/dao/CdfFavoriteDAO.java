package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CdfFavorite;

/**
 * CdfFavoriteDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CdfFavoriteDAO {


	public int countFindAll();
	
	public CdfFavorite findByPK( CdfFavorite obj );
	public List freeFind( CdfFavorite obj );
	public int countFreeFind( CdfFavorite obj );
	public List freeFind( CdfFavorite obj, String orderByColName );
	public List freeFind( CdfFavorite obj, int pageIndex, int pageSize );
	public List freeFind( CdfFavorite obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CdfFavorite>  objColl);
	public void save( CdfFavorite vo );
	public void insert( CdfFavorite vo );
	public void update( CdfFavorite vo );
	public void removeObjectTree( CdfFavorite obj );
	public void remove( CdfFavorite vo );
	public boolean exists( CdfFavorite vo );
}
