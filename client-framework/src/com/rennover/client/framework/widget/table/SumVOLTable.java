package com.rennover.client.framework.widget.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.TableModelEvent;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SumVOLTable extends ValueObjectListTable
{
	Object[] m_columnSum;

	/**
	 * @param valueObjectListModel
	 * @param rowModel
	 */
	public SumVOLTable(ValueObjectListModel valueObjectListModel, TableRowModel rowModel)
	{
		super(valueObjectListModel, rowModel);
	}

	/**
	 * @param valueObjectListModel
	 * @param propertylist
	 * @param columnlist
	 */
	public SumVOLTable(ValueObjectListModel valueObjectListModel, List propertylist, List columnlist)
	{
		super(valueObjectListModel, propertylist, columnlist);
	}

	/**
	 * @param valueObjectListModel
	 * @param propertylist
	 * @param columnlist
	 * @param widthList
	 */
	public SumVOLTable(ValueObjectListModel valueObjectListModel, List propertylist, List columnlist, List widthList)
	{
		super(valueObjectListModel, propertylist, columnlist, widthList);
	}

	/**
	 * @param selectedValueObjectModel
	 * @param valueObjectListModel
	 * @param rowModel
	 */
	public SumVOLTable(ValueObjectModel selectedValueObjectModel, ValueObjectListModel valueObjectListModel,
	        TableRowModel rowModel)
	{
		super(selectedValueObjectModel, valueObjectListModel, rowModel);
	}

	/**
	 * @param selectedValueObjectModel
	 * @param valueObjectListModel
	 * @param propertylist
	 * @param columnlist
	 */
	public SumVOLTable(ValueObjectModel selectedValueObjectModel, ValueObjectListModel valueObjectListModel,
	        List propertylist, List columnlist)
	{
		super(selectedValueObjectModel, valueObjectListModel, propertylist, columnlist);
	}

	/**
	 * @param selectedValueObjectModel
	 * @param valueObjectListModel
	 * @param rowModel
	 */
	public SumVOLTable(ValueObjectListModel selectedValueObjectListModel, ValueObjectListModel valueObjectListModel,
	        TableRowModel rowModel)
	{
		super(selectedValueObjectListModel, valueObjectListModel, rowModel);
	}

	/**
	 * @param selectedValueObjectModel
	 * @param valueObjectListModel
	 * @param propertylist
	 * @param columnlist
	 */
	public SumVOLTable(ValueObjectListModel selectedValueObjectListModel, ValueObjectListModel valueObjectListModel,
	        List propertylist, List columnlist)
	{
		super(selectedValueObjectListModel, valueObjectListModel, propertylist, columnlist);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTable#getRowCount()
	 */
	public int getRowCount()
	{
		return getModel().getRowCount() + 1;
	}

	public boolean isLastRow(int row)
	{
		return row == (getRowCount() - 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.widget.table.SortedTable#getValueAt(int,
	 *      int)
	 */
	public Object getValueAt(int row, int column)
	{
		if (isLastRow(row))
		{
			column = convertColumnIndexToModel(column);

			if (column == 0)
			{
				return "Total";
			} else
			{
				return getColumnSum(column);
			}
		}

		return super.getValueAt(row, column);
	}

	public Class getColumnClass(int column)
	{
		return super.getColumnClass(column);
	}

	public boolean isCellEditable(int row, int column)
	{
		if (isLastRow(row))
		{
			return false;
		}

		return super.isCellEditable(row, column);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.widget.table.SortedTable#setValueAt(java.lang.Object,
	 *      int, int)
	 */
	public void setValueAt(Object aValue, int row, int column)
	{
		super.setValueAt(aValue, row, column);
		updateColumnSum(column);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.widget.table.ValueObjectListTable#getValueObjectAt(int)
	 */
	public ValueObject getValueObjectAt(int row)
	{
		if (isLastRow(row))
		{
			return null;
		}
		return super.getValueObjectAt(row);
	}

	public void tableChanged(TableModelEvent e)
	{
		if (e.getType() == TableModelEvent.UPDATE)
		{
			if (!isLastRow(e.getFirstRow()))
			{
				updateAllColumnSum();
			}
		}
		super.tableChanged(e);
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
			int rowIndex = selectedRowIndexes[i];
			if (!isLastRow(rowIndex))
			{
				selectedValueObjectList.add(model.getValueObjectAt(rowIndex));
			}
		}

		return selectedValueObjectList;
	}

	/**
	 * @param column
	 * @return
	 */
	protected Object getColumnSum(int column)
	{
		if (m_columnSum == null)
		{
			m_columnSum = new Object[getColumnCount()];
			updateAllColumnSum();
		}
		return m_columnSum[column];
	}

	protected void setColumnSum(int column, Object value)
	{
		if (m_columnSum == null)
		{
			m_columnSum = new Object[getColumnCount()];
		}
		m_columnSum[column] = value;
	}

	private void updateAllColumnSum()
	{
		for (int i = 0; i < getColumnCount(); i++)
		{
			updateColumnSum(i);
		}
	}

	private Object updateColumnSum(int column)
	{
		double sum = computeColumnSum(column);
		Class numberClass = getColumnClass(column);

		Object value = null;
		if (Number.class.isAssignableFrom(numberClass))
		{
			value = convertNumber(numberClass, new Double(sum));
		}

		setColumnSum(column, value);
		tableChanged(new TableModelEvent(getModel(), getRowCount() - 1));

		return value;
	}

	public static Number convertNumber(Class numberClass, Number value)
	{
		Number result = value;
		if (Integer.class.isAssignableFrom(numberClass))
		{
			result = new Integer(value.intValue());
		} else if (Short.class.isAssignableFrom(numberClass))
		{
			result = new Short(value.shortValue());
		} else if (Long.class.isAssignableFrom(numberClass))
		{
			result = new Long(value.longValue());
		} else if (Double.class.isAssignableFrom(numberClass))
		{
			result = new Double(value.doubleValue());
		} else if (Float.class.isAssignableFrom(numberClass))
		{
			result = new Float(value.floatValue());
		}
		return result;
	}

	/**
	 * @param column
	 */
	private double computeColumnSum(int column)
	{
		double sum = 0;

		for (int i = 0; i < getModel().getRowCount(); i++)
		{
			Object value = getModel().getValueAt(i, column);
			if (value instanceof Number && value != null)
			{
				sum += ((Number) value).doubleValue();
			}
		}

		return sum;
	}
}