
package com.rennover.client.framework.mvc;

/**
 * 
 * Classe permettant de définir et d'identifier un modèle VOM ou VOLM dans un
 * PanelModel
 * 
 * @author tcaboste
 * 
 */
public class ModelDescription
{
	private Class m_objectClass;

	private Boolean m_useClone; // s'il reste à null c'est la valeur pardéfaut
								// du modèle qui est utilisé (VOM->true,
								// VOLM->false)

	// removed the commented default constructor

	/**
	 * Constructeur avec la classe du valueObject à gérer
	 * 
	 * @param objectClass
	 *            classe du valueObject à gérer
	 */
	public ModelDescription(Class objectClass)
	{
		m_objectClass = objectClass;
	}

	/**
	 * Constructeur avec la classe du valueObject à gérer
	 * 
	 * @param objectClass
	 *            classe du valueObject à gérer
	 * @param useClone
	 *            indique si le clone est utilisé
	 */
	public ModelDescription(Class objectClass, boolean useClone)
	{
		m_objectClass = objectClass;
		m_useClone = Boolean.valueOf(useClone);
	}

	/**
	 * Retourne le type (classe d'objet) de la valeur décrite
	 * 
	 * @return
	 */
	public Class getObjectClass()
	{
		return m_objectClass;
	}

	/**
	 * Retourne l'indication d'utiliser un clone dans le modèle
	 * 
	 * @return
	 */
	public Boolean getUseClone()
	{
		return m_useClone;
	}
}