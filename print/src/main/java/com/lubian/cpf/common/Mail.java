package com.lubian.cpf.common;

import java.util.Map;

import com.lubian.cpf.vo.SysUser;


public class Mail {
	private Map<String, Object> model;
	private String template;
	private SysUser toUser;
	private String Subject;
	private String email;
	private String Content;
	
	
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public SysUser getToUser() {
		return toUser;
	}
	public void setToUser(SysUser toUser) {
		this.toUser = toUser;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
	
	
}
