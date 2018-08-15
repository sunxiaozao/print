package com.lubian.cpf.service.job;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lubian.cpf.common.Mail;
import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.util.Config;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.dao.SysMailJobDAO;
import com.lubian.cpf.dao.SysMailJobItemDAO;
import com.lubian.cpf.vo.SysMailJob;
import com.lubian.cpf.vo.SysMailJobItem;
import com.lubian.cpf.vo.SysUser;

import freemarker.template.Configuration;

@Service
public class MailServiceImpl implements MailService {
	private static final Logger LOGGER = Logger.getLogger(MailServiceImpl.class);
	
	private static final String TEMPLATE_TEST = "template.ftl";
	private static final String FIND_PASSWORD = "findPassword.ftl";	
	private static final String SHARE = "share.ftl";	

	@Autowired
	private Configuration freemarkerConfiguration;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SysMailJobItemDAO mailJobItemDAO;
	
	@Autowired
	private SysMailJobDAO mailJobDAO;

	@Autowired
	private PropertiesConfiguration propertiesConfiguration;

		
	/**
	 * 根据预先设定的模板发送邮件
	 * 记录邮件发送明细
	 */
	public void sendTemplateEmail(SysUser user, SysMailJob job) {
		final Map<String, Object> model = new HashMap<String, Object>();
		
		Mail mail = new Mail();
		mail.setModel(model);
		mail.setEmail(user.getEmail());
		mail.setToUser(user);
		mail.setContent(job.getContent());

		SysMailJobItem item = new SysMailJobItem();
		item.setEmail(user.getEmail());
		item.setMailJobId(job.getMailJobId());
		item.setMailStatus(Enums.MailJobStatus.DONE);
		item.setToUserId(user.getUserId());
		item.setToUserName(user.getUserName());
		item.setSentDate(Calendar.getInstance().getTime());
		
		String toUserIdStr = new DecimalFormat("#").format(user.getUserId());
		try {
			String bizType = job.getBisType();
			if ( bizType.equals(Enums.MailBizType.TEMPLATE_TEST)) {
				mail.setTemplate(TEMPLATE_TEST);
				mail.setSubject("云胶片邮件模板邮件1");
				mail.getModel().put("toUser", user);
				mail.getModel().put("toUserId", toUserIdStr);
				//mail.getModel().put("testPara", xxx);
				sendMail(mail);
			}
			//} else if ( bizType.equals(Enums.MailBizType.TEMPLATE_TEST)) {
				
			//} 
		} catch (Exception e) {
			item.setMailStatus(Enums.MailJobStatus.FAILURE);
			item.setRemark(e.getMessage());
			LOGGER.error("sendTemplateEmail error: toUser("+user.getUserName()+"),"+e.getMessage());
		}
		try {
			mailJobItemDAO.save(item);
			job.setSubject(mail.getSubject());
			job.setContent(mail.getContent());
			mailJobDAO.update(job);
		} catch (Exception e) {
			LOGGER.error("mailJobItemDAO.save failed:"+e.getMessage());
		}

	}
	
	/**
	 * 根据预先设定的模板发送邮件
	 * 记录邮件发送明细
	 */
	public void sendTemplateEmail(String email, SysMailJob job) {
		final Map<String, Object> model = new HashMap<String, Object>();
		
		Mail mail = new Mail();
		mail.setModel(model);
		mail.setEmail(email);
		mail.setContent(job.getContent());

		SysMailJobItem item = new SysMailJobItem();
		item.setEmail(email);
		item.setMailJobId(job.getMailJobId());
		item.setMailStatus(Enums.MailJobStatus.DONE);
		item.setSentDate(Calendar.getInstance().getTime());
		
		try {
			String bizType = job.getBisType();
			if ( bizType.equals(Enums.MailBizType.TEMPLATE_TEST)) {
				mail.setTemplate(TEMPLATE_TEST);
				mail.setSubject("云胶片邮件模板邮件1");
				//mail.getModel().put("testPara", xxx);
				sendMail(mail);
			}
			//} else if ( bizType.equals(Enums.MailBizType.TEMPLATE_TEST)) {
				
			//} 
		} catch (Exception e) {
			item.setMailStatus(Enums.MailJobStatus.FAILURE);
			item.setRemark(e.getMessage());
			LOGGER.error("sendTemplateEmail error: toEmail("+email+"),"+e.getMessage());
		}
		try {
			mailJobItemDAO.save(item);
			job.setSubject(mail.getSubject());
			job.setContent(mail.getContent());
			mailJobDAO.update(job);
		} catch (Exception e) {
			LOGGER.error("mailJobItemDAO.save failed:"+e.getMessage());
		}

	}
	
