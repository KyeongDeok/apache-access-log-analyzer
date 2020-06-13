package com.daou.logpjt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.model.LogFileModel;
import com.daou.logpjt.util.LogParseUtil;

public abstract class AbstractController {
	public static final long[] FILE_POINTER = { 0, 1000000, 2000000, 3000000, Long.MAX_VALUE };
	protected static final long MILLISECOND = 3600000;
	protected static final long HOUR = 24;
	protected LogParseUtil parse = LogParseUtil.getInstance();
	protected LogDataModel model;

	abstract void process(String log);

	protected LogDataModelAndView show() {
		return null;
	}

	public AbstractController(LogDataModel model) {
		this.model = model;
	}

	public AbstractController() {}

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

	public void run() {
		try {
			int len = FILE_POINTER.length;
			Thread[] t = new Thread[len - 1];
			List<BufferedReader> brs = LogFileModel.getFileStreams();
//			ExecutorService executor = Executors.newCachedThreadPool();
			for (BufferedReader br : brs) {
				IntStream.range(0, len - 1).forEach(n -> {
					t[n] = new Thread() {
						@Override
						public void run() {
							System.out.println(Thread.currentThread().getName() + " :");
							br.lines().skip(FILE_POINTER[n]).limit(FILE_POINTER[n + 1]).forEach((log) -> process(log));
						}
					};
				});
				for (int i = 0; i < len - 1; i++) {
					t[i].start();
//					t[i].join();
				}
				for (int j = 0; j < len - 1; j++) {
					t[j].join();
				}
//				executor.shutdown();
//				while(!executor.isTerminated()){
//					;
//				}
				br.close();
			}
		} catch (NullPointerException | IOException | InterruptedException e) {
			e.getStackTrace();
			e.printStackTrace();
		}
		render();
	}
}
