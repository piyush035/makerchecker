package com.rennover.client.framework.widget.base;

import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.rennover.client.framework.widget.panel.StatusBar;
import com.rennover.client.framework.window.DefaultFrame;
import com.rennover.client.framework.window.WindowManager;

public class ZMenuItem extends JMenuItem implements ChangeListener
{
	public ZMenuItem()
	{
		init();
	}

	/**
	 * @param icon
	 */
	public ZMenuItem(Icon icon)
	{
		super(icon);
		init();
	}

	/**
	 * @param a
	 */
	public ZMenuItem(Action a)
	{
		super(a);
		init();
	}

	/**
	 * @param text
	 * @param icon
	 */
	public ZMenuItem(String text, Icon icon)
	{
		super(text, icon);
		init();
	}

	/**
	 * @param text
	 * @param mnemonic
	 */
	public ZMenuItem(String text, int mnemonic)
	{
		super(text, mnemonic);
		init();
	}

	/**
	 * 
	 * @param title
	 */
	public ZMenuItem(String title)
	{
		super(title);
		init();
	}

	/**
	 * 
	 * 
	 */
	protected void init()
	{
		initToolTip();
	}

	private void initToolTip()
	{
		Action action = getAction();
		String description = action != null ? (String) action.getValue(Action.LONG_DESCRIPTION) : null;

		description = ((description != null) || "".equals(description)) ? description : getText();
		if (description != null)
		{
			setToolTipText(description);
		}
	}

	/**
	 * Retourne la description contenue dans l'action associée au menu. si
	 * l'description n'existe pas c'est le nom du menu qui est retourné.
	 * 
	 * @return
	 */
	public String getDescription()
	{
		// Récupération de la description dans l'action du menu
		Action action = getAction();
		String description = action != null ? (String) action.getValue(Action.LONG_DESCRIPTION) : getText();
		description = ((description != null) | "".equals(description)) ? description : getText();
		return description;
	}

	/**
	 * Utilisation Récupération du changement d'état pour mettre à jour la barre
	 * d'état de la fenêtre
	 */
	public void stateChanged(ChangeEvent e)
	{
		Window window = WindowManager.getOwningWindow(this);
		if (window instanceof DefaultFrame)
		{
			StatusBar statusBar = ((DefaultFrame) window).getStatusBar();
			if (isArmed())
			{
				statusBar.setText(getDescription());
			} else
			{
				statusBar.setText("");
			}
		}
	}

	protected PropertyChangeListener createActionPropertyChangeListener(Action a)
	{
		return new AbstractActionPropertyChangeListener(this, a)
		{
			public void propertyChange(PropertyChangeEvent e)
			{
				String propertyName = e.getPropertyName();
				JMenuItem mi = (JMenuItem) getTarget();
				if (mi == null)
				{ // WeakRef GC'ed in 1.2
					Action action = (Action) e.getSource();
					action.removePropertyChangeListener(this);
				} else
				{
					if (e.getPropertyName().equals(Action.NAME))
					{
						String text = (String) e.getNewValue();
						mi.setText(text);
						mi.repaint();
					} else if (propertyName.equals("enabled"))
					{
						Boolean enabledState = (Boolean) e.getNewValue();
						mi.setEnabled(enabledState.booleanValue());
						mi.repaint();
					} else if (e.getPropertyName().equals(Action.SMALL_ICON))
					{
						Icon icon = (Icon) e.getNewValue();
						mi.setIcon(icon);
						mi.invalidate();
						mi.repaint();
					} else if (e.getPropertyName().equals(Action.MNEMONIC_KEY))
					{
						Integer mn = (Integer) e.getNewValue();
						mi.setMnemonic(mn.intValue());
						mi.invalidate();
						mi.repaint();
					} else if (e.getPropertyName().equals(Action.SHORT_DESCRIPTION))
					{
						String text = (String) e.getNewValue();
						mi.setToolTipText(text);
						mi.invalidate();
						mi.repaint();
					}
				}
			}
		};
	}

	abstract static class AbstractActionPropertyChangeListener implements PropertyChangeListener
	{
		private static ReferenceQueue s_queue;

		private WeakReference m_target;

		private Action m_action;

		AbstractActionPropertyChangeListener(JComponent c, Action a)
		{
			super();
			setTarget(c);
			this.m_action = a;
		}

		public void setTarget(JComponent c)
		{
			if (s_queue == null)
			{
				s_queue = new ReferenceQueue();
			}

			// Check to see whether any old buttons have
			// been enqueued for GC. If so, look up their
			// PCL instance and remove it from its Action.
			OwnedWeakReference r;
			while ((r = (OwnedWeakReference) s_queue.poll()) != null)
			{
				AbstractActionPropertyChangeListener oldPCL = (AbstractActionPropertyChangeListener) r.getOwner();
				Action oldAction = oldPCL.getAction();
				if (oldAction != null)
				{
					oldAction.removePropertyChangeListener(oldPCL);
				}
			}
			this.m_target = new OwnedWeakReference(c, s_queue, this);
		}

		public JComponent getTarget()
		{
			return (JComponent) this.m_target.get();
		}

		public Action getAction()
		{
			return m_action;
		}

		private static class OwnedWeakReference extends WeakReference
		{
			private Object m_owner;

			OwnedWeakReference(Object target, ReferenceQueue queue, Object owner)
			{
				super(target, queue);
				this.m_owner = owner;
			}

			public Object getOwner()
			{
				return m_owner;
			}
		}
	}
}