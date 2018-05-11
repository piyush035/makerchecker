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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rennover.hotel.common.exception.Assert;
import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.valueobject.ValueObject;


/**
 * Service de validation des données.
 * <p>
 * Permet de contrôler les données à destination des
 * couches backend, en provenance de l'IHM, de systèmes externes ou de la base de
 * données.
 * </p>
 * <p>
 * Point d'entrée du framework de validation. Les méthodes validate permettent de valider
 * complètement un {@link com.rennover.hotel.common.valueobject.ValueObject}.
 * En retour, l'appelant reçoit une {@link com.rennover.hotel.common.validation.ValidationResponse} non nulle.
 * Si aucun problème n'a été trouvé alors la réponse est valide.
 * <p>
 * </p>
 * Les méthodes revalidate permettent de ne valider qu'un {@link com.rennover.hotel.common.valueobject.ValueObject}
 * ou un attribut d'un valueObject. Cependant, il faut déjà disposer d'une {@link com.rennover.hotel.common.validation.ValidationResponse}.
 * </p>
 */
public abstract class ValidationService
{
    /**
     * Utilisé pour déterminer le nom de la classe qui validera l'objet spécifié.
     * Le BaseValidator est généré mais un développeut peut le sous-classer pour
     * coder un comportement spécifique. Dans cas, la classe doit être suffixée par
     * validator
     */
    private static final String SUFFIXE_BASE_CLASSE_VALIDATOR = "BaseValidator";
    private static final String SUFFIXE_CLASSE_VALIDATOR = "Validator";

    /**
     * Validateur arbitraire qui permet d'enregistrer le fait qu'une classe n'a pas de
     * validateur associé. Cette instance ne sera jamais vue de l'extérieur; elle sera
     * remplacée par la valeur null lors des appels à {@link #getValidator(Class)}.
     */
    private static final Object NO_VALIDATOR_FOR_THIS_TYPE = new Object();

    /**
     * Map the java types and their validators.
     * Contains ValueObjectValidator objects for ValueObject types
     * and PropertyValidator objects for other java types.
     */
    private static Map s_typeValidatorMap = new HashMap();

    /**
     * Indicate whether the validation service has been initialized.
     */
    private static boolean s_initialized = false;

    /**
     * Initialisation du validation service.
     */
    public static void init()
	{
		if (s_initialized)
		{
			return;
		}

		setPropertyTypeValidator(Date.class, new DateValidator());

		s_initialized = true;
	}

    /**
	 * Validation d'un value object (uniquement le valueObject). Cette méthode
	 * est l'un des points d'entrée de cette classe.
	 * 
	 * @param valueObject
	 *            l'objet sur lequel on effectue la validation. La validation
	 *            porte uniquement sur le ValueObject.<br>
	 *            Si cet object n'est pas lié à un validateur, alors une
	 *            {@link com.rennover.hotel.common.validation.ValidationResponse}
	 *            de type "valide" sera retournée
	 * @return {@link com.rennover.hotel.common.validation.ValidationResponse}
	 *         vide si la vérification s'est bien passée
	 * @postcondition Valeur de retour non null
	 */
    public static ValidationResponse validate(ValueObject valueObject)
	{
		checkInitialization();
		Assert.checkNotNull(valueObject);

		ValueObjectValidator valueObjectValidator = getValueObjectValidator(valueObject.getClass());

		if (valueObjectValidator != null)
		{
			return valueObjectValidator.validate(valueObject);
		}

		return new ValidationResponse(valueObject);
	}

    /**
	 * 
	 * @param valueObjectClass
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
    public static ValidationResponse validate(Class valueObjectClass, String propertyName, Object propertyValue)
	{
		checkInitialization();
		Assert.checkNotNull(valueObjectClass);
		Assert.checkNotNull(propertyName);

		ValueObjectValidator valueObjectValidator = getValueObjectValidator(valueObjectClass);

		ValidationResponse response = new ValidationResponse(null);

		if (valueObjectValidator != null)
		{
			valueObjectValidator.checkProperty(propertyName, propertyValue, response);
		}

		return response;
	}

    /**
	 * 
	 * @param valueObjectClass
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
    public static void validate(ValidationResponse response, Class valueObjectClass, String propertyName,
	        Object propertyValue)
	{
		checkInitialization();
		Assert.checkNotNull(valueObjectClass);
		Assert.checkNotNull(propertyName);

		ValueObjectValidator valueObjectValidator = getValueObjectValidator(valueObjectClass);

		if (valueObjectValidator != null)
		{
			valueObjectValidator.checkProperty(propertyName, propertyValue, response);
		}
	}

    /**
	 * Validate a value object and filter the response by removing or keeping
	 * the problems associated with the properties passed.
	 * 
	 * @param valueObject
	 *            value object to validate
	 * @param propertyList
	 *            list of property names (String) to filter the response
	 * @param removePropertiesProblem
	 *            remove or keep
	 * @return the validation response
	 */
    public static ValidationResponse validate(ValueObject valueObject, List propertyList,
	        boolean removePropertiesProblem)
	{
		checkInitialization();

		ValidationResponse validationResponse = validate(valueObject);

		if (propertyList != null)
		{
			if (removePropertiesProblem)
			{
				validationResponse.removePropertyProblems(propertyList);
			} else
			{
				validationResponse.keepPropertyProblems(propertyList);
			}
		}

		return validationResponse;
	}

