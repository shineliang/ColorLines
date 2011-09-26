package com.shine.app.game.colorlines.ui;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.shine.app.game.colorlines.staticdata.IStatic;

public class GridBase extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2463478503212506271L;

	private Cell[][] cellsMap = null;

	public GridBase() {
		init();
	}

	private void init() {
		this.setLayout(new GridLayout(IStatic.X_WIDTH, IStatic.Y_WIDTH));
		initCells();
		cellsMap[2][3].setType(CellType.T3);
	}

	private void initCells() {
		cellsMap = new Cell[IStatic.X_WIDTH][IStatic.Y_WIDTH];
		for (int i = 0; i < IStatic.Y_WIDTH; i++) {
			for (int j = 0; j < IStatic.X_WIDTH; j++) {
				cellsMap[i][j] = new Cell();
			}
		}
		for (Component comp : this.getComponents()) {
			this.remove(comp);
		}
		for (int i = 0; i < IStatic.Y_WIDTH; i++) {
			for (int j = 0; j < IStatic.X_WIDTH; j++) {
				this.add(cellsMap[i][j]);
			}
		}

	}

	public Cell getCell(int x, int y) {
		return cellsMap[x][y];
	}

}
