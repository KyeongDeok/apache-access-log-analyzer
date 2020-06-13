package com.daou.logpjt.view;

import java.util.HashSet;
import java.util.Map;

import com.daou.logpjt.model.LogDataModel;

public class BrowserFromReqView extends AbstractView {
	public static Map <Long,HashSet<String>> browserHm;
	@Override
	public LogDataModel show() {
		browserHm.entrySet().stream().forEach((entry) -> {
			System.out.println();
			System.out.println("Time "+entry.getKey()+": ");
			System.out.println();
			entry.getValue().stream().forEach(el -> System.out.println(el));
		});
		browserHm.clear();
		return null;
	}

}
