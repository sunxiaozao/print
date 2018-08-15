package com.lubian.cpf.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.dao.SysRelRoleFuncDAO;
import com.lubian.cpf.vo.SysRelRoleFunc;

@Service
public class RelRoleFuncServiceImpl implements RelRoleFuncService {
	
	@Autowired
	private SysRelRoleFuncDAO relRoleFuncDAO;

	public void delete(SysRelRoleFunc vo) {
		
		relRoleFuncDAO.remove(vo);
		
	}
	
	public void deleteByRoleId(Integer roleId){
		relRoleFuncDAO.deleteByRoleId(roleId);
	}

	public void insert(SysRelRoleFunc vo) {
		
		relRoleFuncDAO.insert(vo);
	}

	public List freeFind(SysRelRoleFunc obj) {
		
		return relRoleFuncDAO.freeFind(obj);
	}

	public SysRelRoleFunc findByPK(SysRelRoleFunc obj) {
		
		return relRoleFuncDAO.findByPK(obj);
	}
}
