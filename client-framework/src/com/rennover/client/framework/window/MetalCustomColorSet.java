
package com.rennover.client.framework.window;

import java.awt.Color;

/**
 * @author tcaboste
 * 
 * Cet objet contient l'ensemble des couleurs définissables dans le look&feel
 * Alloy. Cet objet sert à paramétrer le changement du style Alloy
 * 
 */
public class MetalCustomColorSet implements Cloneable
{

	public Color m_primary1 = new Color(102, 102, 153); // vert : scroll bars,
														// progress bars, frame
														// décorations

	public Color m_primary2 = new Color(153, 153, 204); // gris : panels,
														// boutons, autres
														// backgrounds

	public Color m_primary3 = new Color(204, 204, 255);

	public Color m_secondary1 = new Color(102, 102, 102);

	public Color m_secondary2 = new Color(153, 153, 153); // comme secondary color
														// : uniquement pour le
														// background des
														// JDesktopPane

	public Color m_secondary3 = new Color(204, 204, 204); // vert foncé :
														// sélections des radio
														// boutons, des
														// check-box

	public Object clone()
	{
		MetalCustomColorSet clone = new MetalCustomColorSet();

		clone.m_primary1 = this.m_primary1;
		clone.m_primary2 = this.m_primary2;
		clone.m_primary3 = this.m_primary3;
		clone.m_secondary1 = this.m_secondary1;
		clone.m_secondary2 = this.m_secondary2;
		clone.m_secondary3 = this.m_secondary3;

		return clone;
	}
}