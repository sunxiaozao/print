package com.lubian.cpf.service.cpf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfPatientRelationUserDAO;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfPatientRelationUser;

@Service
public class CpfPatientRelationUserServiceImpl implements
		CpfPatientRelationUserService {
	@Autowired
	private CpfPatientRelationUserDAO cpfPatientRelationUserDAO;

	public CpfPatientRelationUser findByPK(CpfPatientRelationUser vo) {
		return cpfPatientRelationUserDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfPatientRelationUser vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfPatientRelationUserDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfPatientRelationUser> list = cpfPatientRelationUserDAO.freeFind(
				vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfPatientRelationUser vo) {
		cpfPatientRelationUserDAO.insert(vo);
	}

	public void update(CpfPatientRelationUser vo) {
		cpfPatientRelationUserDAO.update(vo);
	}

	public void delete(CpfPatientRelationUser vo) {
		cpfPatientRelationUserDAO.remove(vo);
	}

	@Override
	public List<CpfPatientRelationUser> freeFindList(CpfPatientRelationUser vo) {

		return cpfPatientRelationUserDAO.freeFind(vo);
	}

	@Override
	public boolean count(CpfPatientRelationUser vo) {
		if (cpfPatientRelationUserDAO.countFreeFind(vo) > 0) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public CpfPatient freeFind(Integer userId) {
		CpfPatientRelationUser vo = new CpfPatientRelationUser();
		vo.setUserId(userId);
		List<CpfPatientRelationUser> list = cpfPatientRelationUserDAO
				.freeFind(vo);
		if (list != null && list.size() > 0) {
			return list.get(0).getCpfPatient();
		}
		return null;
	}

	@Override
	public String freeFind(Integer userId, Integer patientId) {
		CpfPatientRelationUser vo = new CpfPatientRelationUser();
		if (userId != null) {
			vo.setUserId(userId);
		} else {
			vo.setPatientId(patientId);
		}
		List<CpfPatientRelationUser> list = cpfPatientRelationUserDAO
				.freeFind(vo);
		if (list != null && list.size() > 0) {
			List<Integer> idList = new ArrayList<Integer>();
			for (CpfPatientRelationUser cpfPatientRelationUser : list) {
				idList.add(cpfPatientRelationUser.getRelationPatientId());
			}
			return StringUtils.convertInInt(idList);
		}
		return null;
	}
}
