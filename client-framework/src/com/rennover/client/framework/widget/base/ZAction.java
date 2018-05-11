package com.rennover.client.framework.widget.base;

import javax.swing.Action;
import javax.swing.Icon;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface ZAction extends Action
{
	/**
	 * Change la visibilit� des composants representant l'action (bouton ou
	 * menu)
	 * 
	 * @param action
	 * @param visible
	 */
	public void setVisible(boolean visible);

	/**
	 * Indique l'�tat de visibilit� de l'action (et de ses composants associ�s)
	 * 
	 * @param action
	 * @return
	 */
	public boolean isVisible();

	/**
	 * change le nom d'une action ainsi que le libell� des composants associ�s
	 * 
	 * @param action
	 * @param name
	 */
	public void setName(String name);

	/**
	 * Retourne le nom de l'action
	 * 
	 * @param action
	 * @return
	 */
	public String getName();

	/**
	 * Change l'ic�ne de l'action
	 * 
	 * @param action
	 * @param icon
	 */
	public void setIcon(Icon icon);

	/**
	 * Retourne l'ic�ne de l'action
	 * 
	 * @param action
	 * @return
	 */
	public Icon getIcon();
}