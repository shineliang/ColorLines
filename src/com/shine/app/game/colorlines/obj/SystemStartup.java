package com.shine.app.game.colorlines.obj;

import com.shine.app.game.colorlines.action.PrepareNext;
import com.shine.app.game.colorlines.ui.UiConstants;

public class SystemStartup {
	public static void startup() {
		NameRegister.getInstance().addObj(UiConstants.TOP_SCORE_NAME,
				new ScoreKeeper());
		NameRegister.getInstance().addObj(UiConstants.CURRENT_SCORE_NAME,
				new ScoreKeeper());
		NameRegister.getInstance()
				.addObj(UiConstants.GAME_OVER, new GameOver());
	}

	public static void afterStartup() {
		new GameOverHandler();

		IScore topScore = (IScore) NameRegister.getInstance().getObj(
				UiConstants.TOP_SCORE_NAME);
		if (topScore != null) {
			RankList rankList = RankList.load();
			RankEntry topEntry = null;
			try {
				topEntry = rankList.get(0);
			} catch (Exception e) {
			}
			if (topEntry != null) {
				topScore.setScore(topEntry.getScore());
			} else {
				topScore.setScore(0);
			}
		}

		new PrepareNext().fillGridBase();
		new PrepareNext().fillGridBase();
	}
}
