package com.lubian.cpf.common.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class Enums {

	// 关注状态
	public static interface relationStatus {
		String CONFIG = "A";// 待确认
		String CANCEL = "B";// 已取消
		String PASS = "C";// 已关注
	}

	public static Map relationStatuMap() {
		Map map = new LinkedHashMap();
		map.put(relationStatus.CANCEL, "已取消");
		map.put(relationStatus.PASS, "已关注");
		map.put(relationStatus.CONFIG, "待确认");
		return map;
	}

	// 分享类型
	public static interface shareType {
		String EMAIL = "A";// 邮箱
		String MOBILE = "B";// 短信
		String BOTH = "C";// 邮箱和短信
	}

	public static Map shareTypeMap() {
		Map map = new LinkedHashMap();
		map.put(shareType.EMAIL, "邮箱");
		map.put(shareType.MOBILE, "短信");
		map.put(shareType.BOTH, "邮箱、短信");
		return map;
	}

	public static interface isYesOrIsNo {
		String IS_YES = "Y";// 是的
		String IS_NO = "N";// 不是
	}

	// 查看状态
	public static Map viewStatuMap() {
		Map map = new LinkedHashMap();
		map.put(isYesOrIsNo.IS_YES, "已查看");
		map.put(isYesOrIsNo.IS_NO, "未查看");
		return map;
	}

	public static interface getParaCode {
		String SYS = "sys";// 系统级别参数
	}

	public static interface UserType {
		String ADMIN = "A";// 管理员
		String SUPER_DOCTOR = "S";// 超级医生
		String DOCTOR = "D";// 普通医生
		String REMOTE_DOCOR = "R";// 远程医生
		String MEMBER = "M";// 病人
	}

	public static Map getUserType() {
		Map map = new LinkedHashMap();
		map.put(UserType.ADMIN, "管理员");
		map.put(UserType.SUPER_DOCTOR, "超级医生");
		map.put(UserType.DOCTOR, "普通医生");
		map.put(UserType.REMOTE_DOCOR, "远程医生");
		map.put(UserType.MEMBER, "病人");
		return map;
	}

	public static Map getUserTypes() {
		Map map = new LinkedHashMap();
		map.put(UserType.ADMIN, "管理员");
		map.put(UserType.SUPER_DOCTOR, "超级医生");
		map.put(UserType.DOCTOR, "普通医生");
		map.put(UserType.MEMBER, "病人");
		return map;
	}

	public static Map getUserKey() {
		Map map = new LinkedHashMap();
		map.put("管理员", UserType.ADMIN);
		map.put("超级医生", UserType.SUPER_DOCTOR);
		map.put("普通医生", UserType.DOCTOR);
		map.put("病人", UserType.MEMBER);
		return map;
	}

	public static interface UMUserType {// UserMessage中的userType
		String ALLMEMBER = "A";// A所有会员
		String ORG = "B";// B为特定机构
		String GROUP = "C";// C为特定用户组
		String ONEMEMBER = "D";// D为特定会员
	}

	public static Map getUserTypeMap() {
		Map map = new LinkedHashMap();
		map.put("A", "所有会员");
		map.put("B", "特定机构");
		map.put("C", "特定用户组");
		map.put("D", "特定会员");
		return map;
	}

	public static interface DictType {
		String USER_TYPE = "USER_TYPE";// 用户类型
		String HOSPITAL_TYPE = "HOSPITAL_TYPE";// 医院类型
		String DEPARTMENT_TYPE = "DEPARTMENT_TYPE";// 科室类型
	}

	public static Map getDictTypeMap() {
		Map map = new LinkedHashMap();
		map.put(DictType.USER_TYPE, "用户类型");
		map.put(DictType.HOSPITAL_TYPE, "医院类型");
		map.put(DictType.DEPARTMENT_TYPE, "科室类型");
		return map;
	}

	public static interface LoginType {
		String IC = "I";// IC/ID
		String EMAIL = "E";// 邮箱
		String MOBILE = "M";// 手机
		String INITIAL = "N";// 初始化密码
		String USERNAME = "U";// 用户名
	}

	public static Map getLoginTypeMap() {
		Map map = new LinkedHashMap();
		map.put(LoginType.IC, "IC/ID");
		map.put(LoginType.EMAIL, "邮箱");
		map.put(LoginType.MOBILE, "手机");
		return map;
	}

	// 用户账户类型
	public static Map userTypeMap() {
		Map map = new LinkedHashMap();
		map.put(LoginType.IC, "IC/ID");
		map.put(LoginType.EMAIL, "邮箱");
		map.put(LoginType.MOBILE, "手机");
		map.put(LoginType.USERNAME, "用户名");
		return map;
	}

	public static interface Sex {
		String MAN = "M";// 男
		String WOMAN = "W";// 女
	}

	public static Map getSexMap() {
		Map map = new LinkedHashMap();
		map.put(Sex.MAN, "男");
		map.put(Sex.WOMAN, "女");
		return map;
	}

	public static Map setSexKey() {
		Map map = new LinkedHashMap();
		map.put("男", Sex.MAN);
		map.put("女", Sex.WOMAN);
		return map;
	}

	public static interface InitialPassword {
		String INITIAL_PASSWORD = "123456";
	}

	public static interface MailType {
		String SEND_NOW = "A"; // Send Emails Immediately
		String SPARE_TIME = "B"; // Send at spare time
	}

	public static interface MailJobStatus {
		String INIT = "A";
		String PROCESS = "B";
		String DONE = "C";
		String FAILURE = "D";
	}

	public static interface MailBizType {
		String CUSTOMIZE = "0"; // 自定义邮件，没有模板
		String TEMPLATE_TEST = "1"; // 模板邮件，测试
		String SHARE = "2";// 分享模板
		String FIND_PASSWORD = "3";// 密码找回模板
	}

	// 病历夹状态
	public static interface caseFolderStatus {
		String NORMAL = "Y"; // 正常
		String NO_NORMAL = "N"; // 删除
	}

	// 病历夹状态Map
	public static Map getStatusMap() {
		Map map = new LinkedHashMap();
		map.put(caseFolderStatus.NORMAL, "正常");
		map.put(caseFolderStatus.NO_NORMAL, "删除");
		return map;
	}

	/**
	 * 病历资料类别
	 * 
	 * @author Administrator
	 * 
	 */
	public static interface CaseType {
		String FILM = "F";// 胶片
		String DIAGNOSIS = "D";// 申请书
		String REQUISITION = "R";//  诊断报告
		String TEST_REPORT = "T";// 化验单
		String OTHER = "O";// 其他
	}

	public static Map getCaseTypeMap() {
		Map map = new LinkedHashMap();
		map.put(CaseType.FILM, "胶片");
		map.put(CaseType.DIAGNOSIS, "申请单");
		map.put(CaseType.REQUISITION, "诊断报告");
		map.put(CaseType.TEST_REPORT, "化验单");
		map.put(CaseType.OTHER, "其他");
		return map;
	}

	public static Map getCaseTypeMapKey() {
		Map map = new LinkedHashMap();
		map.put("胶片", CaseType.FILM);
		map.put("诊断报告", CaseType.DIAGNOSIS);
		map.put("申请单", CaseType.REQUISITION);
		map.put("化验单", CaseType.TEST_REPORT);
		map.put("其他", CaseType.OTHER);
		return map;
	}

	public static interface getExperied {
		String THREEMONTH = "3";// 3个月
		String SIXMONTH = "6";// 6个月
		String ONEYEAR = "12";// 12个月

	}

	public static Map getExperiedMap() {
		Map map = new LinkedHashMap();
		map.put(getExperied.THREEMONTH, "3");
		map.put(getExperied.SIXMONTH, "6");
		map.put(getExperied.ONEYEAR, "12");
		return map;
	}

	public static interface Relation {
		String LOCAL = "L";// 本地数据
		String HOSPITAL_DATA = "H";// 医院数据
		String TEMPORARY_DATA = "T";// 临时数据
	}

	public static Map getRelationMapKey() {
		Map map = new LinkedHashMap();
		map.put("医院病例资料", Relation.HOSPITAL_DATA);

		map.put("临时病例资料", Relation.TEMPORARY_DATA);
		return map;
	}

	public static Map getRelationMap() {
		Map map = new LinkedHashMap();
		map.put(Relation.HOSPITAL_DATA, "医院病例资料");

		map.put(Relation.TEMPORARY_DATA, "临时病例资料");
		return map;
	}

	public static Map getRelation() {
		Map map = new LinkedHashMap();
		map.put(Relation.LOCAL, "本地病例资料");
		map.put(Relation.HOSPITAL_DATA, "医院病例资料");

		map.put(Relation.TEMPORARY_DATA, "临时病例资料");
		return map;
	}

	/**
	 * 主页风格
	 * 
	 * @author Administrator
	 * 
	 */
	public static interface HomeFlag {

		String SUBTOTAL = "S"; // 分类汇总

		String CASE_DATA = "C";// 病历资料

		String DOSSIER = "D";// 病例夹

	}

	public static Map getHomeFlag() {
		Map map = new LinkedHashMap();
		map.put(HomeFlag.SUBTOTAL, "分类汇总");
		map.put(HomeFlag.CASE_DATA, "病历资料");
		map.put(HomeFlag.DOSSIER, "病历夹");
		return map;
	}
}
