package com.rennover.client.framework.valueobject.widget;

import com.rennover.client.framework.valueobject.model.PropertyPath;
import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.field.InputFormattedTextField;

/**
 * @author tcaboste
 * 
 * Cette classe VOPropertyFormattedTextField d�rive de la classe
 * InputFormattedTextField et est charg�e de formater la saisie de texte. Cette
 * classe utilise un masque � la saisie et un masque � l'affichage NB : Il est
 * possible de d�finir, � l'affichage, un masque diff�rent de celui de la
 * saisie.
 * 
 * La grammaire de d�finition des masques utilise les caract�res suivants : #
 * pour un chiffre ? pour une lettre U pour une lettre en masjuscule L pour une
 * lettre en minuscule A pour une lettre ou un chiffre * pour n'importe quel
 * caract�re H pour un caract�re hexad�cimal
 * 
 * Exemple: un num�ro de t�l�phone "## ## ## ## ##" un num�ro de SIRET
 * "##############" un date "##/##/####" un code � 5 majuscules "UUUUU"
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
	 * Constructeur simplifi� de la classe
	 * 
	 * @param valueModel
	 *            mod�le de la valeur associ�e � la widget
	 * @param pattern
	 *            motif de format de la saisie
	 */
	public VOPropertyFormattedTextField(ValueModel valueModel, String pattern)
	{
		super(pattern, pattern);
		m_propertyEventHandler.setValueModel(this, valueModel);
	}

	/**
	 * Constructeur simplifi� de la classe
	 * 
	 * @param valueObjectModel
	 *            mod�le du ValueObject
	 * @param propertyName
	 *            nom de la propri�t� g�r�e du ValueObject g�r�e par la widget
	 * @param pattern
	 *            motif de format de la saisie
	 */
	public VOPropertyFormattedTextField(ValueObjectModel valueObjectModel, String propertyName, String pattern)
	{
		super(pattern, pattern);
		m_propertyEventHandler.setValueObjectModel(this, valueObjectModel, propertyName);
	}

	/**
	 * Constructeur simplifi� de la classe
	 * 
	 * @param valueObjectModel
	 *            mod�le du ValueObject
	 * @param propertyPath
	 *            nom de la propri�t� g�r�e du ValueObject g�r�e par la widget
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
	 *            mod�le de la valeur associ�e � la widget
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
	 *            mod�le du ValueObject
	 * @param propertyName
	 *            nom de la propri�t� g�r�e par ce champ
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
	 *            mod�le du ValueObject
	 * @param propertyPath
	 *            nom de la propri�t� g�r�e du ValueObject g�r�e par la widget
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