package com.lubian.cpf.service.sys;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.dao.SysUserMessageItemDAO;
import com.lubian.cpf.vo.SysUserGroup;
import com.lubian.cpf.vo.SysUserMessageItem;

@Service
public class UserMessageItemServiceImpl implements UserMessageItemService {
	private static final Logger log = Logger.getLogger(UserMessageItemServiceImpl.class);
	@Autowired
	private SysUserMessageItemDAO userMessageItemDAO;
		
	@Override
	public void insert(SysUserMessageItem vo) {
		userMessageItemDAO.insert(vo);
	}
	
	@Override
	public List getItemList(SysUserMessageItem vo) {
		vo.setIsRead("N");
	    List<SysUserGroup> datas = userMessageItemDAO.freeFind(vo, "create_dt desc");
	    return datas;
	}
	
	@Override
	public void remove(SysUserMessageItem vo) {
		userMessageItemDAO.remove(vo);
	}	
	
	@Override
	public void remove(int itemId) {
		SysUserMessageItem vo = new SysUserMessageItem();
		vo.setMessageItemId(itemId);
		userMessageItemDAO.remove(vo);
	}
	
	@Override
	public void setItemRead(SysUserMessageItem vo) {
		vo.setIsRead("Y");
		vo.setModifyDt(new Date());
		vo.setModifyBy(vo.getToUserName());
		userMessageItemDAO.update(vo);
	}	

}
