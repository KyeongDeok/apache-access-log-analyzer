package com.daou.logpjt.controller;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogTimeUtil;
import com.daou.logpjt.util.RegularExp;

public class BrowserFromReqController extends AbstractController {
	private ConcurrentHashMap <Integer, HashSet<String>> browserHm = new ConcurrentHashMap<>();
	public BrowserFromReqController(LogDataModel model) {
		super(model);
	}
	
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
}
