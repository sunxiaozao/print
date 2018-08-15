package com.lubian.cpf.service.cpf;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CdfFavoriteDAO;
import com.lubian.cpf.vo.CdfFavorite;
import com.lubian.cpf.vo.SysDoctor;

@Service
public class CdfFavoriteServiceImpl implements CdfFavoriteService {
	@Autowired
	private CdfFavoriteDAO cdfFavoriteDAO;

	public CdfFavorite findByPK(CdfFavorite vo) {
		return cdfFavoriteDAO.findByPK(vo);
	}

	public PageModel freeFind(CdfFavorite vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cdfFavoriteDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CdfFavorite> list = cdfFavoriteDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CdfFavorite vo) {
		cdfFavoriteDAO.insert(vo);
	}

	public void update(CdfFavorite vo) {
		cdfFavoriteDAO.update(vo);
	}

	public void delete(CdfFavorite vo) {
		cdfFavoriteDAO.remove(vo);
	}

	/**
	 * 查询收藏记录
	 * 
	 * @param vo
	 * @return list
	 */
	@Override
	public List<CdfFavorite> listFreeFind(CdfFavorite vo) {
		List<CdfFavorite> list = cdfFavoriteDAO.freeFind(vo);
		return list;
	}

	/**
	 * 该医生收藏记录的id 集合
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public String idsByDocotrId(Integer doctorId) {
		CdfFavorite vo = new CdfFavorite();
		vo.setDoctorId(doctorId);
		List<CdfFavorite> list = cdfFavoriteDAO.freeFind(vo);

		// 得到id 集合
		List<Integer> idList = new ArrayList<Integer>();
		for (CdfFavorite cdfFavorite : list) {
			idList.add(cdfFavorite.getId());
		}
		return StringUtils.convertInInt(idList);
	}

	/**
	 * 该医生收藏记录的caseId 集合
	 * 
	 * @param doctorId
	 * @return
	 */
	@Override
	public String caseIdByDocotrId(Integer doctorId) {
		CdfFavorite vo = new CdfFavorite();
		vo.setDoctorId(doctorId);
		List<CdfFavorite> list = cdfFavoriteDAO.freeFind(vo);

		// 得到id 集合
		List<Integer> idList = new ArrayList<Integer>();
		for (CdfFavorite cdfFavorite : list) {
			idList.add(cdfFavorite.getCaseId());
		}
		return StringUtils.convertInInt(idList);
	}

	/**
	 * 收藏删除
	 * 
	 * @param vo
	 */
	@Override
	public void deleteCollect(CdfFavorite vo) {
		List<CdfFavorite> list = cdfFavoriteDAO.freeFind(vo);
		if (null != list && list.size() > 0) {
			vo = list.get(0);
		}
		cdfFavoriteDAO.remove(vo);
	}

	@Override
	public String caseIdByDocotrId(Integer doctorId, Integer bookmarkId) {
		CdfFavorite vo = new CdfFavorite();
		vo.setDoctorId(doctorId);
		vo.setBookmarkId(bookmarkId);
		List<CdfFavorite> list = cdfFavoriteDAO.freeFind(vo);

		// 得到id 集合
		List<Integer> idList = new ArrayList<Integer>();
		for (CdfFavorite cdfFavorite : list) {
			idList.add(cdfFavorite.getCaseId());
		}
		return StringUtils.convertInInt(idList);
	}

	@Override
	public boolean count(Integer bookmarkId) {
		CdfFavorite vo = new CdfFavorite();
		vo.setBookmarkId(bookmarkId);
		int count = cdfFavoriteDAO.countFreeFind(vo);
		if(count>0){
			return false;
		}
		return true;
	
	}
}
