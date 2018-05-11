package com.rennover.client.framework.valueobject.model;

import java.util.EventListener;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface ValueObjectListModelListener extends EventListener
{
	/**
	 * Cette méthode est appelée lorsque le valueObject du valueObjectModel est
	 * changé
	 * 
	 * @param event
	 */
	void valueObjectListChanged(ValueObjectListModelEvent event);

	/**
	 * Cette méthode est appelée lorsque qu'un value object est ajouté à la
	 * liste
	 * 
	 * @param event
	 */
	void valueObjectAdded(ValueObjectListModelEvent event);

	/**
	 * Cette méthode est appelée lorsque qu'un value object est enlevé de la
	 * liste
	 * 
	 * @param event
	 */
	void valueObjectRemoved(ValueObjectListModelEvent event);

	/**
	 * Cette méthode est appelée lorsque qu'un value object est ajouté à la
	 * liste
	 * 
	 * @param event
	 */
	void valueObjectUpdated(ValueObjectListModelEvent event);
}