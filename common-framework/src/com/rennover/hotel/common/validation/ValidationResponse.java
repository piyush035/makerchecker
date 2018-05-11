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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.rennover.hotel.common.exception.Assert;
import com.rennover.hotel.common.helper.ReflectionHelper;
import com.rennover.hotel.common.valueobject.ValueObject;


/**
 * Réponse de validation pour un ValueObject.  S'il référence d'autres ValueObjects, ces
 * derniers ne sont pas validés ici.
 *
 * @author ddreistadt
 */
public class ValidationResponse implements ObjectProblemCollection
{
	private List m_propertyProblemList = null;

	private List m_globalProblemList = null;

	private List m_errorProblemList = null;

	private List m_warningProblemList = null;

    /**
    * Contient le nom des propriétés de cet objet (pas de ses sous-objets) qui restent à
    * remplir.
    */
    private Set m_missingMandatoryPropertySet;

    /**
    * ValueObjet sur lequel porte la reponse.
    */
    private ValueObject m_validatedObject;

    /**
    * @param validatedObject objet sur lequel porte la reponse de validation;
    *
    * @postcondition Le paramètre validatedObject ne doit pas être nul
    */
    public ValidationResponse(ValueObject validatedObject)
	{
		m_validatedObject = validatedObject;
	}

    /**
    * Ajoute la propriété spécifiée à la collection de propriétés qui doivent être
    * remplies pour que l'objet les contenant soit valide.
    */
    public void addMissingMandatoryProperty(String propertyName)
	{
		if (m_missingMandatoryPropertySet == null)
		{
			m_missingMandatoryPropertySet = new HashSet();
		}

		m_missingMandatoryPropertySet.add(propertyName);
	}

	public void addProblems(List problemList)
	{
		for (int i = 0; i < problemList.size(); i++)
		{
			ValidationProblem problem = (ValidationProblem) problemList.get(i);
			addProblem(problem);
		}
	}

	public void addProblem(ValidationProblem problem)
	{
		if (problem.isPropertyProblem())
		{
			_addPropertyProblem(problem);
		} else
		{
			_addGlobalProblem(problem);
		}

		if (problem.isWarning())
		{
			_addWarningProblem(problem);
		} else
		{
			_addErrorProblem(problem);
		}
	}

	private void _addGlobalProblem(ValidationProblem problem)
	{
		Assert.check(!problem.isPropertyProblem());

		if (m_globalProblemList == null)
		{
			m_globalProblemList = new ArrayList(2);
		}

		m_globalProblemList.add(problem);
	}

	private void _addPropertyProblem(ValidationProblem problem)
	{
		Assert.check(problem.isPropertyProblem());

		if (m_propertyProblemList == null)
		{
			m_propertyProblemList = new ArrayList(2);
		}

		m_propertyProblemList.add(problem);
	}

	private void _addWarningProblem(ValidationProblem problem)
	{
		Assert.check(problem.isWarning());

		if (m_warningProblemList == null)
		{
			m_warningProblemList = new ArrayList(2);
		}

		m_warningProblemList.add(problem);
	}

	private void _addErrorProblem(ValidationProblem problem)
	{
		Assert.check(!problem.isWarning());

		if (m_errorProblemList == null)
		{
			m_errorProblemList = new ArrayList(2);
		}

		m_errorProblemList.add(problem);
	}

	public void removeProblems(List problemList)
	{
		for (int i = 0; i < problemList.size(); i++)
		{
			ValidationProblem problem = (ValidationProblem) problemList.get(i);
			removeProblem(problem);
		}
	}

	public void removeProblem(ValidationProblem problem)
	{
		if (problem.isPropertyProblem())
		{
			_removePropertyProblem(problem);
		} else
		{
			_removeGlobalProblem(problem);
		}

		if (problem.isWarning())
		{
			_removeWarningProblem(problem);
		} else
		{
			_removeErrorProblem(problem);
		}
	}

	private void _removeGlobalProblem(ValidationProblem problem)
	{
		Assert.check(!problem.isPropertyProblem());

		if (m_globalProblemList != null)
		{
			m_globalProblemList.remove(problem);

			if (m_globalProblemList.isEmpty())
			{
				m_globalProblemList = null;
			}
		}
	}

	private void _removePropertyProblem(ValidationProblem problem)
	{
		Assert.check(problem.isPropertyProblem());

		if (m_propertyProblemList != null)
		{
			m_propertyProblemList.remove(problem);

			if (m_propertyProblemList.isEmpty())
			{
				m_propertyProblemList = null;
			}
		}
	}

	private void _removeWarningProblem(ValidationProblem problem)
	{
		Assert.check(problem.isWarning());

		if (m_warningProblemList != null)
		{
			m_warningProblemList.remove(problem);

			if (m_warningProblemList.isEmpty())
			{
				m_warningProblemList = null;
			}
		}
	}

	private void _removeErrorProblem(ValidationProblem problem)
	{
		Assert.check(!problem.isWarning());

		if (m_errorProblemList != null)
		{
			m_errorProblemList.remove(problem);

			if (m_errorProblemList.isEmpty())
			{
				m_errorProblemList = null;
			}
		}
	}

