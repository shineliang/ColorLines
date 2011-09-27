package com.shine.app.game.colorlines.ui;

import javax.swing.JPanel;

public class ChessBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6729093943683616607L;
	private static ChessBoard instance = new ChessBoard();
	private GridBase gridBase = null;
	private QueuedGrid queuedGrid = null;

	private ChessBoard() {
		init();
	}

	public static ChessBoard getInstance() {
		return instance;
	}

	private void init() {
		if (queuedGrid == null) {
			queuedGrid = new QueuedGrid();
		}
		this.add(queuedGrid);

		if (gridBase == null)
			gridBase = new GridBase();
		this.add(gridBase);
	}

	public GridBase getGridBase() {
		return gridBase;
	}

	public void setGridBase(GridBase gridBase) {
		if (gridBase != null && this.gridBase != gridBase) {
			this.remove(this.gridBase);
			this.gridBase = gridBase;
			this.add(this.gridBase);
		}

	}

	public QueuedGrid getQueuedGrid() {
		return queuedGrid;
	}

	public void setQueuedGrid(QueuedGrid queuedGrid) {
		if (queuedGrid != null && this.queuedGrid != queuedGrid) {
			this.remove(this.queuedGrid);
			this.queuedGrid = queuedGrid;
			this.add(this.queuedGrid);
		}
	}

}
