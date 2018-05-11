package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.NumericSpinnerField;

public class VOPropertyNumericSpinnerField extends NumericSpinnerField implements VOPropertyField
{
	private VOPropertyNumericSpinnerEventHandler m_propertyEventHandler = new VOPropertyNumericSpinnerEventHandler(this);

	public VOPropertyNumericSpinnerField()
	{
		super();
	}

	public VOPropertyNumericSpinnerField(ValueModel model, Class numberClass, int value, int minimum, int maximum,
	        int stepSize, int nbCaracters)
	{
		super(numberClass, value, minimum, maximum, stepSize, nbCaracters);
		m_propertyEventHandler.setValueModel(this, model);
	}

	public VOPropertyNumericSpinnerField(ValueObjectModel model, String propertyName, Class numberClass, int value,
	        int minimum, int maximum, int stepSize, int nbCaracters)
	{
		super(numberClass, value, minimum, maximum, stepSize, nbCaracters);
		m_propertyEventHandler.setValueObjectModel(this, model, propertyName);
	}

	public VOPropertyNumericSpinnerField(ValueObjectModel model, PropertyPath propertyPath, Class numberClass,
	        int value, int minimum, int maximum, int stepSize, int nbCaracters)
	{
		super(numberClass, value, minimum, maximum, stepSize, nbCaracters);
		m_propertyEventHandler.setValueObjectModel(this, model, propertyPath.toString());
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