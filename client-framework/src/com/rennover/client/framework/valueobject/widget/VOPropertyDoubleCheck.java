package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.DoubleCheckField;

/**
 * @author tcaboste
 * 
 * //french Cette classe gère deux checkboxes. Les deux checkboxes ne peuvent
 * être cochées en même temps, mais elles peuvent être décochées en même temps.
 * Cela donne trois états possibles pour cette widget (true, false, null) : [x] [ ] =
 * true [ ] [x] = false [ ] [ ] = null
 * 
 * Le libellé des checkboxes peuvent être modifié ainsi que la valeur de la
 * widget selon son état.
 * 
 * //english This class manage two checkboxes The checkboxes can not be checked
 * in the same, but they can be unchecked in the same time. This give us 3
 * states for the widgets (true, false, null) [x] [ ] = true [ ] [x] = false [ ] [ ] =
 * null
 * 
 * Checkboxes's labels and the value for the differents state can be changed.
 * 
 */
public class VOPropertyDoubleCheck extends DoubleCheckField implements VOPropertyField
{
	private VOPropertyDoubleCheckEventHandler m_propertyEventHandler = new VOPropertyDoubleCheckEventHandler(this);

	// ---

	/**
	 * @param valueModel
	 *            model of the value
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 */
	public VOPropertyDoubleCheck(ValueModel valueModel, String labelTrue, String labelFalse)
	{
		super(labelTrue, labelFalse);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	/**
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyName
	 *            name of the property managed by the widget
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 */
	public VOPropertyDoubleCheck(ValueObjectModel valueObjectModel, String propertyName, String labelTrue,
	        String labelFalse)
	{
		super(labelTrue, labelFalse);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	/**
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyPath
	 *            pathname of the property managed by the widget
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 */
	public VOPropertyDoubleCheck(ValueObjectModel valueObjectModel, PropertyPath propertyPath, String labelTrue,
	        String labelFalse)
	{
		this(valueObjectModel, propertyPath.toString(), labelTrue, labelFalse);
	}

	// ---

	/**
	 * @param valueModel
	 *            model of the value
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 * @param orientation
	 *            orientation of the widget (VERTICAL or HORIZONTAL)
	 */
	public VOPropertyDoubleCheck(ValueModel valueModel, String labelTrue, String labelFalse, int orientation)
	{
		super(labelTrue, labelFalse, orientation);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	/**
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyName
	 *            name of the property managed by the widget
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 * @param orientation
	 *            orientation of the widget (VERTICAL or HORIZONTAL)
	 */
	public VOPropertyDoubleCheck(ValueObjectModel valueObjectModel, String propertyName, String labelTrue,
	        String labelFalse, int orientation)
	{
		super(labelTrue, labelFalse, orientation);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	/**
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyPath
	 *            pathname of the property managed by the widget
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 * @param orientation
	 *            orientation of the widget (VERTICAL or HORIZONTAL)
	 */
	public VOPropertyDoubleCheck(ValueObjectModel valueObjectModel, PropertyPath propertyPath, String labelTrue,
	        String labelFalse, int orientation)
	{
		this(valueObjectModel, propertyPath.toString(), labelTrue, labelFalse, orientation);
	}

	// ---

	/**
	 * @param valueModel
	 *            model of the value
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 * @param valueTrue
	 *            value when the true checkbox is checked
	 * @param valueFalse
	 *            value when the false checkbox is checked
	 * @param valueNull
	 *            value when no checkboxes are checked
	 */
	public VOPropertyDoubleCheck(ValueModel valueModel, String labelTrue, String labelFalse, Object valueTrue,
	        Object valueFalse, Object valueNull)
	{
		super(labelTrue, labelFalse, valueTrue, valueFalse, valueNull);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	/**
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyName
	 *            name of the property managed by the widget
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 * @param valueTrue
	 *            value when the true checkbox is checked
	 * @param valueFalse
	 *            value when the false checkbox is checked
	 * @param valueNull
	 *            value when no checkboxes are checked
	 */
	public VOPropertyDoubleCheck(ValueObjectModel valueObjectModel, String propertyName, String labelTrue,
	        String labelFalse, Object valueTrue, Object valueFalse, Object valueNull)
	{
		super(labelTrue, labelFalse, valueTrue, valueFalse, valueNull);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	/**
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyPath
	 *            pathname of the property managed by the widget
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 * @param valueTrue
	 *            value when the true checkbox is checked
	 * @param valueFalse
	 *            value when the false checkbox is checked
	 * @param valueNull
	 *            value when no checkboxes are checked
	 */
	public VOPropertyDoubleCheck(ValueObjectModel valueObjectModel, PropertyPath propertyPath, String labelTrue,
	        String labelFalse, Object valueTrue, Object valueFalse, Object valueNull)
	{
		this(valueObjectModel, propertyPath.toString(), labelTrue, labelFalse, valueTrue, valueFalse, valueNull);
	}

	// ---

	/**
	 * @param valueModel
	 *            model of the value
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 * @param valueTrue
	 *            value when the true checkbox is checked
	 * @param valueFalse
	 *            value when the false checkbox is checked
	 * @param valueNull
	 *            value when no checkboxes are checked
	 * @param orientation
	 *            orientation of the widget (VERTICAL or HORIZONTAL)
	 */
	public VOPropertyDoubleCheck(ValueModel valueModel, String labelTrue, String labelFalse, Object valueTrue,
	        Object valueFalse, Object valueNull, int orientation)
	{
		super(labelTrue, labelFalse, valueTrue, valueFalse, valueNull, orientation);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	/**
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyName
	 *            name of the property managed by the widget
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 * @param valueTrue
	 *            value when the true checkbox is checked
	 * @param valueFalse
	 *            value when the false checkbox is checked
	 * @param valueNull
	 *            value when no checkboxes are checked
	 * @param orientation
	 *            orientation of the widget (VERTICAL or HORIZONTAL)
	 */
	public VOPropertyDoubleCheck(ValueObjectModel valueObjectModel, String propertyName, String labelTrue,
	        String labelFalse, Object valueTrue, Object valueFalse, Object valueNull, int orientation)
	{
		super(labelTrue, labelFalse, valueTrue, valueFalse, valueNull, orientation);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	/**
	 * @param valueObjectModel
	 *            model of the valueObject
	 * @param propertyPath
	 *            pathname of the property managed by the widget
	 * @param labelTrue
	 *            label of the true checkbox
	 * @param labelFalse
	 *            label of the false checkbox
	 * @param valueTrue
	 *            value when the true checkbox is checked
	 * @param valueFalse
	 *            value when the false checkbox is checked
	 * @param valueNull
	 *            value when no checkboxes are checked
	 * @param orientation
	 *            orientation of the widget (VERTICAL or HORIZONTAL)
	 */
	public VOPropertyDoubleCheck(ValueObjectModel valueObjectModel, PropertyPath propertyPath, String labelTrue,
	        String labelFalse, Object valueTrue, Object valueFalse, Object valueNull, int orientation)
	{
		this(valueObjectModel, propertyPath.toString(), labelTrue, labelFalse, valueTrue, valueFalse, valueNull,
		        orientation);
	}

	// ---
	public void disconnectFromModel()
	{
		m_propertyEventHandler.disconnect();
	}
}