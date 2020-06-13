package com.daou.logpjt.view;

import java.util.Scanner;

import com.daou.logpjt.model.LogDataModel;
import com.daou.logpjt.util.LogTimeUtil;

public class LongTimeReqView extends AbstractView {
	public LogDataModel show() {
		Scanner s = new Scanner(System.in);
		LogDataModel model = new LogDataModel("LongTimeReqController/run");
		System.out.println("���� �ð��� �Է����ּ���: (ex 18/May/2020:00:01:29)");
		String start = s.next();
		model.addAttribute("startTime", LogTimeUtil.getUnixTime(start));
		System.out.println("������ �ð��� �Է��� �ּ���: (ex 18/May/2020:11:11:30)");
		String end = s.next();
		model.addAttribute("endTime", LogTimeUtil.getUnixTime(end));
		System.out.println("x�� �̻�: (ex 1,2,3...)");
		double resTime = s.nextDouble();
		model.addAttribute("resTime",resTime);
		System.out.println(start+" - "+end+" �ð� ���� "+resTime+"�� �̻� �ɸ� request API: ");
		return model;
	}
}