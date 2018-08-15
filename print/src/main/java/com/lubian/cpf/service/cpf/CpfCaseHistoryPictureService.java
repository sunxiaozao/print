package com.lubian.cpf.service.cpf;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfCaseHistoryPicture;
import com.lubian.cpf.vo.SysUser;

/**
 * CpfCaseHistoryPictureService
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryPictureService {
	public CpfCaseHistoryPicture findByPK( CpfCaseHistoryPicture vo );
	public PageModel freeFind( CpfCaseHistoryPicture vo );	
	public void insert( CpfCaseHistoryPicture vo );
	public void update( CpfCaseHistoryPicture vo );
	public void delete( CpfCaseHistoryPicture vo );
	
	/**
	 * 胶片统计
	 * @param user 用户
	 * @param type 统计类型
	 * @return
	 */
	public Integer subtotal(SysUser user,Integer type);
}
