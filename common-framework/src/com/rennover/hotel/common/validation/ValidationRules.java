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
package com.rennover.hotel.common.validation;

/**
 * Règles de validation sur l'attribut
 */
public class ValidationRules
{
	// Indique si l'attribut est read only
	private boolean m_readOnly = false;

	// Indique si l'attribut est obligatoire
	private boolean m_mandatory = false;

	// Indique la valeur min de l'attribut
	private Comparable m_rangeMin;

	// Indique la valeur max de l'attribut
	private Comparable m_rangeMax;

	// Indique la taille min de l'attribut
	private Long m_sizeMin;

	// Indique la taille max de l'attribut
	private Long m_sizeMax;

	// Indique le nombre de décimales après la virgule
	private Long m_decimalDigits;

	// Indique le pattern que doit respecter l'attribut
	private String m_pattern;

	// Indique la description de l'attribut
	private String m_description;

	// Indique si l'attribut ne doit contenir que des majuscules (uniquement
	// pour les String)
	private boolean m_isUppercase;

	public void setMandatory(boolean mandatory)
	{
		m_mandatory = mandatory;
	}

	public void setPattern(String pattern)
	{
		m_pattern = pattern;
	}

	public void setRangeMax(Comparable rangeMax)
	{
		m_rangeMax = rangeMax;
	}

	public void setRangeMin(Comparable rangeMin)
	{
		m_rangeMin = rangeMin;
	}

	public void setDecimalDigits(Long decimalDigits)
	{
		m_decimalDigits = decimalDigits;
	}

	public void setSizeMax(Long sizeMax)
	{
		m_sizeMax = sizeMax;
	}

	public void setSizeMin(Long sizeMin)
	{
		m_sizeMin = sizeMin;
	}

	public boolean isMandatory()
	{
		return m_mandatory;
	}

	public String getPattern()
	{
		return m_pattern;
	}

	public Comparable getRangeMax()
	{
		return m_rangeMax;
	}

	public Comparable getRangeMin()
	{
		return m_rangeMin;
	}

	public Long getSizeMax()
	{
		return m_sizeMax;
	}

	public Long getSizeMin()
	{
		return m_sizeMin;
	}

	public boolean isReadOnly()
	{
		return m_readOnly;
	}

	public void setReadOnly(boolean readOnly)
	{
		m_readOnly = readOnly;
	}

	public String getDescription()
	{
		return m_description;
	}

	public void setDescription(String string)
	{
		m_description = string;
	}

	public boolean isUppercase()
	{
		return m_isUppercase;
	}

	public void setUppercase(boolean b)
	{
		m_isUppercase = b;
	}

	public Long getDecimalDigits()
	{
		return m_decimalDigits;
	}

}