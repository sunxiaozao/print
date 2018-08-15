package com.lubian.cpf.service.cpf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfPatientDAO;
import com.lubian.cpf.dao.CpfShareDAO;
import com.lubian.cpf.dao.CpfSharingDAO;
import com.lubian.cpf.dao.SysDoctorDAO;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfCaseHistoryApply;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfShare;
import com.lubian.cpf.vo.CpfSharing;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysUser;

@Service
public class CpfShareServiceImpl implements CpfShareService {
	@Autowired
	private CpfShareDAO cpfShareDAO;

	@Autowired
	private SysDoctorDAO sysDoctorDAO;

	@Autowired
	private SysDoctorService sysDoctorService; // 医生表
	@Autowired
	private CpfSharingDAO sharingDAO;

	public CpfShare findByPK(CpfShare vo) {
		return cpfShareDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfShare vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfShareDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfShare> list = cpfShareDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfShare vo) {
		cpfShareDAO.insert(vo);
	}

	public void update(CpfShare vo) {
		cpfShareDAO.update(vo);
	}

	public void delete(CpfShare vo) {
		cpfShareDAO.remove(vo);
	}

	/**
	 * 分享统计
	 * 
	 * @param user
	 *            用户
	 * @param type
	 *            统计类型
	 * @return
	 */
	@Override
	public Integer subtotal(Integer userId, String userType, String viewStatu) {
		CpfShare vo = new CpfShare();
		Integer count = 0;
		// 最新 是否查看
		if (viewStatu.equals(Enums.isYesOrIsNo.IS_NO)) {// 未读
			vo.setStatus(Enums.isYesOrIsNo.IS_NO);
		} else if (viewStatu.equals(Enums.isYesOrIsNo.IS_YES)) {// 总共
			vo.setStatus(null);
		}

		if (userType.equals(Enums.UserType.MEMBER)) {
			vo.setPatientId(userId);
		} else {
			SysDoctor sysDoctor = new SysDoctor();
			sysDoctor.setId(userId);
			sysDoctor = sysDoctorService.findByPK(sysDoctor);

			if (null != sysDoctor) {
				if (userType.equals(Enums.UserType.DOCTOR)) {// 普通医生{
					String email = sysDoctor.getEmail();
					String mobile = sysDoctor.getMobile();

					return cpfShareDAO.countSearchShareByEmailMobile(email, mobile, vo.getStatus());

				} else if (userType.equals(Enums.UserType.SUPER_DOCTOR)) {// 超级医生
					// 登入者为超级医生，置空查询条件
					vo.setPatientId(null);
					Integer hospitalId = sysDoctor.getHospitalId();
					return this.countByHospital(vo, hospitalId);
				}
			}
		}
		count = cpfShareDAO.countFreeFind(vo);
		return count;
	}

