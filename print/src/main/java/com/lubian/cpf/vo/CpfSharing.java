package com.lubian.cpf.vo;

import java.util.*;

/**
 * CpfSharing
 * @author:Lusir<lusire@gmail.com>
 */
public class CpfSharing implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	


  
	private java.lang.Integer shareId;
	
	public java.lang.Integer getShareId() {
		return shareId;
	}
	
	public void setShareId(java.lang.Integer shareId) {
		this.shareId = shareId;
	}
	


  
	private java.lang.Integer doctorId;
	
	public java.lang.Integer getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
	}
	


  
	private java.util.Date createTime;
	
	public java.util.Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
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
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.id!=null)
        	ht.put("id",this.id);
        if(this.shareId!=null)
        	ht.put("shareId",this.shareId);
        if(this.doctorId!=null)
        	ht.put("doctorId",this.doctorId);
        if(this.createTime!=null)
        	ht.put("createTime",this.createTime);
        if(this.createTimeFrom!=null)
        	ht.put("createTimeFrom",this.createTimeFrom);
        if(this.createTimeTo!=null)
        	ht.put("createTimeTo",this.createTimeTo);
		return ht;
	}	
	
}
