package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.ListField;

/**
 * 
 * @author tcaboste
 * 
 * Cette classe est un composant liste interfac� avec une propri�t� d'un
 * valueObject. La valeur de la propri�t� est l'ensemble des �l�ments contenus
 * dans la liste.
 */
public class VOPropertyList extends ListField implements VOPropertyField
{
	private VOPropertyListEventHandler m_propertyEventHandler = new VOPropertyListEventHandler(this);

	public VOPropertyList()
	{
		super();
	}

	// ---
	public VOPropertyList(ValueModel valueModel)
	{
		this();
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyList(ValueObjectModel valueObjectModel, String propertyName)
	{
		this();
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyList(ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
		this();
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	// ---
	public String getPropertyName()
	{
		return m_propertyEventHandler.getPropertyName();
	}

	public void setPropertyName(String propertyName)
	{
		m_propertyEventHandler.setPropertyName(propertyName);
	}

	public void setValidField(boolean valid)
	{
		boolean propertyValidity = true;
		if (m_propertyEventHandler != null)
		{
			ValueObjectModel vom = m_propertyEventHandler.getValueObjectModel();
			if (vom != null)
			{
				propertyValidity = m_propertyEventHandler.getValueObjectModel().isValueObjectPropertyValid(
				        getPropertyName());
			}
		}
		super.setValidField(valid && propertyValidity);
	}

	public void disconnectFromModel()
	{
		m_propertyEventHandler.disconnect();
	}
}