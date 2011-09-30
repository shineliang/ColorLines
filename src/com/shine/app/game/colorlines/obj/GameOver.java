package com.shine.app.game.colorlines.obj;

import java.util.Observable;

public class GameOver extends Observable implements IGameOver {
	@Override
	public void gameOver() {
		setChanged();
		notifyObservers();
	}
}
