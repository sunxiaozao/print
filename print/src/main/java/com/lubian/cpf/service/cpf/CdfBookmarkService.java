package com.lubian.cpf.service.cpf;

import java.util.List;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CdfBookmark;

/**
 * CdfBookmarkService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CdfBookmarkService {
	public CdfBookmark findByPK( CdfBookmark vo );
	public PageModel freeFind( CdfBookmark vo );	
	public void insert( CdfBookmark vo );
	public void update( CdfBookmark vo );
	public void delete( CdfBookmark vo );
	
	public List<CdfBookmark> freeFindList(CdfBookmark vo );
	

}
