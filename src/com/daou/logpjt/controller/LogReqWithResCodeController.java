package com.daou.logpjt.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogTimeUtil;
import com.daou.logpjt.util.RegularExp;

public class LogReqWithResCodeController extends AbstractController {
	private static ConcurrentHashMap<String, Long> reqApiHm = new ConcurrentHashMap<String, Long>();

	public LogDataModelAndView show() {
		LogDataModelAndView mv = new LogDataModelAndView();
		mv.setViewName("LogReqWithResReturnView");
		mv.addObject("reqApiHm", reqApiHm);
		return mv;
	}

	public void process(String log) {
		String reqApi, resCode, reqMethod;
		String method = (String) model.getAttribute("method");
		String code = (String) model.getAttribute("code");
		
		long startTime = (long) model.getAttribute("startTime");
		long endTime = (long) model.getAttribute("endTime");
		long epoch = LogTimeUtil.getEpoch(parse.doParse(RegularExp.LOGTIME, log));
		
		reqApi = parse.doParse(RegularExp.REQAPI, log);
		resCode = parse.doParse(RegularExp.RESCODE, log);
		reqMethod = parse.doParse(RegularExp.METHOD, log);
		
		if (reqApi == null)
			return;
		if (startTime > epoch || epoch > endTime || !method.equals(reqMethod) || !code.equals(resCode))
			return;
		
		reqApiHm.computeIfPresent(reqApi, (key, val) -> val + 1);
		reqApiHm.putIfAbsent(reqApi, (long) 1);
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
