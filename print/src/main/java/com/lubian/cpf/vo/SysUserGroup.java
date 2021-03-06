package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysUserGroup
 * @author:Lusir<lusire@gmail.com>
 */
public class SysUserGroup implements java.io.Serializable{
  
	private java.lang.Integer groupId;
	
	public java.lang.Integer getGroupId() {
		return groupId;
	}
	
	public void setGroupId(java.lang.Integer groupId) {
		this.groupId = groupId;
	}
	


  
	private java.lang.String groupName;
	
	public java.lang.String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(java.lang.String groupName) {
		this.groupName = groupName;
	}
	
	private String groupNameEq;

	public String getGroupNameEq() {
		return groupNameEq;
	}
	
	public void setGroupNameEq(String groupName) {
		this.groupNameEq = groupName;
	}


  
	private java.lang.String status;
	
	public java.lang.String getStatus() {
		return status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	private String statusEq;

	public String getStatusEq() {
		return statusEq;
	}
	
	public void setStatusEq(String status) {
		this.statusEq = status;
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
	


  
	private java.lang.Integer parentId;
	
	public java.lang.Integer getParentId() {
		return parentId;
	}
	
	public void setParentId(java.lang.Integer parentId) {
		this.parentId = parentId;
	}
	


  
	private java.lang.Integer priority;
	
	public java.lang.Integer getPriority() {
		return priority;
	}
	
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
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
        if(this.groupId!=null)
        	ht.put("groupId",this.groupId);
        if(this.groupName!=null)
        	ht.put("groupName",this.groupName);
        if(this.groupNameEq!=null)
        	ht.put("groupNameEq",this.groupNameEq);
        if(this.status!=null)
        	ht.put("status",this.status);
        if(this.statusEq!=null)
        	ht.put("statusEq",this.statusEq);
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
        if(this.parentId!=null)
        	ht.put("parentId",this.parentId);
        if(this.priority!=null)
        	ht.put("priority",this.priority);
		return ht;
	}	
	
}
