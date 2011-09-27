package com.shine.app.game.colorlines.action;

public class BackgroundAction implements IActionWarp{
	
	private volatile Thread blinker;

	@Override
	public Thread getBlinker() {
		return blinker;
	}
	
	public void stop() {
		blinker = null;
	}
}
