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
	 * Cr�e un panneau horizontal vide dont les boutons seront align�s � droite.
	 */
	public ComponentPanel()
	{
		this(HORIZONTAL);
	}

	/**
	 * Cr�e un panneau horizontal vide dont les boutons seront align�s � droite.
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
	 * @deprecated use add(Component) Ajoute un bouton associ� � cette action �
	 *             la suite des autres boutons, s�par� par un espace standard
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
	 * Ajoute un espace de taille fixe � la suite des boutons d�j� existants.
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
	 * Ajoute un ressort qui va plaquer les premiers boutons sur le c�t� gauche
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