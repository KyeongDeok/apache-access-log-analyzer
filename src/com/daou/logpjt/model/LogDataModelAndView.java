package com.daou.logpjt.model;

import java.util.HashMap;
import java.util.Map;

public class LogDataModelAndView {
	public String view;
	public Map<String, Object> attrs = new HashMap<>();

	public void setViewName(String path) {
		this.view = path;
	}

	public void addObject(String attrName, Object obj) {
		attrs.put(attrName, obj);
	}
}