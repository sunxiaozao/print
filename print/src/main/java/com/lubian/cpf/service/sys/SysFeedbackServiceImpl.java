package com.lubian.cpf.service.sys;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysFeedbackDAO;
import com.lubian.cpf.vo.SysFeedback;

@Service
public class SysFeedbackServiceImpl implements SysFeedbackService {
	@Autowired
	private SysFeedbackDAO sysFeedbackDAO;

	public SysFeedback findByPK(SysFeedback vo) {
		return sysFeedbackDAO.findByPK(vo);
	}

	public PageModel freeFind(SysFeedback vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = sysFeedbackDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<SysFeedback> list = sysFeedbackDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(SysFeedback vo) {
		sysFeedbackDAO.insert(vo);
	}

	public void update(SysFeedback vo) {
		sysFeedbackDAO.update(vo);
	}
	
	public void delete(SysFeedback vo) {
		sysFeedbackDAO.remove(vo);
	}
}
