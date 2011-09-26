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
	Thread newThread; // ���߳�
	Image OffScreen; // �λ���
	Graphics drawOffScreen; // ���ƴλ����Graphicsʵ��

	public void init() {
		X = 0; // X����
		Y = 0; // Y����
		moveX = 2; // X���ƶ�����
		moveY = 3; // Y���ƶ�����
		width = getSize().width; // Applet�Ŀ��
		height = getSize().height; // Applet�ĸ߶�
		OffScreen = createImage(width, height); // �����λ���
		drawOffScreen = OffScreen.getGraphics(); // ȡ�ôλ���Ļ�����
	}

	public void start() {
		newThread = new Thread(this); // �������������߳�
		newThread.start();
	}

	public void stop() {
		newThread = null; // ���߳���Ϊnull
	}

	public void paint(Graphics g) {
		// ���������е�����Ϊ����λ���
		drawOffScreen.setColor(Color.black);
		drawOffScreen.fillRect(0, 0, width, height);
		// ���������е�����Ϊ�ڴλ����ϻ���ʵ����Բ��
		drawOffScreen.setColor(Color.white);
		drawOffScreen.fillOval(X, Y, 30, 30);
		// ���λ���������������
		g.drawImage(OffScreen, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g); // ֻ��������paint()����
	}

	public void run() {
		while (newThread != null) {
			repaint(); // ���»���ͼ��
			try {
				Thread.sleep(1); // ��ͣ����ִ��50����
			} catch (InterruptedException E) {
			}
			X = X + moveX; // �����µ�X����
			Y = Y + moveY; // �����µ�Y����
			// ��ײ���߽�ʱ�ͻᷴ��
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