	/**
	 * 根据自定义输入的邮件标题和内容发送邮件
	 * 记录邮件发送明细
	 */
	public void sendCustomizeEmail(SysUser user, SysMailJob job) {
		final Map<String, Object> model = new HashMap<String, Object>();
		
		Mail mail = new Mail();
		mail.setModel(model);
		mail.setEmail(user.getEmail());
		mail.setToUser(user);
		mail.setSubject(job.getSubject());
		mail.setContent(job.getContent());
		
		SysMailJobItem item = new SysMailJobItem();
		item.setEmail(user.getEmail());
		item.setMailJobId(job.getMailJobId());
		item.setMailStatus(Enums.MailJobStatus.DONE);
		item.setToUserId(user.getUserId());
		item.setToUserName(user.getUserName());
		item.setSentDate(Calendar.getInstance().getTime());
		try {
			sendMail(mail);
		} catch (Exception e) {
			item.setMailStatus(Enums.MailJobStatus.FAILURE);
			item.setRemark(e.getMessage());
			LOGGER.error("sendCustomizeEmail error: toUser("+user.getUserName()+"),"+e.getMessage());
		}
		try {
			mailJobItemDAO.save(item);
		} catch (Exception e) {
			LOGGER.error("mailJobItemDAO.save failed:"+e.getMessage());
		}
	}
	
	/**
	 * 根据自定义输入的邮件标题和内容发送邮件
	 * 记录邮件发送明细
	 */
	public void sendCustomizeEmail(String email, SysMailJob job) {
		final Map<String, Object> model = new HashMap<String, Object>();
		
		Mail mail = new Mail();
		mail.setModel(model);
		mail.setEmail(email);
		mail.setSubject(job.getSubject());
		mail.setContent(job.getContent());
		
		SysMailJobItem item = new SysMailJobItem();
		item.setEmail(email);
		item.setMailJobId(job.getMailJobId());
		item.setMailStatus(Enums.MailJobStatus.DONE);
		item.setSentDate(Calendar.getInstance().getTime());
		try {
			sendMail(mail);
		} catch (Exception e) {
			item.setMailStatus(Enums.MailJobStatus.FAILURE);
			item.setRemark(e.getMessage());
			LOGGER.error("sendCustomizeEmail error: toEmail("+email+"),"+e.getMessage());
		}
		try {
			mailJobItemDAO.save(item);
		} catch (Exception e) {
			LOGGER.error("mailJobItemDAO.save failed:"+e.getMessage());
		}
	}
	
	/**
	 * 发送用户找回密码的邮件
	 */
	@Async
	public void sendFindPasswordEmail(SysUser user, String code) {
		final Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("toUser", user);
		model.put("code", code);
		model.put("toUserId", new DecimalFormat("#").format(user.getUserId()));
		
		Mail mail = new Mail();
		mail.setModel(model);
		mail.setToUser(user);
		mail.setEmail(user.getEmail());
		mail.setTemplate(FIND_PASSWORD);
		try {
			sendMail(mail);
		} catch (Exception e) {
			LOGGER.error("sendFindPasswordEmail error: toUser("+user.getUserName()+"),"+e.getMessage());
		}

	}

