package com.lubian.cpf.service.job;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.Config;


@Service
public class AutoJobServiceImpl implements AutoJobService {
	private static final Logger log = Logger.getLogger(AutoJobServiceImpl.class);
	
	public void performAutoJob() {
		if(!Config.RUN_JOB)return;
	}
}
