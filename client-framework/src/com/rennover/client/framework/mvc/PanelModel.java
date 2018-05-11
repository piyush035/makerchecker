
package com.rennover.client.framework.mvc;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rennover.client.framework.valueobject.model.ValueModel;
import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * 
 * Classe � sp�cialiser pour l'adapter au contr�leur d'un �cran
 * 
 * La classe doit permet de stocker les donn�es m�tier utilis�s dans un �cran Le
 * PanelModel est souvent compos� de ValueObjectModels et de
 * ValueObjectListModels Le PanelModel doit proposer une interface simple pour -
 * mettre � jour son contenu � partir des donn�es r�cup�rer par le contr�leur, -
 * et r�cup�rer l'ensemble des donn�es modifi�es par l'�cran.
 * 
 * Le PanelModel ne communique pas avec le serveur et ne conna�t pas les d�tails
 * de la composition de la vue.
 * 
 * @see com.rennover.client.framework.mvc.PanelView
 * @see com.rennover.client.framework.mvc.PanelViewController
 * @see com.rennover.client.framework.mvc.PanelController
 */
public abstract class PanelModel
{
	private Map m_valueModelMap = new Hashtable(); // map des vom

	private Map m_valueObjectModelMap = new Hashtable(); // map des vom

	private Map m_valueObjectListModelMap = new Hashtable(); // map des volm

	/**
	 * @deprecated don't use init() method, use ModelDescription to declare vom
	 *             and volm M�thode � surcharger
	 * 
	 * Cette m�thode doit initialiser toutes les sous-parties du mod�le. Les
	 * sous-parties du mod�le sont en g�n�ral des ValueObjectModels ou des
	 * ValueObjectListModels.
	 * @see com.rennover.client.framework.valueobject.model.ValueObjectModel
	 * @see com.rennover.client.framework.valueobject.model.ValueObjectListModel
	 * @see PanelController
	 * @see PanelView
	 */
	protected void init()
	{
	}

	public void clear()
	{
		Iterator iterator;

		iterator = m_valueModelMap.values().iterator();
		while (iterator.hasNext())
		{
			ValueModel vm = (ValueModel) iterator.next();
			vm.setValue(null);
		}

		iterator = m_valueObjectModelMap.values().iterator();
		while (iterator.hasNext())
		{
			ValueObjectModel vom = (ValueObjectModel) iterator.next();
			vom.setValueObject(null);
		}

		iterator = m_valueObjectListModelMap.values().iterator();
		while (iterator.hasNext())
		{
			ValueObjectListModel volm = (ValueObjectListModel) iterator.next();
			volm.setValueObjectList(null);
		}
	}

	/**
	 * Ajoute et retourne un ValueObjectModel cr�� � partir du ModelDescription
	 * pass� en param�tre
	 * 
	 * @param modelDescr
	 *            description du mod�le � cr�er
	 * @return le vom cr��
	 */
	public ValueObjectModel addValueObjectModel(ModelDescription modelDescr)
	{
		ValueObjectModel vom;
		if (modelDescr.getUseClone() != null)
		{
			vom = new ValueObjectModel(modelDescr.getObjectClass(), modelDescr.getUseClone().booleanValue());
		} else
		{
			vom = new ValueObjectModel(modelDescr.getObjectClass());
		}
		m_valueObjectModelMap.put(modelDescr, vom);
		return vom;
	}

	/**
	 * Ajoute et retourne un ValueObjectModel cr�� � partir du ModelDescription
	 * pass� en param�tre
	 * 
	 * @param modelDescr
	 *            description du mod�le � cr�er
	 * @return le volm cr��
	 */
	public ValueObjectListModel addValueObjectListModel(ModelDescription modelDescr)
	{
		ValueObjectListModel volm;
		if (modelDescr.getUseClone() != null)
		{
			volm = new ValueObjectListModel(modelDescr.getObjectClass(), modelDescr.getUseClone().booleanValue());
		} else
		{
			volm = new ValueObjectListModel(modelDescr.getObjectClass());
		}
		m_valueObjectListModelMap.put(modelDescr, volm);
		return volm;
	}

	/**
	 * Ajoute et retourne un ValueModel cr�� � partir du ModelDescription pass�
	 * en param�tre
	 * 
	 * @param modelDescr
	 *            description du mod�le � cr�er
	 * @return le vm cr��
	 */
	public ValueModel addValueModel(ModelDescription modelDescr)
	{
		ValueModel vm = new ValueModel(modelDescr.getObjectClass());
		m_valueModelMap.put(modelDescr, vm);
		return vm;
	}

