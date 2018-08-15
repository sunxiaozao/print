package com.lubian.cpf.common.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class CustomObjectMapper extends ObjectMapper{
	
	public CustomObjectMapper(){
		super();
		this.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL); 
	}
}
