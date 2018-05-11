package com.rennover.client.framework.valueobject.model;

import java.util.EventListener;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface ValueObjectModelListener extends EventListener
{
	/**
	 * Cette méthode est appelée lorsque le valueObject du valueObjectModel est
	 * changé
	 * 
	 * @param event
	 */
	void valueObjectChanged(ValueObjectModelEvent event);

	/**
	 * Cette méthode est appelée lorsque qu'une propriété du valueObject du
	 * valueObjectModel est changée
	 * 
	 * @param event
	 */
	void valueObjectPropertyChanged(ValueObjectModelEvent event);
}