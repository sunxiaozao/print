package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CpfReply;

/**
 * CpfReplyDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfReplyDAO {


	public int countFindAll();
	
	public CpfReply findByPK( CpfReply obj );
	public List freeFind( CpfReply obj );
	public int countFreeFind( CpfReply obj );
	public List freeFind( CpfReply obj, String orderByColName );
	public List freeFind( CpfReply obj, int pageIndex, int pageSize );
	public List freeFind( CpfReply obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CpfReply>  objColl);
	public void save( CpfReply vo );
	public void insert( CpfReply vo );
	public void update( CpfReply vo );
	public void removeObjectTree( CpfReply obj );
	public void remove( CpfReply vo );
	public boolean exists( CpfReply vo );
}
