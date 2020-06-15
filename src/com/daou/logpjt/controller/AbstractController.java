package com.daou.logpjt.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogParseUtil;

public abstract class AbstractController {
	
	protected String filePath;
	protected LogParseUtil parse = LogParseUtil.getInstance();
	protected LogDataModelAndView show() {
		return null;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public abstract void run();
	
	//FIXME:: 리팩토링 필요.
	public void render() {
		LogDataModelAndView mv = show();
		if (mv == null) {
			return;
		} else {
			String view = mv.view;
			Map<String, Object> obj = mv.attrs;
			try {
				Class<?> viewClass = Class.forName("com.daou.logpjt.view." + view);
				Object newobj = viewClass.newInstance();
				for (Map.Entry<String, Object> o : obj.entrySet()) {
					Field fld = viewClass.getDeclaredField(o.getKey());
					fld.set(newobj, o.getValue());
				}
				Method m = viewClass.getMethod("show");
				m.invoke(newobj);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException
					| SecurityException | NoSuchMethodException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.getCause().printStackTrace();
			}
		}
	}
}