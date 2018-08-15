package com.lubian.cpf.vo;

import java.util.*;

/**
 * CpfPatientRelationUser
 * @author:Lusir<lusire@gmail.com>
 */
public class CpfPatientRelationUser implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	


  
	private java.lang.Integer relationPatientId;
	
	public java.lang.Integer getRelationPatientId() {
		return relationPatientId;
	}
	
	public void setRelationPatientId(java.lang.Integer relationPatientId) {
		this.relationPatientId = relationPatientId;
	}
	


  
	private java.lang.Integer patientId;
	
	public java.lang.Integer getPatientId() {
		return patientId;
	}
	
	public void setPatientId(java.lang.Integer patientId) {
		this.patientId = patientId;
	}
	


  
	private java.lang.Integer userId;
	
	public java.lang.Integer getUserId() {
		return userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	


  
	private java.util.Date createDt;
	
	public java.util.Date getCreateDt() {
		return createDt;
	}
	
	public void setCreateDt(java.util.Date createDt) {
		this.createDt = createDt;
	}
	


  
	private java.lang.String type;
	
	public java.lang.String getType() {
		return type;
	}
	
	public void setType(java.lang.String type) {
		this.type = type;
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

    public void fixup(){
        if ( this.cpfPatient != null ) {
            for ( int i = 0, n = cpfPatient.size(); i < n; i++ ) { 
              CpfPatient oneCpfPatient=(CpfPatient) cpfPatient.get(i);
              oneCpfPatient.setId(this.relationPatientId);
              oneCpfPatient.fixup();
          }
      }
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.id!=null)
        	ht.put("id",this.id);
        if(this.relationPatientId!=null)
        	ht.put("relationPatientId",this.relationPatientId);
        if(this.patientId!=null)
        	ht.put("patientId",this.patientId);
        if(this.userId!=null)
        	ht.put("userId",this.userId);
        if(this.createDt!=null)
        	ht.put("createDt",this.createDt);
        if(this.createDtFrom!=null)
        	ht.put("createDtFrom",this.createDtFrom);
        if(this.createDtTo!=null)
        	ht.put("createDtTo",this.createDtTo);
        if(this.type!=null)
        	ht.put("type",this.type);
		return ht;
	}	
	
}
