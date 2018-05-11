package com.rennover.client.framework.valueobject.model;

import java.util.List;

/**
 * @author tcaboste
 * 
 * Cette classe permet d'�couter les changements de validit� d'un vom. Certaines
 * propri�t�s peuvent �tre exclues ou incluses dans la v�rification.
 * 
 */
public abstract class ValidityChangeListener implements ValueObjectModelListener
{
	private boolean m_isVomValid = false;

	private ValueObjectModel m_vom;

	private boolean m_removePropertiesFromValidation;

	private List m_propertyListForValidation;

	/**
	 * NB : le ValidityChangeListener s'enregistre directement sur le vom pass�
	 * en param�tre.
	 * 
	 * @param vom
	 */
	public ValidityChangeListener(ValueObjectModel vom)
	{
		m_vom = vom;
		vom.addValueObjectModelListener(this);
	}

	/**
	 * Permet de filtrer la v�rification du vom sur certaines propri�t�s ou
	 * d'exclure de la v�rification
	 * 
	 * @param propertyList
	 *            liste des propri�t�s � exclure ou inclure
	 * @param removePropertyProblems
	 *            indique s'il faut exclure les propri�t�s de la v�rification
	 */
	public void setValidationFilter(List propertyList, boolean removePropertyProblems)
	{
		m_propertyListForValidation = propertyList;
		m_removePropertiesFromValidation = removePropertyProblems;
		checkValidity();
	}

	/**
	 * Indique si lors de la derni�re modification le vom �tait valid ou non
	 * (selon le filtre)
	 * 
	 * @return
	 */
	public boolean isVomValid()
	{
		return m_isVomValid;
	}

	/**
	 * M�thode appell�e lorsque la validit� du vom change (selon les crit�res du
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
	 * Calcul la validit� du vom en fonction du filtre en cours
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
