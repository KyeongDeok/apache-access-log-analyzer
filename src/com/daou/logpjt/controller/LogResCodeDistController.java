package com.daou.logpjt.controller;

import java.util.concurrent.ConcurrentHashMap;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.model.LogDataModelAndView;
import com.daou.logpjt.util.LogTimeUtil;
import com.daou.logpjt.util.RegularExp;

public class LogResCodeDistController extends AbstractController {
	protected LogResCodeDistController(LogDataModel model) {
		super(model);
	}

	public LogResCodeDistController() {
	}

	private static ConcurrentHashMap<Integer, ConcurrentHashMap<String, Long>> resCodeHm = new ConcurrentHashMap<>();

	public LogDataModelAndView show() {
		LogDataModelAndView mv = new LogDataModelAndView();
		mv.setViewName("LogResCodeDistView");
		mv.addObject("resCodeHm", resCodeHm);
		return mv;
	}

	public synchronized void process(String log) {
		long cnt = 0;
		int timeKey = LogTimeUtil.getHourOfDay(parse.doParse(RegularExp.LOGTIME, log));
		String resCode = parse.doParse(RegularExp.RESCODE, log);

		synchronized (resCode) {
			if (resCodeHm.containsKey(timeKey)) {
				if (resCodeHm.get(timeKey).containsKey(resCode)) {
					resCodeHm.get(timeKey).compute(resCode, (k, v) -> v + 1);
				} else {
					resCodeHm.get(timeKey).put(resCode, cnt + 1);
				}
			} else {
				ConcurrentHashMap<String, Long> m = new ConcurrentHashMap<>();
				m.put(resCode, cnt + 1);
				resCodeHm.put(timeKey, m);
			}
		}
	}
}
