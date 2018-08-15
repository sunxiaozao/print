package com.lubian.cpf.service.cpf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.Encrypt;
import com.lubian.cpf.common.util.Excel;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfCaseFolderAuthorizationDAO;
import com.lubian.cpf.dao.CpfCaseFolderDAO;
import com.lubian.cpf.dao.CpfCaseHistoryDAO;
import com.lubian.cpf.dao.CpfShareDAO;
import com.lubian.cpf.dao.CpfSharingDAO;
import com.lubian.cpf.dao.SysDepartmentDAO;
import com.lubian.cpf.dao.SysDoctorDAO;
import com.lubian.cpf.dao.SysHospitalDAO;
import com.lubian.cpf.vo.CdfFavorite;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseFolderAuthorization;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfPatientRelationUser;
import com.lubian.cpf.vo.CpfShare;
import com.lubian.cpf.vo.CpfSharing;
import com.lubian.cpf.vo.SysDepartment;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@Service
public class CpfCaseHistoryServiceImpl implements CpfCaseHistoryService {
	@Autowired
	private CpfCaseHistoryDAO cpfCaseHistoryDAO;

	@Autowired
	private CpfShareDAO cpfShareDAO;

	@Autowired
	private SysDoctorDAO sysDoctorDAO;
	@Autowired
	private CpfCaseFolderAuthorizationService cpfCaseFolderAuthorizationService; // 病历夹授权表
	@Autowired
	private CpfCaseFolderAuthorizationDAO cpfCaseFolderAuthorizationDAO;

	@Autowired
	private CpfCaseFolderDAO cpfCaseFolderDAO;

	@Autowired
	private SysHospitalDAO hospitalDAO;

	@Autowired
	private SysDepartmentDAO departmentDAO;
	@Autowired
	private CpfPatientService patientService;

	@Autowired
	private CpfCaseFolderService cpfCaseFolderService; // 病历夹表

	@Autowired
	private CpfPatientRelationUserService pruService;

	@Autowired
	private CpfSharingDAO sharingDAO;

	public CpfCaseHistory findByPK(CpfCaseHistory vo) {
		return cpfCaseHistoryDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfCaseHistory vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfCaseHistoryDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfCaseHistory> list = cpfCaseHistoryDAO.freeFind(vo, pageIndex, pageSize, "case_date desc");
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfCaseHistory vo) {
		cpfCaseHistoryDAO.insert(vo);
	}

	public void update(CpfCaseHistory vo) {
		cpfCaseHistoryDAO.update(vo);
	}

	public void delete(CpfCaseHistory vo) {
		cpfCaseHistoryDAO.remove(vo);
	}

	/**
	 * 病人统计
	 * 
	 * @param user
	 *            用户
	 * @param type
	 *            统计类型
	 * @return
	 */
	@Override
	public Integer subtotal(SysUser user, Integer type) {
		CpfCaseHistory vo = new CpfCaseHistory();
		Integer count = 0;
		vo.setDoctorId(user.getUserId());
		if (type == 1) {// 最新（最近一周）
			// vo.setCreateTimeFrom(DateUtil.getPreviousNDate(new Date(),
			// 7));//大于
			vo.setCaseDateFrom(DateUtil.getPreviousNDate(new Date(), 7));
		}
		count = cpfCaseHistoryDAO.countFreeFind(vo);

		return count;
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
	public Integer subtotalOfHospital(Integer patientId, Date newest) {
		List<CpfCaseHistory> list = cpfCaseHistoryDAO.getDistinctHospitalCount(patientId, newest);
		Integer count = 0;
		if (null != list && list.size() > 0) {
			count = list.size();
		}
		return count;
	}

	/**
	 * 通过病历夹id查询病历资料
	 */
	public List<CpfCaseHistory> freeFindLsit(CpfCaseHistory vo) {
		List<CpfCaseHistory> list = cpfCaseHistoryDAO.freeFind(vo);
		if (null != list && list.size() != 0) {
			return list;
		}
		return null;
	}

	/**
	 * 医生权限下，得到病人最近一周的病例资料
	 */
	public PageModel freeFindInWeek(List<CpfCaseHistory> list) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = list.size();
		pm.setTotal(total);
		pm.setDatas(list);
		return pm;
	}

