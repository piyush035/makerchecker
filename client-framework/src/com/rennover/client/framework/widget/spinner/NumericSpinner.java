package com.rennover.client.framework.widget.spinner;

import com.rennover.client.framework.widget.base.ZSpinner;

public class NumericSpinner extends ZSpinner
{
	private Class m_numberClass;

	public NumericSpinner()
	{
		this(Integer.class, 0, 1000, 3000, 1, 4);
	}

	/**
	 * Constructeur
	 * 
	 * @param numberClass
	 *            class of the number return type
	 * @param value
	 *            default value
	 * @param minimum
	 *            minimum value
	 * @param maximum
	 *            maximum value
	 * @param stepSize
	 *            size of the step
	 * @param length
	 *            count max of characters
	 */
	public NumericSpinner(Class numberClass, int value, int minimum, int maximum, int stepSize, int length)
	{
		super(new DefaultSpinnerNumberModel(value, minimum, maximum, stepSize));
		m_numberClass = numberClass;
		setEditor(new NumericSpinnerEditor(this, numberClass, length));
	}

	/**
	 * @see javax.swing.JSpinner#setValue(java.lang.Object)
	 */
	public void setValue(Object value)
	{
		// 8420 - Start
		((NumericSpinnerEditor) getEditor()).getNumericField().setValue(value);
		// 8420 - End
		super.setValue(value);
	}

	/**
	 * @see javax.swing.JSpinner#getValue()
	 */
	public Object getValue()
	{
		Object value = super.getValue();
		return value;
	}
}