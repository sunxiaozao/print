package com.lubian.cpf.service.cfg;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CfgDictDAO;
import com.lubian.cpf.vo.CfgDict;

@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private CfgDictDAO cfgDictDao;

	public void addCfgDict(CfgDict vo) {
		cfgDictDao.insert(vo);
	}

	public void deleteCfgDict(CfgDict vo) {
		cfgDictDao.remove(vo);

	}

	public CfgDict findCfgDict(CfgDict vo) {
		return cfgDictDao.findByPK(vo);
	}

	public PageModel listCfgDict(CfgDict vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cfgDictDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CfgDict> astHoustList = cfgDictDao.freeFind(vo, pageIndex, pageSize, "TYPE, PRIORITY DESC");
		pm.setDatas(astHoustList);
		return pm;
	}

	public void updateCfgDict(CfgDict vo) {
		cfgDictDao.update(vo);
	}

	public List getAllCfgDict(CfgDict vo) {
		List<CfgDict> list = cfgDictDao.freeFind(vo, "priority DESC");
		return list;
	}

	public List getCfgDict(CfgDict vo, int num) {
		List<CfgDict> list = cfgDictDao.freeFind(vo, 0, num, "priority DESC");
		return list;
	}
	
	public CfgDict freeFindDict(CfgDict vo) {
		List<CfgDict> list = cfgDictDao.freeFind(vo, 0, 1);
		if(list!=null && list.size()>0)
			return list.get(0);
		else return null;
	}
	
	/**
	 * 得到投诉类别的map集合
	 */
	public Map<Integer, String> getAllCfgDictMap(CfgDict vo){
		List<CfgDict> list = cfgDictDao.freeFind(vo, "priority DESC");
		Map<Integer, String> dictMap=new LinkedHashMap<Integer, String>();
		if(null!=list&&list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				CfgDict dict=list.get(i);
				dictMap.put(dict.getDictId(), dict.getLongName());
			}
		}
		return	dictMap;
	}
	

}
