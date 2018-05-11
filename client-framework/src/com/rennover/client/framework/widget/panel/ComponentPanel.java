package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;

import com.rennover.client.framework.widget.base.ZBox;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.hotel.common.exception.Assert;

public class ComponentPanel extends ZPanel
{
	public static final int HORIZONTAL = 0;

	public static final int VERTICAL = 1;

	private ZBox m_internalBox;

	private int m_orientation;

	/**
	 * Crée un panneau horizontal vide dont les boutons seront alignés à droite.
	 */
	public ComponentPanel()
	{
		this(HORIZONTAL);
	}

	/**
	 * Crée un panneau horizontal vide dont les boutons seront alignés à droite.
	 * 
	 * @param orientation
	 *            soit {@link #HORIZONTAL}, soit {@link #VERTICAL}
	 */
	public ComponentPanel(int orientation)
	{
		Assert.check((HORIZONTAL == orientation) || (VERTICAL == orientation));
		m_orientation = orientation;
		setLayout(new BorderLayout());
		if (HORIZONTAL == m_orientation)
		{
			m_internalBox = new ZBox(BoxLayout.X_AXIS);
		} else
		{
			m_internalBox = new ZBox(BoxLayout.Y_AXIS);
		}
		super.add(m_internalBox, BorderLayout.CENTER);
	}

	public Component add(Component component)
	{
		addElement(component);
		return component;
	}

	/**
	 * @deprecated use add(Component) Ajoute un bouton associé à cette action à
	 *             la suite des autres boutons, séparé par un espace standard
	 *             (soit autour de 5 pixels).
	 */
	public void addElement(Component component)
	{
		m_internalBox.add(component);
	}

	public int getOrientation()
	{
		return m_orientation;
	}

	/**
	 * Ajoute un espace de taille fixe à la suite des boutons déjà existants.
	 */
	public void addStrut(int width)
	{
		if (HORIZONTAL == getOrientation())
		{
			addElement(Box.createHorizontalStrut(width));
		} else
		{
			addElement(Box.createVerticalStrut(width));
		}
	}

	public void addGlue()
	{
		if (HORIZONTAL == getOrientation())
		{
			addElement(Box.createHorizontalGlue());
		} else
		{
			addElement(Box.createVerticalGlue());
		}
	}

	/**
	 * Ajoute un ressort qui va plaquer les premiers boutons sur le côté gauche
	 * du panneau
	 */
	public void addSpring()
	{
		if (HORIZONTAL == getOrientation())
		{
			addElement(ZBox.createHorizontalGlue());
		} else
		{
			addElement(ZBox.createVerticalGlue());
		}
	}
}