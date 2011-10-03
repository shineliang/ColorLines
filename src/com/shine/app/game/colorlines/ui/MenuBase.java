package com.shine.app.game.colorlines.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.shine.app.game.colorlines.obj.SystemStartup;

public class MenuBase extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3619260260311508244L;

	public MenuBase() {
		init();
	}

	private void init() {

		// Files
		JMenu menu = new JMenu("Files");
		JMenuItem item = new JMenuItem("New");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doNew();
			}
		});
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("Exit");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doExit();
			}
		});
		menu.add(item);
		add(menu);

		// Options
		menu = new JMenu("Options");
		ButtonGroup group = new ButtonGroup();
		item = new JRadioButtonMenuItem("Standard 9x9 (6 balls)");
		item.setSelected(true);
		group.add(item);
		menu.add(item);

		item = new JRadioButtonMenuItem("Customize");
		group.add(item);
		menu.add(item);
		add(menu);

		// Help
		menu = new JMenu("Help");
		item = new JMenuItem("About");
		menu.add(item);
		add(menu);
	}

	private void doNew() {
		SystemStartup.startup();
		ChessBoard.getInstance().init();
		SystemStartup.afterStartup();
	}

	private void doExit() {
		// TODO Auto-generated method stub

	}
}
