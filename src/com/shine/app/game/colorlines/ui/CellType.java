package com.shine.app.game.colorlines.ui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.shine.app.game.colorlines.ui.exception.InvalidIconPathException;

public enum CellType {

	/**
	 * Support 7 types
	 */
	T0 {
		@Override
		public Icon getImage() {
			return getImage(null);
		}
	},
	T1 {
		@Override
		public Icon getImage() {
			return getImage(getFullPath("1.gif"));
		}
	},
	T2 {
		@Override
		public Icon getImage() {
			return getImage(getFullPath("2.gif"));
		}
	},
	T3 {
		@Override
		public Icon getImage() {
			return getImage(getFullPath("3.gif"));
		}
	},
	T4 {
		@Override
		public Icon getImage() {
			return getImage(getFullPath("4.gif"));
		}
	},
	T5 {
		@Override
		public Icon getImage() {
			return getImage(getFullPath("5.gif"));
		}
	},
	T6 {
		@Override
		public Icon getImage() {
			return getImage(getFullPath("6.gif"));
		}
	},
	T7 {
		@Override
		public Icon getImage() {
			return getImage(getFullPath("7.gif"));
		}
	};

	private static final String pathPrefix = "skin/default/";
	private Icon image = null;

	public abstract Icon getImage();

	protected String getFullPath(String fileName) {
		if (fileName == null || pathPrefix == null) {
			throw new InvalidIconPathException();
		}
		return pathPrefix + fileName;
	}

	protected Icon getImage(String path) {
		if (image == null)
			if (path == null)
				image = new ImageIcon();
			else
				image = new ImageIcon(path);

		return image;
	}
}
