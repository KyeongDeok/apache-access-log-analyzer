package com.daou.logpjt;

import java.util.Scanner;
import com.daou.logpjt.view.LogReqWithResCodeView;
import com.daou.logpjt.view.LongTimeReqView;
import com.daou.logpjt.controller.AbstractController;
import com.daou.logpjt.controller.BrowserFromReqController;
import com.daou.logpjt.controller.LogReqCountController;
import com.daou.logpjt.controller.LogResCodeDistController;
import com.daou.logpjt.view.AbstractView;
import com.daou.logpjt.view.HomeView;

/*
 * 명령어 실행 클래스 입니다.
 */

public class Execute {
	public enum Sel {
		LONG_TIME_REQ_API,
		LOG_REQAPI_WITH_RESCODE_VIEW,
		LOG_REQ_COUNT,
		LOG_RES_CODE_DIST,
		BROWSER_FROM_REQ,
		EXIT;
		
		public static Sel getByValue(int value) {
            return values()[value-1];
        }
	}
	public static void execute() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		int num;
		
		while(flag) {
    		HomeView.getInstance().render();
    		AbstractView v;
    		AbstractController c;
				num = sc.nextInt();
				Sel s = Sel.getByValue(num);
				
    			switch(s) {
    			case LONG_TIME_REQ_API:
    				v = new LongTimeReqView();
    				v.render();
    				break;
    				
    			case LOG_REQAPI_WITH_RESCODE_VIEW:
    				v = new LogReqWithResCodeView();
    				v.render();
    				break;
    				
    			case LOG_REQ_COUNT:
    				c = new LogReqCountController();
    				c.run();
    				break;
    				
    			case LOG_RES_CODE_DIST:
    				c = new LogResCodeDistController();
    				c.run();
    				break;
    				
    			case BROWSER_FROM_REQ:
    				c = new BrowserFromReqController();
    				c.run();
    				break;
    				
    			case EXIT:
    				flag = false;
    				break;
    				
				default:
					flag = false;
    				break;
    			}
    			
        	if(!flag) {System.out.println("프로그램을 종료합니다.");}
        }    	
		
		sc.close();
	}
}