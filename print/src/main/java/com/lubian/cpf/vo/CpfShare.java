package com.lubian.cpf.vo;

import java.util.*;

/**
 * CpfShare
 * @author:Lusir<lusire@gmail.com>
 */
public class CpfShare implements java.io.Serializable{
  
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

  
	private java.lang.Integer caseHistoryId;
	
	public java.lang.Integer getCaseHistoryId() {
		return caseHistoryId;
	}
	
	public void setCaseHistoryId(java.lang.Integer caseHistoryId) {
		this.caseHistoryId = caseHistoryId;
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


  
	private java.lang.String email;
	
	public java.lang.String getEmail() {
		return email;
	}
	
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	
	private String emailEq;

	public String getEmailEq() {
		return emailEq;
	}
	
	public void setEmailEq(String email) {
		this.emailEq = email;
	}


  
	private java.lang.String mobile;
	
	public java.lang.String getMobile() {
		return mobile;
	}
	
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	
	private String mobileEq;

	public String getMobileEq() {
		return mobileEq;
	}
	
	public void setMobileEq(String mobile) {
		this.mobileEq = mobile;
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


  
	private java.lang.String url;
	
	public java.lang.String getUrl() {
		return url;
	}
	
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	
	private String urlEq;

	public String getUrlEq() {
		return urlEq;
	}
	
	public void setUrlEq(String url) {
		this.urlEq = url;
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


  
	private java.lang.Integer creator;
	
	public java.lang.Integer getCreator() {
		return creator;
	}
	
	public void setCreator(java.lang.Integer creator) {
		this.creator = creator;
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
	        
	private ArrayList cpfCaseHistory = new ArrayList();
	//relation=one-to-one
	public CpfCaseHistory getCpfCaseHistory() {
		CpfCaseHistory ret = null;
	    if(this.cpfCaseHistory!=null&&this.cpfCaseHistory.size()>0){
	    	ret = (CpfCaseHistory)this.cpfCaseHistory.get(0);
	    }
	    return ret;
	}
	    
	public void setCpfCaseHistory(CpfCaseHistory vObj ) {
	    if(vObj==null)return;
	    if(this.cpfCaseHistory==null){
	    	this.cpfCaseHistory = new ArrayList();
	    }else{
	    	this.cpfCaseHistory.clear();
	    }
	    this.cpfCaseHistory.add(vObj);
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
        if ( this.cpfPatient != null ) {
            for ( int i = 0, n = cpfPatient.size(); i < n; i++ ) { 
              CpfPatient oneCpfPatient=(CpfPatient) cpfPatient.get(i);
              oneCpfPatient.setId(this.patientId);
              oneCpfPatient.fixup();
          }
      }
        if ( this.cpfCaseHistory != null ) {
            for ( int i = 0, n = cpfCaseHistory.size(); i < n; i++ ) { 
              CpfCaseHistory oneCpfCaseHistory=(CpfCaseHistory) cpfCaseHistory.get(i);
              oneCpfCaseHistory.setId(this.caseHistoryId);
              oneCpfCaseHistory.fixup();
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
        if(this.patientId!=null)
        	ht.put("patientId",this.patientId);
        if(this.patientId_instr!=null)
        	ht.put("patientId_instr",this.patientId_instr);
        if(this.caseHistoryId!=null)
        	ht.put("caseHistoryId",this.caseHistoryId);
        if(this.type!=null)
        	ht.put("type",this.type);
        if(this.typeEq!=null)
        	ht.put("typeEq",this.typeEq);
        if(this.email!=null)
        	ht.put("email",this.email);
        if(this.emailEq!=null)
        	ht.put("emailEq",this.emailEq);
        if(this.mobile!=null)
        	ht.put("mobile",this.mobile);
        if(this.mobileEq!=null)
        	ht.put("mobileEq",this.mobileEq);
        if(this.content!=null)
        	ht.put("content",this.content);
        if(this.contentEq!=null)
        	ht.put("contentEq",this.contentEq);
        if(this.url!=null)
        	ht.put("url",this.url);
        if(this.urlEq!=null)
        	ht.put("urlEq",this.urlEq);
        if(this.password!=null)
        	ht.put("password",this.password);
        if(this.passwordEq!=null)
        	ht.put("passwordEq",this.passwordEq);
        if(this.status!=null)
        	ht.put("status",this.status);
        if(this.statusEq!=null)
        	ht.put("statusEq",this.statusEq);
        if(this.creator!=null)
        	ht.put("creator",this.creator);
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
