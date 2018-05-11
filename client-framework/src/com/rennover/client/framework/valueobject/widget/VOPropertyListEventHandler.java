package com.rennover.client.framework.valueobject.widget;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.rennover.client.framework.widget.field.ListField;

public class VOPropertyListEventHandler extends VOPropertyFieldEventHandler implements ListDataListener
{
	public VOPropertyListEventHandler(ListField listField)
	{
		listField.getModel().addListDataListener(this);
	}

	public void intervalAdded(ListDataEvent e)
	{
		notifyValueObjectModel();
	}

	public void intervalRemoved(ListDataEvent e)
	{
		notifyValueObjectModel();
	}

	public void contentsChanged(ListDataEvent e)
	{
		notifyValueObjectModel();
	}
}