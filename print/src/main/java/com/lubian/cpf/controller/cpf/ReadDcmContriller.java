package com.lubian.cpf.controller.cpf;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lubian.cpf.common.DCMModel;
import com.lubian.cpf.common.annotation.NeedLogin;
import com.lubian.cpf.common.basedata.SysLogsUtil;
import com.lubian.cpf.common.constant.Enums;
import com.lubian.cpf.common.sms.SMSSendService;
import com.lubian.cpf.common.util.Config;
import com.lubian.cpf.common.util.DateUtil;
import com.lubian.cpf.common.util.Encrypt;
import com.lubian.cpf.common.util.ReadDCM;
import com.lubian.cpf.common.util.SessionUtil;
import com.lubian.cpf.common.util.StringUtils;
import com.lubian.cpf.service.cpf.CpfCaseHistoryService;
import com.lubian.cpf.service.cpf.CpfPatientRelationUserService;
import com.lubian.cpf.service.cpf.CpfPatientService;
import com.lubian.cpf.service.sys.SysDoctorService;
import com.lubian.cpf.service.sys.SysHospitalService;
import com.lubian.cpf.vo.CpfCaseHistory;
import com.lubian.cpf.vo.CpfPatient;
import com.lubian.cpf.vo.CpfPatientRelationUser;
import com.lubian.cpf.vo.SysDoctor;
import com.lubian.cpf.vo.SysHospital;
import com.lubian.cpf.vo.SysUser;

@NeedLogin
@Controller
@RequestMapping("/import/casehistory")
public class ReadDcmContriller {
	@Autowired
	private CpfPatientService patientService;
	@Autowired
	private SysDoctorService doctorService;
	@Autowired
	private CpfPatientRelationUserService preService;
	@Autowired
	private CpfCaseHistoryService caseHistoryService;
	@Autowired
	private SysHospitalService hospitalService;

