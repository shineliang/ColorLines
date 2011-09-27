package com.shine.app.game.colorlines.algorithm.astar;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import com.shine.app.game.colorlines.ui.UiConstants;

public class Node implements Comparable<Node> {

	// ����
	public Point position;
	// ��ʼ�ص���ֵ
	public int costFromStart;
	// Ŀ��ص���ֵ
	public int costToObject;
	// ���ڵ�
	public Node parentNode;

	/** */
	/**
	 * ��ע������㷽ʽ��ʼ��Node
	 * 
	 * @param position
	 */
	public Node(Point position) {
		this.position = position;
	}

	/** */
	/**
	 * ����·���ɱ�
	 * 
	 * @param node
	 * @return
	 */
	public int getCost(Node node) {
		// ����������ֵ ��ʽ��(x1, y1)-(x2, y2)
		int m = node.position.x - position.x;
		int n = node.position.y - position.y;
		// ȡ���ڵ��ŷ����¾��루ֱ�߾��룩��Ϊ����ֵ�����Ի�óɱ�
		return (int) Math.sqrt(m * m + n * n);
	}

	/** */
	/**
	 * ���node�����Ƿ����֤����һ��
	 */
	@Override
	public boolean equals(Object node) {
		if (this == node)
			return true;

		if (node instanceof Node) {
			// ��֤����Ϊ�ж�����
			if (position.x == ((Node) node).position.x
					&& position.y == ((Node) node).position.y) {
				return true;
			}
		}

		return false;
	}

	/** */
	/**
	 * �Ƚ������Ի����С�ɱ�����
	 */
	@Override
	public int compareTo(Node node) {
		int a1 = costFromStart + costToObject;
		int a2 = node.costFromStart + node.costToObject;
		return a1 - a2;
	}

	/** */
	/**
	 * ����������Ҹ�����ƶ���������
	 * 
	 * @return
	 */
	public List<Node> getLimit() {
		LinkedList<Node> limit = new LinkedList<Node>();
		int x = position.x;
		int y = position.y;
		// �������Ҹ�����ƶ�����(����б�ӵ�ͼ�����Կ���ע�͵�ƫ�Ʋ��֣���ʱ������8����λ)
		// ��
		if (y > 0)
			limit.add(new Node(new Point(x, y - 1)));
		// ����
		// limit.add(new Node(new Point(x+1, y-1)));
		// ��
		if (x < UiConstants.X_SIZE - 1)
			limit.add(new Node(new Point(x + 1, y)));
		// ����
		// limit.add(new Node(new Point(x+1, y+1)));
		// ��
		if (y < UiConstants.Y_SIZE - 1)
			limit.add(new Node(new Point(x, y + 1)));
		// ����
		// limit.add(new Node(new Point(x-1, y+1)));
		// ��
		if (x > 0)
			limit.add(new Node(new Point(x - 1, y)));
		// ����
		// limit.add(new Node(new Point(x-1, y-1)));
		return limit;
	}

}
