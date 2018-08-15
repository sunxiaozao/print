package com.lubian.cpf.service.sys;

import java.util.List;
import java.util.Map;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysDepartment;

/**
 * SysDepartmentService
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysDepartmentService {
	public SysDepartment findByPK( SysDepartment vo );
	public PageModel freeFind( SysDepartment vo );	
	public void insert( SysDepartment vo );
	public void update( SysDepartment vo );
	public void delete( SysDepartment vo );
	public Map<Integer, String> getDepartmentMap(SysDepartment vo);
	
	public List<SysDepartment> findList(SysDepartment vo);
	/**
	 * 得到该医院的科室id 集合
	 * @param hosptial
	 * @return
	 */
	public List<Integer> idsByHospital(Integer hospitalId);
	/**
	 * 得到该医生的科室id 集合
	 * @param hosptial
	 * @return
	 */
	//public List<Integer> idsByDoctor(Integer doctorId);
	/**
	 * 通过科室id 集合得到该用户的科室信息
	 * @param vo
	 * @return
	 */
	public Map<Integer, String> getDepMapByIds(Integer userId,String userType,List<Integer> ids);
}
