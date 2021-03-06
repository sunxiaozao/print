package com.lubian.cpf.service.job;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.Config;


@Service
public class DailyJobServiceImpl implements DailyJobService {
	private static final Logger log = Logger.getLogger(DailyJobServiceImpl.class);
	
	public void performAutoJob() {
		if(!Config.RUN_JOB)return;
		
		log.info("Daily auto job started.");
		log.info("Daily auto job end.");
	}
	
}
