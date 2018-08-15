package com.lubian.cpf.service.cpf;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfCaseHistoryApply;
import com.lubian.cpf.vo.SysUser;

/**
 * CpfCaseHistoryApplyService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryApplyService {
	public CpfCaseHistoryApply findByPK( CpfCaseHistoryApply vo );
	public PageModel freeFind( CpfCaseHistoryApply vo );	
	public void insert( CpfCaseHistoryApply vo );
	public void update( CpfCaseHistoryApply vo );
	public void delete( CpfCaseHistoryApply vo );
	
	/**
	 * 申请单统计
	 * @param user 用户
	 * @param type 统计类型
	 * @return
	 */
	public Integer subtotal(SysUser user,Integer type);
}
