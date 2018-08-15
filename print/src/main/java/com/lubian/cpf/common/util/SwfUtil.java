package com.lubian.cpf.common.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class SwfUtil extends Thread{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SwfUtil.class);

	public File pdfFile;
	public File swfFile;

	public SwfUtil(String pdfPath, String swfPath) {
		this.pdfFile = new File(pdfPath);
		this.swfFile = new File(swfPath);
	}

	
	public void run() {
		System.out.println(Thread.currentThread().getName() +":" );
		if (pdfFile.exists()) {
			if (!swfFile.exists()) {
						System.out.println(Thread.currentThread().getName() +":" );
						try {
							String paths = "D:/SWFTools/pdf2swf.exe  -t " + pdfFile.getAbsolutePath() + " -o  " + swfFile.getAbsolutePath() + " -s flashversion=9";
							Runtime.getRuntime().exec(paths);
							logger.info("*******pdf转换swf********");
							swfFile.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
							logger.debug(e);
						}
					}
			} else {
				logger.info("*******pdf已经转换swf,不需再次转换********");

			}

		}

	


	
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("D:/a.pdf", "D:/a.swf");
		map.put("D:/c.pdf", "D:/c.swf");
		map.put("D:/b.pdf", "D:/b.swf");
	//	String path = getSwfPath("D:/cpf/pdf/6aec16b2-f87f-4c28-8bd5-5d204b1fc843.pdf", "F:/apache-tomcat-7.0.55/webapps/cpf/up/6aec16b2-f87f-4c28-8bd5-5d204b1fc84.swf");
		for (String string : map.keySet()) {
		new SwfUtil(string,map.get(string)).start();
		}
		
	//	System.out.println(path);
	}
}
