package com.rennover.client.framework.valueobject.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.event.EventListenerList;

import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.helper.ReflectionHelper;
import com.rennover.hotel.common.validation.ValidationResponse;
import com.rennover.hotel.common.validation.ValidationRules;
import com.rennover.hotel.common.validation.ValidationService;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 * @audit dattias 30/12/02
 */
public class ValueObjectModel implements PropertyChangeListener
{
	private boolean m_useClone = true;

	private Class m_valueObjectClass = null;

	private ValueObject m_valueObject;

	private ValueObject m_originalValueObject;

	private EventListenerList m_listenerList = new EventListenerList();

	private EventListenerList m_propertyListenerList = new EventListenerList();

	private Map m_propertyListenerListMap = new HashMap();

	private boolean m_valueObjectModified = false;

	private ValidationResponse m_lastValidationResponse = null;

	private Map m_propertyPathCache = new Hashtable();

	private boolean m_autoValidate = false;

	private boolean m_invalidWhenEmpty = false;

	private List m_propertyListForValidation = null;

	private boolean m_removePropertiesFromValidation = true;

	private String m_propertyChanging = null;

	/**
	 * Constructeur sans paramètres
	 * 
	 */
	public ValueObjectModel()
	{
	}

	/**
	 * Constructor ValueObjectModel.
	 * 
	 * @param valueObjectClass
	 *            classe du ValueObject à accepté dans ce modèle (les
	 *            sous-classes sont aussi autorisées)
	 */
	public ValueObjectModel(Class valueObjectClass)
	{
		this(valueObjectClass, true);
	}

	/**
	 * Constructor ValueObjectModel.
	 * 
	 * @param valueObjectClass
	 *            classe du ValueObject à accepté dans ce modèle (les
	 *            sous-classes sont aussi autorisées)
	 */
	public ValueObjectModel(Class valueObjectClass, boolean useClone, boolean invalidWhenEmpty)
	{
		m_valueObjectClass = valueObjectClass;
		m_useClone = useClone;
		m_invalidWhenEmpty = invalidWhenEmpty;
	}

	/**
	 * Constructor ValueObjectModel.
	 * 
	 * @param valueObjectClass
	 *            classe du ValueObject à accepté dans ce modèle (les
	 *            sous-classes sont aussi autorisées)
	 */
	public ValueObjectModel(Class valueObjectClass, boolean useClone)
	{
		m_valueObjectClass = valueObjectClass;
		m_useClone = useClone;
	}

	/**
	 * Constructor ValueObjectModel.
	 * 
	 * @param valueObjectClass
	 *            classe du ValueObject à accepté dans ce modèle (les
	 *            sous-classes sont aussi autorisées)
	 * @param parentModelListener
	 *            ValueObjectModelListener du modèle parent pour le notifié des
	 *            modifications
	 */
	public ValueObjectModel(Class valueObjectClass, ValueObjectModelListener parentModelListener)
	{
		this(valueObjectClass);
		addValueObjectModelListener(parentModelListener);
	}

	/**
	 * 
	 * Permet de gérer un filtre sur la validation du ValueObject et de ses
	 * subparts. La liste de propriétés passées indique les propriétés
	 * concernées par le filtre. Le booléen indique le mode de gestion du filtre : -
	 * à vrai : les problèmes relatifs aux propriétés indiquées sont enlévés de
	 * la liste. - à faux : seules les problèmes relatifs aux propriétés
	 * indiquées sont gardés
	 * 
	 * exemple: Le niveau organisationnel n'est pas pris en compte dans la
	 * validation ValueObjectModel siteModel =
	 * model.getValueObjectModel(GestionSitesModel.SITE); List propertyList =
	 * new ArrayList(); propertyList.add(Site.NIVEAU_ORGANISATIONNEL_ID);
	 * siteModel.setValidationFilter(propertyList, true);
	 * 
	 * exemple: Seul le nom est pris en compte dans la validation
	 * ValueObjectModel siteModel =
	 * model.getValueObjectModel(GestionSitesModel.SITE); List propertyList =
	 * new ArrayList(); propertyList.add(Site.NOM);
	 * siteModel.setValidationFilter(propertyList, false);
	 * 
	 * @param propertyList
	 *            liste des propriétés concernées par le filtre
	 * @param removePropertyProblems
	 *            true les problèmes sont enlévés, false ils sont les seuls à
	 *            être gardés
	 * 
	 */
	public void setValidationFilter(List propertyList, boolean removePropertyProblems)
	{
		m_propertyListForValidation = propertyList;
		m_removePropertiesFromValidation = removePropertyProblems;
		checkValueObject();
	}