	/**
	 * Retourne le ValueObjectModel associ� au ModelDescription pass� en
	 * param�tre Si le vom n'existe pas il est cr��.
	 * 
	 * @param name
	 *            nom du vom
	 * @return vom recherch� (ou cr�� s'il n'existait pas)
	 */
	public ValueObjectModel getValueObjectModel(ModelDescription modelDescr)
	{
		ValueObjectModel vom = (ValueObjectModel) m_valueObjectModelMap.get(modelDescr);
		if (vom == null)
		{
			vom = addValueObjectModel(modelDescr);
		}
		return vom;
	}

	/**
	 * Retourne le ValueObjectModel associ� au ModelDescription pass� en
	 * param�tre Si le vom n'existe pas il est cr��.
	 * 
	 * @param name
	 *            nom du vom
	 * @return vom recherch� (ou cr�� s'il n'existait pas)
	 */
	public ValueObjectListModel getValueObjectListModel(ModelDescription modelDescr)
	{
		ValueObjectListModel volm = (ValueObjectListModel) m_valueObjectListModelMap.get(modelDescr);
		if (volm == null)
		{
			volm = addValueObjectListModel(modelDescr);
		}
		return volm;
	}

	/**
	 * Retourne le ValueModel associ� au ModelDescription pass� en param�tre Si
	 * le vom n'existe pas il est cr��.
	 * 
	 * @param name
	 *            nom du vm
	 * @return vm recherch� (ou cr�� s'il n'existait pas)
	 */
	public ValueModel getValueModel(ModelDescription modelDescr)
	{
		ValueModel vm = (ValueModel) m_valueModelMap.get(modelDescr);
		if (vm == null)
		{
			vm = addValueModel(modelDescr);
		}
		return vm;
	}

	// --- m�thodes facade pour les VOM

	/**
	 * Retourne le valueObject du vom d�crit par modeldescrition apr�s
	 * validation des changements
	 * 
	 * @param modelDescription
	 *            description du mod�le recherch�
	 * @return le valueObject avec les derni�res modifications
	 */
	public ValueObject getValidatedValueObject(ModelDescription modelDescription)
	{
		return getValueObjectModel(modelDescription).getValidatedValueObject();
	}

	/**
	 * Retourne le valueObject de travail du vom d�sign� par modelDescription
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @return le valueObject de travail (clone contenant les modifications en
	 *         cours)
	 */
	public ValueObject getWorkingValueObject(ModelDescription modelDescription)
	{
		return getValueObjectModel(modelDescription).getWorkingValueObject();
	}

	/**
	 * Valide les modifications en cours sur le vom d�sign� par modelDescription
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 */
	public void validateChanges(ModelDescription modelDescription)
	{
		getValueObjectModel(modelDescription).validateChanges();
	}

	/**
	 * Annule les modifications en cours sur le vom d�sign� par modelDescription
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 */
	public void cancelChanges(ModelDescription modelDescription)
	{
		getValueObjectModel(modelDescription).cancelChanges();
	}

	/**
	 * Affecte le valueObject du vom d�sign� par modelDescription Le vom est
	 * cr�� s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject � affecter
	 */
	public void setValueObject(ModelDescription modelDescription, ValueObject valueObject)
	{
		getValueObjectModel(modelDescription).setValueObject(valueObject);
	}

	// --- m�thodes facade pour les VOLM

	/**
	 * Affecte la liste du volm d�sign� par modelDescription Le volm est cr��
	 * s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject � affecter
	 */
	public void setValueObjectList(ModelDescription modelDescription, List valueObjectList)
	{
		getValueObjectListModel(modelDescription).setValueObjectList(valueObjectList);
	}

	/**
	 * Retourne la liste contenue dans le volm d�sign� par modelDescription Le
	 * volm est cr�� s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject � affecter
	 */
	public List getValueObjectList(ModelDescription modelDescription)
	{
		return getValueObjectListModel(modelDescription).getValueObjectList();
	}

	// --- m�thodes facade pour les VM

	/**
	 * Affecte la liste du volm d�sign� par modelDescription Le volm est cr��
	 * s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject � affecter
	 */
	public void setValue(ModelDescription modelDescription, Object value)
	{
		getValueModel(modelDescription).setValue(value);
	}

	/**
	 * Retourne la liste contenue dans le volm d�sign� par modelDescription Le
	 * volm est cr�� s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject � affecter
	 */
	public Object getValue(ModelDescription modelDescription)
	{
		return getValueModel(modelDescription).getValue();
	}
}
