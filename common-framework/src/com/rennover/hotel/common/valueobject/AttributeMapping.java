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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.helper.ReflectionHelper;

public class AttributeMapping implements Cloneable
{
	private String attributeName;
	private String columnName;
	private Field field;
	private List containedFields;
	private Class referencedClass;
	private String useGetMethod;
	private String useSetMethod;
	private Method getMethod;
	private Method setMethod;
	private boolean readOnly;
	private boolean historisable = true;
	private boolean isPK;

	public void setAttributeName(String aName)
	{
		attributeName = aName;
	}

	public String getAttributeName()
	{
		return attributeName;
	}

	public void setColumnName(String aName)
	{
		columnName = aName;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setField(Field aField)
	{
		field = aField;
		field.setAccessible(true);
	}

	public void addContainedField(String fieldName)
						   throws NoSuchFieldException
	{
		Class containerClass;
		if (containedFields == null)
		{
			containedFields = new ArrayList();
			containerClass = this.getField().getType();
		}
		else
		{
			containerClass = ((Field) containedFields.get(containedFields.size() - 1)).getType();
		}
		Field containedField = ReflectionHelper.getField(containerClass, fieldName);
		containedField.setAccessible(true);
		containedFields.add(containedField);
	}

	public List getContainedFields()
	{
		return containedFields;
	}

	public Field getField()
	{
		return field;
	}

	public Class getReferencedClass()
	{
		return referencedClass;
	}

	public void setReferencedClass(Class aClass)
	{
		referencedClass = aClass;
	}

	public String getReferencedClassName()
	{
		return referencedClass.getName();
	}

	/**
 * Returns the useGetMethod.
 * 
 * @return String
 */
	public String getUseGetMethod()
	{
		return useGetMethod;
	}

	/**
 * Returns the useSetMethod.
 * 
 * @return String
 */
	public String getUseSetMethod()
	{
		return useSetMethod;
	}

	/**
 * Sets the useSetMethod.
 * 
 * @param useSetMethod The useSetMethod to set
 */
	public void setUseGetMethod(String useGetMethod)
	{
		this.useGetMethod = useGetMethod;
	}

	/**
 * Sets the useSetMethod.
 * 
 * @param useSetMethod The useSetMethod to set
 */
	public void setUseSetMethod(String useSetMethod)
	{
		this.useSetMethod = useSetMethod;
	}

	/**
 * Returns the getMethod.
 * 
 * @return Method
 */
	public Method getGetMethod()
	{
		return getMethod;
	}

	/**
 * Returns the setMethod.
 * 
 * @return Method
 */
	public Method getSetMethod()
	{
		return setMethod;
	}

	/**
 * Sets the getMethod.
 * 
 * @param getMethod The getMethod to set
 */
	public void setGetMethod(Method getMethod)
	{
		this.getMethod = getMethod;
		getMethod.setAccessible(true);
	}

	/**
 * Sets the setMethod.
 * 
 * @param setMethod The setMethod to set
 */
	public void setSetMethod(Method setMethod)
	{
		this.setMethod = setMethod;
		setMethod.setAccessible(true);
	}

	public boolean isReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(boolean readOnly)
	{
		this.readOnly = readOnly;
	}

	public boolean isHistorisable()
	{
		return historisable;
	}

	public void setHistorisable(boolean historisable)
	{
		this.historisable = historisable;
	}

	public String toString()
	{
		return "[Mapping : " + attributeName + " - " + columnName + "]";
	}

	public boolean isPK()
	{
		return isPK;
	}

	public void setPK(boolean isPK)
	{
		this.isPK = isPK;
	}

	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
			throw new TechnicalException(e);
		}
	}
}