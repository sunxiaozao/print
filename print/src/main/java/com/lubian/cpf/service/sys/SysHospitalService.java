package com.lubian.cpf.service.sys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

/**
 * SysHospitalService
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysHospitalService {
	public SysHospital findByPK( SysHospital vo );
	public PageModel freeFind( SysHospital vo );	
	public void insert( SysHospital vo );
	public void update( SysHospital vo );
	public void delete( SysHospital vo );
	public Map<Integer, String> getHospitalMap(SysHospital hospital);
	
	public List<SysHospital> findList(SysHospital vo);
	
	/**
	 * 医院统计
	 * @param user
	 * @return
	 */
	public List<SysHospital> subtotalOfHospital(SysUser user);
	
	/**
	 * 得到登陆人的医院名称
	 * @param user
	 * @return
	 */
	public String getHospitalName(SysUser user);
	
	 public List<SysHospital> findFind(Integer id);
}
