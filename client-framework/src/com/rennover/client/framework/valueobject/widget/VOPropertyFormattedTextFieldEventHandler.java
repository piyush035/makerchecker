package com.rennover.client.framework.valueobject.widget;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rennover.client.framework.widget.field.InputFormattedTextField;
import com.rennover.client.framework.widget.field.InputTextField;
import com.rennover.client.framework.widget.field.TextAreaField;

/**
 * @author tcaboste
 * @audit dattias 30/12/02
 */
public class VOPropertyFormattedTextFieldEventHandler extends VOPropertyFieldEventHandler implements DocumentListener
{
	public VOPropertyFormattedTextFieldEventHandler(InputFormattedTextField textField)
	{
		textField.getDocument().addDocumentListener(this);
	}

	public Object getFieldValue()
	{
		return ((InputFormattedTextField) m_field).getFieldValue();
	}

	public void setFieldValue(Object value)
	{
		((InputFormattedTextField) m_field).setFieldValue(value);
	}

	protected boolean isReplaceMode()
	{
		if (m_field instanceof InputTextField)
		{
			return ((InputTextField) m_field).isReplaceMode();
		} else if (m_field instanceof InputFormattedTextField)
		{
			return ((InputFormattedTextField) m_field).isReplaceMode();
		} else if (m_field instanceof TextAreaField)
		{
			return ((TextAreaField) m_field).isReplaceMode();
		}
		return false;
	}

	// --- Interface DocumentListener ---
	public void insertUpdate(DocumentEvent e)
	{
		notifyValueObjectModel();
	}

	public void removeUpdate(DocumentEvent e)
	{
		if (!isReplaceMode())
		{
			notifyValueObjectModel();
		}
	}

	public void changedUpdate(DocumentEvent e)
	{
		notifyValueObjectModel();
	}
}