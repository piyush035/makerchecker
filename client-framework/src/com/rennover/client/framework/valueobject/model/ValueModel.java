package com.rennover.client.framework.valueobject.model;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.validation.PropertyValidator;
import com.rennover.hotel.common.validation.ValidationResponse;
import com.rennover.hotel.common.validation.ValidationService;

/**
 * @author tcaboste
 * 
 * This class is used to store a non-ValueObject object like String, Integer,
 * Date, etc. This class can be used with the VOPropertyFields like a
 * ValueObjectModel.
 */
public class ValueModel extends ValueObjectModel
{
	public static final String VALUE = SimpleValueObject.VALUE;

	private Class m_objectClass = null;

	private Map m_listenerAdaptorMap = new Hashtable();

	private Set m_propertyValidatorSet = null;

	private Class m_valueObjectClass = null;

	private String m_propertyName = null;

	/**
	 * Constructor
	 * 
	 * @param objectClass
	 *            class of the value to managed
	 */
	public ValueModel(Class objectClass)
	{
		super(SimpleValueObject.class, false);
		m_objectClass = objectClass;
		setValueObject(new SimpleValueObject());
	}

	/**
	 * Change the value
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(Object value)
	{
		if (value != null)
		{
			checkObjectClass(value);
		}
		setValueObjectProperty(VALUE, value);
	}

	/**
	 * Check the compatibility beetween the value and the class of object
	 * managed
	 * 
	 * @param value
	 *            the value to check
	 */
	private void checkObjectClass(Object value)
	{
		if (!m_objectClass.isAssignableFrom(value.getClass()))
		{
			throw new IncoherenceException("Incompatibilité entre le type de valeur et le modèle qui le reçoit");
		}
	}

	public void addPropertyValidator(PropertyValidator validator)
	{
		if (validator == null)
		{
			return;
		}

		if (m_propertyValidatorSet == null)
		{
			m_propertyValidatorSet = new HashSet();
		}

		m_propertyValidatorSet.add(validator);
	}

	public void removePropertyValidator(PropertyValidator validator)
	{
		if (validator == null)
		{
			return;
		}

		if (m_propertyValidatorSet == null)
		{
			return;
		}

		m_propertyValidatorSet.remove(validator);

		if (m_propertyValidatorSet.isEmpty())
		{
			m_propertyValidatorSet = null;
		}
	}

	public void setValidationPropertyRules(Class valueObjectClass, String propertyName)
	{
		m_valueObjectClass = valueObjectClass;
		m_propertyName = propertyName;
		clearLastValidationResponse();
	}

	protected ValidationResponse computeValidationResponse()
	{
		ValidationResponse vr = new ValidationResponse(null)
		{
			public void addMissingMandatoryProperty(String propertyName)
			{
				super.addMissingMandatoryProperty(VALUE);
			}

			public void addPropertyProblem(String propertyName, Class validatorClass, boolean warning)
			{
				super.addPropertyProblem(VALUE, validatorClass, warning);
			}
		};

		// Validation avec les même règles qu'une propriété d'un valueobject
		// particulier
		if (m_valueObjectClass != null && m_propertyName != null)
		{
			ValidationService.validate(vr, m_valueObjectClass, m_propertyName, getValue());
		}

		// Validation par rapport à une liste de validators
		if (m_propertyValidatorSet != null)
		{
			Object value = getValue();
			Iterator iter = m_propertyValidatorSet.iterator();
			while (iter.hasNext())
			{
				PropertyValidator validator = (PropertyValidator) iter.next();
				validator.validate(VALUE, value, vr);
			}
		}

		return vr;
	}

	/**
	 * Return the value
	 * 
	 * @return the value
	 */
	public Object getValue()
	{
		return getValueObjectProperty(VALUE);
	}

	public void addValueModelListener(ValueModelListener listener)
	{
		ValueModelListenerAdaptor listenerAdaptor = new ValueModelListenerAdaptor(listener);
		m_listenerAdaptorMap.put(listener, listenerAdaptor);
		super.addValueObjectModelListener(VALUE, listenerAdaptor);
	}

	public void removeValueModelListener(ValueModelListener listener)
	{
		ValueModelListenerAdaptor listenerAdaptor = (ValueModelListenerAdaptor) m_listenerAdaptorMap.get(listener);
		if (listenerAdaptor != null)
		{
			super.removeValueObjectModelListener(listenerAdaptor, VALUE);
			m_listenerAdaptorMap.remove(listener);
		}
	}

	private static class ValueModelListenerAdaptor implements ValueObjectModelListener
	{
		private ValueModelListener m_vml;

		public ValueModelListenerAdaptor(ValueModelListener vml)
		{
			m_vml = vml;
		}

		public void valueObjectChanged(ValueObjectModelEvent event)
		{
			; // rien
		}

		public void valueObjectPropertyChanged(ValueObjectModelEvent event)
		{
			if (m_vml != null)
			{
				m_vml.valueChanged(new ValueModelEvent(event));
			}
		}
	}
}
