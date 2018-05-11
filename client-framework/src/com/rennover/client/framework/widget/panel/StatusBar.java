package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.border.EmptyBorder;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZPanel;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StatusBar extends ZPanel
{
	private ZLabel m_lblMessageText;

	/**
	 * Construit un panel composé d'un JLabel et d'un fond jaune.
	 */
	public StatusBar()
	{
		setLayout(new BorderLayout());

		m_lblMessageText = new ZLabel()
		{
			public Dimension getPreferredSize()
			{
				Dimension prefSize = super.getPreferredSize();
				prefSize.width = 200;
				prefSize.height = getFontMetrics(getFont()).getHeight() + 4;
				return prefSize;
			}

			public void setText(String arg0)
			{
				super.setText(arg0);
				setToolTipText(arg0);
			}
		};

		m_lblMessageText.setBorder(new EmptyBorder(2, 2, 2, 2));
		m_lblMessageText.setOpaque(true);
		m_lblMessageText.setBackground(new Color(255, 255, 204));

		add(m_lblMessageText, BorderLayout.CENTER);

		setText("");
	}

	/**
	 * Permet d'afficher un message dans la barre
	 */
	public void setIcon(Icon icon)
	{
		m_lblMessageText.setIcon(icon);
	}

	/**
	 * Permet d'afficher un message dans la barre
	 */
	public void setText(String text)
	{
		m_lblMessageText.setText(text);
	}

	/**
	 * Permet de changer la couleur de fond du message
	 * 
	 * @param newColor
	 *            nouvelle couleur de fond
	 */
	public void setColor(Color newColor)
	{
		m_lblMessageText.setBackground(newColor);
	}
}