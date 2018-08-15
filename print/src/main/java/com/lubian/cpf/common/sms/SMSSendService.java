package com.lubian.cpf.common.sms;

import java.io.UnsupportedEncodingException;



public interface SMSSendService {
	
	public boolean sendSMS(Integer userId, String strPhone, String content) throws UnsupportedEncodingException;
	public int getBalance();
}
