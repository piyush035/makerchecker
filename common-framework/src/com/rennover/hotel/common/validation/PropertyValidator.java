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
 * Super-classe des validateurs de base. Ces derniers n'op�rent que sur 
 * des propri�t�s de type "simple".
 * </p>
 * <p>
 * Les {@link com.rennover.hotel.common.valueobject.ValueObject} ont leur propre hi�rarchie de validateurs (cf. {@link com.rennover.hotel.common.validation.ValueObjectValidator})
 * </p>
 */
public abstract class PropertyValidator
{
	/**
	 * Valide une r�gle simple sur une propri�t�. La valeur pass�e ne peut pas
	 * �tre nulle.
	 * 
	 * @param propertyName Nom de la propri�t� test�e
	 * @param propertyValue Valeur non nulle de la propri�t�
	 * @param problems Collection des probl�mes
	 */
	public abstract void validate(String propertyName, Object value, PropertyProblemCollection response);

	/**
	 * Retourne un message indiquant que la valeur de cette propri�t� a un type
	 * ill�gal. Ce message doit �tre utilis� comme message d'une
	 * IllegalArgumentException.
	 * 
	 * @param propertyName le nom de la propri�t�
	 * @param value la valeur de la propri�t�
	 * @return le message
	 */
	protected static String createIllegalTypeMessage(String propertyName, Object value)
	{
		return "Ce validateur est incompatible avec le type de la propri�t� � valider, property name = " + propertyName
		        + ", value type = " + value.getClass().getName();
	}
}