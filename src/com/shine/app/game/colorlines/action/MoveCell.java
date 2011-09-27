package com.shine.app.game.colorlines.action;

import java.util.List;

import com.shine.app.game.colorlines.ui.Cell;
import com.shine.app.game.colorlines.ui.CellType;
import com.shine.app.game.colorlines.ui.ITyper;
import com.shine.app.game.colorlines.util.CUtlity;
import com.shine.app.game.colorlines.util.ColorLinesThreadPool;

public class MoveCell implements Runnable {

	private static final int RELAX_INTERVAL = 30;
	private volatile Thread blinker;
	private ITyper src = null;
	private ITyper dest = null;
	private List<Cell> path = null;

	public MoveCell(ITyper source, ITyper destination, List<Cell> path) {
		this.src = source;
		this.dest = destination;
		this.path = path;
	}

	@Override
	public void run() {
		try {
			CellType type = src.getType();
			for (ITyper cell : path) {
				if (cell != dest) {
					CUtlity.startThreadInPool(new FadeAway(cell, type));
					Thread.sleep(RELAX_INTERVAL);
				} else {
					cell.setType(type);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean start() {
		boolean res = isValidCondition();
		if (res) {
			blinker = new Thread(this);
			ColorLinesThreadPool.getInstance().getExecutorServices()
					.execute(blinker);
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
