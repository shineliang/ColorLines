package com.shine.app.game.colorlines.ui;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.shine.app.game.colorlines.ui.exception.InvalidIconPathException;

public enum CellType {

	T0(null), T1("ball_001.png"), T2("ball_002.png"), T3("ball_003.png"), T4(
			"ball_004.png"), T5("ball_005.png"), T6("ball_006.png"), T7(
			"ball_007.png"), T8("ball_008.png"), T9("ball_009.png"), T10(
			"ball_010.png"), T11("ball_011.png"), T12("ball_012.png"), T13(
			"ball_013.png"), T14("ball_014.png");

	private static final String pathPrefix = "skin/ball/";
	private static double MIN_SIZE = 0.4;
	private static int ICONS_SIZE = 3;
	protected ImageIcon icon = null;
	protected String filePath = null;
	private Icon[] icons = null;

	private CellType(String filePath) {
		this.filePath = filePath;
	}

	public Icon getImage() {
		return getIconImage();
	}

	public Icon[] getGradientImages() {
		if (icons == null) {
			icons = new Icon[ICONS_SIZE];
			double precent = (1 - MIN_SIZE) / ICONS_SIZE;
			for (int i = 0; i < ICONS_SIZE; i++) {
				ImageIcon icon = new ImageIcon();
				int height = (int) (UiConstants.CELL_IMAGE_HEIGHT * (MIN_SIZE + precent
						* i));
				icon.setImage(getIconImage()
						.getImage()
						.getScaledInstance(
								UiConstants.CELL_IMAGE_WIDTH,
								height, Image.SCALE_SMOOTH));
				icons[i] = icon;
			}
		}

		return icons;
	}

	private String getFullPath(String fileName) {
		if (pathPrefix == null) {
			throw new InvalidIconPathException();
		}
		return pathPrefix + fileName;
	}

	private ImageIcon getIconImage(String path) {
		ImageIcon image = null;

		if (path == null)
			image = new ImageIcon();
		else
			image = new ImageIcon(path);

		return image;
	}

	protected ImageIcon getIconImage() {
		if (icon == null) {
			ImageIcon iconImage = getIconImage(getFullPath(filePath));
			iconImage.setImage(iconImage.getImage().getScaledInstance(
					UiConstants.CELL_IMAGE_WIDTH,
					UiConstants.CELL_IMAGE_HEIGHT, Image.SCALE_SMOOTH));
			icon = iconImage;

		}

		return icon;
	}
}
