package com.daou.logpjt.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.daou.logpjt.model.LogDataModel;

public abstract class AbstractView {
	public abstract LogDataModel show();
	public void render() {
		LogDataModel model = show();
		if(model == null) {
			return;
		}else {
			String [] paths = model.path.split("/");
			try {
				Class <?> controller = Class.forName("com.daou.logpjt.controller.log."+paths[0]);
				Constructor<?> cs = controller.getConstructor(LogDataModel.class);
				Object newobj = cs.newInstance(model);
				
				Method m = controller.getMethod(paths[1]);
				m.invoke(newobj);
			} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
				e.getCause().printStackTrace();
				e.printStackTrace();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}