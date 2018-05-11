package com.rennover.client.framework.widget.icon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.plaf.metal.MetalLookAndFeel;

class UnknownIcon implements Icon, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 535706468593798159L;
	int m_iconSize = 7;

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Color back;
		Color highlight = MetalLookAndFeel.getPrimaryControlHighlight();

		back = MetalLookAndFeel.getPrimaryControlDarkShadow();

		g.translate(x, y);
		g.setColor(back);
		g.drawLine(0, 1, 5, 6);
		g.drawLine(1, 0, 6, 5);
		g.drawLine(1, 1, 6, 6);
		g.drawLine(6, 1, 1, 6);
		g.drawLine(5, 0, 0, 5);
		g.drawLine(5, 1, 1, 5);

		g.setColor(highlight);
		g.drawLine(6, 2, 5, 3);
		g.drawLine(2, 6, 3, 5);
		g.drawLine(6, 6, 6, 6);

		g.translate(-x, -y);
	}

	public int getIconWidth() {
		return m_iconSize;
	}

	public int getIconHeight() {
		return m_iconSize;
	}
}