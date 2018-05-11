package com.rennover.client.framework.print;

import com.rennover.hotel.common.valueobject.ValueObject;

public class ColonneAImprimer extends ValueObject
{
	public static final String NOM = "nom";

	public static final String AIMPRIMER = "aImprimer";

	private String m_nom;

	private boolean m_aImprimer;

	public ColonneAImprimer(String nom)
	{
		m_nom = nom;
		m_aImprimer = true;
	}

	protected boolean equals2(ValueObject obj)
	{
		return false;
	}

	/**
	 * @return
	 */
	public boolean isAImprimer()
	{
		return m_aImprimer;
	}

	/**
	 * @return
	 */
	public String getNom()
	{
		return m_nom;
	}

	/**
	 * @param b
	 */
	public void setAImprimer(boolean b)
	{
		m_aImprimer = b;
	}

	/**
	 * @param string
	 */
	public void setNom(String string)
	{
		m_nom = string;
	}
}