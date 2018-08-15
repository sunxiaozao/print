package com.lubian.cpf.service;

import java.util.List;
import java.util.Map;

import com.lubian.cpf.vo.SysUser;

public interface AdminService {
	public Map getMenus(SysUser admin);

	public SysUser adminLogin(String userName, String password);

	

	public void updatePassword(SysUser user);

	public int mobileLogin(String userName, String password);

	public int mobileUpdatePass(String userName, String passNew);

	public List getPriviledeList(int roleId);
}
