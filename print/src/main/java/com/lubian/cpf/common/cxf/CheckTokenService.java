package com.lubian.cpf.common.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "checkTokenService")  
public interface CheckTokenService {

	public boolean checkToken(@WebParam(name = "toKen")String toKen);  
}
