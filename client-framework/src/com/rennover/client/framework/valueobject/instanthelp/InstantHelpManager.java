package com.rennover.client.framework.valueobject.instanthelp;

import java.util.Collection;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.event.EventListenerList;

import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModelEvent;
import com.rennover.client.framework.valueobject.model.ValueObjectModelListener;
import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.validation.ValidationProblem;
import com.rennover.hotel.common.validation.ValidationResponse;
import com.rennover.hotel.common.validation.ValidationRules;
import com.rennover.hotel.common.validation.ValidationService;

/**
 * @author tcaboste
 * 
 * Cette classe g�re l'affichage des messages d'aide pour toutes les VOMs qui
 * lui sont raccorch�s Ces messages doivent �tre d�finis dans les fichiers de
 * description des valueobjects
 * 
 */
public class InstantHelpManager implements ValueObjectModelListener
{
	// changed the debug to false for stoping the logging for production defect #18455.
	private boolean m_debug = false;

	private Boolean m_hasErrors = null;

	private InstantHelpView m_instantHelpView = null;

	private Set m_valueObjectModelSet = new HashSet();

	protected EventListenerList m_listenerList = new EventListenerList();

	/**
	 * Contructeur simple
	 * 
	 */
	public InstantHelpManager()
	{
	}

	/**
	 * Constructeur avec un objet zone d'aide � la saisie
	 * 
	 * @param instantHelpView
	 *            objet zone d'aide � la saisie
	 */
	public InstantHelpManager(InstantHelpView instantHelpView)
	{
		m_instantHelpView = instantHelpView;
	}

	/**
	 * Indique si au moins un vom est enregistr� dans l'instantHelpManager
	 */
	public boolean isEmpty()
	{
		return m_valueObjectModelSet.isEmpty();
	}

	/**
	 * Utilisation interne M�thode de mise � jour de l'InstantHelpManager
	 * 
	 * @param valueObjectModel
	 * @param propertyName
	 */
	public void updateInstantHelp(ValueObjectModel valueObjectModel, String propertyName)
	{
		if (m_debug)
		{
			Logger.debugValue(this, "updateInstantHelp propertyName", propertyName);
		}

		// la propri�t� � mettre � jour doit faire partie d'un VOM rattach� �
		// l'InstantHelp
		if (!m_valueObjectModelSet.contains(valueObjectModel))
		{
			globalUpdate();
			return;
		}

		ValidationResponse response = valueObjectModel.getLastValidationResponse();
		if (response == null)
		{
			globalUpdate();
			return;
		}

		// si la propri�t� n'est pas invalide, on affiche l'aide � la saisie.
		// la propri�t� n'est pas valide
		if (propertyName != null)
		{

			List propertyProblemList = response.getPropertyProblemList(propertyName);
			if (propertyProblemList != null)
			{
				String defaultMessage = getPropertyHelpMessage(valueObjectModel.getValueObjectClass(), propertyName);
				displayFirstProblem(propertyProblemList, defaultMessage);
			}

			// est-elle manquante ?
			else if (response.isMissingMandatoryProperty(propertyName))
			{
				String defaultMessage = getPropertyHelpMessage(valueObjectModel.getValueObjectClass(), propertyName);
				if (defaultMessage == null)
				{
					defaultMessage = InstantHelpView.MANDATORY_HELP;
				}
				displayError(defaultMessage);
			}

			// sinon on affiche rien
			else if (response.getGlobalProblemList() != null)
			{
				List problemList = response.getGlobalProblemList();
				displayFirstProblem(problemList);
			}

			// sinon on affiche rien
			else
			{
				globalUpdate();
			}
		}

		// sinon si d'autres propri�t�s sont invalides, on affiche le message
		// d'erreur g�n�rique
		else if (hasInvalidProperties())
		{
			displayInvalidPropertiesHelp();
		}

		// certaines propri�t�s obligatoires ne sont pas renseign�es
		else if (isMandatoryPropertyMissing())
		{
			displayMandatoryHelp();
		}

		// des propri�t�s de l'objet ne sont pas compatibles
		else if ((response != null) && response.hasGlobalProblems())
		{
			List problemList = response.getGlobalProblemList();
			displayFirstProblem(problemList);
		}

		else if (hasGlobalProblems())
		{
			valueObjectModel = getVOMWithGlobalProblems();
			ValidationResponse vr = valueObjectModel.getLastValidationResponse();
			List problemList = vr.getGlobalProblemList();
			displayFirstProblem(problemList);
		} else
		{
			displayNoHelp();
		}

		updateHasErrors();
	}

