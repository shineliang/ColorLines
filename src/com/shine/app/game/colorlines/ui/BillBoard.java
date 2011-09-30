package com.shine.app.game.colorlines.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.shine.app.game.colorlines.obj.IScore;

public class BillBoard extends JLabel implements Observer {

	private static final long serialVersionUID = 6894494103974177373L;
	private IScore score = null;

	public BillBoard(IScore score) {
		this.score = score;
		init();
	}

	private void init() {
		setPreferredSize(new Dimension(UiConstants.LABEL_WIDTH,
				UiConstants.LABLE_HEIGHT));
		setOpaque(true);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.RIGHT);
		setFont(new Font("Tahoma", Font.PLAIN, 16));
		if (score != null && score instanceof Observable) {
			((Observable) score).addObserver(this);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		String val = String.format("%d", ((IScore) o).getScore());
		setText(val);
	}
}
