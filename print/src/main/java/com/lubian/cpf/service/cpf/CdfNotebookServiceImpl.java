package com.lubian.cpf.service.cpf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CdfNotebookDAO;
import com.lubian.cpf.dao.CpfPatientDAO;
import com.lubian.cpf.dao.SysDoctorDAO;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.vo.CdfNotebook;

@Service
public class CdfNotebookServiceImpl implements CdfNotebookService {
	@Autowired
	private CdfNotebookDAO cdfNotebookDAO;

	@Autowired
	private SysDoctorDAO sysDoctorDAO;

	@Autowired
	private CpfPatientDAO cpfPatientDAO;

	@Autowired
	private SysDoctorService sysDoctorService;

	public CdfNotebook findByPK(CdfNotebook vo) {
		return cdfNotebookDAO.findByPK(vo);
	}

	public PageModel freeFind(CdfNotebook vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cdfNotebookDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CdfNotebook> list = cdfNotebookDAO.freeFind(vo, pageIndex,
				pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CdfNotebook vo) {
		cdfNotebookDAO.insert(vo);
	}

	public void update(CdfNotebook vo) {
		cdfNotebookDAO.update(vo);
	}

	public void delete(CdfNotebook vo) {
		cdfNotebookDAO.remove(vo);
	}

	/**
	 * 留言统计
	 * 
	 * @param user用户
	 * @param viewStatu
	 *            统计类型
	 * @return
	 */
	@Override
	// public List<CdfNotebook> subtotal(SysUser user,String viewStatu) {
	public Integer subtotal(Integer userId, String viewStatu, String userType) {
		CdfNotebook vo = new CdfNotebook();
		Integer count = 0;

		if (userType.equals(Enums.UserType.MEMBER)) {
			vo.setPatientId(userId);
		} else if (userType.equals(Enums.UserType.DOCTOR)) {
			vo.setDoctorId(userId);
		} else if (userType.equals(Enums.UserType.SUPER_DOCTOR)) {
			vo.setPatientId(null);
			String ids = sysDoctorService.doctorIdsByHospital(userId);
			vo.setDoctorId_instr(ids);
		}
		// 最新 是否查看
		if (viewStatu.equals(Enums.isYesOrIsNo.IS_NO)) {// 未读
			vo.setStatus(Enums.isYesOrIsNo.IS_NO);
		} else if (viewStatu.equals(Enums.isYesOrIsNo.IS_YES)) {// 总共
			vo.setStatus(null);
		}
		count = cdfNotebookDAO.countFreeFind(vo);
		return count;
	}

	@Override
	public Map find(CdfNotebook vo) {

		List<CdfNotebook> notebooks = cdfNotebookDAO
				.freeFind(vo, "create_time");

		if (notebooks != null && notebooks.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < notebooks.size(); i++) {
				if (i == notebooks.size() - 1) {
					buffer.append(notebooks.get(i).getId());
				} else {
					buffer.append(notebooks.get(i).getId() + ",");
				}
			}
			Map map = new HashMap();
			map.put("ids", buffer.toString());
			map.put("notebooks", notebooks);
			return map;
		}

		return null;
	}

	@Override
	public Map subtotalOfMap(CdfNotebook vo) {
		List<CdfNotebook> notebooks = cdfNotebookDAO
				.freeFind(vo, "create_time");

		if (notebooks != null && notebooks.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < notebooks.size(); i++) {
				if (i == notebooks.size() - 1) {
					buffer.append(notebooks.get(i).getId());
				} else {
					buffer.append(notebooks.get(i).getId() + ",");
				}
			}
			Map map = new HashMap();
			map.put("notebooks", notebooks);
			return map;
		}
		return null;
	}

	@Override
	public void updateByIds(String ids) {
		CdfNotebook updNotebook = new CdfNotebook();
		updNotebook.setId_instr(ids);
		List<CdfNotebook> updNotebookList = cdfNotebookDAO
				.freeFind(updNotebook);

		for (CdfNotebook cdfNotebook : updNotebookList) {
			cdfNotebook.setId(cdfNotebook.getId());
			cdfNotebook.setStatus(Enums.isYesOrIsNo.IS_YES);
			cdfNotebookDAO.update(cdfNotebook);
		}
	}

	@Override
	public Integer subtotalPatient(Integer patientId, String patientIds,
			String viewStatu) {

		CdfNotebook vo = new CdfNotebook();
		if (patientIds != null) {
			vo.setPatientId_instr(patientIds);
		} else {
			vo.setPatientId(patientId);
		}
		// 最新 是否查看
		if (viewStatu.equals(Enums.isYesOrIsNo.IS_NO)) {// 未读
			vo.setStatus(Enums.isYesOrIsNo.IS_NO);
		} else if (viewStatu.equals(Enums.isYesOrIsNo.IS_YES)) {// 总共
			vo.setStatus(null);
		}

		return cdfNotebookDAO.countFreeFind(vo);
	}

	@Override
	public Map find(Integer id, String ids, String viewStatu) {
		CdfNotebook notebook = new CdfNotebook();
		if (ids != null) {
			notebook.setPatientId_instr(ids);
		} else {
			notebook.setPatientId(id);
		}
		// 最新 是否查看
		if (viewStatu.equals(Enums.isYesOrIsNo.IS_NO)) {// 未读
			notebook.setStatus(Enums.isYesOrIsNo.IS_NO);
		} else if (viewStatu.equals(Enums.isYesOrIsNo.IS_YES)) {// 总共
			notebook.setStatus(null);
		}

		List<CdfNotebook> notebooks = cdfNotebookDAO.freeFind(notebook,
				"create_time");

		if (notebooks != null && notebooks.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < notebooks.size(); i++) {
				if (i == notebooks.size() - 1) {
					buffer.append(notebooks.get(i).getId());
				} else {
					buffer.append(notebooks.get(i).getId() + ",");
				}
			}
			Map map = new HashMap();
			map.put("ids", buffer.toString());
			map.put("notebooks", notebooks);
			return map;
		}

		return null;
	}
}
