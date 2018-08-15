package com.lubian.cpf.service.job;

import com.lubian.cpf.vo.SysMailJob;
import com.lubian.cpf.vo.SysUser;

public interface MailService {
	public void sendTemplateEmail(SysUser user, SysMailJob job);
	public void sendFindPasswordEmail(SysUser user, String code);
	public void sendCustomizeEmail(SysUser user, SysMailJob job);
	public void sendTemplateEmail(String email, SysMailJob job);
	public void sendCustomizeEmail(String email, SysMailJob job);
	/**
	 * 发送分享email
	 * @param user 通过用户发送
	 * @param job
	 */
	public void sendShareEmail(SysUser user, SysMailJob job);
	/**
	 * 发送分享email
	 * @param email 通过邮箱发送
	 * @param job
	 */
	public void sendShareEmail(String email, SysMailJob job);
	
	/**
	 * 发送找回密码email
	 * @param user 通过用户发送
	 * @param job
	 */
	public void sendFindPasswordEmail(SysUser user, SysMailJob job);
	/**
	 * 发送找回密码email
	 * @param email 通过邮箱发送
	 * @param job
	 */
	public void sendFindPasswordEmail(String email, SysMailJob job);
}
