package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.widget.field.RadioButtonField;
import com.rennover.client.framework.widget.radiobutton.ActiveRadioButtonListener;

class VOPropertyRadioButtonEventHandler extends VOPropertyFieldEventHandler implements ActiveRadioButtonListener
{
	public VOPropertyRadioButtonEventHandler(RadioButtonField field)
	{
		field.addRadioButtonListener(this);
	}

	public void dataChanged()
	{
		notifyValueObjectModel();
	}
}