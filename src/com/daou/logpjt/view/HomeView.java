package com.daou.logpjt.view;

import com.daou.logpjt.model.LogDataModel;

/*
 * view를 담당하는 클래스
 * command 클래스의 리턴 값을 받아 실행된다.
 * view 메소드 네임 바꿔야 함
 */
public class HomeView extends AbstractView {
    public LogDataModel show () {
		System.out.println("");
		System.out.println("원하는 조건을 선택하세요. (1-6)");
		System.out.println("조건 1. 특정 시간대의 응답시간이 오래 걸린 Request API 추출 (오래 걸린다는 기준은 바뀔 수 있어야 함. 예)1초이상, 2초이상 ....)");
		System.out.println("조건 2. 특정 시간대의 HTTP 응답코드(200, 500, 304 등)와 METHOD(POST, GET, PUT, DELETE) 조건으로 정적 리소스(js, image, css)가 아닌 Request API 검색 (정렬은 호출된 API 건수에 따라 내림차순으로 정렬)");
		System.out.println("조건 3. 시간대별 요청 횟수 카운트");
		System.out.println("조건 4. 시간대별 HTTP 응답코드 분포 (1시간 단위)");
		System.out.println("조건 5. 시간대별 Request 정보에서 Client-Agent 정보를 추출하여 어떤 브라우저(디바이스)에서 접속 했는지 추출");
		System.out.println("조건 6. 종료...");
		return null;
    }
	public static class InnerHomeView {
        private static final HomeView instance = new HomeView();
    }
    public static HomeView getInstance() {
        return InnerHomeView.instance;
    }
}
