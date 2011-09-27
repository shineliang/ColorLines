package com.shine.app.game.colorlines.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.shine.app.game.colorlines.action.FlashCell;
import com.shine.app.game.colorlines.action.MoveCell;
import com.shine.app.game.colorlines.algorithm.astar.PathFinderAdapter;

public class Cell extends JButton implements ActionListener, ITyper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -201838364196140660L;
	private CellType type = null;
	private ISelectable selectable = null;
	private FlashCell flasher = null;

	public Cell() {
		init(null);
	}

	public Cell(ISelectable selectable) {
		init(selectable);
	}

	private void init(ISelectable selectable) {
		Dimension dimension = new Dimension(UiConstants.CELL_WIDTH,
				UiConstants.CELL_HEIGHT);
		setPreferredSize(dimension);
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setFocusable(false);
		setRolloverEnabled(false);
		setType(CellType.T0);
		flasher = new FlashCell(this);
		if (selectable != null) {
			this.selectable = selectable;
			addActionListener(this);
		}
	}

	@Override
	public CellType getType() {
		return type;
	}

	@Override
	public void setType(CellType type) {
		this.type = type;
		this.setIcon(this.type.getImage());
	}

	@Override
	public boolean isEmpty() {
		return type == CellType.T0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Cell previousCell = getSelectedCell();
		if (previousCell != this) {
			if (this.isEmpty()) {
				if (previousCell != null) {
					// move
					previousCell.stopFlash();
					if (moveCell(previousCell)) {
						setSelectedCell(null);
					}
				}
			} else {
				if (previousCell != null) {
					previousCell.stopFlash();
				}
				this.startFlash();
				setSelectedCell(this);
			}
		}
	}

	private boolean moveCell(Cell from) {
		MoveCell move = new MoveCell(from, this,
				new PathFinderAdapter().searchPath(from, this));
		return move.start();
	}

	private Cell getSelectedCell() {
		Cell cell = null;
		if (selectable != null) {
			cell = selectable.getSelectedCell();
		}
		return cell;
	}

	private void setSelectedCell(Cell selectedCell) {
		if (selectable != null) {
			selectable.setSelectedCell(selectedCell);
		}
	}

	public void startFlash() {
		flasher.start();
	}

	public void stopFlash() {
		flasher.stop();
	}
}
