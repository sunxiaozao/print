package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysNotification
 * @author:Lusir<lusire@gmail.com>
 */
public class SysNotification implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	


  
	private java.lang.String functionName;
	
	public java.lang.String getFunctionName() {
		return functionName;
	}
	
	public void setFunctionName(java.lang.String functionName) {
		this.functionName = functionName;
	}
	
	private String functionNameEq;

	public String getFunctionNameEq() {
		return functionNameEq;
	}
	
	public void setFunctionNameEq(String functionName) {
		this.functionNameEq = functionName;
	}


  
	private java.lang.String description;
	
	public java.lang.String getDescription() {
		return description;
	}
	
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	private String descriptionEq;

	public String getDescriptionEq() {
		return descriptionEq;
	}
	
	public void setDescriptionEq(String description) {
		this.descriptionEq = description;
	}


  
	private java.util.Date createDt;
	
	public java.util.Date getCreateDt() {
		return createDt;
	}
	
	public void setCreateDt(java.util.Date createDt) {
		this.createDt = createDt;
	}
	


  
	private java.lang.String isProceed;
	
	public java.lang.String getIsProceed() {
		return isProceed;
	}
	
	public void setIsProceed(java.lang.String isProceed) {
		this.isProceed = isProceed;
	}
	


  
	private java.lang.String processUserName;
	
	public java.lang.String getProcessUserName() {
		return processUserName;
	}
	
	public void setProcessUserName(java.lang.String processUserName) {
		this.processUserName = processUserName;
	}
	
	private String processUserNameEq;

	public String getProcessUserNameEq() {
		return processUserNameEq;
	}
	
	public void setProcessUserNameEq(String processUserName) {
		this.processUserNameEq = processUserName;
	}


  
	private java.lang.Integer processUserId;
	
	public java.lang.Integer getProcessUserId() {
		return processUserId;
	}
	
	public void setProcessUserId(java.lang.Integer processUserId) {
		this.processUserId = processUserId;
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
        if(this.id!=null)
        	ht.put("id",this.id);
        if(this.functionName!=null)
        	ht.put("functionName",this.functionName);
        if(this.functionNameEq!=null)
        	ht.put("functionNameEq",this.functionNameEq);
        if(this.description!=null)
        	ht.put("description",this.description);
        if(this.descriptionEq!=null)
        	ht.put("descriptionEq",this.descriptionEq);
        if(this.createDt!=null)
        	ht.put("createDt",this.createDt);
        if(this.createDtFrom!=null)
        	ht.put("createDtFrom",this.createDtFrom);
        if(this.createDtTo!=null)
        	ht.put("createDtTo",this.createDtTo);
        if(this.isProceed!=null)
        	ht.put("isProceed",this.isProceed);
        if(this.processUserName!=null)
        	ht.put("processUserName",this.processUserName);
        if(this.processUserNameEq!=null)
        	ht.put("processUserNameEq",this.processUserNameEq);
        if(this.processUserId!=null)
        	ht.put("processUserId",this.processUserId);
		return ht;
	}	
	
}
