package com.shine.app.game.colorlines.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ColorLinesThreadPool {
	private static ColorLinesThreadPool install = new ColorLinesThreadPool();
	private ExecutorService executorServices;

	private ColorLinesThreadPool() {
		executorServices = Executors.newCachedThreadPool();
	}

	public static ColorLinesThreadPool getInstance() {
		return install;
	}

	public ExecutorService getExecutorServices() {
		return executorServices;
	}

}
