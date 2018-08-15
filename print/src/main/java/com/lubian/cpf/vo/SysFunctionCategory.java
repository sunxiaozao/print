package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysFunctionCategory
 * @author:Lusir<lusire@gmail.com>
 */
public class SysFunctionCategory implements java.io.Serializable{
  
	private java.lang.Integer categoryId;
	
	public java.lang.Integer getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(java.lang.Integer categoryId) {
		this.categoryId = categoryId;
	}
	


  
	private java.lang.String categoryName;
	
	public java.lang.String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(java.lang.String categoryName) {
		this.categoryName = categoryName;
	}
	
	private String categoryNameEq;

	public String getCategoryNameEq() {
		return categoryNameEq;
	}
	
	public void setCategoryNameEq(String categoryName) {
		this.categoryNameEq = categoryName;
	}


  
	private java.lang.Integer priority;
	
	public java.lang.Integer getPriority() {
		return priority;
	}
	
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}
	


  
	private java.lang.Integer parentId;
	
	public java.lang.Integer getParentId() {
		return parentId;
	}
	
	public void setParentId(java.lang.Integer parentId) {
		this.parentId = parentId;
	}
	


  
	private java.lang.String iconClass;
	
	public java.lang.String getIconClass() {
		return iconClass;
	}
	
	public void setIconClass(java.lang.String iconClass) {
		this.iconClass = iconClass;
	}
	
	private String iconClassEq;

	public String getIconClassEq() {
		return iconClassEq;
	}
	
	public void setIconClassEq(String iconClass) {
		this.iconClassEq = iconClass;
	}


      

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.categoryId!=null)
        	ht.put("categoryId",this.categoryId);
        if(this.categoryName!=null)
        	ht.put("categoryName",this.categoryName);
        if(this.categoryNameEq!=null)
        	ht.put("categoryNameEq",this.categoryNameEq);
        if(this.priority!=null)
        	ht.put("priority",this.priority);
        if(this.parentId!=null)
        	ht.put("parentId",this.parentId);
        if(this.iconClass!=null)
        	ht.put("iconClass",this.iconClass);
        if(this.iconClassEq!=null)
        	ht.put("iconClassEq",this.iconClassEq);
		return ht;
	}	
	
}
