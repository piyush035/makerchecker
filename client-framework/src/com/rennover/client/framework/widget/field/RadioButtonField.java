package com.rennover.client.framework.widget.field;

import java.util.List;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.radiobutton.RadioButtonPanel;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class RadioButtonField extends RadioButtonPanel implements InputField
{
	private boolean m_mandatoryField = false;

	private ZLabel m_associatedLabel;

	private boolean m_validField = true;

	/**
	 * Constructor for RadioButtonField.
	 */
	public RadioButtonField()
	{
		super();
	}

	/**
	 * Constructor for RadioButtonField.
	 * 
	 * @param objectList
	 */
	public RadioButtonField(List objectList)
	{
		super(objectList);
	}

	// removed the commented method RadioButtonField(ValueObjectListModel
	// valueObjectListModel)

	/**
	 * Constructor for RadioButtonField.
	 * 
	 * @param orientation
	 * @param objectList
	 */
	public RadioButtonField(int orientation, List objectList)
	{
		super(orientation, objectList);
	}

	/**
	 * Constructor for RadioButtonField.
	 * 
	 * @param orientation
	 */
	public RadioButtonField(int orientation)
	{
		super(orientation);
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
		return getSelectedItem();
	}

	public void setValue(Object value)
	{
		setSelectedItem(value);
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