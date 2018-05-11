package com.rennover.client.framework.widget.field;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZTextField;

/**
 * 
 * @audit dattias 30/12/02
 */
public class InputTextField extends ZTextField implements InputField
{
	private ZLabel m_associatedLabel;

	private boolean m_mandatoryField = false;

	private boolean m_validField = true;

	public InputTextField()
	{
		super();
		init();
	}

	public InputTextField(int maxChars)
	{
		super(maxChars);
		init();
	}

	public InputTextField(int maxChars, int columns)
	{
		super(maxChars);
		setColumns(columns);
		init();
	}

	private void init()
	{
		// removed the commented code for focus listner
	}

	public Object getValue()
	{
		return this.getText();
	}

	public void setValue(Object newValue)
	{
		Object oldValue = getValue();
		setText(newValue != null ? newValue.toString() : null);
		firePropertyChange("value", oldValue, newValue);
		setValidField(isValidValue());
	}

	// --- Gestion de l'aspect obligatoire et de validité ainsi que du label
	// associé
	public void setMandatoryField(boolean mandatory)
	{
		m_mandatoryField = mandatory;
		InputFieldHelper.doDisplayMandatory(this, mandatory);
	}

	public boolean isMandatoryField()
	{
		return m_mandatoryField;
	}

	public void setValidField(boolean valid)
	{
		m_validField = valid;
		InputFieldHelper.doDisplayValid(this, valid);
	}

	public boolean isValidValue()
	{
		return true;
	}

	public boolean isValidField()
	{
		return m_validField;
	}

	public boolean isValueSetted()
	{
		return true;
	}

	public ZLabel getAssociatedLabel()
	{
		return m_associatedLabel;
	}

	public void setAssociatedLabel(ZLabel associatedLabel)
	{
		m_associatedLabel = associatedLabel;
		InputFieldHelper.doDisplayState(this);
	}

	public boolean isReadOnly()
	{
		return InputFieldHelper.isReadOnly(this);
	}

	public void setReadOnly(boolean readOnly)
	{
		InputFieldHelper.setReadOnly(this, readOnly);
	}

	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		InputFieldHelper.doDisplayState(this);
	}

	public void setVisible(boolean aFlag)
	{
		super.setVisible(aFlag);
		if (m_associatedLabel != null)
		{
			m_associatedLabel.setVisible(aFlag);
		}
	}
}
