package com.lubian.cpf.service.cpf;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfRelation;

/**
 * CpfRelationService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfRelationService {
	public CpfRelation findByPK( CpfRelation vo );
	public PageModel freeFind( CpfRelation vo );	
	public void insert( CpfRelation vo );
	public void update( CpfRelation vo );
	public void delete( CpfRelation vo );
	
	/**
	 * 根据条件查询是否存在此条关注
	 * @param vo
	 * @return
	 */
	public boolean findByCommand(CpfRelation vo);
	/**
	 * 通过其他用户关注申请
	 * @param selectArray
	 * @param password
	 * @return
	 */
	public void updRelationByIds(String[] selectArray,String password);
}
