package com.lubian.cpf.service.cpf;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CdfBookmarkDAO;
import com.lubian.cpf.vo.CdfBookmark;

@Service
public class CdfBookmarkServiceImpl implements CdfBookmarkService {
	@Autowired
	private CdfBookmarkDAO cdfBookmarkDAO;

	public CdfBookmark findByPK(CdfBookmark vo) {
		return cdfBookmarkDAO.findByPK(vo);
	}

	public PageModel freeFind(CdfBookmark vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cdfBookmarkDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CdfBookmark> list = cdfBookmarkDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CdfBookmark vo) {
		cdfBookmarkDAO.insert(vo);
	}

	public void update(CdfBookmark vo) {
		cdfBookmarkDAO.update(vo);
	}
	
	public void delete(CdfBookmark vo) {
		cdfBookmarkDAO.remove(vo);
	}

	@Override
	public List<CdfBookmark> freeFindList(CdfBookmark vo) {

		return cdfBookmarkDAO.freeFind(vo);
	}
}
