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
 * Une liste capable de gérer les objets a effacer, modifier ou insérer en base
 */
public class DeltaList extends ArrayList
{
	private List deletedElementList = new ArrayList();

	//Indique si la deltaList conserve, pour les suppressions, les objets entiers ou seulement leurs ids
	private boolean m_keepDeletedObject = false;

	/**
	* Crée une liste vide
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
	* Crée une liste à partir d'une liste existante.
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
	* récupère la liste des objets à effacer (ces objets on été retirés de la liste)
	* ces objets n'apparaisent pas dans la liste originale, ils sont stoskés uniquement dans la liste 'deletedElements'.
	* Les objets implémentant l'interface HasObjectInfo ne sont pas stockés dans deletedElements,
	* seul l'ObjectInfo correspondant aux instances éffacées est conservé.
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
	* Ajoute un élément dans la liste des éléments effacés. <BR>
	* Equivalent à : <BR>
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
	* Renvoie une DeltaAwareList ne contenant que les objets qui nécessitent des écritures en base.
	* (les objets créés, modifiés ou effacés)
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
	* Copie le contenu de la liste passée en paramètre dans la deltaList.
	* Si la liste passée en paramètre est une delatList, les deletedElements sont aussi copiés (si possible).
	*/
	public void copyFrom(List list)
	{
		this.clear();

		if (null != list)
		{
			this.addAll(list);
			if (list instanceof DeltaList && m_keepDeletedObject == ((DeltaList) list).m_keepDeletedObject)
			{
				//Il faut que les 2 listes gèrent de la même façon les éléments supprimés
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
