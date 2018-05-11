package com.rennover.client.framework.valueobject.widget;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.rennover.client.framework.widget.field.SelectionListField;

class VOPropertySelectionListEventHandler extends VOPropertyFieldEventHandler implements ListSelectionListener
{
	public VOPropertySelectionListEventHandler(SelectionListField listField)
	{
		listField.addListSelectionListener(this);
	}

	public void valueChanged(ListSelectionEvent e)
	{
		notifyValueObjectModel();
	}
}