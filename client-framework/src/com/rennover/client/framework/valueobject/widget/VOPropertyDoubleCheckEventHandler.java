
package com.rennover.client.framework.valueobject.widget;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.rennover.client.framework.widget.field.DoubleCheckField;

public class VOPropertyDoubleCheckEventHandler extends VOPropertyFieldEventHandler implements PropertyChangeListener
{
	public VOPropertyDoubleCheckEventHandler(DoubleCheckField field)
	{
		field.addPropertyChangeListener("value", this);
	}

	public void propertyChange(PropertyChangeEvent evt)
	{
		notifyValueObjectModel();
	}
}