	/**
	 * interne : affecte l'indicateur de modification du valueObject
	 */
	protected void setValueObjectModified(boolean modified)
	{
		m_valueObjectModified = modified;
	}

	/**
	 * Indique que le valueObject a été modifié
	 * 
	 * @return true le valueObject a été modifié
	 */
	public boolean isValueObjectModified()
	{
		return m_valueObjectModified;
	}

	/**
	 * Retourne le ValueObject contenu dans le modèle. NB: cette méthode
	 * instancie un nouveau ValueObject si le modèle est vide.
	 */
	public ValueObject getWorkingValueObject()
	{
		return m_valueObject;
	}

	/**
	 * Affecte le ValueObject à contenir dans le modèle.
	 */
	public void setValueObject(ValueObject valueObject)
	{
		setValueObject(null, valueObject);
	}

	/**
	 * interne : Vérifie la compatibilité entre un valueObject et la classe de
	 * valueObject utilisée dans le VOM
	 * 
	 * @param valueObject
	 */
	protected void checkValueObjectClass(ValueObject valueObject)
	{
		if (!m_valueObjectClass.isAssignableFrom(valueObject.getClass()))
		{
			throw new IncoherenceException("Incompatibilité entre le value object et le modèle qui le reçoit");
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
		if (!ValueObject.class.isAssignableFrom(valueObjectClass))
		{
			throw new IncoherenceException("La classe de ValueObject n'hérite pas de la classe ValueObject");
		}
		m_valueObjectClass = valueObjectClass;
		setValueObject(null);
	}

	/**
	 * Affecte le ValueObject à contenir dans le modèle.
	 */
	public void setValueObject(Object sender, ValueObject valueObject)
	{
		if (isAutoValidate())
		{
			validateChanges();
		}

		if (m_valueObject != null)
		{
			m_valueObject.removePropertyChangeListener(this);
		}

		m_valueObject = valueObject;
		m_originalValueObject = valueObject;

		if (m_valueObject != null)
		{
			checkValueObjectClass(m_valueObject); // Vérification de la classe
			cloneValueObject();
		}

		if (m_valueObject != null)
		{
			m_valueObject.addPropertyChangeListener(this);
		}

		checkValueObject();
		setValueObjectModified(false); // on réinitialise valueObjectModified
		fireValueObjectChanged(sender, valueObject);
	}

	/**
	 * implémentation pour le propertyChangeListener
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt)
	{
		if (isPropertyChanging(evt.getPropertyName()))
		{
			return;
		}

		checkValueObject();
		fireValueObjectPropertyChanged(this, m_valueObject, evt.getPropertyName(), evt.getNewValue(),
		        getLastValidationResponse().isValid());
	}

	public boolean isPropertyChanging(String propertyName)
	{
		return (m_propertyChanging != null) && (m_propertyChanging.equals(propertyName));
	}

	private void cloneValueObject()
	{
		if (m_useClone)
		{
			m_valueObject = (ValueObject) m_originalValueObject.clone();
		}
	}

	public void validateChanges()
	{
		if (m_useClone)
		{
			if (m_originalValueObject == null)
			{
				m_originalValueObject = m_valueObject;
			} else if (m_originalValueObject != m_valueObject)
			{
				m_originalValueObject.copyFrom(m_valueObject);
			}

			setValueObjectModified(false);
		}
	}

	public void cancelChanges()
	{
		if (m_useClone)
		{
			setValueObject(m_originalValueObject);
		}
	}

	/**
	 * @deprecated getWorkingValueObject(), getValidatedValueObject() or
	 *             getOriginalValueObject()
	 * 
	 * work as getValidatedValueObject()
	 * 
	 * @return
	 */
	public ValueObject getValueObject()
	{
		validateChanges();
		return m_originalValueObject;
	}

	public ValueObject getOriginalValueObject()
	{
		return m_originalValueObject;
	}

	public ValueObject getValidatedValueObject()
	{
		validateChanges();
		return m_originalValueObject;
	}

	protected ValueObject createValueObject()
	{
		ValueObject valueObject;

		// NB: ici création automatique de l'objet si le pointeur interne est
		// nul
		try
		{
			valueObject = (ValueObject) m_valueObjectClass.newInstance();
		} catch (InstantiationException e)
		{
			throw new TechnicalException(
			        m_valueObjectClass + " n'est pas instanciable par le constructeur par défaut.", e);
		} catch (IllegalAccessException e)
		{
			throw new TechnicalException(e);
		}

		return valueObject;
	}

	/**
	 * Retourne la valeur d'une propriété du ValueObject
	 */
	public Object getValueObjectProperty(String propertyName)
	{
		if (m_valueObject == null)
		{
			return null;
		}

		if (PropertyPath.isPropertyPath(propertyName))
		{
			String[] propertyNameList = getPropertyPath(propertyName);
			return getValueObjectProperty(propertyNameList);
		} else
		{
			Object value = m_valueObject.getProperty(propertyName);
			return value;
		}
	}

	protected Object getValueObjectProperty(String[] propertyNameList)
	{
		if (m_valueObject == null)
		{
			return null;
		}

		ValueObject valueObject = m_valueObject;
		int nameCount = propertyNameList.length;
		for (int i = 0; i < nameCount - 1; i++)
		{
			Object value = valueObject.getProperty(propertyNameList[i]);
			if (value == null)
			{
				return null;
			} else if (value instanceof ValueObject)
			{
				valueObject = (ValueObject) value;
			} else
			{
				throw new IncoherenceException("La propriété '" + propertyNameList + "' de l'objet '" + m_valueObject
				        + "' n'existe pas");
			}
		}
		String lastName = propertyNameList[nameCount - 1];
		Object value = valueObject.getProperty(lastName);
		return value;
	}

	/**
	 * Change la valeur d'une propriété du ValueObject
	 */
	public void setValueObjectProperty(Object sender, String propertyName, Object value)
	{
		// Sauvegarde du nom de la propriété en cours de modification
		// NB: ceci permet d'éviter que le propertyChange renvoie une deuxième
		// notif, car cette méthode renvoie déjà une notif.
		m_propertyChanging = propertyName;

		try
		{
			ValueObject valueObject;
			if (PropertyPath.isPropertyPath(propertyName))
			{
				String[] propertyNameList = getPropertyPath(propertyName);
				// Commentted the logging for production defect #18455.
//				Logger.debugValue(this, propertyName, value);
				valueObject = setValueObjectProperty(sender, propertyNameList, value);
			} else
			{
				valueObject = m_valueObject;

				if (valueObject == null)
				{
					valueObject = createValueObject();
					setValueObject(sender, valueObject);

					// on est obligé d'égaler les références car la méthode
					// setValueObject crée le clone et m_valueObject "devient"
					// le clone.
					// du coup valueObject et m_valueObject ne sont plus les
					// mêmes objets et le setValuObjectPropery ne met pas à jour
					// le clone
					// mais l'original qui est écrasé par le clone et donc
					// l'ancienne valeur un peu plus loin :-(
					valueObject = m_valueObject;
				}

				// Commentted the logging for production defect #18455.
//				Logger.debugValue(this, propertyName, value);
				valueObject.setProperty(propertyName, value);
			}

			try
			{
				checkValueObject();
				ValidationResponse vr = getLastValidationResponse();
				if (vr != null)
				{
					boolean isPropertyValid = vr.isPropertyValid(propertyName);
					setValueObjectModified(true); // on indique que le
					// valueObject a été modifié
					fireValueObjectPropertyChanged(sender, valueObject, propertyName, value, isPropertyValid);
				}
			} catch (Exception ex)
			{
				// si une exception a eu lieu la valeur est remise au départ
				throw new TechnicalException("Impossible de valider la propriété " + propertyName + " de "
				        + valueObject.getClass(), ex);
			}
		} finally
		{
			m_propertyChanging = null;
		}
	}

	public void setValueObjectProperty(String propertyName, Object value)
	{
		setValueObjectProperty(null, propertyName, value);
	}

	protected String[] getPropertyPath(String propertyFullPath)
	{
		String[] propertyPath = (String[]) m_propertyPathCache.get(propertyFullPath);

		if (propertyPath == null)
		{
			propertyPath = PropertyPath.getPropertyPath(propertyFullPath);
		}
		return propertyPath;
	}

	protected ValueObject setValueObjectProperty(String[] propertyNameList, Object value)
	{
		return setValueObjectProperty(null, propertyNameList, value);
	}

	protected ValueObject setValueObjectProperty(Object sender, String[] propertyNameList, Object value)
	{
		if (m_valueObject == null)
		{
			return null;
		}

		ValueObject valueObject = m_valueObject;
		int nameCount = propertyNameList.length;
		for (int i = 0; i < nameCount - 1; i++)
		{
			Object object = valueObject.getProperty(propertyNameList[i]);
			if (object == null)
			{
				Class objectType = valueObject.getPropertyType(propertyNameList[i]);
				try
				{
					object = objectType.newInstance();
					valueObject.setProperty(propertyNameList[i], object);
					fireValueObjectChanged(sender);
				} catch (Exception e)
				{
					throw new TechnicalException("L'objet de type " + objectType + " n'a pas pu être créer", e);
				}
			}

			if (object instanceof ValueObject)
			{
				valueObject = (ValueObject) object;
			} else
			{
				throw new IncoherenceException("La propriété '" + propertyNameList + "' de l'objet '" + m_valueObject
				        + "' n'existe pas");
			}
		}

		String lastName = propertyNameList[nameCount - 1];
		valueObject.setProperty(lastName, value);
		return valueObject;
	}

	/**
	 * Ajoute un listener sur les évènements du modèle
	 */
	public void addValueObjectModelListener(ValueObjectModelListener listener)
	{
		m_listenerList.add(ValueObjectModelListener.class, listener);
	}

	/**
	 * Enlève un listener sur les évènements du modèle
	 */
	public void removeValueObjectModelListener(ValueObjectModelListener listener)
	{
		m_listenerList.remove(ValueObjectModelListener.class, listener);
	}

	/**
	 * Retourne une liste de listeners associés à une propriété en particulier
	 */
	protected EventListenerList getPropertyListenerList(String propertyName)
	{
		EventListenerList listenerList = (EventListenerList) m_propertyListenerListMap.get(propertyName);

		if (listenerList == null)
		{
			listenerList = new EventListenerList();
			m_propertyListenerListMap.put(propertyName, listenerList);
		}

		return listenerList;
	}

	/**
	 * Ajoute un listener sur les évènements du modèle liés à une propriété
	 * particulière
	 */
	public void addValueObjectModelListener(String propertyName, ValueObjectModelListener listener)
	{
		EventListenerList listenerList = getPropertyListenerList(propertyName);
		listenerList.add(ValueObjectModelListener.class, listener);
		m_propertyListenerList.add(ValueObjectModelListener.class, listener);
	}

	/**
	 * Enlève un listener sur les évènements du modèle liés à une propriété
	 * particulière
	 */
	public void removeValueObjectModelListener(ValueObjectModelListener listener, String propertyName)
	{
		EventListenerList listenerList = getPropertyListenerList(propertyName);
		listenerList.remove(ValueObjectModelListener.class, listener);
		m_propertyListenerList.remove(ValueObjectModelListener.class, listener);
	}

	// removed the commented method addValidityListener,removeValidityListener

	/**
	 * Prévient les listeners que le valueObject a changé
	 */
	protected void fireValueObjectChanged(Object sender, ValueObject valueObject)
	{
		fireValueObjectChanged(new ValueObjectModelEvent(sender, this, valueObject));
	}

	/**
	 * Prévient les listeners qu'une propriété du valueObject a changée
	 */
	protected void fireValueObjectPropertyChanged(Object sender, ValueObject valueObject, String propertyName,
	        Object value, boolean valid)
	{
		fireValueObjectPropertyChanged(new ValueObjectModelEvent(sender, this, valueObject, propertyName, value, valid));
	}

	/**
	 * Prévient les listeners que le valueObject a changé
	 */
	protected void fireValueObjectChanged(ValueObjectModelEvent event)
	{
		// for General Listeners
		// Guaranteed to return a non-null array
		Object[] listeners = m_listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{

			if (listeners[i] == ValueObjectModelListener.class)
			{
				ValueObjectModelListener listener = (ValueObjectModelListener) listeners[i + 1];
				if (listener != event.getSender())
				{
					listener.valueObjectChanged(event);
				}
			}
		}

		// for all Property Listeners
		// Guaranteed to return a non-null array
		listeners = m_propertyListenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == ValueObjectModelListener.class)
			{
				ValueObjectModelListener listener = (ValueObjectModelListener) listeners[i + 1];
				if (listener != event.getSender())
				{
					listener.valueObjectChanged(event);
				}
			}
		}
	}

	/**
	 * Prévient les listeners qu'une propriété du valueObject a changée
	 */
	protected void fireValueObjectPropertyChanged(ValueObjectModelEvent event)
	{
		// for General Listeners
		// Guaranteed to return a non-null array
		Object[] listeners = m_listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == ValueObjectModelListener.class)
			{
				ValueObjectModelListener listener = (ValueObjectModelListener) listeners[i + 1];
				if (listener != event.getSender())
				{
					listener.valueObjectPropertyChanged(event);
				}
			}
		}

		// for associated Property Listeners
		EventListenerList listenerList = getPropertyListenerList(event.getPropertyName());

		// Guaranteed to return a non-null array
		listeners = listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == ValueObjectModelListener.class)
			{
				ValueObjectModelListener listener = (ValueObjectModelListener) listeners[i + 1];
				if (listener != event.getSender())
				{
					listener.valueObjectPropertyChanged(event);
				}
			}
		}
	}

	/**
	 * indique si le valueObject est valide
	 * 
	 * @return true s'il est valide
	 */
	public boolean isValueObjectValid()
	{
		if (isEmpty())
		{
			return m_invalidWhenEmpty ? false : true;
		} else
		{
			ValidationResponse lastValidationResponse = getLastValidationResponse();
			if (lastValidationResponse != null && !lastValidationResponse.isValid())
			{
				// Commentted the logging for production defect #18455.
//				Logger.warn(this, lastValidationResponse.toString());
			}
			return lastValidationResponse != null ? lastValidationResponse.isValid() : true;
		}
	}

	// removed the commented method isValueObjectValid()

	/**
	 * indique si la propriété du valueObject est valide
	 * 
	 * @return true s'elle est valide
	 */
	public boolean isValueObjectPropertyValid(String propertyName)
	{
		ValidationResponse lastValidationResponse = getLastValidationResponse();
		return lastValidationResponse != null ? lastValidationResponse.isPropertyValid(propertyName) : true;
	}

	/**
	 * indique si le vom ne contient pas de valueObject
	 * 
	 * @return true le vom est vide
	 */
	public boolean isEmpty()
	{
		return (m_valueObject == null);
	}

	/**
	 * Valide le valueObject du modèle et renvoie le résultat de la validation
	 * 
	 * @return résultat de la validation
	 */
	protected void checkValueObject()
	{
		ValidationResponse vr = computeValidationResponse();
		setLastValidationResponse(vr);
	}

	protected ValidationResponse computeValidationResponse()
	{
		return computeValidationResponse(m_valueObject, m_invalidWhenEmpty, m_propertyListForValidation,
		        m_removePropertiesFromValidation);
	}

	protected ValidationResponse computeValidationResponse(List propertyList, boolean removeProperties)
	{
		return computeValidationResponse(m_valueObject, m_invalidWhenEmpty, propertyList, removeProperties);
	}

	protected boolean isValueObjectValid(List propertyList, boolean removeProperties)
	{
		return computeValidationResponse(m_valueObject, m_invalidWhenEmpty, propertyList, removeProperties).isValid();
	}

	public static boolean isValueObjectValid(ValueObject valueObject, boolean invalidWhenEmpty, List propertyList,
	        boolean removeProperties)
	{
		ValidationResponse vr = computeValidationResponse(valueObject, invalidWhenEmpty, propertyList, removeProperties);
		return vr.isValid();
	}

	public static ValidationResponse computeValidationResponse(ValueObject valueObject, boolean invalidWhenEmpty,
	        List propertyList, boolean removeProperties)
	{
		ValidationResponse vr = new ValidationResponse(valueObject);

		// removed the if condition which was doing nothing

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

	protected void clearLastValidationResponse()
	{
		m_lastValidationResponse = null;
	}

	protected void setLastValidationResponse(ValidationResponse vr)
	{
		ValidationResponse lastVr = m_lastValidationResponse == null ? new ValidationResponse(m_valueObject)
		        : m_lastValidationResponse;
		m_lastValidationResponse = vr;
		
		// Commentted the logging for production defect #18455.
//		Logger.debugValue(this, "vr", vr);

		// Code permettant de notifier le changement de validité vers des
		// ValueObjectModelListeners
		Collection validityChangedPropertySet = getValidityChangedPropertySet(lastVr.getAllInvalidProperties(), vr
		        .getAllInvalidProperties());
		if (m_propertyChanging != null)
		{
			validityChangedPropertySet.remove(m_propertyChanging); // la
			// propriété
			// en cours
			// de
			// modification
			// est
			// retirée
			// de la
			// notification
		}

		Iterator ite = validityChangedPropertySet.iterator();
		while (ite.hasNext())
		{
			String propertyName = (String) ite.next();
			try
			{
				fireValueObjectPropertyChanged(propertyName);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}

	}

	public Collection getValidityChangedPropertySet(Collection oldList, Collection newList)
	{
		Collection changed = new HashSet();
		Collection becomeInvalid = new HashSet();
		Collection wasInvalid = new HashSet();

		// calcul des propriétés qui étaient invalides
		wasInvalid.addAll(oldList);
		wasInvalid.removeAll(newList);

		// calcul de propriétés qui sont devenue invalides
		becomeInvalid.addAll(newList);
		becomeInvalid.removeAll(oldList);

		// les propriétés ayant changées sont les propriétés qui étaient
		// invalides et celles qui le sont devenues
		changed.addAll(wasInvalid);
		changed.addAll(becomeInvalid);

		return changed;
	}

	/**
	 * Indique qu'une propriété est obligatoire
	 * 
	 * @param propertyName
	 *            nom de la propriété
	 * @return boolean vrai si la propriété est obligatoire
	 */
	public ValidationRules getPropertyValidationRules(String propertyName)
	{
		if (m_valueObjectClass == null && m_valueObject == null)
		{
			return null;
		}

		if (PropertyPath.isPropertyPath(propertyName))
		{
			String[] propertyNameList = getPropertyPath(propertyName);
			return getPropertyValidationRules(propertyNameList);
		} else
		{
			Class valueObjectClass = (m_valueObjectClass != null) ? m_valueObjectClass : m_valueObject.getClass();
			return ValidationService.getPropertyValidationRules(valueObjectClass, propertyName);
		}
	}

	protected ValidationRules getPropertyValidationRules(String[] propertyNameList)
	{
		if (m_valueObjectClass == null && m_valueObject == null)
		{
			return null;
		}

		Class valueObjectClass = (m_valueObjectClass != null) ? m_valueObjectClass : m_valueObject.getClass();
		int nameCount = propertyNameList.length;
		for (int i = 0; i < nameCount - 1; i++)
		{
			Class valueClass = ReflectionHelper.getPropertyType(valueObjectClass, propertyNameList[i]);
			if (valueClass == null)
			{
				return null;
			} else if (ValueObject.class.isAssignableFrom(valueClass))
			{
				valueObjectClass = valueClass;
			} else
			{
				throw new IncoherenceException("La propriété '" + propertyNameList + "' de l'objet '" + m_valueObject
				        + "' n'existe pas");
			}
		}

		String lastName = propertyNameList[nameCount - 1];
		return ValidationService.getPropertyValidationRules(valueObjectClass, lastName);
	}

	public Class getPropertyType(String propertyName)
	{
		Class propertyType;

		if (PropertyPath.isPropertyPath(propertyName))
		{
			String[] propertyNameList = getPropertyPath(propertyName);
			propertyType = getPropertyType(propertyNameList);
		} else
		{
			Class valueObjectClass = isEmpty() ? getValueObjectClass() : getOriginalValueObject().getClass();
			propertyType = ReflectionHelper.getPropertyType(valueObjectClass, propertyName);
			propertyType = ReflectionHelper.getOnlyObjectType(propertyType);
		}

		return propertyType;
	}

	protected Class getPropertyType(String[] propertyNameList)
	{
		if (m_valueObjectClass == null && m_valueObject == null)
		{
			return null;
		}

		Class valueObjectClass = (m_valueObjectClass != null) ? m_valueObjectClass : m_valueObject.getClass();
		int nameCount = propertyNameList.length;
		for (int i = 0; i < nameCount - 1; i++)
		{
			Class valueClass = ReflectionHelper.getPropertyType(valueObjectClass, propertyNameList[i]);
			if (valueClass == null)
			{
				return null;
			} else if (ValueObject.class.isAssignableFrom(valueClass))
			{
				valueObjectClass = valueClass;
			} else
			{
				throw new IncoherenceException("La propriété '" + propertyNameList + "' de l'objet '" + m_valueObject
				        + "' n'existe pas");
			}
		}

		String lastName = propertyNameList[nameCount - 1];

		Class propertyType = ReflectionHelper.getPropertyType(valueObjectClass, lastName);
		propertyType = ReflectionHelper.getOnlyObjectType(propertyType);

		return propertyType;
	}

	public boolean isMandatoryProperty(String propertyName)
	{
		ValidationRules rules = getPropertyValidationRules(propertyName);
		return rules.isMandatory();
	}

	/**
	 * Returns the lastValidationResponse.
	 * 
	 * @return ValidationResponse
	 */
	public ValidationResponse getLastValidationResponse()
	{
		if (m_lastValidationResponse == null)
		{
			checkValueObject();
		}
		return m_lastValidationResponse;
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

	// removed the commented method fireValidityChanged()

	public void fireValueObjectChanged()
	{
		fireValueObjectChanged(null, m_valueObject);
	}

	public void fireValueObjectChanged(Object sender)
	{
		fireValueObjectChanged(sender, m_valueObject);
	}

	public void fireValueObjectPropertyChanged(String propertyName)
	{
		fireValueObjectPropertyChanged(null, m_valueObject, propertyName, getValueObjectProperty(propertyName), true);
	}

	/**
	 * @return
	 */
	public boolean isAutoValidate()
	{
		return m_autoValidate;
	}

	/**
	 * @param b
	 */
	public void setAutoValidate(boolean b)
	{
		m_autoValidate = b;
	}

	/**
	 * @return
	 */
	public boolean isInvalidWhenEmpty()
	{
		return m_invalidWhenEmpty;
	}

	/**
	 * @param b
	 */
	public void setInvalidWhenEmpty(boolean b)
	{
		m_invalidWhenEmpty = b;
	}

	/**
	 * @param m_propertyName
	 */
	public void updateInstantHelp(String m_propertyName)
	{
		fireValueObjectPropertyChanged(m_propertyName);
	}
}