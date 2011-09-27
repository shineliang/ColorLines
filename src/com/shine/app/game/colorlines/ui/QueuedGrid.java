package com.shine.app.game.colorlines.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class QueuedGrid extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3150780851584706548L;
	private Cell[] cells = null;

	public QueuedGrid() {
		init();
	}

	private void init() {
		this.setLayout(new GridLayout(1, UiConstants.NEXT_CELL_NUMBER, 0, 0));
		Dimension dimension = new Dimension(UiConstants.CELL_WIDTH
				* UiConstants.NEXT_CELL_NUMBER, UiConstants.CELL_HEIGHT);
		this.setPreferredSize(dimension);
		initCells();
	}

	private void initCells() {
		cells = new Cell[UiConstants.NEXT_CELL_NUMBER];
		for (int i = 0; i < UiConstants.NEXT_CELL_NUMBER; i++) {
			cells[i] = new Cell();
		}
		for (Component comp : this.getComponents()) {
			this.remove(comp);
		}
		for (int i = 0; i < UiConstants.NEXT_CELL_NUMBER; i++) {
			this.add(cells[i]);
		}
	}

	public ITyper getCell(int index) {
		return cells[index];
	}
}
