package com.daou.logpjt.view;

import java.util.HashSet;

import com.daou.logpjt.model.LogDataModel;

public class LongTimeReqReturnView extends AbstractView {
	public static HashSet <String> reqApiHs;
	public LogDataModel show() {
		reqApiHs.stream().forEach(System.out::println);
		reqApiHs.clear();
		return null;
	}
}