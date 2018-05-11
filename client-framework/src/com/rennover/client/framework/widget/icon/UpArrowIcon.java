/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

package com.rennover.client.framework.widget.icon;

import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * 
 * @author Piyush
 * 
 */
class UpArrowIcon implements Icon, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8453004747342961456L;

	/**
	 * Paints the horizontal bars for the
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		JComponent component = (JComponent) c;
		int iconWidth = getIconWidth();

		g.translate(x, y);

		g.setColor(component.isEnabled() ? MetalLookAndFeel.getControlInfo()
				: MetalLookAndFeel.getControlShadow());
		g.drawLine(4, 0, 4 + (iconWidth - 9), 0);
		g.drawLine(3, 1, 3 + (iconWidth - 7), 1);
		g.drawLine(2, 2, 2 + (iconWidth - 5), 2);
		g.drawLine(1, 3, 1 + (iconWidth - 3), 3);
		g.drawLine(0, 4, iconWidth - 1, 4);

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