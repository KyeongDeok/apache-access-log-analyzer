package com.daou.logpjt.view;

import java.util.Scanner;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.util.LogTimeUtil;

public class LongTimeReqView {
	public LogDataModel show() {
		Scanner s = new Scanner(System.in);
		LogDataModel model = new LogDataModel("LongTimeReqController/run");
		System.out.println("시작 시간을 입력해주세요. : (ex 18/May/2020:00:01:29)");
		String start = s.nextLine();
		model.addAttribute("startTime", LogTimeUtil.getUnixTime(start));
		System.out.println("끝나는 시간을 입력해주세요.  : (ex 18/May/2020:11:11:30)");
		String end = s.nextLine();
		model.addAttribute("endTime", LogTimeUtil.getUnixTime(end));
		System.out.println("응답에 걸린 시간을 입력해주세요 : (ex 1,2,3...)");
		double resTime = s.nextDouble();
		model.addAttribute("resTime",resTime);
		System.out.println(start+" - "+end+" 사이의  "+resTime+"이상 걸린 request API: ");
		
		s.close();
		return model;
	}
}