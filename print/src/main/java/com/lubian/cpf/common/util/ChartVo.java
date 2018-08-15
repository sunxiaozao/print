package com.lubian.cpf.common.util;

import java.util.ArrayList;
import java.util.List;


/**
 * 数据分析，前台构造图表时使用的工具类
 */
public class ChartVo {
	private String name = "";
	private List<Integer> data = new ArrayList();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
}
