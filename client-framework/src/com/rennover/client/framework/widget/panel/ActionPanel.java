package com.rennover.client.framework.widget.panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.Action;
import javax.swing.JButton;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.button.SmallButton;
import com.rennover.client.framework.widget.layout.GridData;
import com.rennover.client.framework.widget.layout.SWTGridLayout;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ActionPanel extends ZPanel
{
	public static final int NORTH = GridBagConstraints.NORTH;

	public static final int NORTHEAST = GridBagConstraints.NORTHEAST;

	public static final int EAST = GridBagConstraints.EAST;

	public static final int SOUTHEAST = GridBagConstraints.SOUTHEAST;

	public static final int SOUTH = GridBagConstraints.SOUTH;

	public static final int SOUTHWEST = GridBagConstraints.SOUTHWEST;

	public static final int WEST = GridBagConstraints.WEST;

	public static final int NORTHWEST = GridBagConstraints.NORTHWEST;

	private JButton m_button;

	private Component m_component;

	public ActionPanel(Component component, Action action)
	{
		this(component, action, NORTHEAST);
	}

	public ActionPanel(Component component, Action action, int orientation)
	{
		compose(component, action, orientation);
	}

	public void compose(Component component, Action action, int orientation)
	{
		SWTGridLayout layout = new SWTGridLayout();
		setLayout(layout);
		layout.m_marginWidth = 0;
		layout.m_marginHeight = 0;
		layout.m_horizontalSpacing = 3;
		layout.m_verticalSpacing = 3;

		m_component = component;
		if (component == null)
		{
			component = new ZLabel(" ");
			layout.m_horizontalSpacing = 0;
			layout.m_verticalSpacing = 0;
		}

		Component buttonComponent;
		if (action != null)
		{
			m_button = new SmallButton(action);
			buttonComponent = m_button;
		} else
		{
			buttonComponent = new SmallPanel();
		}

		// Ajout du composant
		// Ajout du bouton contenant l'action associée au composant
		if (orientation == NORTH) // au dessus (à gauche)
		{
			// 1 colonne le bouton puis le composant
			layout.m_numColumns = 1;
			add(buttonComponent);
			add(component, new GridData(GridData.FILL_BOTH));
		} else if (orientation == NORTHEAST) // à droite en haut
		{
			// 2 colonnes composant puis bouton
			layout.m_numColumns = 2;
			add(component, new GridData(GridData.FILL_BOTH));
			GridData gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.CENTER;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			add(buttonComponent, gridData);
		} else if (orientation == SOUTHEAST) // à droite en bas
		{
			// 2 colonnes composant puis bouton
			layout.m_numColumns = 2;
			add(component, new GridData(GridData.FILL_BOTH));
			GridData gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.CENTER;
			gridData.m_verticalAlignment = GridData.END;
			add(buttonComponent, gridData);
		} else if (orientation == NORTHWEST) // à gauche en haut
		{
			// 2 colonnes bouton puis composant
			layout.m_numColumns = 2;
			GridData gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.CENTER;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			add(buttonComponent, gridData);
			add(component, new GridData(GridData.FILL_BOTH));
		} else if (orientation == SOUTHWEST) // à gauche en bas
		{
			layout.m_numColumns = 2;
			GridData gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.CENTER;
			gridData.m_verticalAlignment = GridData.END;
			add(buttonComponent, gridData);
			add(component, new GridData(GridData.FILL_BOTH));
		} else
		// NORTHEAST (à gauche en haut)
		{
			// 2 colonnes composant puis bouton
			layout.m_numColumns = 2;
			add(component, new GridData(GridData.FILL_BOTH));
			GridData gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.CENTER;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			add(buttonComponent, gridData);
		}
	}

	public void setEnabled(boolean enabled)
	{
		if (m_button != null)
		{
			m_button.setEnabled(enabled);
		}
		super.setEnabled(enabled);
	}

	/**
	 * @return
	 */
	public Component getComponent()
	{
		return m_component;
	}
}

class SmallPanel extends ZPanel
{
	public SmallPanel()
	{
		Dimension size = new Dimension(24, 22);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
	}
}