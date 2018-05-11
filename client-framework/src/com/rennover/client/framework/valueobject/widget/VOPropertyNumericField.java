package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.NumericField;
import com.rennover.hotel.common.validation.ValidationRules;

/**
 * @author tcaboste
 * 
 * Cette classe VOProperty d�rive de la classe NumericField charg�e de formater
 * la saisie de nombre avec ou sans virgule.
 * 
 * exemple: NumericField m_montant = new VOPropertyNumericField(reglementModel,
 * Reglement.MONTANT);
 * 
 * Ce champ permet de saisir le montant d'un r�glement. Le format est r�cup�r�
 * par � partir du m�tadata.
 * 
 * 
 * exemple: NumericField m_montant = new VOPropertyNumericField(reglementModel,
 * Reglement.MONTANT, Double.class, 7, 2);
 * 
 * Ce champ permet de saisir le montant d'un r�glement. La partie entier sera
 * limit�e � 5 chiffres (7-2) et la partie d�cimale � 2 chiffres. La valeur
 * retourn�e par le champ sera du type Double. La valeur est nulle si le champ
 * n'est pas un nombre
 * 
 */
public class VOPropertyNumericField extends NumericField implements VOPropertyField
{

	private VOPropertyNumericFieldEventHandler m_propertyEventHandler = new VOPropertyNumericFieldEventHandler(this);

	/**
	 * Constructeur simplifi� de la classe
	 * 
	 * @param valueObjectModel
	 *            mod�le de value associ� contenant le valueObject � g�rer
	 * @param propertyName
	 *            nom de la propri�t� g�r�e par ce champ
	 */
	public VOPropertyNumericField(ValueObjectModel vom, String propertyName)
	{
		this(vom, propertyName, true);
	}

	public VOPropertyNumericField(ValueObjectModel vom, PropertyPath propertyPath)
	{
		this(vom, propertyPath.toString());
	}

	/**
	 * @param reglementModel
	 * @param string
	 * @param b
	 */
	public VOPropertyNumericField(ValueObjectModel vom, String propertyName, boolean useNullValue)
	{
		super(getPropertyNumberClass(vom, propertyName), getPropertyLength(vom, propertyName, 7), getPropertyDecDigits(
		        vom, propertyName, 0), useNullValue);
		m_propertyEventHandler.setValueObjectModel(this, vom, propertyName);
	}

	public VOPropertyNumericField(ValueObjectModel vom, PropertyPath propertyPath, boolean useNullValue)
	{
		this(vom, propertyPath.toString(), useNullValue);
	}

	public static Class getPropertyNumberClass(ValueObjectModel valueObjectModel, String propertyName)
	{
		Class propertyType = valueObjectModel.getPropertyType(propertyName);
		return propertyType;
	}

	public static int getPropertyLength(ValueObjectModel valueObjectModel, String propertyName, int defaultValue)
	{
		ValidationRules rules = valueObjectModel.getPropertyValidationRules(propertyName);
		Long sizeMax = (rules == null) ? null : rules.getSizeMax();
		return (sizeMax == null) ? defaultValue : sizeMax.intValue();
	}

	public static int getPropertyDecDigits(ValueObjectModel valueObjectModel, String propertyName, int defaultValue)
	{
		ValidationRules rules = valueObjectModel.getPropertyValidationRules(propertyName);
		Long decDigits = (rules == null) ? null : rules.getDecimalDigits();
		return (decDigits == null) ? defaultValue : decDigits.intValue();
	}

	public VOPropertyNumericField(ValueModel valueModel, Class numberClass, int length, int nbdec)
	{
		super(numberClass, length, nbdec);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	/**
	 * Constructeur complet de la classe
	 * 
	 * @param valueObjectModel
	 *            mod�le de value associ� contenant le valueObject � g�rer
	 * @param propertyName
	 *            nom de la propri�t� g�r�e par ce champ
	 * @param numberClass
	 *            classe Number correspondant au type de num�rique
	 * @param length
	 *            nombre de chiffres maximum du nombre � saisir (sans la
	 *            virgule)
	 * @param nbdec
	 *            nombre de d�cimales significatives
	 */
	public VOPropertyNumericField(ValueObjectModel valueObjectModel, String propertyName, Class numberClass,
	        int length, int nbdec)
	{
		super(numberClass, length, nbdec);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyNumericField(ValueObjectModel valueObjectModel, PropertyPath propertyPath, Class numberClass,
	        int length, int nbdec)
	{
		super(numberClass, length, nbdec);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public VOPropertyNumericField(ValueModel valueModel, Class numberClass, int length, int nbdec, boolean useNullValue)
	{
		super(numberClass, length, nbdec, useNullValue);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyNumericField(ValueObjectModel valueObjectModel, String propertyName, Class numberClass,
	        int length, int nbdec, boolean useNullValue)
	{
		super(numberClass, length, nbdec, useNullValue);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyNumericField(ValueObjectModel valueObjectModel, PropertyPath propertyPath, Class numberClass,
	        int length, int nbdec, boolean useNullValue)
	{
		super(numberClass, length, nbdec, useNullValue);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public VOPropertyNumericField(ValueModel valueModel, Class numberClass, int length, int nbdec, double min,
	        double max)
	{
		super(numberClass, length, nbdec, min, max);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyNumericField(ValueObjectModel valueObjectModel, String propertyName, Class numberClass,
	        int length, int nbdec, double min, double max)
	{
		super(numberClass, length, nbdec, min, max);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyNumericField(ValueObjectModel valueObjectModel, PropertyPath propertyPath, Class numberClass,
	        int length, int nbdec, double min, double max)
	{
		super(numberClass, length, nbdec, min, max);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public VOPropertyNumericField(ValueModel valueModel, Class numberClass, int length, int nbdec, double min,
	        double max, boolean useNullValue)
	{
		super(numberClass, length, nbdec, min, max, useNullValue);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	public VOPropertyNumericField(ValueObjectModel valueObjectModel, String propertyName, Class numberClass,
	        int length, int nbdec, double min, double max, boolean useNullValue)
	{
		super(numberClass, length, nbdec, min, max, useNullValue);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	public VOPropertyNumericField(ValueObjectModel valueObjectModel, PropertyPath propertyPath, Class numberClass,
	        int length, int nbdec, double min, double max, boolean useNullValue)
	{
		super(numberClass, length, nbdec, min, max, useNullValue);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	public String getPropertyName()
	{
		return m_propertyEventHandler.getPropertyName();
	}

	public void setPropertyName(String propertyName)
	{
		m_propertyEventHandler.setPropertyName(propertyName);
	}

	public void setValidField(boolean valid)
	{
		boolean propertyValidity = true;
		if (m_propertyEventHandler != null)
		{
			ValueObjectModel vom = m_propertyEventHandler.getValueObjectModel();
			if (vom != null)
			{
				propertyValidity = m_propertyEventHandler.getValueObjectModel().isValueObjectPropertyValid(
				        getPropertyName());
			}
		}
		super.setValidField(valid && propertyValidity);
	}

	public void disconnectFromModel()
	{
		m_propertyEventHandler.disconnect();
	}
}