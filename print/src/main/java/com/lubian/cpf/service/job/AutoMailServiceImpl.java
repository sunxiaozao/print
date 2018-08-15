package com.lubian.cpf.service.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.Config;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.dao.SysMailJobDAO;
import com.lubian.cpf.dao.SysUserDAO;
import com.lubian.cpf.vo.SysMailJob;
import com.lubian.cpf.vo.SysUser;

@Service
public class AutoMailServiceImpl implements AutoMailService {
	private static final Logger LOGGER = Logger.getLogger(AutoMailServiceImpl.class);

	@Autowired
	private MailService mailService;
	
	@Autowired
	private SysMailJobDAO mailJobDAO;
	
	@Autowired
	private SysUserDAO userDAO;

	public void sendAutoMail() {
		if(!Config.RUN_JOB)return;
		
		SysMailJob job = null;
		if (isIdleTime()) {
			//此处要一个个取，否则多线程进入会出问题
			while ((job = getJob()) != null) {
				processJob(job);
			}
		} else {
			while ((job = getInstantJob()) != null) {
				processJob(job);
			}
		}
	}

	// run only once per day.
	private boolean isIdleTime() {
		boolean isIdle = false;
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);

		if (hour == 1 && minute == 0) {
			isIdle = true;
		}
		return isIdle;

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	private void processJob(SysMailJob job) {
		// start process
		updateJobStatus(job.getMailJobId(),Enums.MailJobStatus.PROCESS);

		doJob(job);

		// finished.
		updateJobStatus(job.getMailJobId(),Enums.MailJobStatus.DONE);

	}

	/**
	 * do job by job type.
	 * 
	 * @param job
	 */
	private void doJob(SysMailJob job) {
		
		if(StringUtils.isBlank(job.getToEmailStr())){//根据用户发送
			List<SysUser> userList = new ArrayList<SysUser>();
			getUserList(userList, job.getToUserIdStr());		
			for(SysUser user:userList){
				if(job.getBisType().equals(Enums.MailBizType.CUSTOMIZE)){
					mailService.sendCustomizeEmail(user, job);
				}else if(job.getBisType().equals(Enums.MailBizType.FIND_PASSWORD)){
					mailService.sendFindPasswordEmail(user, job);
				}else{
					//mailService.sendTemplateEmail(user, job);
					mailService.sendShareEmail(user, job);
				}
			}
		}else{//根据邮箱发送
			String uidArray[] = job.getToEmailStr().split(",");
			for(String email : uidArray){
				if(job.getBisType().equals(Enums.MailBizType.CUSTOMIZE)){
					mailService.sendCustomizeEmail(email, job);
				}else if(job.getBisType().equals(Enums.MailBizType.FIND_PASSWORD)){
					mailService.sendFindPasswordEmail(email, job);
				}else{
					//mailService.sendTemplateEmail(email, job);
					mailService.sendShareEmail(email, job);
				}
			}
		}

	}

	private void getUserList(List<SysUser> userList, String ownerUserId) {
		if(StringUtils.isBlank(ownerUserId))return;
		String uidArray[] = ownerUserId.split(",");
		for(String uid:uidArray)
		{
			SysUser user = new SysUser();
			user.setUserId(Integer.parseInt(uid));
			user = userDAO.findByPK(user);
			if (user != null) {
				userList.add(user);
			}
		}
	}
	
	private List<SysUser> getAllUserList() {
		SysUser user = new SysUser();
		user.setUserType(Enums.UserType.MEMBER);
		//user.setEnabled(Enums.Enabled.TRUE);
		return userDAO.freeFind(user);
	}

	private void updateJobStatus(Integer jobId, String status) {
		SysMailJob job = new SysMailJob();
		job.setMailJobId(jobId);
		job.setMailStatus(status);
		mailJobDAO.update(job);
	}

	/**
	 * get job list.
	 * 
	 * @return
	 */
	private SysMailJob getJob() {
		SysMailJob job = new SysMailJob();
		job.setMailStatus(Enums.MailJobStatus.INIT);
		//job.setBisType(Enums.NotificationType.CUSTOMIZE);
		job.setMailType(Enums.MailType.SPARE_TIME);

		List<SysMailJob> list = mailJobDAO.freeFind(job, 0, 1,"CREATE_DT ASC");
		
		if(list!=null&&list.size()>0)
			job = list.get(0);
		else job = null;
		return job;
	}

	/**
	 * get instant job
	 * 
	 * @return
	 */

	private SysMailJob getInstantJob() {
		SysMailJob job = new SysMailJob();
		job.setMailStatus(Enums.MailJobStatus.INIT);
		job.setMailType(Enums.MailType.SEND_NOW);

		List<SysMailJob> list = mailJobDAO.freeFind(job, 0, 1,"CREATE_DT ASC");
		
		if(list!=null&&list.size()>0)
			job = list.get(0);
		else job = null;
		return job;
	}

	public static void main(String arg[]) {
	}

}
