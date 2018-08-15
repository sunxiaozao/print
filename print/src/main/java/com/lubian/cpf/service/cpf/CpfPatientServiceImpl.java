package com.lubian.cpf.service.cpf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfPatientDAO;
import com.lubian.cpf.dao.CpfPatientRelationUserDAO;
import com.lubian.cpf.dao.SysUserDAO;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfPatientRelationUser;
import com.lubian.cpf.vo.SysUser;

@Service
public class CpfPatientServiceImpl implements CpfPatientService {
	@Autowired
	private CpfPatientDAO cpfPatientDAO;

	@Autowired
	private SysUserDAO userDao;

	@Autowired
	private CpfCaseHistoryService cpfCaseHistoryService;// 病历资料

	@Autowired
	private CpfPatientRelationUserDAO cpfPatientRelationUserDAO;

	public CpfPatient findByPK(CpfPatient vo) {
		return cpfPatientDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfPatient vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfPatientDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfPatient> list = cpfPatientDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfPatient vo) {
		cpfPatientDAO.insert(vo);
	}

	public void update(CpfPatient vo) {
		cpfPatientDAO.update(vo);
	}

	public void delete(CpfPatient vo) {
		cpfPatientDAO.remove(vo);
	}

	/**
	 * 根据userId查询病人信息
	 */
	public CpfPatient findPatientByUserId(CpfPatient vo) {
		List<CpfPatient> list = cpfPatientDAO.freeFind(vo);
		if (null != list && list.size() != 0) {
			vo = list.get(0);
			return vo;
		}
		return null;
	}

	@Override
	public void update(HttpServletRequest request, CpfPatient vo, MultipartFile file) {
		String path = null;
		String paths = Constant.ROOT_PATH + Constant.IMPORT_PATH;

		if (file != null && file.getSize() > 0) {// 获取头像信息
			path = com.lubian.cpf.common.util.Upload.upload(request, file, paths);
			if (!StringUtils.isBlank(path)) {// 设置头像
				vo.setSmallImage(Constant.IMPORT_PATH + path);
			}
		}

		cpfPatientDAO.update(vo);
		SessionUtil.setPatient(request, vo);

	}

	@Override
	public boolean findPatientByCardId(CpfPatient vo, SysUser user) {
		List list = cpfPatientDAO.freeFind(vo);
		if (list != null && list.size() > 0) {
			CpfPatient patient = (CpfPatient) list.get(0);
			if (user != null) {
				int uId = user.getUserId();
				int puId = patient.getUserId();
				if (uId == puId) {
					return true;
				}
			}

			return false;
		}
		return true;
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

		CpfPatient patient = new CpfPatient();
		if (Enums.LoginType.IC.equals(type)) {// 判断输入的是什么类型 IC
			patient.setIcIdEq(data);
		} else if (Enums.LoginType.EMAIL.equals(type)) {// Email
			patient.setEmailEq(data);
		} else if (Enums.LoginType.MOBILE.equals(type)) {// 手机
			patient.setMobileEq(data);
		} else {
			patient.setUserNameEq(data);
		}
		List<CpfPatient> lists = cpfPatientDAO.freeFind(patient);

		if (null != lists && lists.size() > 0) {

			return lists.get(0).getId();
		}
		return -1;

	}

