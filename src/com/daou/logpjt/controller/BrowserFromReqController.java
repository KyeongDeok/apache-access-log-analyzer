package com.daou.logpjt.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogTimeUtil;
import com.daou.logpjt.util.RegularExp;

public class BrowserFromReqController extends AbstractController {
	private ConcurrentHashMap <Integer, HashSet<String>> browserHm = new ConcurrentHashMap<>();
	
	public BrowserFromReqController() {};
	
	public LogDataModelAndView show() {
		LogDataModelAndView mv = new LogDataModelAndView();
		mv.setViewName("BrowserFromReqView");
		mv.addObject("browserHm", browserHm);
		return mv;
	}
	
	public void process(String log) {
        int timeKey = LogTimeUtil.getHourOfDay(parse.doParse(RegularExp.LOGTIME,log));
		String device = parse.doParse(RegularExp.DEVICE,log);
		if(device == null) return;
			if(browserHm.containsKey(timeKey)) {
				if(browserHm.get(timeKey).contains(device)) {
					return;
				}else {
					browserHm.get(timeKey).add(device);
				}
			}else {
				HashSet <String> s = new HashSet<>();
				s.add(device);
				browserHm.put(timeKey,s);
			}
	}

	@Override
	public void run() {
		String log;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			
			while((log = br.readLine()) != null) {
				process(log);
			}
			
			br.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
}
