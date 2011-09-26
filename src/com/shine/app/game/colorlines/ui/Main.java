package com.shine.app.game.colorlines.ui;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setTitle("Color Lines");
		frame.setSize(700, 500);
		frame.add(new ChessBoard());
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
