package com.daou.logpjt.controller;

import java.util.concurrent.ConcurrentHashMap;

import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogTimeUtil;
import com.daou.logpjt.util.RegularExp;

public class LogReqCountController extends AbstractController {
	public LogReqCountController() {
	}

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
}