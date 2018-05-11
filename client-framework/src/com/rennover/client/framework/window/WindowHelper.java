
package com.rennover.client.framework.window;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.KeyStroke;

import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.widget.base.ZFrame;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class WindowHelper
{
	public static final int NONE = 0;

	public static final int TOP = 1;

	public static final int VCENTER = 2;

	public static final int BOTTOM = 4;

	public static final int LEFT = 8;

	public static final int HCENTER = 16;

	public static final int RIGHT = 32;

	public static void addShortCut(JTabbedPane tabbedPane, String racc, int index)
	{
		tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(racc), racc);
		tabbedPane.getActionMap().put(racc, new TabbedPaneRaccAction(tabbedPane, index));

		StringBuffer newTitle = new StringBuffer(tabbedPane.getTitleAt(index));
		newTitle.append(" (").append(racc).append(")");
		tabbedPane.setTitleAt(index, newTitle.toString());
	}

	public static void setTransversalKeys(int keysId, String firstKey, String secondKey)
	{
		Set set = new HashSet(KeyboardFocusManager.getCurrentKeyboardFocusManager()
		        .getDefaultFocusTraversalKeys(keysId));
		set.clear(); // Call clear() if you want to eliminate the current key
		// set
		set.add(KeyStroke.getKeyStroke(firstKey));
		set.add(KeyStroke.getKeyStroke(secondKey));
		KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(keysId, set);
	}

	public static void addTransversalKeys(int keysId, String key)
	{
		Set set = new HashSet(KeyboardFocusManager.getCurrentKeyboardFocusManager()
		        .getDefaultFocusTraversalKeys(keysId));
		set.add(KeyStroke.getKeyStroke(key));
		KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(keysId, set);
	}

	public static void setEnterAsTab()
	{
		// Change the forward focus traversal keys for the application
		setTransversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, "ENTER", "TAB");
		setTransversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, "shift ENTER", "shift TAB");

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent evt)
			{
				if ("focusOwner".equals(evt.getPropertyName()))
				{
					Component newComp = (Component) evt.getNewValue();
					WindowHelper.scrollRectToVisible(newComp);
				}
			}
		});
	}

	static public void setCloseOnESC(ZDialog window)
	{
		new WindowCloseAction(window);
	}

	static public void setCloseOnESC(ZFrame window)
	{
		new WindowCloseAction(window);
	}

	static private class WindowCloseAction extends AbstractAction
	{
		private ZFrame m_frame;

		private ZDialog m_dialog;

		public WindowCloseAction(ZDialog window)
		{
			m_dialog = window;
			KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
			window.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
			window.getRootPane().getActionMap().put("ESCAPE", this);
		}

		public WindowCloseAction(ZFrame window)
		{
			m_frame = window;
			KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
			window.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
			window.getRootPane().getActionMap().put("ESCAPE", this);
		}

		public void actionPerformed(ActionEvent e)
		{
			if (m_dialog != null)
			{
				m_dialog.dispose();
			} else if (m_frame != null)
			{
				m_frame.dispose();
			}
		}
	}

	/**
	 * Cette méthode permet de rendre visible un composant en scrollant les
	 * ScrollPanes de tous ses parents. This méthode make a component visible by
	 * scrolling all its parent's scrollpanes.
	 * 
	 * @param component
	 *            the component to make visible
	 */
	public static final void scrollRectToVisible(Component component)
	{
		if (component == null)
		{
			return;
		}

		if (component instanceof JTable || component instanceof JList || component instanceof JTextArea)
		{
			component = component.getParent().getParent();
		}

		Rectangle compRect = component.getBounds();
		for (Component parent = component.getParent(); parent != null; parent = parent.getParent())
		{
			if (!(parent.getParent() instanceof JViewport))
			{
				compRect.x += parent.getLocation().x;
				compRect.y += parent.getLocation().y;
			}

			if (parent instanceof JViewport)
			{
				JViewport jvp = (JViewport) parent;

				compRect.x = 0;
				compRect.height += 4;
				compRect.y -= 2;

				((JComponent) jvp.getView()).scrollRectToVisible(compRect);
			}
		}
	}

	private static class TabbedPaneRaccAction extends AbstractAction
	{
		JTabbedPane m_tabbedPane;

		int m_index;

		private TabbedPaneRaccAction(JTabbedPane tabbedPane, int index)
		{
			m_tabbedPane = tabbedPane;
			m_index = index;
		}

		public void actionPerformed(java.awt.event.ActionEvent e)
		{
			m_tabbedPane.setSelectedIndex(m_index);
		}
	}
}