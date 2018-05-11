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

/**
 * <p>
 * Super-classe des validateurs de base. Ces derniers n'opèrent que sur 
 * des propriétés de type "simple".
 * </p>
 * <p>
 * Les {@link com.rennover.hotel.common.valueobject.ValueObject} ont leur propre hiérarchie de validateurs (cf. {@link com.rennover.hotel.common.validation.ValueObjectValidator})
 * </p>
 */
public abstract class PropertyValidator
{
	/**
	 * Valide une règle simple sur une propriété. La valeur passée ne peut pas
	 * être nulle.
	 * 
	 * @param propertyName Nom de la propriété testée
	 * @param propertyValue Valeur non nulle de la propriété
	 * @param problems Collection des problèmes
	 */
	public abstract void validate(String propertyName, Object value, PropertyProblemCollection response);

	/**
	 * Retourne un message indiquant que la valeur de cette propriété a un type
	 * illégal. Ce message doit être utilisé comme message d'une
	 * IllegalArgumentException.
	 * 
	 * @param propertyName le nom de la propriété
	 * @param value la valeur de la propriété
	 * @return le message
	 */
	protected static String createIllegalTypeMessage(String propertyName, Object value)
	{
		return "Ce validateur est incompatible avec le type de la propriété à valider, property name = " + propertyName
		        + ", value type = " + value.getClass().getName();
	}
}