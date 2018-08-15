package com.lubian.cpf.service.cpf;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfDownload;

/**
 * CpfDownloadService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfDownloadService {
	public CpfDownload findByPK( CpfDownload vo );
	public PageModel freeFind( CpfDownload vo );	
	public void insert( CpfDownload vo );
	public void update( CpfDownload vo );
	public void delete( CpfDownload vo );
}
