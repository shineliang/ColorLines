package com.shine.app.game.colorlines.obj;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import com.shine.app.game.colorlines.ui.RankTable;
import com.shine.app.game.colorlines.ui.UiConstants;

public class GameOverHandler implements Observer {

	public GameOverHandler() {
		GameOver gameOver = (GameOver) NameRegister.getInstance().getObj(
				UiConstants.GAME_OVER);
		if (gameOver != null) {
			gameOver.addObserver(this);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		IScore currentScore = (IScore) NameRegister.getInstance().getObj(
				UiConstants.CURRENT_SCORE_NAME);
		RankList rankList = (RankList) NameRegister.getInstance().getObj(
				UiConstants.RANK_LIST);
		if (currentScore != null && rankList != null) {
			RankEntry entry = new RankEntry();
			entry.setScore(currentScore.getScore());
			int index = rankList.add(entry);
			if (index != -1) {
				// add successfully
				showRankTable(index);
			}
		}
	}

	private void showRankTable(int index) {
		JFrame frame = new JFrame();
		frame.setTitle("Rank Table");
		frame.setSize(300, 580);
		frame.add(new RankTable(index));
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		RankList.save();
	}

}
