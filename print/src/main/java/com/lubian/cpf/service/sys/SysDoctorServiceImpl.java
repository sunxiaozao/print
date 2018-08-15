package com.lubian.cpf.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.util.UserDataAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.Encrypt;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.SysDepartmentDAO;
import com.lubian.cpf.dao.SysDoctorDAO;
import com.lubian.cpf.dao.SysHospitalDAO;
import com.lubian.cpf.dao.SysUserDAO;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@Service
public class SysDoctorServiceImpl implements SysDoctorService {
	@Autowired
	private SysDoctorDAO sysDoctorDAO;

	@Autowired
	private SysUserDAO userDao;

	@Autowired
	private SysHospitalDAO hospitalDAO;
	@Autowired
	private SysDepartmentDAO departmentDAO;

	public SysDoctor findByPK(SysDoctor vo) {
		return sysDoctorDAO.findByPK(vo);
	}

	public PageModel freeFind(SysDoctor vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = sysDoctorDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<SysDoctor> list = sysDoctorDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(SysDoctor vo) {
		sysDoctorDAO.insert(vo);
	}

	public void update(SysDoctor vo) {
		sysDoctorDAO.update(vo);
	}

	public void delete(SysDoctor vo) {
		sysDoctorDAO.remove(vo);
	}

	@Override
	public void save(SysDoctor vo, HttpServletRequest request) {
		String type = request.getParameter("type");// 获取登录类型
		String name = request.getParameter("user");
		String ischeck_ = request.getParameter("ischeck_");
		String userType = request.getParameter("userType");

		SysUser user = new SysUser();
		user.setUserName(vo.getUsername());// 用户名
		user.setRealName(vo.getFullname());// 真实姓名
		user.setPassword(Encrypt.md5(Enums.InitialPassword.INITIAL_PASSWORD));// 默认密码
		user.setGender(vo.getSex());// 性别
		user.setEnabled(1);// 是否可用(可用)
		user.setUserType(userType);// 用户类型
		user.setCreateBy(SessionUtil.getUser(request).getUserName());
		user.setCreateDt(new Date());
		if (Enums.LoginType.IC.equals(type)) {// 判断输入的是什么类型 IC
			user.setIcId(name);
		} else if (Enums.LoginType.EMAIL.equals(type)) {// Email
			user.setEmail(name);
			vo.setEmail(name);
		} else if (Enums.LoginType.MOBILE.equals(type)) {// 手机
			user.setMobile(name);
			vo.setMobile(name);
		}

		if (null != ischeck_ && "1".equals(ischeck_)) {// 是否作为登录账号
			user.setAccountType(Enums.LoginType.INITIAL + "," + type);
		} else {
			user.setAccountType(Enums.LoginType.INITIAL);
		}
		userDao.save(user);
		vo.setUserId(user.getUserId());
		vo.setUsername(user.getUserName());
		vo.setPassword(user.getPassword());
		vo.setCreator(SessionUtil.getUser(request).getUserName());
		vo.setCreateTime(new Date());
		sysDoctorDAO.save(vo);
	}

	@Override
	public void update(SysDoctor vo, HttpServletRequest request) {
		vo.setModifier(SessionUtil.getUser(request).getUserName());
		vo.setModifyTime(new Date());
		SysUser user = new SysUser();
		user.setUserId(vo.getUserId());
		user = userDao.findByPK(user);
		vo.setUsername(user.getUserName());
		if (vo.getPassword() != null) {
			vo.setPassword(Encrypt.md5(vo.getPassword()));
		}
		sysDoctorDAO.update(vo);
		vo = sysDoctorDAO.findByPK(vo);
		String userType = request.getParameter("userType");
		user.setPassword(vo.getPassword());
		user.setMobile(vo.getMobile());
		user.setEmail(vo.getEmail());
		user.setRealName(vo.getFullname());
		user.setGender(vo.getSex());
		user.setUserType(userType);
		user.setModifyBy(SessionUtil.getUser(request).getUserName());
		user.setModifyDt(new Date());
		String ic = request.getParameter("ic");
		String type = request.getParameter("loginType");
		String loginType = user.getAccountType();

		user.setAccountType(type);

		user.setIcId(ic);

		userDao.update(user);

	}

	// 通过某个科室查询医生表
	public List<SysDoctor> findFindByDoctorList(SysDoctor vo) {
		List<SysDoctor> list = sysDoctorDAO.freeFind(vo);
		if (null != list && list.size() != 0) {
			return list;
		}
		return null;
	}

	// 通过某一字段查询医生对象
	public SysDoctor findDoctor(SysDoctor vo) {
		List<SysDoctor> list = sysDoctorDAO.freeFind(vo);
		if (null != list && list.size() != 0) {
			vo = list.get(0);
			return vo;
		}
		return null;
	}

	// 通过某个科室查询医生表
	public Map<String, String> findDoctorMap(SysDoctor vo) {
		List<SysDoctor> list = sysDoctorDAO.freeFind(vo);
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				vo = list.get(i);
				map.put(vo.getId().toString(), vo.getFullname());
			}
			return map;
		}
		return null;
	}

	/**
	 * 根据userid 查询医生信息
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public SysDoctor findDoctorByUserId(Integer userId) {
		SysDoctor vo = new SysDoctor();
		vo.setUserId(userId);
		List<SysDoctor> list = sysDoctorDAO.freeFind(vo);
		if (null != list && list.size() != 0) {
			vo = list.get(0);
			return vo;
		}
		return null;
	}

	/**
	 * 查询该医生所属医院的医生id 集合
	 * 
	 * @param vo
	 * @return
	 */
	@Override
	public String doctorIdsByHospital(Integer userId) {
		// 得到医生信息
		SysDoctor doctor = new SysDoctor();
		doctor.setId(userId);
		doctor = sysDoctorDAO.findByPK(doctor);
		// 设置查询条件
		Integer hospitalId = 0;
		if (null != doctor) {
			hospitalId = doctor.getHospitalId();
		}
		SysDoctor doctorOfHospital = new SysDoctor();
		doctorOfHospital.setHospitalId(hospitalId);
		List<SysDoctor> list = sysDoctorDAO.freeFind(doctorOfHospital);
		// 得到id 集合
		List<Integer> idList = new ArrayList<Integer>();
		for (SysDoctor sysDoctor : list) {
			idList.add(sysDoctor.getId());
		}
		return StringUtils.convertInInt(idList);
	}

	@Override
	public SysUser savePerfect(SysDoctor vo, HttpServletRequest request) {
		String hospital = request.getParameter("hospital");// 获得医院名称
		String department = request.getParameter("department");// 获得科室
		if ("".equals(vo.getEmail()) || "" == vo.getEmail()) {
			vo.setEmail(null);
		}
		if ("".equals(vo.getMobile()) || "" == vo.getMobile()) {
			vo.setMobile(null);
		}

		SysUser user = new SysUser();
		user.setUserName(vo.getUsername());// 用户名
		user.setRealName(vo.getFullname());// 真实姓名
		user.setPassword(Encrypt.md5(vo.getPassword()));// 默认密码
		user.setGender(vo.getSex());// 性别
		user.setEnabled(1);// 是否可用(可用)
		user.setUserType(Enums.UserType.DOCTOR);// 用户类型
		user.setEmail(vo.getEmail());
		user.setMobile(vo.getMobile());
		user.setCreateDt(new Date());
		SessionUtil.setUser(request, user);
		userDao.save(user);

		SysHospital sysHospital = new SysHospital();
		sysHospital.setNameEq(hospital);
		List<SysHospital> hList = hospitalDAO.freeFind(sysHospital);
		if (hList != null && hList.size() > 0) {
			sysHospital = hList.get(0);
			if (sysHospital != null) {
				vo.setHospitalId(sysHospital.getId());

				SysDepartment sysDepartment = new SysDepartment();
				sysDepartment.setHospitalId(sysHospital.getId());
				sysDepartment.setDepartmentName(department);
				List<SysDepartment> dList = departmentDAO.freeFind(sysDepartment);
				if (dList != null && dList.size() > 0) {
					sysDepartment = dList.get(0);
					if (sysDepartment != null) {
						vo.setDepartmentId(sysDepartment.getId());
					}
				}
			}
		}

		vo.setUserId(user.getUserId());
		vo.setUsername(user.getUserName());
		vo.setPassword(user.getPassword());
		vo.setCreator(SessionUtil.getUser(request).getUserName());
		vo.setCreateTime(new Date());
		sysDoctorDAO.save(vo);

		return user;
	}

	@Override
	public List<SysDoctor> idNotInSearch(String ids) {

		if (ids == null) {
			return sysDoctorDAO.freeFind(new SysDoctor());
		}
		return sysDoctorDAO.idNotInSearch(ids);
	}
}
