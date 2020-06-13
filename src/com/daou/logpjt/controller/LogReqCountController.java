package com.daou.logpjt.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogTimeUtil;
import com.daou.logpjt.util.RegularExp;

public class LogReqCountController extends AbstractController {

	private static ConcurrentHashMap<Integer, Long> reqCntHm = new ConcurrentHashMap<>();

	public LogDataModelAndView show() {
		LogDataModelAndView mv = new LogDataModelAndView();
		mv.setViewName("LogReqCountView");
		mv.addObject("reqCntHm", reqCntHm);
		return mv;
	}

	public void process(String log) {
		int timeKey = LogTimeUtil.getHourOfDay(parse.doParse(RegularExp.LOGTIME, log));
		reqCntHm.computeIfPresent(timeKey, (key, val) -> val + 1);
		reqCntHm.putIfAbsent(timeKey, (long) 1);
	}
	
	public void run() {
		
		BufferedReader br;
		String log;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			
			while((log = br.readLine()) != null) {
				process(log);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
}
}