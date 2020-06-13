package com.daou.logpjt.view;

import java.util.Scanner;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.util.LogTimeUtil;

public class LogReqWithResCodeView extends AbstractView {
	public LogDataModel show() {
		Scanner sc = new Scanner(System.in);
		LogDataModel model = new LogDataModel("LogReqWithResCodeController/run");
		
		System.out.println("시작 시간을 입력해주세요. : (ex 18/May/2020:00:01:29)");
		String start = sc.next();
		model.addAttribute("startTime",LogTimeUtil.getUnixTime(start));
		
		System.out.println("끝나는 시간을 입력해주세요.  : (ex 18/May/2020:11:11:30)");
		String end = sc.next();
		model.addAttribute("endTime",LogTimeUtil.getUnixTime(end));
		
		System.out.println("Enter Request Method: (ex get,post...)");
		String method = sc.next().toUpperCase();
		model.addAttribute("method",method);
		
		System.out.println("Enter Response Code: (ex 200,302...)");
		String code = sc.next();
		model.addAttribute("code",code);
		
		System.out.println(start+" - "+end+"사이의 "+method+" method 이고, 응답코드가"+code+"인 request API: ");
		return model;
	}
}
