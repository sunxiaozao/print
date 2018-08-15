package com.lubian.cpf.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.lubian.cpf.common.constant.Constant;
import com.lubian.cpf.common.util.Config;


/**
 * excel导入导出封装类，只适用小数据量excel
 * 
 * @author leon
 * 
 */

@Service
public class ExcelServiceImpl implements ExcelService {
	private static final Logger log = Logger.getLogger(ExcelServiceImpl.class);

	/**
	 * 读取Excel并创建相应版本的wb对象
	 * 
	 * @param filePath
	 * @return
	 */
	private Workbook getExcel(String filePath) {
		Workbook wb = null;
		InputStream is = null;
		if (StringUtils.isBlank(filePath)) {
			return null;
		}
		String fiileName = filePath.substring(filePath.lastIndexOf(".") + 1);
		try {
			// 判断Excel的版本
			// Excel2007之前的版本
			if (fiileName.equals("xls")) {
				is = new FileInputStream(filePath);
				wb = new HSSFWorkbook(is);
			} else {
				File file = new File(filePath);
				OPCPackage opcPackage = OPCPackage.open(file);
				// Excel2007之后的版本
				wb = new XSSFWorkbook(opcPackage);
			}
		} catch (Exception e) {
			log.error(e);
		}

		return wb;
	}

