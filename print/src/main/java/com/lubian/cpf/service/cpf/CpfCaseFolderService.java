package com.lubian.cpf.service.cpf;

import java.util.List;
import java.util.Map;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.SysUser;

/**
 * CpfCaseFolderService
 * 
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseFolderService {
	public CpfCaseFolder findByPK(CpfCaseFolder vo);

	public PageModel freeFind(CpfCaseFolder vo);

	public void insert(CpfCaseFolder vo);

	public void update(CpfCaseFolder vo);

	public void delete(CpfCaseFolder vo);

	public List<CpfCaseFolder> freeFindList(CpfCaseFolder vo);

	public List<CpfCaseFolder> find(CpfCaseFolder vo);

	public Map<Integer, String> findFolderMap(CpfCaseFolder vo);

	public Map findFolder(CpfCaseFolder vo);

	/**
	 * 病历夹统计
	 * 
	 * @param user
	 * @return
	 */
	public List<CpfCaseFolder> subtotalOfFolder(Integer patientId,
			String viewStatu);

	/**
	 * 查询病历夹列表
	 * 
	 * @param patientId
	 * @param patientIds
	 * @param viewStatu
	 * @return
	 */
	public List<CpfCaseFolder> subtotalOfFolderList(Integer patientId,
			String patientIds, String viewStatu);

	/**
	 * 病历夹统计
	 * 
	 * @param user
	 * @return
	 */
	public Integer subtotalOfFolder(Integer patientId, String patientIds,
			String viewStatu);

}
