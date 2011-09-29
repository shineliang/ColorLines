package com.shine.app.game.colorlines.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.shine.app.game.colorlines.obj.ScoreRegister;

public class ChessBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6729093943683616607L;
	private static ChessBoard instance = new ChessBoard();
	private GridBase gridBase = null;
	private QueuedGrid queuedGrid = null;
	private BillBoard topScore = null;
	private BillBoard currentScore = null;

	private ChessBoard() {
	}

	public static ChessBoard getInstance() {
		return instance;
	}

	public void init() {
		JPanel panel = new JPanel();
		Dimension dimension = new Dimension(UiConstants.CELL_WIDTH
				* UiConstants.COL, UiConstants.CELL_HEIGHT);
		panel.setPreferredSize(dimension);
		panel.setLayout(new BorderLayout());

		JPanel jPanel1 = new JPanel(new BorderLayout());
		topScore = new BillBoard(ScoreRegister.getInstance().getIScore(
				UiConstants.TOP_SCORE_NAME));
		jPanel1.add(topScore, BorderLayout.SOUTH);
		panel.add(jPanel1, BorderLayout.WEST);

		JPanel jPanel3 = new JPanel();
		queuedGrid = new QueuedGrid();
		jPanel3.add(queuedGrid);
		panel.add(jPanel3, BorderLayout.CENTER);

		JPanel jPanel2 = new JPanel(new BorderLayout());
		currentScore = new BillBoard(ScoreRegister.getInstance().getIScore(
				UiConstants.CURRENT_SCORE_NAME));
		jPanel2.add(currentScore, BorderLayout.SOUTH);
		panel.add(jPanel2, BorderLayout.EAST);

		this.add(panel);

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
