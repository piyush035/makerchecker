package com.rennover.client.framework.widget.field;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZTextArea;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class TextAreaField extends ZTextArea implements InputField
{
	private ZLabel m_associatedLabel;

	private boolean m_mandatoryField = false;

	private boolean m_validField = true;

	private boolean m_replaceMode = false;

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

	public void setText(String t)
	{
		setReplaceMode(true);
		try
		{
			super.setText(t);
		} finally
		{
			setReplaceMode(false);
		}
	}

	/**
	 * @return
	 */
	public boolean isReplaceMode()
	{
		return m_replaceMode;
	}

	/**
	 * @param b
	 */
	public void setReplaceMode(boolean b)
	{
		m_replaceMode = b;
	}

	// --- Gestion de l'aspect obligatoire et de validit� ainsi que du label
	// associ�
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

	public boolean isValidField()
	{
		return m_validField;
	}

	public boolean isValidValue()
	{
		return true;
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