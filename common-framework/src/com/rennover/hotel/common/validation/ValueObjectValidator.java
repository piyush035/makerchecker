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
 * Fournit un mécanisme générique :
 *
 * <ol>
 * <li>
 * contrôle de toutes les règles locales à un champ ;
 * </li>
 * <li>
 * contrôle des règles globales au value object:
 *
 * <ol>
 * <li>
 * contrôle des champs obligatoires ;
 * </li>
 * <li>
 * autres types de contrôles globaux.
 * </li>
 * </ol>
 *
 * </li>
 * </ol>
 *
 * Les contrôles de types 2.2 sont laissés à la charge des sous-classes (pattern
 * prototype method).
 * </p>
 *
 * <p>
 * Pour chaque {@link com.rennover.hotel.common.valueobject.ValueObject} pouvant être
 * validé, il faut disposer, dans le même package, d'une sous-classe de
 * ValueObjectValidator. Cette sous-classe permet de définir les règles de validation
 * propres au ValueObject.
 * </p>
 *
 * <p>
 * Afin d'associer un ValueObjectValidator à son ValueObject, il faut nommer le
 * validateur  comme le ValueObject et suffixant par "Validator".
 * </p>
 *
 * <p>
 * De plus, il est nécessaire de créer un fichier de propriétés contenant les messages
 * associés à chacune des propriétés validables du ValueObject. Ce fichier doit se
 * trouver dans le même packacke  que le ValueObject et son validateur, et son nom doit
 * correspondre au nom du ValueObject suivi de "_fr.properties". Dans ce fichier, il
 * faut associer un attribut (représenté par son nom) et un message indiquant toutes ses
 * contraintes. Il y a aussi une entrée pour définir les contraintes globales sur le
 * ValueObject : sa clef est "class".
 * </p>
 *
 * <p>
 * Les sous-classes de ValueObjectValidator utilise les constantes représentant les
 * attributs du {@link com.rennover.hotel.common.valueobject.ValueObject} validé pour
 * indiquer les règles de validation sur un attribut.
 * </p>
 *
 * <p>
 * De plus, il est interdit de baser certaines règles de validation sur des références à
 * des objets dépendants persistents  (relation exprimée avec un ObjectId) car l'objet
 * n'est présent que selon le contexte.
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
     * Ensemble des noms des propriétés obligatoires.
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
     * Constructeur par défaut. L'initialisation des validateurs doit être
     * faite dans le constructeur des sous-classes par une suite d'appels à
     * {@link #addBasicValidator(String, BasicValidator)} et {@link
     * #addMandatoryProperty(String)}.
     */
    protected ValueObjectValidator()
	{
	}

    /**
     * Validation complète du value object (l'objet et ses propriétés).
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
	 * Indique que la propriété est obligatoire.
	 * 
	 * @param propertyName
	 *            nom de la propriété
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
     * Indique que la propriété est obligatoire.
     *
     * @param propertyName nom de la propriété
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
	 * Retourne les règles de validation sur l'attribut
	 * 
	 * @param propertyName
	 *            nom de la propriété
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
	 * Renvoie la collection des valueObjects liés au ValueObject à valider.
	 */
    protected Collection getReferencedValueObjectCollection(ValueObject valueObject)
	{
		return new ArrayList(0);
	}

    /**
	 * Validation des règles globales au value object (impliquant plusieurs de
	 * ses champs).<br>
	 * Si des problèmes sont détectés, ils sont agrégés à la {@link
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
	 * Validation des règles sur les propriétés du value object.<br>
	 * Si des problèmes sont détectés, ils sont agrégés à la {@link
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
	 * Indique si l'objet est vide ou pas. Par défaut, cette méthode ne
	 * travaille que sur la nullité de l'objet mais cela peut-être affiné en
	 * redéfinissant isEmpty2
	 * 
	 * @param valueObject
	 *            {@link com.rennover.hotel.common.valueobject.ValueObject}
	 */
    final boolean isEmptyOrNull(ValueObject valueObject)
	{
		return (valueObject == null) || isEmpty(valueObject);
	}

    /**
     * permet de raffiner la détermination de la nullité d'un objet
     * @param valueObject {@link com.rennover.hotel.common.valueobject.ValueObject}
     *
     * TODO (dattias, le 12 août 2003) : Rendre cohérent PropertyHelper.isNull et cette méthode.
     * TODO (dattias, le 12 août 2003) : Voir s'il faut rendre cette méthode abstract pour forcer
     * la redéfinition.
     */
    protected boolean isEmpty(ValueObject valueObject)
	{
		return false;
	}

    /**
     * Associe un {@link com.agefospme.nsicm.common.validation.BasicValidator} à une
     * propriété
     *
     * @param propertyName
     * @param basicValidator
     *
     * TODO (dattias, le 12 août 2003) : Renommer cette méthode addPropertyValidator
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
	 * Vérifie s'il existe des problèmes avec la propriété spécifiée. Les tests
	 * effectués sont:
	 * 
	 * <ol>
	 * <li> si la propriété est obligatoire et remplie </li>
	 * <li> si sa valeur est valide de façon indépendante du reste du
	 * ValueObject </li>
	 * <li> si sa valeur est valide en fonction du ValueObject la contenant
	 * </li>
	 * </ol>
	 * 
	 * @param propertyName
	 *            le nom de la propriété dans l'objet auquel est lié ce
	 *            validateur
	 * @param propertyValue
	 *            la valeur de la propriété; il pourra s'agir d'un {@link
	 *            com.rennover.hotel.common.valueobject.ValueObject} ou d'un
	 *            type primitif (String, Number, etc.)<br>
	 *            Eventuellement, cette valeur pourra être nulle
	 * @param validationResponse
	 *            les réponses qui seront mises à jour si un problème est
	 *            détecté
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
	 * Vérifie si la propriété spécifiée est obligatoire pour cet objet.<br>
	 * Si elle est obligatoire et que sa valeur est nulle (ou égale à {@link
	 * ValueObject#EMPTY_STRING}), alors la {@link
	 * com.rennover.hotel.common.validation.ValidationResponse} spécifiée sera
	 * mise à jour en conséquence.
	 * 
	 * @return Boolean indiquant si cette propriété est manquante
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
	 * Vérifie si la la propriété spécifiée est valide.<br>
	 * Si elle n'est pas correcte, alors la {@link
	 * com.rennover.hotel.common.validation.ValidationResponse} spécifiée sera
	 * mise à jour en conséquence.
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
