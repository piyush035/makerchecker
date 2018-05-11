package com.rennover.client.framework.valueobject.widget;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.InputFormattedTextField;
import com.rennover.client.framework.widget.field.InputTextField;
import com.rennover.client.framework.widget.field.NumericField;
import com.rennover.client.framework.widget.field.TextAreaField;
import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.validation.ValidationRules;

/**
 * @author tcaboste
 * @audit dattias 30/12/02
 */
class VOPropertyNumericFieldEventHandler extends VOPropertyFieldEventHandler implements DocumentListener
{
	public VOPropertyNumericFieldEventHandler(InputTextField textField, ValueObjectModel valueObjectModel,
	        String propertyName)
	{
		this(textField);
		setValueObjectModel(textField, valueObjectModel, propertyName);
	}

	public VOPropertyNumericFieldEventHandler(InputTextField textField)
	{
		textField.getDocument().addDocumentListener(this);
	}

	public VOPropertyNumericFieldEventHandler(TextAreaField textField, ValueObjectModel valueObjectModel,
	        String propertyName)
	{
		this(textField);
		setValueObjectModel(textField, valueObjectModel, propertyName);
	}

	public VOPropertyNumericFieldEventHandler(TextAreaField textField)
	{
		textField.getDocument().addDocumentListener(this);
	}

	public void setValueObjectModel(NumericField field, ValueObjectModel valueObjectModel, String propertyName)
	{
		Class propertyType = valueObjectModel.getPropertyType(propertyName);
		Class fieldType = field.getNumberClass();

		if (!propertyType.isAssignableFrom(fieldType))
		{
			throw new IncoherenceException("The property class '" + propertyType
			        + "' is not compatible with the class used in the field '" + fieldType + "'");
		}

		super.setValueObjectModel(field, valueObjectModel, propertyName);
	}

	protected void applyValidationRules(ValidationRules rules)
	{
		super.applyValidationRules(rules);

		if (m_field instanceof NumericField)
		{
			NumericField numericField = (NumericField) m_field;

			Long sizeMax = rules.getSizeMax();
			Long decDigitsMax = rules.getDecimalDigits();

			if (sizeMax != null && decDigitsMax != null)
			{
				numericField.setNumberDigits(sizeMax.intValue(), decDigitsMax.intValue());
			}

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