	@Override
	public int subtotalByDoctor(SysDoctor doctor, String type) {
		List<CpfCaseHistory> caseHistorys = new ArrayList<CpfCaseHistory>();// 病例集合
		// 1获取授权的病例
		// 1.1获取授权的病例夹
		CpfCaseFolderAuthorization cpfCaseFolderAuthorization = new CpfCaseFolderAuthorization();
		cpfCaseFolderAuthorization.setDoctorEq(doctor.getId().toString());
		String folderIds = cpfCaseFolderAuthorizationService.conditionFindIds(cpfCaseFolderAuthorization);
		if (folderIds != null) {
			// 1.2获得病例夹信息
			CpfCaseFolder cpfCaseFolder = new CpfCaseFolder();
			cpfCaseFolder.setStatus(Enums.caseFolderStatus.NORMAL);// 查看没有删除的
			cpfCaseFolder.setId_instr(folderIds);
			Map folderMap = cpfCaseFolderService.findFolder(cpfCaseFolder);
			String folderId = (String) folderMap.get("folderIds");
			if (folderId != null) {
				CpfCaseHistory caseHistory = new CpfCaseHistory();
				caseHistory.setFolderId_instr(folderId);
				caseHistory.setCaseType(type);
				caseHistorys = cpfCaseHistoryDAO.freeFind(caseHistory);
			}
		}
		// 2获取分享病例的集合
		List<CpfShare> shares = new ArrayList<CpfShare>();// 分享病例的集合
		// 2.1获取关联的分享
		// 2.1.1获取收藏分享信息
		CpfSharing sharing = new CpfSharing();
		sharing.setDoctorId(doctor.getId());
		List<CpfSharing> sharings = sharingDAO.freeFind(sharing);
		if (sharings != null && sharings.size() > 0) {
			List<Integer> id = new ArrayList<Integer>();
			for (CpfSharing s : sharings) {
				id.add(s.getShareId());
			}
			// 2.1.1查询分享信息
			shares = cpfShareDAO.searchByIdStr(StringUtils.convertInInt(id), null);
		}

		// 2.2模糊匹配分享信息
		if (doctor.getEmail() != null || doctor.getMobile() != null) {
			if (shares != null) {
				shares.addAll(cpfShareDAO.searchByEmailOrMobile(doctor.getEmail(), doctor.getMobile(), null));
			} else {
				shares = cpfShareDAO.searchByEmailOrMobile(doctor.getEmail(), doctor.getMobile(), null);
			}

		}
		if (shares != null && shares.size() > 0) {
			List<Integer> id = new ArrayList<Integer>();
			for (CpfShare s : shares) {
				id.add(s.getCaseHistoryId());
			}
			CpfCaseHistory caseHistory = new CpfCaseHistory();
			caseHistory.setId_instr(StringUtils.convertInInt(id));
			caseHistory.setCaseType(type);
			if (caseHistorys != null) {
				caseHistorys.addAll(cpfCaseHistoryDAO.freeFind(caseHistory));
			} else {
				caseHistorys = cpfCaseHistoryDAO.freeFind(caseHistory);
			}

		}

		Set<Integer> set = new HashSet<Integer>();

		for (CpfCaseHistory c : caseHistorys) {
			set.add(c.getId());
		}

		return set.size();
	}

