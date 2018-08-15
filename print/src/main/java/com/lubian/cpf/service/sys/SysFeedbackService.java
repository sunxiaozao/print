package com.lubian.cpf.service.sys;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysFeedback;

/**
 * SysFeedbackService
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysFeedbackService {
	public SysFeedback findByPK( SysFeedback vo );
	public PageModel freeFind( SysFeedback vo );	
	public void insert( SysFeedback vo );
	public void update( SysFeedback vo );
	public void delete( SysFeedback vo );
}
