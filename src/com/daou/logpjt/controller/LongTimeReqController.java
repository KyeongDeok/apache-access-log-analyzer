package com.daou.logpjt.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogTimeUtil;
import com.daou.logpjt.util.RegularExp;

//TODO:: 리플레션 없애고 각 쓰레드별로 파일을 저장해야함.
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
		long startTime = (long) LogDataModel.getAttribute("startTime");
		long endTime = (long) LogDataModel.getAttribute("endTime");
		long epoch = LogTimeUtil.getEpoch(logTime);

		if (startTime > epoch || epoch > endTime)
			return;
		double resTime = (double) LogDataModel.getAttribute("resTime");
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
				//123.123.123.123 - - [12/Apr/2018:17:03:50 +0900] "GET /api/aaaa HTTP/1.1" 200 34 1468 "https://m.naver.com" "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E216 NAVER(inapp; search; 580; 8.6.3; 7)"  
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}		
}