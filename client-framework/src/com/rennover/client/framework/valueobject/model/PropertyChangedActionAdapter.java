package com.rennover.client.framework.valueobject.model;

import java.awt.Component;

import javax.swing.Action;

import com.rennover.client.framework.mvc.ActionAdapter;

public class PropertyChangedActionAdapter extends ActionAdapter implements ValueObjectModelListener
{
	/**
	 * @param action
	 * @param source
	 */
	public PropertyChangedActionAdapter(Action action)
	{
		super(action, null);
	}

	/**
	 * @param action
	 * @param source
	 */
	public PropertyChangedActionAdapter(Action action, Component source)
	{
		super(action, source);
	}

	/**
	 * @param action
	 * @param source
	 * @param command
	 */
	public PropertyChangedActionAdapter(Action action, Component source, String command)
	{
		super(action, source, command);
	}

	public void valueObjectChanged(ValueObjectModelEvent event)
	{
	}

	public void valueObjectPropertyChanged(ValueObjectModelEvent event)
	{
		actionPerformed();
	}
}