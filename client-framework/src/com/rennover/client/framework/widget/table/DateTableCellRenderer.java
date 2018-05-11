package com.rennover.client.framework.widget.table;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.hotel.common.helper.DateHelper;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class DateTableCellRenderer extends ZLabel implements TableCellRenderer
{
	protected static final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

	DateFormat m_dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public DateTableCellRenderer()
	{
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	        int row, int column)
	{
		if (value != null)
		{
			if (DateHelper.INVALID_DATE.equals(value))
			{
				setText(" #ERR ");
			} else if (value != null && value instanceof Date)
			{
				Date dateValue = (Date) value;
				setText(m_dateFormat.format(dateValue));
			} else
			{
				setText(" #ERR ");
			}
		} else
		{
			setText(" - ");
		}

		if (isSelected)
		{
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else
		{
			setForeground(table.getForeground());
			setBackground(table.isCellEditable(row, column) ? table.getBackground() : UIManager
			        .getColor("Table.disabledBackground"));
		}

		setFont(table.getFont());
		setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : noFocusBorder);

		return this;
	}
}