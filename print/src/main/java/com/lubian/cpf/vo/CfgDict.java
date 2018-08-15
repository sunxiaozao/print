package com.lubian.cpf.vo;

import java.util.*;

/**
 * CfgDict
 * @author:Lusir<lusire@gmail.com>
 */
public class CfgDict implements java.io.Serializable{
  
	private java.lang.Integer dictId;
	
	public java.lang.Integer getDictId() {
		return dictId;
	}
	
	public void setDictId(java.lang.Integer dictId) {
		this.dictId = dictId;
	}
	


  
	private java.lang.String type;
	
	public java.lang.String getType() {
		return type;
	}
	
	public void setType(java.lang.String type) {
		this.type = type;
	}
	


  
	private java.lang.String shortCode;
	
	public java.lang.String getShortCode() {
		return shortCode;
	}
	
	public void setShortCode(java.lang.String shortCode) {
		this.shortCode = shortCode;
	}
	
	private String shortCodeEq;

	public String getShortCodeEq() {
		return shortCodeEq;
	}
	
	public void setShortCodeEq(String shortCode) {
		this.shortCodeEq = shortCode;
	}


  
	private java.lang.String longName;
	
	public java.lang.String getLongName() {
		return longName;
	}
	
	public void setLongName(java.lang.String longName) {
		this.longName = longName;
	}
	
	private String longNameEq;

	public String getLongNameEq() {
		return longNameEq;
	}
	
	public void setLongNameEq(String longName) {
		this.longNameEq = longName;
	}


  
	private java.lang.String value;
	
	public java.lang.String getValue() {
		return value;
	}
	
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	private String valueEq;

	public String getValueEq() {
		return valueEq;
	}
	
	public void setValueEq(String value) {
		this.valueEq = value;
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
	




    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.dictId!=null)
        	ht.put("dictId",this.dictId);
        if(this.type!=null)
        	ht.put("type",this.type);
        if(this.shortCode!=null)
        	ht.put("shortCode",this.shortCode);
        if(this.shortCodeEq!=null)
        	ht.put("shortCodeEq",this.shortCodeEq);
        if(this.longName!=null)
        	ht.put("longName",this.longName);
        if(this.longNameEq!=null)
        	ht.put("longNameEq",this.longNameEq);
        if(this.value!=null)
        	ht.put("value",this.value);
        if(this.valueEq!=null)
        	ht.put("valueEq",this.valueEq);
        if(this.priority!=null)
        	ht.put("priority",this.priority);
        if(this.parentId!=null)
        	ht.put("parentId",this.parentId);
		return ht;
	}	
	
}
