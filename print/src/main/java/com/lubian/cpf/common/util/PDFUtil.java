package com.lubian.cpf.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.lubian.cpf.common.constant.Constant;

public class PDFUtil {

	/**
	 * 页面的大小为A4
	 * 
	 * @return
	 */
	public static Document getDocument() {
		return new Document();
	}

	/**
	 * 自定义页面大小
	 * 
	 * @param pageSize
	 * @return
	 */
	public static Document getDocument(Rectangle pageSize) {
		return new Document(pageSize);

	}

	/**
	 * 自定的页面大小及边距
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param marginLeft
	 *            左
	 * @param marginRight
	 *            右
	 * @param marginTop
	 *            上
	 * @param marginBottom
	 *            下
	 * @return
	 */
	public static Document getDocument(Rectangle pageSize, int marginLeft, int marginRight, int marginTop, int marginBottom) {
		return new Document(pageSize, marginLeft, marginRight, marginTop, marginBottom);
	}

	/**
	 * 字体大小
	 * 
	 * @param size
	 * @return
	 */
	public static Font getCHFont(int size) {

		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font headFont = new Font(bfChinese, size, Font.NORMAL);// 设置字体大小
			return headFont;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}// 设置中文字体
		return null;
	}

	/**
	 * 图片
	 * 
	 * @param path
	 *            图片路径
	 * @return
	 */
	public static Image getImage(String path) {
		try {
			return Image.getInstance(path);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成pdf
	 * 
	 * @param path
	 *            生成路径
	 * @param str
	 * @param imgPaths
	 *            图片
	 * @return
	 */
	public static String getPDF(String path, java.util.List<String> str, String... imgPaths) {
		Rectangle pageSize = new Rectangle(PageSize.A4);
		Document document = getDocument(pageSize, 10, 10, 10, 10);
		try {

			path = path + UUID.randomUUID().toString() + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(Constant.ROOT_PATH + "/" + path));
			document.open();
			if (str != null && str.size() > 0) {
				for (String s : str) {
					Paragraph title = new Paragraph(s, getCHFont(15));
					document.add(title);
				}
			}
			if (imgPaths != null && imgPaths.length > 0) {
				for (String string : imgPaths) {
					Image img = getImage(Constant.ROOT_PATH + "/" + string);
					document.add(img);
				}
			}

		} catch (FileNotFoundException e) {
		} catch (DocumentException e) {
		} finally {
			document.close();
		}
		return path;
	}

	public static String mergePdfFiles(String[] files, String newfile) {
		Document document = new Document();
		try {
			newfile = newfile + UUID.randomUUID().toString() + ".pdf";
			PdfCopy copy = new PdfCopy(document, new FileOutputStream(Constant.ROOT_PATH + "/" + newfile));
			document.open();
			for (int i = 0; i < files.length; i++) {
				PdfReader reader = new PdfReader(Constant.ROOT_PATH + "/" + files[i]);
				int n = reader.getNumberOfPages();
				for (int j = 1; j <= n; j++) {
					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
		return newfile;
	}

	public static String mergePdfFiles(String[] files, String newfile, String fileName) {
		Document document = new Document();
		try {
			newfile = newfile + fileName;
			File file = new File(Constant.ROOT_PATH + "/" + newfile);
			if (!file.exists()) {
				PdfCopy copy = new PdfCopy(document, new FileOutputStream(Constant.ROOT_PATH + "/" + newfile));
				document.open();
				for (int i = 0; i < files.length; i++) {
					PdfReader reader = new PdfReader(Constant.ROOT_PATH + "/" + files[i]);
					int n = reader.getNumberOfPages();
					for (int j = 1; j <= n; j++) {
						document.newPage();
						PdfImportedPage page = copy.getImportedPage(reader, j);
						copy.addPage(page);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
		return newfile;
	}

}
