package com.daou.logpjt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * ���ڿ� �Ľ� Ŭ����
 * TODO ���� -> ���� �ν��Ͻ��� �ѹ� ���ǰ� �������� �Ǳ� ������ ���� �ؾ��Ѵ�.
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