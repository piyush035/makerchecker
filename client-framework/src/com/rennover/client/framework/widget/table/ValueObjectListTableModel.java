package com.rennover.client.framework.widget.table;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.valueobject.model.ValueObjectListModelEvent;
import com.rennover.client.framework.valueobject.model.ValueObjectListModelListener;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @deprecate use ValueObjectListTable(ValueObjectListModel
 *            valueObjectListModel, List propertyNameList, List columnNameList)
 */
public class ValueObjectListTableModel extends AbstractTableModel implements ValueObjectListModelListener
{
	private TableRowModel m_rowModel;

	private ValueObjectListModel m_model;

	public ValueObjectListTableModel(ValueObjectListModel valueObjectListModel, List propertyNameList,
	        List columnNameList)
	{
		m_model = valueObjectListModel;
		m_model.addValueObjectListModelListener(this);
		Class valueObjectClass = m_model.getValueObjectClass();
		m_rowModel = new PropertyTableRowModel(valueObjectClass, (String[]) columnNameList.toArray(new String[0]),
		        (String[]) propertyNameList.toArray(new String[0]));
	}

	public ValueObjectListTableModel(ValueObjectListModel valueObjectListModel, TableRowModel rowModel)
	{
		m_model = valueObjectListModel;
		m_model.addValueObjectListModelListener(this);
		m_rowModel = rowModel;
	}

	public void disconnectFromModel()
	{
		m_model.removeValueObjectListModelListener(this);
	}

	public Class getColumnClass(int column)
	{
		return m_rowModel.getColumnTypes()[column];
	}

	public String getColumnName(int column)
	{
		return m_rowModel.getTitles()[column];
	}

	public int getColumnCount()
	{
		return m_rowModel.getTitles().length;
	}

	public int getRowCount()
	{
		return m_model.getValueObjectCount();
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return m_rowModel.getValueAt(columnIndex, m_model.getValueObject(rowIndex));
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		m_rowModel.setValueAt(columnIndex, m_model.getValueObject(rowIndex), aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public void valueObjectListChanged(ValueObjectListModelEvent event)
	{
		TableModelEvent e = new TableModelEvent(this);
		fireTableChanged(e);
	}

	public void valueObjectAdded(ValueObjectListModelEvent event)
	{
		fireTableRowsInserted(event.getIndex(), event.getIndex());
	}

	public void valueObjectRemoved(ValueObjectListModelEvent event)
	{
		fireTableRowsDeleted(event.getIndex(), event.getIndex());
	}

	public void valueObjectUpdated(ValueObjectListModelEvent event)
	{
		fireTableRowsUpdated(event.getIndex(), event.getIndex());
	}

	public ValueObject getValueObjectAt(int rowIndex)
	{
		ValueObject valueObject = m_model.getValueObject(rowIndex);
		return valueObject;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return m_rowModel.isCellEditable(columnIndex, getValueObjectAt(rowIndex));
	}
}