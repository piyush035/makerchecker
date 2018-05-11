package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.CheckBoxField;

public class VOPropertyCheckBox extends CheckBoxField implements VOPropertyField
{
	private VOPropertyCheckBoxEventHandler m_propertyEventHandler = new VOPropertyCheckBoxEventHandler(this);

	// ---
	public VOPropertyCheckBox(ValueModel valueModel)
	{
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyCheckBox(ValueObjectModel valueObjectModel, String propertyName)
	{
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyCheckBox(ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	// ---
	public VOPropertyCheckBox(ValueModel valueModel, String label)
	{
		super(label);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyCheckBox(ValueObjectModel valueObjectModel, String propertyName, String label)
	{
		super(label);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyCheckBox(ValueObjectModel valueObjectModel, PropertyPath propertyPath, String label)
	{
		super(label);
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