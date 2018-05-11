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
package com.rennover.hotel.common.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rennover.hotel.common.valueobject.Identifiable;
import com.rennover.hotel.common.valueobject.Inactivable;
import com.rennover.hotel.common.valueobject.ValueObject;


public abstract class CollectionHelper
{
    /**
     * Indique si une collection est null ou vide.
     *
     * @param c
     */
    public static boolean isNullOrEmpty(Collection c)
	{
		if (c == null)
		{
			return true;
		}

		return c.isEmpty();
	}

    /**
	 * Ajoute un élement à la collection si et seulement si cet élément est non
	 * nul.
	 * 
	 * @param c
	 * @param element
	 */
    public static void addIfNotNull(Collection c, Object element)
	{
		if (element != null)
		{
			c.add(element);
		}
	}

    /**
	 * Ajoute les éléments d'une collection à une autre collection si et
	 * seulement si cette collection est non nulle.
	 */
    public static void addAllIfNotNull(Collection dest, Collection src)
	{
		if (src != null)
		{
			dest.addAll(src);
		}
	}

    public static Collection merge(Collection dest, Collection src)
	{
		if (src == null)
		{
			return dest;
		}

		if (dest == null)
		{
			return src;
		}

		dest.addAll(src);

		return dest;
	}

    /**
	 * @todo (dattias, le 3 déc. 2003) : supprimer cette méthode, elle fait
	 *       doublon avec isNullOrEmpty(Collection).
	 * 
	 * @deprecated replaced by CollectionHelper.isNullOrEmpty(Collection)
	 */
    public static boolean hasNoElement(Collection collection)
	{
		if (collection == null)
		{
			return true;
		}

		if (collection.isEmpty())
		{
			return true;
		}

		return false;
	}

    /**
	 * Transforme une collection d'objets Identifiable (qui possède un ObjectId)
	 * en une Map dont les clés correspondent aux ObjectId des éléments de la
	 * liste
	 */
    public static Map toMap(Collection identifiables)
	{
		if (identifiables == null)
		{
			return null;
		}

		if (identifiables.isEmpty())
		{
			return new HashMap();
		}

		Map map = new Hashtable(identifiables.size());

		for (Iterator iter = identifiables.iterator(); iter.hasNext();)
		{
			Identifiable identifiable = (Identifiable) iter.next();
			map.put(identifiable.getObjectId(), identifiable);
		}

		return map;
	}

    /**
	 * Iterates through the collection, clones each value object in it and
	 * returns a the new collection with these value objects
	 * 
	 * @parameter : Collection valueObjects - collection containing the
	 *            instances of ValueObject
	 * @return : Collection containing the instances of cloned value obejects
	 */
    public static Collection clone(Collection valueObjects)
	{
		if (valueObjects == null)
		{
			return null;
		}

		if (valueObjects.isEmpty())
		{
			return new ArrayList(0);
		}

		ArrayList clones = new ArrayList(valueObjects.size());

		for (Iterator iter = valueObjects.iterator(); iter.hasNext();)
		{
			ValueObject cloneable = (ValueObject) iter.next();
			clones.add(cloneable.clone());
		}

		return clones;
	}

    /**
	 * Retourne la collection des objets passés en argument sous forme de map.
	 * Chaque objet est indexé par la valeur de la propriété spécifiée en
	 * paramètre.
	 */
    public static Map toMap(Collection collection, String propertyName)
	{
		if (collection == null)
		{
			return null;
		}

		if (collection.isEmpty())
		{
			return new HashMap();
		}

		Map map = new HashMap(collection.size());

		for (Iterator iter = collection.iterator(); iter.hasNext();)
		{
			Object element = iter.next();
			Object propertyValue = ReflectionHelper.getProperty(element, propertyName);
			map.put(propertyValue, element);
		}

		return map;
	}

    /**
	 * Supprime les doublons d'une liste.
	 * 
	 * Attention : cette méthode est basée sur le equals des objets de la liste.
	 * Deux objets forment un doublon si equals retourne vrai.
	 * 
	 */
    public static void removeDuplicates(List list)
	{
		if ((list != null) && !list.isEmpty())
		{
			Set set = new HashSet(list.size());

			for (Iterator iter = list.iterator(); iter.hasNext();)
			{
				Object element = iter.next();

				if (set.contains(element))
				{
					iter.remove();
				} else
				{
					set.add(element);
				}
			}
		}
	}

	public static List toObjectIdList(List identifiableList)
	{
		if (identifiableList == null)
		{
			return null;
		}

		if (identifiableList.isEmpty())
		{
			return new ArrayList(0);
		}

		List oidList = new ArrayList(identifiableList.size());

		for (Iterator iter = identifiableList.iterator(); iter.hasNext();)
		{
			oidList.add(((Identifiable) iter.next()).getObjectId());
		}

		return oidList;
	}

	public static Collection filterByClass(Collection collection, Class filterClass)
	{
		for (Iterator iter = collection.iterator(); iter.hasNext();)
		{
			if (!filterClass.equals(iter.next().getClass()))
			{
				iter.remove();
			}
		}

		return collection;
	}

	public static Collection filterActive(Collection collection)
	{
		for (Iterator iter = collection.iterator(); iter.hasNext();)
		{
			Object element = iter.next();

			if (element instanceof Inactivable)
			{
				if (!((Inactivable) element).isActif())
				{
					iter.remove();
				}
			}
		}

		return collection;
	}
}
