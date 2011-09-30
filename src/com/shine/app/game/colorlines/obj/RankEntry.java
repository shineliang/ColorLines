package com.shine.app.game.colorlines.obj;

import java.io.Serializable;

public class RankEntry implements Comparable<RankEntry>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3409796155673119561L;
	private String name = null;
	private int score = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(RankEntry entry) {
		if (entry == null) {
			return 1;
		}

		if (this == entry) {
			return 0;
		}

		return this.score - entry.score;
	}

}
