package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.InputFormattedTextField;

/**
 * @author tcaboste
 * 
 * Cette classe VOPropertyFormattedTextField dérive de la classe
 * InputFormattedTextField et est chargée de formater la saisie de texte. Cette
 * classe utilise un masque à la saisie et un masque à l'affichage NB : Il est
 * possible de définir, à l'affichage, un masque différent de celui de la
 * saisie.
 * 
 * La grammaire de définition des masques utilise les caractères suivants : #
 * pour un chiffre ? pour une lettre U pour une lettre en masjuscule L pour une
 * lettre en minuscule A pour une lettre ou un chiffre * pour n'importe quel
 * caractère H pour un caractère hexadécimal
 * 
 * Exemple: un numéro de téléphone "## ## ## ## ##" un numéro de SIRET
 * "##############" un date "##/##/####" un code à 5 majuscules "UUUUU"
 * 
 */
public class VOPropertyFormattedTextField extends InputFormattedTextField implements VOPropertyField
{
	private VOPropertyFormattedTextFieldEventHandler m_propertyEventHandler = new VOPropertyFormattedTextFieldEventHandler(
	        this);

	public VOPropertyFormattedTextField()
	{
	}

	// ---

	/**
	 * Constructeur simplifié de la classe
	 * 
	 * @param valueModel
	 *            modèle de la valeur associée à la widget
	 * @param pattern
	 *            motif de format de la saisie
	 */
	public VOPropertyFormattedTextField(ValueModel valueModel, String pattern)
	{
		super(pattern, pattern);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	/**
	 * Constructeur simplifié de la classe
	 * 
	 * @param valueObjectModel
	 *            modèle du ValueObject
	 * @param propertyName
	 *            nom de la propriété gérée du ValueObject gérée par la widget
	 * @param pattern
	 *            motif de format de la saisie
	 */
	public VOPropertyFormattedTextField(ValueObjectModel valueObjectModel, String propertyName, String pattern)
	{
		super(pattern, pattern);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	/**
	 * Constructeur simplifié de la classe
	 * 
	 * @param valueObjectModel
	 *            modèle du ValueObject
	 * @param propertyPath
	 *            nom de la propriété gérée du ValueObject gérée par la widget
	 * @param pattern
	 *            motif de format de la saisie
	 */
	public VOPropertyFormattedTextField(ValueObjectModel valueObjectModel, PropertyPath propertyPath, String pattern)
	{
		super(pattern, pattern);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	// ---

	/**
	 * Constructeur complet de la classe
	 * 
	 * @param valueModel
	 *            modèle de la valeur associée à la widget
	 * @param displayPattern
	 *            motif de formatage de l'affichage
	 * @param editPattern
	 *            motif de formatage de la saisie
	 */
	public VOPropertyFormattedTextField(ValueModel valueModel, String displayPattern, String editPattern)
	{
		super(displayPattern, editPattern);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	/**
	 * Constructeur complet de la classe
	 * 
	 * @param valueObjectModel
	 *            modèle du ValueObject
	 * @param propertyName
	 *            nom de la propriété gérée par ce champ
	 * @param displayPattern
	 *            motif de formatage de l'affichage
	 * @param editPattern
	 *            motif de formatage de la saisie
	 */
	public VOPropertyFormattedTextField(ValueObjectModel valueObjectModel, String propertyName, String displayPattern,
	        String editPattern)
	{
		super(displayPattern, editPattern);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	/**
	 * Constructeur complet de la classe
	 * 
	 * @param valueObjectModel
	 *            modèle du ValueObject
	 * @param propertyPath
	 *            nom de la propriété gérée du ValueObject gérée par la widget
	 * @param displayPattern
	 *            motif de formatage de l'affichage
	 * @param editPattern
	 *            motif de formatage de la saisie
	 */
	public VOPropertyFormattedTextField(ValueObjectModel valueObjectModel, PropertyPath propertyPath,
	        String displayPattern, String editPattern)
	{
		super(displayPattern, editPattern);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyPath.toString());
	}

	// ---
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