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

import java.util.List;

/**
 * ...
 * 
 * @author dattias
 */
public interface ObjectProblemCollection extends PropertyProblemCollection
{
	public void addObjectProblem(List propertyNameList, Class validatorClass, boolean warning);

	public void addObjectProblemWithMessage(String specificMessage, List propertyNameList, Class validatorClass,
	        boolean warning);

	public void addGlobalProblem(String specificMessage, Class validatorClass, boolean warning);
}