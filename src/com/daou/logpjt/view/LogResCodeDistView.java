package com.daou.logpjt.view;

import java.util.Map;

import com.daou.logpjt.model.LogDataModel;

public class LogResCodeDistView extends AbstractView{
	public static Map <Long,Map<String,Long>> resCodeHm;
	@Override
	public LogDataModel show() {
		resCodeHm.entrySet().stream().forEach((entry) -> System.out.println(entry.getKey()+" "+entry.getValue()));
		resCodeHm.clear();
		return null;
	}
}