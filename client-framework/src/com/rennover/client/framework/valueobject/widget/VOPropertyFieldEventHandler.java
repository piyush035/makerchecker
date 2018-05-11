package com.rennover.client.framework.valueobject.widget;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComponent;

import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModelEvent;
import com.rennover.client.framework.valueobject.model.ValueObjectModelListener;
import com.rennover.client.framework.widget.base.ComparatorHelper;
import com.rennover.client.framework.widget.field.InputField;
import com.rennover.client.framework.window.DefaultDialog;
import com.rennover.client.framework.window.DefaultFrame;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.validation.ValidationRules;

/**
 * @author tcaboste
 * 
 * Cette classe est un gestionnaire d'évènement pour les objets champs de saisie
 * (VOPropertyXxx). Elle gère la communication un champ de saisie et le modèle
 * de ValueObject (ValueObjectModel) associé à ce champ. Pour cela, elle est
 * enregistrée auprès du ValueObjectModel avec le nom de propriété du
 * ValueObject géré par ce champ. Chaque évènement de modification reçu est
 * alors réappliqué sur l'objet champ de saisie. Cette classe doit être
 * surchargée afin d'y ajouter la communication champ de saisie -> modèle
 * 
 * @audit dattias 30/12/02
 * 
 */
public abstract class VOPropertyFieldEventHandler implements ValueObjectModelListener, FocusListener
{
	private boolean m_notifFromModel = true;

	protected InputField m_field;

	private ValueObjectModel m_valueObjectModel;

	private String m_propertyName;

	private boolean m_notificationModeOn = true;

	/**
	 * Constructeur sans paramètre
	 * 
	 */
	public VOPropertyFieldEventHandler()
	{
	}

	/**
	 * Constructeur avec connexion au valueObject et au champ
	 * 
	 * @param field
	 *            champ à gérer
	 * @param valueObjectModel
	 *            modèle de valueObject à associé au champ
	 * @param propertyName
	 *            propriété du modèle à gérer avec le champ
	 */
	public VOPropertyFieldEventHandler(InputField field, ValueObjectModel valueObjectModel, String propertyName)
	{
		// initialisation du gestionnaire
		setValueObjectModel(field, valueObjectModel, propertyName);
	}

	/**
	 * utilisation interne: gère l'évènement entrée de focus dans le champ
	 */
	public void focusGained(FocusEvent e)
	{
		// prévenir l'instantHelpManager du changement de focus
		updateInstantHelp();
	}

	/**
	 * utilisation interne: gère l'évènement sortie de focus dans le champ
	 */
	public void focusLost(FocusEvent e)
	{
	}

	/**
	 * Retourne l'instantHelpManager de la fenêtre contenant le champ
	 * 
	 * @return l'instantHelpManager
	 */
	public InstantHelpManager getInstantHelpManager()
	{
		InstantHelpManager instantHelpManager = null;
		Window window = WindowManager.getOwningWindow((JComponent) m_field);

		if (window instanceof DefaultDialog)
		{
			instantHelpManager = ((DefaultDialog) window).getInstantHelpManager();
		} else if (window instanceof DefaultFrame)
		{
			instantHelpManager = ((DefaultFrame) window).getInstantHelpManager();
		}

		return instantHelpManager;
	}

	/**
	 * Remet à jour l'instantHelpManager de la fenêtre contenant le champs
	 * 
	 */
	private void updateInstantHelp()
	{

		InstantHelpManager instantHelpManager = getInstantHelpManager();
		if (instantHelpManager != null)
		{
			// l'instantHelp est mis à jour directement
			instantHelpManager.updateInstantHelp(m_valueObjectModel, m_propertyName);
		}
	}

	/**
	 * initialise le gestionnaire d'évènement avec un ValueModel
	 * 
	 * @param field
	 *            widget associée
	 * @param valueModel
	 *            modèle du ValueObject
	 */
	public void setValueModel(InputField field, ValueModel valueModel)
	{
		setValueObjectModel(field, valueModel, ValueModel.VALUE);
	}

	/**
	 * initialise le gestionnaire d'évènement
	 * 
	 * @param field
	 *            widget associé au handler
	 * @param valueObjectModel
	 *            modèle du ValueObject
	 * @param propertyPath
	 *            nom-chemin de la propriété gérée par le champ
	 */
	public void setValueObjectModel(InputField field, ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
		setValueObjectModel(field, valueObjectModel, propertyPath);
	}

