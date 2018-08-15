package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysUserSms
 * @author:Lusir<lusire@gmail.com>
 */
public class SysUserSms implements java.io.Serializable{
  
	private java.lang.Integer smsId;
	
	public java.lang.Integer getSmsId() {
		return smsId;
	}
	
	public void setSmsId(java.lang.Integer smsId) {
		this.smsId = smsId;
	}
	


  
	private java.lang.String phoneNo;
	
	public java.lang.String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(java.lang.String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	private String phoneNoEq;

	public String getPhoneNoEq() {
		return phoneNoEq;
	}
	
	public void setPhoneNoEq(String phoneNo) {
		this.phoneNoEq = phoneNo;
	}


  
	private java.lang.String content;
	
	public java.lang.String getContent() {
		return content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	private String contentEq;

	public String getContentEq() {
		return contentEq;
	}
	
	public void setContentEq(String content) {
		this.contentEq = content;
	}


  
	private java.lang.String respStr;
	
	public java.lang.String getRespStr() {
		return respStr;
	}
	
	public void setRespStr(java.lang.String respStr) {
		this.respStr = respStr;
	}
	
	private String respStrEq;

	public String getRespStrEq() {
		return respStrEq;
	}
	
	public void setRespStrEq(String respStr) {
		this.respStrEq = respStr;
	}


  
	private java.lang.String isSent;
	
	public java.lang.String getIsSent() {
		return isSent;
	}
	
	public void setIsSent(java.lang.String isSent) {
		this.isSent = isSent;
	}
	


  
	private java.util.Date sentDt;
	
	public java.util.Date getSentDt() {
		return sentDt;
	}
	
	public void setSentDt(java.util.Date sentDt) {
		this.sentDt = sentDt;
	}
	


  
	private java.lang.String createBy;
	
	public java.lang.String getCreateBy() {
		return createBy;
	}
	
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}
	
	private String createByEq;

	public String getCreateByEq() {
		return createByEq;
	}
	
	public void setCreateByEq(String createBy) {
		this.createByEq = createBy;
	}


  
	private java.util.Date createDt;
	
	public java.util.Date getCreateDt() {
		return createDt;
	}
	
	public void setCreateDt(java.util.Date createDt) {
		this.createDt = createDt;
	}
	


  
	private java.lang.Integer userId;
	
	public java.lang.Integer getUserId() {
		return userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	


  
	private java.lang.String isUpgoing;
	
	public java.lang.String getIsUpgoing() {
		return isUpgoing;
	}
	
	public void setIsUpgoing(java.lang.String isUpgoing) {
		this.isUpgoing = isUpgoing;
	}
	




	private java.util.Date sentDtFrom;
	
	public java.util.Date getSentDtFrom() {
		return sentDtFrom;
	}
	
	public void setSentDtFrom(java.util.Date sentDtFrom) {
		this.sentDtFrom = sentDtFrom;
	}
		
	private java.util.Date sentDtTo;
	
	public java.util.Date getSentDtTo() {
		return sentDtTo;
	}
	
	public void setSentDtTo(java.util.Date sentDtTo) {
		this.sentDtTo = sentDtTo;
	}

	private java.util.Date createDtFrom;
	
	public java.util.Date getCreateDtFrom() {
		return createDtFrom;
	}
	
	public void setCreateDtFrom(java.util.Date createDtFrom) {
		this.createDtFrom = createDtFrom;
	}
		
	private java.util.Date createDtTo;
	
	public java.util.Date getCreateDtTo() {
		return createDtTo;
	}
	
	public void setCreateDtTo(java.util.Date createDtTo) {
		this.createDtTo = createDtTo;
	}

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.smsId!=null)
        	ht.put("smsId",this.smsId);
        if(this.phoneNo!=null)
        	ht.put("phoneNo",this.phoneNo);
        if(this.phoneNoEq!=null)
        	ht.put("phoneNoEq",this.phoneNoEq);
        if(this.content!=null)
        	ht.put("content",this.content);
        if(this.contentEq!=null)
        	ht.put("contentEq",this.contentEq);
        if(this.respStr!=null)
        	ht.put("respStr",this.respStr);
        if(this.respStrEq!=null)
        	ht.put("respStrEq",this.respStrEq);
        if(this.isSent!=null)
        	ht.put("isSent",this.isSent);
        if(this.sentDt!=null)
        	ht.put("sentDt",this.sentDt);
        if(this.sentDtFrom!=null)
        	ht.put("sentDtFrom",this.sentDtFrom);
        if(this.sentDtTo!=null)
        	ht.put("sentDtTo",this.sentDtTo);
        if(this.createBy!=null)
        	ht.put("createBy",this.createBy);
        if(this.createByEq!=null)
        	ht.put("createByEq",this.createByEq);
        if(this.createDt!=null)
        	ht.put("createDt",this.createDt);
        if(this.createDtFrom!=null)
        	ht.put("createDtFrom",this.createDtFrom);
        if(this.createDtTo!=null)
        	ht.put("createDtTo",this.createDtTo);
        if(this.userId!=null)
        	ht.put("userId",this.userId);
        if(this.isUpgoing!=null)
        	ht.put("isUpgoing",this.isUpgoing);
		return ht;
	}	
	
}
