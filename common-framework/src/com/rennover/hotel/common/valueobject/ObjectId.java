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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.helper.ReflectionHelper;

/**
 * Cette classe représente l'ID des objets stockés en base.  Toute instance persistante
 * doit avoir un et un seul ID.  Un ID identifie de facon unique une instance. La classe
 * ObjectId est immutable.
 */
public class ObjectId implements Serializable, Cloneable, Identifiable, Deletable
{
	public static final String ID_VALUE = "idValue";
	private Class m_objectClass;
	private Map m_objectId;

	public ObjectId(Class objectClass)
	{
		m_objectClass = objectClass;
		m_objectId = new HashMap();
	}
	
	/**
	 * Permet d'obtenir un clone d'un ObjectId en changeant la classe. utile dans le case par exemple des 
	 */
	public ObjectId getCloneForClass(Class newClass)
	{
		ObjectId oid = new ObjectId(newClass);
		oid.setId(this.getId());
		return oid;
	}

	public Object getIdValue()
	{
		Collection values = m_objectId.values();
		if (values.size() != 1)
		{
			throw new IncoherenceException("The objectId " + this + " should contain one element, not" + values.size());
		}
		else
		{
			return values.iterator().next();
		}
	}

	/**
	 * Renvoie la valeur de l'ID. cette valeur peut être un Wrapper de type de base
	 * (Integer, Long), une String ou d'autres types pour des sous classes d'ObjectId.
	 */
	Object getId()
	{
		return m_objectId;
	}

	protected void setId(Object id)
	{
		m_objectId = (Map) id;
	}

	public Object getKeyPart(String roleName)
	{
		return ((Map) getId()).get(roleName);
	}

	public void setKeyPart(String roleName, Object value)
	{
		if (value instanceof ObjectId)
		{
			value = ((ObjectId) value).getIdValue();
		}

		((Map) getId()).put(roleName, value);
	}

	public byte getByteKeyPart(String roleName)
	{
		return ((Byte) getKeyPart(roleName)).byteValue();
	}

	public short getShortKeyPart(String roleName)
	{
		return ((Short) getKeyPart(roleName)).shortValue();
	}

	public int getIntKeyPart(String roleName)
	{
		return ((Integer) getKeyPart(roleName)).intValue();
	}

	public long getLongKeyPart(String roleName)
	{
		return ((Long) getKeyPart(roleName)).longValue();
	}

	public boolean getBooleanKeyPart(String roleName)
	{
		return ((Boolean) getKeyPart(roleName)).booleanValue();
	}

	public char getCharKeyPart(String roleName)
	{
		return ((Character) getKeyPart(roleName)).charValue();
	}

	public float getFloatKeyPart(String roleName)
	{
		return ((Float) getKeyPart(roleName)).floatValue();
	}

	public double getDoubleKeyPart(String roleName)
	{
		return ((Double) getKeyPart(roleName)).doubleValue();
	}

	/**
	 * Renvoie le type de l'objet identifié par cet Id. Cette méthode peut en particulier
	 * être utile pour connaitre le type d'un objet référencé  puisque les réféérences
	 * entre objets persistants sont implémentées sous forme d'ObjectId.
	 */
	public Class getObjectType()
	{
		return m_objectClass;
	}

	public boolean isObjectTypeOf(Class objectClass)
	{
		return m_objectClass == objectClass;
	}

	public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof ObjectId)) return false;
		ObjectId oid2 = (ObjectId) obj;
		if (!oid2.getObjectType().isAssignableFrom(this.getObjectType()) 
			&& ! this.getObjectType().isAssignableFrom(oid2.getObjectType()))
		{
			return false;
		}
		return oid2.getId().equals(this.getId());
	}

	public int hashCode()
	{
		return m_objectId.hashCode();
	}

	public Map getIdMap()
	{
		return (Map) getId();
	}

	public String toString()
	{
		return "[ObjectId, " + "class=" + ReflectionHelper.getClassShortName(m_objectClass) + ", id=" + m_objectId + ']';
	}

	public ObjectId getObjectId()
	{
		return this;
	}
}