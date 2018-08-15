package com.lubian.cpf.common.basedata;

import org.apache.log4j.Logger;

import com.lubian.cpf.common.util.spring.SpringContextUtil;
import com.lubian.cpf.service.sys.SysNotificationService;

public class NotificationUtil {
	private static Logger log =Logger.getLogger(NotificationUtil.class);
	
	public static SysNotificationService notificationService = null;

	private synchronized static void init() {
		notificationService = (SysNotificationService) SpringContextUtil.getBean(SysNotificationService.class);
	}

	static {
		init();
	}

	public static void add(String fName, String desc) {
		try{
			notificationService.insertNotification(fName, desc);
		}catch(Exception e){
			log.error(e);
		}

	}
}
