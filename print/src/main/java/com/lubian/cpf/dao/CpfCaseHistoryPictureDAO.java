package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfCaseHistoryPicture;

/**
 * CpfCaseHistoryPictureDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryPictureDAO {


	public int countFindAll();
	
	public CpfCaseHistoryPicture findByPK( CpfCaseHistoryPicture obj );
	public List freeFind( CpfCaseHistoryPicture obj );
	public int countFreeFind( CpfCaseHistoryPicture obj );
	public List freeFind( CpfCaseHistoryPicture obj, String orderByColName );
	public List freeFind( CpfCaseHistoryPicture obj, int pageIndex, int pageSize );
	public List freeFind( CpfCaseHistoryPicture obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfCaseHistoryPicture>  objColl);
	public void save( CpfCaseHistoryPicture vo );
	public void insert( CpfCaseHistoryPicture vo );
	public void update( CpfCaseHistoryPicture vo );
	public void removeObjectTree( CpfCaseHistoryPicture obj );
	public void remove( CpfCaseHistoryPicture vo );
	public boolean exists( CpfCaseHistoryPicture vo );
}
