package com.rennover.client.framework.widget.spinner;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class DefaultSpinnerNumberModel extends SpinnerNumberModel
{
	private Number m_value;

	private Number m_stepSize;

	private Number m_minimum;

	private Number m_maximum;

	/**
	 * Constructs a <code>SpinnerNumberModel</code> with the specified
	 * <code>value</code>, <code>minimum</code>/<code>maximum</code>
	 * bounds, and <code>stepSize</code>.
	 * 
	 * @param value
	 *            the current value of the model
	 * @param minimum
	 *            the first number in the sequence
	 * @param maximum
	 *            the last number in the sequence
	 * @param stepSize
	 *            the difference between elements of the sequence
	 * @throws IllegalArgumentException
	 *             if the following expression is false:
	 *             <code>minimum &lt;= value &lt;= maximum</code>
	 */
	public DefaultSpinnerNumberModel(int value, int minimum, int maximum, int stepSize)
	{
		super(value, minimum, maximum, stepSize);
		m_value = new Integer(value);
		m_stepSize = new Integer(stepSize);
		m_minimum = new Integer(minimum);
		m_maximum = new Integer(maximum);
	}

	/**
	 * Returns the next number in the sequence.
	 * 
	 * @return <code>value + stepSize</code> or <code>null</code> if the sum
	 *         exceeds <code>maximum</code>.
	 * 
	 * @see SpinnerModel#getNextValue
	 * @see #getPreviousValue
	 * @see #setStepSize
	 */
	public Object getNextValue()
	{
		return incrValue(+1);
	}

	/**
	 * Returns the previous number in the sequence.
	 * 
	 * @return <code>value - stepSize</code>, or <code>null</code> if the
	 *         sum is less than <code>minimum</code>.
	 * 
	 * @see SpinnerModel#getPreviousValue
	 * @see #getNextValue
	 * @see #setStepSize
	 */
	public Object getPreviousValue()
	{
		return incrValue(-1);
	}

	/**
	 * Returns the value of the current element of the sequence.
	 * 
	 * @return the value property
	 * @see #setValue
	 * @see #getNumber
	 */
	public Object getValue()
	{
		return m_value;
	}

	/**
	 * Sets the current value for this sequence. If <code>value</code> is
	 * <code>null</code>, or not a <code>Number</code>, an
	 * <code>IllegalArgumentException</code> is thrown. No bounds checking is
	 * done here; the new value may invalidate the
	 * <code>(minimum &lt;= value &lt;= maximum)</code> invariant enforced by
	 * the constructors. It's also possible to set the value to be something
	 * that wouldn't naturally occur in the sequence, i.e. a value that's not
	 * modulo the <code>stepSize</code>. This is to simplify updating the
	 * model, and to accommodate spinners that don't want to restrict values
	 * that have been directly entered by the user. Naturally, one should ensure
	 * that the <code>(minimum &lt;= value &lt;= maximum)</code> invariant is
	 * true before calling the <code>next</code>, <code>previous</code>,
	 * or <code>setValue</code> methods.
	 * <p>
	 * This method fires a <code>ChangeEvent</code> if the value has changed.
	 * 
	 * @param value
	 *            the current (non <code>null</code>) <code>Number</code>
	 *            for this sequence
	 * @throws IllegalArgumentException
	 *             if <code>value</code> is <code>null</code> or not a
	 *             <code>Number</code>
	 * @see #getNumber
	 * @see #getValue
	 * @see SpinnerModel#addChangeListener
	 */
	public void setValue(Object value)
	{
		if (value instanceof Number)
		{
			if (!value.equals(m_value))
			{
				m_value = (Number) value;
				fireStateChanged();
			}
		} else if (value == null)
		{
			m_value = null;
			fireStateChanged();
		} else if (!(value instanceof Number))
		{
			throw new IllegalArgumentException("illegal value");
		}
	}

	private Number incrValue(int dir)
	{
		Number newValue;
		if (m_value != null)
		{
			long v = m_value.longValue() + (m_stepSize.longValue() * (long) dir);
			if (m_value instanceof Long)
			{
				newValue = new Long(v);
			} else if (m_value instanceof Integer)
			{
				newValue = new Integer((int) v);
			} else if (m_value instanceof Short)
			{
				newValue = new Short((short) v);
			} else
			{
				newValue = new Byte((byte) v);
			}
		} else
		{
			// Si la valeur précédente est null, alors si l'utilisateur a
			// incrémenté, on met la valeur minimum, si l'utilisateur a
			// décrémenté, on met la valeur maximum
			if (m_value instanceof Long)
			{
				if (dir < 0)
				{
					newValue = new Long(m_maximum.longValue());
				} else
				{
					newValue = new Long(m_minimum.longValue());
				}
			} else if (m_value instanceof Integer)
			{
				if (dir < 0)
				{
					newValue = new Integer(m_maximum.intValue());
				} else
				{
					newValue = new Integer(m_minimum.intValue());
				}
			} else if (m_value instanceof Short)
			{
				if (dir < 0)
				{
					newValue = new Short(m_maximum.shortValue());
				} else
				{
					newValue = new Short(m_minimum.shortValue());
				}
			} else
			{
				if (dir < 0)
				{
					newValue = new Byte(m_maximum.byteValue());
				} else
				{
					newValue = new Byte(m_minimum.byteValue());
				}
			}
		}

		// On teste si la valeur demandé est comprise entre le minimum et le
		// maximum, sinon, on ne modifie pas la valeur
		if (m_value instanceof Long)
		{
			if ((m_maximum != null) && (newValue.longValue() > m_maximum.intValue()))
			{
				return null;
			}
			if ((m_minimum != null) && (newValue.longValue() < m_minimum.intValue()))
			{
				return null;
			}
		} else if (m_value instanceof Integer)
		{
			if ((m_maximum != null) && (newValue.intValue() > m_maximum.intValue()))
			{
				return null;
			}
			if ((m_minimum != null) && (newValue.intValue() < m_minimum.intValue()))
			{
				return null;
			}
		} else if (m_value instanceof Short)
		{
			if ((m_maximum != null) && (newValue.shortValue() > m_maximum.intValue()))
			{
				return null;
			}
			if ((m_minimum != null) && (newValue.shortValue() < m_minimum.intValue()))
			{
				return null;
			}
		} else
		{
			if ((m_maximum != null) && (newValue.byteValue() > m_maximum.intValue()))
			{
				return null;
			}
			if ((m_minimum != null) && (newValue.byteValue() < m_minimum.intValue()))
			{
				return null;
			}
		}
		return newValue;
	}
}