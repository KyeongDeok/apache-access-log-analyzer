package com.daou.logpjt.view;

import java.util.Map;

import com.daou.logpjt.model.LogDataModel;

public class LogReqWithResReturnView extends AbstractView {
	public static Map <String,Long> reqApiHm;
	public LogDataModel show() {
		reqApiHm.entrySet().stream()
		.sorted((o1, o2) -> (o2.getValue().compareTo(o1.getValue())))
		.forEach((entry) -> System.out.println(entry.getKey()+" "+entry.getValue()));
		reqApiHm.clear();
		return null;
	}
}