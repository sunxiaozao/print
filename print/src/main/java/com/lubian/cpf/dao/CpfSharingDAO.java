package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfSharing;

/**
 * CpfSharingDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfSharingDAO {


	public int countFindAll();
	
	public CpfSharing findByPK( CpfSharing obj );
	public List freeFind( CpfSharing obj );
	public int countFreeFind( CpfSharing obj );
	public List freeFind( CpfSharing obj, String orderByColName );
	public List freeFind( CpfSharing obj, int pageIndex, int pageSize );
	public List freeFind( CpfSharing obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfSharing>  objColl);
	public void save( CpfSharing vo );
	public void insert( CpfSharing vo );
	public void update( CpfSharing vo );
	public void removeObjectTree( CpfSharing obj );
	public void remove( CpfSharing vo );
	public boolean exists( CpfSharing vo );
}
