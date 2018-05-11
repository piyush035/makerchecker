/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.hotel.common.idgen;

public class IdGenerator
{
	private static final int ID_PAGE_SIZE = 50;

	private static IdPage s_currentPage;

	private static IdPageGenerator s_pageGenerator;

	private static boolean s_unplugged = false;

	public static synchronized long getNextId()
	{
		if (s_unplugged)
		{
			return System.currentTimeMillis();
		}
		if (s_pageGenerator == null)
		{
			throw new IllegalStateException("IdGenerateur non initialisé : aucun IdPageGenerator présent");
		}
		if (s_currentPage == null || !s_currentPage.hasNext())
		{
			s_currentPage = s_pageGenerator.getNextPage(ID_PAGE_SIZE);
		}
		return s_currentPage.next();
	}

	/**
	 * Initialise le générateur d'id avec un générateur de page. Le générateur
	 * d'id ne fonctionne pas sans générateur de page sauf en mode unplugged.
	 */
	public static void init(IdPageGenerator pageGenerator)
	{
		s_pageGenerator = pageGenerator;
	}

	/**
	 * Returns the unplugged. En mode unplugged les ids sont générés à partir de
	 * l'horloge.
	 * 
	 * @return boolean
	 */
	public static boolean isUnplugged()
	{
		return s_unplugged;
	}

	/**
	 * Sets the unplugged. En mode unplugged les ids sont générés à partir de
	 * l'horloge.
	 * 
	 * @param unplugged
	 *            The unplugged to set
	 */
	public static void setUnplugged(boolean unplugged)
	{
		s_unplugged = unplugged;
	}
}