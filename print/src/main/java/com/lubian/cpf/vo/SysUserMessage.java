package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysUserMessage
 * @author:Lusir<lusire@gmail.com>
 */
public class SysUserMessage implements java.io.Serializable{
  
	private java.lang.Integer messageId;
	
	public java.lang.Integer getMessageId() {
		return messageId;
	}
	
	public void setMessageId(java.lang.Integer messageId) {
		this.messageId = messageId;
	}
	


  
	private java.lang.String title;
	
	public java.lang.String getTitle() {
		return title;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	private String titleEq;

	public String getTitleEq() {
		return titleEq;
	}
	
	public void setTitleEq(String title) {
		this.titleEq = title;
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


  
	private java.lang.Integer msgCount;
	
	public java.lang.Integer getMsgCount() {
		return msgCount;
	}
	
	public void setMsgCount(java.lang.Integer msgCount) {
		this.msgCount = msgCount;
	}
	


  
	private java.lang.String userType;
	
	public java.lang.String getUserType() {
		return userType;
	}
	
	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}
	
	private String userTypeEq;

	public String getUserTypeEq() {
		return userTypeEq;
	}
	
	public void setUserTypeEq(String userType) {
		this.userTypeEq = userType;
	}


  
	private java.lang.String toUserIdStr;
	
	public java.lang.String getToUserIdStr() {
		return toUserIdStr;
	}
	
	public void setToUserIdStr(java.lang.String toUserIdStr) {
		this.toUserIdStr = toUserIdStr;
	}
	
	private String toUserIdStrEq;

	public String getToUserIdStrEq() {
		return toUserIdStrEq;
	}
	
	public void setToUserIdStrEq(String toUserIdStr) {
		this.toUserIdStrEq = toUserIdStr;
	}


  
	private java.lang.String isGenerated;
	
	public java.lang.String getIsGenerated() {
		return isGenerated;
	}
	
	public void setIsGenerated(java.lang.String isGenerated) {
		this.isGenerated = isGenerated;
	}
	
	private String isGeneratedEq;

	public String getIsGeneratedEq() {
		return isGeneratedEq;
	}
	
	public void setIsGeneratedEq(String isGenerated) {
		this.isGeneratedEq = isGenerated;
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


  
	private java.util.Date createDt;
	
	public java.util.Date getCreateDt() {
		return createDt;
	}
	
	public void setCreateDt(java.util.Date createDt) {
		this.createDt = createDt;
	}
	


  
	private java.lang.Integer orgId;
	
	public java.lang.Integer getOrgId() {
		return orgId;
	}
	
	public void setOrgId(java.lang.Integer orgId) {
		this.orgId = orgId;
	}
	


  
	private java.lang.Integer groupId;
	
	public java.lang.Integer getGroupId() {
		return groupId;
	}
	
	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
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
        if(this.messageId!=null)
        	ht.put("messageId",this.messageId);
        if(this.title!=null)
        	ht.put("title",this.title);
        if(this.titleEq!=null)
        	ht.put("titleEq",this.titleEq);
        if(this.content!=null)
        	ht.put("content",this.content);
        if(this.contentEq!=null)
        	ht.put("contentEq",this.contentEq);
        if(this.msgCount!=null)
        	ht.put("msgCount",this.msgCount);
        if(this.userType!=null)
        	ht.put("userType",this.userType);
        if(this.userTypeEq!=null)
        	ht.put("userTypeEq",this.userTypeEq);
        if(this.toUserIdStr!=null)
        	ht.put("toUserIdStr",this.toUserIdStr);
        if(this.toUserIdStrEq!=null)
        	ht.put("toUserIdStrEq",this.toUserIdStrEq);
        if(this.isGenerated!=null)
        	ht.put("isGenerated",this.isGenerated);
        if(this.isGeneratedEq!=null)
        	ht.put("isGeneratedEq",this.isGeneratedEq);
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
        if(this.createDt!=null)
        	ht.put("createDt",this.createDt);
        if(this.createDtFrom!=null)
        	ht.put("createDtFrom",this.createDtFrom);
        if(this.createDtTo!=null)
        	ht.put("createDtTo",this.createDtTo);
        if(this.orgId!=null)
        	ht.put("orgId",this.orgId);
        if(this.groupId!=null)
        	ht.put("groupId",this.groupId);
		return ht;
	}	
	
}
