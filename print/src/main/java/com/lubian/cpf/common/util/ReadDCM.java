package com.lubian.cpf.common.util;

import java.io.File;
import java.util.Date;

import org.dicom4j.data.elements.CodeString;
import org.dicom4j.data.elements.DateElement;
import org.dicom4j.data.elements.LongString;
import org.dicom4j.data.elements.PersonName;
import org.dicom4j.data.elements.ShortString;
import org.dicom4j.data.elements.UniqueIdentifier;
import org.dicom4j.dicom.DicomTags;
import org.dicom4j.io.media.DicomFile;

import com.lubian.cpf.common.DCMModel;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.vo.CpfCaseHistory;

public class ReadDCM {
	/**
	 * 读取dcm文件
	 * 
	 * @param path
	 *            文件路劲
	 * @return
	 */
	public static DCMModel readDcm(String path) {
		

			DCMModel m = new DCMModel();
			DicomFile file = new DicomFile(path);
			try {
				file.open();
				ShortString uid = file.getDataset().getShortString(DicomTags.UID);
				if (uid != null) {
					m.setUID(uid.toString());
				}
				ShortString insurance = file.getDataset().getShortString(DicomTags.InsurancePlanIdentificationRetired);
				if (insurance != null) {
					m.setInsurancePlanIdentificationRetired(insurance.toString());
				}
				UniqueIdentifier studyUID = file.getDataset().getUniqueIdentifier(DicomTags.StudyInstanceUID);
				if (studyUID != null) {
					m.setStudyUiD(studyUID.toString());
				}
				ShortString studyID = file.getDataset().getShortString(DicomTags.StudyID);
				if (studyID != null) {
					m.setStudyID(studyID.toString());
				}
				LongString patientID = file.getDataset().getLongString(DicomTags.PatientID);
				if (patientID != null) {
					m.setPatientID(patientID.toString());
				}
				PersonName patientName = file.getDataset().getPersonName(DicomTags.PatientName);
				if (patientName != null) {
					m.setPatientName(patientName.toString());
					m.setCaseName(patientName.toString());
				}
				CodeString patinetSex = file.getDataset().getCodeString(DicomTags.PatientSex);
				if (patinetSex != null) {
					m.setPatinetSex(patinetSex.toString());
				}
				LongString patinetMobile = file.getDataset().getLongString(DicomTags.PatientPhoneNumbers);
				if (patinetMobile != null) {
					m.setPatinetMobile(patinetMobile.toString());
				}
				DateElement date = file.getDataset().getDate(DicomTags.StudyDate);
				if (date != null) {
					m.setDate(DateUtil.getDate(date.toString()));
				}
				m.setPath(path);

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					file.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return m;
		
	}

	public static CpfCaseHistory read(File files) {
		DicomFile file = new DicomFile(files);
		CpfCaseHistory caseHistory = new CpfCaseHistory();
		try {
			file.open();

			UniqueIdentifier studyID = file.getDataset().getUniqueIdentifier(DicomTags.StudyInstanceUID);// study
			if (studyID != null) {
				caseHistory.setStudy(studyID.toString());
			}
			ShortString study = file.getDataset().getShortString(DicomTags.StudyID);
			if (studyID != null) {
				caseHistory.setPn(study.toString());
			}
			LongString patientID = file.getDataset().getLongString(DicomTags.PatientID);// pid
			if (patientID != null) {
				caseHistory.setPid(patientID.toString());
			}
			DateElement date = file.getDataset().getDate(DicomTags.StudyDate);
			if (date != null) {
				caseHistory.setCaseDate(DateUtil.getDate(date.toString()));// 就诊时间
			}
			PersonName patientName = file.getDataset().getPersonName(DicomTags.PatientName);
			if (patientName != null) {
				caseHistory.setCaseName(patientName.toString());
			}
			caseHistory.setCaseType(Enums.CaseType.OTHER);// 病例类型
			caseHistory.setNilUrl(files.getPath());
			caseHistory.setCreateTime(new Date());
			caseHistory.setRelateStatus(Enums.isYesOrIsNo.IS_YES);//是否可见
			caseHistory.setViewStatus(Enums.isYesOrIsNo.IS_NO);//是否可见
			caseHistory.setSource(Enums.Relation.HOSPITAL_DATA);// 添加本地资料
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				file.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return caseHistory;
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}
}
