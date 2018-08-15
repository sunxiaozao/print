package com.lubian.cpf.service.cpf;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfCaseHistoryApplyDAO;
import com.lubian.cpf.vo.CdfNotebook;
import com.lubian.cpf.vo.CpfCaseHistoryApply;
import com.lubian.cpf.vo.SysUser;

@Service
public class CpfCaseHistoryApplyServiceImpl implements CpfCaseHistoryApplyService {
	@Autowired
	private CpfCaseHistoryApplyDAO cpfCaseHistoryApplyDAO;

	public CpfCaseHistoryApply findByPK(CpfCaseHistoryApply vo) {
		return cpfCaseHistoryApplyDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfCaseHistoryApply vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfCaseHistoryApplyDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfCaseHistoryApply> list = cpfCaseHistoryApplyDAO.freeFind(vo, pageIndex, pageSize,"case_date desc");
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfCaseHistoryApply vo) {
		cpfCaseHistoryApplyDAO.insert(vo);
	}

	public void update(CpfCaseHistoryApply vo) {
		cpfCaseHistoryApplyDAO.update(vo);
	}
	
	public void delete(CpfCaseHistoryApply vo) {
		cpfCaseHistoryApplyDAO.remove(vo);
	}

	/**
	 * 申请单统计
	 * @param user 用户
	 * @param type 统计类型
	 * @return
	 */
	@Override
	public Integer subtotal(SysUser user, Integer type) {
		CpfCaseHistoryApply vo=new CpfCaseHistoryApply();
		Integer count=0;
		if(user!=null && user.getUserType().equals(Enums.UserType.MEMBER)){
			vo.setPatientId(user.getUserId());
		}else{
			vo.setDoctorId(user.getUserId());
		}
		
		if (type==1) {//最新申请单（最近一周）
			vo.setCaseDateFrom(DateUtil.getPreviousNDate(new Date(), 7));
		}
		count=cpfCaseHistoryApplyDAO.countFreeFind(vo);
		return count;
	}
}
