package com.lubian.cpf.vo;

import java.util.*;

/**
 * CfgPara
 * @author:Lusir<lusire@gmail.com>
 */
public class CfgPara implements java.io.Serializable{
  
	private java.lang.String paraCode;
	
	public java.lang.String getParaCode() {
		return paraCode;
	}
	
	public void setParaCode(java.lang.String paraCode) {
		this.paraCode = paraCode;
	}
	
	private String paraCodeEq;

	public String getParaCodeEq() {
		return paraCodeEq;
	}
	
	public void setParaCodeEq(String paraCode) {
		this.paraCodeEq = paraCode;
	}


  
	private java.lang.String paraName;
	
	public java.lang.String getParaName() {
		return paraName;
	}
	
	public void setParaName(java.lang.String paraName) {
		this.paraName = paraName;
	}
	
	private String paraNameEq;

	public String getParaNameEq() {
		return paraNameEq;
	}
	
	public void setParaNameEq(String paraName) {
		this.paraNameEq = paraName;
	}


  
	private java.lang.String paraValue;
	
	public java.lang.String getParaValue() {
		return paraValue;
	}
	
	public void setParaValue(java.lang.String paraValue) {
		this.paraValue = paraValue;
	}
	
	private String paraValueEq;

	public String getParaValueEq() {
		return paraValueEq;
	}
	
	public void setParaValueEq(String paraValue) {
		this.paraValueEq = paraValue;
	}


  
	private java.lang.String parentCode;
	
	public java.lang.String getParentCode() {
		return parentCode;
	}
	
	public void setParentCode(java.lang.String parentCode) {
		this.parentCode = parentCode;
	}
	
	private String parentCodeEq;

	public String getParentCodeEq() {
		return parentCodeEq;
	}
	
	public void setParentCodeEq(String parentCode) {
		this.parentCodeEq = parentCode;
	}


  
	private java.lang.String paraType;
	
	public java.lang.String getParaType() {
		return paraType;
	}
	
	public void setParaType(java.lang.String paraType) {
		this.paraType = paraType;
	}
	
	private String paraTypeEq;

	public String getParaTypeEq() {
		return paraTypeEq;
	}
	
	public void setParaTypeEq(String paraType) {
		this.paraTypeEq = paraType;
	}


  
	private java.lang.Integer priority;
	
	public java.lang.Integer getPriority() {
		return priority;
	}
	
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}
	


  

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.paraCode!=null)
        	ht.put("paraCode",this.paraCode);
        if(this.paraCodeEq!=null)
        	ht.put("paraCodeEq",this.paraCodeEq);
        if(this.paraName!=null)
        	ht.put("paraName",this.paraName);
        if(this.paraNameEq!=null)
        	ht.put("paraNameEq",this.paraNameEq);
        if(this.paraValue!=null)
        	ht.put("paraValue",this.paraValue);
        if(this.paraValueEq!=null)
        	ht.put("paraValueEq",this.paraValueEq);
        if(this.parentCode!=null)
        	ht.put("parentCode",this.parentCode);
        if(this.parentCodeEq!=null)
        	ht.put("parentCodeEq",this.parentCodeEq);
        if(this.paraType!=null)
        	ht.put("paraType",this.paraType);
        if(this.paraTypeEq!=null)
        	ht.put("paraTypeEq",this.paraTypeEq);
        if(this.priority!=null)
        	ht.put("priority",this.priority);
		return ht;
	}	
	
}
