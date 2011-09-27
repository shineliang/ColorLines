package com.shine.app.game.colorlines.action;

import java.util.List;
import java.util.Random;

import com.shine.app.game.colorlines.ui.Cell;
import com.shine.app.game.colorlines.ui.ITyper;

public class NextCells {

	private int count = 0;
	private List<Cell> emptyCells = null;

	public NextCells(int count, List<Cell> emptyCells) {
		this.count = count;
		this.emptyCells = emptyCells;
	}

	public ITyper[] generateCells() {
		ITyper[] cells = new ITyper[count];
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			try {
				cells[i] = generateCell(random);
			} catch (Throwable e) {
				// ignore
			}
		}
		return cells;
	}

	private ITyper generateCell(Random random) {
		int index = random.nextInt(emptyCells.size());
		ITyper cell = emptyCells.remove(index);
		return cell;
	}

}
