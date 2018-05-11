package com.rennover.client.framework.valueobject.instanthelp;

import java.util.EventListener;

/**
 * @author tcaboste
 * 
 * Interface permettant de recevoir les notifications d'un InstantHelpManager
 */
public interface InstantHelpListener extends EventListener
{
	/**
	 * méthode appelée lorsque la validité d'un InstantHelpManager a changé
	 * 
	 * @param e
	 *            informations sur l'évènement
	 */
	public void validityChanged(InstantHelpEvent e);

	/**
	 * méthode appelée lorsqu'une valeur d'un des modèles de
	 * l'InstantHelpManager a changé
	 * 
	 * @param e
	 *            informations sur l'évènement
	 */
	public void valueChanged(InstantHelpEvent e);
}