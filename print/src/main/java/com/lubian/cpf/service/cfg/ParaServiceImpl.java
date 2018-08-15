package com.lubian.cpf.service.cfg;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CfgParaDAO;
import com.lubian.cpf.vo.CfgPara;


@Service
public class ParaServiceImpl implements ParaService {
	private static final Logger log = Logger.getLogger(ParaServiceImpl.class);
	@Autowired
	private CfgParaDAO cfgParaDAO;
	public PageModel freeFind(CfgPara cfgPara) {
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = cfgParaDAO.countFreeFind(cfgPara);
		pm.setTotal(total);
		List<CfgPara> datas = cfgParaDAO.freeFind(cfgPara, pageIndex, pageSize,"PRIORITY DESC");
		pm.setDatas(datas);
		return pm;
	}
	
	public void remove(CfgPara vo) {		
		cfgParaDAO.remove(vo);
	}
	
	public boolean exists(CfgPara vo) {		
		return cfgParaDAO.exists(vo);
	}
	
	public void insert(CfgPara vo) {		
		cfgParaDAO.insert(vo);		
	}
	
	public CfgPara findByPK(CfgPara obj) {		
		return cfgParaDAO.findByPK(obj);
	}
	
	public void update(CfgPara vo) {
		cfgParaDAO.update(vo);
		
	}
	
	public List<CfgPara> getParaTypeList() {		
		return cfgParaDAO.getParaType();
	}
	
	public String getCfgParaValueByCode(String code) {
		CfgPara cfgPara = new CfgPara();
		cfgPara.setParaCode(code);
		cfgPara = cfgParaDAO.findByPK(cfgPara);
		if(cfgPara!=null)
			return cfgPara.getParaValue();
		return "";
	}
	
	public List<CfgPara> getAllCfgPara(){
		return cfgParaDAO.freeFind(new CfgPara(), "PRIORITY DESC");
	}	
}
