package com.lubian.cpf.common.sms;


import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.util.Config;
import com.lubian.cpf.dao.SysUserSmsDAO;
import com.lubian.cpf.vo.SysUserSms;

@Service
public class SMSSendServiceImpl implements SMSSendService {
	private Logger log =Logger.getLogger(SMSSendServiceImpl.class);
	@Autowired
	private SysUserSmsDAO userSmsDAO;
	
	/**
	 * 发送短信
	 * @throws UnsupportedEncodingException 
	 */
	public boolean sendSMS(Integer userId, String strPhone, String strContent) throws UnsupportedEncodingException{
		//准备信息
		String strSmsUrl = Config.getProperty(Constant.SMS_SEND_URL);
		String strReg = Config.getProperty(Constant.SMS_REG_USER);
        String strPwd = Config.getProperty(Constant.SMS_REG_PASS);
        //String strSourceAdd = Config.getProperty(Constant.SMS_SOURCE_ADD);
        //String content = SMSHttpSendClient.paraTo16(strContent);
        String content = SMSHttpSendClient.paraToGBK(strContent);
        //组装参数
		//String strSmsParam = "reg=" + strReg + "&pwd=" + strPwd + "&sourceadd=" + strSourceAdd + "&phone=" + strPhone + "&content=" + content;
        String strSmsParam = "username=" + strReg + "&password=" + strPwd 
        		+ "&mobile=" + strPhone + "&message=" + content;
        //发送
		String strResponse = SMSHttpSendClient.postSend(strSmsUrl, strSmsParam);
		//记录日志
		SysUserSms vo = new SysUserSms();
		vo.setPhoneNo(strPhone);
		if(strContent != null && strContent.length() > 500){
			vo.setContent(strContent.substring(0, 500));
		}else{
			vo.setContent(strContent);
		}
		if(strResponse != null && strResponse.length() > 500){
			vo.setRespStr(strResponse.substring(0, 500));
		}else{
			vo.setRespStr(strResponse);
		}		
		vo.setCreateBy("system");
		vo.setCreateDt(new Date());
		vo.setIsUpgoing("N");		
		vo.setUserId(userId);
		
		boolean ret;
		if(strResponse != null && strResponse.indexOf("result=0") != -1){
			vo.setIsSent("Y");
			vo.setSentDt(new Date());
			ret = true;
		}else{
			vo.setIsSent("N");
			ret = false;
		}
		userSmsDAO.insert(vo);
		return ret;
	}
	
	/**
	 * 查询余额
	 */
	public int getBalance(){
		try{
			//准备信息
			String strSmsUrl = Config.getProperty(Constant.SMS_BALANCE_URL);
			String strReg = Config.getProperty(Constant.SMS_REG_USER);
	        String strPwd = Config.getProperty(Constant.SMS_REG_PASS);
	        //组装参数
			String strSmsParam = "reg=" + strReg + "&pwd=" + strPwd;
			//发送
			String strResponse = SMSHttpSendClient.postSend(strSmsUrl, strSmsParam);
			
			if(strResponse != null && strResponse.indexOf("result=0") != -1){
				String[] ret = strResponse.split("&");
				if(ret != null && ret.length >0){
					for(String s : ret){
						if(s.startsWith("balance")){
							return Integer.parseInt(s.substring(8));
						}
					}
				}
			}
		}catch(Exception e){
			log.error("获取短信余额出错:"+e);
		}
		return -1;
	}	
}
