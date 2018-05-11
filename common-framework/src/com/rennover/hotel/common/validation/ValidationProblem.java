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
package com.rennover.hotel.common.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.rennover.hotel.common.helper.ReflectionHelper;

/**
 * Représente une non-conformité à une règle de validation.
 * 
 * @author    ddreistadt v1
 * @author    dattias v2 11/08/03
 */
public final class ValidationProblem
{
	private String m_message;

	private List m_propertyNameList;

	private boolean m_warning = false;

	private Class m_validatorClass;

	ValidationProblem(String message, List propertyNameList, Class validatorClass, boolean isWarning)
	{
		m_validatorClass = validatorClass;
		m_message = message;
		m_propertyNameList = propertyNameList;
		m_warning = isWarning;
	}

	Iterator getInvolvedPropertyNames()
	{
		return m_propertyNameList == null ? Collections.EMPTY_LIST.iterator() : m_propertyNameList.iterator();
	}

	List getInvolvedPropertyNameList()
	{
		return m_propertyNameList == null ? Collections.EMPTY_LIST : m_propertyNameList;
	}

	void addRootPropertyName(String propertyName)
	{
		if (m_propertyNameList != null)
		{
			ArrayList newList = new ArrayList(m_propertyNameList.size());

			for (Iterator iter = m_propertyNameList.iterator(); iter.hasNext();)
			{
				newList.add(propertyName + '.' + iter.next());
			}

			m_propertyNameList = newList;
		}
	}

	public boolean isPropertyProblem()
	{
		return (m_propertyNameList != null) && (!m_propertyNameList.isEmpty());
	}

	public boolean isPropertyInvolved(String propertyName)
	{
		if (m_propertyNameList != null)
		{
			return m_propertyNameList.contains(propertyName);
		}

		return false;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer(50);
		buffer.append('[').append(ReflectionHelper.getClassShortName(this.getClass()));
		buffer.append(", problem=").append(m_message);
		buffer.append(", warning=").append(m_warning);
		buffer.append(", property=").append(m_propertyNameList);
		return buffer.append(']').toString();
	}

	// ////////////////////////
	// /// public get/set /////
	// ////////////////////////
	public String getMessage()
	{
		return m_message;
	}

	public boolean isWarning()
	{
		return m_warning;
	}

	public Class getValidatorClass()
	{
		return m_validatorClass;
	}

	// ///////////////////////////////////
	// /// package protected get/set /////
	// ///////////////////////////////////
	void setMessage(String value)
	{
		m_message = value;
	}

	void setWarning(boolean value)
	{
		m_warning = value;
	}

	void setValidatorClass(Class value)
	{
		m_validatorClass = value;
	}

	List getPropertyNameList()
	{
		return m_propertyNameList;
	}

	void setPropertyNameList(List value)
	{
		m_propertyNameList = value;
	}
}