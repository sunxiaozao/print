package com.lubian.cpf.service.cpf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfCaseFolderDAO;
import com.lubian.cpf.dao.CpfCaseHistoryDAO;
import com.lubian.cpf.vo.CpfCaseFolder;
import com.lubian.cpf.vo.CpfCaseFolderAuthorization;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.SysUser;

@Service
public class CpfCaseFolderServiceImpl implements CpfCaseFolderService {
	@Autowired
	private CpfCaseFolderDAO cpfCaseFolderDAO;

	@Autowired
	private CpfCaseHistoryDAO cpfCaseHistoryDAO;

	public CpfCaseFolder findByPK(CpfCaseFolder vo) {
		return cpfCaseFolderDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfCaseFolder vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfCaseFolderDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfCaseFolder> list = cpfCaseFolderDAO.freeFind(vo, pageIndex,
				pageSize);
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfCaseFolder vo) {
		cpfCaseFolderDAO.insert(vo);
	}

	public void update(CpfCaseFolder vo) {
		cpfCaseFolderDAO.update(vo);
	}

	public void delete(CpfCaseFolder vo) {
		cpfCaseFolderDAO.remove(vo);
	}

	@Override
	public List<CpfCaseFolder> find(CpfCaseFolder vo) {
		return cpfCaseFolderDAO.freeFind(vo);

	}

	/**
	 * 通过某字段获取list集合
	 */
	public List<CpfCaseFolder> freeFindList(CpfCaseFolder vo) {
		List<CpfCaseFolder> list = cpfCaseFolderDAO.freeFind(vo);
		if (null != list && list.size() != 0) {
			return list;
		}
		return null;
	}

	/**
	 * 病历夹的map集合
	 */
	public Map<Integer, String> findFolderMap(CpfCaseFolder vo) {
		List<CpfCaseFolder> list = cpfCaseFolderDAO.freeFind(vo);
		Map map = new LinkedHashMap();
		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				CpfCaseFolder folder = list.get(i);
				map.put(folder.getId(), folder.getFolderName());
			}
			return map;
		} else {
			return null;
		}
	}

	@Override
	public List<CpfCaseFolder> subtotalOfFolder(Integer patientId,
			String viewStatu) {
		// 得到该病人的病历夹
		CpfCaseFolder ccf = new CpfCaseFolder();
		ccf.setPatientId(patientId);

		ccf.setStatus(Enums.isYesOrIsNo.IS_YES);
		List<CpfCaseFolder> folderList = cpfCaseFolderDAO.freeFind(ccf);
		List<Integer> idFolderList = new ArrayList<Integer>();
		for (CpfCaseFolder CpfCaseFolder : folderList) {
			idFolderList.add(CpfCaseFolder.getId());
		}

		if (viewStatu.equals(Enums.isYesOrIsNo.IS_NO)) {// 最新
			// 得到该病人的病历资料
			CpfCaseHistory vo = new CpfCaseHistory();
			vo.setPatientId(patientId);
			List<CpfCaseHistory> list = cpfCaseHistoryDAO.freeFind(vo);
			List<Integer> idList = new ArrayList<Integer>();
			for (CpfCaseHistory cpfCaseHistory : list) {
				idList.add(cpfCaseHistory.getFolderId());
			}
			// 最新病历夹（为空的病历夹，即该病历资料中没有病历夹的id）
			List<CpfCaseFolder> unFolderList = new ArrayList<CpfCaseFolder>();

			// 一般情况下，病历资料是多于病历夹的
			idFolderList = StringUtils.getDiffrent(idFolderList, idList);
			for (int i = 0; i < idFolderList.size(); i++) {
				for (CpfCaseFolder cpfCaseFolder : folderList) {
					if (cpfCaseFolder.getId().equals(idFolderList.get(i))) {
						unFolderList.add(cpfCaseFolder);
					}
				}
			}

			return unFolderList;
		} else {
			return folderList;
		}
	}

	@Override
	public Integer subtotalOfFolder(Integer patientId, String patientIds,
			String viewStatu) {
		// 得到该病人的病历夹
		CpfCaseFolder ccf = new CpfCaseFolder();
		if (patientIds != null) {
			ccf.setPatientId_instr(patientIds);
		} else {
			ccf.setPatientId(patientId);
		}
		ccf.setStatus(Enums.isYesOrIsNo.IS_YES);
		if (!Enums.isYesOrIsNo.IS_NO.equals(viewStatu)) {// 查询全部
			return cpfCaseFolderDAO.countFreeFind(ccf);
		} else {// 查询最新病历夹
			List<CpfCaseFolder> list = cpfCaseFolderDAO.freeFind(ccf);
			Integer count = 0;
			for (CpfCaseFolder cpfCaseFolder : list) {
				CpfCaseHistory caseHistory = new CpfCaseHistory();
				caseHistory.setFolderId(cpfCaseFolder.getId());
				Integer num = cpfCaseHistoryDAO.countFreeFind(caseHistory);
				if (num == 0) {// 如果病例资料为空则最新的病历夹数加一
					count++;
				}
			}
			return count;
		}

	}

	@Override
	public List<CpfCaseFolder> subtotalOfFolderList(Integer patientId,
			String patientIds, String viewStatu) {
		// 得到该病人的病历夹
				CpfCaseFolder ccf = new CpfCaseFolder();
				if (patientIds != null) {
					ccf.setPatientId_instr(patientIds);
				} else {
					ccf.setPatientId(patientId);
				}
				ccf.setStatus(Enums.isYesOrIsNo.IS_YES);
				if (!Enums.isYesOrIsNo.IS_NO.equals(viewStatu)) {// 查询全部
					return cpfCaseFolderDAO.freeFind(ccf);
				} else {// 查询最新病历夹
					List<CpfCaseFolder> list = cpfCaseFolderDAO.freeFind(ccf);
					List<CpfCaseFolder> newList=new ArrayList<CpfCaseFolder>();
					Integer count = 0;
					for (CpfCaseFolder cpfCaseFolder : list) {
						CpfCaseHistory caseHistory = new CpfCaseHistory();
						caseHistory.setFolderId(cpfCaseFolder.getId());
						Integer num = cpfCaseHistoryDAO.countFreeFind(caseHistory);
						if (num == 0) {// 如果病例资料为空则最新的病历夹数加一
							newList.add(cpfCaseFolder);
						}
					}
					return newList;
				}
	}

	@Override
	public Map findFolder(CpfCaseFolder vo) {

		List<CpfCaseFolder> list = cpfCaseFolderDAO.freeFind(vo);
		
		Map maps=new HashMap();
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> id = new ArrayList<Integer>();
		if (null != list && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				CpfCaseFolder folder = list.get(i);
				
				idList.add(folder.getId());
				id.add(folder.getPatientId());
			}
			
			maps.put("folderIds", StringUtils.convertInInt(idList));
			maps.put("patientIds", StringUtils.convertInInt(id));
			maps.put("list", list);
			return maps;
		} else {
			return null;
		}
		
		
		
		
		
		
	}

}
