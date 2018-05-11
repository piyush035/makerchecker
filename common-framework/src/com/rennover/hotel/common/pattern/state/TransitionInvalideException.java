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
package com.rennover.hotel.common.pattern.state;

/**
 * Créée lorsque le client de l'objet cherche à lui appliquer une transition non
 * autorisée <BR>
 * par son diagramme d'états.
 */
public class TransitionInvalideException extends Exception
{
	public TransitionInvalideException(String uneTransition, Object objetUtilise)
	{
		super("La transition " + uneTransition + " sur " + objetUtilise.getClass().getName() + " est invalide");
	}
}