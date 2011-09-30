package com.shine.app.game.colorlines.action;

import java.util.List;

import com.shine.app.game.colorlines.obj.IGameOver;
import com.shine.app.game.colorlines.obj.NameRegister;
import com.shine.app.game.colorlines.ui.Cell;
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
		List<Cell> emptyCells = ChessBoard.getInstance().getGridBase()
				.getEmptyCells();
		int emptySize = emptyCells.size();
		cells = new NextCells(UiConstants.NEXT_CELL_NUMBER, emptyCells)
				.generateCells();
		for (int i = 0; i < UiConstants.NEXT_CELL_NUMBER; i++) {
			ITyper cell = cells[i];
			if (cell != null) {
				cell.setType(types[i]);
			}
		}
		if (emptySize <= UiConstants.NEXT_CELL_NUMBER) {
			Object over = NameRegister.getInstance().getObj(
					UiConstants.GAME_OVER);
			if (over instanceof IGameOver) {
				((IGameOver) over).gameOver();
			}
			return;
		}
	}

}
