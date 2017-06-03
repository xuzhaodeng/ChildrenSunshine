package com.pas.edu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommUtil {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取当前时间字符串并格式化时间 
	 * @throws Exception 
	 */
	public static String getDateFormat(){
		return dateFormat.format(new Date());
	}
	
	/**
	 * 时间字符串转化成Date
	 * @param str 待转换时间字符串
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public static Date getDateFormat(String str) throws ParseException{
		return str != null && !"".equals(str) && !"null".equals(str) ? dateFormat.parse(str) : null;
	}

}
