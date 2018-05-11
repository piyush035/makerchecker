
package com.rennover.client.framework.widget.combobox;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.valueobject.model.ValueObjectListModelEvent;
import com.rennover.client.framework.valueobject.model.ValueObjectListModelListener;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ValueObjectListComboBoxModel extends DefaultAbstractComboBoxModel implements ValueObjectListModelListener,
        SortableComboBoxModel
{
	/**
	 * Constructor for ValueObjectListComboBoxModel.
	 */
	private ValueObjectListModel m_model;

	public ValueObjectListComboBoxModel(ValueObjectListModel valueObjectListModel)
	{
		super();
		m_model = valueObjectListModel;
		m_model.addValueObjectListModelListener(this);
	}

	public ValueObjectListComboBoxModel(ValueObjectListModel valueObjectListModel, Object nullValue)
	{
		super(nullValue);
		m_model = valueObjectListModel;
		m_model.addValueObjectListModelListener(this);
	}

	public ValueObjectListComboBoxModel(ValueObjectListModel valueObjectListModel, boolean useNullValue)
	{
		super(useNullValue);
		m_model = valueObjectListModel;
		m_model.addValueObjectListModelListener(this);
	}

	public void sort()
	{
		m_model.sort();
	}

	public int getSize()
	{
		int nbItems = (m_model == null) ? 0 : m_model.getValueObjectCount();
		return getUseNullValue() ? (nbItems + 1) : nbItems;
	}

	public Object getElementAt(int index)
	{
		// NB: la valeur nulle a toujours l'index 0, lorsqu'elle est active
		if ((index == 0) && getUseNullValue())
		{
			return getNullValue();
		}

		index = getUseNullValue() ? (index - 1) : index;

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
