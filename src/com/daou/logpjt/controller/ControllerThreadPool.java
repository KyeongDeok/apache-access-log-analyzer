package com.daou.logpjt.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.daou.logpjt.model.LogFileModel;

public class ControllerThreadPool {
	
	ExecutorService executor;
	List <Path> filepaths;
	
	public ControllerThreadPool() {
		executor = Executors.newCachedThreadPool();
		try {
			filepaths = LogFileModel.getFilePaths();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void assignThreadtoController(ControllerExecute controllerExecuter) {
		for(Path path : filepaths) {
			controllerExecuter.setFilePath(path.toString());
			executor.execute(controllerExecuter);
		}
		executor.shutdown();
	}
}
