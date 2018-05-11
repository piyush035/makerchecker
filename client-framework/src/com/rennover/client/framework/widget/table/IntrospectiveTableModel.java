package com.rennover.client.framework.widget.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.rennover.hotel.common.helper.ReflectionHelper;

/**
 * @deprecated use ValueObjectListTable and TableRowModel
 */
public class IntrospectiveTableModel extends AbstractTableModel
{
	private List m_valueList = new ArrayList();

	private List m_propertyNameList = new ArrayList();

	private List m_columnNameList = new ArrayList();

	public int getColumnCount()
	{
		return m_columnNameList.size();
	}

	public int getRowCount()
	{
		return m_valueList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return ReflectionHelper.getProperty(m_valueList.get(rowIndex), (String) m_propertyNameList.get(columnIndex));
	}

	public String getColumnName(int column)
	{
		return (String) m_columnNameList.get(column);
	}

	/**
	 * @return List
	 */
	public List getColumnNameList()
	{
		return m_columnNameList;
	}

	/**
	 * @return List
	 */
	public List getPropertyNameList()
	{
		return m_propertyNameList;
	}

	/**
	 * @return List
	 */
	public List getValueList()
	{
		return m_valueList;
	}

	/**
	 * @param columnNameList
	 */
	public void setColumnNameList(List columnNameList)
	{
		m_columnNameList = columnNameList;
		fireTableStructureChanged();
	}

	/**
	 * @param propertyNameList
	 */
	public void setPropertyNameList(List propertyNameList)
	{
		m_propertyNameList = propertyNameList;
		fireTableStructureChanged();
	}

	/**
	 * @param valueList
	 */
	public void setValueList(List valueList)
	{
		m_valueList = valueList;
		fireTableDataChanged();
	}
}