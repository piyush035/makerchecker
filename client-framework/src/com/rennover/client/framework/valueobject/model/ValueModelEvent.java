package com.rennover.client.framework.valueobject.model;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ValueModelEvent
{
	private Object m_sender;

	private ValueModel m_valueModel;

	private Object m_value;

	/**
	 * @param event
	 */
	public ValueModelEvent(ValueObjectModelEvent event)
	{
		m_sender = event.getSender();
		m_valueModel = (ValueModel) event.getValueObjectModel();
		m_value = event.getPropertyValue();
	}

	/**
	 * Constructor ValueObjectModelEvent.
	 * 
	 * @param valueObjectModel
	 * @param valueObject
	 * @param propertyName
	 */
	public ValueModelEvent(Object sender, ValueModel valueModel, Object value)
	{
		m_sender = sender;
		m_valueModel = valueModel;
		m_value = value;
	}

	/**
	 * @return
	 */
	public Object getSender()
	{
		return m_sender;
	}

	public ValueModel getValueModel()
	{
		return m_valueModel;
	}

	public Object getValue()
	{
		return m_value;
	}
}