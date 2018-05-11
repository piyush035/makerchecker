package com.rennover.client.framework.widget.field;

import java.util.List;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.combobox.ComboBoxForList;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ComboBoxField extends ComboBoxForList implements InputField
{
	// --- Gestion de l'aspect obligatoire et de validité ainsi que du label
	// associé
	private ZLabel m_associatedLabel;

	private boolean m_mandatoryField = false;

	private boolean m_validField = true;

	public ComboBoxField()
	{
		super();
		updateTooltip();
	}

	public ComboBoxField(List objectList)
	{
		this();
		setObjectList(objectList);
		updateTooltip();
	}

	// --- InputField Interface ---
	public Object getValue()
	{
		return getSelectedItem();
	}

	public void setValue(Object value)
	{
		setSelectedItem(value);
	}

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

	/**
	 * Returns the associatedLabel.
	 * 
	 * @return ZLabel
	 */
	public ZLabel getAssociatedLabel()
	{
		return m_associatedLabel;
	}

	/**
	 * Sets the associatedLabel.
	 * 
	 * @param associatedLabel
	 *            The associatedLabel to set
	 */
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