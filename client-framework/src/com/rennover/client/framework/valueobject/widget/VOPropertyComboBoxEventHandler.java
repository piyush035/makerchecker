package com.rennover.client.framework.valueobject.widget;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.rennover.client.framework.widget.field.ComboBoxField;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
class VOPropertyComboBoxEventHandler extends VOPropertyFieldEventHandler implements ListDataListener
{
	public VOPropertyComboBoxEventHandler(ComboBoxField field)
	{
		field.getModel().addListDataListener(this);
	}

	public void intervalAdded(ListDataEvent e)
	{
	}

	public void intervalRemoved(ListDataEvent e)
	{
	}

	public void contentsChanged(ListDataEvent e)
	{
		notifyValueObjectModel();
	}
}