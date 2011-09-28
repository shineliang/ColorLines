package com.shine.app.game.colorlines.ui;

import javax.swing.Icon;

public interface ITyper {

	public abstract CellType getType();

	public abstract void setType(CellType type);

	public abstract boolean isEmpty();

	public void setIcon(Icon defaultIcon);

}