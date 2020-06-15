package com.daou.logpjt.util;


public enum RegularExp {
	LOGTIME("(?<LOGTIME>\\S)"),
	METHOD("\\S"),
	RESCODE("\\S"),
	DELAY("\\S"),
	REQAPI("\\S"),
	DEVICE("\\S");
//	OPERA();
	private String regex;
	
	RegularExp(String regex) {this.regex = regex;}
	String getTag() {return name();};
	String getRegEx() {return regex;};
}
