package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysFunctionCrud
 * @author:Lusir<lusire@gmail.com>
 */
public class SysFunctionCrud implements java.io.Serializable{
  
	private java.lang.Integer relId;
	
	public java.lang.Integer getRelId() {
		return relId;
	}
	
	public void setRelId(java.lang.Integer relId) {
		this.relId = relId;
	}
	


  
	private java.lang.String alias;
	
	public java.lang.String getAlias() {
		return alias;
	}
	
	public void setAlias(java.lang.String alias) {
		this.alias = alias;
	}
	
	private String aliasEq;

	public String getAliasEq() {
		return aliasEq;
	}
	
	public void setAliasEq(String alias) {
		this.aliasEq = alias;
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


  
	private java.lang.String url;
	
	public java.lang.String getUrl() {
		return url;
	}
	
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	private String urlEq;

	public String getUrlEq() {
		return urlEq;
	}
	
	public void setUrlEq(String url) {
		this.urlEq = url;
	}


  
	private java.lang.Integer priority;
	
	public java.lang.Integer getPriority() {
		return priority;
	}
	
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}
	


  
	private java.lang.String createdBy;
	
	public java.lang.String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}
	
	private String createdByEq;

	public String getCreatedByEq() {
		return createdByEq;
	}
	
	public void setCreatedByEq(String createdBy) {
		this.createdByEq = createdBy;
	}


  
	private java.util.Date createdDt;
	
	public java.util.Date getCreatedDt() {
		return createdDt;
	}
	
	public void setCreatedDt(java.util.Date createdDt) {
		this.createdDt = createdDt;
	}
	


  
	private java.lang.Integer functionId;
	
	public java.lang.Integer getFunctionId() {
		return functionId;
	}
	
	public void setFunctionId(java.lang.Integer functionId) {
		this.functionId = functionId;
	}
	




	private java.util.Date createdDtFrom;
	
	public java.util.Date getCreatedDtFrom() {
		return createdDtFrom;
	}
	
	public void setCreatedDtFrom(java.util.Date createdDtFrom) {
		this.createdDtFrom = createdDtFrom;
	}
		
	private java.util.Date createdDtTo;
	
	public java.util.Date getCreatedDtTo() {
		return createdDtTo;
	}
	
	public void setCreatedDtTo(java.util.Date createdDtTo) {
		this.createdDtTo = createdDtTo;
	}

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.relId!=null)
        	ht.put("relId",this.relId);
        if(this.alias!=null)
        	ht.put("alias",this.alias);
        if(this.aliasEq!=null)
        	ht.put("aliasEq",this.aliasEq);
        if(this.description!=null)
        	ht.put("description",this.description);
        if(this.descriptionEq!=null)
        	ht.put("descriptionEq",this.descriptionEq);
        if(this.url!=null)
        	ht.put("url",this.url);
        if(this.urlEq!=null)
        	ht.put("urlEq",this.urlEq);
        if(this.priority!=null)
        	ht.put("priority",this.priority);
        if(this.createdBy!=null)
        	ht.put("createdBy",this.createdBy);
        if(this.createdByEq!=null)
        	ht.put("createdByEq",this.createdByEq);
        if(this.createdDt!=null)
        	ht.put("createdDt",this.createdDt);
        if(this.createdDtFrom!=null)
        	ht.put("createdDtFrom",this.createdDtFrom);
        if(this.createdDtTo!=null)
        	ht.put("createdDtTo",this.createdDtTo);
        if(this.functionId!=null)
        	ht.put("functionId",this.functionId);
		return ht;
	}	
	
}
