package com.lubian.cpf.common;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private boolean success = true;
	private String error;
	private Map data;

	public boolean isSuccess() {
		return success;
	}

	public Result setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public static Result createResult() {
		return new Result();
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

	public void setDataValue(String key, Object value) {
		if (data == null) {
			data = new HashMap();
		}
		data.put(key, value);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
