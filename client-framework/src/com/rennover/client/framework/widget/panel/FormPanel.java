package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Action;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.rennover.client.framework.widget.base.ZButton;
import com.rennover.client.framework.widget.base.ZPanel;

/**
 * @author tcaboste
 * 
 * Le FormPanel est un panneau reprenant les même particularités du fieldPanel
 * en y ajoutant le fait de gérer un buttonpanel. Le buttonPanel est placé en
 * bas du panneau et il suffit d'ajouter un action au panneau pour qu'un boutton
 * associé à cette action y soit créé.
 * 
 */
public class FormPanel extends ZPanel
{
	private ButtonPanel m_buttonPanel = new ButtonPanel();

	public FormPanel(Component panel)
	{
		this(null, panel);
	}

	public FormPanel(String title, Component panel)
	{
		if (title != null)
		{
			setBorder(new TitledBorder(title));
		}

		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		m_buttonPanel.setBorder(new EmptyBorder(4, 4, 4, 4));
		add(m_buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre de boutons placé en bas
	 * du panneau
	 * 
	 * @param action
	 *            action associée au bouton
	 */
	public ZButton addButtonAction(Action action)
	{
		return m_buttonPanel.addButtonAction(action);
	}

	public ZButton addButtonAction(Action action, boolean lockable)
	{
		ZButton button = m_buttonPanel.addButtonAction(action);
		LockHelper.setLockable(button, lockable);
		return button;
	}

	public ZButton addButtonAction(String action)
	{
		return m_buttonPanel.addButtonAction(action);
	}

	public void addButtonSpring()
	{
		m_buttonPanel.addSpring();
	}
}