package com.lubian.cpf.service.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysUser;

public interface UserService {

	public List freeFind(SysUser obj);

	public PageModel getAdminUserList(SysUser SysUser);

	public void deleteUser(SysUser vo);

	public SysUser findByPK(SysUser vo);

	public void updateUser(SysUser vo);

	public void UpdateMember(SysUser vo);

	public void UpdateDoctor(SysUser vo, HttpServletRequest request);

	public void insert(SysUser vo);

	public void updateLastLogin(Integer userId, String token);

	public SysUser login(SysUser vo);

	public SysUser getUserByEmail(String email);

	public Map<String, SysUser> getAllUserMap();

	public SysUser getUserByUserName(String name);

	public String getUserIdStrByOrgId(SysUser user);

	public String getUserIdStr(SysUser user);

	public boolean checkUserNameExistance(String name);

	public boolean checkUserMobileExistance(String mobile);



	/**
	 * 检测是否存在该用户，并返还用户id
	 * 
	 * @param data
	 *            用户名、邮箱等
	 * @param type
	 *            登陆类型
	 * @return 用户id
	 */
	public Integer checkUserReturnId(String data, String type);

	public void saveDocer(SysUser user, HttpServletRequest request);

	/**
	 * 用户导入
	 * 
	 * @param file
	 * @param user
	 * @param type
	 * @return
	 */
	public Map<String, List> userImport(MultipartFile file,
			SysUser user, String type);

}
