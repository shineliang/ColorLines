package com.shine.app.game.colorlines.util;


public class CUtlity {

	public static void startThreadInPool(Runnable runnable) {
		ColorLinesThreadPool.getInstance().getExecutorServices()
				.execute(runnable);
	}

}
