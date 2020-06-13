package com.daou.logpjt.view;

import com.daou.logpjt.model.LogDataModel;


public class HomeView extends AbstractView {
    public LogDataModel show () {
		System.out.println("");
		System.out.println("조건을 선택해주세요. (1-6)");
		System.out.println("조건 1. 특정 시간동안에 특정시간 이상 걸린 Request API 추출 (1초이상, 2초 이상 ...)");
		System.out.println("조건 2. HTTP response(200, 500, 304 등) METHOD(POST, GET, PUT, DELETE) 를 가지고 정적 리소스가 아닌(js, image, css) Request API 추출(가장 많이 호출된 순서로 내림차순)");
		System.out.println("조건 3. request API 카운트");
		System.out.println("조건 4. HTTP response code 분포 추출");
		System.out.println("조건 5. HTTP requset log에서부터 Client Device 추출");
		System.out.println("조건 6. 프로그램을 종료합니다...");
		return null;
    }
	public static class InnerHomeView {
        private static final HomeView instance = new HomeView();
    }
    public static HomeView getInstance() {
        return InnerHomeView.instance;
    }
}
