
package com.rennover.client.framework.window;

import java.awt.Color;

/**
 * @author tcaboste
 * 
 * Cet objet contient l'ensemble des couleurs définissables dans le look&feel
 * Alloy. Cet objet sert à paramétrer le changement du style Alloy
 * 
 */
public class AlloyCustomColorSet implements Cloneable
{
	public Color m_primary = new Color(144, 225, 208); // vert : scroll bars,
														// progress bars, frame
														// décorations

	public Color m_secondary = new Color(236, 234, 230); // gris : panels,
														// boutons, autres
														// backgrounds

	public Color m_arrowButton = new Color(158, 181, 158);

	public Color m_frame = new Color(235, 250, 235);

	public Color m_desktop = new Color(236, 234, 230); // comme secondary color
														// : uniquement pour le
														// background des
														// JDesktopPane

	public Color m_selection = new Color(51, 183, 156); // vert foncé :
														// sélections des radio
														// boutons, des
														// check-box

	public Color m_rollover = new Color(95, 211, 188); // vert foncé : rollover
														// sur les boutons, les
														// radio boutons, les
														// check-box

	public Color m_highlight = new Color(202, 255, 233); // vert clair : textes
														// sélectionnés et menu
														// sélectionnés

	public Object clone()
	{
		AlloyCustomColorSet clone = new AlloyCustomColorSet();

		clone.m_primary = this.m_primary;
		clone.m_secondary = this.m_secondary;
		clone.m_arrowButton = this.m_arrowButton;
		clone.m_frame = this.m_frame;
		clone.m_desktop = this.m_desktop;
		clone.m_selection = this.m_selection;
		clone.m_rollover = this.m_rollover;
		clone.m_highlight = this.m_highlight;

		return clone;
	}
}