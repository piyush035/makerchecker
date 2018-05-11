
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
 * Classe à spécialiser pour l'adapter au contrôleur d'un écran
 * 
 * La classe doit permet de stocker les données métier utilisés dans un écran Le
 * PanelModel est souvent composé de ValueObjectModels et de
 * ValueObjectListModels Le PanelModel doit proposer une interface simple pour -
 * mettre à jour son contenu à partir des données récupérer par le contrôleur, -
 * et récupérer l'ensemble des données modifiées par l'écran.
 * 
 * Le PanelModel ne communique pas avec le serveur et ne connaît pas les détails
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
	 *             and volm Méthode à surcharger
	 * 
	 * Cette méthode doit initialiser toutes les sous-parties du modèle. Les
	 * sous-parties du modèle sont en général des ValueObjectModels ou des
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
	 * Ajoute et retourne un ValueObjectModel créé à partir du ModelDescription
	 * passé en paramètre
	 * 
	 * @param modelDescr
	 *            description du modèle à créer
	 * @return le vom créé
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
	 * Ajoute et retourne un ValueObjectModel créé à partir du ModelDescription
	 * passé en paramètre
	 * 
	 * @param modelDescr
	 *            description du modèle à créer
	 * @return le volm créé
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
	 * Ajoute et retourne un ValueModel créé à partir du ModelDescription passé
	 * en paramètre
	 * 
	 * @param modelDescr
	 *            description du modèle à créer
	 * @return le vm créé
	 */
	public ValueModel addValueModel(ModelDescription modelDescr)
	{
		ValueModel vm = new ValueModel(modelDescr.getObjectClass());
		m_valueModelMap.put(modelDescr, vm);
		return vm;
	}

	/**
	 * Retourne le ValueObjectModel associé au ModelDescription passé en
	 * paramètre Si le vom n'existe pas il est créé.
	 * 
	 * @param name
	 *            nom du vom
	 * @return vom recherché (ou créé s'il n'existait pas)
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
	 * Retourne le ValueObjectModel associé au ModelDescription passé en
	 * paramètre Si le vom n'existe pas il est créé.
	 * 
	 * @param name
	 *            nom du vom
	 * @return vom recherché (ou créé s'il n'existait pas)
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
	 * Retourne le ValueModel associé au ModelDescription passé en paramètre Si
	 * le vom n'existe pas il est créé.
	 * 
	 * @param name
	 *            nom du vm
	 * @return vm recherché (ou créé s'il n'existait pas)
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

	// --- méthodes facade pour les VOM

	/**
	 * Retourne le valueObject du vom décrit par modeldescrition après
	 * validation des changements
	 * 
	 * @param modelDescription
	 *            description du modèle recherché
	 * @return le valueObject avec les dernières modifications
	 */
	public ValueObject getValidatedValueObject(ModelDescription modelDescription)
	{
		return getValueObjectModel(modelDescription).getValidatedValueObject();
	}

	/**
	 * Retourne le valueObject de travail du vom désigné par modelDescription
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
	 * Valide les modifications en cours sur le vom désigné par modelDescription
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 */
	public void validateChanges(ModelDescription modelDescription)
	{
		getValueObjectModel(modelDescription).validateChanges();
	}

	/**
	 * Annule les modifications en cours sur le vom désigné par modelDescription
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 */
	public void cancelChanges(ModelDescription modelDescription)
	{
		getValueObjectModel(modelDescription).cancelChanges();
	}

	/**
	 * Affecte le valueObject du vom désigné par modelDescription Le vom est
	 * créé s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject à affecter
	 */
	public void setValueObject(ModelDescription modelDescription, ValueObject valueObject)
	{
		getValueObjectModel(modelDescription).setValueObject(valueObject);
	}

	// --- méthodes facade pour les VOLM

	/**
	 * Affecte la liste du volm désigné par modelDescription Le volm est créé
	 * s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject à affecter
	 */
	public void setValueObjectList(ModelDescription modelDescription, List valueObjectList)
	{
		getValueObjectListModel(modelDescription).setValueObjectList(valueObjectList);
	}

	/**
	 * Retourne la liste contenue dans le volm désigné par modelDescription Le
	 * volm est créé s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject à affecter
	 */
	public List getValueObjectList(ModelDescription modelDescription)
	{
		return getValueObjectListModel(modelDescription).getValueObjectList();
	}

	// --- méthodes facade pour les VM

	/**
	 * Affecte la liste du volm désigné par modelDescription Le volm est créé
	 * s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject à affecter
	 */
	public void setValue(ModelDescription modelDescription, Object value)
	{
		getValueModel(modelDescription).setValue(value);
	}

	/**
	 * Retourne la liste contenue dans le volm désigné par modelDescription Le
	 * volm est créé s'il n'existe pas
	 * 
	 * @param valueObjectName
	 *            nom du vom
	 * @param valueObject
	 *            valueObject à affecter
	 */
	public Object getValue(ModelDescription modelDescription)
	{
		return getValueModel(modelDescription).getValue();
	}
}
