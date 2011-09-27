package com.shine.app.game.colorlines.algorithm.astar;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class PathFinder {
	// ·�����ȵȼ�list(��ʾ����Ϊ�ڲ�����)
	private LevelList levelList;
	// �����·����list
	private LinkedList<Node> closedList;
	// ��ͼ����
	private int[][] map;
	// ������������
	private int[] limit;

	/**
	 * ��ע���ͼ��2ά���鼰���Ƶ�������ʼ������
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
	 * A*��ʽѰ��,ע�뿪ʼ���꼰Ŀ�����������,���ؿ���·����List
	 * 
	 * @param startPos
	 * @param objectPos
	 * @return
	 */
	public List<Node> searchPath(Point startPos, Point objectPos) {
		// ��ʼ����ʼ�ڵ���Ŀ��ڵ�
		Node startNode = new Node(startPos);
		Node objectNode = new Node(objectPos);
		// �趨��ʼ�ڵ����
		startNode.costFromStart = 0;
		startNode.costToObject = startNode.getCost(objectNode);
		startNode.parentNode = null;
		// ��������ȼ�����
		levelList.add(startNode);
		// ������ȼ������д�������ʱ��ѭ������Ѱ����ֱ��levelListΪ��
		while (!levelList.isEmpty()) {
			// ȡ����ɾ�������Ԫ��
			Node firstNode = (Node) levelList.removeFirst();
			// �ж��Ƿ��Ŀ��node�������
			if (firstNode.equals(objectNode)) {
				// �ǵĻ����ɹ�������������·��ͼ���������
				return makePath(firstNode);
			} else {
				// ����
				// ��������֤List
				closedList.add(firstNode);
				// ���firstNode���ƶ�����
				List<Node> _limit = firstNode.getLimit();
				// ����
				for (Node neighborNode : _limit) {
					// ������ڽڵ�
					// ����Ƿ�����ȼ�����
					boolean isOpen = levelList.contains(neighborNode);
					// ����Ƿ�������
					boolean isClosed = closedList.contains(neighborNode);
					// �ж��Ƿ��޷�ͨ��
					boolean isHit = isHit(neighborNode.position.x,
							neighborNode.position.y);
					// �������ж��Է�ʱ
					if (!isOpen && !isClosed && !isHit) {
						// �趨costFromStart
						neighborNode.costFromStart = firstNode.costFromStart + 1;
						// �趨costToObject
						neighborNode.costToObject = neighborNode
								.getCost(objectNode);
						// �ı�neighborNode���ڵ�
						neighborNode.parentNode = firstNode;
						// ����level
						levelList.add(neighborNode);
					}
				}
			}
		}
		// �������
		levelList.clear();
		closedList.clear();
		// ��while�޷�����ʱ��������null
		return null;
	}

	/** */
	/**
	 * �ж��Ƿ�Ϊ��ͨ������
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isHit(int x, int y) {
		for (int i : limit) {
			if (map[x][y] == i) {
				return true;
			}
		}
		return false;
	}

	/** */
	/**
	 * ͨ��Node��������·��
	 * 
	 * @param node
	 * @return
	 */
	private LinkedList<Node> makePath(Node node) {
		LinkedList<Node> path = new LinkedList<Node>();
		// ���ϼ��ڵ����ʱ
		while (node.parentNode != null) {
			// �ڵ�һ��Ԫ�ش����
			path.addFirst(node);
			// ��node��ֵΪparent node
			node = node.parentNode;
		}
		// �ڵ�һ��Ԫ�ش����
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
		 * ����һ��node
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
