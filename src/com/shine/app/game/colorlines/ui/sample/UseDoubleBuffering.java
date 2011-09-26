package com.shine.app.game.colorlines.ui.sample;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UseDoubleBuffering extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -606156563665683781L;
	int X, Y, moveX, moveY, width, height;
	Thread newThread; // 新线程
	Image OffScreen; // 次画面
	Graphics drawOffScreen; // 绘制次画面的Graphics实体

	public void init() {
		X = 0; // X坐标
		Y = 0; // Y坐标
		moveX = 2; // X轴移动距离
		moveY = 3; // Y轴移动距离
		width = getSize().width; // Applet的宽度
		height = getSize().height; // Applet的高度
		OffScreen = createImage(width, height); // 建立次画面
		drawOffScreen = OffScreen.getGraphics(); // 取得次画面的绘制类
	}

	public void start() {
		newThread = new Thread(this); // 建立与启动新线程
		newThread.start();
	}

	public void stop() {
		newThread = null; // 将线程设为null
	}

	public void paint(Graphics g) {
		// 下面这两行的作用为清除次画面
		drawOffScreen.setColor(Color.black);
		drawOffScreen.fillRect(0, 0, width, height);
		// 下面这两行的作用为在次画面上绘制实心正圆形
		drawOffScreen.setColor(Color.white);
		drawOffScreen.fillOval(X, Y, 30, 30);
		// 将次画面贴到主画面上
		g.drawImage(OffScreen, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g); // 只单纯调用paint()函数
	}

	public void run() {
		while (newThread != null) {
			repaint(); // 重新绘制图像
			try {
				Thread.sleep(1); // 暂停程序执行50毫秒
			} catch (InterruptedException E) {
			}
			X = X + moveX; // 计算新的X坐标
			Y = Y + moveY; // 计算新的Y坐标
			// 碰撞到边界时就会反弹
			if (X >= (width - 30)) {
				X = width - 30;
				moveX = -moveX;
			}
			if (X <= 0) {
				X = 0;
				moveX = -moveX;
			}
			if (Y >= (height - 30)) {
				Y = height - 30;
				moveY = -moveY;
			}
			if (Y <= 0) {
				Y = 0;
				moveY = -moveY;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		UseDoubleBuffering frame = new UseDoubleBuffering();
		frame.setSize(480, 380);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(new JPanel());
		frame.setVisible(true);
		frame.init();
		frame.start();
		Thread.sleep(10 * 1000);
		frame.stop();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
