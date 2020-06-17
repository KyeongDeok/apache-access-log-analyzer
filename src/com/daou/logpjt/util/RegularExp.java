package com.daou.logpjt.util;


public enum RegularExp {
	LOGTIME("\\[(?<LOGTIME>.+)\\]"),
	METHOD(" (?<METHOD>\\D{1,7}?) \\/"),
	RESCODE("HTTP.{1,4} (?<RESCODE>\\d{3})"),
	DELAY(" (?<DELAY>\\d?\\.\\d{3,})"),
	REQAPI("\\w (?<REQAPI>\\/api((?!\\/\\w+@|\\/\\%|\\/\\w+\\.\\w+|\\/\\w+\\-|\\/\\d|\\?|\\=|[A-Z])\\S){1,})"),
	DEVICE("(?<DEVICE>\"Mozilla\\/5\\.0 .+?\\\")"),
	CHROME("Chrome"),
	WINDOW(" MSIE 8\\.0"),
	FIREFOX("Firefox"),
	SAFARI(" Version/5.0.2 Safari/");
//	OPERA();
	private String regex;
	
	RegularExp(String regex) {this.regex = regex;}
	String getTag() {return name();};
	String getRegEx() {return regex;};
}
