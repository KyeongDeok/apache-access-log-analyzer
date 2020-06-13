package com.daou.logpjt.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogParseUtil;

public abstract class FakeAbstractController {
	protected LogParseUtil parse = LogParseUtil.getInstance();
	protected LogDataModel model;

	abstract void process(String log);

	protected LogDataModelAndView show() {
		return null;
	}

	public FakeAbstractController(LogDataModel model) {
		this.model = model;
	}

	public FakeAbstractController() {}
	
	public void run(String filePath) {
		
		BufferedReader br;
		String log;
		
		try {
			br = new BufferedReader(new FileReader("filepath"));
			
			while((log = br.readLine()) != null) {
				process(log);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
