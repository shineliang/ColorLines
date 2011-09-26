package com.shine.app.game.colorlines.ui;

import javax.swing.JButton;

public class Cell extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -201838364196140660L;

	private CellType type = CellType.T0;

	public Cell() {
		init();
	}

	private void init() {
		setType(CellType.T0);
	}

	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
		this.setIcon(this.type.getImage());
	}

}
