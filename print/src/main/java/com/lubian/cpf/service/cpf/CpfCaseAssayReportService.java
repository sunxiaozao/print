package com.lubian.cpf.service.cpf;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfCaseAssayReport;
import com.lubian.cpf.vo.SysUser;

/**
 * CpfCaseAssayReportService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseAssayReportService {
	public CpfCaseAssayReport findByPK( CpfCaseAssayReport vo );
	public PageModel freeFind( CpfCaseAssayReport vo );	
	public void insert( CpfCaseAssayReport vo );
	public void update( CpfCaseAssayReport vo );
	public void delete( CpfCaseAssayReport vo );
	
	/**
	 * 化验单统计
	 * @param user 用户
	 * @param type 统计类型
	 * @return
	 */
	public Integer subtotal(SysUser user,Integer type);
}
