package com.lubian.cpf.service.cpf;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfCaseHistoryOthersDAO;
import com.lubian.cpf.vo.CpfCaseHistoryOthers;

@Service
public class CpfCaseHistoryOthersServiceImpl implements CpfCaseHistoryOthersService {
	@Autowired
	private CpfCaseHistoryOthersDAO cpfCaseHistoryOthersDAO;

	public CpfCaseHistoryOthers findByPK(CpfCaseHistoryOthers vo) {
		return cpfCaseHistoryOthersDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfCaseHistoryOthers vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfCaseHistoryOthersDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfCaseHistoryOthers> list = cpfCaseHistoryOthersDAO.freeFind(vo, pageIndex, pageSize,"case_date desc");
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfCaseHistoryOthers vo) {
		cpfCaseHistoryOthersDAO.insert(vo);
	}

	public void update(CpfCaseHistoryOthers vo) {
		cpfCaseHistoryOthersDAO.update(vo);
	}
	
	public void delete(CpfCaseHistoryOthers vo) {
		cpfCaseHistoryOthersDAO.remove(vo);
	}
}
