package com.lubian.cpf.common.cxf;

import javax.jws.WebService;

@WebService
public interface TokenWebService {
	
	public String checkToken(String token);
}
