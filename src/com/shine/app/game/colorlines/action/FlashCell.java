package com.shine.app.game.colorlines.action;

import com.shine.app.game.colorlines.ui.CellType;
import com.shine.app.game.colorlines.ui.ITyper;

public class FlashCell implements Runnable {

	private static final int RELAX_INTERVAL = 200;
	private ITyper cell = null;

	private volatile Thread blinker;

	public FlashCell(ITyper cell) {
		this.cell = cell;
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (currentThread == blinker) {
			cell.setIcon(CellType.T0.getImage());
			if (cell.getType() == CellType.T0) {
				break;
			}
			try {
				Thread.sleep(RELAX_INTERVAL / 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// change the icon back
			cell.setIcon(cell.getType().getImage());
			try {
				Thread.sleep(RELAX_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		blinker = new Thread(this);
		blinker.start();
	}

	public void stop() {
		blinker = null;
	}

}
