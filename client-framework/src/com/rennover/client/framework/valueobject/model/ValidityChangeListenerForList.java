package com.rennover.client.framework.valueobject.model;

import java.util.List;

/**
 * @author tcaboste
 *
 * Cette classe permet d'écouter les changements de validité d'un vom.
 * Certaines propriétés peuvent être exclues ou incluses dans la vérification.
 *
 */
public abstract class ValidityChangeListenerForList implements ValueObjectListModelListener
{
    private boolean m_isVomValid = false;

	private ValueObjectListModel m_volm;
    private boolean m_removePropertiesFromValidation;
    private List m_propertyListForValidation;

    /**
     * NB : le ValidityChangeListener s'enregistre directement sur le vom passé en paramètre.
     * @param vom
     */
    public ValidityChangeListenerForList(ValueObjectListModel volm)
    {
        m_volm = volm;
        volm.addValueObjectListModelListener(this);
    }

    /**
     * Permet de filtrer la vérification du vom sur certaines propriétés ou d'exclure de la vérification
     * @param propertyList liste des propriétés à exclure ou inclure
     * @param removePropertyProblems indique s'il faut exclure les propriétés de la vérification
     */
    public void setValidationFilter(List propertyList, boolean removePropertyProblems)
    {
        m_propertyListForValidation = propertyList;
        m_removePropertiesFromValidation = removePropertyProblems;
        checkValidity();
    }

    /**
     * Indique si lors de la dernière modification le vom était valid ou non (selon le filtre)
     * @return
     */
    public boolean isVomValid()
    {
        return m_isVomValid;
    }

    public void valueObjectAdded(ValueObjectListModelEvent event)
    {
        checkValidity();
    }

    public void valueObjectListChanged(ValueObjectListModelEvent event)
    {
        checkValidity();
    }

    public void valueObjectRemoved(ValueObjectListModelEvent event)
    {
        checkValidity();
    }

    public void valueObjectUpdated(ValueObjectListModelEvent event)
    {
        checkValidity();
    }

    /**
     * Méthode appellée lorsque la validité du vom change (selon les critères du filtre)
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
     * @return
     */
    private boolean _isVomValid()
    {
        return m_volm.isAllValueObjectValid(m_propertyListForValidation, m_removePropertiesFromValidation);
    }
}
