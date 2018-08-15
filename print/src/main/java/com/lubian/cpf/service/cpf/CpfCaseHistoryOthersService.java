package com.lubian.cpf.service.cpf;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfCaseHistoryOthers;

/**
 * CpfCaseHistoryOthersService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryOthersService {
	public CpfCaseHistoryOthers findByPK( CpfCaseHistoryOthers vo );
	public PageModel freeFind( CpfCaseHistoryOthers vo );	
	public void insert( CpfCaseHistoryOthers vo );
	public void update( CpfCaseHistoryOthers vo );
	public void delete( CpfCaseHistoryOthers vo );
}
