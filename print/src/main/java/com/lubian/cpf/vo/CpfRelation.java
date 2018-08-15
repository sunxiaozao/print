package com.lubian.cpf.vo;

import java.util.*;

/**
 * CpfRelation
 * @author:Lusir<lusire@gmail.com>
 */
public class CpfRelation implements java.io.Serializable{
  
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


  
	private java.lang.Integer relatePatientId;
	
	public java.lang.Integer getRelatePatientId() {
		return relatePatientId;
	}
	
	public void setRelatePatientId(java.lang.Integer relatePatientId) {
		this.relatePatientId = relatePatientId;
	}
	

	private String relatePatientId_instr;

	public String getRelatePatientId_instr() {
		return relatePatientId_instr;
	}
	
	public void setRelatePatientId_instr(String relatePatientId) {
		this.relatePatientId_instr = relatePatientId;
	}

  
	private java.lang.String password;
	
	public java.lang.String getPassword() {
		return password;
	}
	
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	
	private String passwordEq;

	public String getPasswordEq() {
		return passwordEq;
	}
	
	public void setPasswordEq(String password) {
		this.passwordEq = password;
	}


  
	private java.lang.String status;
	
	public java.lang.String getStatus() {
		return status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	private String statusEq;

	public String getStatusEq() {
		return statusEq;
	}
	
	public void setStatusEq(String status) {
		this.statusEq = status;
	}


  
	private java.util.Date applyTime;
	
	public java.util.Date getApplyTime() {
		return applyTime;
	}
	
	public void setApplyTime(java.util.Date applyTime) {
		this.applyTime = applyTime;
	}
	


  
	private java.lang.String applyReason;
	
	public java.lang.String getApplyReason() {
		return applyReason;
	}
	
	public void setApplyReason(java.lang.String applyReason) {
		this.applyReason = applyReason;
	}
	
	private String applyReasonEq;

	public String getApplyReasonEq() {
		return applyReasonEq;
	}
	
	public void setApplyReasonEq(String applyReason) {
		this.applyReasonEq = applyReason;
	}


  
	private java.util.Date checkTime;
	
	public java.util.Date getCheckTime() {
		return checkTime;
	}
	
	public void setCheckTime(java.util.Date checkTime) {
		this.checkTime = checkTime;
	}
	


      
	private ArrayList relatePatient = new ArrayList();
	//relation=one-to-one
	public CpfPatient getRelatePatient() {
		CpfPatient ret = null;
	    if(this.relatePatient!=null&&this.relatePatient.size()>0){
	    	ret = (CpfPatient)this.relatePatient.get(0);
	    }
	    return ret;
	}
	    
	public void setRelatePatient(CpfPatient vObj ) {
	    if(vObj==null)return;
	    if(this.relatePatient==null){
	    	this.relatePatient = new ArrayList();
	    }else{
	    	this.relatePatient.clear();
	    }
	    this.relatePatient.add(vObj);
	}
	  

	private java.util.Date applyTimeFrom;
	
	public java.util.Date getApplyTimeFrom() {
		return applyTimeFrom;
	}
	
	public void setApplyTimeFrom(java.util.Date applyTimeFrom) {
		this.applyTimeFrom = applyTimeFrom;
	}
		
	private java.util.Date applyTimeTo;
	
	public java.util.Date getApplyTimeTo() {
		return applyTimeTo;
	}
	
	public void setApplyTimeTo(java.util.Date applyTimeTo) {
		this.applyTimeTo = applyTimeTo;
	}

	private java.util.Date checkTimeFrom;
	
	public java.util.Date getCheckTimeFrom() {
		return checkTimeFrom;
	}
	
	public void setCheckTimeFrom(java.util.Date checkTimeFrom) {
		this.checkTimeFrom = checkTimeFrom;
	}
		
	private java.util.Date checkTimeTo;
	
	public java.util.Date getCheckTimeTo() {
		return checkTimeTo;
	}
	
	public void setCheckTimeTo(java.util.Date checkTimeTo) {
		this.checkTimeTo = checkTimeTo;
	}

    public void fixup(){
        if ( this.relatePatient != null ) {
            for ( int i = 0, n = relatePatient.size(); i < n; i++ ) { 
              CpfPatient oneCpfPatient=(CpfPatient) relatePatient.get(i);
              oneCpfPatient.setId(this.relatePatientId);
              oneCpfPatient.fixup();
          }
      }
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
        if(this.type!=null)
        	ht.put("type",this.type);
        if(this.typeEq!=null)
        	ht.put("typeEq",this.typeEq);
        if(this.relatePatientId!=null)
        	ht.put("relatePatientId",this.relatePatientId);
        if(this.relatePatientId_instr!=null)
        	ht.put("relatePatientId_instr",this.relatePatientId_instr);
        if(this.password!=null)
        	ht.put("password",this.password);
        if(this.passwordEq!=null)
        	ht.put("passwordEq",this.passwordEq);
        if(this.status!=null)
        	ht.put("status",this.status);
        if(this.statusEq!=null)
        	ht.put("statusEq",this.statusEq);
        if(this.applyTime!=null)
        	ht.put("applyTime",this.applyTime);
        if(this.applyTimeFrom!=null)
        	ht.put("applyTimeFrom",this.applyTimeFrom);
        if(this.applyTimeTo!=null)
        	ht.put("applyTimeTo",this.applyTimeTo);
        if(this.applyReason!=null)
        	ht.put("applyReason",this.applyReason);
        if(this.applyReasonEq!=null)
        	ht.put("applyReasonEq",this.applyReasonEq);
        if(this.checkTime!=null)
        	ht.put("checkTime",this.checkTime);
        if(this.checkTimeFrom!=null)
        	ht.put("checkTimeFrom",this.checkTimeFrom);
        if(this.checkTimeTo!=null)
        	ht.put("checkTimeTo",this.checkTimeTo);
		return ht;
	}	
	
}
