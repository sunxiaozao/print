package com.lubian.cpf.vo;

import java.util.*;

/**
 * CpfReply
 * @author:Lusir<lusire@gmail.com>
 */
public class CpfReply implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	


  
	private java.lang.Integer notebookId;
	
	public java.lang.Integer getNotebookId() {
		return notebookId;
	}
	
	public void setNotebookId(java.lang.Integer notebookId) {
		this.notebookId = notebookId;
	}
	

	private String notebookId_instr;

	public String getNotebookId_instr() {
		return notebookId_instr;
	}
	
	public void setNotebookId_instr(String notebookId) {
		this.notebookId_instr = notebookId;
	}

  
	private java.lang.Integer patientId;
	
	public java.lang.Integer getPatientId() {
		return patientId;
	}
	
	public void setPatientId(java.lang.Integer patientId) {
		this.patientId = patientId;
	}
	


  
	private java.lang.Integer doctorId;
	
	public java.lang.Integer getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
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


  
	private java.util.Date replyTime;
	
	public java.util.Date getReplyTime() {
		return replyTime;
	}
	
	public void setReplyTime(java.util.Date replyTime) {
		this.replyTime = replyTime;
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


  
	private java.lang.Integer replyCount;
	
	public java.lang.Integer getReplyCount() {
		return replyCount;
	}
	
	public void setReplyCount(java.lang.Integer replyCount) {
		this.replyCount = replyCount;
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
	  

	private java.util.Date replyTimeFrom;
	
	public java.util.Date getReplyTimeFrom() {
		return replyTimeFrom;
	}
	
	public void setReplyTimeFrom(java.util.Date replyTimeFrom) {
		this.replyTimeFrom = replyTimeFrom;
	}
		
	private java.util.Date replyTimeTo;
	
	public java.util.Date getReplyTimeTo() {
		return replyTimeTo;
	}
	
	public void setReplyTimeTo(java.util.Date replyTimeTo) {
		this.replyTimeTo = replyTimeTo;
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
        if(this.notebookId!=null)
        	ht.put("notebookId",this.notebookId);
        if(this.notebookId_instr!=null)
        	ht.put("notebookId_instr",this.notebookId_instr);
        if(this.patientId!=null)
        	ht.put("patientId",this.patientId);
        if(this.doctorId!=null)
        	ht.put("doctorId",this.doctorId);
        if(this.content!=null)
        	ht.put("content",this.content);
        if(this.contentEq!=null)
        	ht.put("contentEq",this.contentEq);
        if(this.replyTime!=null)
        	ht.put("replyTime",this.replyTime);
        if(this.replyTimeFrom!=null)
        	ht.put("replyTimeFrom",this.replyTimeFrom);
        if(this.replyTimeTo!=null)
        	ht.put("replyTimeTo",this.replyTimeTo);
        if(this.status!=null)
        	ht.put("status",this.status);
        if(this.statusEq!=null)
        	ht.put("statusEq",this.statusEq);
        if(this.replyCount!=null)
        	ht.put("replyCount",this.replyCount);
		return ht;
	}	
	
}
