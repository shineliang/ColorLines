package com.shine.app.game.colorlines.ui;

import javax.swing.JFrame;

import com.shine.app.game.colorlines.action.PrepareNext;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		frame.setTitle("Color Lines");
		frame.setSize(480, 580);
		ChessBoard chessBoard = ChessBoard.getInstance();
		chessBoard.init();

		frame.add(chessBoard);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addCells();
	}

	private static void addCells() throws InterruptedException {
		new PrepareNext().fillGridBase();
		new PrepareNext().fillGridBase();
	}

}
