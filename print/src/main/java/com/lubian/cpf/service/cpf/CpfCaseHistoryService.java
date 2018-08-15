package com.lubian.cpf.service.cpf;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysUser;

/**
 * CpfCaseHistoryService
 * 
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfCaseHistoryService {
	public CpfCaseHistory findByPK(CpfCaseHistory vo);

	public PageModel freeFind(CpfCaseHistory vo);

	public void insert(CpfCaseHistory vo);

	public void update(CpfCaseHistory vo);

	public void delete(CpfCaseHistory vo);
	
	public boolean count(String studyID,String patientID);
	public CpfCaseHistory find(String studyID,String patientID,String patientId,String is_Yes);

	/**
	 * 病人统计
	 * 
	 * @param user
	 *            用户
	 * @param type
	 *            统计类型
	 * @return
	 */
	public Integer subtotal(SysUser user, Integer type);

	/**
	 * 医院统计
	 * 
	 * @param user
	 * @param newest
	 *            当前时间(此系统暂时未使用)
	 * @return
	 */
	public Integer subtotalOfHospital(Integer patientId, Date newest);

	/**
	 * 医院方式 分类汇总 报告 胶片 申请单 化验单
	 * 
	 * @param patientId
	 *            病例资料id
	 * @param type
	 *            F胶片 R诊断书 D申请单 T化验单
	 * @param viewStatu
	 *            是否查看
	 * @return
	 */
	public Integer subtotalByCommand(Integer patientId, String type,
			String viewStatu);

	/**
	 * 微医通登录 分类汇总 报告 胶片 申请单 化验单
	 * 
	 * @param patientId
	 *            病例资料id数组
	 * @param type
	 *            F胶片 R诊断书 D申请单 T化验单
	 * @param viewStatu
	 *            是否查看
	 * @return
	 */
	public Integer subtotalByCommand(String patientIds, String type,
			String viewStatu);

	/**
	 * 分类汇总 报告 胶片 申请单 化验单(医生)
	 * 
	 * @param user
	 * @param type
	 * @param viewStatu
	 * @return
	 */
	// public List<CpfCaseHistory> subtotalByDoctor(Integer userId,String type);
	public int subtotalByDoctor(SysDoctor doctor, String type);

	/**
	 * 得到医生被授权的病历资料
	 * 
	 * @param userId
	 * @return
	 */
	// public List<CpfCaseHistory> subtotalByAuthori(Integer userId,Integer
	// hospitalId);
	public List<CpfCaseHistory> subtotalByAuthori(SysUser user);

	/**
	 * 得到医生被授权和被分享的病历资料
	 * 
	 * @param userId
	 * @return
	 */
	// public List<CpfCaseHistory> subtotalByAuthoriAndShare(Integer userId);
	public List<CpfCaseHistory> subtotalByAuthoriAndShare(SysUser user);

	public List<CpfCaseHistory> freeFindLsit(CpfCaseHistory vo);
	public int count(CpfCaseHistory vo);

	public PageModel freeFindInWeek(List<CpfCaseHistory> list);

	public Map<String, List<CpfCaseHistory>> imports(MultipartFile file,
			SysUser user);

	public void updateRelateStatus(String relateStatus, Integer patientId,
			Integer folderId, String idStr);

	
	
	
	public String subtotalByid(SysDoctor doctor, String type);
	
	
	
	
	
	
	
	
}
