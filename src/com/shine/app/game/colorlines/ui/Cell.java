package com.shine.app.game.colorlines.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

import com.shine.app.game.colorlines.action.FlashCell;

public class Cell extends JButton implements ITyper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -201838364196140660L;
	private CellType type = null;
	private FlashCell flasher = null;

	public Cell() {
		init();
	}

	private void init() {
		Dimension dimension = new Dimension(UiConstants.CELL_WIDTH,
				UiConstants.CELL_HEIGHT);
		setPreferredSize(dimension);
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.LIGHT_GRAY);
		setFocusable(false);
		setRolloverEnabled(false);
		setType(CellType.T0);
		flasher = new FlashCell(this);
	}

	@Override
	public CellType getType() {
		return type;
	}

	@Override
	public synchronized void setType(CellType type) {
		CellType oldType = this.type;
		this.type = type;
		super.setIcon(this.type.getImage());
		firePropertyChange("type", oldType, this.type);
	}

	@Override
	public synchronized void setIcon(Icon defaultIcon) {
		super.setIcon(defaultIcon);
	}

	@Override
	public boolean isEmpty() {
		return type == CellType.T0;
	}

	public void startFlash() {
		flasher.start();
	}

	public void stopFlash() {
		flasher.stop();
	}

	public int getXPos() {
		return getX() / UiConstants.CELL_WIDTH;
	}

	public int getYPos() {
		return getY() / UiConstants.CELL_HEIGHT;
	}

}
