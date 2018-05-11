package com.rennover.client.framework.widget.field;

/**
 * Cette classe g�re un champs de type bool�en avec des RadioButtons
 * 
 */
public class BooleanRadioButtonField extends RadioButtonField
{
	/**
	 * Contructeur avec les items "oui" et "non" align�s � l'horizontale.
	 */
	public BooleanRadioButtonField()
	{
		this(HORIZONTAL);
	}

	/**
	 * Contructeur avec les items "oui" et "non" align�s selon l'orientation en
	 * param�tre.
	 * 
	 * @param orientation
	 *            orientation de l'alignement des items (HORIZONTAL ou VERTICAL)
	 */
	public BooleanRadioButtonField(int orientation)
	{
		super(orientation);
		super.addRadioButton("oui", Boolean.TRUE);
		super.addRadioButton("non", Boolean.FALSE);
	}

	/**
	 * Constructeur avec un item r�pr�sentant le choix indiff�rent. Les items
	 * sont align�s � l'horizontale.
	 * 
	 * @param nullValueLabel
	 *            label de l'item "indiff�rent"
	 */
	public BooleanRadioButtonField(String nullValueLabel)
	{
		super(HORIZONTAL);
		super.addRadioButton("oui", Boolean.TRUE);
		super.addRadioButton("non", Boolean.FALSE);
		super.addRadioButtonForNull(nullValueLabel);
	}

	/**
	 * Constructeur avec red�finition des labels des items. Les items sont
	 * align�s � l'horizontale. Si "nullValueLabel" est null, l'item
	 * "indiff�rent" n'appara�t pas.
	 * 
	 * @param trueLabel
	 *            label de l'item "oui"
	 * @param falseLabel
	 *            label de l'item "non"
	 * @param nullValueLabel
	 *            label de l'item "indiff�rent"
	 */
	public BooleanRadioButtonField(String trueLabel, String falseLabel, String nullValueLabel)
	{
		super(HORIZONTAL);
		super.addRadioButton(trueLabel, Boolean.TRUE);
		super.addRadioButton(falseLabel, Boolean.FALSE);
		if (nullValueLabel != null)
		{
			super.addRadioButtonForNull(nullValueLabel);
		}
	}

	/**
	 * modified by Ivega Offshore - to give an option to the user to set the
	 * orientation fix for defect #2450
	 * 
	 * @param trueLabel
	 * @param falseLabel
	 * @param nullValueLabel
	 * @param orientation
	 */
	public BooleanRadioButtonField(String trueLabel, String falseLabel, String nullValueLabel, int orientation)
	{
		super(orientation);
		super.addRadioButton(trueLabel, Boolean.TRUE);
		super.addRadioButton(falseLabel, Boolean.FALSE);
		if (nullValueLabel != null)
		{
			super.addRadioButtonForNull(nullValueLabel);
		}
	}
}