package com.shine.app.game.colorlines.action;

import com.shine.app.game.colorlines.ui.CellType;
import com.shine.app.game.colorlines.ui.ITyper;
import com.shine.app.game.colorlines.ui.exception.InvalidCellTypeState;

public class FlashCell implements Runnable {

	private static final int RELAX_INTERVAL = 200;
	private ITyper cell = null;
	private CellType originalType = null;
	private CellType currentType = null;
	private volatile Thread blinker;

	public FlashCell(ITyper cell) {
		this.cell = cell;
	}

	@Override
	public void run() {
		this.originalType = cell.getType();
		this.currentType = this.originalType;
		Thread currentThread = Thread.currentThread();
		while (currentThread == blinker) {
			changeIcon();
			try {
				Thread.sleep(RELAX_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// change the icon back
			changeIcon();
			try {
				Thread.sleep(RELAX_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void changeIcon() {
		if (originalType == CellType.T0)
			throw new InvalidCellTypeState();

		if (currentType == CellType.T0) {
			currentType = originalType;
		} else if (currentType == originalType) {
			currentType = CellType.T0;
		} else {
			throw new InvalidCellTypeState();
		}

		cell.setType(currentType);

	}

	public void start() {
		blinker = new Thread(this);
		blinker.start();
	}

	public void stop() {
		blinker = null;
	}

}
