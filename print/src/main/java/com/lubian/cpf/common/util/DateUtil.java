package com.lubian.cpf.common.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 得到当前日期-格式YYYYMMdd
	 * 返回String类型
	 */
	public static String getNowDateString() throws Exception {
		String dateString = "";
		String year = Calendar.getInstance().get(Calendar.YEAR) + "";
		String month = Calendar.getInstance().get(Calendar.MONTH) + "";
		Integer monthTemp = Integer.parseInt(month) + 1;
		String monthReslut = "";
		if (monthTemp < 10) {
			monthReslut = "0" + (monthTemp + "");
		}else{
			monthReslut=monthTemp.toString();
		}
		
		String day = Calendar.getInstance().get(Calendar.DATE) + "";
		if (Integer.parseInt(day) < 10) {
			day = "0" + day;
		}
		
		dateString = year + monthReslut + day;
		return dateString;
	}
	/***
	 * 计算传入日期的往后顺延N天以后的日期对象
	 * 
	 * @param date
	 *            传入的日期对象
	 * @param n
	 *            往后顺延的天数
	 * @return 顺延后的日期对象
	 */
	public static Date getPreviousNDate(Date date, long n) {
		if(date == null){
			return null;
		}
		long time = date.getTime();
		// 用毫秒数来计算新的日期
		time = time - n * 24 * 60 * 60 * 1000;
		return new Date(time);
	}

	public static String  getDateStrByWeek(){
		SimpleDateFormat format = new SimpleDateFormat("yyw");
		return format.format(Calendar.getInstance().getTime());
	}
	
	public static String  getDateStrByMonth(){
		SimpleDateFormat format = new SimpleDateFormat("yyMM");
		return format.format(Calendar.getInstance().getTime());
	}	
	
	public static Date parseDate(String in, String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(in);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getDateStr(Date in, String format){
		if(in == null){
			return "";
		}
		
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(in);
	}
	
	public static Date addOneDay(Date d) {
		if(d==null)return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	private static String secondStr = "秒";
	private static String minStr = "分";
	private static String hourStr = "小时";
	public static String getDateGap(Date s, Date e) {
		if(s==null || e==null)return "";
		long slong= s.getTime();
		long elong =e.getTime();
		if(slong >= elong)return "0" + secondStr;
		int sec = (int)((elong - slong)/1000);
		if(sec < 60)return sec + secondStr;
		int min = (int)(sec/60);
		sec = sec - min * 60;
		if(min < 60)return min + minStr + sec + secondStr;
		int hour = (int)(min/60);
		min = min - hour * 60;
		return  hour + hourStr + min + minStr + sec + secondStr;
	}
	
	
	public static Date getDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
	    try {
			return sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
	public static void main(String[] args) throws IOException {
		System.out.println(DateUtil.getDate("20141012"));
	}


}