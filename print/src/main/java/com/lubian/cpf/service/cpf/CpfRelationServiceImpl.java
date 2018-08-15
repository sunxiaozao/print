package com.lubian.cpf.service.cpf;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfRelationDAO;
import com.lubian.cpf.vo.CpfRelation;

@Service
public class CpfRelationServiceImpl implements CpfRelationService {
	@Autowired
	private CpfRelationDAO cpfRelationDAO;

	public CpfRelation findByPK(CpfRelation vo) {
		return cpfRelationDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfRelation vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfRelationDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfRelation> list = cpfRelationDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfRelation vo) {
		cpfRelationDAO.insert(vo);
	}

	public void update(CpfRelation vo) {
		cpfRelationDAO.update(vo);
	}
	
	public void delete(CpfRelation vo) {
		cpfRelationDAO.remove(vo);
	}

	/**
	 * 根据条件查询是否存在此条关注
	 * @param vo
	 * @return
	 */
	@Override
	public boolean findByCommand(CpfRelation vo) {
	
		if (cpfRelationDAO.countFreeFind(vo)>0) {
			return true;//已存在
		}
		return false;
	}
	/**
	 * 通过其他用户关注申请
	 * @param selectArray
	 * @return
	 */
	@Override
	public void updRelationByIds(String[] selectArray,String password) {
		if (selectArray != null) {
			for (int i = 0; i < selectArray.length; i++) {
				CpfRelation vo=new CpfRelation();
				vo.setId(Integer.parseInt(selectArray[i]));
				vo.setStatus(Enums.relationStatus.PASS);
				vo.setCheckTime(new Date());
				vo.setPassword(password);
				cpfRelationDAO.update(vo);
			}
		}
	}
}
