package com.rennover.client.framework.widget.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.valueobject.model.ValueObjectListModelEvent;
import com.rennover.client.framework.valueobject.model.ValueObjectListModelListener;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModelEvent;
import com.rennover.client.framework.valueobject.model.ValueObjectModelListener;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ValueObjectListTable extends SortedTable // implements
														// ValueObjectModelListener,
														// ValueObjectListModelListener
{
	private ValueObjectModel m_selectedValueObjectModel;

	private ValueObjectListModel m_selectedValueObjectListModel;

	private SelectedValueObjectListener m_selectedValueObjectListener = new SelectedValueObjectListener();

	private boolean m_updating = false;

	public ValueObjectListTable(ValueObjectListModel valueObjectListModel, TableRowModel rowModel)
	{
		ValueObjectListTableModel model = new ValueObjectListTableModel(valueObjectListModel, rowModel);
		setModel(model);
		setColumnsSize(rowModel.getColumnsSize());
		setHeaderToolTips(rowModel.getTitles(), rowModel.getTooltips());
	}

	public ValueObjectListTable(ValueObjectModel selectedValueObjectModel, ValueObjectListModel valueObjectListModel,
	        TableRowModel rowModel)
	{
		ValueObjectListTableModel model = new ValueObjectListTableModel(valueObjectListModel, rowModel);
		setModel(model);
		setSelectedValueObjectModel(selectedValueObjectModel);
		setColumnsSize(rowModel.getColumnsSize());
		setHeaderToolTips(rowModel.getTitles(), rowModel.getTooltips());

	}

	public ValueObjectListTable(ValueObjectListModel valueObjectListModel, List propertylist, List columnlist)
	{
		this(valueObjectListModel, propertylist, columnlist, null);
	}

	public ValueObjectListTable(ValueObjectListModel valueObjectListModel, List propertylist, List columnlist,
	        List widthList)
	{
		Class valueObjectClass = valueObjectListModel.getValueObjectClass();
		TableRowModel rowModel = new PropertyTableRowModel(valueObjectClass, propertylist, columnlist, widthList);
		ValueObjectListTableModel model = new ValueObjectListTableModel(valueObjectListModel, rowModel);
		setModel(model);
		setColumnsSize(rowModel.getColumnsSize());
	}

	/**
	 * @param strings
	 */
	private void setHeaderToolTips(String[] names, String[] tooltips)
	{
		if (names != null)
		{
			int count = getColumnCount();

			for (int i = 0; i < count; i++)
			{
				TableColumn column = getColumnModel().getColumn(i);
				column.setHeaderValue(new ColumnHeader());
			}

			for (int i = 0; i < names.length; i++)
			{
				TableColumn column = getColumnModel().getColumn(i);
				ColumnHeader columnHeader = (ColumnHeader) column.getHeaderValue();
				columnHeader.m_text = names[i];
			}
		}

		if (tooltips != null)
		{
			for (int i = 0; i < tooltips.length; i++)
			{
				TableColumn column = getColumnModel().getColumn(i);
				ColumnHeader columnHeader = (ColumnHeader) column.getHeaderValue();
				columnHeader.m_toolTip = tooltips[i];
			}
		}

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

	public ValueObjectListTable(ValueObjectModel selectedValueObjectModel, ValueObjectListModel valueObjectListModel,
	        List propertylist, List columnlist)
	{
		ValueObjectListTableModel model = new ValueObjectListTableModel(valueObjectListModel, propertylist, columnlist);
		setModel(model);
		setSelectedValueObjectModel(selectedValueObjectModel);
	}

	public ValueObjectListTable(ValueObjectListModel selectedValueObjectListModel,
	        ValueObjectListModel valueObjectListModel, TableRowModel rowModel)
	{
		ValueObjectListTableModel model = new ValueObjectListTableModel(valueObjectListModel, rowModel);
		setModel(model);
		setSelectedValueObjectListModel(selectedValueObjectListModel);
		setColumnsSize(rowModel.getColumnsSize());
	}

	public ValueObjectListTable(ValueObjectListModel selectedValueObjectListModel,
	        ValueObjectListModel valueObjectListModel, List propertylist, List columnlist)
	{
		ValueObjectListTableModel model = new ValueObjectListTableModel(valueObjectListModel, propertylist, columnlist);
		setModel(model);
		setSelectedValueObjectListModel(selectedValueObjectListModel);
	}

	private void updateSelectedElementProperties()
	{
		setUpdating(true);
		try
		{
			updateRow(getSelectedRow());
		} finally
		{
			setUpdating(false);
		}
	}

	public void disconnectFromModel()
	{
		ValueObjectListTableModel model = (ValueObjectListTableModel) getModel();
		model.disconnectFromModel();
	}

	public void valueChanged(ListSelectionEvent e)
	{
		// important : ignore extra message
		if (!isUpdating() && !e.getValueIsAdjusting())
		{
			// modifier la sélection (monoselection)
			ValueObjectModel selectedValueObjectModel = getSelectedValueObjectModel();
			if (selectedValueObjectModel != null)
			{
				ValueObject vo = getSelectedValueObject();
				getSelectedValueObjectModel().setValueObject(m_selectedValueObjectListener, vo);
			}

			// modifier la sélection (multiselection)
			ValueObjectListModel selectedValueObjectListModel = getSelectedValueObjectListModel();
			if (selectedValueObjectListModel != null)
			{
				List voList = getSelectedValueObjectList();
				getSelectedValueObjectListModel().setValueObjectList(m_selectedValueObjectListener, voList);
			}
		}

		super.valueChanged(e);
	}

	public ValueObject getSelectedValueObject()
	{
		return getValueObjectAt(getSelectedRow());
	}

	public ValueObject getValueObjectAt(int row)
	{
		if (row == -1)
		{
			return null;
		}
		row = getRowIndex(row);
		ValueObjectListTableModel model = (ValueObjectListTableModel) getModel();
		return model.getValueObjectAt(row);
	}

	// removed the commented method moveSelectedValueObject(ValueObject
	// valueObject, boolean up)

	public int getIndexOf(ValueObject valueObject)
	{
		if (valueObject == null)
		{
			return -1;
		}

		for (int i = 0; i < getRowCount(); i++)
		{
			ValueObject vo = getValueObjectAt(i);
			if (vo.equals(valueObject))
			{
				return i;
			}
		}
		return -1;
	}

	public void setSelectedValueObjectList(List elements)
	{
		int[] selectedIndexes = new int[elements.size()];
		for (int i = 0; i < elements.size(); i++)
		{
			ValueObject vo = (ValueObject) elements.get(i);
			int index = getIndexOf(vo);
			selectedIndexes[i] = index;
		}

		setSelectedRows(selectedIndexes);
	}

	public void setSelectedValueObject(ValueObject valueObject)
	{
		int index = getIndexOf(valueObject);
		setSelectedRow(index);
		updateRow(index);
	}

	public List getSelectedValueObjectList()
	{
		int[] selectedRowIndexes = getModelSelectedRows();
		if (selectedRowIndexes.length == 0)
		{
			return Collections.EMPTY_LIST;
		}

		ValueObjectListTableModel model = (ValueObjectListTableModel) getModel();
		List selectedValueObjectList = new ArrayList(selectedRowIndexes.length);
		for (int i = 0; i < selectedRowIndexes.length; i++)
		{
			selectedValueObjectList.add(model.getValueObjectAt(selectedRowIndexes[i]));
		}

		return selectedValueObjectList;
	}

	public void addListSelectionListener(ListSelectionListener listener)
	{
		super.addListSelectionListener(listener);
	}

	public void setSelectionAction(Action action)
	{
		super.setSelectionAction(action);
	}

	/**
	 * Returns the selectedValueObjectModel.
	 * 
	 * @return ValueObjectModel
	 */
	public ValueObjectModel getSelectedValueObjectModel()
	{
		return m_selectedValueObjectModel;
	}

	/**
	 * Sets the selectedValueObjectModel.
	 * 
	 * @param selectedValueObjectModel
	 *            The selectedValueObjectModel to set
	 */
	public void setSelectedValueObjectModel(ValueObjectModel selectedValueObjectModel)
	{
		if (m_selectedValueObjectModel == selectedValueObjectModel)
		{
			return;
		}

		if (m_selectedValueObjectModel != null)
		{
			m_selectedValueObjectModel.removeValueObjectModelListener(m_selectedValueObjectListener);
		}

		m_selectedValueObjectModel = selectedValueObjectModel;

		if (m_selectedValueObjectModel != null)
		{
			m_selectedValueObjectModel.addValueObjectModelListener(m_selectedValueObjectListener);
			setSelectedValueObjectListModel(null);
			setSelectionMode(SINGLE_SELECTION);
		}
	}

	/**
	 * Returns the selectedValueObjectModel.
	 * 
	 * @return ValueObjectModel
	 */
	public ValueObjectListModel getSelectedValueObjectListModel()
	{
		return m_selectedValueObjectListModel;
	}

	/**
	 * Sets the selectedValueObjectModel.
	 * 
	 * @param selectedValueObjectModel
	 *            The selectedValueObjectModel to set
	 */
	public void setSelectedValueObjectListModel(ValueObjectListModel selectedValueObjectListModel)
	{
		if (m_selectedValueObjectListModel != null)
		{
			m_selectedValueObjectListModel.removeValueObjectListModelListener(m_selectedValueObjectListener);
		}

		m_selectedValueObjectListModel = selectedValueObjectListModel;

		if (m_selectedValueObjectListModel != null)
		{
			m_selectedValueObjectListModel.addValueObjectListModelListener(m_selectedValueObjectListener);
			setSelectedValueObjectModel(null);
			setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
		}
	}

	private void updateSelectedElement()
	{
		setUpdating(true);
		try
		{
			setSelectedValueObject(getSelectedValueObjectModel().getOriginalValueObject());
		} finally
		{
			setUpdating(false);
		}
	}

	private void updateSelectedElements()
	{
		setUpdating(true);
		try
		{
			setSelectedValueObjectList(getSelectedValueObjectListModel().getValueObjectList());
		} finally
		{
			setUpdating(false);
		}
	}

	/**
	 * @return
	 */
	public boolean isUpdating()
	{
		return m_updating;
	}

	/**
	 * @param b
	 */
	public void setUpdating(boolean b)
	{
		m_updating = b;
	}

	private class SelectedValueObjectListener implements ValueObjectModelListener, ValueObjectListModelListener
	{
		public void valueObjectChanged(ValueObjectModelEvent event)
		{
			if (!isUpdating())
			{
				updateSelectedElement();
			}
		}

		public void valueObjectPropertyChanged(ValueObjectModelEvent event)
		{
			if (!isUpdating())
			{
				updateSelectedElementProperties();
			}
		}

		public void valueObjectAdded(ValueObjectListModelEvent event)
		{
			if (!isUpdating())
			{
				updateSelectedElements();
			}
		}

		public void valueObjectRemoved(ValueObjectListModelEvent event)
		{
			updateSelectedElements();
		}

		public void valueObjectUpdated(ValueObjectListModelEvent event)
		{
			updateSelectedElements();
		}

		public void valueObjectListChanged(ValueObjectListModelEvent event)
		{
			updateSelectedElements();
		}
	}
}
