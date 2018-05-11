package com.rennover.client.framework.widget.field;

import com.rennover.client.framework.widget.base.ZCheckBox;
import com.rennover.client.framework.widget.base.ZLabel;

public class CheckBoxField extends ZCheckBox implements InputField
{
	private boolean m_mandatoryField = false;

	private ZLabel m_associatedLabel;

	private boolean m_validField = true;

	public CheckBoxField()
	{
		super();
	}

	public CheckBoxField(String title)
	{
		super(title);
	}

	public void setAssociatedLabel(ZLabel associatedLabel)
	{
		m_associatedLabel = associatedLabel;
		InputFieldHelper.doDisplayState(this);
	}

	public ZLabel getAssociatedLabel()
	{
		return m_associatedLabel;
	}

	public void setMandatoryField(boolean mandatory)
	{
		m_mandatoryField = mandatory;
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

	public Object getValue()
	{
		return Boolean.valueOf(isSelected());
	}

	public void setValue(Object value)
	{
		if ((value != null) && value instanceof Boolean)
		{
			setSelected(((Boolean) value).booleanValue());
		} else
		{
			setSelected(false);
		}
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