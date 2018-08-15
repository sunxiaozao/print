package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysUserMessageItem
 * @author:Lusir<lusire@gmail.com>
 */
public class SysUserMessageItem implements java.io.Serializable{
  
	private java.lang.Integer messageItemId;
	
	public java.lang.Integer getMessageItemId() {
		return messageItemId;
	}
	
	public void setMessageItemId(java.lang.Integer messageItemId) {
		this.messageItemId = messageItemId;
	}
	


  
	private java.lang.String isRead;
	
	public java.lang.String getIsRead() {
		return isRead;
	}
	
	public void setIsRead(java.lang.String isRead) {
		this.isRead = isRead;
	}
	
	private String isReadEq;

	public String getIsReadEq() {
		return isReadEq;
	}
	
	public void setIsReadEq(String isRead) {
		this.isReadEq = isRead;
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


  
	private java.lang.Integer messageId;
	
	public java.lang.Integer getMessageId() {
		return messageId;
	}
	
	public void setMessageId(java.lang.Integer messageId) {
		this.messageId = messageId;
	}
	


  
	private java.util.Date createDt;
	
	public java.util.Date getCreateDt() {
		return createDt;
	}
	
	public void setCreateDt(java.util.Date createDt) {
		this.createDt = createDt;
	}
	


  
	private java.lang.String modifyBy;
	
	public java.lang.String getModifyBy() {
		return modifyBy;
	}
	
	public void setModifyBy(java.lang.String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	private String modifyByEq;

	public String getModifyByEq() {
		return modifyByEq;
	}
	
	public void setModifyByEq(String modifyBy) {
		this.modifyByEq = modifyBy;
	}


  
	private java.util.Date modifyDt;
	
	public java.util.Date getModifyDt() {
		return modifyDt;
	}
	
	public void setModifyDt(java.util.Date modifyDt) {
		this.modifyDt = modifyDt;
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

	private java.util.Date modifyDtFrom;
	
	public java.util.Date getModifyDtFrom() {
		return modifyDtFrom;
	}
	
	public void setModifyDtFrom(java.util.Date modifyDtFrom) {
		this.modifyDtFrom = modifyDtFrom;
	}
		
	private java.util.Date modifyDtTo;
	
	public java.util.Date getModifyDtTo() {
		return modifyDtTo;
	}
	
	public void setModifyDtTo(java.util.Date modifyDtTo) {
		this.modifyDtTo = modifyDtTo;
	}

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.messageItemId!=null)
        	ht.put("messageItemId",this.messageItemId);
        if(this.isRead!=null)
        	ht.put("isRead",this.isRead);
        if(this.isReadEq!=null)
        	ht.put("isReadEq",this.isReadEq);
        if(this.toUserId!=null)
        	ht.put("toUserId",this.toUserId);
        if(this.toUserName!=null)
        	ht.put("toUserName",this.toUserName);
        if(this.toUserNameEq!=null)
        	ht.put("toUserNameEq",this.toUserNameEq);
        if(this.messageId!=null)
        	ht.put("messageId",this.messageId);
        if(this.createDt!=null)
        	ht.put("createDt",this.createDt);
        if(this.createDtFrom!=null)
        	ht.put("createDtFrom",this.createDtFrom);
        if(this.createDtTo!=null)
        	ht.put("createDtTo",this.createDtTo);
        if(this.modifyBy!=null)
        	ht.put("modifyBy",this.modifyBy);
        if(this.modifyByEq!=null)
        	ht.put("modifyByEq",this.modifyByEq);
        if(this.modifyDt!=null)
        	ht.put("modifyDt",this.modifyDt);
        if(this.modifyDtFrom!=null)
        	ht.put("modifyDtFrom",this.modifyDtFrom);
        if(this.modifyDtTo!=null)
        	ht.put("modifyDtTo",this.modifyDtTo);
        if(this.createBy!=null)
        	ht.put("createBy",this.createBy);
        if(this.createByEq!=null)
        	ht.put("createByEq",this.createByEq);
		return ht;
	}	
	
}
