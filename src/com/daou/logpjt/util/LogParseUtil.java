package com.daou.logpjt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 문자열 파싱 클래스
 * TODO 수정 -> 패턴 인스턴스는 한번 사용되고 가비지가 되기 때문에 재사용 해야한다.
 */
public class LogParseUtil {
	public static class InnerParse {
        private static final LogParseUtil instance = new LogParseUtil();
    }
    public static LogParseUtil getInstance() {
        return InnerParse.instance;
    }
	public String doParse(RegularExp tag,String log) {
		String key = tag.getTag();
		String pattern = tag.getRegEx();
		Matcher m = Pattern.compile(pattern).matcher(log);
		return m.find()? m.group(key) : null;
	}
}