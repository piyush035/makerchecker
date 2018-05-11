package com.rennover.client.framework.widget.base;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.rennover.client.framework.window.WindowBlocker;
import com.rennover.client.framework.window.WindowManager;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ZDialog extends JDialog
{
	/**
	 * @param frame
	 * @param title
	 * @param b
	 */
	public ZDialog(Dialog parent, String title, boolean modal)
	{
		super(parent, title, modal);
	}

	/**
	 * @param frame
	 * @param title
	 * @param b
	 */
	public ZDialog(Frame parent, String title, boolean modal)
	{
		super(parent, title, modal);
	}

	private ZDialog m_lockedDialog = null;

	/**
	 * Constructor for ZDialog.
	 * 
	 * @param owner
	 * @throws HeadlessException
	 */
	public ZDialog(Frame owner) throws HeadlessException
	{
		super(owner);
		init();
	}

	/**
	 * Constructor for ZDialog.
	 * 
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public ZDialog(Frame owner, String title) throws HeadlessException
	{
		super(owner, title);
		init();
	}

	/**
	 * Constructor for ZDialog.
	 * 
	 * @param owner
	 * @throws HeadlessException
	 */
	public ZDialog(Dialog owner) throws HeadlessException
	{
		super(owner);
		init();
	}

	/**
	 * Constructor for ZDialog.
	 * 
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public ZDialog(Dialog owner, String title) throws HeadlessException
	{
		super(owner, title);
		init();
	}

	private void init()
	{
		// removed the commented code for window listner
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

	public void setVisible(boolean b)
	{
		super.setVisible(b);

		if (isSpecialModality())
		{
			if (!b)
			{
				lockParent(false);
			}
		}
	}

	public void setModal(boolean modal)
	{
		if (isSpecialModality())
		{
			lockParent(modal);
			super.setModal(false);
		} else
		{
			super.setModal(modal);
		}
	}

	private void lockParent(boolean lock)
	{
		Window parent = getOwner();

		if (parent instanceof ZFrame)
		{
			((ZFrame) parent).setLockedBy(lock ? this : null);
		}

		if (parent instanceof ZDialog)
		{
			((ZDialog) parent).setLockedBy(lock ? this : null);
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

	public void setLocation(int x, int y)
	{
		Dimension screenSize = WindowManager.getClientScreenSize();

		int h = getHeight();
		int w = getWidth();

		if (x + w > screenSize.width)
		{
			x = screenSize.width - w;
		}

		if (y + h > screenSize.height)
		{
			y = screenSize.height - h;
		}

		if (x < 0)
		{
			x = 0;
		}
		if (y < 0)
		{
			y = 0;
		}
		super.setLocation(x, y);
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

	/**
	 * @return
	 */
	private boolean isSpecialModality()
	{
		return false;
	}

	static final class WakingRunnable implements Runnable
	{
		public void run()
		{
		}
	}
}
