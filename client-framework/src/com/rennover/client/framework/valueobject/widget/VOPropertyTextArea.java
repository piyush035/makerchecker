package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.TextAreaField;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class VOPropertyTextArea extends TextAreaField implements VOPropertyField
{
	private VOPropertyTextFieldEventHandler m_propertyEventHandler = new VOPropertyTextFieldEventHandler(this);

	public VOPropertyTextArea()
	{
	}

	public VOPropertyTextArea(ValueModel valueModel)
	{
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyTextArea(ValueObjectModel valueObjectModel, String propertyName)
	{
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyTextArea(ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
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

}