package com.rennover.client.framework.valueobject.widget;

import java.util.List;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.SelectionListField;

/**
 * @author tcaboste
 * 
 * Cette classe est un composant liste interfacé avec une propriété d'un
 * valueObject. La valeur de la propriété est l'ensemble des éléments
 * sélectionnés dans la liste.
 */
public class VOPropertySelectionList extends SelectionListField implements VOPropertyField
{
	private VOPropertySelectionListEventHandler m_propertyEventHandler = new VOPropertySelectionListEventHandler(this);

	/**
	 * Constructor for VOPropertySelectionList.
	 */
	public VOPropertySelectionList()
	{
		super();
	}

	/**
	 * Constructor for VOPropertySelectionList.
	 * 
	 * @param objectList
	 */
	public VOPropertySelectionList(List objectList)
	{
		super(objectList);
	}

	public VOPropertySelectionList(ValueModel valueModel)
	{
		this();
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertySelectionList(ValueObjectModel valueObjectModel, String propertyName)
	{
		this();
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertySelectionList(ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
		this();
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public VOPropertySelectionList(ValueModel valueModel, List objectList)
	{
		this(objectList);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertySelectionList(ValueObjectModel valueObjectModel, String propertyName, List objectList)
	{
		this(objectList);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertySelectionList(ValueObjectModel valueObjectModel, PropertyPath propertyPath, List objectList)
	{
		this(objectList);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public String getPropertyName()
	{
		return m_propertyEventHandler.getPropertyName();
	}

	public void setPropertyName(String propertyName)
	{
		m_propertyEventHandler.setPropertyName(propertyName);
	}

	public void disconnectFromModel()
	{
		m_propertyEventHandler.disconnect();
	}
}