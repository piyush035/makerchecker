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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rennover.hotel.common.exception.Assert;
import com.rennover.hotel.common.valueobject.PropertyHelper;
import com.rennover.hotel.common.valueobject.ValueObject;


/**
 * Objet validant un value object.
 *
 * <p>
 * Fournit un m�canisme g�n�rique :
 *
 * <ol>
 * <li>
 * contr�le de toutes les r�gles locales � un champ ;
 * </li>
 * <li>
 * contr�le des r�gles globales au value object:
 *
 * <ol>
 * <li>
 * contr�le des champs obligatoires ;
 * </li>
 * <li>
 * autres types de contr�les globaux.
 * </li>
 * </ol>
 *
 * </li>
 * </ol>
 *
 * Les contr�les de types 2.2 sont laiss�s � la charge des sous-classes (pattern
 * prototype method).
 * </p>
 *
 * <p>
 * Pour chaque {@link com.rennover.hotel.common.valueobject.ValueObject} pouvant �tre
 * valid�, il faut disposer, dans le m�me package, d'une sous-classe de
 * ValueObjectValidator. Cette sous-classe permet de d�finir les r�gles de validation
 * propres au ValueObject.
 * </p>
 *
 * <p>
 * Afin d'associer un ValueObjectValidator � son ValueObject, il faut nommer le
 * validateur  comme le ValueObject et suffixant par "Validator".
 * </p>
 *
 * <p>
 * De plus, il est n�cessaire de cr�er un fichier de propri�t�s contenant les messages
 * associ�s � chacune des propri�t�s validables du ValueObject. Ce fichier doit se
 * trouver dans le m�me packacke  que le ValueObject et son validateur, et son nom doit
 * correspondre au nom du ValueObject suivi de "_fr.properties". Dans ce fichier, il
 * faut associer un attribut (repr�sent� par son nom) et un message indiquant toutes ses
 * contraintes. Il y a aussi une entr�e pour d�finir les contraintes globales sur le
 * ValueObject : sa clef est "class".
 * </p>
 *
 * <p>
 * Les sous-classes de ValueObjectValidator utilise les constantes repr�sentant les
 * attributs du {@link com.rennover.hotel.common.valueobject.ValueObject} valid� pour
 * indiquer les r�gles de validation sur un attribut.
 * </p>
 *
 * <p>
 * De plus, il est interdit de baser certaines r�gles de validation sur des r�f�rences �
 * des objets d�pendants persistents  (relation exprim�e avec un ObjectId) car l'objet
 * n'est pr�sent que selon le contexte.
 * </p>
 *
 * @author ddreistadt
 * @audit dattias 17/09/02
 * @audit dattias 23/12/02
 */
public class ValueObjectValidator
{
    /**
     * @link aggregation
     * @associates <{com.agefospme.nsicm.common.validation.BasicValidator}>
     * @supplierCardinality 0.. Couples(String : nom de la propriete, Set : ensemble de
     *                      {@link
     *                      com.agefospme.nsicm.common.validation.BasicValidator})
     */
    private Map m_propertyValidatorMap;

    /**
     * Ensemble des noms des propri�t�s obligatoires.
     */
    private Set m_mandatoryPropertySet;

    /**
     * @link aggregation
     * @associates <{com.agefospme.nsicm.common.validation.ValidationRules}>
     * @supplierCardinality 0.. Couples(String : nom de la propriete, {@link
     *                      com.rennover.hotel.common.validation.ValidationRules})
     */
    private Map m_propertyValidationRulesMap;

    /**
     * Constructeur par d�faut. L'initialisation des validateurs doit �tre
     * faite dans le constructeur des sous-classes par une suite d'appels �
     * {@link #addBasicValidator(String, BasicValidator)} et {@link
     * #addMandatoryProperty(String)}.
     */
    protected ValueObjectValidator()
	{
	}

    /**
     * Validation compl�te du value object (l'objet et ses propri�t�s).
     */
    final ValidationResponse validate(ValueObject valueObject)
	{
		ValidationResponse response = new ValidationResponse(valueObject);
		validate(valueObject, response);

		return response;
	}

    final ValidationResponse validate(ValueObject valueObject, ValidationResponse response)
	{
		validatePropertyRules(valueObject, response);
		validateObjectRules(valueObject, response);

		return response;
	}

    /**
	 * Indique que la propri�t� est obligatoire.
	 * 
	 * @param propertyName
	 *            nom de la propri�t�
	 */
    private final void addMandatoryProperty(String propertyName)
	{
		if (m_mandatoryPropertySet == null)
		{
			m_mandatoryPropertySet = new HashSet();
		}

        m_mandatoryPropertySet.add(propertyName);
    }

    /**
     * Indique que la propri�t� est obligatoire.
     *
     * @param propertyName nom de la propri�t�
     */
    protected final boolean isPropertyMandatory(String propertyName)
	{
		if (m_mandatoryPropertySet != null)
		{
			return m_mandatoryPropertySet.contains(propertyName);
		}

		return false;
	}