	/**
	 * 根据条件查询分享记录
	 * 
	 * @param vo
	 * @return
	 */
	@Override
	public CpfShare findByCommand(CpfShare vo) {
		List<CpfShare> list = cpfShareDAO.freeFind(vo);
		if (null != list && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据条件查询分享记录
	 * 
	 * @param vo
	 * @return list
	 */
	@Override
	public List<CpfShare> listFreeFind(CpfShare vo) {
		return cpfShareDAO.freeFind(vo);
	}

	/**
	 * 根据条件统计分享数量
	 * 
	 * @param vo
	 * @return int
	 */
	@Override
	public Integer freeCount(CpfShare vo) {
		return cpfShareDAO.countFreeFind(vo);
	}

	/**
	 * 根据条件升序查询分享记录
	 * 
	 * @param vo
	 * @return 第一条cpfShare
	 */
	@Override
	public CpfShare findByOrder(CpfShare vo) {
		List<CpfShare> list = cpfShareDAO.freeFind(vo, "create_time");
		if (null != list && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageModel subtotalOfPage(Integer userId, String userType, String viewStatu) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = 0;

		List<CpfShare> list = new ArrayList<CpfShare>();
		CpfShare vo = new CpfShare();
		// 最新 是否查看
		if (viewStatu.equals(Enums.isYesOrIsNo.IS_NO)) {// 未读
			vo.setStatus(Enums.isYesOrIsNo.IS_NO);
		} else if (viewStatu.equals(Enums.isYesOrIsNo.IS_YES)) {// 总共
			vo.setStatus(null);
		}
		if (userType.equals(Enums.UserType.MEMBER)) {
			vo.setPatientId(userId);
		} else {
			SysDoctor sysDoctor = sysDoctorService.findDoctorByUserId(userId);
			if (null != sysDoctor) {
				if (userType.equals(Enums.UserType.DOCTOR)) {// 普通医生{
					String email = sysDoctor.getEmail();
					String mobile = sysDoctor.getMobile();

					total = cpfShareDAO.countSearchShareByEmailMobile(email, mobile, vo.getStatus());
					pm.setTotal(total);
					list = cpfShareDAO.searchShareByEmailMobile(email, mobile, vo.getStatus(), pageIndex, pageSize);
					pm.setDatas(list);
					return pm;

				} else if (userType.equals(Enums.UserType.SUPER_DOCTOR)) {// 超级医生
					// 登入者为超级医生，置空查询条件
					vo.setPatientId(null);
					Integer hospitalId = sysDoctor.getHospitalId();
					total = this.countByHospital(vo, hospitalId);
					pm.setTotal(total);
					list = this.findByHospital(vo, hospitalId);
					pm.setDatas(list);
					return pm;
				}
			}
		}

		total = cpfShareDAO.countFreeFind(vo);
		pm.setTotal(total);
		list = cpfShareDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;
	}

	@Override
	public List<CpfShare> findByHospital(CpfShare vo, Integer hospitalId) {
		List<CpfShare> tempList = new ArrayList<CpfShare>();// 得到该医生的分享记录
		List<CpfShare> list = new ArrayList<CpfShare>();// 该医院的分享记录
		SysDoctor doctor = new SysDoctor();

		doctor.setHospitalId(hospitalId);

		List<SysDoctor> doctorListOfHospital = sysDoctorDAO.freeFind(doctor);
		for (SysDoctor sysDoctor2 : doctorListOfHospital) {
			vo.setEmail(sysDoctor2.getEmail());
			vo.setMobile(sysDoctor2.getMobile());
			tempList = cpfShareDAO.freeFind(vo);// 得到该医生的分享记录

			if (null != tempList && tempList.size() > 0) {
				for (CpfShare cpfShare : tempList) {// 取出该医生的分享记录
					list.add(cpfShare);// 将该医生的分享记录填充到该医院分享记录的list里
				}
			}
		}
		return list;
	}

	@Override
	public Integer countByHospital(CpfShare vo, Integer hospitalId) {
		Integer tempCount = 0;// 得到该医生的分享记录
		Integer count = 0;// 该医院的分享记录

		SysDoctor doctor = new SysDoctor();

		doctor.setHospitalId(hospitalId);

		List<SysDoctor> doctorListOfHospital = sysDoctorDAO.freeFind(doctor);
		for (SysDoctor sysDoctor2 : doctorListOfHospital) {
			vo.setEmail(sysDoctor2.getEmail());
			vo.setMobile(sysDoctor2.getMobile());
			tempCount = cpfShareDAO.countFreeFind(vo);// 得到该医生的分享记录条数
			count += tempCount;
		}
		return count;
	}

	@Override
	public Integer subtotalPatient(Integer patientId, String patientIds, String viewStatu) {
		CpfShare vo = new CpfShare();
		if (viewStatu.equals(Enums.isYesOrIsNo.IS_NO)) {// 未读
			vo.setStatus(Enums.isYesOrIsNo.IS_NO);
		} else if (viewStatu.equals(Enums.isYesOrIsNo.IS_YES)) {// 总共
			vo.setStatus(null);
		}
		if (patientIds != null) {
			vo.setPatientId_instr(patientIds);
		} else {
			vo.setPatientId(patientId);
		}
		return cpfShareDAO.countFreeFind(vo);
	}

	@Override
	public boolean searchByIdStr(String ids) {
		return cpfShareDAO.countSearchByIdStr(ids, null) > 0 ? true : false;
	}

	@Override
	public void saveSharing(CpfSharing sharing) {
		sharingDAO.insert(sharing);

	}

	@Override
	public boolean findSharing(CpfSharing sharing) {

		return sharingDAO.countFreeFind(sharing) > 0 ? false : true;
	}

	@Override
	public Integer countShare(SysDoctor doctor, String viewStatu) {

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
			shares = cpfShareDAO.searchByIdStr(StringUtils.convertInInt(id), viewStatu);
		}

		// 2.2模糊匹配分享信息
		if (doctor.getEmail() != null || doctor.getMobile() != null) {
			if (shares != null) {
				shares.addAll(cpfShareDAO.searchByEmailOrMobile(doctor.getEmail(), doctor.getMobile(), viewStatu));
			} else {
				shares = cpfShareDAO.searchByEmailOrMobile(doctor.getEmail(), doctor.getMobile(), viewStatu);
			}

		}

		Set<Integer> set = new HashSet<Integer>();

		for (CpfShare c : shares) {
			set.add(c.getId());
		}

		return set.size();
	}

	@Override
	public PageModel findtShare(SysDoctor doctor, String viewStatu) {
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
			shares = cpfShareDAO.searchByIdStr(StringUtils.convertInInt(id), viewStatu);
		}

		// 2.2模糊匹配分享信息
		if (doctor.getEmail() != null || doctor.getMobile() != null) {
			if (shares != null) {
				shares.addAll(cpfShareDAO.searchByEmailOrMobile(doctor.getEmail(), doctor.getMobile(), viewStatu));
			} else {
				shares = cpfShareDAO.searchByEmailOrMobile(doctor.getEmail(), doctor.getMobile(), viewStatu);
			}

		}

		List<Integer> set = new ArrayList<Integer>();

		for (CpfShare c : shares) {
			set.add(c.getId());
		}

		String ids = StringUtils.convertInInt(set);

		if (ids != null && ids.length() > 0) {
			PageModel pm = new PageModel();
			int pageIndex = SystemContext.getOffset();
			int pageSize = SystemContext.getPagesize();
			int total = 0;
			CpfShare share = new CpfShare();
			share.setId_instr(ids);
			total = cpfShareDAO.countFreeFind(share);
			List<CpfShare> list = cpfShareDAO.freeFind(share, pageIndex, pageSize);
			pm.setTotal(total);
			pm.setDatas(list);
			return pm;
		}

		return null;
	}
}
