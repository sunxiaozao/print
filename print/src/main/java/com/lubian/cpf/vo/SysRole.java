package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysRole
 * @author:Lusir<lusire@gmail.com>
 */
public class SysRole implements java.io.Serializable{
  
	private java.lang.Integer roleId;
	
	public java.lang.Integer getRoleId() {
		return roleId;
	}
	
	public void setRoleId(java.lang.Integer roleId) {
		this.roleId = roleId;
	}
	


  
	private java.lang.String roleName;
	
	public java.lang.String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}
	
	private String roleNameEq;

	public String getRoleNameEq() {
		return roleNameEq;
	}
	
	public void setRoleNameEq(String roleName) {
		this.roleNameEq = roleName;
	}


  
	private java.lang.String roleDesc;
	
	public java.lang.String getRoleDesc() {
		return roleDesc;
	}
	
	public void setRoleDesc(java.lang.String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	private String roleDescEq;

	public String getRoleDescEq() {
		return roleDescEq;
	}
	
	public void setRoleDescEq(String roleDesc) {
		this.roleDescEq = roleDesc;
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
	


      
	private ArrayList relRoleFuncList = new ArrayList();
	//relation=one-to-many
	public ArrayList getRelRoleFuncList() {
	    return this.relRoleFuncList;
	}
	    
	public void setRelRoleFuncList(ArrayList vObj ) {
	    this.relRoleFuncList= new ArrayList();
	    for ( int i = 0, n = vObj.size();i<n;i++ ) {
	        SysRelRoleFunc oneObj =  (SysRelRoleFunc) vObj.get(i);
	        this.addRelRoleFuncList(oneObj);
	    }
	}    
	
	public void addRelRoleFuncList(SysRelRoleFunc newSysRelRoleFunc ) {
	    if ( this.relRoleFuncList == null ) 
	        this.relRoleFuncList = new ArrayList();
	    if(newSysRelRoleFunc==null)return;
	    this.relRoleFuncList.add(newSysRelRoleFunc);
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
        if ( this.relRoleFuncList != null ) {
            for ( int i = 0, n = relRoleFuncList.size(); i < n; i++ ) { 
              SysRelRoleFunc oneSysRelRoleFunc=(SysRelRoleFunc) relRoleFuncList.get(i);
              oneSysRelRoleFunc.setRoleId(this.roleId);
              oneSysRelRoleFunc.fixup();
          }
      }
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.roleId!=null)
        	ht.put("roleId",this.roleId);
        if(this.roleName!=null)
        	ht.put("roleName",this.roleName);
        if(this.roleNameEq!=null)
        	ht.put("roleNameEq",this.roleNameEq);
        if(this.roleDesc!=null)
        	ht.put("roleDesc",this.roleDesc);
        if(this.roleDescEq!=null)
        	ht.put("roleDescEq",this.roleDescEq);
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
		return ht;
	}	
	
}
