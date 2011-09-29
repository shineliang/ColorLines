package com.shine.app.game.colorlines.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.util.Observable;
import java.util.Observer;

import com.shine.app.game.colorlines.obj.IScore;

public class BillBoard extends TextField implements Observer {

	private static final long serialVersionUID = 6894494103974177373L;
	private IScore score = null;

	public BillBoard(IScore score) {
		this.score = score;
		init();
	}

	private void init() {
		setPreferredSize(new Dimension(UiConstants.LABEL_WIDTH,
				UiConstants.LABLE_HEIGHT));
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setEditable(false);
		setEnabled(false);
		setFont(new Font("Tahoma", Font.BOLD, 20));
		setText("123");
		if (score != null && score instanceof Observable) {
			((Observable) score).addObserver(this);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		String val = String.format("%5d", ((IScore) o).getScore());
		setText(val);
	}
}
