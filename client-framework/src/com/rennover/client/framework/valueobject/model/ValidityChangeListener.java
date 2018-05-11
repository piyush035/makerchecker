package com.rennover.client.framework.valueobject.model;

import java.util.List;

/**
 * @author tcaboste
 * 
 * Cette classe permet d'écouter les changements de validité d'un vom. Certaines
 * propriétés peuvent être exclues ou incluses dans la vérification.
 * 
 */
public abstract class ValidityChangeListener implements ValueObjectModelListener
{
	private boolean m_isVomValid = false;

	private ValueObjectModel m_vom;

	private boolean m_removePropertiesFromValidation;

	private List m_propertyListForValidation;

	/**
	 * NB : le ValidityChangeListener s'enregistre directement sur le vom passé
	 * en paramètre.
	 * 
	 * @param vom
	 */
	public ValidityChangeListener(ValueObjectModel vom)
	{
		m_vom = vom;
		vom.addValueObjectModelListener(this);
	}

	/**
	 * Permet de filtrer la vérification du vom sur certaines propriétés ou
	 * d'exclure de la vérification
	 * 
	 * @param propertyList
	 *            liste des propriétés à exclure ou inclure
	 * @param removePropertyProblems
	 *            indique s'il faut exclure les propriétés de la vérification
	 */
	public void setValidationFilter(List propertyList, boolean removePropertyProblems)
	{
		m_propertyListForValidation = propertyList;
		m_removePropertiesFromValidation = removePropertyProblems;
		checkValidity();
	}

	/**
	 * Indique si lors de la dernière modification le vom était valid ou non
	 * (selon le filtre)
	 * 
	 * @return
	 */
	public boolean isVomValid()
	{
		return m_isVomValid;
	}

	/**
	 * Méthode appellée lorsque la validité du vom change (selon les critères du
	 * filtre)
	 * 
	 * @param isVomValid
	 */
	protected abstract void validityChanged(boolean isVomValid);

	protected void checkValidity()
	{
		m_isVomValid = _isVomValid();
		validityChanged(m_isVomValid);
	}

	/**
	 * Calcul la validité du vom en fonction du filtre en cours
	 * 
	 * @return
	 */
	private boolean _isVomValid()
	{
		return m_vom.isValueObjectValid(m_propertyListForValidation, m_removePropertiesFromValidation);
	}

	// --- ValueObjectModelListener Interface ---
	public void valueObjectChanged(ValueObjectModelEvent event)
	{
		checkValidity();
	}

	// --- ValueObjectModelListener Interface ---
	public void valueObjectPropertyChanged(ValueObjectModelEvent event)
	{
		checkValidity();
	}

}
