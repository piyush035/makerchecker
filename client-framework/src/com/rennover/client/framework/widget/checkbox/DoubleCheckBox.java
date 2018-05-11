package com.rennover.client.framework.widget.checkbox;

import javax.swing.ButtonModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.rennover.client.framework.widget.base.ZCheckBox;
import com.rennover.client.framework.widget.panel.ComponentPanel;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DoubleCheckBox extends ComponentPanel implements ChangeListener
{
	private ZCheckBox m_checkBox1 = null;

	private ZCheckBox m_checkBox2 = null;

	private Object m_value1 = null;

	private Object m_value2 = null;

	private Object m_value3 = null;

	private Object m_value = null;

	/**
	 * 
	 */
	public DoubleCheckBox(String labelTrue, String labelFalse)
	{
		super(VERTICAL);
		init(labelTrue, labelFalse, Boolean.TRUE, Boolean.FALSE, null);
	}

	/**
	 * @param orientation
	 */
	public DoubleCheckBox(String labelTrue, String labelFalse, int orientation)
	{
		super(orientation);
		init(labelTrue, labelFalse, Boolean.TRUE, Boolean.FALSE, null);
	}

	/**
	 * 
	 */
	public DoubleCheckBox(String labelTrue, String labelFalse, Object valueTrue, Object valueFalse, Object valueNull)
	{
		super(VERTICAL);
		init(labelTrue, labelFalse, valueTrue, valueFalse, valueNull);
	}

	/**
	 * @param orientation
	 */
	public DoubleCheckBox(String labelTrue, String labelFalse, Object valueTrue, Object valueFalse, Object valueNull,
	        int orientation)
	{
		super(orientation);
		init(labelTrue, labelFalse, valueTrue, valueFalse, valueNull);
	}

	protected void init(String label1, String label2, Object value1, Object value2, Object value3)
	{
		m_value1 = value1;
		m_value2 = value2;
		m_value3 = value3;

		m_checkBox1 = addCheckBox(label1);
		m_checkBox2 = addCheckBox(label2);
	}

	private ZCheckBox addCheckBox(String label)
	{
		ZCheckBox checkBox = new ZCheckBox(label);
		add(checkBox);
		checkBox.getModel().addChangeListener(this);
		return checkBox;
	}

	public Object getValue()
	{
		return m_value;
	}

	public void setValue(Object value)
	{
		if (!equals(m_value, value))
		{
			Object oldValue = m_value;
			m_value = value;
			updateCheckBoxes();
			firePropertyChange("value", oldValue, value);
		}
	}

	public static boolean equals(Object value1, Object value2)
	{
		if (value1 == null && value2 == null)
		{
			return true;
		} else if (value1 != null && value1.equals(value2))
		{
			return true;
		} else if (value2 != null && value2.equals(value1))
		{
			return true;
		}

		return false;
	}

	protected void updateCheckBoxes()
	{
		Object value = getValue();
		m_checkBox1.setSelected((value != null) && value.equals(m_value1));
		m_checkBox2.setSelected((value != null) && value.equals(m_value2));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	public void stateChanged(ChangeEvent e)
	{
		ButtonModel checkBoxModel = (ButtonModel) e.getSource();

		boolean s1 = m_checkBox1.getModel().isSelected();
		boolean s2 = m_checkBox2.getModel().isSelected();

		if (checkBoxModel == m_checkBox1.getModel() && m_checkBox1.isSelected())
		{
			m_checkBox2.getModel().setSelected(false);
			s2 = false;
			setValue(m_value1);
		} else if (checkBoxModel == m_checkBox2.getModel() && m_checkBox2.isSelected())
		{
			m_checkBox1.getModel().setSelected(false);
			s1 = false;
			setValue(m_value2);
		}

		if (!s1 && !s2)
		{
			setValue(m_value3);
		}
	}
}