	public Set getMissingMandatoryPropertySet()
	{
		return m_missingMandatoryPropertySet;
	}

	public List getWarningProblemList()
	{
		return m_warningProblemList;
	}

	public List getErrorProblemList()
	{
		return m_errorProblemList;
	}

	public List getGlobalProblemList()
	{
		return m_globalProblemList;
	}

	public List getPropertyProblemList()
	{
		return m_propertyProblemList;
	}

	public List getPropertyProblemList(String propertyName)
	{
		ArrayList result = null;

		if (m_propertyProblemList != null)
		{
			for (Iterator iter = m_propertyProblemList.iterator(); iter.hasNext();)
			{
				ValidationProblem problem = (ValidationProblem) iter.next();

				if (problem.isPropertyInvolved(propertyName))
				{
					if (result == null)
					{
						result = new ArrayList(2);
					}

					result.add(problem);
				}
			}
		}

		return result;
	}

    /**
	 * get the set of name of property linked to a problem
	 * 
	 * @return a List of String or null
	 */
    public Set getPropertyNameWithProblemSet()
	{
		HashSet set = null;

		if (m_propertyProblemList != null)
		{
			set = new HashSet();

			List propertyNameList;
			ValidationProblem currentProblem;

			for (int i = m_propertyProblemList.size() - 1; i >= 0; i--)
			{
				currentProblem = (ValidationProblem) m_propertyProblemList.get(i);
				propertyNameList = currentProblem.getPropertyNameList();

				if (propertyNameList != null)
				{
					set.addAll(propertyNameList);
				}
			}
		}

		return set;
	}

    /**
	 * @deprecated use getErrorProblemList()
	 * @return
	 */
    public List getAllProblemCollection()
	{
		return getErrorProblemList();
	}

	public Collection getAllInvalidProperties()
	{
		Set propertySet = new TreeSet();

		// Ajout des propriétés manquantes
		if (m_missingMandatoryPropertySet != null)
		{
			Iterator missingIterator = m_missingMandatoryPropertySet.iterator();

			while (missingIterator.hasNext())
			{
				String propertyName = (String) missingIterator.next();
				propertySet.add(propertyName);
			}
		}

		// Ajout des propriétés invalides pour chaque problèmes de propriétés
		if (m_propertyProblemList != null)
		{
			List problemList = m_propertyProblemList;
			int count = problemList.size();

			for (int i = 0; i < count; i++)
			{
				ValidationProblem problem = (ValidationProblem) problemList.get(i);
				List involvedPropertyNameList = problem.getInvolvedPropertyNameList();
				propertySet.addAll(involvedPropertyNameList);
			}
		}

		return propertySet;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer(200);

		buffer.append('[');
		buffer.append(ReflectionHelper.getClassShortName(this.getClass()));
		buffer.append(", isValid=").append(isValid());
		buffer.append(", propertyProblemList=").append(m_propertyProblemList);
		buffer.append(", globalProblemList=").append(m_globalProblemList);
		buffer.append(", missingPropertieSet=").append(m_missingMandatoryPropertySet);
		buffer.append(']');

		return buffer.toString();
	}

	public void addValidationResponse(String propertyName, ValidationResponse response)
	{
		// propriétés manquantes
		addSubpartMissingPropertySet(propertyName, response.m_missingMandatoryPropertySet);

		// erreurs
		addProblemFromProperty(propertyName, response.getErrorProblemList());

		// alertes
		addProblemFromProperty(propertyName, response.getWarningProblemList());
	}

	protected void addProblemFromProperty(String propertyName, List problemList)
	{
		if (problemList == null)
		{
			return;
		}

		int count = problemList.size();

		for (int i = 0; i < count; i++)
		{
			ValidationProblem problem = (ValidationProblem) problemList.get(i);
			problem.addRootPropertyName(propertyName); // NB: permet de
														// construire le chemin
														// de la propriété (ex:
														// adresse.telephone)
			addProblem(problem);
		}
	}

	private void addSubpartMissingPropertySet(String rootPropertyName, Set subpartMissingPropertySet)
	{
		if (subpartMissingPropertySet == null)
		{
			return;
		}

		for (Iterator iter = subpartMissingPropertySet.iterator(); iter.hasNext();)
		{
			String propertyName = (String) iter.next();
			addMissingMandatoryProperty(rootPropertyName + '.' + propertyName);
		}
	}

    /**
	 * Indique si tous les objets sont valides c'est-à-dire que, pour chaque
	 * objet :
	 * 
	 * <ul>
	 * <li> Tous les champs obligatoires sont renseignés </li>
	 * <li> Toutes les règles de niveau objet sont satisfaites </li>
	 * <li> Toutes les propriétés basiques sont valides </li>
	 * </ul>
	 */
    public boolean isValid()
	{
		return !hasErrors();
	}

	public boolean hasErrors()
	{
		return (getErrorProblemList() != null) || hasMissingMandatoryProperties();
	}

