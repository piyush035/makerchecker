package com.rennover.client.framework.valueobject.instanthelp;

import com.rennover.client.framework.valueobject.model.ValueObjectModel;

/**
 * @author tcaboste
 * 
 * Cet objet est utilisé lors des notifications de l'InstantHelpManager vers des
 * InstantHelpListeners. Il est utilisé à la fois pour l'évènément
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
	 * Constructeur dans le cas d'un changement de validité d'un
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
	 * Constructeur dans le cas d'un changement d'une propriété d'un modèle d'un
	 * InstantHelpManager
	 * 
	 * @param ihManager
	 *            l'instantHelpManager
	 * @param valid
	 *            indique si l'instantHelpManager est valide
	 * @param vom
	 *            ValueObjectModel ayant reçu la modification
	 * @param propertyName
	 *            nom de la propriété modifiée
	 * @param value
	 *            nouvelle valeur de la propriété modifiée
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
	 * Indique si l'ensemble des modèles de l'InstanHelpManager sont valides
	 * 
	 * @return vrai si l'InstantHelpManager est valide
	 */
	public boolean isValid()
	{
		return m_valid;
	}

	/**
	 * Retourne l'InstantHelpManager qui à généré cet évènement
	 * 
	 * @return
	 */
	public InstantHelpManager getInstantHelpManager()
	{
		return m_instantHelpManager;
	}

	/**
	 * Retourne le nom de la propriété qui a été modifiée
	 * 
	 * @return
	 */
	public String getPropertyName()
	{
		return m_propertyName;
	}

	/**
	 * Retourne la nouvelle valeur de la propriété qui a été modifiée
	 * 
	 * @return
	 */
	public Object getPropertyValue()
	{
		return m_propertyValue;
	}

	/**
	 * Retourne le modèle dont une propriété a été modifiée
	 * 
	 * @return
	 */
	public ValueObjectModel getVom()
	{
		return m_vom;
	}
}