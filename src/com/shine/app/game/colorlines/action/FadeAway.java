package com.shine.app.game.colorlines.action;

import com.shine.app.game.colorlines.ui.CellType;
import com.shine.app.game.colorlines.ui.ITyper;

public class FadeAway implements Runnable {

	private ITyper cell = null;
	private CellType type = null;

	public FadeAway(ITyper cell, CellType type) {
		this.cell = cell;
		this.type = type;
	}

	@Override
	public void run() {
		try {
			cell.setType(type);
			Thread.sleep(120);
			cell.setType(CellType.T0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
