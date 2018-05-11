package com.rennover.client.framework.valueobject.model;

import javax.swing.Action;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ValidityActionActivator extends ValidityChangeListener
{

	private Action m_action;

	public ValidityActionActivator(ValueObjectModel vom, Action action)
	{
		super(vom);
		m_action = action;
	}

	/**
	 * @return
	 */
	public Action getAction()
	{
		return m_action;
	}

	/**
	 * @param action
	 */
	public void setAction(Action action)
	{
		m_action = action;
	}

	protected void validityChanged(boolean isVomValid)
	{
		m_action.setEnabled(isVomValid);
	}

}
