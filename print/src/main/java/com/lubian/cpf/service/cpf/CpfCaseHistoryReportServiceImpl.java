package com.lubian.cpf.service.cpf;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfCaseHistoryReportDAO;
import com.lubian.cpf.vo.CpfCaseHistoryReport;
import com.lubian.cpf.vo.SysUser;

@Service
public class CpfCaseHistoryReportServiceImpl implements CpfCaseHistoryReportService {
	@Autowired
	private CpfCaseHistoryReportDAO cpfCaseHistoryReportDAO;

	public CpfCaseHistoryReport findByPK(CpfCaseHistoryReport vo) {
		return cpfCaseHistoryReportDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfCaseHistoryReport vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfCaseHistoryReportDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfCaseHistoryReport> list = cpfCaseHistoryReportDAO.freeFind(vo, pageIndex, pageSize,"case_date desc");
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfCaseHistoryReport vo) {
		cpfCaseHistoryReportDAO.insert(vo);
	}

	public void update(CpfCaseHistoryReport vo) {
		cpfCaseHistoryReportDAO.update(vo);
	}
	
	public void delete(CpfCaseHistoryReport vo) {
		cpfCaseHistoryReportDAO.remove(vo);
	}

	/**
	 * 报告统计
	 * @param user 用户
	 * @param type 统计类型
	 * @return
	 */
	@Override
	public Integer subtotal(SysUser user,Integer type) {
		CpfCaseHistoryReport vo=new CpfCaseHistoryReport();
		Integer count=0;
		if(user!=null && user.getUserType().equals(Enums.UserType.MEMBER)){
			vo.setPatientId(user.getUserId());
		}else{
			vo.setDoctorId(user.getUserId());
		}
		if (type==1) {//最新报告（最近一周）
			//cchr.setCreateTimeFrom(DateUtil.getPreviousNDate(new Date(), 7));//大于
			vo.setCaseDateFrom(DateUtil.getPreviousNDate(new Date(), 7));
		}
		count=cpfCaseHistoryReportDAO.countFreeFind(vo);
			
		return count;
	}

	
}
