package com.shine.app.game.colorlines.ui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.shine.app.game.colorlines.ui.exception.InvalidIconPathException;

public enum CellType {

	/**
	 * Support 6 types + 1 empty
	 */
	T0 {
		@Override
		public Icon getImage() {
			filePath = null;
			return getIconImage();
		}
	},
	T1 {
		@Override
		public Icon getImage() {
			filePath = "1.gif";
			return getIconImage();
		}
	},
	T2 {
		@Override
		public Icon getImage() {
			filePath = "2.gif";
			return getIconImage();
		}
	},
	T3 {
		@Override
		public Icon getImage() {
			filePath = "3.gif";
			return getIconImage();
		}
	},
	T4 {
		@Override
		public Icon getImage() {
			filePath = "4.gif";
			return getIconImage();
		}
	},
	T5 {
		@Override
		public Icon getImage() {
			filePath = "5.gif";
			return getIconImage();
		}
	},
	T6 {
		@Override
		public Icon getImage() {
			filePath = "6.gif";
			return getIconImage();
		}
	};

	private static final String pathPrefix = "skin/default/";
	protected Icon image = null;
	protected String filePath = null;

	public abstract Icon getImage();

	private String getFullPath(String fileName) {
		if (pathPrefix == null) {
			throw new InvalidIconPathException();
		}
		return pathPrefix + fileName;
	}

	private Icon getIconImage(String path) {
		if (path == null)
			image = new ImageIcon();
		else
			image = new ImageIcon(path);

		return image;
	}

	protected Icon getIconImage() {
		if (image == null)
			image = getIconImage(getFullPath(filePath));

		return image;
	}
}
