package com.lubian.cpf.service.cpf;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfReplyDAO;
import com.lubian.cpf.vo.CpfReply;

@Service
public class CpfReplyServiceImpl implements CpfReplyService {
	@Autowired
	private CpfReplyDAO cpfReplyDAO;

	public CpfReply findByPK(CpfReply vo) {
		return cpfReplyDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfReply vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfReplyDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfReply> list = cpfReplyDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfReply vo) {
		cpfReplyDAO.insert(vo);
	}

	public void update(CpfReply vo) {
		cpfReplyDAO.update(vo);
	}

	public void delete(CpfReply vo) {
		cpfReplyDAO.remove(vo);
	}

	@Override
	public List<CpfReply> find(String notebookIds) {
		CpfReply reply = new CpfReply();
		reply.setNotebookId_instr(notebookIds);
		reply.setStatus(Enums.isYesOrIsNo.IS_YES);
		return cpfReplyDAO.freeFind(reply, "reply_time");
	}
}
