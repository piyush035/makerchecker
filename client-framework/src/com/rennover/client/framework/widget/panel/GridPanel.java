package com.rennover.client.framework.widget.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.rennover.client.framework.widget.base.ZPanel;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class GridPanel extends ZPanel
{
	private Insets m_defaultInsets = new Insets(0, 0, 0, 0);

	/**
	 * Constructor for GridPanel.
	 */
	public GridPanel(int nbRows, int nbColumns, int hgap, int vgap)
	{
		super(new GridBagLayout());
	}

	public void addComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight)
	{
		GridBagConstraints gb;
		gb = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1, 1, GridBagConstraints.NORTHWEST,
		        GridBagConstraints.BOTH, m_defaultInsets, 0, 0);
		add(component, gb);
	}

	public void addComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight, int fill)
	{
		GridBagConstraints gb;
		gb = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1, 1, GridBagConstraints.NORTHWEST, fill,
		        m_defaultInsets, 0, 0);
		add(component, gb);
	}

	public void addComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight, int weightx,
	        int weighty, int fill)
	{
		GridBagConstraints gb;
		gb = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty,
		        GridBagConstraints.NORTHWEST, fill, m_defaultInsets, 0, 0);
		add(component, gb);
	}

	/**
	 * Returns the defaultInsets.
	 * 
	 * @return Insets
	 */
	public Insets getDefaultInsets()
	{
		return m_defaultInsets;
	}

	/**
	 * Sets the defaultInsets.
	 * 
	 * @param defaultInsets
	 *            The defaultInsets to set
	 */
	public void setDefaultInsets(Insets defaultInsets)
	{
		m_defaultInsets = defaultInsets;
	}
}