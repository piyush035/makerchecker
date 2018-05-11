package com.rennover.client.framework.widget.base;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ZSpinner extends JSpinner implements ChangeListener
{
	private Action m_changeAction;

	public ZSpinner(SpinnerModel model)
	{
		super(model);
		setBorder(null);
		getModel().addChangeListener(this);
	}

	/**
	 * @deprecated use setChangeAction(Action action) or use
	 *             releaseMouse(JSpinner spinner) before opening a message
	 *             dialog or the spinner will keep spinning after a click on an
	 *             arrowbutton.
	 */
	public void addChangeListener(ChangeListener listener)
	{
		super.addChangeListener(listener);
	}

	/**
	 * implementation de ChangeListener
	 */
	public void stateChanged(ChangeEvent e)
	{
		releaseMouse();
		if (m_changeAction != null)
		{
			m_changeAction.actionPerformed(new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "change"));
		}
	}

	/**
	 * Permet d'affecter l'action à lancer lorsque la valeur du spinner change
	 * 
	 * @param action
	 */
	public void setChangeAction(Action action)
	{
		m_changeAction = action;
	}

	protected void releaseMouse()
	{
		releaseMouse(ZSpinner.this);
	}

	public static void releaseMouse(JSpinner spinner)
	{
		for (int i = 0; i < spinner.getComponentCount(); i++)
		{
			Component comp = (Component) spinner.getComponent(i);
			if (comp instanceof JButton)
			{
				releaseMouse(comp);
			}
		}
	}

	private static void releaseMouse(Component component)
	{
		MouseListener[] listeners = component.getMouseListeners();
		for (int i = 0; i < listeners.length; i++)
		{
			MouseListener listener = (MouseListener) listeners[i];
			listener.mouseReleased(new MouseEvent(component, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0,
			        0, 0, 1, false));
		}
	}
}