	@Autowired
	private SMSSendService sMSSendService;// 发送信息
	private static final Logger logger = Logger.getLogger(ReadDcmContriller.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("/readdcm")
	public String readDcm(HttpServletRequest request, Model model) {

		List<DCMModel> d = new ArrayList<DCMModel>();
		int count = 0;
		// 获得登录人的信息
		SysUser user = SessionUtil.getUser(request);
		SysDoctor doctor = doctorService.findDoctorByUserId(user.getUserId());

		SysHospital hospital = new SysHospital();
		hospital.setId(doctor.getHospitalId());
		hospital = hospitalService.findByPK(hospital);

		// 获取读取文件夹
		String date = DateUtil.getDateStr(new Date(), "yyyyMMdd");
		String path = Config.getProperty("dcm_path") + hospital.getHospitalId() + "/" + date + "/";

		/**
		 * 1获得文件
		 */
		List<String> subFiles = getFiel(path);
		List<CpfCaseHistory> caseHistorys = new ArrayList<>();
		for (String string : subFiles) {
			// 2获得文件信息
			DCMModel md = ReadDCM.readDcm(string);
			if (md != null) {

				CpfPatient patient = patientService.find(md.getUserName(), null);// 3查询该病人存不存在
				if (patient == null) {// 如果不存在则,创建
					if ((md.getUserName() != null && md.getUserName().length() >= 6) && (md.getPwd() != null && md.getPwd().length() >= 6)) {

						patient = new CpfPatient();
						patient.setUserName(md.getUserName());// 登录名
						patient.setPassword(Encrypt.md5(md.getPwd()));// 登录密码
						patient.setCreateTime(new Date());
						patient.setSex(md.getPatinetSex());
						patient.setMobile(md.getPatinetMobile());
						patient.setFullname(md.getPatientName());
						patient.setIcId(md.getUID());
						patient.setCardId(md.getInsurancePlanIdentificationRetired());
						if (doctor != null) {
							patient.setHospitalId(doctor.getHospitalId());
						}
						patient.setCreator(user.getUserName());
						patientService.insert(patient);
						CpfPatientRelationUser u = new CpfPatientRelationUser();
						u.setPatientId(patient.getId());
						u.setRelationPatientId(patient.getId());
						SysLogsUtil.saveLogs("添加病人", "用户" + user.getUserName() + "添加了一个病人", request);
						preService.insert(u);

						CpfCaseHistory caseHistory = new CpfCaseHistory();
						caseHistory.setPatientId(patient.getId());
						caseHistory.setCaseName(md.getCaseName());
						caseHistory.setCaseType(md.getCaseType());
						caseHistory.setCaseDate(md.getDate());
						caseHistory.setStudy(md.getStudyUiD());
						caseHistory.setPn(md.getStudyID());
						caseHistory.setPid(md.getPatientID());
						caseHistory.setNilUrl(md.getPath());
						caseHistory.setCreator(user.getUserName());
						caseHistory.setCreateTime(new Date());
						caseHistory.setHospitalId(doctor.getHospitalId());
						caseHistory.setViewStatus(Enums.isYesOrIsNo.IS_NO);// 是否可见
						caseHistory.setCreateTime(new Date());
						caseHistory.setRelateStatus(Enums.isYesOrIsNo.IS_YES);
						caseHistory.setSource(Enums.Relation.TEMPORARY_DATA);
						caseHistoryService.insert(caseHistory);
						caseHistory.setCpfPatient(patient);
						caseHistorys.add(caseHistory);
						StringBuffer mobileContent = new StringBuffer();
						mobileContent.append("【厦门新夏宇科技有限公司】");
						mobileContent.append(hospital.getName() + "为你开通了微医通账号,");
						mobileContent.append("账号名:" + md.getUserName());
						mobileContent.append("密码:" + md.getPwd());
						mobileContent.append("你可以登录:http://www.yunjiaopian.com来查看你的病例资料");
						sendSMS(patient.getId(), patient.getMobile(), mobileContent.toString());

						count++;
					}
					d.add(md);

				} else {// 如果存在则添加病例
					if (null==caseHistoryService.find(md.getStudyUiD(), md.getPatientID(),patient.getId().toString(),null)) {
						CpfCaseHistory caseHistory = new CpfCaseHistory();
						caseHistory.setPatientId(patient.getId());
						caseHistory.setCaseName(md.getCaseName());
						caseHistory.setCaseType(md.getCaseType());
						caseHistory.setCaseDate(md.getDate());
						caseHistory.setStudy(md.getStudyUiD());
						caseHistory.setPid(md.getPatientID());
						caseHistory.setPn(md.getStudyID());
						caseHistory.setCreator(user.getUserName());
						caseHistory.setCreateTime(new Date());
						caseHistory.setNilUrl(md.getPath());
						caseHistory.setHospitalId(doctor.getHospitalId());
						caseHistory.setViewStatus(Enums.isYesOrIsNo.IS_NO);// 是否可见
						caseHistory.setCreateTime(new Date());
						caseHistory.setRelateStatus(Enums.isYesOrIsNo.IS_YES);
						caseHistory.setSource(Enums.Relation.TEMPORARY_DATA);
						caseHistory.setSource(Enums.Relation.TEMPORARY_DATA);
						caseHistoryService.insert(caseHistory);
						caseHistory.setCpfPatient(patient);
						caseHistorys.add(caseHistory);
						count++;

						StringBuffer mobileContent = new StringBuffer();
						mobileContent.append("【厦门新夏宇科技有限公司】");
						mobileContent.append("您有新的病例资料导入,请登录微医通系统查看");
						sendSMS(patient.getId(), patient.getMobile(), mobileContent.toString());
					}

				}

				SysLogsUtil.saveLogs("病例资料导入", "用户" + user.getUserName() + "导入一条病例资料", request);
			}
		}
		model.addAttribute("count", count);
		model.addAttribute("ucount", d.size());

		int counts = d != null ? d.size() : 0;

		model.addAttribute("error", "新增用户" + counts + "个!导入病历资料" + count);
		model.addAttribute("dcm", d);
		model.addAttribute("caseHistorys", caseHistorys);
		return "tiles.doctor.import";
	}

	private void sendSMS(Integer userId, String strPhone, String strContent) {
		if (!StringUtils.isBlank(strPhone)) {
			try {
				sMSSendService.sendSMS(userId, strPhone, strContent);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public List<String> getFiel(String path) {
		List<String> l = new ArrayList<String>();
		File file = new File(path);
		File[] files = file.listFiles();
		if (files != null) {

			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					l.addAll(getFiel(files[i].getAbsolutePath()));
				} else {
					String strFileName = files[i].getAbsolutePath().toLowerCase();

					l.add(strFileName);
				}
			}
		} else {
			l.add(path);
		}

		return l;
	}
}
