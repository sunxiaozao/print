package com.lubian.cpf.service.cfg;

import java.util.List;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CfgPara;

public interface ParaService {

	public PageModel freeFind(CfgPara cfgPara);

	public void remove(CfgPara vo);

	public void insert(CfgPara vo);

	public boolean exists(CfgPara vo);

	public CfgPara findByPK(CfgPara obj);

	public void update(CfgPara vo);

	public List<CfgPara> getParaTypeList();

	public String getCfgParaValueByCode(String code);

	public List<CfgPara> getAllCfgPara();
}
