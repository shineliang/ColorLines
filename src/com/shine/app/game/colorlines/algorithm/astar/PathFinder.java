package com.shine.app.game.colorlines.algorithm.astar;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class PathFinder {
	// 路径优先等级list(此示例中为内部方法)
	private LevelList levelList;
	// 已完成路径的list
	private LinkedList<Node> closedList;
	// 地图描述
	private int[][] map;
	// 行走区域限制
	private int[] limit;

	/**
	 * 以注入地图的2维数组及限制点描述初始化此类
	 * 
	 * @param map
	 */
	public PathFinder(int[][] map, int[] limit) {
		this.map = map;
		this.limit = limit;
		levelList = new LevelList();
		closedList = new LinkedList<Node>();
	}

	/** */
	/**
	 * A*方式寻径,注入开始坐标及目标坐标后运算,返回可行路径的List
	 * 
	 * @param startPos
	 * @param objectPos
	 * @return
	 */
	public List<Node> searchPath(Point startPos, Point objectPos) {
		// 初始化起始节点与目标节点
		Node startNode = new Node(startPos);
		Node objectNode = new Node(objectPos);
		// 设定起始节点参数
		startNode.costFromStart = 0;
		startNode.costToObject = startNode.getCost(objectNode);
		startNode.parentNode = null;
		// 加入运算等级序列
		levelList.add(startNode);
		// 当运算等级序列中存在数据时，循环处理寻径，直到levelList为空
		while (!levelList.isEmpty()) {
			// 取出并删除最初的元素
			Node firstNode = (Node) levelList.removeFirst();
			// 判定是否和目标node坐标相等
			if (firstNode.equals(objectNode)) {
				// 是的话即可构建出整个行走路线图，运算完毕
				return makePath(firstNode);
			} else {
				// 否则
				// 加入已验证List
				closedList.add(firstNode);
				// 获得firstNode的移动区域
				List<Node> _limit = firstNode.getLimit();
				// 遍历
				for (Node neighborNode : _limit) {
					// 获得相邻节点
					// 获得是否满足等级条件
					boolean isOpen = levelList.contains(neighborNode);
					// 获得是否已行走
					boolean isClosed = closedList.contains(neighborNode);
					// 判断是否无法通行
					boolean isHit = isHit(neighborNode.position.x,
							neighborNode.position.y);
					// 当三则判定皆非时
					if (!isOpen && !isClosed && !isHit) {
						// 设定costFromStart
						neighborNode.costFromStart = firstNode.costFromStart + 1;
						// 设定costToObject
						neighborNode.costToObject = neighborNode
								.getCost(objectNode);
						// 改变neighborNode父节点
						neighborNode.parentNode = firstNode;
						// 加入level
						levelList.add(neighborNode);
					}
				}
			}
		}
		// 清空数据
		levelList.clear();
		closedList.clear();
		// 当while无法运行时，将返回null
		return null;
	}

	/** */
	/**
	 * 判定是否为可通行区域
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isHit(int x, int y) {
		for (int i : limit) {
			if (map[y][x] == i) {
				return true;
			}
		}
		return false;
	}

	/** */
	/**
	 * 通过Node制造行走路径
	 * 
	 * @param node
	 * @return
	 */
	private LinkedList<Node> makePath(Node node) {
		LinkedList<Node> path = new LinkedList<Node>();
		// 当上级节点存在时
		while (node.parentNode != null) {
			// 在第一个元素处添加
			path.addFirst(node);
			// 将node赋值为parent node
			node = node.parentNode;
		}
		// 在第一个元素处添加
		path.addFirst(node);
		return path;
	}

	private class LevelList extends LinkedList<Node> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/** */
		/**
		 * 增加一个node
		 * 
		 * @param node
		 */
		public boolean add(Node node) {
			for (int i = 0; i < size(); i++) {
				if (node.compareTo(get(i)) <= 0) {
					add(i, node);
					return true;
				}
			}
			addLast(node);
			return true;
		}
	}
}
