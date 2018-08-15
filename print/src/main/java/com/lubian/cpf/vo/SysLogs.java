package com.lubian.cpf.vo;

import java.util.*;

/**
 * SysLogs
 * @author:Lusir<lusire@gmail.com>
 */
public class SysLogs implements java.io.Serializable{
  
	private java.lang.Integer logId;
	
	public java.lang.Integer getLogId() {
		return logId;
	}
	
	public void setLogId(java.lang.Integer logId) {
		this.logId = logId;
	}
	


  
	private java.lang.Integer userId;
	
	public java.lang.Integer getUserId() {
		return userId;
	}
	
	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}
	


  
	private java.lang.String userName;
	
	public java.lang.String getUserName() {
		return userName;
	}
	
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
	private String userNameEq;

	public String getUserNameEq() {
		return userNameEq;
	}
	
	public void setUserNameEq(String userName) {
		this.userNameEq = userName;
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


  
	private java.util.Date cdate;
	
	public java.util.Date getCdate() {
		return cdate;
	}
	
	public void setCdate(java.util.Date cdate) {
		this.cdate = cdate;
	}
	


  
	private java.lang.String ip;
	
	public java.lang.String getIp() {
		return ip;
	}
	
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}
	
	private String ipEq;

	public String getIpEq() {
		return ipEq;
	}
	
	public void setIpEq(String ip) {
		this.ipEq = ip;
	}


  
	private java.lang.String location;
	
	public java.lang.String getLocation() {
		return location;
	}
	
	public void setLocation(java.lang.String location) {
		this.location = location;
	}
	
	private String locationEq;

	public String getLocationEq() {
		return locationEq;
	}
	
	public void setLocationEq(String location) {
		this.locationEq = location;
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


  
	private java.lang.Integer functionId;
	
	public java.lang.Integer getFunctionId() {
		return functionId;
	}
	
	public void setFunctionId(java.lang.Integer functionId) {
		this.functionId = functionId;
	}
	


  
	private java.lang.String functionName;
	
	public java.lang.String getFunctionName() {
		return functionName;
	}
	
	public void setFunctionName(java.lang.String functionName) {
		this.functionName = functionName;
	}
	
	private String functionNameEq;

	public String getFunctionNameEq() {
		return functionNameEq;
	}
	
	public void setFunctionNameEq(String functionName) {
		this.functionNameEq = functionName;
	}




	private java.util.Date cdateFrom;
	
	public java.util.Date getCdateFrom() {
		return cdateFrom;
	}
	
	public void setCdateFrom(java.util.Date cdateFrom) {
		this.cdateFrom = cdateFrom;
	}
		
	private java.util.Date cdateTo;
	
	public java.util.Date getCdateTo() {
		return cdateTo;
	}
	
	public void setCdateTo(java.util.Date cdateTo) {
		this.cdateTo = cdateTo;
	}

    public void fixup(){
    }

	//be carefull of the case,key is the original name
	public HashMap toHashMap() {
        HashMap ht = new HashMap();
        if(this.logId!=null)
        	ht.put("logId",this.logId);
        if(this.userId!=null)
        	ht.put("userId",this.userId);
        if(this.userName!=null)
        	ht.put("userName",this.userName);
        if(this.userNameEq!=null)
        	ht.put("userNameEq",this.userNameEq);
        if(this.description!=null)
        	ht.put("description",this.description);
        if(this.descriptionEq!=null)
        	ht.put("descriptionEq",this.descriptionEq);
        if(this.cdate!=null)
        	ht.put("cdate",this.cdate);
        if(this.cdateFrom!=null)
        	ht.put("cdateFrom",this.cdateFrom);
        if(this.cdateTo!=null)
        	ht.put("cdateTo",this.cdateTo);
        if(this.ip!=null)
        	ht.put("ip",this.ip);
        if(this.ipEq!=null)
        	ht.put("ipEq",this.ipEq);
        if(this.location!=null)
        	ht.put("location",this.location);
        if(this.locationEq!=null)
        	ht.put("locationEq",this.locationEq);
        if(this.type!=null)
        	ht.put("type",this.type);
        if(this.typeEq!=null)
        	ht.put("typeEq",this.typeEq);
        if(this.functionId!=null)
        	ht.put("functionId",this.functionId);
        if(this.functionName!=null)
        	ht.put("functionName",this.functionName);
        if(this.functionNameEq!=null)
        	ht.put("functionNameEq",this.functionNameEq);
		return ht;
	}	
	
}
