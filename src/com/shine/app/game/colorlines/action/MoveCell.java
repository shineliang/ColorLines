package com.shine.app.game.colorlines.action;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.shine.app.game.colorlines.ui.Cell;
import com.shine.app.game.colorlines.ui.CellType;
import com.shine.app.game.colorlines.ui.ITyper;

public class MoveCell implements Runnable {

	private ITyper src = null;
	private ITyper dest = null;
	private List<Cell> path = null;
	private CountDownLatch doneSignal = null;

	public MoveCell(ITyper source, ITyper destination, List<Cell> path) {
		this.src = source;
		this.dest = destination;
		this.path = path;
	}

	public MoveCell(ITyper source, ITyper destination, List<Cell> path,
			CountDownLatch doneSignal) {
		this.src = source;
		this.dest = destination;
		this.path = path;
		this.doneSignal = doneSignal;
	}

	@Override
	public void run() {
		CellType type = src.getType();
		for (Cell cell : path) {
			if (cell == src) {
				cell.setType(CellType.T0);
			} else if (cell == dest) {
				cell.setType(type);
			}
		}
		System.out.println("Move thread f:run() Done");
		if (doneSignal != null) {
			doneSignal.countDown();
		}
	}

	public boolean start() {
		boolean res = isValidCondition();
		if (res) {
			new Thread(this).start();
			System.out.println("Move thread f:start() Done");
		}
		return res;
	}

	private boolean isValidCondition() {
		boolean isTypeValid = src != null && !src.isEmpty() && dest != null
				&& dest.isEmpty();
		boolean isPathValid = path != null && path.size() > 1
				&& src == path.get(0) && dest == path.get(path.size() - 1);

		return isTypeValid && isPathValid;
	}

}
