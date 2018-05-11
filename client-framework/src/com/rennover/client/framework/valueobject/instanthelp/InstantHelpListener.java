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
	 * m�thode appel�e lorsque la validit� d'un InstantHelpManager a chang�
	 * 
	 * @param e
	 *            informations sur l'�v�nement
	 */
	public void validityChanged(InstantHelpEvent e);

	/**
	 * m�thode appel�e lorsqu'une valeur d'un des mod�les de
	 * l'InstantHelpManager a chang�
	 * 
	 * @param e
	 *            informations sur l'�v�nement
	 */
	public void valueChanged(InstantHelpEvent e);
}