	/**
	 * 执行邮件发送的具体操作
	 * @param mail
	 * @throws Exception
	 */
	private void sendMail(Mail mail) throws Exception {
		String content = "";
		try {
			mail.getModel().put("SITE", getSiteInfo());
			content = mail.getContent();
			String template = mail.getTemplate();
			if (template != null && !StringUtils.isBlank(template)) {
				content = FreeMarkerTemplateUtils.processTemplateIntoString(
						freemarkerConfiguration.getTemplate(template),
						mail.getModel());
				mail.setContent(content);
			}

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true,
					"UTF-8");
			String email = mail.getEmail();
			if (StringUtils.isBlank(email))
				throw new Exception("SysUser's email is blank.");
			helper.setTo(email);
			helper.setFrom(
					(String) propertiesConfiguration.getProperty("mail.from"),
					Config.getProperty("mail.fromName"));
			helper.setText(content, true);
			String subject = "云胶片邮件通知";
			if (!StringUtils.isBlank(mail.getSubject())) {
				subject = mail.getSubject();
			}
			helper.setSubject(subject);
			javaMailSender.send(message);
		} catch (Exception e) {
			LOGGER.error("-----------------------------------------");
			LOGGER.error(e);
			LOGGER.error("MailTo:" + mail.getEmail());
			LOGGER.error("MailBody:" + content);
			LOGGER.error("-----------------------------------------");
			throw e;
		}
	}

	/**
	 * 设置网站级别的通用参数，供邮件模板中使用
	 * @return
	 */
	private Map<String, String> getSiteInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("imageRoot", Config.getProperty(Constant.IMAGE_CONTEXT));
		map.put("siteName", Config.getProperty("site_name"));
		map.put("siteGlobalName", Config.getProperty("site_global_name"));
		map.put("siteUrl", Config.getProperty("site_url"));
		String root = Config.getProperty("site_url")+Config.getProperty("site_context");
		if(!root.endsWith("/"))root += "/";
		map.put("root", root);
		return map;
	}

	/**
	 * 发送分享email
	 * 
	 * @param user
	 *            通过用户发送
	 * @param job
	 */
	@Override
	public void sendShareEmail(SysUser user, SysMailJob job) {
		final Map<String, Object> model = new HashMap<String, Object>();

		Mail mail = new Mail();
		mail.setModel(model);
		mail.setEmail(user.getEmail());
		mail.setToUser(user);
		mail.setContent(job.getContent());

		SysMailJobItem item = new SysMailJobItem();
		item.setEmail(user.getEmail());
		item.setMailJobId(job.getMailJobId());
		item.setMailStatus(Enums.MailJobStatus.DONE);
		item.setToUserId(user.getUserId());
		item.setToUserName(user.getUserName());
		item.setSentDate(Calendar.getInstance().getTime());

		String toUserIdStr = new DecimalFormat("#").format(user.getUserId());
		try {
			String bizType = job.getBisType();
			if (bizType.equals(Enums.MailBizType.SHARE)) {
				mail.setTemplate(SHARE);
				mail.setSubject("夏宇科技微医通邮件");
				mail.getModel().put("toUser", user);
				mail.getModel().put("toUserId", toUserIdStr);
				//mail.getModel().put("testPara", xxx);
				
				mail.getModel().put("c",mail.getContent());
				
				sendMail(mail);
			}
			// } else if ( bizType.equals(Enums.MailBizType.TEMPLATE_TEST)) {

			// }
		} catch (Exception e) {
			item.setMailStatus(Enums.MailJobStatus.FAILURE);
			item.setRemark(e.getMessage());
			LOGGER.error("sendTemplateEmail error: toUser("
					+ user.getUserName() + ")," + e.getMessage());
		}
		try {
			mailJobItemDAO.save(item);
			job.setSubject(mail.getSubject());
			job.setContent(mail.getContent());
			mailJobDAO.update(job);
		} catch (Exception e) {
			LOGGER.error("mailJobItemDAO.save failed:" + e.getMessage());
		}
	}

	/**
	 * 发送分享email
	 * 
	 * @param email
	 *            通过邮箱发送
	 * @param job
	 */
	@Override
	public void sendShareEmail(String email, SysMailJob job) {
		final Map<String, Object> model = new HashMap<String, Object>();

		Mail mail = new Mail();
		mail.setModel(model);
		mail.setEmail(email);
		mail.setContent(job.getContent());

		SysMailJobItem item = new SysMailJobItem();
		item.setEmail(email);
		item.setMailJobId(job.getMailJobId());
		item.setMailStatus(Enums.MailJobStatus.DONE);
		item.setSentDate(Calendar.getInstance().getTime());

		try {
			String bizType = job.getBisType();
			if (bizType.equals(Enums.MailBizType.SHARE)) {
				mail.setTemplate(SHARE);
				mail.setSubject("夏宇科技微医通邮件");
				
				mail.getModel().put("c",mail.getContent());
				
				sendMail(mail);
			}
			// } else if ( bizType.equals(Enums.MailBizType.TEMPLATE_TEST)) {

			// }
		} catch (Exception e) {
			item.setMailStatus(Enums.MailJobStatus.FAILURE);
			item.setRemark(e.getMessage());
			LOGGER.error("sendTemplateEmail error: toEmail(" + email + "),"
					+ e.getMessage());
		}
		try {
			mailJobItemDAO.save(item);
			job.setSubject(mail.getSubject());
			job.setContent(mail.getContent());
			mailJobDAO.update(job);
		} catch (Exception e) {
			LOGGER.error("mailJobItemDAO.save failed:" + e.getMessage());
		}
	}

	@Override
	public void sendFindPasswordEmail(SysUser user, SysMailJob job) {
		final Map<String, Object> model = new HashMap<String, Object>();

		Mail mail = new Mail();
		mail.setModel(model);
		mail.setEmail(user.getEmail());
		mail.setToUser(user);
		mail.setContent(job.getContent());

		SysMailJobItem item = new SysMailJobItem();
		item.setEmail(user.getEmail());
		item.setMailJobId(job.getMailJobId());
		item.setMailStatus(Enums.MailJobStatus.DONE);
		item.setToUserId(user.getUserId());
		item.setToUserName(user.getUserName());
		item.setSentDate(Calendar.getInstance().getTime());

		String toUserIdStr = new DecimalFormat("#").format(user.getUserId());
		try {
			String bizType = job.getBisType();
			if (bizType.equals(Enums.MailBizType.FIND_PASSWORD)) {
				mail.setTemplate(FIND_PASSWORD);
				mail.setSubject("夏宇科技微医通邮件");
				mail.getModel().put("toUser", user);
				mail.getModel().put("toUserId", toUserIdStr);
				
				//mail.getModel().put("c",mail.getContent());
				
				sendMail(mail);
			}
		} catch (Exception e) {
			item.setMailStatus(Enums.MailJobStatus.FAILURE);
			item.setRemark(e.getMessage());
			LOGGER.error("sendTemplateEmail error: toUser("
					+ user.getUserName() + ")," + e.getMessage());
		}
		try {
			mailJobItemDAO.save(item);
			job.setSubject(mail.getSubject());
			job.setContent(mail.getContent());
			mailJobDAO.update(job);
		} catch (Exception e) {
			LOGGER.error("mailJobItemDAO.save failed:" + e.getMessage());
		}
	}

	@Override
	public void sendFindPasswordEmail(String email, SysMailJob job) {
		final Map<String, Object> model = new HashMap<String, Object>();

		Mail mail = new Mail();
		mail.setModel(model);
		mail.setEmail(email);
		mail.setContent(job.getContent());

		SysMailJobItem item = new SysMailJobItem();
		item.setEmail(email);
		item.setMailJobId(job.getMailJobId());
		item.setMailStatus(Enums.MailJobStatus.DONE);
		item.setSentDate(Calendar.getInstance().getTime());

		try {
			String bizType = job.getBisType();
			if (bizType.equals(Enums.MailBizType.FIND_PASSWORD)) {
				mail.setTemplate(FIND_PASSWORD);
				mail.setSubject("夏宇科技微医通邮件");
				
				mail.getModel().put("email",email);
				mail.getModel().put("address",Constant.FIND_PASS_ADDRESS);
				
				sendMail(mail);
			}
		} catch (Exception e) {
			item.setMailStatus(Enums.MailJobStatus.FAILURE);
			item.setRemark(e.getMessage());
			LOGGER.error("sendTemplateEmail error: toEmail(" + email + "),"
					+ e.getMessage());
		}
		try {
			mailJobItemDAO.save(item);
			job.setSubject(mail.getSubject());
			job.setContent(mail.getContent());
			mailJobDAO.update(job);
		} catch (Exception e) {
			LOGGER.error("mailJobItemDAO.save failed:" + e.getMessage());
		}
	}

}
