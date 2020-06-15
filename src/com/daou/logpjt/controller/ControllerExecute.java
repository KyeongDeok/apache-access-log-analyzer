package com.daou.logpjt.controller;

public class ControllerExecute implements Runnable {

	private AbstractController controller;
	private String filePath;
	
	public ControllerExecute(AbstractController controller) { 
		setController( controller );
	}
	
	public void setController(AbstractController newController) { 
		this.controller = newController; 
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void run() {
		controller.setFilePath(filePath);
		controller.run();
	}
}