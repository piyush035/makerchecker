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

/**
 * Interface pour les raisons d'inactivation d'un objet du système
 * On utilise une interface pour éviter les dépendances entre package car cette interface
 * est utilisée dans ValueObject et les MotifInactivation sont des objets administrables
 */
public interface DeactivationReason
{
	String CODE_PAS_DE_MOTIF = "PM";
	String CODE_SUPPRESSION_LOGIQUE = "SL";

	public String getCode();
}