	private void globalUpdate()
	{
		// sinon si d'autres propri�t�s sont invalides, on affiche le message
		// d'erreur g�n�rique
		if (hasInvalidProperties())
		{
			displayInvalidPropertiesHelp();
		}

		// certaines propri�t�s obligatoires ne sont pas renseign�es
		else if (isMandatoryPropertyMissing())
		{
			displayMandatoryHelp();
		}

		// des propri�t�s de l'objet ne sont pas compatibles
		else if (hasGlobalProblems())
		{
			ValueObjectModel valueObjectModel = getVOMWithGlobalProblems();
			ValidationResponse vr = valueObjectModel.getLastValidationResponse();
			List problemList = vr.getGlobalProblemList();
			displayFirstProblem(problemList);
		} else
		{
			displayNoHelp();
		}
	}

	/**
	 * Affiche un message d'aide lorsque la saisie de l'utilisateur est invalide
	 * selon les r�gles de validation. Par exemple, le nom d'une personne
	 * contient un chiffre.
	 */

	// removed the commented code for method displayPropertyHelp(Class
	// valueObjectClass, String propertyName)
	/**
	 * Retourn le message d'aide de la propri�t� du ValueObject Par exemple, le
	 * nom d'une personne contient un chiffre.
	 */
	private String getPropertyHelpMessage(Class valueObjectClass, String propertyName)
	{
		ValidationRules metadata = ValidationService.getPropertyValidationRules(valueObjectClass, propertyName);
		String message = metadata != null ? metadata.getDescription() : "Le message d'aide est indisponible";
		return message;
	}

	/**
	 * Affiche un message d'aide lorsqu'un objet ne peut pas �tre instanci�. Par
	 * exemple, l'utilisateur entre une date incompl�te ("01/01/200"), il est
	 * impossible d'instancier un objet Date ou Calendar.
	 */
	private void displayInvalidPropertiesHelp()
	{
		displayString(InstantHelpView.ERROR_ICON, InstantHelpView.INVALID_PROPERTIES_HELP);
	}

	/**
	 * Affiche un message contenant la liste des champs qui doivent �tre
	 * obligatoirement remplis pour cet �cran.
	 */
	private void displayMandatoryHelp()
	{
		displayString(InstantHelpView.ERROR_ICON, InstantHelpView.MANDATORY_HELP);
	}

	// removed the commented code for method displayValueObjectHelp(Class
	// valueObjectClass)

	/**
	 * Permet de vider la zone d'aide � la saisie
	 * 
	 */
	public void displayNoHelp()
	{
		// il y a espace pour s'assurer que la zone ne diminue pas de taille
		// lorsque
		// la zone est videe
		displayString(" ");
	}

	/**
	 * Utilisation interne Permet d'afficher un text dans le zone d'aide � la
	 * saisie
	 * 
	 * @param text
	 *            texte � afficher
	 */
	public void displayString(String text)
	{
		displayString(InstantHelpView.NO_ICON, text);
	}

	public void displayWarning(String text)
	{
		displayString(InstantHelpView.WARNING_ICON, text);
	}

	public void displayError(String text)
	{
		displayString(InstantHelpView.ERROR_ICON, text);
	}

	private void displayString(int iconStyle, String text)
	{
		if (m_instantHelpView != null)
		{
			m_instantHelpView.displayString(iconStyle, text);
			if (m_debug)
			{
				Logger.debugValue(this, "displayString text", text);
			}
		}
	}

