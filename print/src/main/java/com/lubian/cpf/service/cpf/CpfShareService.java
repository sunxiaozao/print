package com.lubian.cpf.service.cpf;

import java.util.List;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfShare;
import com.lubian.cpf.vo.CpfSharing;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysUser;

/**
 * CpfShareService
 * 
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfShareService {
	public CpfShare findByPK(CpfShare vo);

	public PageModel freeFind(CpfShare vo);

	public void insert(CpfShare vo);

	public void update(CpfShare vo);

	public void delete(CpfShare vo);

	/**
	 * 分享统计
	 * 
	 * @param user
	 * @param viewStatu
	 * @return
	 */
	// public List<CpfShare> subtotal(SysUser user,String viewStatu);
	public Integer subtotal(Integer userId, String userType, String viewStatu);

	/**
	 * 病人分享统计
	 * 
	 * @param patientId
	 *            病人id
	 * @param patientIds
	 *            病人id集合
	 * @param viewStatu
	 *            统计类型
	 * @return
	 */
	public Integer subtotalPatient(Integer patientId, String patientIds,
			String viewStatu);

	/**
	 * 分享统计 分页
	 * 
	 * @param user
	 * @param viewStatu
	 * @return
	 */
	// public PageModel subtotalOfPage(SysUser user,String viewStatu);
	public PageModel subtotalOfPage(Integer userId, String userType,
			String viewStatu);

	/**
	 * 根据条件查询分享记录
	 * 
	 * @param vo
	 * @return cpfShare
	 */
	public CpfShare findByCommand(CpfShare vo);

	/**
	 * 根据条件查询分享记录
	 * 
	 * @param vo
	 * @return list
	 */
	public List<CpfShare> listFreeFind(CpfShare vo);

	/**
	 * 根据条件统计分享数量
	 * 
	 * @param vo
	 * @return int
	 */
	public Integer freeCount(CpfShare vo);

	/**
	 * 根据条件升序查询分享记录
	 * 
	 * @param vo
	 * @return 第一条cpfShare
	 */
	public CpfShare findByOrder(CpfShare vo);

	/**
	 * 根据条件 查询 该医院的分享记录
	 * 
	 * @param superDoctor
	 * @return
	 */
	public List<CpfShare> findByHospital(CpfShare vo, Integer hospitalId);

	/**
	 * 根据条件 查询 该医院的分享记录
	 * 
	 * @param superDoctor
	 * @return
	 */
	public Integer countByHospital(CpfShare vo, Integer hospitalId);
	
	
	
	
	public Integer countShare(SysDoctor doctor,String viewStatu);
	public PageModel findtShare(SysDoctor doctor,String viewStatu);
	
	
	
	public boolean searchByIdStr(String ids);
	
	
	public void saveSharing(CpfSharing sharing);
	public boolean findSharing(CpfSharing sharing);
	
	

}
