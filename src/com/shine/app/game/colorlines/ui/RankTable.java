package com.shine.app.game.colorlines.ui;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.shine.app.game.colorlines.obj.NameRegister;
import com.shine.app.game.colorlines.obj.RankList;

public class RankTable extends JTable {

	private static final String DEFAULT_USER_NAME = "UNKNOWN";
	/**
	 * 
	 */
	private static final long serialVersionUID = -7303124238067320602L;
	private static final int ROW = 11;
	private static final int COL = 3;

	private TableModel model = null;
	private RankList rankList = null;
	private int index = 0;

	public RankTable(int index) {
		this.index = index;
		init();
	}

	private void init() {
		setPreferredSize(new Dimension(200, 500));

		rankList = (RankList) NameRegister.getInstance().getObj(
				UiConstants.RANK_LIST);

		inputUser();

		initModel();
	}

	private void inputUser() {
		String result = JOptionPane.showInputDialog("Your Name:",
				DEFAULT_USER_NAME);
		if (result == null) {
			result = DEFAULT_USER_NAME;
		}
		rankList.get(index).setName(result);
	}

	private void initModel() {
		model = new AbstractTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2139447816263235542L;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				if (rowIndex == 0) {
					switch (columnIndex) {
					case 0:
						return "No.";
					case 1:
						return "Name";
					case 2:
						return "Score";
					default:
						return null;
					}
				} else {
					int rankIndex = rowIndex - 1;
					switch (columnIndex) {
					case 0:
						return rowIndex;
					case 1:
						try {
							return rankList.get(rankIndex).getName();
						} catch (Exception e) {
						}
						return null;
					case 2:
						try {
							return rankList.get(rankIndex).getScore();
						} catch (Exception e) {
						}
						return null;
					default:
						return null;
					}
				}
			}

			@Override
			public int getRowCount() {
				return ROW;
			}

			@Override
			public int getColumnCount() {
				return COL;
			}
		};
		this.setModel(model);
	}
}