	/**
	 * 解析并得到Excel的sheet名称列表
	 */
	public List getSheetNameList(String filePath) {
		List sheetNameList = new ArrayList();
		Workbook wb = this.getExcel(filePath);
		if (wb != null) {
			for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
				sheetNameList.add(wb.getSheetAt(numSheet).getSheetName());
			}
		}
		return sheetNameList;
	}

	/**
	 * 获取excel中特定sheet的列名称，即sheet第一行的数据
	 */
	public List getSheetColList(String filePath, String sheetName) {
		Row row = null;
		List sheetColList = new ArrayList();
		Workbook wb = this.getExcel(filePath);
		if (wb != null) {
			if (!StringUtils.isBlank(sheetName)) {
				row = wb.getSheet(sheetName).getRow(0);
			} else {
				row = wb.getSheetAt(0).getRow(0);
			}
			if (row != null) {
				int colNum = row.getPhysicalNumberOfCells();
				for (int i = 0; i < colNum; i++) {
					row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
					sheetColList.add(row.getCell(i).getStringCellValue());
				}
			}
		}
		return sheetColList;
	}

	/**
	 * 解析Excel并且以list格式放回excel所有内容, list第一行为excel第一个sheet的第一行即为列名(格式Map<colName,
	 * colNum>), 后续列即为excel各行数值(格式Map<colNum, colValue>), colNum对应第一行数据中的colNum,
	 * colValue全部为String类型.
	 * 
	 * 注意： 1. 此方法只适用小数据量的excel, 大数据量excel会导致严重性能问题（需要使用Event Model方式导入） 2.
	 * 导入的excel格式第一行必须为列名，且列名不能重复 3. 如果有多个sheet，每个sheet的第一行都必须是列名，且各sheet第一行完全一样
	 * 
	 * @param filePath
	 * @return
	 */
	public List<Map> getCell(String filePath) {
		List<Map> list = new ArrayList<Map>();
		Map smallMap = new HashMap();// 存储excel第一行，列字段
		Workbook wb = this.getExcel(filePath);
		if (wb == null) {
			return list;
		}
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
			Sheet sheet = wb.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}

			// 循环行Row
			Row row = null;
			for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				log.debug("Index" + rowNum + "Row--");

				// 循环列Cell
				Map bigMap = new HashMap();// 存储每一行的数据，excel的内容值
				Cell cell = null;
				for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
					cell = row.getCell(cellNum);
					if (cell == null) {
						continue;
					}
					if (rowNum == 0) {
						// key为标题列的值(如:年份,月份,日) value为列的编号(如0,1,2)
						smallMap.put(getCellValue(cell), cellNum);
					} else {
						// value为列的编号(如0,1,2) value为出标题列以外的cell的值
						bigMap.put(cellNum, getCellValue(cell));
					}
					log.debug("Index" + cellNum + " column "
							+ getCellValue(cell));
				}
				if (rowNum == 0 && list.size() == 0) {
					list.add(smallMap);
				}
				if (rowNum > 0) {
					list.add(bigMap);
				}
			}
		}
		return list;
	}

	/**
	 * 把excel格中的值转化为String并返回
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell) {
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			double dd = cell.getNumericCellValue();
			return String.valueOf(dd);
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	/**
	 * 根据入参，生成excel并存储于临时目录，返回excel的相对路径
	 * 
	 * @param haaderList
	 * @param rowList
	 * @param realPath
	 * @param excelName
	 * @return
	 */
	public String export(List headerList, List rowList, String realPath,
			String excelName) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dowloadDir = Config.getProperty(Constant.DOWNLOAD_DIR);
		if (dowloadDir == null) {
			dowloadDir = "/down";
		}
		String tempFolder = df.format(new Date()) + "/";
		String tempPath = realPath + dowloadDir + "/" + tempFolder;
		File file = new File(tempPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String excelPath = tempPath + excelName;
		FileOutputStream writer = null;
		// 生成excel
		try {
			writer = new FileOutputStream(excelPath + ".xls");
			HSSFWorkbook workbook = this.createResultFile(headerList, rowList);
			workbook.write(writer);
			writer.flush();
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
		return dowloadDir + "/" + tempFolder + excelName + ".xls";
	}

	/**
	 * 根据入参填充excel信息，设置字体颜色等格式
	 * 
	 * @param haaderList
	 * @param rowList
	 * @return
	 */
	private HSSFWorkbook createResultFile(List headerList, List rowList) {
		int rowsPerSheet = 65530;// 65536，单个excel最大行数2003
		int rowCount = rowList.size();
		int startRow = 1;
		int sheetId = 0;
		int columnCount = headerList.size();
		HSSFWorkbook workbook = createWorkBook(headerList, rowCount,
				rowsPerSheet);

		// 创建数据项格式
		HSSFCellStyle cs1 = workbook.createCellStyle();
		cs1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs1.setWrapText(false);
		cs1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		for (int i = 0; i < rowList.size(); i++) {
	
			List data = (List) rowList.get(i);
			String[] values = new String[columnCount];
			for (int j = 0; j < columnCount; j++) {
				values[j] = (String) data.get(j);
			}

			HSSFCell cell = null;
			HSSFRow row = workbook.getSheetAt(sheetId).createRow(startRow);
			row.setHeightInPoints(16);
			for (int j = 0; j < columnCount; j++) {
				cell = row.createCell(j);
				cell.setCellValue(values[j]);
				cell.setCellStyle(cs1);
			}
			startRow++;
			if (startRow > rowsPerSheet) {
				startRow = 1;
				sheetId++;
			}
		}
		return workbook;
	}

	
	
	
	
	/**
	 * 创建excel workbook，HSSF office3003格式
	 * 
	 * @param haaderList
	 * @param rowCount
	 * @param rowsPerSheet
	 * @return
	 */
	private HSSFWorkbook createWorkBook(List headerList, int rowCount,
			int rowsPerSheet) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		int sheetCount = (rowCount + rowsPerSheet - 1) / rowsPerSheet;
		for (int i = 0; i < sheetCount; i++) {
			workbook.createSheet("sheet" + (i + 1));
		}
		createHeader(workbook, headerList);
		return workbook;
	}

	/**
	 * 根据输入的列信息，生成excel第一行信息
	 * 
	 * @param workbook
	 * @param haaderList
	 */
	private void createHeader(HSSFWorkbook workbook, List headerList) {
		// 设置字体格式
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 10);
		// 创建格式模型
		HSSFCellStyle cs = workbook.createCellStyle();
		cs.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		cs.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		cs.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		cs.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		cs.setFillForegroundColor(HSSFColor.LIME.index);
		cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 自动换行
		cs.setWrapText(true);
		// 上下居中
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setFont(font);

		Map<String, String> headers = new HashMap<String, String>();
		for (int i = 0; i < headerList.size(); i++) {
			String name = (String) headerList.get(i);
			headers.put("column" + i, name);
		}

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);
			HSSFRow row = sheet.createRow(0);
			row.setHeightInPoints(16);
			for (int j = 0; j < headers.size(); j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(new HSSFRichTextString(headers.get("column"
						+ j)));
				cell.setCellStyle(cs);
			}
			for (int j = 0; j < headerList.size(); j++) {
				sheet.setColumnWidth(j, 32 * 140);
			}
		}

	}

}
