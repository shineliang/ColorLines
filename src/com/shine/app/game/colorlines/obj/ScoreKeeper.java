package com.shine.app.game.colorlines.obj;

import java.util.Observable;

import com.shine.app.game.colorlines.ui.exception.InvalidBallNumberException;

public class ScoreKeeper extends Observable implements IScore {

	private int score = 0;

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public synchronized void setScore(int score) {
		this.score = score;
		setChanged();
		notifyObservers();
	}

	@Override
	public void passBalls(int number) {
		if (number < 5) {
			throw new InvalidBallNumberException();
		}

		setScore(getScore() + addScore(number));
	}

	@Override
	public void passBallsInMultiLines(int number, int xLines) {
		if (xLines < 1 || xLines > 4)
			throw new InvalidBallNumberException();

		if (number < (xLines * 4 + 1))
			throw new InvalidBallNumberException();

		setScore(getScore() + addScore(number) * xLines);
	}

	private int addScore(int number) {
		return number * 2 + (number - 5) * (number - 5);
	}
}
