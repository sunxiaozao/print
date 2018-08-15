package com.lubian.cpf.service.sys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfCaseHistoryDAO;
import com.lubian.cpf.dao.CpfPatientDAO;
import com.lubian.cpf.dao.SysDoctorDAO;
import com.lubian.cpf.dao.SysHospitalDAO;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@Service
public class SysHospitalServiceImpl implements SysHospitalService {
	@Autowired
	private SysHospitalDAO sysHospitalDAO;

	@Autowired
	private CpfCaseHistoryDAO cpfCaseHistoryDAO;// 病历资料

	@Autowired
	private CpfPatientDAO cpfPatientDAO;

	@Autowired
	private SysDoctorDAO sysDoctorDAO;

	public SysHospital findByPK(SysHospital vo) {
		return sysHospitalDAO.findByPK(vo);
	}

	public PageModel freeFind(SysHospital vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = sysHospitalDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<SysHospital> list = sysHospitalDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(SysHospital vo) {
		sysHospitalDAO.insert(vo);
	}

	public void update(SysHospital vo) {
		sysHospitalDAO.update(vo);
	}

	public void delete(SysHospital vo) {
		sysHospitalDAO.remove(vo);
	}

	/**
	 * 得到医院的map集合
	 */
	public Map<Integer, String> getHospitalMap(SysHospital vo) {
		List<SysHospital> list = sysHospitalDAO.freeFind(vo);
		Map map = new LinkedHashMap();
		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				SysHospital sysHospital = list.get(i);
				map.put(sysHospital.getId(), sysHospital.getName());
			}
			return map;
		} else {
			return null;
		}
	}

	@Override
	public List<SysHospital> findList(SysHospital vo) {

		return sysHospitalDAO.freeFind(vo);
	}

	/**
	 * 医院统计
	 * 
	 * @param user
	 * @param newest
	 *            当前时间
	 * @return
	 */
	@Override
	public List<SysHospital> subtotalOfHospital(SysUser user) {
		CpfPatient patient = new CpfPatient();
		patient.setUserId(user.getUserId());
		List<CpfPatient> patientList = cpfPatientDAO.freeFind(patient);
		if (null != patientList && patientList.size() > 0) {
			patient = patientList.get(0);
		}
		// 得到该病人所有的病历资料
		CpfCaseHistory vo = new CpfCaseHistory();
		vo.setPatientId(patient.getId());
		List<CpfCaseHistory> list = cpfCaseHistoryDAO.freeFind(vo);
		// 从中取出医院id,存入一个TreeSet中
		TreeSet idSet = new TreeSet();
		if (null != list && list.size() > 0) {
			for (CpfCaseHistory cpfCaseHistory : list) {
				idSet.add(cpfCaseHistory.getHospitalId());
			}
		}

		// 循环遍历该医院id集合，得到该医院信息
		List<SysHospital> hosList = new ArrayList<SysHospital>();
		Iterator it = idSet.iterator();
		if (it != null) {
			while (it.hasNext()) {
				SysHospital h = new SysHospital();
				String itStr = "0";
				itStr = it.next().toString();
				h.setId(Integer.parseInt(itStr));
				h = sysHospitalDAO.findByPK(h);
				hosList.add(h);
			}
		}
		return hosList;
	}

	/**
	 * 得到登陆人的医院名称
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String getHospitalName(SysUser user) {
		SysDoctor doctor = new SysDoctor();
		doctor.setUserId(user.getUserId());
		List<SysDoctor> list = sysDoctorDAO.freeFind(doctor);
		if (null != list && list.size() > 0) {
			doctor = list.get(0);
		}
		// 该医生的医院信息
		SysHospital sysHospital = new SysHospital();
		sysHospital.setId(doctor.getHospitalId());
		sysHospital = sysHospitalDAO.findByPK(sysHospital);
		String hospitalName = sysHospital.getHospitalId();

		return hospitalName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysHospital> findFind(Integer id) {
		return sysHospitalDAO.searchByIdOrParentId(id, id);
	}
}
