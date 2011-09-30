package com.shine.app.game.colorlines.obj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import com.shine.app.game.colorlines.ui.UiConstants;

public class RankList implements Iterable<RankEntry>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7988303575455991461L;
	private static final String fileName = "record.data";
	private static final int MAX_SIZE = 10;
	private LinkedList<RankEntry> list = new LinkedList<RankEntry>();

	/**
	 * Add the entry into the list
	 * 
	 * @param entry
	 * @return the index of the inserted entry, or -1 if add failed.
	 */
	public int add(RankEntry entry) {
		if (entry == null) {
			return -1;
		}

		int result = -1;
		int size = Math.min(list.size(), MAX_SIZE);
		for (int i = 0; i < size; i++) {
			if (entry.compareTo(list.get(i)) > 0) {
				list.add(i, entry);
				result = i;
				break;
			}
		}

		if (result == -1 && list.size() < MAX_SIZE) {
			list.add(entry);
			result = list.size() - 1;
		}

		while (list.size() > MAX_SIZE) {
			list.removeLast();
		}

		return result;
	}

	@Override
	public Iterator<RankEntry> iterator() {
		return list.iterator();
	}

	public RankEntry get(int index) {
		return list.get(index);
	}

	public int size() {
		return list.size();
	}

	public static RankList load() {
		RankList rankList = null;
		try {
			FileInputStream fi = new FileInputStream(fileName);
			ObjectInputStream os = new ObjectInputStream(fi);
			rankList = (RankList) os.readObject();
		} catch (Exception e) {
			// ignore
		}
		if (rankList == null) {
			rankList = new RankList();
		}
		NameRegister.getInstance().addObj(UiConstants.RANK_LIST, rankList);

		return rankList;
	}

	public static void save() {
		try {
			FileOutputStream fs = new FileOutputStream(fileName);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			NameRegister.getInstance().getObj(UiConstants.RANK_LIST);
			os.writeObject(NameRegister.getInstance().getObj(
					UiConstants.RANK_LIST));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
