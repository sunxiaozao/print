package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysMailJobItem
 * @author:Lusir<lusire@gmail.com>
 */
public class SysMailJobItem implements java.io.Serializable{
  
	private java.lang.Integer mailItemId;
	
	public java.lang.Integer getMailItemId() {
		return mailItemId;
	}
	
	public void setMailItemId(java.lang.Integer mailItemId) {
		this.mailItemId = mailItemId;
	}
	


  
	private java.lang.Integer mailJobId;
	
	public java.lang.Integer getMailJobId() {
		return mailJobId;
	}
	
	public void setMailJobId(java.lang.Integer mailJobId) {
		this.mailJobId = mailJobId;
	}
	


  
	private java.lang.Integer toUserId;
	
	public java.lang.Integer getToUserId() {
		return toUserId;
	}
	
	public void setToUserId(java.lang.Integer toUserId) {
		this.toUserId = toUserId;
	}
	


  
	private java.lang.String toUserName;
	
	public java.lang.String getToUserName() {
		return toUserName;
	}
	
	public void setToUserName(java.lang.String toUserName) {
		this.toUserName = toUserName;
	}
	
	private String toUserNameEq;

	public String getToUserNameEq() {
		return toUserNameEq;
	}
	
	public void setToUserNameEq(String toUserName) {
		this.toUserNameEq = toUserName;
	}


  
	private java.lang.String email;
	
	public java.lang.String getEmail() {
		return email;
	}
	
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	
	private String emailEq;

	public String getEmailEq() {
		return emailEq;
	}
	
	public void setEmailEq(String email) {
		this.emailEq = email;
	}


  
	private java.util.Date sentDate;
	
	public java.util.Date getSentDate() {
		return sentDate;
	}
	
	public void setSentDate(java.util.Date sentDate) {
		this.sentDate = sentDate;
	}
	


  
	private java.lang.String mailStatus;
	
	public java.lang.String getMailStatus() {
		return mailStatus;
	}
	
	public void setMailStatus(java.lang.String mailStatus) {
		this.mailStatus = mailStatus;
	}
	
	private String mailStatusEq;

	public String getMailStatusEq() {
		return mailStatusEq;
	}
	
	public void setMailStatusEq(String mailStatus) {
		this.mailStatusEq = mailStatus;
	}


  
	private java.lang.String remark;
	
	public java.lang.String getRemark() {
		return remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	private String remarkEq;

	public String getRemarkEq() {
		return remarkEq;
	}
	
	public void setRemarkEq(String remark) {
		this.remarkEq = remark;
	}




	private java.util.Date sentDateFrom;
	
	public java.util.Date getSentDateFrom() {
		return sentDateFrom;
	}
	
	public void setSentDateFrom(java.util.Date sentDateFrom) {
		this.sentDateFrom = sentDateFrom;
	}
		
	private java.util.Date sentDateTo;
	
	public java.util.Date getSentDateTo() {
		return sentDateTo;
	}
	
	public void setSentDateTo(java.util.Date sentDateTo) {
		this.sentDateTo = sentDateTo;
	}

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.mailItemId!=null)
        	ht.put("mailItemId",this.mailItemId);
        if(this.mailJobId!=null)
        	ht.put("mailJobId",this.mailJobId);
        if(this.toUserId!=null)
        	ht.put("toUserId",this.toUserId);
        if(this.toUserName!=null)
        	ht.put("toUserName",this.toUserName);
        if(this.toUserNameEq!=null)
        	ht.put("toUserNameEq",this.toUserNameEq);
        if(this.email!=null)
        	ht.put("email",this.email);
        if(this.emailEq!=null)
        	ht.put("emailEq",this.emailEq);
        if(this.sentDate!=null)
        	ht.put("sentDate",this.sentDate);
        if(this.sentDateFrom!=null)
        	ht.put("sentDateFrom",this.sentDateFrom);
        if(this.sentDateTo!=null)
        	ht.put("sentDateTo",this.sentDateTo);
        if(this.mailStatus!=null)
        	ht.put("mailStatus",this.mailStatus);
        if(this.mailStatusEq!=null)
        	ht.put("mailStatusEq",this.mailStatusEq);
        if(this.remark!=null)
        	ht.put("remark",this.remark);
        if(this.remarkEq!=null)
        	ht.put("remarkEq",this.remarkEq);
		return ht;
	}	
	
}
