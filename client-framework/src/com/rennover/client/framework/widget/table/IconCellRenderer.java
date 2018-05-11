package com.rennover.client.framework.widget.table;

import java.awt.Component;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import com.rennover.client.framework.widget.base.ZLabel;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class IconCellRenderer implements TableCellRenderer
{
	private ZLabel m_component = new ZLabel();

	/**
	 * Constructor for NationalCcnTableCellEditorRenderer.
	 */
	public IconCellRenderer()
	{
		super();
		m_component.setHorizontalAlignment(ZLabel.CENTER);
	}

	private Map m_iconMap = new Hashtable();

	private Map m_textMap = new Hashtable();

	public void setIconValue(Object value, Icon icon, String text)
	{
		if (value == null)
		{
			return;
		}

		if (icon != null)
		{
			m_iconMap.put(value, icon);
		}

		if (icon != null)
		{
			m_textMap.put(value, text);
		}
	}

	public void setIconValue(Object value, Icon icon)
	{
		if (value != null)
		{
			m_iconMap.put(value, icon);
		}
	}

	public Icon getIconValue(Object value)
	{
		return (Icon) m_iconMap.get(value);
	}

	public String getTextValue(Object value)
	{
		return (String) m_textMap.get(value);
	}

	/**
	 * TableCellRenderer Interface
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	        int row, int column)
	{
		Icon icon = getIconValue(value);
		m_component.setIcon(icon);

		String text = getTextValue(value);
		m_component.setText(text);

		if (isSelected || hasFocus)
		{
			m_component.setForeground(table.getSelectionForeground());
			m_component.setBackground(table.getSelectionBackground());
		} else
		{
			m_component.setForeground(table.getForeground());
			m_component.setBackground(table.isCellEditable(row, column) ? table.getBackground() : UIManager
			        .getColor("Table.disabledBackground"));
		}

		return m_component;
	}
}
