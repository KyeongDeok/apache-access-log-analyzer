package com.daou.logpjt.view;

import java.util.Map;

import com.daou.logpjt.model.LogDataModel;

public class LogReqCountView extends AbstractView {
	public static Map <Integer,Long> reqCntHm;
	@Override
	public LogDataModel show() {
		reqCntHm.entrySet().stream().forEach((entry) -> System.out.println(entry.getKey()+" "+entry.getValue()));
		reqCntHm.clear();
		return null;
	}
}