package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.DateField;

/**
 * @author tcaboste
 * 
 * Cette classe gère la saisie d'une date
 * 
 */
public class VOPropertyDateField extends DateField implements VOPropertyField
{
	private VOPropertyFormattedTextFieldEventHandler m_propertyEventHandler = new VOPropertyFormattedTextFieldEventHandler(
	        this);

	public VOPropertyDateField()
	{
	}

	// ---

	/**
	 * 
	 * @param valueModel
	 *            model of the value
	 */
	public VOPropertyDateField(ValueModel valueModel)
	{
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	/**
	 * 
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyName
	 *            name of the property managed by the widget
	 */
	public VOPropertyDateField(ValueObjectModel valueObjectModel, String propertyName)
	{
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	/**
	 * 
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyPath
	 *            pathname of the property managed by the widget
	 */
	public VOPropertyDateField(ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
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
		ValueObjectModel vom = m_propertyEventHandler.getValueObjectModel();
		if (vom != null)
		{
			String propertyName = getPropertyName();
			propertyValidity = m_propertyEventHandler.getValueObjectModel().isValueObjectPropertyValid(propertyName);
		}
		super.setValidField(valid && propertyValidity);
	}

	public void disconnectFromModel()
	{
		m_propertyEventHandler.disconnect();
	}
}