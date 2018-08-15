package com.lubian.cpf.service.cpf;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.page.PageModel;
import com.lubian.cpf.common.util.page.SystemContext;
import com.lubian.cpf.dao.CpfCaseHistoryPictureDAO;
import com.lubian.cpf.vo.CpfCaseAssayReport;
import com.lubian.cpf.vo.CpfCaseHistoryPicture;
import com.lubian.cpf.vo.SysUser;

@Service
public class CpfCaseHistoryPictureServiceImpl implements CpfCaseHistoryPictureService {
	@Autowired
	private CpfCaseHistoryPictureDAO cpfCaseHistoryPictureDAO;

	public CpfCaseHistoryPicture findByPK(CpfCaseHistoryPicture vo) {
		return cpfCaseHistoryPictureDAO.findByPK(vo);
	}

	public PageModel freeFind(CpfCaseHistoryPicture vo) {
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		int total = cpfCaseHistoryPictureDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<CpfCaseHistoryPicture> list = cpfCaseHistoryPictureDAO.freeFind(vo, pageIndex, pageSize,"case_date desc");
		pm.setDatas(list);
		return pm;
	}

	public void insert(CpfCaseHistoryPicture vo) {
		cpfCaseHistoryPictureDAO.insert(vo);
	}

	public void update(CpfCaseHistoryPicture vo) {
		cpfCaseHistoryPictureDAO.update(vo);
	}
	
	public void delete(CpfCaseHistoryPicture vo) {
		cpfCaseHistoryPictureDAO.remove(vo);
	}

	/**
	 * 胶片统计
	 * @param user 用户
	 * @param type 统计类型
	 * @return
	 */
	@Override
	public Integer subtotal(SysUser user, Integer type) {
		CpfCaseHistoryPicture vo=new CpfCaseHistoryPicture();
		Integer count=0;
		if(user!=null && user.getUserType().equals(Enums.UserType.MEMBER)){
			vo.setPatientId(user.getUserId());
		}else{
			vo.setDoctorId(user.getUserId());
		} 
		if (type==1) {//最新胶片（最近一周）
			vo.setCaseDateFrom(DateUtil.getPreviousNDate(new Date(), 7));
		}
		count=cpfCaseHistoryPictureDAO.countFreeFind(vo);
		return count;
	}
}
