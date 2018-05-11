package com.rennover.client.framework.widget.table;

import java.util.List;

import com.rennover.client.framework.widget.base.ZTable;

public interface TablePanelController
{
	/**
	 * renvoie une liste d'Actions. le TablePanel associé contiendra un bouton
	 * pour chaque action
	 */
	public List getButtonActionList();

	/**
	 * permet au controller de garder une reference vers la table
	 */
	public void setTable(ZTable table);
}