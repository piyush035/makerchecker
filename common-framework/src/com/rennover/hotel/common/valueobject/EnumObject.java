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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.helper.ReflectionHelper;

/**
 * Classe de base pour toutes les enumérations de données statiques métiers.
 * toutes les sous classes doivent fournir une méthode statique getInstance(char param).
 * Attention : la liste renvoyée par getAllInstances n'est pas serializable. En effet,
 * un EnumObject est déjà côté client donc n'a pas besoin d'être envoyé par le serveur.
 * Si cela est nécessaire, il faut encapsuler le retour de getAllInstances dans une Collection
 * serializable (par ex : ArrayList)
 * 
 * TODO (dattias, le 23 oct. 2003) : il faudra retirer l'héritage vers ValueObject
 * car les enum ne devrait pas utiliser les services de ValueObject et d'AbstractBean
 * comme le clonage, le firePropertyChange, ...
 */
public abstract class EnumObject extends ValueObject
	implements Identifiable
{
	////////////////////////////////////
	////// Enum Static Management //////
	////////////////////////////////////
	
	private static Map indexByClass = new HashMap();
	private Object m_code;
	private String m_description;

	protected EnumObject(char code)
	{
		this(new Character(code));
	}

	protected EnumObject(char code, String description)
	{
		this(new Character(code), description);
	}

	protected EnumObject(short code)
	{
		this(new Short(code));
	}

	protected EnumObject(short code, String description)
	{
		this(new Short(code), description);
	}

	protected EnumObject(int code)
	{
		this(new Integer(code));
	}

	protected EnumObject(int code, String description)
	{
		this(new Integer(code), description);
	}

	protected EnumObject(long code)
	{
		this(new Long(code));
	}

	protected EnumObject(long code, String description)
	{
		this(new Long(code), description);
	}

	protected EnumObject(Object code)
	{
		this(code, null);
	}

	protected EnumObject(Object code, String description)
	{
		if (code == null)
		{
			throw new IllegalArgumentException("code is null");
		}

		m_code = code;
		m_description = description;

		register(this);
	}

	public final Object getCode()
	{
		return m_code;
	}

	public String getDescription()
	{
		return m_description;
	}

	public final ObjectId getObjectId()
	{
		ObjectId oid = new ObjectId(this.getClass());
		oid.setKeyPart("id", m_code);
		return oid;		
	}

	protected final boolean equals2(ValueObject enum1)
	{
		return m_code.equals(((EnumObject) enum1).getCode());
	}

	public final int hashCode()
	{
		return m_code.hashCode();
	}

	public String toString()
	{
		if (m_description == null)
		{
			return m_code.toString();
		}
		else
		{
			return m_description;
		}
	}

	private static void register(EnumObject enum1)
	{
		Class aClass = enum1.getClass();
		Map indexByCode = (Map) indexByClass.get(aClass);

		if (indexByCode == null)
		{
			indexByCode = new HashMap();
			indexByClass.put(aClass, indexByCode);
		}

		Object oldValue = indexByCode.put(enum1.getCode(), enum1);

		if (oldValue != null)
		{
			throw new IllegalArgumentException("Enum code used twice, class = " + enum1.getClass() + ", code = " + enum1.getCode());
		}
	}

	public static EnumObject getInstance(Class aClass, Object code)
	{
		ReflectionHelper.initialize(aClass);
		Map indexByCode = (Map) indexByClass.get(aClass);

		if (indexByCode == null)
		{
			throw new IncoherenceException("Unknown enum class, class = " + aClass.getName());
		}

		EnumObject result = (EnumObject) indexByCode.get(code);

		if (result == null)
		{
			throw new IncoherenceException("Unknown enum code, class = " + aClass.getName() + ", code = " + code);
		}

		return result;
	}

	protected static Collection getAllInstances(Class aClass)
	{
		ReflectionHelper.initialize(aClass);
		return ((Map) indexByClass.get(aClass)).values();
	}
}