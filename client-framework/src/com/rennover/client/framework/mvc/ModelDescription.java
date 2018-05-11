
package com.rennover.client.framework.mvc;

/**
 * 
 * Classe permettant de d�finir et d'identifier un mod�le VOM ou VOLM dans un
 * PanelModel
 * 
 * @author tcaboste
 * 
 */
public class ModelDescription
{
	private Class m_objectClass;

	private Boolean m_useClone; // s'il reste � null c'est la valeur pard�faut
								// du mod�le qui est utilis� (VOM->true,
								// VOLM->false)

	// removed the commented default constructor

	/**
	 * Constructeur avec la classe du valueObject � g�rer
	 * 
	 * @param objectClass
	 *            classe du valueObject � g�rer
	 */
	public ModelDescription(Class objectClass)
	{
		m_objectClass = objectClass;
	}

	/**
	 * Constructeur avec la classe du valueObject � g�rer
	 * 
	 * @param objectClass
	 *            classe du valueObject � g�rer
	 * @param useClone
	 *            indique si le clone est utilis�
	 */
	public ModelDescription(Class objectClass, boolean useClone)
	{
		m_objectClass = objectClass;
		m_useClone = Boolean.valueOf(useClone);
	}

	/**
	 * Retourne le type (classe d'objet) de la valeur d�crite
	 * 
	 * @return
	 */
	public Class getObjectClass()
	{
		return m_objectClass;
	}

	/**
	 * Retourne l'indication d'utiliser un clone dans le mod�le
	 * 
	 * @return
	 */
	public Boolean getUseClone()
	{
		return m_useClone;
	}
}