	/**
	 * 查询病人姓名map集合
	 */
	public Map<Integer, String> findPatientMap(CpfPatient vo) {
		List<CpfPatient> list = cpfPatientDAO.freeFind(vo);
		Map map = new LinkedHashMap();
		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				CpfPatient p = list.get(i);
				map.put(p.getId(), p.getFullname());
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 查询属于该医生的病人集合
	 * 
	 * @return
	 */
	@Override
	public List<CpfPatient> subtotalOfPatient(SysUser user) {

		// 调用 得到医生被授权和分享的病历资料 方法
		List<CpfCaseHistory> caseHistoryList = cpfCaseHistoryService.subtotalByAuthoriAndShare(user);

		// System.out.println("最终的caseHistoryList size "+caseHistoryList.size());

		// 该医生的病人集合
		List<CpfPatient> list = new ArrayList<CpfPatient>();
		// 通过病历资料得到该医生的病人
		Integer patientId = 0;
		for (CpfCaseHistory cpfCaseHistory : caseHistoryList) {

			if (null != cpfCaseHistory) {
				if (null != cpfCaseHistory.getPatientId() && 0 != cpfCaseHistory.getPatientId()) {
					if (patientId == cpfCaseHistory.getPatientId()) {
						continue;
					}
					patientId = cpfCaseHistory.getPatientId();
				}

			}

			CpfPatient vo = new CpfPatient();
			vo.setId(patientId);

			list.add(cpfPatientDAO.findByPK(vo));
		}

		return list;
	}

	/**
	 * 检测用户是否存在，并返还用户id 集合
	 * 
	 * @param data
	 *            用户名、邮箱等
	 * @param type
	 *            登陆类型
	 * @return 用户id
	 */
	@Override
	public String checkUserReturnIds(String data, String type) {
		CpfPatient user = new CpfPatient();
		if (Enums.LoginType.IC.equals(type)) {// 判断输入的是什么类型 IC
			user.setIcId(data);
		} else if (Enums.LoginType.EMAIL.equals(type)) {// Email
			user.setEmail(data);
		} else if (Enums.LoginType.MOBILE.equals(type)) {// 手机
			user.setMobile(data);
		} else {
			user.setUserName(data);
		}
		List<CpfPatient> list = cpfPatientDAO.freeFind(user);
		List<Integer> idList = new ArrayList<Integer>();
		for (CpfPatient cpfPatient : list) {
			idList.add(cpfPatient.getId());
		}

		String ids = StringUtils.convertInInt(idList);

		return ids;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findPatientIds(Integer uId) {
		CpfPatient patient = new CpfPatient();
		patient.setUserId(uId);
		List<CpfPatient> list = cpfPatientDAO.freeFind(patient);
		if (list != null && list.size() > 0) {
			List<Integer> idList = new ArrayList<Integer>();
			for (CpfPatient cpfPatient : list) {
				idList.add(cpfPatient.getId());
			}
			String ids = StringUtils.convertInInt(idList);

			return ids;
		} else {
			return null;
		}

	}

	@Override
	public CpfPatient findByUserId(Integer uId) {
		CpfPatient patient = new CpfPatient();
		patient.setUserId(uId);
		List<CpfPatient> list = cpfPatientDAO.freeFind(patient);
		if (list != null && list.size() > 0) {
			for (CpfPatient cpfPatient : list) {
				if (Enums.isYesOrIsNo.IS_YES.equals(cpfPatient.getIcLoginFlag())) {
					return cpfPatient;
				}
			}
			return null;

		} else {
			return null;
		}
	}

	@Override
	public List<CpfPatient> findList(CpfPatient vo) {

		return cpfPatientDAO.freeFind(vo);
	}

	@Override
	public CpfPatient find(String userName, String password) {
		CpfPatient patient = new CpfPatient();
		patient.setUserNameEq(userName);
		patient.setPasswordEq(password);
		// user.setUserType(Enums.UserType.ADMIN);
		try {
			List list = cpfPatientDAO.freeFind(patient);
			if (list != null && list.size() > 0)
				return (CpfPatient) list.get(0);
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public Map<Integer, Integer> findMap(CpfPatient vo) {
		List<CpfPatient> list = cpfPatientDAO.freeFind(vo);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (CpfPatient cpfPatient : list) {
			map.put(cpfPatient.getHospitalId(), cpfPatient.getId());
		}
		return map;
	}

	@Override
	public String checkUserReturnId(String data) {
		CpfPatient user = new CpfPatient();
		
			user.setFullname(data);
		
		List<CpfPatient> list = cpfPatientDAO.freeFind(user);
		List<Integer> idList = new ArrayList<Integer>();
		for (CpfPatient cpfPatient : list) {
			idList.add(cpfPatient.getId());
		}

		String ids = StringUtils.convertInInt(idList);

		return ids;
	}

}
