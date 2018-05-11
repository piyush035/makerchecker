package com.rennover.client.framework.widget.table;

import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.UIManager;

import com.rennover.client.framework.widget.base.ZLabel;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DefaultTableCellRenderer extends javax.swing.table.DefaultTableCellRenderer
{
	/**
	 * 
	 */
	public DefaultTableCellRenderer()
	{
		super();
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	        int row, int column)
	{
		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (!isSelected)
		{
			setBackground(table.isCellEditable(row, column) ? table.getBackground() : UIManager
			        .getColor("Table.disabledBackground"));
		}

		setToolTipText(getText());
		return comp;
	}

	public static Component adaptColorComponent(JComponent component, JTable table, Object value, boolean isSelected,
	        boolean hasFocus, int row, int column)
	{
		if (isSelected)
		{
			component.setForeground(table.getSelectionForeground());
			component.setBackground(table.isCellEditable(row, column) && hasFocus ? table.getBackground() : table
			        .getSelectionBackground());
		} else
		{
			component.setForeground(table.getForeground());
			component.setBackground(table.isCellEditable(row, column) ? table.getBackground() : UIManager
			        .getColor("Table.disabledBackground"));
		}

		component.setFont(table.getFont());
		component.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : noFocusBorder);

		return (Component) component;
	}

	static class NumberRenderer extends DefaultTableCellRenderer
	{
		public NumberRenderer()
		{
			super();
			setHorizontalAlignment(ZLabel.RIGHT);
		}
	}

	static class DoubleRenderer extends NumberRenderer
	{
		NumberFormat m_formatter;

		public DoubleRenderer()
		{
			super();
		}

		public void setValue(Object value)
		{
			if (m_formatter == null)
			{
				m_formatter = NumberFormat.getInstance();
			}
			setText((value == null) ? "" : m_formatter.format(value));
		}
	}
}
