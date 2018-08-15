package com.lubian.cpf.vo;

import java.util.*;

/**
 * CdfFavorite
 * @author:Lusir<lusire@gmail.com>
 */
public class CdfFavorite implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	


  
	private java.lang.Integer doctorId;
	
	public java.lang.Integer getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(java.lang.Integer doctorId) {
		this.doctorId = doctorId;
	}
	


  
	private java.lang.Integer caseId;
	
	public java.lang.Integer getCaseId() {
		return caseId;
	}
	
	public void setCaseId(java.lang.Integer caseId) {
		this.caseId = caseId;
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


  
	private java.util.Date favoriteTime;
	
	public java.util.Date getFavoriteTime() {
		return favoriteTime;
	}
	
	public void setFavoriteTime(java.util.Date favoriteTime) {
		this.favoriteTime = favoriteTime;
	}
	


  
	private java.lang.String reason;
	
	public java.lang.String getReason() {
		return reason;
	}
	
	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}
	
	private String reasonEq;

	public String getReasonEq() {
		return reasonEq;
	}
	
	public void setReasonEq(String reason) {
		this.reasonEq = reason;
	}


  
	private java.lang.Integer bookmarkId;
	
	public java.lang.Integer getBookmarkId() {
		return bookmarkId;
	}
	
	public void setBookmarkId(java.lang.Integer bookmarkId) {
		this.bookmarkId = bookmarkId;
	}
	


      
	private ArrayList caseHistory = new ArrayList();
	//relation=one-to-one
	
	    
	
	        
	private ArrayList cdfBookmark = new ArrayList();
	//relation=one-to-one
	public CdfBookmark getCdfBookmark() {
		CdfBookmark ret = null;
	    if(this.cdfBookmark!=null&&this.cdfBookmark.size()>0){
	    	ret = (CdfBookmark)this.cdfBookmark.get(0);
	    }
	    return ret;
	}
	    
	public void setCdfBookmark(CdfBookmark vObj ) {
	    if(vObj==null)return;
	    if(this.cdfBookmark==null){
	    	this.cdfBookmark = new ArrayList();
	    }else{
	    	this.cdfBookmark.clear();
	    }
	    this.cdfBookmark.add(vObj);
	}
	  

	private java.util.Date favoriteTimeFrom;
	
	public java.util.Date getFavoriteTimeFrom() {
		return favoriteTimeFrom;
	}
	
	public void setFavoriteTimeFrom(java.util.Date favoriteTimeFrom) {
		this.favoriteTimeFrom = favoriteTimeFrom;
	}
		
	private java.util.Date favoriteTimeTo;
	
	public java.util.Date getFavoriteTimeTo() {
		return favoriteTimeTo;
	}
	
	public void setFavoriteTimeTo(java.util.Date favoriteTimeTo) {
		this.favoriteTimeTo = favoriteTimeTo;
	}


	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.id!=null)
        	ht.put("id",this.id);
        if(this.doctorId!=null)
        	ht.put("doctorId",this.doctorId);
        if(this.caseId!=null)
        	ht.put("caseId",this.caseId);
        if(this.type!=null)
        	ht.put("type",this.type);
        if(this.typeEq!=null)
        	ht.put("typeEq",this.typeEq);
        if(this.favoriteTime!=null)
        	ht.put("favoriteTime",this.favoriteTime);
        if(this.favoriteTimeFrom!=null)
        	ht.put("favoriteTimeFrom",this.favoriteTimeFrom);
        if(this.favoriteTimeTo!=null)
        	ht.put("favoriteTimeTo",this.favoriteTimeTo);
        if(this.reason!=null)
        	ht.put("reason",this.reason);
        if(this.reasonEq!=null)
        	ht.put("reasonEq",this.reasonEq);
        if(this.bookmarkId!=null)
        	ht.put("bookmarkId",this.bookmarkId);
		return ht;
	}	
	
}
