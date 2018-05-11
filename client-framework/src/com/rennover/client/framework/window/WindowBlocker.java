
package com.rennover.client.framework.window;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WindowBlocker extends EventQueue
{
	private static WindowBlocker s_instance = null;

	private boolean m_inBlockedState = false;

	private EventQueue m_sysQ = Toolkit.getDefaultToolkit().getSystemEventQueue();

	private boolean m_alreadyBlockedOnce = false;

	// removed the commented code reset()

	private Set m_restrictedWindowSet = new HashSet();

	private WindowBlocker()
	{
	}

	public static synchronized WindowBlocker getInstance()
	{
		if (s_instance == null)
		{
			s_instance = new WindowBlocker();
		}
		return s_instance;
	}

	public void addRestrictedWindow(Window window)
	{
		m_restrictedWindowSet.add(window);
	}

	public void removeRestrictedWindow(Window window)
	{
		m_restrictedWindowSet.remove(window);
	}

	private boolean isParentComponentOf(Component parent, Component child)
	{
		return WindowManager.isParentComponentOf(parent, child);
	}

	private boolean isSourceBlocked(Component source)
	{
		boolean blocked = false;

		if ((!m_restrictedWindowSet.isEmpty()) && (source != null))
		{
			Iterator iter = m_restrictedWindowSet.iterator();
			while (iter.hasNext())
			{
				Window restrictedWindow = (Window) iter.next();
				if (isParentComponentOf(restrictedWindow, source))
				{
					blocked = true;
					break;
				}
			}
		}

		return blocked;
	}

	protected void dispatchEvent(AWTEvent event)
	{
		boolean blocked = false;

		if (m_inBlockedState && event.getSource() instanceof Component)
		{
			blocked = isSourceBlocked((Component) event.getSource());
		}

		if (blocked && event instanceof MouseEvent && event.getSource() instanceof Component)
		{
			// rien
		} else if (blocked && event instanceof KeyEvent && event.getSource() instanceof Component)
		{
			// rien
		} else
		{
			super.dispatchEvent(event);
		}
	}

	public void setBlockingEnabled(boolean block)
	{
		// this methods must be called from the AWT thread to avoid
		// toggling between states while events are being processed
		if (block && !m_inBlockedState)
		{
			if (!m_alreadyBlockedOnce)
			{
				// here is where we replace the SystemQueue
				m_sysQ.push(this);
				m_alreadyBlockedOnce = true;
			}
			m_inBlockedState = true;
		} else if (!block && m_inBlockedState)
		{
			m_inBlockedState = false;
		}
	}
}
