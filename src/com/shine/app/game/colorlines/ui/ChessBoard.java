package com.shine.app.game.colorlines.ui;

import javax.swing.JPanel;

public class ChessBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6729093943683616607L;

	public ChessBoard() {
		init();
	}

	private void init() {
		this.add(new GridBase());
	}
}
