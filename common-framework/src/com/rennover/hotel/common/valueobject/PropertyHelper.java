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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.rennover.hotel.common.exception.IncoherenceException;

/**
 * Cette classe propose une gestion unique des objets nuls.
 * 
 * @author ddreistadt
 */
public final class PropertyHelper
{
	/** Représente une valeur nulle pour le type de base int */
	public static final int NULL_INT = Integer.MIN_VALUE;

	/** Représente une valeur nulle pour le type de base long */
	public static final long NULL_LONG = Long.MIN_VALUE;

	/** Représente une valeur nulle pour le type de base float */
	public static final float NULL_FLOAT = Float.MIN_VALUE;

	/** Représente une valeur nulle pour le type de base double */
	public static final double NULL_DOUBLE = Double.MIN_VALUE;

	/** Représente une valeur nulle pour le type de base int */
	public static final String EMPTY_STRING = "";

	/*
	 * This method will return an empty string if the passed argument is null
	 * otherwise will return the argument that is passed
	 */
	public static final String nullToEmpty(String property)
	{
		return property == null ? EMPTY_STRING : property;
	}

	/**
	 * Fonction simple permettant de valider si une chaîne est vide ou non
	 */
	public static final boolean isNull(String s)
	{
		return (s == null || s.equals(EMPTY_STRING));
	}

	/**
	 * Fonction simple permettant de valider si un Object est nul ou non
	 */
	public static final boolean isNull(Object obj)
	{
		if (obj instanceof String)
		{
			return isNull((String) obj);
		}

		return (obj == null);
	}

	/**
	 * Fonction simple permettant de valider si un entier est "nul"
	 */
	public static final boolean isNull(int i)
	{
		return (i == NULL_INT);
	}

	/**
	 * Fonction simple permettant de valider si un long est "nul"
	 */
	public static final boolean isNull(long l)
	{
		return (l == NULL_LONG);
	}

	/**
	 * Fonction simple permettant de valider si un float est "nul"
	 */
	public static final boolean isNull(float f)
	{
		return (f == NULL_FLOAT);
	}

	/**
	 * Fonction simple permettant de valider si un double est "nul"
	 */
	public static final boolean isNull(double d)
	{
		return (d == NULL_DOUBLE);
	}

	// ///////////////////////////////
	// /// Static equals methods /////
	// ///////////////////////////////

	public static boolean equals(Object value1, Object value2)
	{
		if (isNull(value1) && isNull(value2))
		{
			return true;
		} else if (isNull(value1) || isNull(value2))
		{
			return false;
		} else
		{
			return value1.equals(value2);
		}
	}

	public static boolean equals(byte value1, byte value2)
	{
		return value1 == value2;
	}

	public static boolean equals(short value1, short value2)
	{
		return value1 == value2;
	}

	public static boolean equals(int value1, int value2)
	{
		return value1 == value2;
	}

	public static boolean equals(long value1, long value2)
	{
		return value1 == value2;
	}

	public static boolean equals(double value1, double value2)
	{
		return value1 == value2;
	}

	public static boolean equals(float value1, float value2)
	{
		return value1 == value2;
	}

	public static boolean equals(char value1, char value2)
	{
		return value1 == value2;
	}

	public static boolean equals(boolean value1, boolean value2)
	{
		return value1 == value2;
	}

	/**
	 * Teste si la collection est composée d'ObjectIds. Lève une exception dans
	 * le cas contraire.
	 */
	public static final void checkObjectIdCollection(Collection collection)
	{
		try
		{
			if (!(collection.iterator().next() instanceof ObjectId))
			{
				throw new IncoherenceException("Les objets de la collection ne sont pas de type ObjectId");
			}
		} catch (NullPointerException nsee)
		{
			throw new IncoherenceException("La collection est null");
		} catch (NoSuchElementException nsee)
		{
			// traitement particulier pour les deltalist
			// si elementdata.size = 0 alors on s'occupe des deletedelements
			if (collection instanceof DeltaList && ((DeltaList) collection).getDeletedElementList() != null)
			{
				checkObjectIdCollection(((DeltaList) collection).getDeletedElementList());
			}
		}
	}

