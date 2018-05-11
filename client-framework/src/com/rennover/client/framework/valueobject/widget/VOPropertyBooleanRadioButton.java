package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.BooleanRadioButtonField;

public class VOPropertyBooleanRadioButton extends BooleanRadioButtonField implements VOPropertyField
{
	private VOPropertyRadioButtonEventHandler m_propertyEventHandler = new VOPropertyRadioButtonEventHandler(this);

	// ---
	public VOPropertyBooleanRadioButton(ValueModel valueModel)
	{
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyBooleanRadioButton(ValueObjectModel valueObjectModel, String propertyName)
	{
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyBooleanRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
		this(valueObjectModel, propertyPath.toString());
	}

	// ---
	public VOPropertyBooleanRadioButton(ValueModel valueModel, String nullValueString)
	{
		super(nullValueString);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyBooleanRadioButton(ValueObjectModel valueObjectModel, String propertyName, String nullValueString)
	{
		super(nullValueString);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyBooleanRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath,
	        String nullValueString)
	{
		this(valueObjectModel, propertyPath.toString(), nullValueString);
	}

	// ---
	public VOPropertyBooleanRadioButton(ValueModel valueModel, String trueLabel, String falseLabel,
	        String nullValueString)
	{
		super(trueLabel, falseLabel, nullValueString);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyBooleanRadioButton(ValueObjectModel valueObjectModel, String propertyName, String trueLabel,
	        String falseLabel, String nullValueString)
	{
		super(trueLabel, falseLabel, nullValueString);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	/**
	 * * modified by Ivega Offshore - to give an option to the user to set the
	 * orientation fix for defect #2450
	 * 
	 * @param valueObjectModel
	 * @param propertyName
	 * @param trueLabel
	 * @param falseLabel
	 * @param nullValueString
	 * @param orientation
	 */
	public VOPropertyBooleanRadioButton(ValueObjectModel valueObjectModel, String propertyName, String trueLabel,
	        String falseLabel, String nullValueString, int orientation)
	{
		super(trueLabel, falseLabel, nullValueString, orientation);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyBooleanRadioButton(ValueObjectModel valueObjectModel, PropertyPath propertyPath, String trueLabel,
	        String falseLabel, String nullValueString)
	{
		this(valueObjectModel, propertyPath.toString(), trueLabel, falseLabel, nullValueString);
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

	public void disconnectFromModel()
	{
		m_propertyEventHandler.disconnect();
	}
}