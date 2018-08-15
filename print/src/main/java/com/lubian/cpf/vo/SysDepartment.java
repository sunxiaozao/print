package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysDepartment
 * @author:Lusir<lusire@gmail.com>
 */
public class SysDepartment implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	


  
	private java.lang.Integer hospitalId;
	
	public java.lang.Integer getHospitalId() {
		return hospitalId;
	}
	
	public void setHospitalId(java.lang.Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	


  
	private java.lang.String departmentName;
	
	public java.lang.String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(java.lang.String departmentName) {
		this.departmentName = departmentName;
	}
	
	private String departmentNameEq;

	public String getDepartmentNameEq() {
		return departmentNameEq;
	}
	
	public void setDepartmentNameEq(String departmentName) {
		this.departmentNameEq = departmentName;
	}


  
	private java.lang.String type;
	
	public java.lang.String getType() {
		return type;
	}
	
	public void setType(java.lang.String type) {
		this.type = type;
	}
	
	private String typeEq;

	public String getTypeEq() {
		return typeEq;
	}
	
	public void setTypeEq(String type) {
		this.typeEq = type;
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


  
	private java.lang.String creator;
	
	public java.lang.String getCreator() {
		return creator;
	}
	
	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}
	
	private String creatorEq;

	public String getCreatorEq() {
		return creatorEq;
	}
	
	public void setCreatorEq(String creator) {
		this.creatorEq = creator;
	}


  
	private java.util.Date createDate;
	
	public java.util.Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	


  
	private java.lang.String modifier;
	
	public java.lang.String getModifier() {
		return modifier;
	}
	
	public void setModifier(java.lang.String modifier) {
		this.modifier = modifier;
	}
	
	private String modifierEq;

	public String getModifierEq() {
		return modifierEq;
	}
	
	public void setModifierEq(String modifier) {
		this.modifierEq = modifier;
	}


  
	private java.util.Date modifyDate;
	
	public java.util.Date getModifyDate() {
		return modifyDate;
	}
	
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	




	private java.util.Date createDateFrom;
	
	public java.util.Date getCreateDateFrom() {
		return createDateFrom;
	}
	
	public void setCreateDateFrom(java.util.Date createDateFrom) {
		this.createDateFrom = createDateFrom;
	}
		
	private java.util.Date createDateTo;
	
	public java.util.Date getCreateDateTo() {
		return createDateTo;
	}
	
	public void setCreateDateTo(java.util.Date createDateTo) {
		this.createDateTo = createDateTo;
	}

	private java.util.Date modifyDateFrom;
	
	public java.util.Date getModifyDateFrom() {
		return modifyDateFrom;
	}
	
	public void setModifyDateFrom(java.util.Date modifyDateFrom) {
		this.modifyDateFrom = modifyDateFrom;
	}
		
	private java.util.Date modifyDateTo;
	
	public java.util.Date getModifyDateTo() {
		return modifyDateTo;
	}
	
	public void setModifyDateTo(java.util.Date modifyDateTo) {
		this.modifyDateTo = modifyDateTo;
	}

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.id!=null)
        	ht.put("id",this.id);
        if(this.hospitalId!=null)
        	ht.put("hospitalId",this.hospitalId);
        if(this.departmentName!=null)
        	ht.put("departmentName",this.departmentName);
        if(this.departmentNameEq!=null)
        	ht.put("departmentNameEq",this.departmentNameEq);
        if(this.type!=null)
        	ht.put("type",this.type);
        if(this.typeEq!=null)
        	ht.put("typeEq",this.typeEq);
        if(this.description!=null)
        	ht.put("description",this.description);
        if(this.descriptionEq!=null)
        	ht.put("descriptionEq",this.descriptionEq);
        if(this.creator!=null)
        	ht.put("creator",this.creator);
        if(this.creatorEq!=null)
        	ht.put("creatorEq",this.creatorEq);
        if(this.createDate!=null)
        	ht.put("createDate",this.createDate);
        if(this.createDateFrom!=null)
        	ht.put("createDateFrom",this.createDateFrom);
        if(this.createDateTo!=null)
        	ht.put("createDateTo",this.createDateTo);
        if(this.modifier!=null)
        	ht.put("modifier",this.modifier);
        if(this.modifierEq!=null)
        	ht.put("modifierEq",this.modifierEq);
        if(this.modifyDate!=null)
        	ht.put("modifyDate",this.modifyDate);
        if(this.modifyDateFrom!=null)
        	ht.put("modifyDateFrom",this.modifyDateFrom);
        if(this.modifyDateTo!=null)
        	ht.put("modifyDateTo",this.modifyDateTo);
		return ht;
	}	
	
}
