package com.rennover.client.framework.valueobject.instanthelp;

import com.rennover.client.framework.valueobject.model.ValueObjectModel;

/**
 * @author tcaboste
 * 
 * Cet objet est utilis� lors des notifications de l'InstantHelpManager vers des
 * InstantHelpListeners. Il est utilis� � la fois pour l'�v�n�ment
 * validityChanged et valueChanged
 * 
 */
public class InstantHelpEvent
{
	private InstantHelpManager m_instantHelpManager = null;

	private boolean m_valid = false;

	private ValueObjectModel m_vom = null;

	private String m_propertyName = null;

	private Object m_propertyValue = null;

	/**
	 * Constructeur dans le cas d'un changement de validit� d'un
	 * InstantHelpManager
	 * 
	 * @param ihManager
	 * @param valid
	 */
	public InstantHelpEvent(InstantHelpManager ihManager, boolean valid)
	{
		this.m_instantHelpManager = ihManager;
		this.m_valid = valid;
	}

	/**
	 * Constructeur dans le cas d'un changement d'une propri�t� d'un mod�le d'un
	 * InstantHelpManager
	 * 
	 * @param ihManager
	 *            l'instantHelpManager
	 * @param valid
	 *            indique si l'instantHelpManager est valide
	 * @param vom
	 *            ValueObjectModel ayant re�u la modification
	 * @param propertyName
	 *            nom de la propri�t� modifi�e
	 * @param value
	 *            nouvelle valeur de la propri�t� modifi�e
	 */
	public InstantHelpEvent(InstantHelpManager ihManager, boolean valid, ValueObjectModel vom, String propertyName,
	        Object value)
	{
		this(ihManager, valid);
		m_vom = vom;
		m_propertyName = propertyName;
		m_propertyValue = value;
	}

	/**
	 * Indique si l'ensemble des mod�les de l'InstanHelpManager sont valides
	 * 
	 * @return vrai si l'InstantHelpManager est valide
	 */
	public boolean isValid()
	{
		return m_valid;
	}

	/**
	 * Retourne l'InstantHelpManager qui � g�n�r� cet �v�nement
	 * 
	 * @return
	 */
	public InstantHelpManager getInstantHelpManager()
	{
		return m_instantHelpManager;
	}

	/**
	 * Retourne le nom de la propri�t� qui a �t� modifi�e
	 * 
	 * @return
	 */
	public String getPropertyName()
	{
		return m_propertyName;
	}

	/**
	 * Retourne la nouvelle valeur de la propri�t� qui a �t� modifi�e
	 * 
	 * @return
	 */
	public Object getPropertyValue()
	{
		return m_propertyValue;
	}

	/**
	 * Retourne le mod�le dont une propri�t� a �t� modifi�e
	 * 
	 * @return
	 */
	public ValueObjectModel getVom()
	{
		return m_vom;
	}
}