    /**
	 * Retourne les r�gles de validation sur l'attribut
	 * 
	 * @param propertyName
	 *            nom de la propri�t�
	 */
    protected final ValidationRules getPropertyValidationRules(String propertyName)
	{
		if (m_propertyValidationRulesMap != null)
		{
			return (ValidationRules) m_propertyValidationRulesMap.get(propertyName);
		}

		return null;
	}

    /**
	 * Renvoie la collection des valueObjects li�s au ValueObject � valider.
	 */
    protected Collection getReferencedValueObjectCollection(ValueObject valueObject)
	{
		return new ArrayList(0);
	}

    /**
	 * Validation des r�gles globales au value object (impliquant plusieurs de
	 * ses champs).<br>
	 * Si des probl�mes sont d�tect�s, ils sont agr�g�s � la {@link
	 * com.rennover.hotel.common.validation.ValidationResponse}
	 * 
	 * @param valueObject
	 *            {@link com.rennover.hotel.common.valueobject.ValueObject}
	 * @param response
	 *            {@link ValueObjectValidationResponse}
	 */
    protected void validateObjectRules(ValueObject valueObject, ObjectProblemCollection response)
	{
	}

    /**
	 * Validation des r�gles sur les propri�t�s du value object.<br>
	 * Si des probl�mes sont d�tect�s, ils sont agr�g�s � la {@link
	 * com.rennover.hotel.common.validation.ValidationResponse}
	 * 
	 * @param valueObject
	 *            {@link com.rennover.hotel.common.valueobject.ValueObject}
	 * @param response
	 *            {@link ValueObjectValidationResponse}
	 */
    protected void validatePropertyRules(ValueObject valueObject, ValidationResponse response)
	{
	}

    /**
	 * Indique si l'objet est vide ou pas. Par d�faut, cette m�thode ne
	 * travaille que sur la nullit� de l'objet mais cela peut-�tre affin� en
	 * red�finissant isEmpty2
	 * 
	 * @param valueObject
	 *            {@link com.rennover.hotel.common.valueobject.ValueObject}
	 */
    final boolean isEmptyOrNull(ValueObject valueObject)
	{
		return (valueObject == null) || isEmpty(valueObject);
	}

    /**
     * permet de raffiner la d�termination de la nullit� d'un objet
     * @param valueObject {@link com.rennover.hotel.common.valueobject.ValueObject}
     *
     * TODO (dattias, le 12 ao�t 2003) : Rendre coh�rent PropertyHelper.isNull et cette m�thode.
     * TODO (dattias, le 12 ao�t 2003) : Voir s'il faut rendre cette m�thode abstract pour forcer
     * la red�finition.
     */
    protected boolean isEmpty(ValueObject valueObject)
	{
		return false;
	}

    /**
     * Associe un {@link com.agefospme.nsicm.common.validation.BasicValidator} � une
     * propri�t�
     *
     * @param propertyName
     * @param basicValidator
     *
     * TODO (dattias, le 12 ao�t 2003) : Renommer cette m�thode addPropertyValidator
     */
    protected final void addBasicValidator(String propertyName, PropertyValidator basicValidator)
	{
		if (m_propertyValidatorMap == null)
		{
			m_propertyValidatorMap = new HashMap();
		}

		List propertyValidators = (List) m_propertyValidatorMap.get(propertyName);

		if (propertyValidators == null)
		{
			propertyValidators = new ArrayList(1);
			m_propertyValidatorMap.put(propertyName, propertyValidators);
		}

		propertyValidators.add(basicValidator);
	}

    /**
	 * V�rifie s'il existe des probl�mes avec la propri�t� sp�cifi�e. Les tests
	 * effectu�s sont:
	 * 
	 * <ol>
	 * <li> si la propri�t� est obligatoire et remplie </li>
	 * <li> si sa valeur est valide de fa�on ind�pendante du reste du
	 * ValueObject </li>
	 * <li> si sa valeur est valide en fonction du ValueObject la contenant
	 * </li>
	 * </ol>
	 * 
	 * @param propertyName
	 *            le nom de la propri�t� dans l'objet auquel est li� ce
	 *            validateur
	 * @param propertyValue
	 *            la valeur de la propri�t�; il pourra s'agir d'un {@link
	 *            com.rennover.hotel.common.valueobject.ValueObject} ou d'un
	 *            type primitif (String, Number, etc.)<br>
	 *            Eventuellement, cette valeur pourra �tre nulle
	 * @param validationResponse
	 *            les r�ponses qui seront mises � jour si un probl�me est
	 *            d�tect�
	 */
    protected final void checkProperty(String propertyName, Object propertyValue, ValidationResponse response)
	{
		Assert.checkNotNull(propertyName);
		Assert.checkNotNull(response);

		// FIX FOR THE DATEVALIDATOR PROBLEM
		// NEED TO TEST THIS.... SANJEEV
		boolean missingMandatoryProperty = checkMandatoryRules(propertyName, propertyValue, response);

		if (!missingMandatoryProperty)
		{
			checkPropertyRules(propertyName, propertyValue, response);
		}
	}

