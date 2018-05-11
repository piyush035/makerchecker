package com.rennover.client.framework.valueobject.model;

import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ValueObjectModelEvent
{
	private Object m_sender;

	private ValueObjectModel m_valueObjectModel;

	private ValueObject m_valueObject;

	private String m_propertyName;

	private Object m_propertyValue;

	private boolean m_propertyValueIsValid = true;

	/**
	 * Constructor ValueObjectModelEvent.
	 * 
	 * @param valueObjectModel
	 * @param valueObject
	 */
	public ValueObjectModelEvent(Object sender, ValueObjectModel valueObjectModel, ValueObject valueObject)
	{
		m_sender = sender;
		m_valueObjectModel = valueObjectModel;
		m_valueObject = valueObject;
	}

	/**
	 * Constructor ValueObjectModelEvent.
	 * 
	 * @param valueObjectModel
	 * @param valueObject
	 * @param propertyName
	 */
	public ValueObjectModelEvent(Object sender, ValueObjectModel valueObjectModel, ValueObject valueObject,
	        String propertyName, Object propertyValue, boolean valid)
	{
		m_sender = sender;
		m_valueObjectModel = valueObjectModel;
		m_valueObject = valueObject;
		m_propertyName = propertyName;
		m_propertyValue = propertyValue;
		m_propertyValueIsValid = valid;
	}

	public ValueObjectModel getValueObjectModel()
	{
		return m_valueObjectModel;
	}

	public ValueObject getValueObject()
	{
		return m_valueObject;
	}

	public String getPropertyName()
	{
		return m_propertyName;
	}

	public Object getPropertyValue()
	{
		return m_propertyValue;
	}

	public boolean isPropertyValid()
	{
		return m_propertyValueIsValid;
	}

	/**
	 * @return
	 */
	public Object getSender()
	{
		return m_sender;
	}
}