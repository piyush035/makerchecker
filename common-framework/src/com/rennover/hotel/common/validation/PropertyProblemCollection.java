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
 * ...
 * 
 * @author dattias
 */
public interface PropertyProblemCollection
{
	public void addPropertyProblem(String propertyName, Class validatorClass, boolean warning);

	public void addPropertyProblemWithMessage(String specificMessage, String propertyName, Class validatorClass,
	        boolean warning);
}