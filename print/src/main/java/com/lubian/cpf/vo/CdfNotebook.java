package com.lubian.cpf.vo;

import java.util.*;

/**
 * CdfNotebook
 * @author:Lusir<lusire@gmail.com>
 */
public class CdfNotebook implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	

	private String id_instr;

	public String getId_instr() {
		return id_instr;
	}
	
	public void setId_instr(String id) {
		this.id_instr = id;
	}

  
	private java.lang.Integer caseHistoryId;
	
	public java.lang.Integer getCaseHistoryId() {
		return caseHistoryId;
	}
	
	public void setCaseHistoryId(java.lang.Integer caseHistoryId) {
		this.caseHistoryId = caseHistoryId;
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

  
	private java.lang.Integer doctorId;
	
	public java.lang.Integer getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
	}
	

	private String doctorId_instr;

	public String getDoctorId_instr() {
		return doctorId_instr;
	}
	
	public void setDoctorId_instr(String doctorId) {
		this.doctorId_instr = doctorId;
	}

  
	private java.lang.String content;
	
	public java.lang.String getContent() {
		return content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	private String contentEq;

	public String getContentEq() {
		return contentEq;
	}
	
	public void setContentEq(String content) {
		this.contentEq = content;
	}


  
	private java.util.Date createTime;
	
	public java.util.Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	


  
	private java.lang.Integer replyCount;
	
	public java.lang.Integer getReplyCount() {
		return replyCount;
	}
	
	public void setReplyCount(java.lang.Integer replyCount) {
		this.replyCount = replyCount;
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


      
	private ArrayList cpfPatient = new ArrayList();
	//relation=one-to-one
	public CpfPatient getCpfPatient() {
		CpfPatient ret = null;
	    if(this.cpfPatient!=null&&this.cpfPatient.size()>0){
	    	ret = (CpfPatient)this.cpfPatient.get(0);
	    }
	    return ret;
	}
	    
	public void setCpfPatient(CpfPatient vObj ) {
	    if(vObj==null)return;
	    if(this.cpfPatient==null){
	    	this.cpfPatient = new ArrayList();
	    }else{
	    	this.cpfPatient.clear();
	    }
	    this.cpfPatient.add(vObj);
	}
	        
	private ArrayList sysDoctor = new ArrayList();
	//relation=one-to-one
	public SysDoctor getSysDoctor() {
		SysDoctor ret = null;
	    if(this.sysDoctor!=null&&this.sysDoctor.size()>0){
	    	ret = (SysDoctor)this.sysDoctor.get(0);
	    }
	    return ret;
	}
	    
	public void setSysDoctor(SysDoctor vObj ) {
	    if(vObj==null)return;
	    if(this.sysDoctor==null){
	    	this.sysDoctor = new ArrayList();
	    }else{
	    	this.sysDoctor.clear();
	    }
	    this.sysDoctor.add(vObj);
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

    public void fixup(){
        if ( this.cpfPatient != null ) {
            for ( int i = 0, n = cpfPatient.size(); i < n; i++ ) { 
              CpfPatient oneCpfPatient=(CpfPatient) cpfPatient.get(i);
              oneCpfPatient.setId(this.patientId);
              oneCpfPatient.fixup();
          }
      }
        if ( this.sysDoctor != null ) {
            for ( int i = 0, n = sysDoctor.size(); i < n; i++ ) { 
              SysDoctor oneSysDoctor=(SysDoctor) sysDoctor.get(i);
              oneSysDoctor.setId(this.doctorId);
              oneSysDoctor.fixup();
          }
      }
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.id!=null)
        	ht.put("id",this.id);
        if(this.id_instr!=null)
        	ht.put("id_instr",this.id_instr);
        if(this.caseHistoryId!=null)
        	ht.put("caseHistoryId",this.caseHistoryId);
        if(this.patientId!=null)
        	ht.put("patientId",this.patientId);
        if(this.patientId_instr!=null)
        	ht.put("patientId_instr",this.patientId_instr);
        if(this.doctorId!=null)
        	ht.put("doctorId",this.doctorId);
        if(this.doctorId_instr!=null)
        	ht.put("doctorId_instr",this.doctorId_instr);
        if(this.content!=null)
        	ht.put("content",this.content);
        if(this.contentEq!=null)
        	ht.put("contentEq",this.contentEq);
        if(this.createTime!=null)
        	ht.put("createTime",this.createTime);
        if(this.createTimeFrom!=null)
        	ht.put("createTimeFrom",this.createTimeFrom);
        if(this.createTimeTo!=null)
        	ht.put("createTimeTo",this.createTimeTo);
        if(this.replyCount!=null)
        	ht.put("replyCount",this.replyCount);
        if(this.status!=null)
        	ht.put("status",this.status);
        if(this.statusEq!=null)
        	ht.put("statusEq",this.statusEq);
		return ht;
	}	
	
}
