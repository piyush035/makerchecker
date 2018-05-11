package com.rennover.client.framework.widget.field;

/**
 * Cette classe gère un champs de type booléen avec des RadioButtons
 * 
 */
public class BooleanRadioButtonField extends RadioButtonField
{
	/**
	 * Contructeur avec les items "oui" et "non" alignés à l'horizontale.
	 */
	public BooleanRadioButtonField()
	{
		this(HORIZONTAL);
	}

	/**
	 * Contructeur avec les items "oui" et "non" alignés selon l'orientation en
	 * paramètre.
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
	 * Constructeur avec un item réprésentant le choix indifférent. Les items
	 * sont alignés à l'horizontale.
	 * 
	 * @param nullValueLabel
	 *            label de l'item "indifférent"
	 */
	public BooleanRadioButtonField(String nullValueLabel)
	{
		super(HORIZONTAL);
		super.addRadioButton("oui", Boolean.TRUE);
		super.addRadioButton("non", Boolean.FALSE);
		super.addRadioButtonForNull(nullValueLabel);
	}

	/**
	 * Constructeur avec redéfinition des labels des items. Les items sont
	 * alignés à l'horizontale. Si "nullValueLabel" est null, l'item
	 * "indifférent" n'apparaît pas.
	 * 
	 * @param trueLabel
	 *            label de l'item "oui"
	 * @param falseLabel
	 *            label de l'item "non"
	 * @param nullValueLabel
	 *            label de l'item "indifférent"
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