package com.lubian.cpf.service.cpf;

import java.util.Date;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfCaseHistoryReport;
import com.lubian.cpf.vo.SysUser;

/**
 * CpfCaseHistoryReportService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryReportService {
	public CpfCaseHistoryReport findByPK( CpfCaseHistoryReport vo );
	public PageModel freeFind( CpfCaseHistoryReport vo );	
	public void insert( CpfCaseHistoryReport vo );
	public void update( CpfCaseHistoryReport vo );
	public void delete( CpfCaseHistoryReport vo );
	
	/**
	 * 报告统计
	 * @param user 用户
	 * @param type 统计类型
	 * @return
	 */
	public Integer subtotal(SysUser user,Integer type);
}
