package com.lubian.cpf.service.sys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.Encrypt;
import com.lubian.cpf.common.util.Excel;
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
public class UserServiceImpl implements UserService {
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private SysUserDAO userDAO;


	@Autowired
	private SysDoctorDAO sysDoctorDAO;
	@Autowired
	private SysHospitalDAO hospitalDAO;
	@Autowired
	private SysDepartmentDAO departmentDAO;


	public List freeFind(SysUser user) {
		return userDAO.freeFind(user);
	}

	public PageModel getAdminUserList(SysUser user) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = userDAO.countFreeFind(user);
		pm.setTotal(total);
		List<SysUser> datas = userDAO.freeFind(user, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	public Map<String, SysUser> getAllUserMap() {
		HashMap<String, SysUser> map = new HashMap();
		SysUser user = new SysUser();
		List<SysUser> datas = userDAO.freeFind(user);
		DecimalFormat df = new DecimalFormat("#");
		for (SysUser u : datas) {
			map.put(df.format(u.getUserId()), u);
		}
		return map;
	}

	public void deleteUser(SysUser vo) {
		userDAO.remove(vo);

	}

	public SysUser findByPK(SysUser vo) {
		return userDAO.findByPK(vo);

	}

	public void updateUser(SysUser vo) {
		userDAO.update(vo);
	}

	public void insert(SysUser vo) {
		userDAO.insert(vo);
	}

	/**
	 * 更新用户最后一次登陆时间
	 * 
	 * @param userId
	 */
	public void updateLastLogin(Integer userId, String token) {
		SysUser user = new SysUser();
		user.setUserId(userId);
		user.setLastLoginDt(Calendar.getInstance().getTime());
		user.setAccessToken(token);
		try {
			userDAO.update(user);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public SysUser login(SysUser user) {
		user.setPassword(StringUtils.md5(user.getPassword()));
		List<SysUser> list = userDAO.freeFind(user);
		if (list != null && list.size() > 0) {
			return (SysUser) list.get(0);
		} else {
			return null;
		}
	}

	public SysUser getUserByEmail(String email) {
		SysUser user = new SysUser();
		user.setEmailEq(email);
		List<SysUser> list = userDAO.freeFind(user);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	public SysUser getUserByUserName(String name) {
		SysUser user = new SysUser();
		user.setUserNameEq(name);
		List<SysUser> list = userDAO.freeFind(user, 0, 1);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}


	public boolean checkUserMobileExistance(String mobile) {
		SysUser user = new SysUser();
		user.setMobileEq(mobile);
		int count = userDAO.countFreeFind(user);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 得到与入参user统一机构的所有用户id，拼成sql in查询部分格式
	 * 
	 * @param user
	 * @return
	 */
	public String getUserIdStrByOrgId(SysUser user) {
		if (user == null)
			return null;
		if (user.getOrgId() == null)
			return user.getUserId() + "";
		SysUser vo = new SysUser();
		vo.setOrgId(user.getOrgId());
		List<SysUser> list = userDAO.freeFind(vo);
		List idList = new ArrayList();
		if (list.size() > 0) {
			for (SysUser u : list) {
				idList.add(u.getUserId());
			}
		}
		return StringUtils.convertInInt(idList);
	}

	public String getUserIdStr(SysUser user) {
		if (user == null)
			return null;
		List<SysUser> list = userDAO.freeFind(user);
		List idList = new ArrayList();
		if (list.size() > 0) {
			for (SysUser u : list) {
				idList.add(u.getUserId());
			}
		}
		return StringUtils.convertInInt(idList);
	}

	public boolean checkUser(String data, String type, SysUser user) {
		SysUser users = new SysUser();
		if (Enums.LoginType.IC.equals(type)) {// 判断输入的是什么类型 IC
			users.setIcIdEq(data);
		} else if (Enums.LoginType.EMAIL.equals(type)) {// Email
			users.setEmailEq(data);

		} else if (Enums.LoginType.MOBILE.equals(type)) {// 手机
			users.setMobileEq(data);
		}
		List list = userDAO.freeFind(users);

		if (null != list && list.size() > 0) {
			if (user != null) {
				users = (SysUser) list.get(0);
				int uid = user.getUserId();
				int id = users.getUserId();
				if (uid == id) {
					return true;
				}
			}

			return false;
		} else {
			//return checkpatient(data, type, patient);
		}
		return false;

	}



	/**
	 * 检测是否存在该用户，并返还用户id
	 * 
	 * @param data
	 *            用户名、邮箱等
	 * @param type
	 *            登陆类型
	 * @return 用户id
	 */
	@Override
	public Integer checkUserReturnId(String data, String type) {
		SysUser user = new SysUser();
		if (Enums.LoginType.IC.equals(type)) {// 判断输入的是什么类型 IC
			user.setIcId(data);
		} else if (Enums.LoginType.EMAIL.equals(type)) {// Email
			user.setEmail(data);
		} else if (Enums.LoginType.MOBILE.equals(type)) {// 手机
			user.setMobile(data);
		} else {
			user.setUserName(data);
		}
		List<SysUser> list = userDAO.freeFind(user);

		if (null != list && list.size() > 0) {
			return list.get(0).getUserId();
		}

		return -1;
	}

	@Override
	public void saveDocer(SysUser user, HttpServletRequest request) {
		user.setPassword(Encrypt.md5(user.getPassword()));
		user.setCreateDt(new Date());
		user.setEnabled(1);
		user.setAccountType(Enums.LoginType.INITIAL);
		userDAO.insert(user);
		try {
			SysDoctor vo = new SysDoctor();
			vo.setUserId(user.getUserId());
			vo.setUsername(user.getUserName());
			vo.setPassword(user.getPassword());
			vo.setFullname(user.getRealName());
			vo.setSex(user.getGender());
			vo.setEmail(user.getEmail());
			vo.setMobile(user.getMobile());
			String hospitalId = request.getParameter("hospitalId");
			if (hospitalId != null && !"".equals(hospitalId)) {
				vo.setHospitalId(Integer.parseInt(hospitalId));
			}
			String departmentId = request.getParameter("departmentId");
			if (departmentId != null && !"".equals(departmentId)) {
				vo.setDepartmentId(Integer.parseInt(departmentId));
			}

			vo.setTitle(request.getParameter("title"));
			vo.setCreator(SessionUtil.getUser(request).getUserName());
			vo.setCreateTime(new Date());
			sysDoctorDAO.save(vo);
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}

	}



	/**
	 * 医生导入辅助方法
	 * 
	 * @param list
	 * @param user
	 * @param hMap
	 * @return
	 */
	private Map<String, SysDoctor> doctor(List<String> list, SysUser user) {
		Map<String, SysDoctor> m = new HashMap<String, SysDoctor>();
		Map mp = hMap();

		SysDoctor doctor = new SysDoctor();
		doctor.setFullname(list.get(0));// 姓名
		doctor.setEmail(list.get(2));// 邮箱

		String userName = list.get(1);
		if (userName != null && !"".equals(userName)) {
			doctor.setUsername(userName);// 用户名
		} else {
			doctor.setUsername(doctor.getEmail());// 如果用户名为空默认默认使用邮箱作为登录名
		}

		doctor.setMobile(list.get(3));// 手机
		String pwd = list.get(4);// 密码
		if (pwd != null && !"".equals(pwd)) {// 如果密码为空这使用默认密码
			doctor.setPassword(Encrypt.md5(pwd));
		} else {
			doctor.setPassword(Encrypt
					.md5(Enums.InitialPassword.INITIAL_PASSWORD));// 默认密码
		}
		Object sex = Enums.setSexKey().get(list.get(5));
		if (sex != null) {
			doctor.setSex(sex.toString());
		}

		Object hospitalId = mp.get(list.get(6));// 获得医院信息id
		if (hospitalId != null) {
			doctor.setHospitalId((Integer) hospitalId);
			Map dMap = (Map) mp.get(doctor.getHospitalId());// 获取医院的科室
			if (dMap != null) {// 如果存在科室
				Object departmentId = dMap.get(list.get(7));// 获取科室id
				if (departmentId != null) {
					doctor.setDepartmentId((Integer) departmentId);
				}
			}
		}

		doctor.setTitle(list.get(8));
		Object type = Enums.getUserKey().get(list.get(9));

		if (doctor.getUsername() != null && !"".equals(doctor.getUsername())
				&& doctor.getUsername().length() >= 6 && type != null) {// 判断登录名是否为空

			SysUser u = new SysUser();
			u.setUserName(doctor.getUsername());// 用户名
			u.setPassword(doctor.getPassword());// 密码
			u.setEmail(doctor.getEmail());// 邮箱
			u.setMobile(doctor.getMobile());// 手机
			u.setGender(doctor.getSex());// 性别
			u.setUserType(type.toString());// 用户类型
			u.setEnabled(1);// 是否可用
			u.setCreateBy(user.getUserName());
			u.setCreateDt(new Date());
			userDAO.insert(u);
			doctor.setUserId(u.getUserId());
			doctor.setCreator(user.getUserName());
			doctor.setCreateTime(new Date());

			sysDoctorDAO.save(doctor);

			m.put("success", doctor);
		} else {
			m.put("failure", doctor);
		}

		return m;
	}

	/**
	 * 获得医院科室信息
	 * 
	 * @return
	 */
	private Map hMap() {
		List<SysHospital> hList = hospitalDAO.freeFind(new SysHospital());
		Map hMap = new HashMap();
		for (SysHospital sysHospital : hList) {
			Map dMap = new HashMap();
			SysDepartment department = new SysDepartment();
			department.setHospitalId(sysHospital.getId());
			List<SysDepartment> dList = departmentDAO.freeFind(department);
			for (SysDepartment sysDepartment : dList) {
				Map map = new HashMap();
				dMap.put(sysDepartment.getDepartmentName(),
						sysDepartment.getId());
			}
			hMap.put(sysHospital.getName(), sysHospital.getId());
			hMap.put(sysHospital.getId(), dMap);
		}

		return hMap;
	}

	@Override
	public void UpdateMember(SysUser vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateDoctor(SysUser vo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkUserNameExistance(String name) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Map<String, List> userImport(MultipartFile file, SysUser user,
			String type) {
		// TODO Auto-generated method stub
		return null;
	}
}
