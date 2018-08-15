package com.lubian.cpf.service.sys;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.dao.SysFunctionDAO;
import com.lubian.cpf.vo.SysFunction;

@Service
public class FunctionServiceImpl implements FunctionService {
	
	@Autowired
	private SysFunctionDAO functionDAO;

	public List<SysFunction> getFuctionList(SysFunction obj) {		
		return functionDAO.freeFind(obj, "PRIORITY DESC");
	}
	

}
