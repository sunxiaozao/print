package com.lubian.cpf.dao;

import java.util.List;
import java.util.Collection;
import com.lubian.cpf.vo.CfgPara;

/**
 * CfgParaDAO
 * @author:Lusir<lusire@gmail.com>
 */
public interface CfgParaDAO {


	public List getParaType();
	public int countGetParaType();
	public List getParaType(  int pageIndex, int pageSize);

	public int countFindAll();
	
	public CfgPara findByPK( CfgPara obj );
	public List freeFind( CfgPara obj );
	public int countFreeFind( CfgPara obj );
	public List freeFind( CfgPara obj, String orderByColName );
	public List freeFind( CfgPara obj, int pageIndex, int pageSize );
	public List freeFind( CfgPara obj, int pageIndex, int pageSize , String orderByColName);
	
	public void saveAll(Collection<CfgPara>  objColl);
	public void save( CfgPara vo );
	public void insert( CfgPara vo );
	public void update( CfgPara vo );
	public void removeObjectTree( CfgPara obj );
	public void remove( CfgPara vo );
	public boolean exists( CfgPara vo );
}
