package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.SysDoctor;

/**
 * SysDoctorDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysDoctorDAO {


	public List idNotInSearch(Object idStr);
	public int countIdNotInSearch(Object idStr);
	public List idNotInSearch(Object idStr , int pageIndex, int pageSize);

	public int countFindAll();
	
	public SysDoctor findByPK( SysDoctor obj );
	public List freeFind( SysDoctor obj );
	public int countFreeFind( SysDoctor obj );
	public List freeFind( SysDoctor obj, String orderByColName );
	public List freeFind( SysDoctor obj, int pageIndex, int pageSize );
	public List freeFind( SysDoctor obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<SysDoctor>  objColl);
	public void save( SysDoctor vo );
	public void insert( SysDoctor vo );
	public void update( SysDoctor vo );
	public void removeObjectTree( SysDoctor obj );
	public void remove( SysDoctor vo );
	public boolean exists( SysDoctor vo );
}
