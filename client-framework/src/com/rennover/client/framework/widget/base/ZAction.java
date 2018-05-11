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
	 * Change la visibilité des composants representant l'action (bouton ou
	 * menu)
	 * 
	 * @param action
	 * @param visible
	 */
	public void setVisible(boolean visible);

	/**
	 * Indique l'état de visibilité de l'action (et de ses composants associés)
	 * 
	 * @param action
	 * @return
	 */
	public boolean isVisible();

	/**
	 * change le nom d'une action ainsi que le libellé des composants associés
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
	 * Change l'icône de l'action
	 * 
	 * @param action
	 * @param icon
	 */
	public void setIcon(Icon icon);

	/**
	 * Retourne l'icône de l'action
	 * 
	 * @param action
	 * @return
	 */
	public Icon getIcon();
}