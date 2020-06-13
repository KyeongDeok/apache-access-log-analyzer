package com.daou.logpjt.model;

import java.util.HashMap;
import java.util.Map;

public class LogDataModel {
	public String path;
	public Map <String,Object> attrs = new HashMap<>();
	public LogDataModel(String path) {
		this.path = path;
	}
	public LogDataModel() {};
	public void setPathName(String path) {
		this.path = path;
	}
	public void addAttribute(String attrName,Object obj) {
		attrs.put(attrName,obj);
	}
	public Object getAttribute(String attrName) {
		return attrs.get(attrName);
	}
}