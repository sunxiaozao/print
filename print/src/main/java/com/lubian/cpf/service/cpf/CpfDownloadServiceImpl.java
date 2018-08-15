package com.lubian.cpf.service.cpf;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfDownloadDAO;
import com.lubian.cpf.vo.CpfDownload;

@Service
public class CpfDownloadServiceImpl implements CpfDownloadService {
	@Autowired
	private CpfDownloadDAO cpfDownloadDAO;

	public CpfDownload findByPK(CpfDownload vo) {
		return cpfDownloadDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfDownload vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfDownloadDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfDownload> list = cpfDownloadDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfDownload vo) {
		cpfDownloadDAO.insert(vo);
	}

	public void update(CpfDownload vo) {
		cpfDownloadDAO.update(vo);
	}
	
	public void delete(CpfDownload vo) {
		cpfDownloadDAO.remove(vo);
	}
}