	/**
	 * initialise le gestionnaire d'évènement
	 * 
	 * @param field
	 *            widget associé au handler
	 * @param valueObjectModel
	 *            modèle du ValueObject
	 * @param propertyName
	 *            nom de la propriété gérée par le champ
	 */
	public void setValueObjectModel(InputField field, ValueObjectModel valueObjectModel, String propertyName)
	{
		m_field = field;
		if (m_valueObjectModel != null)
		{
			m_valueObjectModel.removeValueObjectModelListener(this, m_propertyName);
		}

		m_valueObjectModel = valueObjectModel;
		m_propertyName = propertyName;

		((JComponent) m_field).addFocusListener(this);

		// connexion au gestionnaire au modèle
		valueObjectModel.addValueObjectModelListener(propertyName, this);

		ValidationRules rules = valueObjectModel.getPropertyValidationRules(propertyName);
		if (rules != null)
		{
			applyValidationRules(rules);
		}

		if (m_propertyName != null)
		{
			Object value = m_valueObjectModel.getValueObjectProperty(m_propertyName);
			setValueWithoutNotification(value);
			m_field.setValidField(m_field.isValidValue());
		}
	}

	// removed the commented method setField(InputField field),
	// connect(ValueObjectModel valueObjectModel, String propertyName)

	/**
	 * Cette méthode permet de déconnecter le handler de son ValueObjectModel
	 */
	public void disconnect()
	{
		if (m_valueObjectModel != null && m_propertyName != null)
		{
			m_valueObjectModel.removeValueObjectModelListener(this, m_propertyName);
			m_valueObjectModel = null;
			m_propertyName = null;
		}
	}

	/**
	 * Retourne le valueObject
	 * 
	 * @return le valueObject
	 */
	public ValueObjectModel getValueObjectModel()
	{
		return m_valueObjectModel;
	}

	/**
	 * Applique au champ les règles de présentation de la propriété.
	 * Actuellement, les caractères obligatoires
	 * 
	 * @param rules
	 */
	protected void applyValidationRules(ValidationRules rules)
	{
		m_field.setMandatoryField(rules.isMandatory());
		m_field.setReadOnly(rules.isReadOnly());
	}

	/**
	 * Notifie le modèle du ValueObject que la valeur a changé
	 */
	public void notifyValueObjectModel()
	{
		if (m_notificationModeOn)
		{
			updateValueObjectModel();
		}
	}

	protected void updateValueObjectModel()
	{
		if (m_propertyName == null)
		{
			return;
		}

		Object oldValue = m_valueObjectModel.getValueObjectProperty(m_propertyName);
		try
		{
			Object newValue = getFieldValue();
			if (ComparatorHelper.compareObject(newValue, oldValue) != 0)
			{
				m_notifFromModel = false;
				try
				{
					m_valueObjectModel.setValueObjectProperty(this, m_propertyName, newValue);
					m_field.setValidField(m_field.isValidValue());
				} finally
				{
					m_notifFromModel = true;
				}
			}
		} catch (Exception ex)
		{
			WindowManager.showExceptionMessage(ex, (Component) m_field);
			if (m_propertyName != null)
			{
				setValueWithoutNotification(oldValue);
			}
		}
	}

	/**
	 * Change la valeur du champ sans que le composant ne notifie une
	 * modification vers le modèle du ValueObject
	 */
	public void setValueWithoutNotification(Object value)
	{
		setNotificationMode(false);

		try
		{
			Object newValue = value;
			Object oldValue = getFieldValue();

			if (isFieldSetted())
			{
				if ((newValue == null) && (oldValue == null))
				{
					return;
				}

				if (newValue != null)
				{
					if (newValue.equals(oldValue))
					{
						return;
					}
				}
			}

			setFieldValue(newValue);
		} finally
		{
			setNotificationMode(true);
		}
	}

	/**
	 * Retourne la valeur contenue dans le champ de saisie
	 */
	public Object getFieldValue()
	{
		return m_field.getValue();
	}

	public void setFieldValue(Object value)
	{
		m_field.setValue(value);
	}

	public boolean isFieldSetted()
	{
		return m_field.isValueSetted();
	}

	/**
	 * (des)active les notifications vers le modèle du ValueObject
	 */
	private void setNotificationMode(boolean value)
	{
		m_notificationModeOn = value;
	}

	// --- ValueObjectModelListener Interface ---
	public void valueObjectChanged(ValueObjectModelEvent event)
	{
		if (m_propertyName != null && event.getSender() != this)
		{
			Object value = m_valueObjectModel.getValueObjectProperty(m_propertyName);
			setValueWithoutNotification(value);
		}
	}

	public void valueObjectPropertyChanged(ValueObjectModelEvent event)
	{
		if (m_propertyName != null && m_notifFromModel)
		{
			if (event.getPropertyName().equals(m_propertyName))
			{
				if (event.getSender() != this)
				{
					setValueWithoutNotification(event.getPropertyValue());
				}
				m_field.setValidField(m_field.isValidValue());
			}
		}
	}

	/**
	 * Returns the propertyName.
	 * 
	 * @return String
	 */
	public String getPropertyName()
	{
		return m_propertyName;
	}

	/**
	 * Sets the propertyName.
	 * 
	 * @param propertyName
	 *            The propertyName to set
	 */
	public void setPropertyName(String propertyName)
	{
		m_propertyName = propertyName;
	}

	/**
	 * @return
	 */
	public InputField getField()
	{
		return m_field;
	}
}