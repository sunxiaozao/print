package com.lubian.cpf.service.cpf;

import java.util.List;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfPatientRelationUser;

/**
 * CpfPatientRelationUserService
 * 
 * @author:Lusir<lusire@gmail.com>
 */
public interface CpfPatientRelationUserService {
	public CpfPatientRelationUser findByPK(CpfPatientRelationUser vo);

	public PageModel freeFind(CpfPatientRelationUser vo);

	public void insert(CpfPatientRelationUser vo);

	public void update(CpfPatientRelationUser vo);

	public void delete(CpfPatientRelationUser vo);
	public boolean count(CpfPatientRelationUser vo);

	/**
	 * 返回关联的列表
	 * 
	 * @param vo
	 * @return
	 */
	public List<CpfPatientRelationUser> freeFindList(CpfPatientRelationUser vo);
	
	/**
	 * 根据userId获得一个关联对象
	 * @param userId
	 * @return
	 */
	public CpfPatient freeFind(Integer userId);
	
	
	/**
	 * 更加userId or patientId 查询关联的病人id
	 * @param userId
	 * @param patientId
	 * @return
	 */
	public String freeFind(Integer userId,Integer patientId);


}
