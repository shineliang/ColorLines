package com.shine.app.game.colorlines.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.shine.app.game.colorlines.action.PrepareNext;
import com.shine.app.game.colorlines.obj.IGameOver;
import com.shine.app.game.colorlines.obj.NameRegister;
import com.shine.app.game.colorlines.obj.SystemStartup;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Exception {
		SystemStartup.startup();

		JFrame frame = new JFrame();
		frame.setTitle("Color Lines");
		frame.setSize(480, 580);
		ChessBoard chessBoard = ChessBoard.getInstance();
		chessBoard.init();

		frame.add(chessBoard);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		SystemStartup.afterStartup();

		addCells();

		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Object over = NameRegister.getInstance().getObj(
						UiConstants.GAME_OVER);
				if (over instanceof IGameOver) {
					((IGameOver) over).gameOver();
				}
			}

		});

	}

	private static void addCells() throws InterruptedException {
		new PrepareNext().fillGridBase();
		new PrepareNext().fillGridBase();
	}

}
