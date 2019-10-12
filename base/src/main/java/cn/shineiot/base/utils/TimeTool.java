package cn.shineiot.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangs
 * 时间类型转换
 */
public class TimeTool {

	/**注意日期格式*/
	public static  final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static  final String YMDH = "yyyy-MM-dd HH";
	public static  final String YMD = "yyyy:MM:dd";
	public static  final String HMS = "HH:mm:ss";
	private static long time;

	/**获取当前时间搓*/
	public static long getCurrentTime(){
		return System.currentTimeMillis();
	}

	/**
	 * 日期转换为时间戳
	 */
	public static long stringTOlong(String data, String type){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
		try {
			Date date = simpleDateFormat.parse(data);
			time = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 将时间戳转换为某一类型的日期
	 * @param type 如:传 YYYY:MM:dd 或者 YYYY:MM:dd HH:mm:ss等
	 */
	public static String longTOstring(long data, String type){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
		String time = simpleDateFormat.format(new Date(data));
		return time;
	}

	/**
	 * 日期之间的转换  如YYYY:MM:dd HH:mm:ss转为YYYY:MM:dd (大转小)
	 * @param type  要转换的格式
	 */
	public static String stringTostring(String dataType, String data, String type){
		long timea = stringTOlong(data,dataType);
		String time = longTOstring(timea,type);
		return time;
	}
}
