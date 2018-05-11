package com.rennover.client.framework.widget.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.rennover.client.framework.window.WindowBlocker;
import com.rennover.client.framework.window.WindowManager;

/**
 * @author tcaboste
 * 
 */
public class ZFrame extends JFrame
{
	private ZDialog m_lockedDialog = null;

	/**
	 * Constructor for ZFrame.
	 * 
	 * @throws HeadlessException
	 */
	public ZFrame() throws HeadlessException
	{
		super();
		init();
	}

	/**
	 * Constructor for ZFrame.
	 * 
	 * @param gc
	 */
	public ZFrame(GraphicsConfiguration gc)
	{
		super(gc);
		init();
	}

	/**
	 * Constructor for ZFrame.
	 * 
	 * @param title
	 * @throws HeadlessException
	 */
	public ZFrame(String title) throws HeadlessException
	{
		super(title);
		init();
	}

	/**
	 * Constructor for ZFrame.
	 * 
	 * @param title
	 * @param gc
	 */
	public ZFrame(String title, GraphicsConfiguration gc)
	{
		super(title, gc);
		init();
	}

	private void init()
	{
		addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				if (m_lockedDialog != null)
				{
					m_lockedDialog.requestFocus();
				}
			}
		});

	}

	/**
	 * @param parent
	 */
	public void setLockedBy(ZDialog childDialog)
	{
		m_lockedDialog = childDialog;

		boolean block = childDialog != null ? true : false;

		if (block)
		{
			WindowBlocker.getInstance().addRestrictedWindow(this);
			WindowBlocker.getInstance().setBlockingEnabled(true);
		} else
		{
			WindowBlocker.getInstance().removeRestrictedWindow(this);
		}

	}

	public void centerToScreen()
	{
		Dimension screenSize = WindowManager.getClientScreenSize();
		Dimension paneSize = getSize();
		setLocation((screenSize.width - paneSize.width) / 2, (screenSize.height - paneSize.height) / 2);
	}

	public void centerToParent()
	{
		Window parent = getOwner();
		if (parent != null && (parent instanceof JFrame || parent instanceof JDialog))
		{
			Point location = parent.getLocation();
			location.x += ((parent.getWidth() - getWidth()) / 2);
			location.y += ((parent.getHeight() - getHeight()) / 2);
			setLocation(location);
		} else
		{
			centerToScreen();
		}
	}

	public void setSize(int width, int height)
	{
		Dimension screenSize = WindowManager.getClientScreenSize();
		if (screenSize.width > 1024)
		{
			screenSize.width = 1024;
		}
		if (screenSize.height > 768)
		{
			screenSize.height = 768;
		}

		if (width > screenSize.width)
		{
			width = screenSize.width;
		}
		if (height > screenSize.height)
		{
			height = screenSize.height;
		}
		super.setSize(width, height);
	}

	public void changeContent(ZPanel panel)
	{
		getContentPane().removeAll();
		if (panel != null)
		{
			getContentPane().add(panel, BorderLayout.CENTER);
		}
		validate();
		repaint();
	}
	// removed the commented method processEvent(AWTEvent e)
}
