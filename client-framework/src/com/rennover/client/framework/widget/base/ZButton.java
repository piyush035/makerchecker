package com.rennover.client.framework.widget.base;

import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;

import com.rennover.client.framework.mvc.ActionHelper;

/**
 * Un simple héritier de {@link javax.swing.JButton}, créé au cas où il s'avère
 * nécessaire d'implémenter des fonctionnalités communes à tous les boutons
 * utilisés dans l'application.
 * 
 * <p>
 * Note: il n'y a pas de constructeur prenant en paramètre une String, comme
 * dans {@link javax.swing.JButton#JButton(java.lang.String) JButton}, parce
 * qu'on {@link javax.swing.AbstractButton#setAction(javax.swing.Action)
 * utilisera} des {@link javax.swing.Action}s qui positionneront le texte
 * eux-même.
 * </p>
 * 
 * <p>
 * Du coup, il est également inutile d'appeler la méthode {@link
 * javax.swing.AbstractButton#setText(java.lang.String)}.
 * </p>
 */
public class ZButton extends JButton
{
	public ZButton()
	{
		super();
		init();
	}

	public ZButton(Icon icon, Action action)
	{
		super(action);
		setIcon(icon);
		setToolTipText(getText());
		setText(null);
		setMargin(new Insets(2, 2, 2, 2));
		init();
	}

	public ZButton(String title)
	{
		super(title);
		init();
	}

	public ZButton(String title, Action action)
	{
		super(action);
		init();
		setText(title);
	}

	public ZButton(Action action)
	{
		super(action);
		init();
	}

	protected PropertyChangeListener createActionPropertyChangeListener(Action a)
	{
		return new ZButtonActionPropertyChangeListener(this, a);
	}

	private void init()
	{
		// removed the commented code for Utilisation de la touche "entrée" pour
		// presser le bouton
	}

	/**
	 * @deprecate cette méthode ne devrait pas être utilisée, puisque le texte
	 *            doit être positionné par l'Action.
	 */
	public void setText(String text)
	{
		super.setText(text);
	}

	public void setEnabled(boolean b)
	{
		Action action = getAction();
		if (action != null)
		{
			if (action.isEnabled())
			{
				super.setEnabled(b);
			} else if (isEnabled())
			{
				super.setEnabled(false);
			}
		} else
		{
			super.setEnabled(b);
		}
	}

	private static class ZButtonActionPropertyChangeListener implements PropertyChangeListener
	{
		private static ReferenceQueue s_queue;

		private WeakReference m_target;

		private Action m_action;

		ZButtonActionPropertyChangeListener(AbstractButton b, Action a)
		{
			this((JComponent) b, a);
		}

		ZButtonActionPropertyChangeListener(JComponent c, Action a)
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
				ZButtonActionPropertyChangeListener oldPCL = (ZButtonActionPropertyChangeListener) r.getOwner();
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

		public void propertyChange(PropertyChangeEvent e)
		{
			String propertyName = e.getPropertyName();
			AbstractButton button = (AbstractButton) getTarget();
			if (button == null)
			{ // WeakRef GC'ed in 1.2
				Action action = (Action) e.getSource();
				action.removePropertyChangeListener(this);
			} else
			{
				if (e.getPropertyName().equals(Action.NAME))
				{
					Boolean hide = (Boolean) button.getClientProperty("hideActionText");
					if (hide == null || hide == Boolean.FALSE)
					{
						String text = (String) e.getNewValue();
						button.setText(text);
						button.repaint();
					}
				} else if (e.getPropertyName().equals(Action.SHORT_DESCRIPTION))
				{
					String text = (String) e.getNewValue();
					button.setToolTipText(text);
				} else if (propertyName.equals("enabled"))
				{
					Boolean enabledState = (Boolean) e.getNewValue();
					button.setEnabled(enabledState.booleanValue());
					button.repaint();
				} else if (e.getPropertyName().equals(ActionHelper.VISIBLE))
				{
					Boolean visibleState = (Boolean) e.getNewValue();
					button.setVisible(visibleState.booleanValue());
					button.repaint();
				} else if (e.getPropertyName().equals(Action.SMALL_ICON))
				{
					Icon icon = (Icon) e.getNewValue();
					button.setIcon(icon);
					button.invalidate();
					button.repaint();
				} else if (e.getPropertyName().equals(Action.MNEMONIC_KEY))
				{
					Integer mn = (Integer) e.getNewValue();
					button.setMnemonic(mn.intValue());
					button.invalidate();
					button.repaint();
				} else if (e.getPropertyName().equals(Action.ACTION_COMMAND_KEY))
				{
					button.setActionCommand((String) e.getNewValue());
				}
			}
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