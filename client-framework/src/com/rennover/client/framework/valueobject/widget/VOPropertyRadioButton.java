package com.rennover.client.framework.valueobject.widget;

import java.util.List;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.ValueObjectRadioButtonField;

public class VOPropertyRadioButton extends ValueObjectRadioButtonField implements VOPropertyField
{
	private VOPropertyRadioButtonEventHandler m_propertyEventHandler = new VOPropertyRadioButtonEventHandler(this);

	public VOPropertyRadioButton(ValueModel valueModel, int orientation)
	{
		super(orientation);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, String propertyName, int orientation)
	{
		super(orientation);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath, int orientation)
	{
		super(orientation);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public VOPropertyRadioButton(ValueModel valueModel)
	{
		super(VERTICAL);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, String propertyName)
	{
		super(VERTICAL);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
		super(VERTICAL);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public VOPropertyRadioButton(ValueModel valueModel, boolean useObjectIdMode)
	{
		this(valueModel, VERTICAL);
		setObjectIdMode(useObjectIdMode);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, String propertyName, boolean useObjectIdMode)
	{
		this(valueObjectModel, propertyName, VERTICAL);
		setObjectIdMode(useObjectIdMode);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath, boolean useObjectIdMode)
	{
		this(valueObjectModel, propertyPath.toString(), VERTICAL);
		setObjectIdMode(useObjectIdMode);
	}

	public VOPropertyRadioButton(ValueModel valueModel, int orientation, List objectList)
	{
		super(orientation, objectList);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, String propertyName, int orientation,
	        List objectList)
	{
		super(orientation, objectList);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath, int orientation,
	        List objectList)
	{
		super(orientation, objectList);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public VOPropertyRadioButton(ValueModel valueModel, List objectList)
	{
		this(valueModel, VERTICAL, objectList);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, String propertyName, List objectList)
	{
		this(valueObjectModel, propertyName, VERTICAL, objectList);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath, List objectList)
	{
		this(valueObjectModel, propertyPath.toString(), VERTICAL, objectList);
	}

	public VOPropertyRadioButton(ValueModel valueModel, List objectList, boolean useObjectIdMode)
	{
		this(valueModel, VERTICAL, objectList);
		setObjectIdMode(useObjectIdMode);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, String propertyName, List objectList,
	        boolean useObjectIdMode)
	{
		this(valueObjectModel, propertyName, VERTICAL, objectList);
		setObjectIdMode(useObjectIdMode);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath, List objectList,
	        boolean useObjectIdMode)
	{
		this(valueObjectModel, propertyPath.toString(), VERTICAL, objectList, useObjectIdMode);
	}

	public VOPropertyRadioButton(ValueModel valueModel, int orientation, boolean useObjectIdMode)
	{
		super(orientation);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, String propertyName, int orientation,
	        boolean useObjectIdMode)
	{
		super(orientation);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath, int orientation,
	        boolean useObjectIdMode)
	{
		super(orientation);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public VOPropertyRadioButton(ValueModel valueModel, int orientation, List objectList, boolean useObjectIdMode)
	{
		super(orientation, objectList);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, String propertyName, int orientation,
	        List objectList, boolean useObjectIdMode)
	{
		super(orientation, objectList);
		setObjectIdMode(useObjectIdMode);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath, int orientation,
	        List objectList, boolean useObjectIdMode)
	{
		super(orientation, objectList);
		setObjectIdMode(useObjectIdMode);
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