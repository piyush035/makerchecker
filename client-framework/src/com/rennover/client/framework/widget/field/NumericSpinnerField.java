package com.rennover.client.framework.widget.field;

import java.awt.Dimension;
import java.awt.FontMetrics;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.spinner.NumericSpinner;
import com.rennover.client.framework.widget.spinner.NumericSpinnerEditor;

public class NumericSpinnerField extends NumericSpinner implements InputField
{
	// --- Gestion de l'aspect obligatoire et de validité ainsi que du label
	// associé
	private ZLabel m_associatedLabel;

	private boolean m_mandatoryField = false;

	private boolean m_validField = true;

	int m_columns = 0;

	private int m_columnWidth = 0;

	public NumericSpinnerField()
	{
		super();
	}

	public NumericSpinnerField(Class numberClass, int value, int minimum, int maximum, int stepSize, int nbCaracters)
	{
		super(numberClass, value, minimum, maximum, stepSize, nbCaracters);
	}

	/**
	 * Sets the associatedLabel.
	 * 
	 * @param associatedLabel
	 *            The associatedLabel to set
	 */
	public void setAssociatedLabel(ZLabel label)
	{
		m_associatedLabel = label;
		if (getEditor() instanceof NumericSpinnerEditor)
		{
			((NumericSpinnerEditor) getEditor()).getNumericField().setAssociatedLabel(label);
		}
		InputFieldHelper.doDisplayState(this);
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

	public boolean isReadOnly()
	{
		return InputFieldHelper.isReadOnly(this);
	}

	public void setReadOnly(boolean readOnly)
	{
		InputFieldHelper.setReadOnly(this, readOnly);
	}

	public void setColumns(int columns)
	{
		m_columns = columns;
	}

	protected int getColumnWidth()
	{
		if (m_columnWidth == 0)
		{
			FontMetrics metrics = getFontMetrics(getFont());
			m_columnWidth = metrics.charWidth('n');
		}
		return m_columnWidth;
	}

	public Dimension getPreferredSize()
	{
		Dimension prefSize = super.getPreferredSize();

		if (m_columns != 0)
		{
			prefSize.width = (m_columns + 1) * getColumnWidth();
		}

		prefSize.width = (int) (Math.round(prefSize.width / 32.0) * 32) + 16;
		return prefSize;
	}
}