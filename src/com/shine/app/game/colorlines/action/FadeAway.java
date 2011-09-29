package com.shine.app.game.colorlines.action;

import java.util.concurrent.CountDownLatch;

import com.shine.app.game.colorlines.ui.Cell;
import com.shine.app.game.colorlines.ui.CellType;

public class FadeAway implements Runnable {

	private Cell cell = null;
	private CellType type = null;
	private CountDownLatch startSignal = null;
	private CountDownLatch doneSignal = null;

	public FadeAway(Cell cell, CellType type) {
		this.cell = cell;
		this.type = type;
	}

	public FadeAway(Cell cell, CellType type, CountDownLatch startSignal,
			CountDownLatch doneSignal) {
		this.cell = cell;
		this.type = type;
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	@Override
	public void run() {
		if (startSignal != null) {
			try {
				startSignal.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			cell.setIcon(type.getImage());
			Thread.sleep(200);
			cell.setIcon(CellType.T0.getImage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (doneSignal != null) {
			doneSignal.countDown();
		}
	}

}
