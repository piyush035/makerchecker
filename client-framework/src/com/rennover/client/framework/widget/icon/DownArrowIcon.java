package com.rennover.client.framework.widget.icon;

import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalLookAndFeel;

class DownArrowIcon implements Icon, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3575458143120600058L;

	/**
	 * Paints the horizontal bars for the
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		JComponent component = (JComponent) c;
		int iconWidth = getIconWidth();

		g.translate(x, y);

		g.setColor(component.isEnabled() ? MetalLookAndFeel.getControlInfo()
				: MetalLookAndFeel.getControlShadow());
		g.drawLine(0, 0, iconWidth - 1, 0);
		g.drawLine(1, 1, 1 + (iconWidth - 3), 1);
		g.drawLine(2, 2, 2 + (iconWidth - 5), 2);
		g.drawLine(3, 3, 3 + (iconWidth - 7), 3);
		g.drawLine(4, 4, 4 + (iconWidth - 9), 4);

		g.translate(-x, -y);
	}

	/**
	 * Created a stub to satisfy the interface.
	 */
	public int getIconWidth() {
		return 10;
	}

	/**
	 * Created a stub to satisfy the interface.
	 */
	public int getIconHeight() {
		return 5;
	}
}