	/**
	 * 得到医生被授权的病历资料
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<CpfCaseHistory> subtotalByAuthori(SysUser user) {
		SysDoctor sysDoctor = new SysDoctor();
		// 授权期限map
		Map experiedMap = Enums.getExperiedMap();

		// 通过医生id获得授权病历夹id
		CpfCaseFolderAuthorization cpfCaseFolderAuthorization = new CpfCaseFolderAuthorization();

		String userStr = "";
		if (user != null) {
			sysDoctor.setUserId(user.getUserId());
			List<SysDoctor> list = sysDoctorDAO.freeFind(sysDoctor);// 获得医生信息
			if (null != list && list.size() > 0) {
				sysDoctor = list.get(0);
			}
			if (user.getUserType().equals(Enums.UserType.DOCTOR)) {// 普通医生
				cpfCaseFolderAuthorization.setDoctorEq(sysDoctor.getId().toString());// doctor id 授权表中doctor
			} else if (user.getUserType().equals(Enums.UserType.SUPER_DOCTOR)) {// 超级医生
				cpfCaseFolderAuthorization.setDoctorEq(null);
				cpfCaseFolderAuthorization.setHospitalId(sysDoctor.getHospitalId());
			}
		}

		// 属于该doctor的所有授权
		List<CpfCaseFolderAuthorization> authorizationList = cpfCaseFolderAuthorizationDAO.freeFind(cpfCaseFolderAuthorization);

		// 得到该医生在期限内的授权
		List<CpfCaseFolderAuthorization> authorList = new ArrayList<CpfCaseFolderAuthorization>();

		// List<Integer> departIdList = new ArrayList<Integer>();
		if (null != authorizationList && authorizationList.size() != 0) {
			for (int i = 0; i < authorizationList.size(); i++) {
				CpfCaseFolderAuthorization cpfAuthorization = authorizationList.get(i);
				/*
				 * if (null==cpfAuthorization) { continue; }
				 */
				// 用于判断该病历夹是否已被删除
				CpfCaseFolder cpFolder = new CpfCaseFolder();
				cpFolder.setId(cpfAuthorization.getFolderId());
				cpFolder = cpfCaseFolderDAO.findByPK(cpFolder);
				if (null != cpfAuthorization.getExperiod() && !cpfAuthorization.getExperiod().equals("")) {
					Date newDate = new Date();
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTime(cpfAuthorization.getCreateTime());
					cal.add(Calendar.MONTH, Integer.parseInt(experiedMap.get(cpfAuthorization.getExperiod()).toString())); // 到期时间
					Date date = cal.getTime();

					if (date.getTime() > newDate.getTime() && cpFolder.getStatus().equals(Enums.caseFolderStatus.NORMAL)) { // 权限日期大于当前日期
						authorList.add(cpfAuthorization);
					}
				} /*
				 * else { authorList.add(cpfAuthorization); }
				 */
			}
		}

		// System.out.println("Authori 方法中的authorList size "+authorList.size());

		// 通过病历夹id去查病历资料id
		List<CpfCaseHistory> caseHistoryList = new ArrayList<CpfCaseHistory>();
		if (null != authorList && authorList.size() != 0) {
			for (CpfCaseFolderAuthorization ccfa : authorList) {
				if (null == ccfa) {
					continue;
				}
				CpfCaseHistory caseHistory = new CpfCaseHistory();
				caseHistory.setFolderId(ccfa.getFolderId());
				caseHistory.setViewStatus(Enums.caseFolderStatus.NORMAL);

				// 查询病历资料表时，还是要限制医生id的
				if (user.getUserType().equals(Enums.UserType.DOCTOR)) {// 普通医生
					caseHistory.setDoctorId(sysDoctor.getId());// doctor id
																// 授权表中doctor
				} else if (user.getUserType().equals(Enums.UserType.SUPER_DOCTOR)) {// 超级医生
					caseHistory.setDoctorId(null);
					caseHistory.setHospitalId(sysDoctor.getHospitalId());
				}

				List<CpfCaseHistory> listHis = cpfCaseHistoryDAO.freeFind(caseHistory);
				if (null != listHis) {
					for (CpfCaseHistory cpfCaseHistory : listHis) {
						caseHistoryList.add(cpfCaseHistory);
					}
				}

			}
		}

		// System.out.println("Authori 方法中的caseHistoryList size "+caseHistoryList.size());

		return caseHistoryList;
	}

	/**
	 * 得到医生被授权和被分享的病历资料
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<CpfCaseHistory> subtotalByAuthoriAndShare(SysUser user) {
		// 调用 得到医生被授权的病历资料 方法
		List<CpfCaseHistory> caseHistoryList = this.subtotalByAuthori(user);

		Integer doctorId = 0;
		Integer hospitalId = 0;
		// 得到登陆医生
		SysDoctor sysDoctor = new SysDoctor();
		List<SysDoctor> sysDoctorList = sysDoctorDAO.freeFind(sysDoctor);
		if (null != sysDoctorList && sysDoctorList.size() > 0) {
			sysDoctor = sysDoctorList.get(0);
			if (null != sysDoctor) {
				doctorId = sysDoctor.getId();
				hospitalId = sysDoctor.getHospitalId();
			}
		}
		// 属于该医生的分享
		CpfShare share = new CpfShare();
		List<CpfShare> list = new ArrayList<CpfShare>();

		if (user.getUserType().equals(Enums.UserType.DOCTOR)) {// 普通医生
			share.setEmail(sysDoctor.getEmail());
			share.setMobile(sysDoctor.getMobile());
			list = cpfShareDAO.freeFind(share);
		} else if (user.getUserType().equals(Enums.UserType.SUPER_DOCTOR)) {// 超级医生
			// 通过登陆的者的信息，查询出该医院所有医生的email和mobile
			SysDoctor superDoctor = new SysDoctor();
			List<CpfShare> tempList = new ArrayList<CpfShare>();

			superDoctor.setHospitalId(hospitalId);

			List<SysDoctor> doctorListOfHospital = sysDoctorDAO.freeFind(sysDoctor);
			for (SysDoctor sysDoctor2 : doctorListOfHospital) {
				share.setEmail(sysDoctor2.getEmail());
				share.setMobile(sysDoctor2.getMobile());
				tempList = cpfShareDAO.freeFind(share);

				if (null != tempList && tempList.size() > 0) {
					for (CpfShare cpfShare : tempList) {
						list.add(cpfShare);
					}
				}
			}
		}

		// System.out.println("AuthoriAndShare 方法中第一次的caseHistoryList size "+caseHistoryList.size());

		// 该医生分享中的病历资料 集合
		// List<CpfCaseHistory> caseHistoryList=new ArrayList<CpfCaseHistory>();
		if (null != list && list.size() > 0) {
			for (CpfShare cpfShare : list) {
				CpfCaseHistory vo = new CpfCaseHistory();
				vo.setId(cpfShare.getCaseHistoryId());
				vo = cpfCaseHistoryDAO.findByPK(vo);
				if (null != vo) {
					if (vo.getId() != cpfShare.getCaseHistoryId()) {
						caseHistoryList.add(vo);
					}
				}
			}
		}

		return caseHistoryList;
	}

	@Override
	public Map<String, List<CpfCaseHistory>> imports(MultipartFile file, SysUser user) {
		List<CpfCaseHistory> success = new ArrayList<CpfCaseHistory>();
		List<CpfCaseHistory> failure = new ArrayList<CpfCaseHistory>();
		if (file != null && file.getSize() > 0) {

			List<SysHospital> hList = hospitalDAO.freeFind(new SysHospital());// 获取医院信息
			Map hMap = new HashMap();
			for (SysHospital sysHospital : hList) {
				Map dMap = new HashMap();
				SysDepartment department = new SysDepartment();
				department.setHospitalId(sysHospital.getId());
				List<SysDepartment> dList = departmentDAO.freeFind(department);// 获得科室信息
				for (SysDepartment sysDepartment : dList) {
					Map map = new HashMap();
					dMap.put(sysDepartment.getDepartmentName(), sysDepartment.getId());
					SysDoctor doctor = new SysDoctor();
					doctor.setDepartmentId(sysDepartment.getId());
					List<SysDoctor> list = sysDoctorDAO.freeFind(doctor);// 获得医生信息
					for (SysDoctor sysDoctor : list) {
						map.put(sysDoctor.getFullname(), sysDoctor.getId());
					}
					dMap.put(sysDepartment.getId(), map);
				}
				hMap.put(sysHospital.getName(), sysHospital.getId());
				hMap.put(sysHospital.getId(), dMap);
			}

			try {

				List<List<String>> list = Excel.getData(file, 1);

				for (List<String> list2 : list) {
					Map<String, CpfCaseHistory> m = dataProcessing(list2, hMap, user);
					if (m.get("success") != null) {
						success.add(m.get("success"));
					} else if (m.get("failure") != null) {
						failure.add(m.get("failure"));
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, List<CpfCaseHistory>> m = new HashMap<String, List<CpfCaseHistory>>();
		m.put("success", success);
		m.put("failure", failure);
		return m;
	}

	public Map<String, CpfCaseHistory> dataProcessing(List<String> list, Map hMap, SysUser user) {

		Map<String, CpfCaseHistory> m = new HashMap<String, CpfCaseHistory>();

		CpfCaseHistory caseHistory = new CpfCaseHistory();
		Object hospitalId = hMap.get(list.get(0));// 获得医院信息id
		if (hospitalId != null) {// 如果存在医院信息id
			caseHistory.setHospitalId((Integer) hospitalId);// 得到医院id
			Map dMap = (Map) hMap.get(caseHistory.getHospitalId());// 获取医院的科室
			if (dMap != null) {// 如果存在科室
				Object departmentId = dMap.get(list.get(1));// 获取科室id
				if (departmentId != null) {// 如果科室尊重
					caseHistory.setDepartmentId((Integer) departmentId);// 得到科室id
					Map map = (Map) dMap.get(caseHistory.getDepartmentId());// 获得医生
					if (map != null) {
						Object doctorId = map.get(list.get(2));
						if (doctorId != null) {
							caseHistory.setDoctorId((Integer) doctorId);
						}
					}
				}
			}

		}

		caseHistory.setTempId(list.get(3));// 临时病例id
		caseHistory.setViewPassword(list.get(4));// 查看密码
		caseHistory.setIcId(list.get(5));// icid
		String caseDate = list.get(6);
		if (caseDate != null) {
			caseHistory.setCaseDate(DateUtil.parseDate(caseDate, "yyyy-MM-dd"));// 就诊时间
		}

		String type = list.get(7).trim();
		Object caseType = Enums.getCaseTypeMapKey().get(type);
		if (caseType != null) {

			caseHistory.setCaseType(caseType.toString());// 资料类型
		}
		caseHistory.setCaseName(list.get(8));// 资料名
		caseHistory.setCatgory(list.get(9));// 就诊类别
		caseHistory.setItem(list.get(10));// 检查项目
		caseHistory.setTechnician(list.get(11));// 执行技师
		caseHistory.setDescription(list.get(12));// 临床诊断
		caseHistory.setHospitalNo(list.get(13));// 住院号
		caseHistory.setBedNo(list.get(14));// 床号
		caseHistory.setPatientCatgory(list.get(15));// 病人性质
		caseHistory.setPicUrl(list.get(16));// 胶片
		caseHistory.setApplyUrl(list.get(17));// 申请单
		caseHistory.setReportUrl(list.get(18));// 诊断报告
		caseHistory.setCheckFormUrl(list.get(19));// 化验单
		caseHistory.setOthers(list.get(20));// 其他
		caseHistory.setCaseDesc(list.get(21));// 备注

		Object source = Enums.getRelationMapKey().get(list.get(22));
		if (source != null) {
			caseHistory.setSource(source.toString());// 数据来源
		}
		caseHistory.setStudy(list.get(23));
		caseHistory.setPid(list.get(24));
		caseHistory.setPn(list.get(25));
		caseHistory.setCreateTime(new Date());
		caseHistory.setCreator(user.getUserName());

		caseHistory.setViewStatus(Enums.isYesOrIsNo.IS_YES);
		caseHistory.setRelateStatus(Enums.isYesOrIsNo.IS_NO);

		if (caseHistory.getCaseType() != null && caseHistory.getCaseName() != null && caseHistory.getSource() != null && caseHistory.getStudy() != null && caseHistory.getPid() != null) {
			try {

				CpfPatient patient = patientService.find(caseHistory.getIcId(), null);
				if (patient == null) {
					patient = new CpfPatient();
					String password = caseHistory.getIcId().substring(caseHistory.getIcId().length() - 6);
					String pwd = Encrypt.md5(password);
					patient.setUserName(caseHistory.getIcId());
					patient.setPassword(pwd);
					patient.setHospitalId(caseHistory.getHospitalId());
					patient.setCreateTime(new Date());
					patientService.insert(patient);
					CpfPatientRelationUser u = new CpfPatientRelationUser();
					u.setPatientId(patient.getId());
					u.setRelationPatientId(patient.getId());
					pruService.insert(u);

					caseHistory.setPatientId(patient.getId());
				} else {
					caseHistory.setPatientId(patient.getId());
				}

				cpfCaseHistoryDAO.save(caseHistory);
				m.put("success", caseHistory);
			} catch (Exception e) {
				e.printStackTrace();
				m.put("failure", caseHistory);
			}
		} else {
			m.put("failure", caseHistory);
		}

		return m;
	}

	@Override
	public void updateRelateStatus(String relateStatus, Integer patientId, Integer folderId, String idStr) {
		cpfCaseHistoryDAO.updateRelateStatus(relateStatus, patientId, folderId, idStr);

	}

	/**
	 * 分类汇总 报告 胶片 申请单 化验单
	 * 
	 * @param patientId
	 *            病人id
	 * @param patientIds
	 *            病人id数组
	 * @param type
	 *            F胶片 R诊断书 D申请单 T化验单
	 * @param viewStatu
	 * @return
	 */
	private Integer subtotalCount(Integer patientId, String patientIds, String type, String viewStatu) {
		CpfCaseHistory vo = new CpfCaseHistory();
		if (patientIds != null) {// 微医通方式登录
			vo.setPatientId_instr(patientIds);
		} else {
			vo.setPatientId(patientId);// 医院
		}
		// 最新 是否查看
		if (Enums.isYesOrIsNo.IS_NO.equals(viewStatu)) {// 未读
			vo.setViewStatus(Enums.isYesOrIsNo.IS_NO);
		} else {
			vo.setViewStatus(null);
		}
		// 判断是汇总类型
		if (type.equals(Enums.CaseType.FILM)) {// 胶片
			vo.setCaseType(Enums.CaseType.FILM);
		} else if (type.equals(Enums.CaseType.REQUISITION)) {// 诊断书
			vo.setCaseType(Enums.CaseType.REQUISITION);
		} else if (type.equals(Enums.CaseType.DIAGNOSIS)) {// 申请单
			vo.setCaseType(Enums.CaseType.DIAGNOSIS);
		} else if (type.equals(Enums.CaseType.TEST_REPORT)) {// 化验单
			vo.setCaseType(Enums.CaseType.TEST_REPORT);
		}
		vo.setRelateStatus(Enums.isYesOrIsNo.IS_YES);
		return cpfCaseHistoryDAO.countFreeFind(vo);

	}

	@Override
	public Integer subtotalByCommand(Integer patientId, String type, String viewStatu) {
		return subtotalCount(patientId, null, type, viewStatu);
	}

	@Override
	public Integer subtotalByCommand(String patientIds, String type, String viewStatu) {
		return subtotalCount(null, patientIds, type, viewStatu);
	}

	@Override
	public int count(CpfCaseHistory vo) {
		return cpfCaseHistoryDAO.countFreeFind(vo);
	}

	@Override
	public boolean count(String studyID, String patientID) {
		CpfCaseHistory vo = new CpfCaseHistory();
		vo.setStudyEq(studyID);
		vo.setPidEq(patientID);
		if (count(vo) > 0) {
			return false;
		}
		return true;
	}

	@Override
	public CpfCaseHistory find(String studyID, String patientID, String patientId, String is_Yes) {
		CpfCaseHistory vo = new CpfCaseHistory();
		if (is_Yes != null) {
			vo.setRelateStatus(is_Yes);
		}
		vo.setPatientId_instr(patientId);
		vo.setStudyEq(studyID);
		vo.setPidEq(patientID);
		List<CpfCaseHistory> list = cpfCaseHistoryDAO.freeFind(vo);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public String subtotalByid(SysDoctor doctor, String type) {

		List<CpfCaseHistory> caseHistorys = new ArrayList<CpfCaseHistory>();// 病例集合
		// 1获取授权的病例
		// 1.1获取授权的病例夹
		CpfCaseFolderAuthorization cpfCaseFolderAuthorization = new CpfCaseFolderAuthorization();
		cpfCaseFolderAuthorization.setDoctorEq(doctor.getId().toString());
		String folderIds = cpfCaseFolderAuthorizationService.conditionFindIds(cpfCaseFolderAuthorization);
		if (folderIds != null) {
			// 1.2获得病例夹信息
			CpfCaseFolder cpfCaseFolder = new CpfCaseFolder();
			cpfCaseFolder.setStatus(Enums.caseFolderStatus.NORMAL);// 查看没有删除的
			cpfCaseFolder.setId_instr(folderIds);
			Map folderMap = cpfCaseFolderService.findFolder(cpfCaseFolder);
			String folderId = (String) folderMap.get("folderIds");
			if (folderId != null) {
				CpfCaseHistory caseHistory = new CpfCaseHistory();
				caseHistory.setFolderId_instr(folderId);
				caseHistory.setCaseType(type);
				caseHistorys = cpfCaseHistoryDAO.freeFind(caseHistory);
			}
		}
		// 2获取分享病例的集合
		List<CpfShare> shares = new ArrayList<CpfShare>();// 分享病例的集合
		// 2.1获取关联的分享
		// 2.1.1获取收藏分享信息
		CpfSharing sharing = new CpfSharing();
		sharing.setDoctorId(doctor.getId());
		List<CpfSharing> sharings = sharingDAO.freeFind(sharing);
		if (sharings != null && sharings.size() > 0) {
			List<Integer> id = new ArrayList<Integer>();
			for (CpfSharing s : sharings) {
				id.add(s.getShareId());
			}
			// 2.1.1查询分享信息
			shares = cpfShareDAO.searchByIdStr(StringUtils.convertInInt(id), null);
		}

		// 2.2模糊匹配分享信息
		if (doctor.getEmail() != null || doctor.getMobile() != null) {
			if (shares != null) {
				shares.addAll(cpfShareDAO.searchByEmailOrMobile(doctor.getEmail(), doctor.getMobile(), null));
			} else {
				shares = cpfShareDAO.searchByEmailOrMobile(doctor.getEmail(), doctor.getMobile(), null);
			}

		}
		if (shares != null && shares.size() > 0) {
			List<Integer> id = new ArrayList<Integer>();
			for (CpfShare s : shares) {
				id.add(s.getCaseHistoryId());
			}
			CpfCaseHistory caseHistory = new CpfCaseHistory();
			caseHistory.setId_instr(StringUtils.convertInInt(id));
			caseHistory.setCaseType(type);
			if (caseHistorys != null) {
				caseHistorys.addAll(cpfCaseHistoryDAO.freeFind(caseHistory));
			} else {
				caseHistorys = cpfCaseHistoryDAO.freeFind(caseHistory);
			}

		}

		List<Integer> set = new ArrayList<Integer>();

		for (CpfCaseHistory c : caseHistorys) {
			set.add(c.getId());
		}
		return StringUtils.convertInInt(set);
	}
}
