package com.shine.app.game.colorlines.algorithm.astar;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import com.shine.app.game.colorlines.ui.UiConstants;

public class Node implements Comparable<Node> {

	// 坐标
	public Point position;
	// 开始地点数值
	public int costFromStart;
	// 目标地点数值
	public int costToObject;
	// 父节点
	public Node parentNode;

	/** */
	/**
	 * 以注入坐标点方式初始化Node
	 * 
	 * @param position
	 */
	public Node(Point position) {
		this.position = position;
	}

	/** */
	/**
	 * 返回路径成本
	 * 
	 * @param node
	 * @return
	 */
	public int getCost(Node node) {
		// 获得坐标点间差值 公式：(x1, y1)-(x2, y2)
		int m = node.position.x - position.x;
		int n = node.position.y - position.y;
		// 取两节点间欧几理德距离（直线距离）做为估价值，用以获得成本
		return (int) Math.sqrt(m * m + n * n);
	}

	/** */
	/**
	 * 检查node对象是否和验证对象一致
	 */
	@Override
	public boolean equals(Object node) {
		if (this == node)
			return true;

		if (node instanceof Node) {
			// 验证坐标为判断依据
			if (position.x == ((Node) node).position.x
					&& position.y == ((Node) node).position.y) {
				return true;
			}
		}

		return false;
	}

	/** */
	/**
	 * 比较两点以获得最小成本对象
	 */
	@Override
	public int compareTo(Node node) {
		int a1 = costFromStart + costToObject;
		int a2 = node.costFromStart + node.costToObject;
		return a1 - a2;
	}

	/** */
	/**
	 * 获得上下左右各点间移动限制区域
	 * 
	 * @return
	 */
	public List<Node> getLimit() {
		LinkedList<Node> limit = new LinkedList<Node>();
		int x = position.x;
		int y = position.y;
		// 上下左右各点间移动区域(对于斜视地图，可以开启注释的偏移部分，此时将评估8个方位)
		// 上
		if (y > 0)
			limit.add(new Node(new Point(x, y - 1)));
		// 右上
		// limit.add(new Node(new Point(x+1, y-1)));
		// 右
		if (x < UiConstants.X_SIZE - 1)
			limit.add(new Node(new Point(x + 1, y)));
		// 右下
		// limit.add(new Node(new Point(x+1, y+1)));
		// 下
		if (y < UiConstants.Y_SIZE - 1)
			limit.add(new Node(new Point(x, y + 1)));
		// 左下
		// limit.add(new Node(new Point(x-1, y+1)));
		// 左
		if (x > 0)
			limit.add(new Node(new Point(x - 1, y)));
		// 左上
		// limit.add(new Node(new Point(x-1, y-1)));
		return limit;
	}

}
