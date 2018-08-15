package com.lubian.cpf.vo;

import java.util.*;

/**
 * CpfDownload
 * @author:Lusir<lusire@gmail.com>
 */
public class CpfDownload implements java.io.Serializable{
  
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


  
	private java.util.Date downTime;
	
	public java.util.Date getDownTime() {
		return downTime;
	}
	
	public void setDownTime(java.util.Date downTime) {
		this.downTime = downTime;
	}
	


  
	private java.lang.String downUrl;
	
	public java.lang.String getDownUrl() {
		return downUrl;
	}
	
	public void setDownUrl(java.lang.String downUrl) {
		this.downUrl = downUrl;
	}
	
	private String downUrlEq;

	public String getDownUrlEq() {
		return downUrlEq;
	}
	
	public void setDownUrlEq(String downUrl) {
		this.downUrlEq = downUrl;
	}




	private java.util.Date downTimeFrom;
	
	public java.util.Date getDownTimeFrom() {
		return downTimeFrom;
	}
	
	public void setDownTimeFrom(java.util.Date downTimeFrom) {
		this.downTimeFrom = downTimeFrom;
	}
		
	private java.util.Date downTimeTo;
	
	public java.util.Date getDownTimeTo() {
		return downTimeTo;
	}
	
	public void setDownTimeTo(java.util.Date downTimeTo) {
		this.downTimeTo = downTimeTo;
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
        if(this.type!=null)
        	ht.put("type",this.type);
        if(this.typeEq!=null)
        	ht.put("typeEq",this.typeEq);
        if(this.downTime!=null)
        	ht.put("downTime",this.downTime);
        if(this.downTimeFrom!=null)
        	ht.put("downTimeFrom",this.downTimeFrom);
        if(this.downTimeTo!=null)
        	ht.put("downTimeTo",this.downTimeTo);
        if(this.downUrl!=null)
        	ht.put("downUrl",this.downUrl);
        if(this.downUrlEq!=null)
        	ht.put("downUrlEq",this.downUrlEq);
		return ht;
	}	
	
}
