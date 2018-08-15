package com.lubian.cpf.service.cpf;

import java.util.List;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfReply;

/**
 * CpfReplyService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfReplyService {
	public CpfReply findByPK( CpfReply vo );
	public PageModel freeFind( CpfReply vo );	
	public void insert( CpfReply vo );
	public void update( CpfReply vo );
	public void delete( CpfReply vo );
	
	public List<CpfReply> find(String notebookIds);
}
