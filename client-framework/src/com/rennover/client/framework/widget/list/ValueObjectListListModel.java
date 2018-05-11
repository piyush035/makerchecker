package com.rennover.client.framework.widget.list;

import javax.swing.AbstractListModel;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.valueobject.model.ValueObjectListModelEvent;
import com.rennover.client.framework.valueobject.model.ValueObjectListModelListener;
import com.rennover.hotel.common.valueobject.ValueObject;

public class ValueObjectListListModel extends AbstractListModel implements ValueObjectListModelListener
{
	private ValueObjectListModel m_model;

	public ValueObjectListListModel(ValueObjectListModel valueObjectListModel)
	{
		m_model = valueObjectListModel;
		m_model.addValueObjectListModelListener(this);
	}

	public int getSize()
	{
		return m_model.getValueObjectCount();
	}

	public Object getElementAt(int index)
	{
		ValueObject valueObject = m_model.getValueObject(index);
		return valueObject;
	}

	public void valueObjectListChanged(ValueObjectListModelEvent event)
	{
		fireContentsChanged(this, event.getIndex(), event.getIndex());
	}

	public void valueObjectAdded(ValueObjectListModelEvent event)
	{
		fireIntervalAdded(this, event.getIndex(), event.getIndex());
	}

	public void valueObjectRemoved(ValueObjectListModelEvent event)
	{
		fireIntervalRemoved(this, event.getIndex(), event.getIndex());
	}

	public void valueObjectUpdated(ValueObjectListModelEvent event)
	{
		fireContentsChanged(this, event.getIndex(), event.getIndex());
	}

	public ValueObject getValueObjectAt(int rowIndex)
	{
		ValueObject valueObject = m_model.getValueObject(rowIndex);
		return valueObject;
	}
}