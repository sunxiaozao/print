package com.lubian.cpf.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.lubian.cpf.common.constant.Constant;

public class Excel {
	private static final Logger log = Logger.getLogger(Excel.class);

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
			String excelName, List<String> propertys, Map<String, String> map,
			HttpServletRequest request) {
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
			HSSFWorkbook workbook = this.createResultFile(headerList, rowList,
					propertys, map, request);
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
	@SuppressWarnings("rawtypes")
	private HSSFWorkbook createResultFile(List headerList, List rowList,
			List<String> propertys, Map<String, String> map,
			HttpServletRequest request) throws Exception {
		int rowsPerSheet = 65530;// 65536，单个excel最大行数2003
		int rowCount = rowList.size();
		int startRow = 1;
		int sheetId = 0;
		HSSFWorkbook workbook = createWorkBook(headerList, rowCount,
				rowsPerSheet);

		// 创建数据项格式
		HSSFCellStyle cs1 = workbook.createCellStyle();
		
		cs1.setWrapText(false);
		cs1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		for (Object o : rowList) {
			HSSFCell cell = null;
			HSSFRow row = workbook.getSheetAt(sheetId).createRow(startRow);
			row.setHeightInPoints(16);
			for (int i = 0; i < propertys.size(); i++) {
				Object obj = null;
				String propertyName = "";
				obj = o;
				propertyName = propertys.get(i);
				try {
					cell = row.createCell(i);
					if (map != null) {
						if (map.containsKey(propertyName)) {
							Map m = (Map) request.getSession().getAttribute(
									map.get(propertyName));
							String val = BeanUtils.getProperty(obj,
									propertyName);
							if (val != null && !"".equals(val)
									&& m.containsKey(val)) {
								cell.setCellValue(m
										.get(BeanUtils.getProperty(obj,
												propertyName)).toString());
							}
						} else {
							cell.setCellValue(BeanUtils.getProperty(obj,
									propertyName));
						}

					} else {
						cell.setCellValue(BeanUtils.getProperty(obj,
								propertyName));
					}
					cell.setCellStyle(cs1);
				} catch (Exception e) {

					System.out.println("aaa");
					e.printStackTrace();
				}
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
		HSSFCellStyle headerStyle = workbook.createCellStyle();// 创建标题样式
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置水平居中
		HSSFFont headerFont = (HSSFFont) workbook.createFont(); // 创建字体样式
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
		headerFont.setFontName("Times New Roman"); // 设置字体类型
		headerFont.setFontHeightInPoints((short) 8); // 设置字体大小
		headerStyle.setFont(headerFont); // 为标题样式设置字体样式
		headerStyle
				.setFillForegroundColor(new HSSFColor.BLUE_GREY().getIndex());
		headerStyle
				.setFillBackgroundColor(new HSSFColor.BLUE_GREY().getIndex());
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
				cell.setCellStyle(headerStyle);
			}
			for (int k = 0; k < row.getLastCellNum(); k++) {
				sheet.setColumnWidth(k, 5000);// 设置列宽
			}
		}

	}

	/**
	 * 读取Excel的内容，
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * @return 读出的Excel中数据的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<List<String>> getData(java.io.File file, int ignoreRows)
			throws FileNotFoundException, IOException {
		List<List<String>> result = new ArrayList<List<String>>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		HSSFSheet st = wb.getSheetAt(0);
		// 第一行为标题，不取
		for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
			HSSFRow row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			List<String> values = new ArrayList<String>();
			boolean hasValue = false;
			for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex);
				if (cell != null) {
					// 注意：一定要设成这个，否则可能会出现乱码
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								value = new SimpleDateFormat("yyyy-MM-dd")
										.format(date);
							} else {
								value = "";
							}
						} else {
							value = new DecimalFormat("0").format(cell
									.getNumericCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						// 导入时如果为公式生成的数据则无值
						if (!cell.getStringCellValue().equals("")) {
							value = cell.getStringCellValue();
						} else {
							value = cell.getNumericCellValue() + "";
						}
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						break;
					case HSSFCell.CELL_TYPE_ERROR:
						value = "";
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						value = (cell.getBooleanCellValue() == true ? "Y" : "N");
						break;
					default:
						value = "";
					}
				}
				if (columnIndex == 0 && value.trim().equals("")) {
					break;
				}
				values.add(rightTrim(value));
				hasValue = true;
			}

			if (hasValue) {
				result.add(values);
			}
		}
		in.close();
		return result;
	}

	/**
	 * 读取Excel的内容，
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * @return 读出的Excel中数据的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<List<String>> getData(MultipartFile file, int ignoreRows)
			throws FileNotFoundException, IOException {
		List<List<String>> result = new ArrayList<List<String>>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(file.getInputStream());
		
		
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		
		HSSFCell cell = null;
		HSSFSheet st = wb.getSheetAt(0);
		// 第一行为标题，不取
		for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
			HSSFRow row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			List<String> values = new ArrayList<String>();
			boolean hasValue = false;
			for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex);
				if (cell != null) {
					// 注意：一定要设成这个，否则可能会出现乱码
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								value = new SimpleDateFormat("yyyy-MM-dd")
										.format(date);
							} else {
								value = "";
							}
						} else {
							value = new DecimalFormat("0").format(cell
									.getNumericCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						// 导入时如果为公式生成的数据则无值
						if (!cell.getStringCellValue().equals("")) {
							value = cell.getStringCellValue();
						} else {
							value = cell.getNumericCellValue() + "";
						}
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						break;
					case HSSFCell.CELL_TYPE_ERROR:
						value = null;
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						value = (cell.getBooleanCellValue() == true ? "Y" : "N");
						break;
					default:
						value = null;
					}
				}
				if (columnIndex == 0 && value.trim().equals("")) {
					break;
				}
				if(value==null||"".equals(value)){
					values.add(null);
				}else{
					values.add(rightTrim(value).trim());
				}
			
				hasValue = true;
			}

			if (hasValue) {
				result.add(values);
			}
		}
		in.close();
		return result;
	}
	public static String value(Cell cell) {
		String value = null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				if (date != null) {
					value = new SimpleDateFormat("yyyy-MM-dd").format(date);
				} else {
					value = "";
				}
			} else {
				value = new DecimalFormat("0").format(cell
						.getNumericCellValue());
			}
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			// 导入时如果为公式生成的数据则无值
			if (!cell.getStringCellValue().equals("")) {
				value = cell.getStringCellValue();
			} else {
				value = cell.getNumericCellValue() + "";
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			value = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			value = (cell.getBooleanCellValue() == true ? "Y" : "N");
			break;
		default:
			value = "";
		}
		
		return leftTrim(value);
	}

	/**
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}

	public static String leftTrim(String str) {
		if (str == null)
			return "";
		int start = 0;
		int i = 0;
		for (int n = str.length(); i < n && str.charAt(i) == ' '; i++)
			start++;

		return str.substring(start);
	}

	public static String getExcel(String temp, Map beans, String to) {
		try {
			XLSTransformer transformer = new XLSTransformer();
			transformer.transformXLS(temp, beans, to);
		} catch (ParsePropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return to;
	}

	/**
	 * 检查Excel的结构
	 * 
	 * @param tempPath
	 *            模板的路径
	 * @param inPath
	 *            导入的路径
	 * @return
	 */
	public static List<String> checkStruct(String tempPath, String inPath) {
		List<String> result = new ArrayList<String>();
		InputStream inp = null;
		HSSFWorkbook wb = null;
		try {
			inp = new FileInputStream(inPath);
			wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			HSSFSheet inSheet = wb.getSheetAt(0);
			inp = new FileInputStream(tempPath);
			wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			HSSFSheet tempSheet = wb.getSheetAt(0);
			System.out.println("inSheet.getLastRowNum:"+inSheet.getLastRowNum());
			System.out.println("tempSheet.getLastRowNum:"+tempSheet.getLastRowNum());
			
			if (inSheet.getLastRowNum() > tempSheet.getLastRowNum()) {
				Row inRow = inSheet.getRow(tempSheet.getLastRowNum());			
				Row tempRow = tempSheet.getRow(tempSheet.getLastRowNum());
				if (inRow.getLastCellNum() == tempRow.getLastCellNum()) {
					for (int i = 0; i < tempRow.getLastCellNum(); i++) {
						if (!tempRow.getCell(i).toString()
								.equals(inRow.getCell(i).toString())) {
							result.add(tempRow.getCell(i).toString()
									+ "不存在!");
						}
					}
				} else {
					result.add("列数不匹配!");
				}if(result.size()>0)
				result.add("请擦参照模板填写!");
			} else {
				result.add("请填写导入数据");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inp != null) {
				try {
					inp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
