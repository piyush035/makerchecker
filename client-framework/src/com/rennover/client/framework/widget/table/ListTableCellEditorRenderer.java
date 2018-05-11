package com.rennover.client.framework.widget.table;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.rennover.client.framework.widget.field.ListField;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ListTableCellEditorRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
{
	private ListField m_component = new ListField();

	private JTable m_table = null;

	private int m_row;

	private int m_column;

	public ListTableCellEditorRenderer()
	{
		m_component.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent e)
			{
				stopCellEditing();
			}
		});
	}

	public Object getCellEditorValue()
	{
		return m_table == null ? null : m_table.getValueAt(m_row, m_column);
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		boolean hasFocus = true;
		ListField listField = (ListField) getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		return new JScrollPane(listField);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	        int row, int column)
	{
		m_table = table;
		m_row = row;
		m_column = column;

		Rectangle cellBounds = m_component.getCellBounds(0, 0);
		int rowHeightUnit = cellBounds != null ? cellBounds.height : 21;
		int rowHeight = rowHeightUnit;

		int nbItems = 1;

		if (value != null)
		{
			List list = new ArrayList((List) value);
			m_component.setObjectList(list);

			nbItems = list.size();

			StringBuffer toolTipText = new StringBuffer("<html>");

			for (int i = 0; i < nbItems; i++)
			{
				if (i > 0)
				{
					toolTipText.append("<br>");
				}
				toolTipText.append(list.get(i).toString());
			}
			toolTipText.append("</html>");
			m_component.setToolTipText(toolTipText.toString());

			if (nbItems > 3)
			{
				nbItems = 3;
			}
			if (nbItems == 0)
			{
				nbItems = 1;
			}

		} else
		{
			m_component.setObjectList(Collections.EMPTY_LIST);
			nbItems = 1;
			m_component.setToolTipText(null);
		}

		rowHeight = rowHeightUnit * nbItems;

		if (table.getRowHeight(row) != rowHeight)
		{
			table.setRowHeight(row, rowHeight);
		}

		if (isSelected)
		{
			m_component.setBackground(table.getSelectionBackground());
			m_component.setForeground(table.getSelectionForeground());
		} else
		{
			m_component.setForeground(table.getForeground());
			m_component.setBackground(UIManager.getColor("Table.disabledBackground"));
		}

		return m_component;
	}

	public Component getTableCellRendererComponentEx(JTable table, Object value, boolean isSelected, boolean hasFocus,
	        int row, int column)
	{
		m_table = table;
		m_row = row;
		m_column = column;

		int rowHeightUnit = table.getFont().getSize() + 6;
		int rowHeight = rowHeightUnit;
		if (null != value)
		{
			List list = new ArrayList((List) value);
			m_component.setObjectList(list);
			if (list.size() > 3)
			{
				rowHeight = rowHeightUnit * 3;
			} else if (list.size() > 0)
			{
				rowHeight = rowHeightUnit * list.size();
			}
		} else
		{
			m_component.setObjectList(Collections.EMPTY_LIST);
		}

		if (isSelected)
		{
			m_component.setBackground(table.getSelectionBackground());
			m_component.setForeground(table.getSelectionForeground());
		} else
		{
			m_component.setForeground(table.getForeground());
			m_component.setBackground(UIManager.getColor("Table.disabledBackground"));
		}

		if (table.getRowHeight(row) != rowHeight)
		{
			table.setRowHeight(row, rowHeight);
		}

		return m_component;
	}
}