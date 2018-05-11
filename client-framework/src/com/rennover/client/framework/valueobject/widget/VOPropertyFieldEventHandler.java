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
 * Cette classe est un gestionnaire d'�v�nement pour les objets champs de saisie
 * (VOPropertyXxx). Elle g�re la communication un champ de saisie et le mod�le
 * de ValueObject (ValueObjectModel) associ� � ce champ. Pour cela, elle est
 * enregistr�e aupr�s du ValueObjectModel avec le nom de propri�t� du
 * ValueObject g�r� par ce champ. Chaque �v�nement de modification re�u est
 * alors r�appliqu� sur l'objet champ de saisie. Cette classe doit �tre
 * surcharg�e afin d'y ajouter la communication champ de saisie -> mod�le
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
	 * Constructeur sans param�tre
	 * 
	 */
	public VOPropertyFieldEventHandler()
	{
	}

	/**
	 * Constructeur avec connexion au valueObject et au champ
	 * 
	 * @param field
	 *            champ � g�rer
	 * @param valueObjectModel
	 *            mod�le de valueObject � associ� au champ
	 * @param propertyName
	 *            propri�t� du mod�le � g�rer avec le champ
	 */
	public VOPropertyFieldEventHandler(InputField field, ValueObjectModel valueObjectModel, String propertyName)
	{
		// initialisation du gestionnaire
		setValueObjectModel(field, valueObjectModel, propertyName);
	}

	/**
	 * utilisation interne: g�re l'�v�nement entr�e de focus dans le champ
	 */
	public void focusGained(FocusEvent e)
	{
		// pr�venir l'instantHelpManager du changement de focus
		updateInstantHelp();
	}

	/**
	 * utilisation interne: g�re l'�v�nement sortie de focus dans le champ
	 */
	public void focusLost(FocusEvent e)
	{
	}

	/**
	 * Retourne l'instantHelpManager de la fen�tre contenant le champ
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
	 * Remet � jour l'instantHelpManager de la fen�tre contenant le champs
	 * 
	 */
	private void updateInstantHelp()
	{

		InstantHelpManager instantHelpManager = getInstantHelpManager();
		if (instantHelpManager != null)
		{
			// l'instantHelp est mis � jour directement
			instantHelpManager.updateInstantHelp(m_valueObjectModel, m_propertyName);
		}
	}

	/**
	 * initialise le gestionnaire d'�v�nement avec un ValueModel
	 * 
	 * @param field
	 *            widget associ�e
	 * @param valueModel
	 *            mod�le du ValueObject
	 */
	public void setValueModel(InputField field, ValueModel valueModel)
	{
		setValueObjectModel(field, valueModel, ValueModel.VALUE);
	}

	/**
	 * initialise le gestionnaire d'�v�nement
	 * 
	 * @param field
	 *            widget associ� au handler
	 * @param valueObjectModel
	 *            mod�le du ValueObject
	 * @param propertyPath
	 *            nom-chemin de la propri�t� g�r�e par le champ
	 */
	public void setValueObjectModel(InputField field, ValueObjectModel valueObjectModel, PropertyPath propertyPath)
	{
		setValueObjectModel(field, valueObjectModel, propertyPath);
	}

	/**
	 * initialise le gestionnaire d'�v�nement
	 * 
	 * @param field
	 *            widget associ� au handler
	 * @param valueObjectModel
	 *            mod�le du ValueObject
	 * @param propertyName
	 *            nom de la propri�t� g�r�e par le champ
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

		// connexion au gestionnaire au mod�le
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
	 * Cette m�thode permet de d�connecter le handler de son ValueObjectModel
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
	 * Applique au champ les r�gles de pr�sentation de la propri�t�.
	 * Actuellement, les caract�res obligatoires
	 * 
	 * @param rules
	 */
	protected void applyValidationRules(ValidationRules rules)
	{
		m_field.setMandatoryField(rules.isMandatory());
		m_field.setReadOnly(rules.isReadOnly());
	}

	/**
	 * Notifie le mod�le du ValueObject que la valeur a chang�
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
	 * modification vers le mod�le du ValueObject
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
	 * (des)active les notifications vers le mod�le du ValueObject
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