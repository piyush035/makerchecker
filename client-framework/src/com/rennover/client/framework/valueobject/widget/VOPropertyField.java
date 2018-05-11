package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.widget.field.InputField;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface VOPropertyField extends InputField
{
	/**
	 * deconnecte le VOPropertyField de son ValueObjectModel
	 */
	public void disconnectFromModel();
}