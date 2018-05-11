
package com.rennover.client.framework.thread;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;

import com.rennover.client.framework.window.WindowManager;

/**
 * Cette classe est un intermédiaire qui redirige tous les appels à ses méthodes
 * vers une Action secondaire, qui a au préalable été passée au constructeur.
 * <p>
 * Le rôle de cette classe est de désynchroniser les tâches qui prennent du
 * temps, typiquement un appel au backend. <br>
 * Pour cela, au lieu d'exécuter directement la méthode {@link
 * #actionPerformed(ActionEvent)}, elle appelle la méthode équivalente dans
 * l'Action secondaire à partir d'un thread séparé. Ainsi, l'exécution de
 * l'Action ne bloque pas le reste de l'application.
 * <p>
 * Noter que tous les changements de propriétés (nom, état "enabled", etc.)
 * apportés au ActionDesynchronizedAdapter sont propagés vers l'Action
 * secondaire, et vice-versa.
 * <p>
 * 
 * @audit dattias 27/09/02
 *        <p>
 * @tochange supprimer l'attribut m_actionEvent
 * @tochange vérifier que le propagation des changements sur les deux actions
 *           marche correctement
 * @tochange renommer cette classe ActionAsynchronousAdapter
 */
public final class AsynchronousActionAdapter extends AbstractAction
{
	private Action m_action;

	// necessaire pour l'implementation de la methode actionPerformed
	private ActionEvent m_actionEvent;

	private boolean m_useFreeze = true;

	/**
	 * @param action
	 *            l'action que l'on veut vraiment exécuter lors du clic sur un
	 *            bouton
	 */
	public AsynchronousActionAdapter(Action action)
	{
		this(action, true);
	}

	/**
	 * @param action
	 *            l'action que l'on veut vraiment exécuter lors du clic sur un
	 *            bouton
	 * @param useFreeze
	 *            indique si l'action affiche une fenêtre d'attente (en gelant
	 *            le fenêtre en cours)
	 */
	public AsynchronousActionAdapter(Action action, boolean useFreeze)
	{
		super((String) action.getValue(Action.NAME));
		this.m_action = action;
		this.m_useFreeze = useFreeze;

		copyValue(action, Action.SMALL_ICON);
		copyValue(action, Action.MNEMONIC_KEY);
		copyValue(action, Action.LONG_DESCRIPTION);
		copyValue(action, Action.SHORT_DESCRIPTION);
		copyValue(action, Action.ACCELERATOR_KEY);
		copyValue(action, Action.ACTION_COMMAND_KEY);

		// observation des changements de proprietes, afin de les propager a
		// cette action
		m_action.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent evt)
			{
				doPropagateChange(evt, AsynchronousActionAdapter.this);
			}
		});

		this.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent evt)
			{
				doPropagateChange(evt, m_action);
			}
		});
	}

	private void copyValue(Action action, String key)
	{
		this.putValue(key, action.getValue(key));
	}

	private static void doPropagateChange(PropertyChangeEvent evt, Action targetAction)
	{
		String type = evt.getPropertyName();
		if (type.equalsIgnoreCase("enabled"))
		{
			targetAction.setEnabled(((Boolean) evt.getNewValue()).booleanValue());
		} else
		{
			targetAction.putValue(type, evt.getNewValue());
		}
	}

	/**
	 * 
	 * (Des/)Active les boîtes de dialogue d'attente
	 * 
	 */
	public void setUseFreeze(boolean useFreeze)
	{
		m_useFreeze = useFreeze;
	}

	/**
	 * Méthode wrapper qui redirige l'appel vers la méthode correspondante de
	 * l'Action secondaire, à partir d'un Thread séparé.
	 * <p>
	 * 
	 * @tochange sortir la task dans une classe nommée qui prend l'event en
	 *           paramètre de construction
	 */
	public final void actionPerformed(ActionEvent actionEvent)
	{
		this.m_actionEvent = actionEvent;
		Runnable task = new Runnable()
		{
			public void run()
			{
				if (m_useFreeze)
				{
					JComponent source = (JComponent) m_actionEvent.getSource();
					try
					{
						WindowManager.freeze(source);
						doAction();
					} finally
					{
						WindowManager.unfreeze(source);
					}
				} else
				{
					doAction();
				}
			}

			public void doAction()
			{
				m_action.actionPerformed(m_actionEvent);
			}
		};

		TaskHelper.run(task);
	}
}