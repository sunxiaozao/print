package com.lubian.cpf.service.sys;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysDepartmentDAO;
import com.lubian.cpf.dao.SysDoctorDAO;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysDoctor;

@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {
	@Autowired
	private SysDepartmentDAO sysDepartmentDAO;
	
	@Autowired
	private SysDoctorDAO sysDoctorDAO;

	public SysDepartment findByPK(SysDepartment vo) {
		return sysDepartmentDAO.findByPK(vo);
	}

	public PageModel freeFind(SysDepartment vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = sysDepartmentDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<SysDepartment> list = sysDepartmentDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(SysDepartment vo) {
		sysDepartmentDAO.insert(vo);
	}

	public void update(SysDepartment vo) {
		sysDepartmentDAO.update(vo);
	}
	
	public void delete(SysDepartment vo) {
		sysDepartmentDAO.remove(vo);
	}

	/**
	 * 得到所有科室的Map集合
	 */
	public Map<Integer, String> getDepartmentMap(SysDepartment vo) {
		List<SysDepartment> list = sysDepartmentDAO.freeFind(vo);
		Map map = new LinkedHashMap();
		if(null!=list&&list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				SysDepartment department=list.get(i);
				map.put(department.getId(), department.getDepartmentName());
			}
			return map;
		}else{
			return null;
		}
	}

	@Override
	public List<SysDepartment> findList(SysDepartment vo) {
		
		return sysDepartmentDAO.freeFind(vo);
	}
	/**
	 * 得到该医院的科室id 集合
	 * @param hosptial
	 * @return
	 */
	@Override
	public List<Integer> idsByHospital(Integer hospitalId) {
		SysDepartment vo=new SysDepartment();
		vo.setHospitalId(hospitalId);
		List<SysDepartment> list = sysDepartmentDAO.freeFind(vo);
		
		List<Integer> idList=new ArrayList<Integer>();
		if (null!=list && list.size()>0) {
			for (SysDepartment sysDepartment : list) {
				idList.add(sysDepartment.getId());
			}
		}
		return idList;
	}
	/**
	 * 通过科室id 集合得到该用户的科室信息
	 * @param vo
	 * @return
	 */
	@Override
	public Map<Integer, String> getDepMapByIds(Integer userId, String userType,
			List<Integer> departIdList) {
		// 登入者为超级医生，查询该医院所有科室
		if (!userType.equals(Enums.UserType.MEMBER)) {
			SysDoctor doctor = new SysDoctor();
			doctor.setId(userId);
			doctor = sysDoctorDAO.findByPK(doctor);
			if (userType.equals(Enums.UserType.DOCTOR)) {
				departIdList=new ArrayList<Integer>();
				departIdList.add(doctor.getDepartmentId());
			} else if (userType.equals(Enums.UserType.SUPER_DOCTOR)) {
				departIdList = this.idsByHospital(doctor.getHospitalId());
			}
		}
		
		// 得到该医生权限下病历夹的所有科室去重
		for (int i = 0; i < departIdList.size() - 1; i++) {
			for (int j = departIdList.size() - 1; j > i; j--) {
				if (departIdList.get(j) == departIdList.get(i)) {
					departIdList.remove(j);
				}
			}
		}
		// 得到科室map
		Map<Integer, String> departMap = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < departIdList.size(); i++) {
			SysDepartment department = new SysDepartment();
			department.setId(departIdList.get(i));
			department = sysDepartmentDAO.findByPK(department);
			if(department!=null){
				departMap.put(department.getId(), department.getDepartmentName());
			}
			
		}
		return departMap;
	}
	
}
