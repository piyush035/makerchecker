package com.rennover.client.framework.widget.field;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.checkbox.DoubleCheckBox;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DoubleCheckField extends DoubleCheckBox implements InputField
{
	private ZLabel m_associatedLabel;

	private boolean m_mandatoryField = false;

	private boolean m_validField = true;

	/**
	 * @param labelTrue
	 * @param labelFalse
	 */
	public DoubleCheckField(String labelTrue, String labelFalse)
	{
		super(labelTrue, labelFalse);
	}

	/**
	 * @param labelTrue
	 * @param labelFalse
	 * @param orientation
	 */
	public DoubleCheckField(String labelTrue, String labelFalse, int orientation)
	{
		super(labelTrue, labelFalse, orientation);
	}

	/**
	 * @param labelTrue
	 * @param labelFalse
	 * @param valueTrue
	 * @param valueFalse
	 * @param valueNull
	 */
	public DoubleCheckField(String labelTrue, String labelFalse, Object valueTrue, Object valueFalse, Object valueNull)
	{
		super(labelTrue, labelFalse, valueTrue, valueFalse, valueNull);
	}

	/**
	 * @param labelTrue
	 * @param labelFalse
	 * @param valueTrue
	 * @param valueFalse
	 * @param valueNull
	 * @param orientation
	 */
	public DoubleCheckField(String labelTrue, String labelFalse, Object valueTrue, Object valueFalse, Object valueNull,
	        int orientation)
	{
		super(labelTrue, labelFalse, valueTrue, valueFalse, valueNull, orientation);
	}

	public Object getValue()
	{
		return super.getValue();
	}

	public void setValue(Object value)
	{
		super.setValue(value);
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