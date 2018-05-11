package com.rennover.client.framework.widget.table;

import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.EventObject;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.Action;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.rennover.client.framework.widget.base.ZTable;
import com.rennover.client.framework.widget.field.NumericField;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class DefaultTable extends ZTable
{
	private Map m_columnSizeMap = new Hashtable();

	/**
	 * Constructor for DefaultTable.
	 */
	public DefaultTable()
	{
		super();
	}

	/**
	 * Constructor for DefaultTable.
	 * 
	 * @param rowData
	 * @param columns
	 */
	public DefaultTable(Object[][] rowData, Object[] columns)
	{
		super(rowData, columns);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTable#createDefaultRenderers()
	 */
	protected void createDefaultRenderers()
	{
		super.createDefaultRenderers();

		setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
		setDefaultRenderer(Date.class, new DateTableCellRenderer());
		setDefaultRenderer(Boolean.class, new CheckBoxCellEditorRenderer());
		setDefaultRenderer(List.class, new ListTableCellEditorRenderer());
		setDefaultRenderer(Number.class, new DefaultTableCellRenderer.NumberRenderer());
		setDefaultRenderer(Double.class, new DefaultTableCellRenderer.DoubleRenderer());
		setDefaultRenderer(Float.class, new DefaultTableCellRenderer.DoubleRenderer());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTable#createDefaultEditors()
	 */
	protected void createDefaultEditors()
	{
		super.createDefaultEditors();
		setDefaultEditor(Boolean.class, new CheckBoxCellEditorRenderer());
		setDefaultEditor(List.class, new ListTableCellEditorRenderer());
	}

	public boolean editCellAt(int row, int column, EventObject e)
	{
		boolean retVal = super.editCellAt(row, column, e);
		if (retVal)
		{
			// removed the commented code which was commented to fix defect
			// 7104,7514

			editorComp.requestFocus();

			if (!(e instanceof MouseEvent))
			{
				if (editorComp instanceof NumericField)
				{
					((NumericField) editorComp).dontSelectNextTime();
				}
			}
		}

		return retVal;
	}

	public void setSelectionAction(Action action)
	{
		super.setSelectionAction(action);
	}

	public void setDoubleClickAction(Action action)
	{
		super.setDoubleClickAction(action);
	}

	public boolean isColumnVisible(int columnIndex)
	{
		return getColumnSize(columnIndex).m_visible;
	}

	public void setColumnVisible(int columnIndex, boolean visible)
	{
		ColumnSize colSize = getColumnSize(columnIndex);
		colSize.m_visible = visible;
		setColumnSize(columnIndex, colSize);
	}

	public ColumnSize getColumnSize(int columnIndex)
	{
		ColumnSize colSize = (ColumnSize) m_columnSizeMap.get(new Integer(columnIndex));
		if (colSize == null)
		{
			TableColumn header = getTableHeader().getColumnModel().getColumn(columnIndex);
			colSize = new ColumnSize(header.getMinWidth(), header.getPreferredWidth(), header.getMaxWidth());
		}

		return colSize;
	}

	public void setColumnSize(int columnIndex, ColumnSize colSize)
	{
		if (colSize == null)
		{
			return;
		}

		m_columnSizeMap.put(new Integer(columnIndex), colSize);

		if (colSize.m_visible)
		{
			TableColumn column = getColumnModel().getColumn(columnIndex);
			TableColumn header = getTableHeader().getColumnModel().getColumn(columnIndex);

			if (colSize.m_minWidth != null)
			{
				column.setMinWidth(colSize.m_minWidth.intValue());
				header.setMinWidth(colSize.m_minWidth.intValue());
			}

			if (colSize.m_maxWidth != null)
			{
				column.setMaxWidth(colSize.m_maxWidth.intValue());
				header.setMaxWidth(colSize.m_maxWidth.intValue());
			}

			if (colSize.m_preferredWidth != null)
			{
				int prefWidth = colSize.m_preferredWidth.intValue();

				column.setPreferredWidth(prefWidth);
				header.setPreferredWidth(prefWidth);
			}
		} else
		{
			TableColumn column = getColumnModel().getColumn(columnIndex);
			TableColumn header = getTableHeader().getColumnModel().getColumn(columnIndex);
			column.setMaxWidth(0);
			column.setMinWidth(0);
			header.setMaxWidth(0);
			header.setMinWidth(0);
		}
	}

	public void setColumnWidth(int columnIndex, Integer minWidth, Integer prefWidth, Integer maxWidth)
	{
		ColumnSize colSize = new ColumnSize(minWidth, prefWidth, maxWidth);
		setColumnSize(columnIndex, colSize);
	}

	public void setColumnsSize(ColumnSize[] columnSizeList)
	{
		if (columnSizeList == null)
		{
			return;
		}

		for (int i = 0; i < columnSizeList.length; i++)
		{
			ColumnSize colSize = columnSizeList[i];
			setColumnSize(i, colSize);
		}
	}

	public void setColumnMinWidth(int columnIndex, int width)
	{
		ColumnSize colSize = getColumnSize(columnIndex);
		setColumnWidth(columnIndex, new Integer(width), colSize.m_preferredWidth, colSize.m_maxWidth);
	}

	public void setColumnPreferredWidth(int columnIndex, int width)
	{
		ColumnSize colSize = getColumnSize(columnIndex);
		setColumnWidth(columnIndex, colSize.m_minWidth, new Integer(width), colSize.m_maxWidth);
	}

	public void setColumnMaxWidth(int columnIndex, int width)
	{
		ColumnSize colSize = getColumnSize(columnIndex);
		setColumnWidth(columnIndex, colSize.m_minWidth, colSize.m_preferredWidth, new Integer(width));
	}

	public void addListSelectionListener(ListSelectionListener listener)
	{
		super.addListSelectionListener(listener);
	}

	public void setSelectedRow(int index)
	{
		super.setSelectedRow(index);
	}

	public void setSelectedRows(int[] indices)
	{
		super.setSelectedRows(indices);
	}
}
