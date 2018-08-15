package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysRelRoleFunc
 * @author:Lusir<lusire@gmail.com>
 */
public class SysRelRoleFunc implements java.io.Serializable{
  
	private java.lang.Integer roleFuncId;
	
	public java.lang.Integer getRoleFuncId() {
		return roleFuncId;
	}
	
	public void setRoleFuncId(java.lang.Integer roleFuncId) {
		this.roleFuncId = roleFuncId;
	}
	


  
	private java.lang.Integer roleId;
	
	public java.lang.Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
	}
	


  
	private java.lang.Integer relId;
	
	public java.lang.Integer getRelId() {
		return relId;
	}
	
	public void setRelId(java.lang.Integer relId) {
		this.relId = relId;
	}
	


  
	private java.lang.String functionCrudUrl;
	
	public java.lang.String getFunctionCrudUrl() {
		return functionCrudUrl;
	}
	
	public void setFunctionCrudUrl(java.lang.String functionCrudUrl) {
		this.functionCrudUrl = functionCrudUrl;
	}
	
	private String functionCrudUrlEq;

	public String getFunctionCrudUrlEq() {
		return functionCrudUrlEq;
	}
	
	public void setFunctionCrudUrlEq(String functionCrudUrl) {
		this.functionCrudUrlEq = functionCrudUrl;
	}


  

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.roleFuncId!=null)
        	ht.put("roleFuncId",this.roleFuncId);
        if(this.roleId!=null)
        	ht.put("roleId",this.roleId);
        if(this.relId!=null)
        	ht.put("relId",this.relId);
        if(this.functionCrudUrl!=null)
        	ht.put("functionCrudUrl",this.functionCrudUrl);
        if(this.functionCrudUrlEq!=null)
        	ht.put("functionCrudUrlEq",this.functionCrudUrlEq);
		return ht;
	}	
	
}
