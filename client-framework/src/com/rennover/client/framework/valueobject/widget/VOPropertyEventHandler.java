
package com.rennover.client.framework.valueobject.widget;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.DoubleCheckField;
import com.rennover.client.framework.widget.field.InputField;

public class VOPropertyEventHandler extends VOPropertyFieldEventHandler implements PropertyChangeListener
{
	public VOPropertyEventHandler()
	{
		super();
	}

	public void setValueObjectModel(InputField field, ValueObjectModel valueObjectModel, String propertyName)
	{
		((DoubleCheckField) field).addPropertyChangeListener("value", this);
		super.setValueObjectModel(field, valueObjectModel, propertyName);
	}

	public void propertyChange(PropertyChangeEvent evt)
	{
		notifyValueObjectModel();
	}
}