	public boolean hasWarnings()
	{
		return getWarningProblemList() != null;
	}

	public boolean isPropertyValid(String propertyName)
	{
		return (getPropertyProblemList(propertyName) == null) && !isMissingMandatoryProperty(propertyName);
	}

	public boolean isMissingMandatoryProperty(String propertyName)
	{
		return (m_missingMandatoryPropertySet != null) && m_missingMandatoryPropertySet.contains(propertyName);
	}

    /**
	 * Indique si au moins un des {@link ValueObject}s validés a au moins une
	 * règle de niveau object non validée. Si les seules erreurs sont des
	 * propriétés manquantes ou invalides, alors cette valeur doit être à true.
	 * 
	 * <p>
	 * Pour tester si l'objet est correct ET rempli, utiliser plutôt la méthode
	 * {@link #isValid()}.
	 * </p>
	 */
    public boolean hasGlobalProblems()
	{
		List list = getGlobalProblemList();

		return (list != null) && !list.isEmpty();
	}

    /**
	 * Indique si au moins un des {@link ValueObject}s validés a au moins une
	 * propriété manquante (non remplie).
	 */
    public boolean hasMissingMandatoryProperties()
	{
		Set list = getMissingMandatoryPropertySet();

		return (list != null) && !list.isEmpty();
	}

    /**
	 * Indique si au moins un des {@link ValueObject}s validés a au moins une
	 * propriété invalide.
	 * 
	 * <p>
	 * Ne donne aucune information sur le fait qu'une propriété est
	 * incomplètement remplie.
	 * </p>
	 */
    public boolean hasInvalidProperties()
	{
		List list = getPropertyProblemList();

		return (list != null) && !list.isEmpty();
	}

    /**
	 * @param propertyList
	 */
    public void removePropertyProblems(List propertyList)
	{
		int count = propertyList.size();

		for (int i = 0; i < count; i++)
		{
			String propertyName = (String) propertyList.get(i);

			// suppression de tous les problèmes contenant cette propriété
			List problemToRemoveList = getPropertyProblemList(propertyName);

			if (problemToRemoveList != null)
			{
				removeProblems(problemToRemoveList);
			}

			// suppression de la propriété dans les propriétés obligatoires
			// manquantes
			if (m_missingMandatoryPropertySet != null)
			{
				m_missingMandatoryPropertySet.remove(propertyName);
			}
		}
	}

    /**
	 * @param propertyList
	 */
    public void keepPropertyProblems(List propertyList)
	{
		// suppression de tous les problèmes ayant des propriétés hors de la liste de propriétés
		if (m_propertyProblemList != null)
		{
			List problemList = m_propertyProblemList;
			List problemToRemoveList = new ArrayList();
			int count = problemList.size();

			for (int i = 0; i < count; i++)
			{
				ValidationProblem problem = (ValidationProblem) problemList.get(i);
				List involvedPropertyNameList = problem.getInvolvedPropertyNameList();

				if (!propertyList.containsAll(involvedPropertyNameList))
				{
					problemToRemoveList.add(problem);
				}
			}

			removeProblems(problemToRemoveList);
		}

		// suppression des propriétés obligatoires hors de la liste de propriétés
		if (m_missingMandatoryPropertySet != null)
		{
			List propertyToRemoveList = new ArrayList();
			Iterator missingIterator = m_missingMandatoryPropertySet.iterator();

			while (missingIterator.hasNext())
			{
				String propertyName = (String) missingIterator.next();

				if (!propertyList.contains(propertyName))
				{
					propertyToRemoveList.add(propertyName);
				}
			}

			m_missingMandatoryPropertySet.removeAll(propertyToRemoveList);
		}
	}

	public void addPropertyProblem(String propertyName, Class validatorClass, boolean warning)
	{
		addObjectProblem(Collections.singletonList(propertyName), validatorClass, warning);
	}

	public void addObjectProblem(List propertyNameList, Class validatorClass, boolean warning)
	{
		Assert.checkNotNull(propertyNameList);
		addProblem(null, propertyNameList, validatorClass, warning);
	}

	public void addPropertyProblemWithMessage(String specificMessage, String propertyName, Class validatorClass,
	        boolean warning)
	{
		addObjectProblemWithMessage(specificMessage, Collections.singletonList(propertyName), validatorClass, warning);
	}

	public void addObjectProblemWithMessage(String specificMessage, List propertyNameList, Class validatorClass,
	        boolean warning)
	{
		Assert.checkNotNull(specificMessage);
		Assert.checkNotNull(propertyNameList);
		addProblem(specificMessage, propertyNameList, validatorClass, warning);
	}

	public void addGlobalProblem(String specificMessage, Class validatorClass, boolean warning)
	{
		Assert.checkNotNull(specificMessage);
		addProblem(specificMessage, null, validatorClass, warning);
	}

	private void addProblem(String specificMessage, List propertyNameList, Class validatorClass, boolean warning)
	{
		ValidationProblem problem = new ValidationProblem(specificMessage, propertyNameList, validatorClass, warning);
		addProblem(problem);
	}
}
