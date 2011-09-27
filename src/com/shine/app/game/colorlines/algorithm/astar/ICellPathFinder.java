package com.shine.app.game.colorlines.algorithm.astar;

import java.util.List;

import com.shine.app.game.colorlines.ui.Cell;

public interface ICellPathFinder {

	public List<Cell> searchPath(Cell start, Cell end);

}