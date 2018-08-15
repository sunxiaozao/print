package com.lubian.cpf.service.cpf;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfCaseFolderAuthorizationDAO;
import com.lubian.cpf.dao.SysDoctorDAO;
import com.lubian.cpf.vo.CpfCaseFolderAuthorization;
import com.lubian.cpf.vo.CpfCaseHistoryApply;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfPatientRelationUser;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysUser;

@Service
public class CpfCaseFolderAuthorizationServiceImpl implements
		CpfCaseFolderAuthorizationService {
	@Autowired
	private CpfCaseFolderAuthorizationDAO cpfCaseFolderAuthorizationDAO;

	@Autowired
	private SysDoctorDAO sysDoctorDAO;

	public CpfCaseFolderAuthorization findByPK(CpfCaseFolderAuthorization vo) {
		return cpfCaseFolderAuthorizationDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfCaseFolderAuthorization vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfCaseFolderAuthorizationDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfCaseFolderAuthorization> list = cpfCaseFolderAuthorizationDAO
				.freeFind(vo, pageIndex, pageSize, "id desc");
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfCaseFolderAuthorization vo) {
		cpfCaseFolderAuthorizationDAO.insert(vo);
	}

	public void update(CpfCaseFolderAuthorization vo) {
		cpfCaseFolderAuthorizationDAO.update(vo);
	}

	public void delete(CpfCaseFolderAuthorization vo) {
		cpfCaseFolderAuthorizationDAO.remove(vo);
	}

	/**
	 * 病历夹统计
	 * 
	 * @param user
	 *            用户
	 * @param type
	 *            统计类型
	 * @return
	 */
	@Override
	public Integer subtotal(SysUser user, Integer type) {
		CpfCaseFolderAuthorization vo = new CpfCaseFolderAuthorization();
		Integer count = 0;
		if (user != null && user.getUserType().equals(Enums.UserType.MEMBER)) {
			vo.setPatientId(user.getUserId());
			if (type == 1) {// 最新（最近一周）
				vo.setCreateTimeFrom(DateUtil.getPreviousNDate(new Date(), 7));
			}
			count = cpfCaseFolderAuthorizationDAO.countFreeFind(vo);
		}
		return count;
	}

	/**
	 * 通过某个字段条件的到对象
	 * 
	 * @param vo
	 * @return
	 */
	@Override
	public CpfCaseFolderAuthorization getCaseFolderAuthor(
			CpfCaseFolderAuthorization vo) {
		CpfCaseFolderAuthorization caseFolderAuthor = new CpfCaseFolderAuthorization();
		List<CpfCaseFolderAuthorization> list = cpfCaseFolderAuthorizationDAO
				.freeFind(vo);
		if (null != list && list.size() != 0) {
			caseFolderAuthor = list.get(0);
			return caseFolderAuthor;
		}
		return null;
	}

	/**
	 * 通过医院字段条件得到授权科室map记录
	 * 
	 * @param vo
	 * @return
	 */
	public Map<Integer, Integer> getCaseFolderAuthorMap(
			CpfCaseFolderAuthorization vo) {
		Map map = new LinkedHashMap();
		List<CpfCaseFolderAuthorization> list = cpfCaseFolderAuthorizationDAO
				.freeFind(vo);
		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				CpfCaseFolderAuthorization cpfFolderAuthorization = new CpfCaseFolderAuthorization();
				cpfFolderAuthorization = list.get(i);
				map.put(cpfFolderAuthorization.getDepartmentId(),
						cpfFolderAuthorization.getHospitalId());
			}
			return map;
		}
		return null;
	}

	/**
	 * 通过科室字段条件得到医生map授权记录
	 * 
	 * @param vo
	 * @return
	 */
	public Map<Integer, Integer> getImpowerDoctorMap(
			CpfCaseFolderAuthorization vo) {
		Map map = new LinkedHashMap();
		List<CpfCaseFolderAuthorization> list = cpfCaseFolderAuthorizationDAO
				.freeFind(vo);
		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				CpfCaseFolderAuthorization cpfFolderAuthorization = new CpfCaseFolderAuthorization();
				cpfFolderAuthorization = list.get(i);
				map.put(Integer.parseInt(cpfFolderAuthorization.getDoctor()),
						cpfFolderAuthorization.getDepartmentId());
			}
			return map;
		}
		return null;
	}

	/**
	 * 通过某个字段条件的到list对象
	 * 
	 * @param vo
	 * @return
	 */
	public List<CpfCaseFolderAuthorization> getCaseFolderAuthorList(
			CpfCaseFolderAuthorization vo) {
		List<CpfCaseFolderAuthorization> list = cpfCaseFolderAuthorizationDAO
				.freeFind(vo);
		if (null != list && list.size() != 0) {
			return list;
		}
		return null;
	}

	@Override
	public String getIds(CpfCaseFolderAuthorization vo) {

		StringBuffer sBuffer = new StringBuffer();
		List<CpfCaseFolderAuthorization> list = cpfCaseFolderAuthorizationDAO
				.freeFind(vo);

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1) {
					sBuffer.append(list.get(i).getFolderId());
				} else {
					sBuffer.append(list.get(i).getFolderId() + ",");
				}
			}
			return sBuffer.toString();
		}

		return null;
	}

	@Override
	public String conditionFind(Object folderId) {
		String sql = "PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(create_time,'%Y%m')) >=0 and PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(create_time,'%Y%m')) <=experiod";

		List<CpfCaseFolderAuthorization> list = cpfCaseFolderAuthorizationDAO
				.conditionFind(folderId, null, null, null, null, sql);
		if (list != null && list.size() > 0) {

			List<Integer> idList = new ArrayList<Integer>();
			for (CpfCaseFolderAuthorization cp : list) {
				if (cp.getDoctor() != null && !"".equals(cp.getDoctor())) {
					idList.add(Integer.parseInt(cp.getDoctor()));
				}
			}
			return StringUtils.convertInInt(idList);
		}
		return null;

	}

	@Override
	public PageModel conditionFind(CpfCaseFolderAuthorization vo) {

		String sql = " PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(create_time,'%Y%m')) >=0 and PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(create_time,'%Y%m')) <=experiod";

		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfCaseFolderAuthorizationDAO.countConditionFind(
				vo.getFolderId(), vo.getHospitalId(), vo.getDepartmentId(),
				vo.getDoctor(), vo.getPatientId_instr(), sql);
		pm.setTotal(total);
		List<CpfCaseFolderAuthorization> list = cpfCaseFolderAuthorizationDAO
				.conditionFind(vo.getFolderId(), vo.getHospitalId(),
						vo.getDepartmentId(), vo.getDoctor(),
						vo.getPatientId_instr(), sql, pageIndex, pageSize);
		pm.setDatas(list);
		return pm;

	}

	@Override
	public String conditionFindIds(CpfCaseFolderAuthorization vo) {
		String sql = "PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(create_time,'%Y%m')) >=0 and PERIOD_DIFF(date_format(now(),'%Y%m'),date_format(create_time,'%Y%m'))<=experiod";
		List<CpfCaseFolderAuthorization> list = cpfCaseFolderAuthorizationDAO
				.conditionFind(vo.getFolderId(), vo.getHospitalId(),
						vo.getDepartmentId(), vo.getDoctor(),
						vo.getPatientId_instr(), sql);
		if (list != null && list.size() > 0) {

			List<Integer> idList = new ArrayList<Integer>();
			for (CpfCaseFolderAuthorization cp : list) {
				if (cp.getDoctor() != null && !"".equals(cp.getDoctor())) {
					idList.add(cp.getFolderId());
				}
			}
			return StringUtils.convertInInt(idList);
		}
		return null;
	}
}
