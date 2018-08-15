package com.lubian.cpf.common.cxf;

import javax.jws.WebService;

@WebService(endpointInterface = "com.lubian.cpf.common.cxf.TokenWebService", serviceName = "tokenWebService")
public class TokenWebServiceImpl implements TokenWebService {

	@Override
	public String checkToken(String token) {

		String str = "hello[" + token.trim() + "]WebService test ok!";
		System.out.println(str);
		return str;
	}

}
