package com.daou.logpjt.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFileModel {
	public static List<BufferedReader> getFileStreams() throws IOException {
		List<Path> filepaths = getFilePaths();
		List<BufferedReader> brlist = new LinkedList<>();
		for (Path filepath : filepaths) {
			System.out.println(filepath.toString());
			brlist.add(new BufferedReader(new FileReader(filepath.toString())));
		}
		return brlist;
	}

	public static List<Path> getFilePaths() throws IOException {
		List<Path> paths = Files.walk(Paths.get("C:\\Users\\kdpark\\Desktop\\testLog")).filter(Files::isDirectory)
				.flatMap(e -> {
					try {
						return Files.walk(e);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return null;
				}).filter(Files::isRegularFile).collect(Collectors.toList());
		return paths;
	}

	public static class InnerLogFileModel {
		private static final LogFileModel instance = new LogFileModel();
	}

	public static LogFileModel getInstance() {
		return InnerLogFileModel.instance;
	}
}