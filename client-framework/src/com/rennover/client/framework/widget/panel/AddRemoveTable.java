package com.rennover.client.framework.widget.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.widget.table.TableRowModel;
import com.rennover.client.framework.widget.table.ValueObjectListTable;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author sanjeevkr
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddRemoveTable implements AddRemoveList
{
	private ValueObjectListTable m_table;

	private ValueObjectListModel m_valueObjectListModel;

	public AddRemoveTable(Class objectClass, TableRowModel rowModel)
	{
		m_valueObjectListModel = new ValueObjectListModel(objectClass);
		m_table = new ValueObjectListTable(m_valueObjectListModel, rowModel);
		m_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

	public AddRemoveTable(Class objectClass, TableRowModel rowModel, boolean isManualySorted)
	{
		m_valueObjectListModel = new ValueObjectListModel(objectClass);
		m_table = new ValueObjectListTable(m_valueObjectListModel, rowModel);
		m_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		m_table.setSortable(!isManualySorted);
	}

	public void addListSelectionListener(ListSelectionListener listener)
	{
		m_table.addListSelectionListener(listener);
	}

	public int getSelectedRow()
	{
		return m_table.getSelectedRow();
	}

	public int getRowCount()
	{
		return m_table.getRowCount();
	}

	public void sort(int columnIndex, boolean ascendingSort)
	{
		m_table.sort(columnIndex, ascendingSort);
	}

	public void setSortable(boolean sortable)
	{
		m_table.setSortable(sortable);
	}

	public boolean isManualySorted()
	{
		return !m_table.isSortable();
	}

	public List getList()
	{
		return m_valueObjectListModel.getValueObjectList();
	}

	// removed commented method getModel()

	public List getSelectedElements()
	{
		return m_table.getSelectedValueObjectList();
	}

	public void moveSelectedUp()
	{
		ValueObject vo = m_table.getSelectedValueObject();
		m_valueObjectListModel.moveValueObject(vo, true);
		m_table.setSelectedValueObject(vo);
	}

	public void moveSelectedDown()
	{
		ValueObject vo = m_table.getSelectedValueObject();
		m_valueObjectListModel.moveValueObject(vo, false);
		m_table.setSelectedValueObject(vo);
	}

	public JComponent getComponent()
	{
		return m_table;
	}

	public boolean isSelectionEmpty()
	{
		return m_table.getSelectedRowCount() == 0;
	}

	public void addElements(List elements)
	{
		if (elements == null)
		{
			return;
		}

		List elementList = new ArrayList(elements);
		for (int i = 0; i < elementList.size(); i++)
		{
			ValueObject element = (ValueObject) elementList.get(i);
			m_valueObjectListModel.addValueObject(element);
		}

		m_table.setSelectedValueObjectList(elements);
	}

	public void removeElements(List elements)
	{
		if (elements == null)
		{
			return;
		}

		List elementList = new ArrayList(elements);
		for (int i = 0; i < elementList.size(); i++)
		{
			ValueObject element = (ValueObject) elementList.get(i);
			m_valueObjectListModel.removeValueObject(element);
		}

		m_table.setSelectedRow(-1);
	}

	public void setDoubleClickAction(Action action)
	{
		m_table.setDoubleClickAction(action);
	}

	public ValueObjectListModel getValueObjectListModel()
	{
		return m_valueObjectListModel;
	}

	// ME-1219 : Added the following code.
	public AddRemoveTable(ValueObjectListModel tableModel, ValueObjectListTable table)
	{
		// m_valueObjectListModel = new ValueObjectListModel(objectClass);
		m_valueObjectListModel = tableModel;
		// m_table = new ValueObjectListTable(m_valueObjectListModel, rowModel);
		m_table = table;
		m_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	}

	public AddRemoveTable(Class objectClass, TableRowModel rowModel, boolean isManualySorted, boolean freezeTableHeader)
	{
		m_valueObjectListModel = new ValueObjectListModel(objectClass);
		m_table = new ValueObjectListTable(m_valueObjectListModel, rowModel);
		m_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		m_table.setSortable(!isManualySorted);
		m_table.getTableHeader().setReorderingAllowed(false);
		m_table.getTableHeader().setResizingAllowed(false);
	}

	// ME-1219 : Ends.

}
