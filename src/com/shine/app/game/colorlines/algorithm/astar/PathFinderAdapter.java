package com.shine.app.game.colorlines.algorithm.astar;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import com.shine.app.game.colorlines.ui.Cell;
import com.shine.app.game.colorlines.ui.ChessBoard;
import com.shine.app.game.colorlines.ui.GridBase;
import com.shine.app.game.colorlines.ui.UiConstants;

public class PathFinderAdapter implements ICellPathFinder {

	private final static int[] HIT = { 1 };

	@Override
	public List<Cell> searchPath(Cell start, Cell end) {
		GridBase gridBase = ChessBoard.getInstance().getGridBase();
		PathFinder finder = new PathFinder(gridBase.buildPathMap(), HIT);
		Point startPos = new Point(start.getX() / UiConstants.CELL_WIDTH,
				start.getY() / UiConstants.CELL_HEIGHT);
		Point endPos = new Point(end.getX() / UiConstants.CELL_WIDTH,
				end.getY() / UiConstants.CELL_HEIGHT);
		List<Node> nodeList = finder.searchPath(startPos, endPos);
		List<Cell> cellList = new LinkedList<Cell>();
		for (Node node : nodeList) {
			cellList.add(gridBase.getCell(node.position.x, node.position.y));
		}
		return cellList;
	}

}