	/**
	 * Teste si la collection est composée de ValueObject. Lève une exception
	 * dans le cas contraire.
	 */
	public static final void checkValueObjectCollection(Collection collection)
	{
		try
		{
			// Commmented code removed
			// En attendant que ObjectId n'hérite plus de ValueObject
			if (!(collection.iterator().next() instanceof ValueObject)
			        || (collection.iterator().next() instanceof ObjectId))
			{
				throw new IncoherenceException("Les objets de la collection ne sont pas de type ValueObject");
			}
		} catch (NullPointerException nsee)
		{
			throw new IncoherenceException("La collection est null");
		} catch (NoSuchElementException nsee)
		{
			// traitement particulier pour les deltalist
			// si elementdata.size = 0 alors on s'occupe des deletedelements
			if (collection instanceof DeltaList && ((DeltaList) collection).getDeletedElementList() != null)
			{
				checkValueObjectCollection(((DeltaList) collection).getDeletedElementList());
			}
		}
	}

	/**
	 * Teste si la collection est composée de GraphObject. Lève une exception
	 * dans le cas contraire.
	 */
	public static final void checkGraphObjectCollection(Collection collection)
	{
		try
		{
			if (!(collection.iterator().next() instanceof GraphObject))
			{
				throw new IncoherenceException("Les objets de la collection ne sont pas de type GraphObject");
			}
		} catch (NullPointerException nsee)
		{
			throw new IncoherenceException("La collection est null");
		} catch (NoSuchElementException nsee)
		{
			// traitement particulier pour les deltalist
			// si elementdata.size = 0 alors on s'occupe des deletedelements
			if (collection instanceof DeltaList && ((DeltaList) collection).getDeletedElementList() != null)
			{
				checkGraphObjectCollection(((DeltaList) collection).getDeletedElementList());
			}
		}
	}

	/**
	 * Teste si la map est composée d'ObjectIds. Lève une exception dans le cas
	 * contraire.
	 */
	public static final void checkObjectIdMap(Map map)
	{
		try
		{
			if (!(map.keySet().iterator().next() instanceof ObjectId))
			{
				throw new IncoherenceException("Les objets de la mape sont pas de type ObjectId");
			}
		} catch (NullPointerException nsee)
		{
			throw new IncoherenceException("La map est null");
		} catch (NoSuchElementException nsee)
		{
			// ne rien faire lorsque la collection est vide
		}
	}

	/**
	 * Teste si la map est composée de ValueObject. Lève une exception dans le
	 * cas contraire.
	 */
	public static final void checkValueObjectMap(Map map)
	{
		try
		{
			if (!(map.entrySet().iterator().next() instanceof ValueObject))
			{
				throw new IncoherenceException("Les objets de la map ne sont pas de type ValueObject");
			}
		} catch (NullPointerException nsee)
		{
			throw new IncoherenceException("La map est null");
		} catch (NoSuchElementException nsee)
		{
			// ne rien faire lorsque la collection est vide
		}
	}

	/**
	 * Teste si la map est composée de GraphObject. Lève une exception dans le
	 * cas contraire.
	 */
	public static final void checkGraphObjectMap(Map map)
	{
		try
		{
			if (!(map.entrySet().iterator().next() instanceof GraphObject))
			{
				throw new IncoherenceException("Les objets de la map ne sont pas de type GraphObject");
			}
		} catch (NullPointerException nsee)
		{
			throw new IncoherenceException("La map est null");
		} catch (NoSuchElementException nsee)
		{
			// ne rien faire lorsque la collection est vide
		}
	}

