package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.InputTextField;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class VOPropertyTextField extends InputTextField implements VOPropertyField
{
	private VOPropertyTextFieldEventHandler m_propertyEventHandler = new VOPropertyTextFieldEventHandler(this);

	public VOPropertyTextField()
	{
	}

	public VOPropertyTextField(ValueModel valueModel)
	{
		setValueModel(valueModel);
	}

	public VOPropertyTextField(ValueObjectModel valueObjectModel, String propertyName)
	{
		setValueObjectModel(valueObjectModel, propertyName);
	}

	public VOPropertyTextField(ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
		setValueObjectModel(valueObjectModel, propertyPath.toString());
	}

	public VOPropertyTextField(ValueModel valueModel, boolean editable)
	{
		setValueModel(valueModel);
		setEditable(editable);
	}

	public VOPropertyTextField(ValueObjectModel valueObjectModel, String propertyName, boolean editable)
	{
		setValueObjectModel(valueObjectModel, propertyName);
		setEditable(editable);
	}

	public VOPropertyTextField(ValueObjectModel valueObjectModel, PropertyPath propertyPath, boolean editable)
	{
		setValueObjectModel(valueObjectModel, propertyPath.toString());
		setEditable(editable);
	}

	public VOPropertyTextField(ValueModel valueModel, boolean editable, boolean upperCase)
	{
		setValueModel(valueModel);
		setEditable(editable);
		setUseUpperCase(upperCase);
	}

	public VOPropertyTextField(ValueObjectModel valueObjectModel, String propertyName, boolean editable,
	        boolean upperCase)
	{
		setValueObjectModel(valueObjectModel, propertyName);
		setEditable(editable);
		setUseUpperCase(upperCase);
	}

	public VOPropertyTextField(ValueObjectModel valueObjectModel, PropertyPath propertyPath, boolean editable,
	        boolean upperCase)
	{
		setValueObjectModel(valueObjectModel, propertyPath.toString());
		setEditable(editable);
		setUseUpperCase(upperCase);
	}

	public void setValueModel(ValueModel valueModel)
	{
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public void setValueObjectModel(ValueObjectModel valueObjectModel, String propertyName)
	{
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public String getPropertyName()
	{
		return m_propertyEventHandler.getPropertyName();
	}

	public void setPropertyName(String propertyName)
	{
		m_propertyEventHandler.setPropertyName(propertyName);
	}

	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
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