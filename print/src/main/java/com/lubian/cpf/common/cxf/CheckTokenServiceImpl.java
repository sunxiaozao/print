package com.lubian.cpf.common.cxf;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.lubian.cpf.dao.SysUserDAO;
import com.lubian.cpf.service.sys.UserService;
import com.lubian.cpf.vo.SysUser;

@WebService(serviceName = "checkTokenService",   
endpointInterface = "com.lubian.cpf.common.cxf.CheckTokenService")  
public class CheckTokenServiceImpl implements CheckTokenService{
	
	@Autowired
	private UserService userService;

	@Override
	public boolean checkToken(String token) {
		
		SysUser user=new SysUser();
		user.setAccessTokenEq(token);
		List<SysUser> list=userService.freeFind(user);
		if(list!=null&&list.size()>0){
			return true;
		}
		
		return false;
	}

	
}
