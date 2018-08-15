package com.lubian.cpf.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExcelService {
	public List getSheetNameList(String filePath);
	public List getSheetColList(String filePath,String sheetName);
	public List<Map> getCell(String filePath);
	public String export(List headerList, List rowList, String realPath, String excelName);

}
