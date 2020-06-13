package com.daou.logpjt.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogTimeUtil;
import com.daou.logpjt.util.RegularExp;


public class LongTimeReqController extends AbstractController {
	private static HashSet<String> reqApiHs = new HashSet<>();

	public LogDataModelAndView show() {
		LogDataModelAndView mv = new LogDataModelAndView();
		mv.setViewName("LongTimeReqReturnView");
		mv.addObject("reqApiHs", reqApiHs);
		return mv;
	}

	public  void process(String log) {
		String logTime = parse.doParse(RegularExp.LOGTIME, log);
		if (logTime == null || logTime.equals(""))
			return;
		long startTime = (long) model.getAttribute("startTime");
		long endTime = (long) model.getAttribute("endTime");
		long epoch = LogTimeUtil.getEpoch(logTime);

		if (startTime > epoch || epoch > endTime)
			return;
		double resTime = (double) model.getAttribute("resTime");
		String resDelay = parse.doParse(RegularExp.DELAY, log);
		if (resDelay == null)
			return;
		double delay = Double.parseDouble(resDelay);

		if (resTime > delay)
			return;
		String reqApi = parse.doParse(RegularExp.REQAPI, log);
		if (reqApi == null)
			return;
		reqApiHs.add(reqApi);
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