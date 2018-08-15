package com.lubian.cpf.service.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysUser;

/**
 * SysDoctorService
 * 
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysDoctorService {
	public SysDoctor findByPK(SysDoctor vo);

	public PageModel freeFind(SysDoctor vo);

	public void save(SysDoctor vo, HttpServletRequest request);
	
	/**
	 * 临时医生完善个人信息系
	 * @param vo
	 * @param request
	 */
	public SysUser savePerfect(SysDoctor vo, HttpServletRequest request);
	
	public void update(SysDoctor vo, HttpServletRequest request);

	public void insert(SysDoctor vo);

	public void update(SysDoctor vo);

	public void delete(SysDoctor vo);
	
	public List<SysDoctor> findFindByDoctorList(SysDoctor vo);
	
	public SysDoctor findDoctor(SysDoctor vo);
	
	public Map<String, String> findDoctorMap(SysDoctor vo);
	/**
	 * 根据userid 查询医生信息
	 * @param userId
	 * @return
	 */
	public SysDoctor findDoctorByUserId(Integer userId);
	
	/**
	 * 查询该医生所属医院的医生id 集合
	 * @param userId 即doctor表中的id
	 * @return
	 */
	public String doctorIdsByHospital(Integer userId);
	
	public List<SysDoctor> idNotInSearch(String ids);
	
}
