package com.lubian.cpf.service.sys;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.dao.SysFunctionCrudDAO;
import com.lubian.cpf.vo.SysFunctionCrud;

@Service
public class FunctionCrudServiceImpl implements FunctionCrudService {
	
	@Autowired
	private SysFunctionCrudDAO functionCrudDAO;

	public SysFunctionCrud findByPK(SysFunctionCrud obj) {
		
		return functionCrudDAO.findByPK(obj);
	}


	

}
