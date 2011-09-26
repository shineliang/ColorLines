package com.shine.app.game.colorlines.algorithm.run;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.shine.app.game.colorlines.algorithm.astar.Node;
import com.shine.app.game.colorlines.algorithm.astar.PathFinder;

public class Main extends JPanel {
	final static private long serialVersionUID = 1L;
	final static private int WIDTH = 480;
	final static private int HEIGHT = 480;
	final static private int CS = 32;
	private TestMap map;
	private PathFinder astar;
	// 起始坐标1,1
	private static Point START_POS = new Point(1, 1);
	// 目的坐标10,13
	private static Point OBJECT_POS = new Point(8, 6);
	private Image screen;
	private Graphics graphics;
	private List<Node> path;

	public Main() {
		setSize(WIDTH, HEIGHT);
		setFocusable(true);
		screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		graphics = screen.getGraphics();
		map = new TestMap();
		// 注入地图描述及障碍物描述
		astar = new PathFinder(TestMap.MAP, TestMap.HIT);
		// searchPath将获得两点间移动路径坐标的List集合
		// 在实际应用中，利用Thread分步处理List中坐标即可实现自动行走
		path = astar.searchPath(START_POS, OBJECT_POS);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		// 绘制地图
		map.draw(graphics);
		graphics.setColor(Color.RED);
		graphics.fillRect(START_POS.x * CS, START_POS.y * CS, CS, CS);
		graphics.setColor(Color.BLUE);
		graphics.fillRect(OBJECT_POS.x * CS, OBJECT_POS.y * CS, CS, CS);
		// 绘制路径
		if (path != null) {
			graphics.setColor(Color.YELLOW);
			// 遍历坐标，并一一描绘
			for (int i = 0; i < path.size(); i++) {
				Node node = (Node) path.get(i);
				Point pos = node.position;
				// 描绘边框
				graphics.fillRect(pos.x * CS + 7, pos.y * CS + 7, CS - 14,
						CS - 14);
			}
		}
		g.drawImage(screen, 0, 0, this);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Java的A*寻径实现");
		frame.setSize(WIDTH, HEIGHT + 20);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(new Main());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
