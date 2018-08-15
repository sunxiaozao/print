package com.lubian.cpf.common.basedata;

import java.util.HashMap;
import java.util.List;

import com.lubian.cpf.common.util.spring.SpringContextUtil;
import com.lubian.cpf.service.cfg.ParaService;
import com.lubian.cpf.vo.CfgPara;


/**
 * 系统启动时装载系统参数
 * @author Administrator
 *
 */
public class ParaUtil {
	
	private static ParaService service = null;
	public static HashMap<String,String> SYS_PARA_MAP = null;

	private synchronized static void init() {
		service = SpringContextUtil.getBean(ParaService.class);
		List<CfgPara> list = service.getAllCfgPara();
		
		SYS_PARA_MAP = new HashMap<String,String>();
		for(CfgPara para: list){
			SYS_PARA_MAP.put(para.getParaCode(), para.getParaValue());
		}
	}

	static {
		init();
	}

	/**
	 * 根据para code 取值
	 * @param code
	 * @return
	 */
	public static String getParaValue(String code) {
		return service.getCfgParaValueByCode(code);
	}
	
	public static String getCachedParaValue(String code) {
		return SYS_PARA_MAP.get(code);
	}
}