	private void displayFirstProblem(List problemList)
	{
		if (problemList != null)
		{
			ValidationProblem problem = (ValidationProblem) problemList.get(0);
			int iconStyle = problem.isWarning() ? InstantHelpView.WARNING_ICON : InstantHelpView.ERROR_ICON;
			displayString(iconStyle, problem.getMessage());
		}
	}

	private void displayFirstProblem(List problemList, String defaultMessage)
	{
		String message = defaultMessage;
		int iconStyle = InstantHelpView.NO_ICON;

		if (problemList != null)
		{
			ValidationProblem problem = (ValidationProblem) problemList.get(0);
			String pbMessage = problem.getMessage();
			if (pbMessage != null)
			{
				message = pbMessage;
			}
			iconStyle = problem.isWarning() ? InstantHelpView.WARNING_ICON : InstantHelpView.ERROR_ICON;
		}

		displayString(iconStyle, message);
	}

	// removed the commented code for method displayProblem(ValidationProblem
	// problem)

	/**
	 * Utilisation Interne M�thode de notification des InstantHelpListeners sur
	 * le changement de validit�
	 */
	private void fireValidityChanged()
	{
		fireValidityChanged(new InstantHelpEvent(this, !hasErrors()));
	}

	/**
	 * Utilisation Interne M�thode de notification des InstantHelpListeners sur
	 * le changement de validit�
	 * 
	 * @param event
	 */
	private void fireValidityChanged(InstantHelpEvent event)
	{
		// for General Listeners
		// Guaranteed to return a non-null array
		Object[] listeners = m_listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == InstantHelpListener.class)
			{
				((InstantHelpListener) listeners[i + 1]).validityChanged(event);
			}
		}
	}

	/**
	 * Utilisation Interne M�thode de notification des InstantHelpListeners sur
	 * le changement d'une valeur dans un vom
	 * 
	 * @param vom
	 *            le ValueObjectModel ayant re�u la modification
	 * @param propertyName
	 *            le nom de la propri�t� modifi�e
	 * @param propertyValue
	 *            la nouvelle valeur affect�e � la propri�t�
	 */
	private void fireValueChanged(ValueObjectModel vom, String propertyName, Object propertyValue)
	{
		fireValueChanged(new InstantHelpEvent(this, !hasErrors(), vom, propertyName, propertyValue));
	}

	/**
	 * Utilisation Interne M�thode de notification des InstantHelpListeners sur
	 * le changement d'une valeur dans un vom
	 * 
	 * @param event
	 */
	private void fireValueChanged(InstantHelpEvent event)
	{
		// for General Listeners
		// Guaranteed to return a non-null array
		Object[] listeners = m_listenerList.getListenerList();

		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2)
		{
			if (listeners[i] == InstantHelpListener.class)
			{
				((InstantHelpListener) listeners[i + 1]).valueChanged(event);
			}
		}
	}

	/**
	 * Ajoute un listener sur les �v�nements du mod�le
	 */
	public void addInstantHelpListener(InstantHelpListener listener)
	{
		m_listenerList.add(InstantHelpListener.class, listener);
		updateInstantHelp();
	}

	/**
	 * Enl�ve un listener sur les �v�nements du mod�le
	 */
	public void removeInstantHelpListener(InstantHelpListener listener)
	{
		m_listenerList.remove(InstantHelpListener.class, listener);
	}

	/**
	 * Enl�ve tous les listeners sur les �v�nements du mod�le
	 */
	public void removeAllInstantHelpListeners()
	{
		EventListener[] listeners = m_listenerList.getListeners(InstantHelpListener.class);
		for (int i = 0; i < listeners.length; i++)
		{
			//m_listenerList.remove(InstantHelpListener.class, listeners[i]);
		}
	}

	/**
	 * Indique s'il y a au moins une erreur de saisie
	 * 
	 * @return si vrai il y a au moins une erreur de saisie
	 */
	public boolean hasErrors()
	{
		if (m_hasErrors == null)
		{
			updateHasErrors();
		}
		return m_hasErrors.booleanValue();
	}

	protected boolean computeHasErrors()
	{
		for (Iterator iter = m_valueObjectModelSet.iterator(); iter.hasNext();)
		{
			ValueObjectModel model = (ValueObjectModel) iter.next();
			if (!model.isEmpty())
			{
				ValidationResponse vr = model.getLastValidationResponse();
				if (!vr.isValid())
				{
					return true;
				}
			}
		}
		return false;
	}

	protected void updateHasErrors()
	{
		setHasErrors(computeHasErrors());
	}

	/**
	 * Utilisation interne Permet d'indiquer qu'il y a ou non une erreur de
	 * saisie
	 * 
	 * @param hasErrorsValue
	 *            vrai si il y a une erreur
	 */
	protected void setHasErrors(boolean hasErrorsValue)
	{
		Boolean hasErrors = Boolean.valueOf(hasErrorsValue);

		if (!hasErrors.equals(m_hasErrors))
		{
			m_hasErrors = hasErrors;
			fireValidityChanged();
		} else
		{
			m_hasErrors = hasErrors;
		}
	}

	/**
	 * Indique si au moins une propri�t� est invalide
	 * 
	 * @return si vrai au moins une propri�t� est invalide
	 */
	public boolean hasInvalidProperties()
	{
		for (Iterator iter = m_valueObjectModelSet.iterator(); iter.hasNext();)
		{
			ValueObjectModel model = (ValueObjectModel) iter.next();
			if (!model.isValueObjectValid())
			{
				ValidationResponse vr = model.getLastValidationResponse();
				if (vr != null)
				{
					if (!vr.isValid())
					{
						if (vr.hasInvalidProperties())
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Indique si au moins une propri�t� n'est pas renseign�e
	 * 
	 * @return si vrai au moins une propri�t� est manquante
	 */
	public boolean isMandatoryPropertyMissing()
	{

		for (Iterator iter = m_valueObjectModelSet.iterator(); iter.hasNext();)
		{
			ValueObjectModel model = (ValueObjectModel) iter.next();
			if (!model.isValueObjectValid())
			{
				ValidationResponse vr = model.getLastValidationResponse();
				if (vr != null)
				{
					if (vr.hasMissingMandatoryProperties())
					{
						return true;
					}
				}
			}
		}
		return false;

	}

	/**
	 * Utilisation Interne Indique si un VOM contient un ValueObject dont l'une
	 * des r�gles de validation n'a pas �t� respect�e
	 * 
	 * @return
	 */
	private boolean hasGlobalProblems()
	{
		return getVOMWithGlobalProblems() != null;
	}

	/**
	 * Utilisation Interne Retourne le ValueObject dont l'une des r�gles de
	 * validation n'a pas �t� respect�e
	 * 
	 * @return
	 */
	private ValueObjectModel getVOMWithGlobalProblems()
	{

		for (Iterator iter = m_valueObjectModelSet.iterator(); iter.hasNext();)
		{
			ValueObjectModel model = (ValueObjectModel) iter.next();
			if (!model.isEmpty() && model.getLastValidationResponse().hasGlobalProblems())
			{
				return model;
			}
		}
		return null;

	}

	// removed the commented code for method searchVOMex(VOMFilter check)

	/**
	 * Met � jour l'InstantHelpManager en v�rifiant l'�tat de chaque mod�le
	 */
	public void updateInstantHelp()
	{
		for (Iterator iter = m_valueObjectModelSet.iterator(); iter.hasNext();)
		{
			ValueObjectModel model = (ValueObjectModel) iter.next();
			if (!model.isEmpty())
			{
				ValidationResponse vr = model.getLastValidationResponse();
				if (!vr.isValid())
				{
					updateInstantHelp(model, null);
					return;
				}
			}
		}

		for (Iterator iter = m_valueObjectModelSet.iterator(); iter.hasNext();)
		{
			ValueObjectModel model = (ValueObjectModel) iter.next();
			if (!model.isEmpty())
			{
				ValidationResponse vr = model.getLastValidationResponse();
				if (!vr.hasGlobalProblems())
				{
					updateInstantHelp(model, null);
					return;
				}
			}
		}

		updateHasErrors();
	}

	/**
	 * Ajoute un mod�le � v�rifier
	 * 
	 * @param model
	 *            � v�rifier
	 */
	public void addValueObjectModel(ValueObjectModel model)
	{
		m_valueObjectModelSet.add(model); // ajout du mod�le dans le
		// InstantHelpManager
		model.addValueObjectModelListener(this); // enregistrement du
		// InstantHelpManager dans
		// le mod�le
		updateInstantHelp(); // mise � jour du InstantHelpManager
	}

	/**
	 * Enl�ve un mod�le � v�rifier
	 * 
	 * @param model
	 *            � ne plus v�rifier
	 */
	public boolean removeValueObjectModel(ValueObjectModel model)
	{
		model.removeValueObjectModelListener(this); // supression du
		// InstanthelpManager dans
		// le mod�le
		boolean result = m_valueObjectModelSet.remove(model); // supression du
		// mod�le dans
		// le
		// InstanthelpManager
		updateInstantHelp(); // mise � jour du InstantHelpManager
		return result;
	}

	/**
	 * Enl�ve tous les mod�les � v�rifier
	 */
	public void removeAllValueObjectModels()
	{
		// suppression du InstanthelpManager dans les mod�les
		for (Iterator iter = m_valueObjectModelSet.iterator(); iter.hasNext();)
		{
			ValueObjectModel model = (ValueObjectModel) iter.next();
			model.removeValueObjectModelListener(this);
		}

		// suppression des mod�les dans le InstanthelpManager
		m_valueObjectModelSet.clear();

		// mise � jour du InstantHelpManager
		updateInstantHelp();
	}

	/**
	 * Retourne l'InstantHelpView (objet o� s'affiche le message de saisie)
	 * 
	 * @return objet zone d'aide � la saisie
	 */
	public InstantHelpView getInstantHelpView()
	{
		return m_instantHelpView;
	}

	/**
	 * Affecte l'InstantHelpView (objet o� s'affiche le message de saisie)
	 * 
	 * @param view
	 *            nouvel objet zone d'aide � la saisie
	 */
	public void setInstantHelpView(InstantHelpView view)
	{
		m_instantHelpView = view;
		updateInstantHelp();
	}

	/**
	 * Utilisation interne M�thode surcharger de l'interface
	 * ValueObjectModelListener
	 */
	public void valueObjectChanged(ValueObjectModelEvent event)
	{
		ValueObjectModel valueObjectModel = event.getValueObjectModel();

		updateInstantHelp(valueObjectModel, null);
		fireValueChanged(valueObjectModel, null, null);
	}

	/**
	 * Utilisation interne M�thode surcharger de l'interface
	 * ValueObjectModelListener
	 */
	public void valueObjectPropertyChanged(ValueObjectModelEvent event)
	{
		ValueObjectModel valueObjectModel = event.getValueObjectModel();
		String propertyName = event.getPropertyName();

		updateInstantHelp(valueObjectModel, propertyName);
		fireValueChanged(valueObjectModel, propertyName, event.getPropertyValue());
	}

	// removed the commented code for method VOMFilter

	/**
	 * @return
	 */
	public Set getAllVOMSet()
	{
		return (Set) ((HashSet) m_valueObjectModelSet).clone();
	}

	static public boolean isVOMCollectionValid(Collection vomCollection)
	{
		for (Iterator iter = vomCollection.iterator(); iter.hasNext();)
		{
			ValueObjectModel vom = (ValueObjectModel) iter.next();
			if (!vom.isValueObjectValid())
			{
				return false;
			}
		}
		return true;
	}
}
