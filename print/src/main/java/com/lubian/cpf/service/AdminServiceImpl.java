package com.lubian.cpf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.Encrypt;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.dao.SysFunctionCategoryDAO;
import com.lubian.cpf.dao.SysFunctionDAO;
import com.lubian.cpf.dao.SysRelRoleFuncDAO;
import com.lubian.cpf.dao.SysUserDAO;
import com.lubian.cpf.vo.SysFunction;
import com.lubian.cpf.vo.SysFunctionCategory;
import com.lubian.cpf.vo.SysRelRoleFunc;
import com.lubian.cpf.vo.SysUser;

@Service
public class AdminServiceImpl implements AdminService {
	private static final Logger log = Logger.getLogger(AdminServiceImpl.class);
	@Autowired
	private SysFunctionCategoryDAO categoryDAO;
	@Autowired
	private SysFunctionDAO functionDAO;
	@Autowired
	private SysRelRoleFuncDAO relRoleFuncDAO;
	@Autowired
	private SysUserDAO userDAO;


	/**
	 * 根据用户取得其所有菜单目录
	 * 
	 * @param admin
	 * @return
	 */
	public Map getCategory(SysUser admin) {
		Map<Integer, SysFunctionCategory> map = new HashMap();
		// 1. 取第2级目录(第一级目录未使用)
		List<SysFunctionCategory> secondCatLists = categoryDAO.getSecondMenuByRoleId(admin.getRoleId());
		for (SysFunctionCategory cat : secondCatLists) {
			map.put(cat.getCategoryId(), cat);
		}
		return map;
	}

	/**
	 * 根据用户取得其所有菜单
	 * 
	 * @param admin
	 * @param map
	 * @return
	 */
	public Map getMenus(SysUser admin) {
		Map<SysFunctionCategory, List> map = new LinkedHashMap();
		// 1. 取第2级目录(第一级目录未使用)
		List<SysFunctionCategory> secondCatLists = categoryDAO.getSecondMenuByRoleId(admin.getRoleId());
		for (SysFunctionCategory cat : secondCatLists) {
			// 2. 取菜单
			List<SysFunction> menus = functionDAO.getFunctionByRoleAndCategory(admin.getRoleId(), cat.getCategoryId());
			map.put(cat, menus);
		}
		return map;
	}

	/**
	 * 管理员登陆验证
	 * 
	 * @param user
	 * @return
	 */

	public SysUser adminLogin(String userName, String password) {
		SysUser user = null;
		user = checkUser(userName, password);
		if (user != null) {
			return user;
		}
		user = checkIC(userName, password);
		if (user != null) {
			return user;
		}
		user = checkEmail(userName, password);
		if (user != null) {
			return user;
		}
		user = checkMobile(userName, password);
		if (user != null) {
			return user;
		}
		return null;
	}


	public int mobileLogin(String userName, String password) {
		SysUser user = new SysUser();
		user.setUserNameEq(userName);
		user.setPasswordEq(password);
		user.setEnabled(1);
		try {
			List list = userDAO.freeFind(user);
			if (list != null && list.size() > 0)
				return 0;// 成功登陆

			user.setPasswordEq(null);
			list = userDAO.freeFind(user);
			if (list != null && list.size() > 0)
				return 1;// 密码错误
			else
				return 2;// 用户名不存在
		} catch (Exception e) {
			log.error(e);
		}
		return 2;
	}

	public int mobileUpdatePass(String userName, String passNew) {
		SysUser user = new SysUser();
		user.setUserName(userName);
		try {
			List list = userDAO.freeFind(user);
			if (list == null || list.size() == 0)
				return 1;// 登陆失败

			user = (SysUser) list.get(0);
			user.setPassword(passNew);
			user.setModifyDt(new Date());
			user.setModifyBy(userName);
			userDAO.update(user);

		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}

	public void updatePassword(SysUser user) {
		this.userDAO.update(user);

	}

	public List getPriviledeList(int roleId) {
		SysRelRoleFunc func = new SysRelRoleFunc();
		func.setRoleId(roleId);
		List<SysRelRoleFunc> list = relRoleFuncDAO.freeFind(func);
		return list;
	}

	private SysUser checkUser(SysUser user) {
		user.setEnabled(1);
		try {
			List list = userDAO.freeFind(user);
			if (list != null && list.size() > 0)
				return (SysUser) list.get(0);
		} catch (Exception e) {
			log.error(e);
		}
		return null;

	}

	private SysUser checkUser(String userName, String password) {
		SysUser user = new SysUser();
		user.setUserNameEq(userName);
		user.setPasswordEq(password);
		user = checkUser(user);
		if (user != null) {
			return user;
		} else {
			return null;
		}

	}

	private SysUser checkIC(String userName, String password) {
		SysUser user = new SysUser();
		user.setIcIdEq(userName);
		user.setPasswordEq(password);
		user = checkUser(user);
		if (user != null) {
			if (!StringUtils.isBlank(user.getAccountType()) && user.getAccountType().indexOf(Enums.LoginType.IC) > -1) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private SysUser checkEmail(String userName, String password) {
		SysUser user = new SysUser();
		user.setEmailEq(userName);
		user.setPasswordEq(password);
		user = checkUser(user);
		if (user != null) {
			if (!StringUtils.isBlank(user.getAccountType()) && user.getAccountType().indexOf(Enums.LoginType.EMAIL) > -1) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private SysUser checkMobile(String userName, String password) {
		SysUser user = new SysUser();
		user.setMobileEq(userName);
		user.setPasswordEq(password);
		user = checkUser(user);
		if (user != null) {
			if (!StringUtils.isBlank(user.getAccountType()) && user.getAccountType().indexOf(Enums.LoginType.MOBILE) > -1) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}


}
