package com.shine.app.game.colorlines.obj;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ScoreRegister {
	private static ScoreRegister instance = new ScoreRegister();
	private ConcurrentMap<String, IScore> map = new ConcurrentHashMap<String, IScore>();

	private ScoreRegister() {

	}

	public static ScoreRegister getInstance() {
		return instance;
	}

	public void addIScore(String name, IScore score) {
		map.put(name, score);
	}

	public IScore getIScore(String name) {
		IScore score = map.get(name);
		if (score == null) {
			score = createScore();
			map.put(name, score);
		}

		return score;
	}

	private IScore createScore() {
		return new ScoreKeeper();
	}

}
