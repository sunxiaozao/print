package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfDownload;

/**
 * CpfDownloadDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfDownloadDAO {


	public int countFindAll();
	
	public CpfDownload findByPK( CpfDownload obj );
	public List freeFind( CpfDownload obj );
	public int countFreeFind( CpfDownload obj );
	public List freeFind( CpfDownload obj, String orderByColName );
	public List freeFind( CpfDownload obj, int pageIndex, int pageSize );
	public List freeFind( CpfDownload obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfDownload>  objColl);
	public void save( CpfDownload vo );
	public void insert( CpfDownload vo );
	public void update( CpfDownload vo );
	public void removeObjectTree( CpfDownload obj );
	public void remove( CpfDownload vo );
	public boolean exists( CpfDownload vo );
}
