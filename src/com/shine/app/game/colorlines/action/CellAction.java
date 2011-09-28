package com.shine.app.game.colorlines.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.shine.app.game.colorlines.algorithm.astar.PathFinderAdapter;
import com.shine.app.game.colorlines.ui.Cell;
import com.shine.app.game.colorlines.ui.CellType;
import com.shine.app.game.colorlines.ui.GridBase;
import com.shine.app.game.colorlines.ui.UiConstants;
import com.shine.app.game.colorlines.util.CUtlity;

public class CellAction implements ActionListener, PropertyChangeListener {

	private GridBase gridBase = null;
	private PrepareNext next = null;
	private volatile boolean isNeedGenerateCells = false;

	public CellAction(GridBase gridBase) {
		this.gridBase = gridBase;
		next = new PrepareNext();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof Cell) {
			Cell currentCell = (Cell) source;
			Cell previousCell = getSelectedCell();
			if (previousCell != currentCell) {
				if (currentCell.isEmpty()) {
					if (previousCell != null) {
						moveCell(previousCell, currentCell);
					}
				} else {
					setSelectedCell(currentCell);
				}
			}
		}
	}

	private void moveCell(Cell from, Cell to) {
		List<Cell> path = new PathFinderAdapter().searchPath(from, to);
		if (path != null && path.size() > 0) {
			setSelectedCell(null);
			CountDownLatch countdown = new CountDownLatch(1);
			MoveCell move = new MoveCell(from, to, path, countdown);
			isNeedGenerateCells = true;
			move.start();
			try {
				countdown.await();
				System.out.println("countdown.await()");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// if need to add more cells to gridbase?
			if (isNeedGenerateCells) {
				System.out.println("isNeedGenerateCells is TRUE");
				next.fillGridBase();
				next = new PrepareNext();
			}
		}
	}

	private Cell getSelectedCell() {
		Cell cell = null;
		if (gridBase != null) {
			cell = gridBase.getSelectedCell();
		}
		return cell;
	}

	private void setSelectedCell(Cell selectedCell) {
		if (gridBase != null) {
			gridBase.setSelectedCell(selectedCell);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		Object newVal = evt.getNewValue();
		if (source instanceof Cell && newVal instanceof CellType) {
			Cell cell = (Cell) source;
			CellType type = (CellType) newVal;
			System.out.println(cell.getX() / UiConstants.CELL_WIDTH + ","
					+ cell.getY() / UiConstants.CELL_HEIGHT + ":" + type + " ["
					+ evt + "]");
			if (type != CellType.T0) {
				checkSameInLine(cell);
			}
		}
	}

	private void checkSameInLine(Cell cell) {
		if (gridBase != null && cell != null) {
			int posX = cell.getX() / UiConstants.CELL_WIDTH;
			int posY = cell.getY() / UiConstants.CELL_HEIGHT;

			List<Cell> cellList = new ArrayList<Cell>();
			cellList.add(cell);

			List<Cell> xList = new ArrayList<Cell>();
			for (int x = posX - 1, y = posY; x >= 0; --x) {
				if (!calcSameCells(cell, xList, x, y))
					break;
			}
			for (int x = posX + 1, y = posY; x < UiConstants.X_SIZE; ++x) {
				if (!calcSameCells(cell, xList, x, y))
					break;
			}
			if (xList.size() >= 4) {
				// find 5 in line
				cellList.addAll(xList);
			}

			List<Cell> yList = new ArrayList<Cell>();
			for (int x = posX, y = posY - 1; y >= 0; --y) {
				if (!calcSameCells(cell, yList, x, y))
					break;
			}
			for (int x = posX, y = posY + 1; y < UiConstants.Y_SIZE; ++y) {
				if (!calcSameCells(cell, yList, x, y))
					break;
			}
			if (yList.size() >= 4) {
				// find 5 in line
				cellList.addAll(yList);
			}

			List<Cell> xy45List = new ArrayList<Cell>();
			for (int x = posX - 1, y = posY - 1; x >= 0 && y >= 0; --x, --y) {
				if (!calcSameCells(cell, xy45List, x, y))
					break;
			}
			for (int x = posX + 1, y = posY + 1; x < UiConstants.X_SIZE
					&& y < UiConstants.Y_SIZE; ++x, ++y) {
				if (!calcSameCells(cell, xy45List, x, y))
					break;
			}
			if (xy45List.size() >= 4) {
				// find 5 in line
				cellList.addAll(xy45List);
			}

			List<Cell> xy135List = new ArrayList<Cell>();
			for (int x = posX - 1, y = posY + 1; x >= 0
					&& y < UiConstants.Y_SIZE; --x, ++y) {
				if (!calcSameCells(cell, xy135List, x, y))
					break;
			}
			for (int x = posX + 1, y = posY - 1; x < UiConstants.X_SIZE
					&& y >= 0; ++x, --y) {
				if (!calcSameCells(cell, xy135List, x, y))
					break;
			}
			if (xy135List.size() >= 4) {
				// find 5 in line
				cellList.addAll(xy135List);
			}

			if (cellList.size() > 1) {
				cleanCells(cellList);
			}

		}
	}

	private boolean calcSameCells(Cell cell, List<Cell> list, int x, int y) {
		boolean result = false;
		Cell tmpCell = gridBase.getCell(x, y);
		if (tmpCell != null && cell.getType() == tmpCell.getType()) {
			result = list.add(tmpCell);
		}

		return result;
	}

	private void cleanCells(List<Cell> cellList) {
		if (cellList != null) {
			for (Cell cell : cellList) {
				CUtlity.startThreadInPool(new FadeAway(cell, cell.getType()));
			}
			for (Cell cell : cellList) {
				cell.setType(CellType.T0);
			}
			isNeedGenerateCells = false;
			System.out.println("set isNeedGenerateCells to FALSE");
		}
	}
}