	/**
	 * Retourne les ObjectId des objets Identifiable de la Map passés en
	 * paramêtre.
	 * 
	 * @param persistentObjectMap
	 *            map d'objets de type Persistent
	 */
	public static final List getObjectIdList(Map identifiableMap)
	{
		if (identifiableMap != null)
		{
			List objectIdList = new ArrayList(identifiableMap.size());
			Iterator iterator = identifiableMap.values().iterator();
			while (iterator.hasNext())
			{
				objectIdList.add(((Identifiable) iterator.next()).getObjectId());
			}
			return objectIdList;
		} else
		{
			return null;
		}
	}

	/**
	 * Retourne une map avec les ObjectId en clés et les Persistent en valeurs.
	 * 
	 * @param persistentObjectList
	 *            liste d'objets de type Persistent
	 */
	public static final Map mapPersistentObjectList(Collection identifiableCollection)
	{
		if (identifiableCollection != null)
		{
			Map objectMap = new HashMap(identifiableCollection.size());
			Iterator iterator = identifiableCollection.iterator();
			Identifiable persistentObject;
			while (iterator.hasNext())
			{
				persistentObject = (Identifiable) iterator.next();

				objectMap.put(persistentObject.getObjectId(), persistentObject);
			}
			return objectMap;
		} else
		{
			return null;
		}
	}

	/**
	 * Retourne les ObjectId des objets Graph de la List passés en paramêtre.
	 * 
	 * @param graphObjectList
	 *            liste d'objets de type GraphObject wrappant des objets
	 *            Persistent
	 */
	public static final List getObjectIdGraphList(Collection graphObjectCollection)
	{
		if (graphObjectCollection != null)
		{
			List objectIdList = new ArrayList(graphObjectCollection.size());
			Iterator iterator = graphObjectCollection.iterator();
			GraphObject graphObject;
			while (iterator.hasNext())
			{
				graphObject = (GraphObject) iterator.next();
				objectIdList.add(((Identifiable) graphObject.getWrappedObject()).getObjectId());
			}
			return objectIdList;
		} else
		{
			return null;
		}
	}

	/**
	 * Retourne les Object wrappés des objets Graph de la List passés en
	 * paramêtre.
	 * 
	 * @param graphObjectList
	 *            liste d'objets de type GraphObject wrappant des objets
	 *            Persistent
	 */
	public static final List getWrappedObjectList(Collection graphObjectCollection)
	{
		if (graphObjectCollection != null)
		{
			List objectList = new ArrayList(graphObjectCollection.size());
			Iterator iterator = graphObjectCollection.iterator();
			GraphObject graphObject;
			while (iterator.hasNext())
			{
				graphObject = (GraphObject) iterator.next();
				objectList.add(graphObject.getWrappedObject());
			}
			return objectList;
		} else
		{
			return null;
		}
	}

	public static Object getIdValue(ObjectId oid)
	{
		if (null != oid)
		{
			return oid.getIdValue();
		} else
		{
			return null;
		}
	}

	/**
	 * @param graphObjectList
	 *            Doit forcément être une liste de GraphObject
	 * @return le graphe qui encapsule le VO passé en paramètre. Ce graphe doit
	 *         faire partie de la liste graphObjectList Si la liste (ou le VO)
	 *         est null, alors on renvoie null
	 */
	public static GraphObject getGraphObjectByValueObject(ValueObject vo, List graphObjectList)
	{
		if (null != graphObjectList && null != vo)
		{
			if (graphObjectList.size() > 0 && !(graphObjectList.get(0) instanceof GraphObject))
			{
				throw new IncoherenceException("La liste passée en paramètre doit contenir des GraphObjects");
			}

			int i = -1;
			for (i = graphObjectList.size() - 1; i >= 0
			        && !vo.equals(((GraphObject) graphObjectList.get(i)).getWrappedObject()); i--)
			{
				;
			}

			if (i >= 0 && vo.equals(((GraphObject) graphObjectList.get(i)).getWrappedObject()))
			{
				return (GraphObject) graphObjectList.get(i);
			} else
			{
				throw new IncoherenceException("GraphObject non trouvé " + vo);
			}
		}

		return null;
	}
}