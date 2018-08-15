package com.lubian.cpf.common.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Config {
	/**
	 * if true, then system runs in debug mode.
	 */
	public final static boolean isDEBUG;
	/**
	 * if true, system can run backend jobs. each backend service should check this switch.
	 */
	public final static boolean RUN_JOB;
	private static PropertiesConfiguration configuration =null;
	static{
		try {
			configuration = new PropertiesConfiguration();  
			//configuration=new PropertiesConfiguration("all.properties");
			configuration.setEncoding("utf8");
			configuration.load("config/all.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		if ("true".equals(configuration.getString("is_debug"))) {
			isDEBUG=true;
		} else {
			isDEBUG=false;
		}
				
		if("true".equals(configuration.getString("run_job"))){
			RUN_JOB=true;
		}else{
			RUN_JOB=false;
		}
	}

	public static String getProperty(String key) {
		return configuration.getString(key);
	}
	
}
