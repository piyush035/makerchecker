package com.rennover.client.framework.valueobject.model;

import java.util.EventListener;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface ValueModelListener extends EventListener
{
	/**
	 * Cette m�thode est appel�e lorsque que la propri�t� du valueObject du
	 * valueObjectModel est chang�e
	 * 
	 * @param event
	 */
	void valueChanged(ValueModelEvent event);
}