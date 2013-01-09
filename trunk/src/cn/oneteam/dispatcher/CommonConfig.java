package cn.oneteam.dispatcher;

import cn.oneteam.interceptor.InterceptorList;
/**
 * 框架常量参数。
 * @author OneTeam K
 */

public class CommonConfig {
	private static String maxFileSize = "10485760";
	private static String encoding;
	private static String ioc;
	private static String kInt;
	private static String template;

	public static String getEncoding() {
		return encoding;
	}
	/**
	 * 编码。
	 */
	public static void setEncoding(String chatset) {
		encoding = chatset;
	}
	public static String getIoc() {
		return ioc;
	}
	public static void setIoc(String xmlioc) {
		ioc = xmlioc;
	}
	public static void setKInt(String actInt) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(actInt != null && !actInt.equals("")){
			InterceptorList.addKInt(actInt.split(","));
		}
	}
	public static String getTemplate() {
		return template;
	}
	public static void setTemplate(String xmlview) {
		template = xmlview;
	}
	public static String getMaxFileSize() {
		return maxFileSize;
	}
	public static void setMaxFileSize(String maxFileSize) {
		CommonConfig.maxFileSize = maxFileSize;
	}
	public static String getKInt() {
		return kInt;
	}
}