package com.rennover.client.framework.valueobject.model;

import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ValueObjectListModelEvent
{
	private int m_index = -1;

	private ValueObjectListModel m_valueObjectListModel;

	private ValueObject m_valueObject;

	/**
	 * Constructor ValueObjectModelEvent.
	 * 
	 * @param valueObjectModel
	 * @param valueObject
	 */
	public ValueObjectListModelEvent(ValueObjectListModel valueObjectListModel)
	{
		m_valueObjectListModel = valueObjectListModel;
		m_valueObject = null;
	}

	/**
	 * Constructor ValueObjectModelEvent.
	 */
	public ValueObjectListModelEvent(ValueObjectListModel valueObjectListModel, ValueObject valueObject, int index)
	{
		m_valueObjectListModel = valueObjectListModel;
		m_valueObject = valueObject;
		m_index = index;
	}

	public ValueObjectListModel getValueObjectListModel()
	{
		return m_valueObjectListModel;
	}

	public ValueObject getValueObject()
	{
		return m_valueObject;
	}

	/**
	 * Returns the index.
	 * 
	 * @return int
	 */
	public int getIndex()
	{
		return m_index;
	}
}