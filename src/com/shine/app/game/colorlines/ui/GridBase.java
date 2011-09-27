package com.shine.app.game.colorlines.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GridBase extends JPanel implements ISelectable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2463478503212506271L;
	private Cell[][] cellsMap = null;
	private Cell selectedCell = null;

	public GridBase() {
		init();
	}

	private void init() {
		this.setLayout(new GridLayout(UiConstants.X_SIZE, UiConstants.Y_SIZE,
				0, 0));
		Dimension dimension = new Dimension(UiConstants.CELL_WIDTH
				* UiConstants.X_SIZE, UiConstants.CELL_HEIGHT
				* UiConstants.Y_SIZE);
		this.setPreferredSize(dimension);
		initCells();
	}

	private void initCells() {
		cellsMap = new Cell[UiConstants.X_SIZE][UiConstants.Y_SIZE];
		for (int j = 0; j < UiConstants.Y_SIZE; j++) {
			for (int i = 0; i < UiConstants.X_SIZE; i++) {
				cellsMap[i][j] = new Cell(this);
			}
		}
		for (Component comp : this.getComponents()) {
			this.remove(comp);
		}
		for (int j = 0; j < UiConstants.Y_SIZE; j++) {
			for (int i = 0; i < UiConstants.X_SIZE; i++) {
				this.add(cellsMap[i][j]);
			}
		}

	}

	public Cell getCell(int x, int y) {
		return cellsMap[x][y];
	}

	public ITyper getCellByPosition(int x, int y) {
		return cellsMap[x / UiConstants.X_SIZE][y / UiConstants.Y_SIZE];
	}

	public List<Cell> getEmptyCells() {
		List<Cell> list = new ArrayList<Cell>();
		for (int j = 0; j < UiConstants.Y_SIZE; j++) {
			for (int i = 0; i < UiConstants.X_SIZE; i++) {
				Cell cell = cellsMap[i][j];
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
		this.selectedCell = selectedCell;
	}

	public int[][] buildPathMap() {
		int[][] map = new int[UiConstants.X_SIZE][UiConstants.Y_SIZE];
		for (int j = 0; j < UiConstants.Y_SIZE; j++) {
			for (int i = 0; i < UiConstants.X_SIZE; i++) {
				Cell cell = cellsMap[i][j];
				if (cell.isEmpty()) {
					map[i][j] = 0;
				} else {
					map[i][j] = 1;
				}
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		return map;
	}
}
