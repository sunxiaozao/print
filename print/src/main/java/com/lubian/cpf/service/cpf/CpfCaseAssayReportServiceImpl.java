package com.lubian.cpf.service.cpf;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfCaseAssayReportDAO;
import com.lubian.cpf.vo.CpfCaseAssayReport;
import com.lubian.cpf.vo.CpfCaseHistoryReport;
import com.lubian.cpf.vo.SysUser;

@Service
public class CpfCaseAssayReportServiceImpl implements CpfCaseAssayReportService {
	@Autowired
	private CpfCaseAssayReportDAO cpfCaseAssayReportDAO;

	public CpfCaseAssayReport findByPK(CpfCaseAssayReport vo) {
		return cpfCaseAssayReportDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfCaseAssayReport vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfCaseAssayReportDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfCaseAssayReport> list = cpfCaseAssayReportDAO.freeFind(vo, pageIndex, pageSize,"case_date desc");
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfCaseAssayReport vo) {
		cpfCaseAssayReportDAO.insert(vo);
	}

	public void update(CpfCaseAssayReport vo) {
		cpfCaseAssayReportDAO.update(vo);
	}
	
	public void delete(CpfCaseAssayReport vo) {
		cpfCaseAssayReportDAO.remove(vo);
	}

	/**
	 * 化验单统计
	 * @param user 用户
	 * @param type 统计类型
	 * @return
	 */
	@Override
	public Integer subtotal(SysUser user, Integer type) {
		CpfCaseAssayReport vo=new CpfCaseAssayReport();
		Integer count=0;
		if(user!=null && user.getUserType().equals(Enums.UserType.MEMBER)){
			vo.setPatientId(user.getUserId());
		}else{
			vo.setDoctorId(user.getUserId());
		}
		if (type==1) {//最新化验单（最近一周）
			vo.setCaseDateFrom(DateUtil.getPreviousNDate(new Date(), 7));
		}
		count=cpfCaseAssayReportDAO.countFreeFind(vo);
		return count;
	}
}
