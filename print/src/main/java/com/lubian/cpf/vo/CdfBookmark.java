package com.lubian.cpf.vo;

import java.util.*;

/**
 * CdfBookmark
 * @author:Lusir<lusire@gmail.com>
 */
public class CdfBookmark implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	


  
	private java.lang.String name;
	
	public java.lang.String getName() {
		return name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	private String nameEq;

	public String getNameEq() {
		return nameEq;
	}
	
	public void setNameEq(String name) {
		this.nameEq = name;
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
        if(this.name!=null)
        	ht.put("name",this.name);
        if(this.nameEq!=null)
        	ht.put("nameEq",this.nameEq);
        if(this.doctorId!=null)
        	ht.put("doctorId",this.doctorId);
        if(this.createTime!=null)
        	ht.put("createTime",this.createTime);
        if(this.createTimeFrom!=null)
        	ht.put("createTimeFrom",this.createTimeFrom);
        if(this.createTimeTo!=null)
        	ht.put("createTimeTo",this.createTimeTo);
        if(this.description!=null)
        	ht.put("description",this.description);
        if(this.descriptionEq!=null)
        	ht.put("descriptionEq",this.descriptionEq);
		return ht;
	}	
	
}
