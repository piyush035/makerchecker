package com.rennover.client.framework.valueobject.widget;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.InputFormattedTextField;
import com.rennover.client.framework.widget.field.InputTextField;
import com.rennover.client.framework.widget.field.TextAreaField;
import com.rennover.hotel.common.validation.ValidationRules;

/**
 * @author tcaboste
 * @audit dattias 30/12/02
 */
public class VOPropertyTextFieldEventHandler extends VOPropertyFieldEventHandler implements DocumentListener
{
	public VOPropertyTextFieldEventHandler(InputTextField textField, ValueObjectModel valueObjectModel,
	        String propertyName)
	{
		this(textField);
		setValueObjectModel(textField, valueObjectModel, propertyName);
	}

	public VOPropertyTextFieldEventHandler(InputTextField textField)
	{
		textField.getDocument().addDocumentListener(this);
	}

	public VOPropertyTextFieldEventHandler(TextAreaField textField, ValueObjectModel valueObjectModel,
	        String propertyName)
	{
		this(textField);
		setValueObjectModel(textField, valueObjectModel, propertyName);
	}

	public VOPropertyTextFieldEventHandler(TextAreaField textField)
	{
		textField.getDocument().addDocumentListener(this);
	}

	protected void applyValidationRules(ValidationRules rules)
	{
		super.applyValidationRules(rules);
		if (m_field instanceof InputTextField)
		{
			Long sizeMax = rules.getSizeMax();
			((InputTextField) m_field).setMaxChars(sizeMax == null ? -1 : sizeMax.intValue());
			if (m_field instanceof VOPropertyTextField)
			{
				((InputTextField) m_field).setUseUpperCase((boolean) rules.isUppercase());
			}
		} else if (m_field instanceof TextAreaField)
		{
			Long sizeMax = rules.getSizeMax();
			((TextAreaField) m_field).setMaxChars(sizeMax == null ? -1 : sizeMax.intValue());
		}
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