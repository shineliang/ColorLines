package com.shine.app.game.colorlines.ui;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.shine.app.game.colorlines.ui.exception.InvalidIconPathException;

public class CellType {

	public static final CellType T0 = new CellType(null);
	private static CellType[] typeList = null;
	private static int MAX_COLOR = 6;
	private static final String pathPrefix = "skin/ball/";
	private static double MIN_SIZE = 0.4;
	private static int ICONS_SIZE = 3;
	protected ImageIcon icon = null;
	protected String filePath = null;
	private Icon[] icons = null;
	private static CellType[] totalType = null;
	private static String[] fileList = { "ball_001.png", "ball_002.png",
			"ball_003.png", "ball_004.png", "ball_005.png", "ball_006.png",
			"ball_007.png", "ball_008.png", "ball_009.png", "ball_010.png",
			"ball_011.png", "ball_012.png", "ball_013.png", "ball_014.png" };

	private CellType(String filePath) {
		this.filePath = filePath;
	}

	public static CellType[] getTypeList() {
		if (totalType == null) {
			totalType = new CellType[fileList.length];
			for (int i = 0; i < totalType.length; i++) {
				totalType[i] = new CellType(fileList[i]);
			}
		}
		if (typeList == null) {
			initTypeList();
		}
		return typeList;
	}

	private static void initTypeList() {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for (int index = 0; index < fileList.length; index++) {
			ll.add(index);
		}
		typeList = new CellType[getMaxColor()];
		Random random = new Random();
		for (int i = 0; i < typeList.length; i++) {
			typeList[i] = generateType(random, ll);
		}
	}

	public static int getMaxColor() {
		return MAX_COLOR;
	}

	public static void setMaxColor(int maxColor) {
		MAX_COLOR = maxColor;
		initTypeList();
	}

	private static CellType generateType(Random random, LinkedList<Integer> ll) {
		int index = random.nextInt(ll.size());
		int val = ll.remove(index);
		return totalType[val];
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
				icon.setImage(getIconImage().getImage().getScaledInstance(
						UiConstants.CELL_IMAGE_WIDTH, height,
						Image.SCALE_SMOOTH));
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
