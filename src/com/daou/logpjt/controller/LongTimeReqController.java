package com.daou.logpjt.controller;

import java.util.HashSet;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogTimeUtil;
import com.daou.logpjt.util.RegularExp;

/*
 * log를 처리 하는 클래스
 */
public class LongTimeReqController extends AbstractController {
	private static HashSet<String> reqApiHs = new HashSet<>();

	public LongTimeReqController(LogDataModel model) {
		super(model);
	}

	public LogDataModelAndView show() {
		LogDataModelAndView mv = new LogDataModelAndView();
		mv.setViewName("LongTimeReqReturnView");
		mv.addObject("reqApiHs", reqApiHs);
		return mv;
	}

	public synchronized void process(String log) {
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
}