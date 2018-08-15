package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysFunction
 * @author:Lusir<lusire@gmail.com>
 */
public class SysFunction implements java.io.Serializable{
  
	private java.lang.Integer functionId;
	
	public java.lang.Integer getFunctionId() {
		return functionId;
	}
	
	public void setFunctionId(java.lang.Integer functionId) {
		this.functionId = functionId;
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


  
	private java.lang.String others;
	
	public java.lang.String getOthers() {
		return others;
	}
	
	public void setOthers(java.lang.String others) {
		this.others = others;
	}
	
	private String othersEq;

	public String getOthersEq() {
		return othersEq;
	}
	
	public void setOthersEq(String others) {
		this.othersEq = others;
	}


  
	private java.lang.Integer priority;
	
	public java.lang.Integer getPriority() {
		return priority;
	}
	
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
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
	


  
	private java.lang.Integer categoryId;
	
	public java.lang.Integer getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(java.lang.Integer categoryId) {
		this.categoryId = categoryId;
	}
	


        
	private ArrayList functionCrudList = new ArrayList();
	//relation=one-to-many
	public ArrayList getFunctionCrudList() {
	    return this.functionCrudList;
	}
	    
	public void setFunctionCrudList(ArrayList vObj ) {
	    this.functionCrudList= new ArrayList();
	    for ( int i = 0, n = vObj.size();i<n;i++ ) {
	        SysFunctionCrud oneObj =  (SysFunctionCrud) vObj.get(i);
	        this.addFunctionCrudList(oneObj);
	    }
	}    
	
	public void addFunctionCrudList(SysFunctionCrud newSysFunctionCrud ) {
	    if ( this.functionCrudList == null ) 
	        this.functionCrudList = new ArrayList();
	    if(newSysFunctionCrud==null)return;
	    this.functionCrudList.add(newSysFunctionCrud);
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
        if ( this.functionCrudList != null ) {
            for ( int i = 0, n = functionCrudList.size(); i < n; i++ ) { 
              SysFunctionCrud oneSysFunctionCrud=(SysFunctionCrud) functionCrudList.get(i);
              oneSysFunctionCrud.setFunctionId(this.functionId);
              oneSysFunctionCrud.fixup();
          }
      }
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.functionId!=null)
        	ht.put("functionId",this.functionId);
        if(this.functionName!=null)
        	ht.put("functionName",this.functionName);
        if(this.functionNameEq!=null)
        	ht.put("functionNameEq",this.functionNameEq);
        if(this.url!=null)
        	ht.put("url",this.url);
        if(this.urlEq!=null)
        	ht.put("urlEq",this.urlEq);
        if(this.others!=null)
        	ht.put("others",this.others);
        if(this.othersEq!=null)
        	ht.put("othersEq",this.othersEq);
        if(this.priority!=null)
        	ht.put("priority",this.priority);
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
        if(this.categoryId!=null)
        	ht.put("categoryId",this.categoryId);
		return ht;
	}	
	
}
