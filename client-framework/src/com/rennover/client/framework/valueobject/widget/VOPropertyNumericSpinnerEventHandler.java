package com.rennover.client.framework.valueobject.widget;

import javax.swing.event.ChangeEvent;

import com.rennover.client.framework.widget.base.ZSpinner;
import com.rennover.client.framework.widget.field.NumericSpinnerField;

public class VOPropertyNumericSpinnerEventHandler extends VOPropertyFieldEventHandler implements
        javax.swing.event.ChangeListener
{
	public VOPropertyNumericSpinnerEventHandler(NumericSpinnerField numericSpinnerField)
	{
		numericSpinnerField.getModel().addChangeListener(this);
	}

	public void stateChanged(ChangeEvent e)
	{
		ZSpinner.releaseMouse((ZSpinner) getField());
		notifyValueObjectModel();
	}
}