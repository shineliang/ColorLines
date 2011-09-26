package com.shine.app.game.colorlines.algorithm.run;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class TestMap {
	final static private int CS = 32;
	// 地图描述
	final static public int[][] MAP = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
	// 无法移动区域
	final static public int[] HIT = { 1 };
	// 设定背景方格默认行数
	final static private int ROW = 15;
	// 设定背景方格默认列数
	final static private int COL = 15;
	private Image floorImage;
	private Image wallImage;

	public TestMap() {
		try {
			floorImage = javax.imageio.ImageIO.read(new File("floor.gif"));
			wallImage = javax.imageio.ImageIO.read(new File("wall.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				switch (MAP[i][j]) {
				case 0:
					g.drawImage(floorImage, j * CS, i * CS, null);
					break;
				case 1:
					g.drawImage(wallImage, j * CS, i * CS, null);
					break;
				}
			}
		}
	}

}
