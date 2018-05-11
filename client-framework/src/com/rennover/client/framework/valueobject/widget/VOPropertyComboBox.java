package com.rennover.client.framework.valueobject.widget;

import java.util.List;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.ValueObjectComboBoxField;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class VOPropertyComboBox extends ValueObjectComboBoxField implements VOPropertyField
{
	private VOPropertyComboBoxEventHandler m_propertyEventHandler = new VOPropertyComboBoxEventHandler(this);

	public VOPropertyComboBox()
	{
		super();
	}

	// ---
	public VOPropertyComboBox(ValueModel valueModel)
	{
		this();
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, String propertyName)
	{
		this();
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
		this();
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	// ---
	public VOPropertyComboBox(ValueModel valueModel, boolean useObjectIdMode)
	{
		this();
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, String propertyName, boolean useObjectIdMode)
	{
		this();
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, PropertyPath propertyPath, boolean useObjectIdMode)
	{
		this();
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	// ---
	public VOPropertyComboBox(ValueModel valueModel, List objectList)
	{
		super(objectList);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, String propertyName, List objectList)
	{
		super(objectList);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, PropertyPath propertyPath, List objectList)
	{
		super(objectList);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	// ---
	public VOPropertyComboBox(ValueModel valueModel, List objectList, boolean useObjectIdMode)
	{
		super(objectList);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, String propertyName, List objectList,
	        boolean useObjectIdMode)
	{
		super(objectList);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, PropertyPath propertyPath, List objectList,
	        boolean useObjectIdMode)
	{
		super(objectList);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	// ---
	public VOPropertyComboBox(ValueModel valueModel, ValueObjectListModel valueObjectListModel)
	{
		super(valueObjectListModel);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, String propertyName,
	        ValueObjectListModel valueObjectListModel)
	{
		super(valueObjectListModel);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, PropertyPath propertyPath,
	        ValueObjectListModel valueObjectListModel)
	{
		super(valueObjectListModel);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	// ---
	public VOPropertyComboBox(ValueModel valueModel, ValueObjectListModel valueObjectListModel, boolean useObjectIdMode)
	{
		super(valueObjectListModel);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, String propertyName,
	        ValueObjectListModel valueObjectListModel, boolean useObjectIdMode)
	{
		super(valueObjectListModel);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyComboBox(ValueObjectModel valueObjectModel, PropertyPath propertyPath,
	        ValueObjectListModel valueObjectListModel, boolean useObjectIdMode)
	{
		super(valueObjectListModel);
		setObjectIdMode(useObjectIdMode);
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

	public void setValue(Object value)
	{
		super.setValue(value);
		boolean nullUnavailable = (value == null) && (getValue() != value);
		if (nullUnavailable)
		{
			m_propertyEventHandler.notifyValueObjectModel();
			// BUG: cet appel direct à updateValueObjectModel ne gère pas
			// les set sans notification
			m_propertyEventHandler.updateValueObjectModel();
		}
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