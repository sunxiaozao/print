package com.lubian.cpf.vo;

import java.util.*;

/**
 * CpfCaseFolderAuthorization
 * @author:Lusir<lusire@gmail.com>
 */
public class CpfCaseFolderAuthorization implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	


  
	private java.lang.Integer patientId;
	
	public java.lang.Integer getPatientId() {
		return patientId;
	}
	
	public void setPatientId(java.lang.Integer patientId) {
		this.patientId = patientId;
	}
	

	private String patientId_instr;

	public String getPatientId_instr() {
		return patientId_instr;
	}
	
	public void setPatientId_instr(String patientId) {
		this.patientId_instr = patientId;
	}

  
	private java.lang.Integer folderId;
	
	public java.lang.Integer getFolderId() {
		return folderId;
	}
	
	public void setFolderId(java.lang.Integer folderId) {
		this.folderId = folderId;
	}
	


  
	private java.lang.Integer hospitalId;
	
	public java.lang.Integer getHospitalId() {
		return hospitalId;
	}
	
	public void setHospitalId(java.lang.Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	


  
	private java.lang.Integer departmentId;
	
	public java.lang.Integer getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(java.lang.Integer departmentId) {
		this.departmentId = departmentId;
	}
	


  
	private java.lang.String doctor;
	
	public java.lang.String getDoctor() {
		return doctor;
	}
	
	public void setDoctor(java.lang.String doctor) {
		this.doctor = doctor;
	}
	
	private String doctorEq;

	public String getDoctorEq() {
		return doctorEq;
	}
	
	public void setDoctorEq(String doctor) {
		this.doctorEq = doctor;
	}


  
	private java.lang.String experiod;
	
	public java.lang.String getExperiod() {
		return experiod;
	}
	
	public void setExperiod(java.lang.String experiod) {
		this.experiod = experiod;
	}
	
	private String experiodEq;

	public String getExperiodEq() {
		return experiodEq;
	}
	
	public void setExperiodEq(String experiod) {
		this.experiodEq = experiod;
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


  
	private java.util.Date createTime;
	
	public java.util.Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
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


  
	private java.util.Date modifyTime;
	
	public java.util.Date getModifyTime() {
		return modifyTime;
	}
	
	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	


  

	private java.util.Date createTimeFrom;
	
	public java.util.Date getCreateTimeFrom() {
		return createTimeFrom;
	}
	
	public void setCreateTimeFrom(java.util.Date createTimeFrom) {
		this.createTimeFrom = createTimeFrom;
	}
		
	private java.util.Date createTimeTo;
	
	public java.util.Date getCreateTimeTo() {
		return createTimeTo;
	}
	
	public void setCreateTimeTo(java.util.Date createTimeTo) {
		this.createTimeTo = createTimeTo;
	}

	private java.util.Date modifyTimeFrom;
	
	public java.util.Date getModifyTimeFrom() {
		return modifyTimeFrom;
	}
	
	public void setModifyTimeFrom(java.util.Date modifyTimeFrom) {
		this.modifyTimeFrom = modifyTimeFrom;
	}
		
	private java.util.Date modifyTimeTo;
	
	public java.util.Date getModifyTimeTo() {
		return modifyTimeTo;
	}
	
	public void setModifyTimeTo(java.util.Date modifyTimeTo) {
		this.modifyTimeTo = modifyTimeTo;
	}

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.id!=null)
        	ht.put("id",this.id);
        if(this.patientId!=null)
        	ht.put("patientId",this.patientId);
        if(this.patientId_instr!=null)
        	ht.put("patientId_instr",this.patientId_instr);
        if(this.folderId!=null)
        	ht.put("folderId",this.folderId);
        if(this.hospitalId!=null)
        	ht.put("hospitalId",this.hospitalId);
        if(this.departmentId!=null)
        	ht.put("departmentId",this.departmentId);
        if(this.doctor!=null)
        	ht.put("doctor",this.doctor);
        if(this.doctorEq!=null)
        	ht.put("doctorEq",this.doctorEq);
        if(this.experiod!=null)
        	ht.put("experiod",this.experiod);
        if(this.experiodEq!=null)
        	ht.put("experiodEq",this.experiodEq);
        if(this.creator!=null)
        	ht.put("creator",this.creator);
        if(this.creatorEq!=null)
        	ht.put("creatorEq",this.creatorEq);
        if(this.createTime!=null)
        	ht.put("createTime",this.createTime);
        if(this.createTimeFrom!=null)
        	ht.put("createTimeFrom",this.createTimeFrom);
        if(this.createTimeTo!=null)
        	ht.put("createTimeTo",this.createTimeTo);
        if(this.modifier!=null)
        	ht.put("modifier",this.modifier);
        if(this.modifierEq!=null)
        	ht.put("modifierEq",this.modifierEq);
        if(this.modifyTime!=null)
        	ht.put("modifyTime",this.modifyTime);
        if(this.modifyTimeFrom!=null)
        	ht.put("modifyTimeFrom",this.modifyTimeFrom);
        if(this.modifyTimeTo!=null)
        	ht.put("modifyTimeTo",this.modifyTimeTo);
		return ht;
	}	
	
}
