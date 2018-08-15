package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysFeedback;

/**
 * SysFeedbackDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysFeedbackDAO {


	public int countFindAll();
	
	public SysFeedback findByPK( SysFeedback obj );
	public List freeFind( SysFeedback obj );
	public int countFreeFind( SysFeedback obj );
	public List freeFind( SysFeedback obj, String orderByColName );
	public List freeFind( SysFeedback obj, int pageIndex, int pageSize );
	public List freeFind( SysFeedback obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysFeedback>  objColl);
	public void save( SysFeedback vo );
	public void insert( SysFeedback vo );
	public void update( SysFeedback vo );
	public void removeObjectTree( SysFeedback obj );
	public void remove( SysFeedback vo );
	public boolean exists( SysFeedback vo );
}
