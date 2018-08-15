package com.lubian.cpf.service.cpf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.SysUser;

/**
 * CpfPatientService
 * 
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfPatientService {
	public CpfPatient findByPK(CpfPatient vo);

	public PageModel freeFind(CpfPatient vo);

	public void insert(CpfPatient vo);

	public void update(CpfPatient vo);

	public void delete(CpfPatient vo);

	public CpfPatient findPatientByUserId(CpfPatient vo);

	public void update(HttpServletRequest request, CpfPatient vo,
			MultipartFile file);

	public boolean findPatientByCardId(CpfPatient vo, SysUser user);

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
	public String checkUserReturnId(String data);

	/**
	 * 检测用户是否存在，并返还用户id 集合
	 * 
	 * @param data
	 *            用户名、邮箱等
	 * @param type
	 *            登陆类型
	 * @return 用户id
	 */
	public String checkUserReturnIds(String data, String type);

	/**
	 * 查询病人姓名map集合
	 */
	public Map<Integer, String> findPatientMap(CpfPatient vo);

	/**
	 * 查询属于该医生的病人集合
	 * 
	 * @return
	 */
	// public List<CpfPatient> subtotalOfPatient(Integer userId);
	public List<CpfPatient> subtotalOfPatient(SysUser user);

	/**
	 * 根据UserId 查询病人id 集合
	 * 
	 * @param uId
	 * @return
	 */
	public String findPatientIds(Integer uId);

	public CpfPatient findByUserId(Integer uId);
	public CpfPatient find(String  userName,String password);

	/**
	 * 查询病人列表
	 * 
	 * @param vo
	 * @return
	 */
	public List<CpfPatient> findList(CpfPatient vo);
	public Map<Integer,Integer> findMap(CpfPatient vo);
}