    /**
	 * Retourne un validateur qui permet de valider les instances du type
	 * spécifié. Si aucun validateur n'est trouvé, la valeur null est retournée.
	 * 
	 * @param valueObjectClass
	 *            La classe de l'objet à valider
	 * @return {@link com.rennover.hotel.common.validation.ValueObjectValidator}.
	 *         Peut-être nul si l'objet est de type simple ou s'il s'agit d'un
	 *         {@link com.rennover.hotel.common.valueobject.ValueObject} ne
	 *         proposant pas de validation
	 */
    static ValueObjectValidator getValueObjectValidator(Class objectClass)
	{
		checkInitialization();

		if (ValueObject.class.isAssignableFrom(objectClass))
		{
			return (ValueObjectValidator) getValidator(objectClass);
		}

		throw new IllegalArgumentException("Not a ValueObject class, " + objectClass);
	}

    /**
	 * 
	 * @param objectClass
	 * @return
	 */
    static PropertyValidator getPropertyTypeValidator(Class objectClass)
	{
		checkInitialization();

		if (objectClass.isAssignableFrom(ValueObject.class))
		{
			throw new IllegalArgumentException("ValueObject class, " + objectClass);
		}

		return (PropertyValidator) getValidator(objectClass);
	}

	public static void setPropertyTypeValidator(Class objectClass, PropertyValidator validator)
	{
		setValidator(objectClass, validator);
	}

    /**
	 * Retourne un validateur qui permet de valider les instances du type
	 * spécifié. Si aucun validateur n'est trouvé, la valeur null est retournée.
	 * 
	 * @param valueObjectClass
	 *            La classe de l'objet à valider
	 * @return {@link com.rennover.hotel.common.validation.ValueObjectValidator}.
	 *         Peut-être nul si l'objet est de type simple ou s'il s'agit d'un
	 *         {@link com.rennover.hotel.common.valueobject.ValueObject} ne
	 *         proposant pas de validation
	 * 
	 * Warning: recursive method.
	 */
    private static Object getValidator(Class objectClass)
	{
		if (objectClass == null)
		{
			return null;
		}

		// a-t-on deja un validateur en cache?
		Object validator = s_typeValidatorMap.get(objectClass);

		if (validator == null)
		{
			// try to create the validator
			validator = createValidator(objectClass);

			if (validator == null)
			{
				// this class doesn't have a direct validator
				// get the super class validator
				validator = getValidator(objectClass.getSuperclass());

				if (validator == null)
				{
					// on utilise un object arbitraire pour indiquer qu'aucun
					// validateur
					// n'existe pour cet objet (pour optimisation de la
					// recherche de validateur)
					validator = NO_VALIDATOR_FOR_THIS_TYPE;
				}
			}

			// le validateur est mis en cache
			setValidator(objectClass, validator);
		}

		if (validator == NO_VALIDATOR_FOR_THIS_TYPE)
		{
			// if no registered validator, returns null
			validator = null;
		}

		return validator;
	}

	private static void setValidator(Class objectClass, Object validator)
	{
		s_typeValidatorMap.put(objectClass, validator);
	}

    /**
	 * Create the direct validator object associated with class passed as a
	 * parameter. Search for <class name>Validator class or <class
	 * name>BaseValidator class.
	 * 
	 * @param objectClass
	 *            type for which a validator is searched
	 * @return the validator for this type
	 */
    private static Object createValidator(Class objectClass)
	{
		String validatorClassName = null;
		Class validatorClass = null;

		// To fix 2676 defaut class loader is used
		try
		{
			try
			{
				validatorClassName = objectClass.getName() + SUFFIXE_CLASSE_VALIDATOR;
				validatorClass = Class.forName(validatorClassName);
			} catch (ClassNotFoundException cnfe)
			{
				validatorClassName = objectClass.getName() + SUFFIXE_BASE_CLASSE_VALIDATOR;
				validatorClass = Class.forName(validatorClassName);
			}

			return validatorClass.newInstance();
		} catch (ClassNotFoundException cnfe)
		{
			// return null if no direct validator is found
			// based or not based
			return null;
		} catch (InstantiationException ie)
		{
			throw new IncoherenceException("New instance error for class " + validatorClassName, ie);
		} catch (IllegalAccessException iae)
		{
			throw new IncoherenceException("New instance error for class " + validatorClassName, iae);
		}
	}

    /**
	 * Retourne les règles de validation sur l'attribut de la classe
	 * (restriction sur la taille, caractère obligatoire, ...)
	 * 
	 * @param objectClass
	 *            classe contenant la propriété
	 * @param propertyName
	 *            nom de la propriété
	 */
    public static final ValidationRules getPropertyValidationRules(Class objectClass, String propertyName)
	{
		checkInitialization();

		ValueObjectValidator validator = getValueObjectValidator(objectClass);

		if (validator != null)
		{
			return validator.getPropertyValidationRules(propertyName);
		}

		return null;
	}

    /**
	 * Indique si l'objet est vide ou pas. Le caractère vide d'un objet est
	 * défini par le validateur associé. Cette information peut-être basée sur
	 * une exigence fonctionnelle
	 */
    public static final boolean isEmptyOrNull(ValueObject valueObject)
	{
		checkInitialization();

		if (valueObject == null)
		{
			return true;
		}

		ValueObjectValidator validator = getValueObjectValidator(valueObject.getClass());

		if (validator != null)
		{
			return validator.isEmptyOrNull(valueObject);
		}

		return false;
	}

	private static void checkInitialization()
	{
		if (!s_initialized)
		{
			throw new IllegalStateException("ValidationService not initialized");
		}
	}
}
