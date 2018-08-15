package com.lubian.cpf.service.sys;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysNotification;

/**
 * SysNotificationService
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysNotificationService {
	public SysNotification findByPK( SysNotification vo );
	public PageModel freeFind( SysNotification vo );	
	public void insert( SysNotification vo );
	public void update( SysNotification vo );
	public void delete( SysNotification vo );
	public void insertNotification(String functionName, String desc);
	public int countUnProceedNoti();
}
