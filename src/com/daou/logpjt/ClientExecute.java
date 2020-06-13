package com.daou.logpjt;

import java.util.Scanner;

import com.daou.logpjt.controller.BrowserFromReqController;
import com.daou.logpjt.controller.ControllerExecute;
import com.daou.logpjt.controller.ControllerThreadPool;
import com.daou.logpjt.controller.LogReqCountController;
import com.daou.logpjt.controller.LogResCodeDistController;
import com.daou.logpjt.controller.LongTimeReqController;
import com.daou.logpjt.view.AbstractView;
import com.daou.logpjt.view.HomeView;
import com.daou.logpjt.view.LongTimeReqView;

public class ClientExecute {
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
		int num;
		Scanner sc = new Scanner(System.in);
		ControllerExecute controllerExecute = null;
		ControllerThreadPool threadPool = new ControllerThreadPool();
		
		while(flag) {
    		HomeView.getInstance().render();
    		AbstractView v;
				num = sc.nextInt();
				Sel s = Sel.getByValue(num);
				
				//TODO:: 리플렉션 제거해야함.
    			switch(s) {
    			case LONG_TIME_REQ_API:
    				controllerExecute  = new ControllerExecute(new LongTimeReqController());
    				v = new LongTimeReqView();
    				v.render();
    				break;
    				
    			case LOG_REQAPI_WITH_RESCODE_VIEW:
    				controllerExecute  = new ControllerExecute(new LongTimeReqController());
    				break;
    				
    			case LOG_REQ_COUNT:
    				controllerExecute  = new ControllerExecute(new LogReqCountController());
    				break;
    				
    			case LOG_RES_CODE_DIST:
    				controllerExecute  = new ControllerExecute(new LogResCodeDistController());
    				break;
    				
    			case BROWSER_FROM_REQ:
    				controllerExecute  = new ControllerExecute(new BrowserFromReqController());
    				break;
    				
    			case EXIT:
    				flag = false;
    				break;
    				
				default:
					flag = false;
    				break;
    			}
    			
        	if(!flag) {System.out.println("시스템을 종료합니다.");}
        	
        }    	
		
		sc.close();
		threadPool.assignThreadtoController(controllerExecute);
	}
}