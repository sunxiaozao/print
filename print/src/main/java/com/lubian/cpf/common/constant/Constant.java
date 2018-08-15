package com.lubian.cpf.common.constant;

public class Constant {
	
	//docm文件上传路径
	public final static String DOCM_UPLOAD_PATH = "dcm_path";
	
	//http://www.yunjiaopian.com
	public final static String ADDRESS = "http://www.yunjiaopian.com/member/share/noLogin";
	
	public final static String FIND_PASS_ADDRESS = "http://www.yunjiaopian.com/admin/findPass";
	//public final static String ADDRESS = "http://localhost:8080/cpf/member/share/noLogin";
	//public final static String FIND_PASS_ADDRESS = "http://localhost:8080/cpf/admin/findPass";
	
	// 2SC 商户id
	public final static Integer AGENCY_ID = -1;

	public static String ROOT_PATH = "";
	public final static String USER_SESSION = "USER_SESSION";
	public final static String PATIENT_SESSION="PATIENT_SESSION";
	
	
	public final static String USER_MENUS = "USER_SESSION_MENUS";
	public final static int DEFAULT_PAGESIZE = 20;

	public final static String UPLOAD_PATH = "file_servie_dir";
	public final static String IMAGE_CONTEXT = "image_context";
	public final static String STATIC_CONTEXT = "static_context";
	public static final String DOWNLOAD_DIR = "download_dir";
	public final static String UPLOAD_IMAGE_PATH = "images/up/";

	public final static String EXPORT_PATH = "images/export/";// 导出文件所在地址
	public final static String IMPORT_PATH = "images/import/";// 上传文件所在地址

	public final static String IMG_PATH="img_path";
	
	public final static String PDF_PATH="export/pdf/"; 
	public final static String EXCEL_CASE_DATA_PATH="exceltemp/casedatatemp.xls";//病例资料模板
	public final static String EXCEL_PATIENT_DATA_PATH="exceltemp/patienttemp.xls";//病人模板
	public final static String EXCEL_DOCTOR_DATA_PATH="exceltemp/doctortemp.xls";//病人模板
	
	
	
	public final static String KAPTCHA_SESSION_KEY = "USS_KAPTCHA_SESSION_KEY";
	public final static String USERNAME_COOKIE = "USSLogU";
	public final static String USERPASS_COOKIE = "USSLogP";
	public final static String LASTPAGE_COOKIE = "lastPage";

	public final static String COOKIE_MENU_CODE = "selectedMenu";
	public final static String SEL_MENU_CAT = "selectedMenuCat";
	public final static String SEL_MENU_ITEM = "selectedMenuItem";

	// 平安银行配置信息
	public final static String PINAN_STANDARD_CODE = "pinan.standardCode";
	public final static String PINAN_ACCT_NO = "pinan.acctNo";
	public final static String PINAN_SERVER_IP = "pinan.server.ip";
	public final static String PINAN_SERVER_PORT = "pinan.server.port";
	// 浦发银行配置信息
	public final static String PUFA_MASTER_ID = "pufa.masterID";
	public final static String PUFA_ACCT_NO = "pufa.acctNo";
	public final static String PUFA_SERVER_IP = "pufa.server.ip";
	public final static String PUFA_SERVER_PORT = "pufa.server.port";
	public final static String PUFA_SERVER_SIGN_PORT = "pufa.server.sign.port";
	// 短信发送相关
	public final static String SMS_SEND_URL = "sms_send_url";
	public final static String SMS_BALANCE_URL = "sms_balance_url";
	public final static String SMS_REG_USER = "sms_reg_user";
	public final static String SMS_REG_PASS = "sms_reg_pass";
	public final static String SMS_SOURCE_ADD = "sms_source_add";
	// 接受银联POS信息的Scoket端口
	public final static String POS_SOCKET_SERVER_PORT = "pos.socket.server.port";
	//短信验证码有限时间
	public final static long TOKEN_VALID_TIME = 1800000;//1800,000半小时有效
	//默认的商户角色id
	public final static Integer AGENCY_ROLE_ID = 2;
	//当前用户是否有预支功能session
	public final static String SESSION_AGENCY_CAN_APPLY = "SESSION_AGENCY_CAN_APPLY";
	
	public final static String dcm_path="D:/cpf/dcm/";
	public final static String	personal_dcm_path="D:/cpf/personal/";
}
