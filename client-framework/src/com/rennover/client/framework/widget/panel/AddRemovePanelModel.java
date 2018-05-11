package com.rennover.client.framework.widget.panel;

import java.util.ArrayList;
import java.util.List;

/**
 * Le modèle associé à un {@link
 * com.agefospme.nsicm.client.common.widget.AddRemovePanel}. Il est possible de
 * positionner la liste de données d'origine (d'où l'utilisateur va choisir les
 * éléments qui l'intéressent) mais aussi une préselection pour le choix de
 * l'utilisateur.
 */
public class AddRemovePanelModel
{
	private List m_sourceList = new ArrayList();

	private List m_destinationList = new ArrayList();

	public AddRemovePanelModel()
	{
	}

	public List getDestinationList()
	{
		return m_destinationList;
	}

	public List getSourceList()
	{
		return m_sourceList;
	}

	public void setDestinationList(List destinationList)
	{
		m_destinationList = new ArrayList(destinationList);
	}

	public void setSourceList(List sourceList)
	{
		m_sourceList = new ArrayList(sourceList);
	}
}