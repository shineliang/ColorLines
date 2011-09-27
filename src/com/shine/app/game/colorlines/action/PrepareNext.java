package com.shine.app.game.colorlines.action;

import com.shine.app.game.colorlines.ui.CellType;
import com.shine.app.game.colorlines.ui.ChessBoard;
import com.shine.app.game.colorlines.ui.ITyper;
import com.shine.app.game.colorlines.ui.QueuedGrid;
import com.shine.app.game.colorlines.ui.UiConstants;

public class PrepareNext {

	private CellType[] types;
	private ITyper[] cells;

	public PrepareNext() {
		init();
	}

	private void init() {
		types = new NextTypes(UiConstants.NEXT_CELL_NUMBER).generateTypes();
	}

	public void prepareQueuedGrid() {
		QueuedGrid queuedGrid = ChessBoard.getInstance().getQueuedGrid();
		for (int i = 0; i < UiConstants.NEXT_CELL_NUMBER; i++) {
			queuedGrid.getCell(i).setType(types[i]);
		}
	}

	public void fillGridBase() {
		cells = new NextCells(UiConstants.NEXT_CELL_NUMBER, ChessBoard
				.getInstance().getGridBase().getEmptyCells()).generateCells();
		for (int i = 0; i < UiConstants.NEXT_CELL_NUMBER; i++) {
			ITyper cell = cells[i];
			if (cell != null) {
				cell.setType(types[i]);
			}
		}
	}

}
