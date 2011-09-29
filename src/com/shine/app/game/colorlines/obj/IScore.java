package com.shine.app.game.colorlines.obj;


public interface IScore {

	public int getScore();

	public void setScore(int score);

	public void passBalls(int number);

	public void passBallsInMultiLines(int number, int xLines);

}