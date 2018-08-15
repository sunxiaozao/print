package com.lubian.cpf.service.cfg;

import java.util.List;
import java.util.Map;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CfgDict;

public interface DictService {
	public CfgDict findCfgDict(CfgDict vo);

	public PageModel listCfgDict(CfgDict vo);

	public void addCfgDict(CfgDict vo);

	public void updateCfgDict(CfgDict vo);

	public void deleteCfgDict(CfgDict vo);
	
	public List getAllCfgDict(CfgDict vo);
	
	public List getCfgDict(CfgDict vo, int num);
	
	public CfgDict freeFindDict(CfgDict vo);
	
	public Map<Integer, String> getAllCfgDictMap(CfgDict vo);
}
