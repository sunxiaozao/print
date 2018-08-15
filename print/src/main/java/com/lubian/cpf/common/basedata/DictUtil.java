package com.lubian.cpf.common.basedata;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lubian.cpf.common.util.spring.SpringContextUtil;
import com.lubian.cpf.service.cfg.DictService;
import com.lubian.cpf.vo.CfgDict;

/**
 * 系统启动时装载系统参数
 * @author Administrator
 *
 */
public class DictUtil {
	
	private static DictService service = null;

	private synchronized static void init() {
		service = SpringContextUtil.getBean(DictService.class);
	}

	static {
		init();
	}

	public static Map<String,CfgDict> getDictMap(String type) {
		List<CfgDict> list = getDictList(type);
		Map<String,CfgDict> map = new LinkedHashMap<String,CfgDict>();
		for(CfgDict dict : list){
			map.put(dict.getShortCode(), dict);
		}
		return map;
	}
	
	public static List<CfgDict> getDictList(String type) {
		CfgDict dict = new CfgDict();
		dict.setType(type);
		List<CfgDict> list = service.getAllCfgDict(dict);
		return list;
	}
}
