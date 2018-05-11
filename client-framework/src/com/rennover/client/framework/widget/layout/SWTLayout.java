package com.rennover.client.framework.widget.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SWTLayout
{
	// Méthodes supplémentaire
	protected static void setBounds(Component control, int widgetX, int widgetY, int widgetW, int widgetH)
	{
		control.setBounds(widgetX, widgetY, widgetW, widgetH);
	}

	protected static Rectangle getClientArea(Container composite)
	{
		Rectangle rect = new Rectangle(composite.getBounds());
		Insets insets = composite.getInsets();
		rect.x = insets.left;
		rect.y = insets.top;
		rect.width -= insets.left + insets.right;
		rect.height -= insets.top + insets.bottom;
		return rect;
	}

	protected static Dimension computeSize(Component control, int wHint, int hHint, boolean flushCache)
	{
		Dimension prefSize = control.getPreferredSize();
		if (wHint == -1)
		{
			wHint = prefSize.width;
		}
		if (hHint == -1)
		{
			hHint = prefSize.height;
		}

		return new Dimension(wHint, hHint);
	}
}