package com.lubian.cpf.service.cpf;

import java.util.List;
import java.util.Map;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CdfNotebook;
import com.lubian.cpf.vo.SysUser;

/**
 * CdfNotebookService
 * 
 * @author:Lusir<lusire@gmail.com>
 */
public interface CdfNotebookService {
	public CdfNotebook findByPK(CdfNotebook vo);

	public PageModel freeFind(CdfNotebook vo);

	public void insert(CdfNotebook vo);

	public void update(CdfNotebook vo);

	public void delete(CdfNotebook vo);

	public Map find(CdfNotebook vo);
	public Map find(Integer id,String ids,String viewStatu);

	/**
	 * 留言统计
	 * 
	 * @param user用户
	 * @param viewStatu
	 *            统计类型
	 * @return
	 */
	// public List<CdfNotebook> subtotal(SysUser user,String viewStatu);
	public Integer subtotal(Integer userId, String viewStatu, String userType);

	/**
	 * 统计病人的最新留言
	 * 
	 * @param patientId
	 *            病人id
	 * @param patientIds
	 *            病例id集合
	 * @param viewStatu
	 *            统计类型
	 * @return
	 */
	public Integer subtotalPatient(Integer patientId, String patientIds,
			String viewStatu);

	/**
	 * 留言统计
	 * 
	 * @param user用户
	 * @param viewStatu
	 *            统计类型
	 * @return
	 */
	public Map subtotalOfMap(CdfNotebook vo);

	/**
	 * 根据 id字符串，更新多条留言记录
	 * 
	 * @param ids
	 */
	public void updateByIds(String ids);
}