    /**
	 * V�rifie si la propri�t� sp�cifi�e est obligatoire pour cet objet.<br>
	 * Si elle est obligatoire et que sa valeur est nulle (ou �gale � {@link
	 * ValueObject#EMPTY_STRING}), alors la {@link
	 * com.rennover.hotel.common.validation.ValidationResponse} sp�cifi�e sera
	 * mise � jour en cons�quence.
	 * 
	 * @return Boolean indiquant si cette propri�t� est manquante
	 */
    private final boolean checkMandatoryRules(String propertyName, Object propertyValue, ValidationResponse response)
	{
		boolean missing = false;

		if (m_mandatoryPropertySet != null)
		{
			if (m_mandatoryPropertySet.contains(propertyName))
			{
				if (propertyValue instanceof ValueObject)
				{
					missing = ValidationService.isEmptyOrNull((ValueObject) propertyValue);
				} else
				{
					missing = PropertyHelper.isNull(propertyValue);
				}
			}
		}

		if (missing)
		{
			response.addMissingMandatoryProperty(propertyName);
		}

		return missing;
	}

    /**
	 * V�rifie si la la propri�t� sp�cifi�e est valide.<br>
	 * Si elle n'est pas correcte, alors la {@link
	 * com.rennover.hotel.common.validation.ValidationResponse} sp�cifi�e sera
	 * mise � jour en cons�quence.
	 */
    private final void checkPropertyRules(String propertyName, Object propertyValue, ValidationResponse response)
	{
		if (propertyValue instanceof ValueObject)
		{
			ValueObject vo = (ValueObject) propertyValue;

			if (!ValidationService.isEmptyOrNull(vo))
			{
				ValidationResponse propertyResponse = ValidationService.validate(vo);
				response.addValidationResponse(propertyName, propertyResponse);
			}
		}
        // FIX FOR THE DATE VALIDATOR PROBLEM
        // NEED TO TEST THIS........//SANJEEV

        /**
         * Mandatory rules must have already been verified but
         * the property value can still be null.
         */
        else if (!PropertyHelper.isNull(propertyValue))
		{
			// property type validation
			PropertyValidator typeValidator = ValidationService.getPropertyTypeValidator(propertyValue.getClass());

			if (typeValidator != null)
			{
				typeValidator.validate(propertyName, propertyValue, response);
			}

			// specific property validation
			if (m_propertyValidatorMap != null)
			{
				List validatorList = (List) m_propertyValidatorMap.get(propertyName);

				if (validatorList != null)
				{
					for (Iterator iterator = validatorList.iterator(); iterator.hasNext();)
					{
						PropertyValidator basicValidator = (PropertyValidator) iterator.next();
						basicValidator.validate(propertyName, propertyValue, response);
					}
				}
			}
		}
	}

    // /////////////////////////
    // / Metadata managemant ///
    // /////////////////////////
    protected void setPropertyReadOnly(String propertyName, boolean readOnly)
	{
		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setReadOnly(readOnly);
	}

	protected void setPropertyUpperCase(String propertyName, boolean upperCase)
	{
		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setUppercase(upperCase);
	}

	protected void setPropertyRangeMin(String propertyName, Comparable min)
	{
		addBasicValidator(propertyName, new RangeMinValidator(min));

		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setRangeMin(min);
	}

	protected void setPropertyRangeMax(String propertyName, Comparable max)
	{
		addBasicValidator(propertyName, new RangeMaxValidator(max));

		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setRangeMax(max);
	}

	protected void setPropertySizeMin(String propertyName, long min)
	{
		addBasicValidator(propertyName, new SizeMinValidator(min));

		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setSizeMin(new Long(min));
	}

	protected void setPropertySizeMax(String propertyName, long max)
	{
		addBasicValidator(propertyName, new SizeMaxValidator(max));

		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setSizeMax(new Long(max));
	}

	protected void setPropertyDecimalDigits(String propertyName, long max)
	{
		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setDecimalDigits(new Long(max));
	}

	protected void setPropertyMandatory(String propertyName, boolean mandatory)
	{
		if (mandatory)
		{
			addMandatoryProperty(propertyName);
		}

		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setMandatory(mandatory);
	}

	protected void setPropertyPattern(String propertyName, String pattern)
	{
		addBasicValidator(propertyName, new StringPatternValidator(pattern));

		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setPattern(pattern);
	}

	protected void setPropertyDescription(String propertyName, String description)
	{
		ValidationRules rules = getOrCreatePropertyMetadata(propertyName);
		rules.setDescription(description);
	}

	private ValidationRules getOrCreatePropertyMetadata(String propertyName)
	{
		ValidationRules metadata = null;

		if (m_propertyValidationRulesMap == null)
		{
			m_propertyValidationRulesMap = new HashMap();
		} else
		{
			metadata = (ValidationRules) m_propertyValidationRulesMap.get(propertyName);
		}

		if (metadata == null)
		{
			metadata = new ValidationRules();
			m_propertyValidationRulesMap.put(propertyName, metadata);
		}

		return metadata;
	}
}
