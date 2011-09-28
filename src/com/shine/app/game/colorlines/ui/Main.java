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
		frame.setSize(680, 680);
		ChessBoard chessBoard = ChessBoard.getInstance();
		// GridBase gridBase = chessBoard.getGridBase();
		// ITyper cell = gridBase.getCell(2, 3);
		// cell.setType(CellType.T2);
		// gridBase.getCell(3, 3).setType(CellType.T2);
		// gridBase.getCell(4, 3).setType(CellType.T2);
		// gridBase.getCell(5, 3).setType(CellType.T2);
		// gridBase.getCell(2, 4).setType(CellType.T2);
		// gridBase.getCell(2, 5).setType(CellType.T2);
		// gridBase.getCell(2, 6).setType(CellType.T2);
		// gridBase.getCell(3, 6).setType(CellType.T2);
		// gridBase.getCell(5, 4).setType(CellType.T2);
		// gridBase.getCell(5, 5).setType(CellType.T2);
		// gridBase.getCell(5, 6).setType(CellType.T2);

		frame.add(chessBoard);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		test();
		test();
	}

	private static void test() throws InterruptedException {
		PrepareNext next = new PrepareNext();
		next.prepareQueuedGrid();
		// Thread.sleep(1000);
		next.fillGridBase();
	}

}
