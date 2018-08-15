package com.lubian.cpf.common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
	/**
	 * 生成 workBook
	 * 
	 * @param sheetCount
	 * @param mergecell
	 * @param header
	 * @return
	 */
	public static HSSFWorkbook createWorkBook(int sheetCount, String sheetName,
			int[][][] mergecell, String[][] header) {

		HSSFWorkbook wb = new HSSFWorkbook();

		for (int i = 0; i < sheetCount; i++) {
			wb.createSheet(sheetName + (i + 1));
		}
		createHeader(wb, sheetCount, sheetName, mergecell, header);
		return wb;
	}

	/**
	 * 
	 * @param wb
	 * @param sheetCount
	 *            一共多少sheet
	 * @param mergecell
	 *            如何合并单元个 int a[行][列][合并]={{{开始行,结束行,开始列,结束列}}};
	 * @param header
	 *            列名 String[][]={{},{}}
	 */
	private static void createHeader(HSSFWorkbook wb, int sheetCount,
			String sheetName, int[][][] mergecell, String[][] header) {

		HSSFCellStyle headerStyle = wb.createCellStyle();// 创建标题样式
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置水平居中
		HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
		headerFont.setFontName("Times New Roman"); // 设置字体类型
		headerFont.setFontHeightInPoints((short) 8); // 设置字体大小
		headerStyle.setFont(headerFont); // 为标题样式设置字体样式
		headerStyle
				.setFillForegroundColor(new HSSFColor.BLUE_GREY().getIndex());
		headerStyle
				.setFillBackgroundColor(new HSSFColor.BLUE_GREY().getIndex());
		for (int e = 0; e < sheetCount; e++) {
			HSSFRow row = null;
			HSSFSheet sheet = wb.getSheet(sheetName + (e + 1));
			for (Integer i = 0; i < mergecell.length; i++) {
				row = sheet.createRow(i);
				for (int j = 0; j < mergecell[i].length; j++) {
					sheet.addMergedRegion(new CellRangeAddress(
							mergecell[i][j][0], mergecell[i][j][1],
							mergecell[i][j][2], mergecell[i][j][3]));
					HSSFCell cell = row.createCell(mergecell[i][j][2]);
					cell.setCellValue(header[i][j]);
					cell.setCellStyle(headerStyle);
					cell.setCellType(HSSFCell.ENCODING_UTF_16);
				}
			}
			for (int i = 0; i < row.getLastCellNum(); i++) {
				sheet.setColumnWidth(i, 5000);// 设置列宽
			}
		}
	}
}
