package com.shine.app.game.colorlines.action;

import java.util.Random;

import com.shine.app.game.colorlines.ui.CellType;

public class NextTypes {

	private int count = 0;
	private CellType[] totalValues = CellType.values();

	public NextTypes(int count) {
		this.count = count;
	}

	public CellType[] generateTypes() {
		Random random = new Random();
		CellType[] types = new CellType[count];
		for (int i = 0; i < count; i++) {
			types[i] = genetateType(random);
		}
		return types;
	}

	/**
	 * Will not include the CellType.T0
	 * 
	 * @param random
	 * @return
	 */
	private CellType genetateType(Random random) {
		int value = random.nextInt(totalValues.length - 1);
		return totalValues[value + 1];
	}

}
