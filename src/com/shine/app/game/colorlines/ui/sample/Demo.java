package com.shine.app.game.colorlines.ui.sample;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class Demo extends JFrame {

	public Demo(String title) throws HeadlessException {
		super(title);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		// 绘制渐变
		g2d.setPaint(new GradientPaint(0, 0, Color.RED, getWidth(),
				getHeight(), Color.YELLOW));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.fillOval(250, 250, 200, 200);
		g2d.setPaint(new GradientPaint(0, 0, Color.BLUE, 500, 0, Color.WHITE));

		g2d.fillOval(50, 50, 200, 200);
		// 平移原点到图形环境的中心
		g2d.translate(250, 250);
		g2d.setPaint(Color.RED);
		// g2d.rotate();
		g2d.setFont(new Font("宋体", Font.BOLD, 28));
		// g2d.rotate(-20 * Math.PI / 180);
		g2d.drawString("Java Graphics2D 实现文字渐变效果", -250, -200);
		// g2d.fillRect(0, 0, getWidth(), getHeight());

		// g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
		// // 旋转文本

		GradientPaint g1 = new GradientPaint(0, 0, Color.RED, 500, 0,
				Color.YELLOW);
		GradientPaint g2 = new GradientPaint(0, 0, Color.BLUE, 500, 0,
				Color.GREEN);

		for (int i = 0; i < 12; i++) {
			g2d.rotate(30 * Math.PI / 180);
			// g2d.setPaint(colors[i % 2]);
			if (i % 2 == 0) {
				g2d.setPaint(g1);
			} else {
				g2d.setPaint(g2);
			}
			g2d.drawString("Java 2D 旋转效果 ", 0, 0);
		}
	}

	public static void main(String[] args) {
		new Demo("Graphics");
	}
}
