package com.rennover.client.framework.valueobject.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.rennover.client.framework.widget.field.CheckBoxField;

class VOPropertyCheckBoxEventHandler extends VOPropertyFieldEventHandler implements ActionListener
{
	public VOPropertyCheckBoxEventHandler(CheckBoxField checkBoxField)
	{
		checkBoxField.getModel().addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		notifyValueObjectModel();
	}
}