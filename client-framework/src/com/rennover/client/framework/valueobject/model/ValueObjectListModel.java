package com.rennover.client.framework.valueobject.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.event.EventListenerList;

import com.rennover.client.framework.widget.base.ComparatorHelper;
import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.validation.ValidationResponse;
import com.rennover.hotel.common.validation.ValidationService;
import com.rennover.hotel.common.valueobject.DeltaList;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ValueObjectListModel
{
	private Class m_valueObjectClass;

	private List m_valueObjectList = new ArrayList();

	private List m_originalValueObjectList = null;

	protected EventListenerList m_listenerList = new EventListenerList();

	private boolean m_valueObjectListModified = false;

	private boolean m_useClone = false;

	private ValueObjectPropertyChangeListener m_valueObjectListener = new ValueObjectPropertyChangeListener();

	/**
	 * Constructor ValueObjectModel.
	 * 
	 * @param valueObjectClass
	 *            classe du ValueObject à accepté dans ce modèle (les
	 *            sous-classes sont aussi autorisées)
	 */
	public ValueObjectListModel(Class valueObjectClass)
	{
		this(valueObjectClass, false);
	}

	/**
	 * Constructor ValueObjectModel.
	 * 
	 * @param valueObjectClass
	 *            classe du ValueObject à accepté dans ce modèle (les
	 *            sous-classes sont aussi autorisées)
	 */
	public ValueObjectListModel(Class valueObjectClass, boolean useClone)
	{
		m_useClone = useClone;
		m_valueObjectClass = valueObjectClass;
	}

	/**
	 * Constructor ValueObjectModel.
	 * 
	 * @param valueObjectClass
	 *            classe du ValueObject à accepté dans ce modèle (les
	 *            sous-classes sont aussi autorisées)
	 * @param parentModelListener
	 *            ValueObjectListModelListener du modèle parent pour le notifié
	 *            des modifications
	 */
	public ValueObjectListModel(Class valueObjectClass, ValueObjectListModelListener parentModelListener)
	{
		this(valueObjectClass);
		addValueObjectListModelListener(parentModelListener);
	}

	public List getValidatedValueObjectList()
	{
		validateChanges();
		return m_originalValueObjectList;
	}

	/**
	 * Retourne le ValueObject contenu dans le modèle. NB: cette méthode
	 * instancie un nouveau ValueObject si le modèle est vide.
	 */
	public ValueObject getValueObject(int i)
	{
		// Added to fix defect 6988 By Rakesh dated 01/03/06 It is done to avoid
		// ArrayIndexOutOfBoundException which was rarely happening.
		if (m_valueObjectList.size() > i)
		{
			return (ValueObject) m_valueObjectList.get(i);
		} else
		{
			return null;
		}
	}

	/**
	 * Sets the valueObjectClass.
	 * 
	 * @param valueObjectClass
	 *            The valueObjectClass to set
	 */
	public void setValueObjectClass(Class valueObjectClass)
	{
		m_valueObjectClass = valueObjectClass;
	}

	/**
	 * Returns the valueObjectClass.
	 * 
	 * @return Class
	 */
	public Class getValueObjectClass()
	{
		return m_valueObjectClass;
	}

	public int getValueObjectCount()
	{
		return m_valueObjectList.size();
	}

	public void setValueObjectList(List valueObjectList)
	{
		setValueObjectList(null, valueObjectList);
	}

	public void setValueObjectList(Object sender, List valueObjectList)
	{
		if (m_valueObjectList == null)
		{
			for (int i = 0; i < m_valueObjectList.size(); i++)
			{
				ValueObject vo = (ValueObject) m_valueObjectList.get(i);
				vo.removePropertyChangeListener(m_valueObjectListener);
			}
		}

		if (valueObjectList == null)
		{
			m_valueObjectList = new ArrayList();
		} else
		{
			if (valueObjectList.size() > 0)
			{
				ValueObject valueObject = (ValueObject) valueObjectList.get(0);
				if ((valueObject != null) && (!m_valueObjectClass.isAssignableFrom(valueObject.getClass())))
				{
					throw new IncoherenceException(
					        "Incompatibility between the type of value object and the model that receives");
				}
			}

			if (valueObjectList instanceof DeltaList)
			{
				m_valueObjectList = new DeltaList(valueObjectList, ((DeltaList) valueObjectList).isKeepDeletedObject());
			} else
			{
				m_valueObjectList = new ArrayList(valueObjectList);
			}
		}

		cloneValueObjectList();
		setValueObjectListModified(false); // réinitialisation du booléen de
											// modification

		for (int i = 0; i < m_valueObjectList.size(); i++)
		{
			ValueObject vo = (ValueObject) m_valueObjectList.get(i);
			vo.addPropertyChangeListener(m_valueObjectListener);
		}

		if (sender != null)
		{
			fireValueObjectListChanged(sender);
		} else
		{
			fireValueObjectListChanged();
		}
	}

	public List getValueObjectList()
	{
		return m_valueObjectList;
	}

	public boolean isEmpty()
	{
		return m_valueObjectList.isEmpty();
	}

	public boolean isValueObjectListModified()
	{
		return m_valueObjectListModified;
	}

	// removed the commented method checkValueObject, computeValidationResponse,
	// computeValidationResponse,isValueObjectValid

	public static boolean isValueObjectValid(ValueObject valueObject, boolean invalidWhenEmpty, List propertyList,
	        boolean removeProperties)
	{
		ValidationResponse vr = computeValidationResponse(valueObject, invalidWhenEmpty, propertyList, removeProperties);
		return vr.isValid();
	}

	// removed the commented method fireValueObjectListChanged()

	/**
	 * Affecte le ValueObject à contenir dans le modèle.
	 */
	public void addValueObject(ValueObject valueObject)
	{
		if ((valueObject != null) && (!m_valueObjectClass.isAssignableFrom(valueObject.getClass())))
		{
			throw new IncoherenceException("Incompatibility between the type of value object and the model that receives");
		}

		m_valueObjectList.add(valueObject);
		int index = m_valueObjectList.indexOf(valueObject);
		valueObject.addPropertyChangeListener(m_valueObjectListener);

		setValueObjectListModified(true);
		fireValueObjectAdded(valueObject, index);
	}

	/**
	 * Ajoute un listener sur les évènements du modèle
	 */
	public void addValueObjectListModelListener(ValueObjectListModelListener listener)
	{
		m_listenerList.add(ValueObjectListModelListener.class, listener);
	}

	public void cancelChanges()
	{
		if (m_useClone)
		{
			copyFrom(m_valueObjectList, m_originalValueObjectList);
		}
	}

	public static List cloneValueObjectList(List listToClone)
	{
		if (listToClone == null)
		{
			return null;
		}

		int count = listToClone.size();
		List clonedList = new ArrayList(count);
		for (int i = 0; i < count; i++)
		{
			ValueObject vo = (ValueObject) listToClone.get(i);
			clonedList.add(vo.clone());
		}
		return clonedList;
	}

	public static ValidationResponse computeValidationResponse(ValueObject valueObject, boolean invalidWhenEmpty,
	        List propertyList, boolean removeProperties)
	{
		ValidationResponse vr = new ValidationResponse(valueObject);

		// removed the if condition which was doing nothing.

		if (valueObject != null)
		{
			if (propertyList == null)
			{
				vr = ValidationService.validate(valueObject);
			} else
			{
				vr = ValidationService.validate(valueObject, propertyList, removeProperties);
			}
		}

		return vr;
	}

	public static void copyFrom(List listToChange, List workingList)
	{
		int count = workingList.size();

		for (int i = 0; i < count; i++)
		{
			ValueObject workingVo = (ValueObject) workingList.get(i);
			ValueObject vo = (ValueObject) listToChange.get(i);
			vo.copyFrom(workingVo);
		}
	}

	/**
	 * @param m_propertyListForValidation
	 * @param m_removePropertiesFromValidation
	 * @return
	 */
	public boolean isAllValueObjectValid(List m_propertyListForValidation, boolean m_removePropertiesFromValidation)
	{
		boolean valid = true;

		for (int i = 0; i < m_valueObjectList.size(); i++)
		{
			ValueObject vo = (ValueObject) m_valueObjectList.get(i);
			boolean valueObjectValid = isValueObjectValid(vo, false, m_propertyListForValidation,
			        m_removePropertiesFromValidation);
			if (!valueObjectValid)
			{
				valid = false;
				break;
			}
		}

		return valid;
	}

	/**
	 * Method fireValueObjectListChanged.
	 * 
	 * @param m_valueObjectList
	 */
	public void fireValueObjectListChanged()
	{
		ValueObjectListModelEvent event = new ValueObjectListModelEvent(this);

		EventListener[] listeners = m_listenerList.getListeners(ValueObjectListModelListener.class);
		for (int i = listeners.length - 1; i >= 0; i--)
		{
			ValueObjectListModelListener listener = (ValueObjectListModelListener) listeners[i];
			listener.valueObjectListChanged(event);
		}
	}

	public void fireValueObjectListChanged(Object sender)
	{
		ValueObjectListModelEvent event = new ValueObjectListModelEvent(this);

		EventListener[] listeners = m_listenerList.getListeners(ValueObjectListModelListener.class);
		for (int i = listeners.length - 1; i >= 0; i--)
		{
			ValueObjectListModelListener listener = (ValueObjectListModelListener) listeners[i];
			if (listener != sender)
			{
				listener.valueObjectListChanged(event);
			}
		}
	}

	public void moveValueObject(ValueObject valueObject, boolean up)
	{
		if ((valueObject != null) && (!m_valueObjectClass.isAssignableFrom(valueObject.getClass())))
		{
			throw new IncoherenceException("Incompatibility between the type of value object and the model that receives");
		}

		int indexFrom = m_valueObjectList.indexOf(valueObject);
		int indexTo = indexFrom;
		if (up)
		{
			if (indexFrom > 0)
			{
				indexTo--;
			}
		} else
		{
			if (indexFrom < m_valueObjectList.size() - 1)
			{
				indexTo++;
			}
		}

		moveValueObject(valueObject, indexTo);
	}

	public void moveValueObject(ValueObject valueObject, int indexTo)
	{
		if ((valueObject != null) && (!m_valueObjectClass.isAssignableFrom(valueObject.getClass())))
		{
			throw new IncoherenceException("Incompatibility between the type of value object and the model that receives");
		}

		int indexFrom = m_valueObjectList.indexOf(valueObject);
		if (indexFrom != indexTo)
		{
			if (m_valueObjectList.remove(valueObject))
			{
				setValueObjectListModified(true);
				fireValueObjectRemoved(valueObject, indexFrom);

				m_valueObjectList.add(indexTo, valueObject);
				fireValueObjectAdded(valueObject, indexTo);
			}
		}
	}

	/**
	 * Affecte le ValueObject à contenir dans le modèle.
	 */
	public void removeValueObject(ValueObject valueObject)
	{
		int index = m_valueObjectList.indexOf(valueObject);
		if (m_valueObjectList.remove(valueObject))
		{
			setValueObjectListModified(true);
			valueObject.removePropertyChangeListener(m_valueObjectListener);
			fireValueObjectRemoved(valueObject, index);
		}
	}

	/**
	 * Enlève un listener sur les évènements du modèle
	 */
	public void removeValueObjectListModelListener(ValueObjectListModelListener listener)
	{
		m_listenerList.remove(ValueObjectListModelListener.class, listener);
	}

	public void sort()
	{
		ComparatorHelper.sort(m_valueObjectList);
		fireValueObjectListChanged(this);
	}

	/**
	 * 
	 * force to update widget bound on a valueobject
	 * 
	 * @deprecate now when a valueObject has been changed the volm is
	 *            automatically notified via PropertyChangeListener use contains
	 *            if you want to know if the object is in the volm
	 * @param valueObject
	 * @return
	 */
	public boolean updateValueObject(ValueObject valueObject)
	{
		return _updateValueObject(valueObject);
	}

	public boolean contains(ValueObject valueObject)
	{
		return m_valueObjectList.indexOf(valueObject) != -1;
	}

	private boolean _updateValueObject(ValueObject valueObject)
	{
		int index = m_valueObjectList.indexOf(valueObject);
		if (index == -1)
		{
			return false;
		}
		fireValueObjectUpdated(valueObject, index);
		setValueObjectListModified(true);
		return true;
	}

	public void validateChanges()
	{
		if (m_useClone)
		{
			copyFrom(m_originalValueObjectList, m_valueObjectList);
		}
	}

	protected void setValueObjectListModified(boolean modified)
	{
		m_valueObjectListModified = modified;
	}

	/**
	 * Method fireValueObjectAdded.
	 * 
	 * @param valueObject
	 */
	protected void fireValueObjectAdded(ValueObject valueObject, int index)
	{
		ValueObjectListModelEvent event = new ValueObjectListModelEvent(this, valueObject, index);

		EventListener[] listeners = m_listenerList.getListeners(ValueObjectListModelListener.class);
		for (int i = listeners.length - 1; i >= 0; i--)
		{
			ValueObjectListModelListener listener = (ValueObjectListModelListener) listeners[i];
			listener.valueObjectAdded(event);
		}
	}

	/**
	 * Method fireValueObjectRemoved.
	 * 
	 * @param valueObject
	 */
	protected void fireValueObjectRemoved(ValueObject valueObject, int index)
	{
		ValueObjectListModelEvent event = new ValueObjectListModelEvent(this, valueObject, index);

		EventListener[] listeners = m_listenerList.getListeners(ValueObjectListModelListener.class);
		for (int i = listeners.length - 1; i >= 0; i--)
		{
			ValueObjectListModelListener listener = (ValueObjectListModelListener) listeners[i];
			listener.valueObjectRemoved(event);
		}
	}

	/**
	 * Method fireValueObjectUpdated.
	 * 
	 * @param valueObject
	 */
	protected void fireValueObjectUpdated(ValueObject valueObject, int index)
	{
		if (index == -1)
		{
			return;
		}

		ValueObjectListModelEvent event = new ValueObjectListModelEvent(this, valueObject, index);

		EventListener[] listeners = m_listenerList.getListeners(ValueObjectListModelListener.class);
		for (int i = listeners.length - 1; i >= 0; i--)
		{
			ValueObjectListModelListener listener = (ValueObjectListModelListener) listeners[i];
			listener.valueObjectUpdated(event);
		}
	}

	private void cloneValueObjectList()
	{
		if (m_useClone)
		{
			m_originalValueObjectList = m_valueObjectList;
			m_valueObjectList = cloneValueObjectList(m_originalValueObjectList);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		int nbValueObject = m_valueObjectList.size();

		int nbListener = m_listenerList.getListenerCount();

		return "ValueObjectListModel : { use_clone = " + m_useClone + ", vo_count = " + nbValueObject
		        + ", listener_count=" + nbListener + " }";
	}

	class ValueObjectPropertyChangeListener implements PropertyChangeListener
	{
		public void propertyChange(PropertyChangeEvent evt)
		{
			ValueObject vo = (ValueObject) evt.getSource();
			_updateValueObject(vo);
		}
	}
}
