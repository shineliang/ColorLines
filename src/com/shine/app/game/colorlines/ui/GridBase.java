package com.shine.app.game.colorlines.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.shine.app.game.colorlines.action.CellAction;

public class GridBase extends JPanel implements ISelectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2463478503212506271L;
	private Cell[][] cellsMap = null;
	private Cell selectedCell = null;
	private CellAction cellAction = null;

	public GridBase() {
		init();
	}

	private void init() {
		this.setLayout(new GridLayout(UiConstants.ROW, UiConstants.COL, 0, 0));
		Dimension dimension = new Dimension(UiConstants.CELL_WIDTH
				* UiConstants.X_SIZE, UiConstants.CELL_HEIGHT
				* UiConstants.Y_SIZE);
		this.setPreferredSize(dimension);
		cellAction = new CellAction(this);
		initCells();
	}

	private void initCells() {
		cellsMap = new Cell[UiConstants.ROW][UiConstants.COL];
		for (int row = 0; row < cellsMap.length; row++) {
			for (int col = 0; col < cellsMap[row].length; col++) {
				Cell cell = new Cell();
				cellsMap[row][col] = cell;
				cell.addActionListener(cellAction);
				cell.addPropertyChangeListener("type", cellAction);
			}
		}
		for (Component comp : this.getComponents()) {
			this.remove(comp);
		}
		for (int row = 0; row < cellsMap.length; row++) {
			for (int col = 0; col < cellsMap[row].length; col++) {
				this.add(cellsMap[row][col]);
			}
		}
		cellAction.prepareNext();
	}

	public Cell getCell(int x, int y) {
		return cellsMap[y][x];
	}

	public Cell getCellByPos(int row, int col) {
		return cellsMap[row][col];
	}

	public ITyper getCellByPosition(int x, int y) {
		return cellsMap[y / UiConstants.ROW][y / UiConstants.COL];
	}

	public List<Cell> getEmptyCells() {
		List<Cell> list = new ArrayList<Cell>();
		for (int row = 0; row < cellsMap.length; row++) {
			for (int col = 0; col < cellsMap[row].length; col++) {
				Cell cell = cellsMap[row][col];
				if (cell.isEmpty()) {
					list.add(cell);
				}
			}
		}
		return list;
	}

	@Override
	public Cell getSelectedCell() {
		return selectedCell;
	}

	@Override
	public void setSelectedCell(Cell selectedCell) {
		if (selectedCell != null) {
			if (this.selectedCell != null) {
				this.selectedCell.stopFlash();
			}
			selectedCell.startFlash();
		} else {
			if (this.selectedCell != null) {
				this.selectedCell.stopFlash();
			}
		}
		this.selectedCell = selectedCell;

	}

	public int[][] buildPathMap() {
		int[][] map = new int[UiConstants.ROW][UiConstants.COL];
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				Cell cell = cellsMap[row][col];
				if (cell.isEmpty()) {
					map[row][col] = 0;
				} else {
					map[row][col] = 1;
				}
				// System.out.print(map[row][col]);
			}
			// System.out.println();
		}
		// System.out.println();
		return map;
	}
}
