package com.rennover.client.framework.widget.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.rennover.client.framework.widget.panel.LockHelper;

public class ZPanel extends JPanel
{
	private String m_title = "";

	public ZPanel()
	{
		init();
	}

	public ZPanel(LayoutManager layout)
	{
		super(layout);
		init();
	}

	public ZPanel(String panelTitle)
	{
		super();
		setTitle(panelTitle);
		init();
	}

	public ZPanel(String panelTitle, LayoutManager layout)
	{
		super(layout);
		setTitle(panelTitle);
		init();
	}

	private void init()
	{
	}

	/**
	 * 
	 * Ce constructeur permet de créer un panneau factice. Ce panneau permet de
	 * représenter rapidement les écrans qui ne sont pas encore créés. Ceci est
	 * utile lors des prototypages. Le panneau factice est blanc bordé de vert
	 * foncé avec le titre du panneau inscrit en son milieu.
	 * 
	 * @param panelTitle
	 *            Titre du panneau (qui apparaît au centre du panneau)
	 * @param useDraftMode
	 *            indique l'utilisation du mode brouillon
	 */
	public ZPanel(String panelTitle, boolean useDraftMode)
	{
		this(panelTitle);

		if (useDraftMode)
		{
			setLayout(new BorderLayout());
			setBackground(Color.WHITE); // fond en blanc
			setBorder(new LineBorder(Color.GREEN.darker().darker(), 4)); // bordure
																			// en
																			// vert
																			// foncé

			ZLabel label = new ZLabel(panelTitle);
			label.setHorizontalAlignment(ZLabel.CENTER); // justification
															// centré du label
			add(label, BorderLayout.CENTER); // label placé au centre du
												// panneau
		}
	}

	/**
	 * @return
	 */
	public String getTitle()
	{
		return m_title;
	}

	/**
	 * @param string
	 */
	public void setTitle(String title)
	{
		m_title = title;
		setName(title);
	}

	/**
	 * Modifie les contraintes pour positionner un composant
	 */
	public static final void updateGBConstraints(GridBagConstraints constraints, int gridx, int gridy, int gridwidth,
	        int gridheight, double weightx, double weighty)
	{
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
	}

	/**
	 * Modifie les contraintes pour positionner un composant
	 */
	public static final void updateGBConstraints(GridBagConstraints constraints, int gridx, int gridy, int gridwidth,
	        int gridheight, double weightx, double weighty, int fill)
	{
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.fill = fill;
	}

	public void setEnabled(boolean activate)
	{
		super.setEnabled(activate);
		LockHelper.setLocked(this, !activate);
	}
}