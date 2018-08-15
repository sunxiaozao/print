package com.lubian.cpf.service.cpf;

import java.util.List;
import java.util.Map;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfCaseFolderAuthorization;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysUser;

/**
 * CpfCaseFolderAuthorizationService
 * 
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseFolderAuthorizationService {
	public CpfCaseFolderAuthorization findByPK(CpfCaseFolderAuthorization vo);

	public PageModel freeFind(CpfCaseFolderAuthorization vo);

	public void insert(CpfCaseFolderAuthorization vo);

	public void update(CpfCaseFolderAuthorization vo);

	public void delete(CpfCaseFolderAuthorization vo);

	/**
	 * 病历夹统计
	 * 
	 * @param user
	 *            用户
	 * @param type
	 *            统计类型
	 * @return
	 */
	public Integer subtotal(SysUser user, Integer type);

	/**
	 * 通过某个字段条件的到对象
	 * 
	 * @param vo
	 * @return
	 */
	public CpfCaseFolderAuthorization getCaseFolderAuthor(
			CpfCaseFolderAuthorization vo);

	/**
	 * 通过医院字段条件的到map授权记录
	 * 
	 * @param vo
	 * @return
	 */
	public Map<Integer, Integer> getCaseFolderAuthorMap(
			CpfCaseFolderAuthorization vo);

	/**
	 * 通过科室字段条件得到医生map授权记录
	 * 
	 * @param vo
	 * @return
	 */
	public Map<Integer, Integer> getImpowerDoctorMap(
			CpfCaseFolderAuthorization vo);

	/**
	 * 通过某个字段条件的到list对象
	 * 
	 * @param vo
	 * @return
	 */
	public List<CpfCaseFolderAuthorization> getCaseFolderAuthorList(
			CpfCaseFolderAuthorization vo);

	public String getIds(CpfCaseFolderAuthorization vo);

	
	/**
	 * 获得授权的医生ids
	 * @param folderId
	 * @return
	 */
	public String conditionFind(Object folderId);
	
	/**
	 * 获得授权的病例夹
	 * 
	 * @param vo
	 * @return
	 */
	public String conditionFindIds(CpfCaseFolderAuthorization vo);
	
	public PageModel conditionFind(CpfCaseFolderAuthorization vo);
}
