/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.hotel.common.valueobject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Une liste capable de g�rer les objets a effacer, modifier ou ins�rer en base
 */
public class DeltaList extends ArrayList
{
	private List deletedElementList = new ArrayList();

	//Indique si la deltaList conserve, pour les suppressions, les objets entiers ou seulement leurs ids
	private boolean m_keepDeletedObject = false;

	/**
	* Cr�e une liste vide
	*/
	public DeltaList()
	{
		super();
	}

	public DeltaList(boolean keepDeletedObject)
	{
		super();
		m_keepDeletedObject = keepDeletedObject;
	}

	public DeltaList(int size)
	{
		super(size);
	}

	public DeltaList(int size, boolean keepDeletedObject)
	{
		super(size);
		m_keepDeletedObject = keepDeletedObject;
	}

	/**
	* Cr�e une liste � partir d'une liste existante.
	* La liste deletedElements de la nouvelle instance est vide.
	*/
	public DeltaList(List list)
	{
		super(list);
	}

	public DeltaList(List list, boolean keepDeletedObject)
	{
		super(list);
		m_keepDeletedObject = keepDeletedObject;
	}

	/**
	* r�cup�re la liste des objets � effacer (ces objets on �t� retir�s de la liste)
	* ces objets n'apparaisent pas dans la liste originale, ils sont stosk�s uniquement dans la liste 'deletedElements'.
	* Les objets impl�mentant l'interface HasObjectInfo ne sont pas stock�s dans deletedElements,
	* seul l'ObjectInfo correspondant aux instances �ffac�es est conserv�.
	*/
	public List getDeletedElementList()
	{
		return deletedElementList;
	}

	public Object remove(int index)
	{
		Object deleted = super.remove(index);
		if (!m_keepDeletedObject && deleted instanceof HasObjectInfo)
		{
			deletedElementList.add(((HasObjectInfo) deleted).getObjectInfo());
		}
		else
		{
			deletedElementList.add(deleted);
		}
		return deleted;
	}

	public void clear()
	{
		super.clear();
		deletedElementList.clear();
	}

	/**
	* Ajoute un �l�ment dans la liste des �l�ments effac�s. <BR>
	* Equivalent � : <BR>
	* <code>
	* maListe.add(deletedElement);
	* maListe.remove(deletedElement);
	* </code>
	*/
	public void addToDeletedElements(Object deletedElement)
	{
		if (!m_keepDeletedObject && deletedElement instanceof HasObjectInfo)
		{
			deletedElementList.add(((HasObjectInfo) deletedElement).getObjectInfo());
		}
		else
		{
			deletedElementList.add(deletedElement);
		}
	}

	/**
	* Renvoie une DeltaAwareList ne contenant que les objets qui n�cessitent des �critures en base.
	* (les objets cr��s, modifi�s ou effac�s)
	* Typiquement, cette liste est a renvoyer au serveur apres modifications sur le client
	* afin de sauvegarder en base les modifications.
	*/
	public DeltaList getDeltaOnly()
	{
		DeltaList result = new DeltaList(m_keepDeletedObject);
		result.deletedElementList.clear();
		result.deletedElementList.addAll(deletedElementList);
		Iterator iter = this.iterator();
		while (iter.hasNext())
		{
			Object element = (Object) iter.next();
			if (element instanceof StateManaged)
			{
				StateManaged smElement = (StateManaged) element;
				if (smElement.isNewInBase() || smElement.isDirty())
				{
					result.add(smElement);
				}
			}
			else
			{
				result.add(element);
			}
		}
		return result;
	}

	/**
	* Copie le contenu de la liste pass�e en param�tre dans la deltaList.
	* Si la liste pass�e en param�tre est une delatList, les deletedElements sont aussi copi�s (si possible).
	*/
	public void copyFrom(List list)
	{
		this.clear();

		if (null != list)
		{
			this.addAll(list);
			if (list instanceof DeltaList && m_keepDeletedObject == ((DeltaList) list).m_keepDeletedObject)
			{
				//Il faut que les 2 listes g�rent de la m�me fa�on les �l�ments supprim�s
				this.deletedElementList.clear();
				this.deletedElementList.addAll(((DeltaList) list).deletedElementList);
			}
		}
	}

	public boolean isKeepDeletedObject()
	{
		return m_keepDeletedObject;
	}
}
