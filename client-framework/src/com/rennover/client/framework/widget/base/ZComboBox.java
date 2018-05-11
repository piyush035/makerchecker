package com.rennover.client.framework.widget.base;

import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import com.rennover.client.framework.widget.combobox.ZComboBoxModel;

public class ZComboBox extends JComboBox
{
	int m_columns = 0;

	private int m_columnWidth = 0;

	private boolean m_editable2 = false;

	public ZComboBox(final Object[] items)
	{
		// TCA: Attention ici on utilise le DefaultComboBoxModel
		super(items);
		init();
	}

	public ZComboBox()
	{
		super(new ZComboBoxModel());
		init();
	}

	public ZComboBox(ComboBoxModel model)
	{
		super(model);
		init();
	}

	private void init()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#setPreferredSize(java.awt.Dimension)
	 */
	public void setPreferredSize(Dimension preferredSize)
	{
		super.setPreferredSize(preferredSize);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#getMinimumSize()
	 */
	public Dimension getMinimumSize()
	{
		return getPreferredSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#getMaximumSize()
	 */
	public Dimension getMaximumSize()
	{
		return getPreferredSize();
	}

	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		if (m_editable2)
		{
			super.setEditable(enabled);
		}
	}

	public void setEditable(boolean aFlag)
	{
		super.setEditable(aFlag);
		m_editable2 = aFlag;
	}
}