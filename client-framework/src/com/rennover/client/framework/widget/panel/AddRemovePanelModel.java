package com.rennover.client.framework.widget.panel;

import java.util.ArrayList;
import java.util.List;

/**
 * Le mod�le associ� � un {@link
 * com.agefospme.nsicm.client.common.widget.AddRemovePanel}. Il est possible de
 * positionner la liste de donn�es d'origine (d'o� l'utilisateur va choisir les
 * �l�ments qui l'int�ressent) mais aussi une pr�selection pour le choix de
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