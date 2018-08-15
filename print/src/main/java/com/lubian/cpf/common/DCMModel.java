package com.lubian.cpf.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import freemarker.template.SimpleDate;

public class DCMModel {
	private String UID;// UID
	private String InsurancePlanIdentificationRetired;// 社保卡ID
	
	private String studyUiD;
	private String studyID;
	private String patientID;
	private String patientName;
	private String patinetSex;
	private String patinetMobile;
	private String caseType = "O";
	private String caseName;
	private String path;
	private Date date;
	public String getUID() {
		return UID;
	}

	public String getStudyUiD() {
		return studyUiD;
	}

	public void setStudyUiD(String studyUiD) {
		this.studyUiD = studyUiD;
	}

	public void setUID(String uID) {
		UID = uID;
	}

	public String getInsurancePlanIdentificationRetired() {
		return InsurancePlanIdentificationRetired;
	}

	public void setInsurancePlanIdentificationRetired(String insurancePlanIdentificationRetired) {
		InsurancePlanIdentificationRetired = insurancePlanIdentificationRetired;
	}

	public String getStudyID() {
		return studyID;
	}

	public void setStudyID(String studyID) {
		this.studyID = studyID;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatinetSex() {
		return patinetSex;
	}

	public void setPatinetSex(String patinetSex) {
		this.patinetSex = !"F".equals(patinetSex) ? "W" : "M";
	}

	public String getPatinetMobile() {
		return patinetMobile;
	}

	public void setPatinetMobile(String patinetMobile) {
		this.patinetMobile = patinetMobile;
	}

	public String getUserName() {
		if (this.UID != null) {
			return this.UID;
		} else if (this.InsurancePlanIdentificationRetired != null) {
			return this.InsurancePlanIdentificationRetired;
		} else if((this.patientID + this.studyID).replace(" ", "").length()>=6){
			return (this.patientID + this.studyID).replace(" ", "");
		}else{
			java.util.Date date=new java.util.Date ();
			SimpleDateFormat sd=new SimpleDateFormat("yyMMdd");

			return sd.format(date)+ (this.patientID + this.studyID).replace(" ", "");
		}

	}

	public String getPwd() {
		if (this.UID != null) {
			return this.UID.substring(this.UID.length() - 6);
		} else if (this.InsurancePlanIdentificationRetired != null) {
			return this.InsurancePlanIdentificationRetired.substring(this.InsurancePlanIdentificationRetired.length() - 6);
		} else {
			String password = (this.patientID + this.studyID).replace(" ", "");
			if (password != null&&password.length() >=6) {
				return password.substring(password.length() - 6);
			} else {
				java.util.Date date=new java.util.Date ();
				SimpleDateFormat sd=new SimpleDateFormat("yyMMdd");
				String pwd=sd.format(date)+ (this.patientID + this.studyID).replace(" ", "");
				return pwd.substring(pwd.length() - 6);
			}

		}

	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() {
		return "UID" + getUID() + "|InsurancePlanIdentificationRetired" + getInsurancePlanIdentificationRetired() + "|studyID" + getStudyID() + "|patientID" + getPatientID() + "|patientName" + getPatientName() + "|patientMobile" + getPatinetMobile() + "|patinetSex" + getPatinetSex() + "|caseName" + getCaseName() + "|caseType" + getCaseType() + "|userName" + getUserName() + "|pwe" + getPwd()+"|date"+getDate();
	}

}
