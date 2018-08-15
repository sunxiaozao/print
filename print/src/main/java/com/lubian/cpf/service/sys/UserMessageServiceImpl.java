package com.lubian.cpf.service.sys;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysUserMessageDAO;
import com.lubian.cpf.vo.SysUserMessage;

@Service
public class UserMessageServiceImpl implements UserMessageService {
	private static final Logger log = Logger.getLogger(UserMessageServiceImpl.class);
	@Autowired
	private SysUserMessageDAO userMessageDAO;
	
	@Override
	public PageModel freeFind(SysUserMessage userMessage) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = userMessageDAO.countFreeFind(userMessage);
		pm.setTotal(total);
		List<SysUserMessage> datas = userMessageDAO.freeFind(userMessage, pageIndex, pageSize, "CREATE_DT DESC");
		pm.setDatas(datas);
		return pm;
		
	}
	
	@Override
	public void insert(SysUserMessage vo) {
		userMessageDAO.insert(vo);
	}
	@Override
	public void remove(SysUserMessage vo) {

		userMessageDAO.remove(vo);		
	}
	@Override
	public void save(SysUserMessage vo) {
		userMessageDAO.save(vo);
	}
	
	@Override
	public void update(SysUserMessage vo) {
		userMessageDAO.update(vo);
	}

	@Override
	public SysUserMessage findByPK(SysUserMessage obj) {
		return userMessageDAO.findByPK(obj);
	}
	
	@Override
	public SysUserMessage findByPK(int messageId) {
		SysUserMessage obj = new SysUserMessage();
		obj.setMessageId(messageId);
		return userMessageDAO.findByPK(obj);
	}

}
