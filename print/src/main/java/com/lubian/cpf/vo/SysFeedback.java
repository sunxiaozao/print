package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysFeedback
 * @author:Lusir<lusire@gmail.com>
 */
public class SysFeedback implements java.io.Serializable{
  
	private java.lang.Integer id;
	
	public java.lang.Integer getId() {
		return id;
	}
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	


  
	private java.lang.Integer userId;
	
	public java.lang.Integer getUserId() {
		return userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
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


  
	private java.util.Date createDt;
	
	public java.util.Date getCreateDt() {
		return createDt;
	}
	
	public void setCreateDt(java.util.Date createDt) {
		this.createDt = createDt;
	}
	


  
	private java.lang.String deviceId;
	
	public java.lang.String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(java.lang.String deviceId) {
		this.deviceId = deviceId;
	}
	
	private String deviceIdEq;

	public String getDeviceIdEq() {
		return deviceIdEq;
	}
	
	public void setDeviceIdEq(String deviceId) {
		this.deviceIdEq = deviceId;
	}


  
	private java.lang.String mac;
	
	public java.lang.String getMac() {
		return mac;
	}
	
	public void setMac(java.lang.String mac) {
		this.mac = mac;
	}
	
	private String macEq;

	public String getMacEq() {
		return macEq;
	}
	
	public void setMacEq(String mac) {
		this.macEq = mac;
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
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.id!=null)
        	ht.put("id",this.id);
        if(this.userId!=null)
        	ht.put("userId",this.userId);
        if(this.content!=null)
        	ht.put("content",this.content);
        if(this.contentEq!=null)
        	ht.put("contentEq",this.contentEq);
        if(this.createDt!=null)
        	ht.put("createDt",this.createDt);
        if(this.createDtFrom!=null)
        	ht.put("createDtFrom",this.createDtFrom);
        if(this.createDtTo!=null)
        	ht.put("createDtTo",this.createDtTo);
        if(this.deviceId!=null)
        	ht.put("deviceId",this.deviceId);
        if(this.deviceIdEq!=null)
        	ht.put("deviceIdEq",this.deviceIdEq);
        if(this.mac!=null)
        	ht.put("mac",this.mac);
        if(this.macEq!=null)
        	ht.put("macEq",this.macEq);
		return ht;
	}	
	
}
