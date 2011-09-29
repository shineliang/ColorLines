package com.shine.app.game.colorlines.action;

import javax.swing.Icon;

import com.shine.app.game.colorlines.ui.ITyper;

public class FlashCell implements Runnable {

	private static final int RELAX_INTERVAL = 50;
	private ITyper cell = null;

	private volatile Thread blinker;

	public FlashCell(ITyper cell) {
		this.cell = cell;
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (currentThread == blinker) {
			try {
				Icon[] icons = cell.getType().getGradientImages();
				for (int i = icons.length - 1; i >= 0; i--) {
					cell.setIcon(icons[i]);
					Thread.sleep(RELAX_INTERVAL);
				}
				for (int i = 1; i < icons.length; i++) {
					cell.setIcon(icons[i]);
					Thread.sleep(RELAX_INTERVAL);
				}
				cell.setIcon(cell.getType().getImage());

			} catch (Exception e) {

			}

			// cell.setIcon(CellType.T0.getImage());
			// if (cell.getType() == CellType.T0) {
			// break;
			// }
			// try {
			// Thread.sleep(RELAX_INTERVAL / 2);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			// // change the icon back
			// cell.setIcon(cell.getType().getImage());
			// try {
			// Thread.sleep(RELAX_INTERVAL);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
	}

	public void start() {
		blinker = new Thread(this);
		blinker.start();
	}

	public void stop() {
		blinker = null;
	}

}
