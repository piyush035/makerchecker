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
package com.rennover.hotel.common.valueobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rennover.hotel.common.helper.ReflectionHelper;

public class ClassMapping
{
	private static final String VERSION_COLUMN = "fwk_version";
	private static final String LOCK_USER_ID_COLUMN = "fwk_lock_user_id";
	private static final String DEACTIVATION_COLUMN = "fwk_deactivation_reason";
	private List fieldMappings = new ArrayList();
	private List pkFieldMappings = new ArrayList();
	private String filterColumn;
	private boolean filteringOnNull;
	private String filteringValue;
	private String inheritanceMapping;
	private List subClasses = new ArrayList();
	private String tableName;
	private String historisationTableName;
	private Class theClass;
	private AttributeMapping versionMapping;
	private boolean optimistLock;
	private boolean pessimistLock;
	private AttributeMapping lockAttributeMapping;
	private AttributeMapping deactivationAttributeMapping;
	private Map attributeByColumnIndex = new HashMap();
	private Map attributeByNameIndex = new HashMap();
	private String insertProcedureName;
	private String updateProcedureName;
	private String deleteProcedureName;
	private List subClassMappings = new ArrayList();
	private ClassMapping superClassMapping;
	private List codes = new ArrayList();  // codes correspondant aux sous classes pour les colonnes de filtrage
	private String pkJoinColumnName;  // nom de la colonne PK uniquement pour les tables avec mapping vertical

	public ClassMapping()
	{
	}

	public void setDescribedClass(Class aClass)
	{
		theClass = aClass;
	}

	public String getClassName()
	{
		return theClass.getName();
	}

	public Class getDescribedClass()
	{
		return theClass;
	}

	public void addFieldMappings(List fieldMappings)
	{
		this.fieldMappings.addAll(fieldMappings);
		createIndexes();
	}

	public void addFieldMapping(AttributeMapping fieldMapping)
	{
		this.fieldMappings.add(fieldMapping);
		createIndexes();
	}

	public List getFieldMappings()
	{
		return fieldMappings;
	}

	public void setFilterColumn(String filterColumn)
	{
		this.filterColumn = filterColumn;
	}

	public String getFilterColumn()
	{
		return filterColumn;
	}

	public void setFilteringOnNull(boolean filteringOnNull)
	{
		this.filteringOnNull = filteringOnNull;
	}

	public boolean isFiltered()
	{
		return "filtered".equals(inheritanceMapping);
	}

	public boolean isVertical()
	{
		return "vertical".equals(inheritanceMapping);
	}

	public boolean isHorizontal()
	{
		return "horizontal".equals(inheritanceMapping);
	}

	public boolean isFilteringOnNull()
	{
		return filteringOnNull;
	}

	public void setFilteringValue(String filteringValue)
	{
		if (isFiltered() && getFilterColumn() == null && filteringValue != null)
		{
			throw new MappingException("La colonne de filtrage doit être précisée pour spécifier une valeur de filtrage pour la class "
									   + theClass.getName());
		}
		if ((!isFiltered()) && (!isVertical()) && filteringValue != null)
		{
			throw new MappingException("On ne peut spécifier de valeur de filtrage que pour le mapping vertical ou filtré pour la classe "
									   + theClass.getName());
		}
		this.filteringValue = filteringValue;
		codes.add(filteringValue);
	}

	public String getFilteringValue()
	{
		return filteringValue;
	}

	public void setInheritanceMapping(String inheritanceMapping)
	{
		this.inheritanceMapping = inheritanceMapping;
	}

	public String getInheritanceMapping()
	{
		return inheritanceMapping;
	}

	public void setSubClasses(List subClasses)
	{
		this.subClasses = subClasses;
	}

	public List getSubClasses()
	{
		return subClasses;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setVersionColumn(String versionColumn)
	{
		if (versionColumn == null)
		{
			return;
		}
		if (getVersionMapping() == null)
		{
			setVersionMapping(new AttributeMapping());
			try
			{
				getVersionMapping().setField(ReflectionHelper.getField(getDescribedClass(), PersistentDomainObject.VERSION));
			}
			catch (NoSuchFieldException nsfe)
			{
				throw new MappingException("impossible de trouver le champ '" + PersistentDomainObject.VERSION + "' dans la classe " + getClassName(), 
										   nsfe);
			}
		}
		getVersionMapping().setColumnName(versionColumn);
	}

	public String getVersionColumn()
	{
		if (getVersionMapping() == null)
		{
			return null;
		}
		return getVersionMapping().getColumnName();
	}

	public void setOptimistLockSupported(boolean supported)
	{
		if (supported)
		{
			setVersionColumn(VERSION_COLUMN);
		}
		else
		{
			setVersionColumn(null);
		}
		optimistLock = supported;
	}

	public boolean isOptimistLock()
	{
		return optimistLock;
	}

	public boolean isPessimistLock()
	{
		return pessimistLock;
	}

	public void setPessimistLockSupported(boolean supported)
	{
		if (supported)
		{
			setVersionColumn(VERSION_COLUMN);
			setLockUserIdColumn(LOCK_USER_ID_COLUMN);
		}
		else
		{
			setVersionColumn(null);
			setLockUserIdColumn(null);
		}
		pessimistLock = supported;
	}

	public void setDeactivationSupported(boolean supported)
	{
		if (supported)
		{
			setDeactivationColumn(DEACTIVATION_COLUMN);
		}
		else
		{
			setDeactivationColumn(null);
		}
	}

	public void setDeactivationColumn(String columnName)
	{
		if (columnName == null)
		{
			return;
		}
		if (deactivationAttributeMapping == null)
		{
			deactivationAttributeMapping = new AttributeMapping();
			deactivationAttributeMapping.setAttributeName(PersistentDomainObject.DEACTIVATION_REASON);
			try
			{
				deactivationAttributeMapping.setField(ReflectionHelper.getField(theClass, PersistentDomainObject.DEACTIVATION_REASON));
			}
			catch (NoSuchFieldException nsfe)
			{
				throw new MappingException("Impossible de trouver l'attribut " + PersistentDomainObject.DEACTIVATION_REASON + " dans la classe"
										   + theClass.getName());
			}
		}
		deactivationAttributeMapping.setColumnName(columnName);
	}

	public AttributeMapping getDeactivationAttributeMapping()
	{
		return deactivationAttributeMapping;
	}

	public boolean isDeactivable()
	{
		return (deactivationAttributeMapping != null);
	}

	public AttributeMapping getLockAttributeMapping()
	{
		return lockAttributeMapping;
	}

	public void setLockUserIdColumn(String columnName)
	{
		if (columnName == null)
		{
			return;
		}
		if (getLockAttributeMapping() == null)
		{
			lockAttributeMapping = new AttributeMapping();
			try
			{
				getLockAttributeMapping().setField(ReflectionHelper.getField(getDescribedClass(), PersistentDomainObject.LOCK_USER_LOGIN));
			}
			catch (NoSuchFieldException nsfe)
			{
				throw new MappingException("impossible de trouver le field '" + PersistentDomainObject.LOCK_USER_LOGIN + "' dans la classe "
										   + getClassName(), nsfe);
			}
		}
		lockAttributeMapping.setColumnName(columnName);
	}

	public void setVersionMapping(AttributeMapping versionMapping)
	{
		this.versionMapping = versionMapping;
	}

	public AttributeMapping getVersionMapping()
	{
		return versionMapping;
	}

	public void createIndexes()
	{
		attributeByColumnIndex.clear();
		attributeByNameIndex.clear();
		pkFieldMappings.clear();
		Iterator fieldMappingsIt = fieldMappings.iterator();
		while (fieldMappingsIt.hasNext())
		{
			AttributeMapping mapping = (AttributeMapping) fieldMappingsIt.next();
			if (mapping.isPK())
			{
				pkFieldMappings.add(mapping);
			}
			attributeByColumnIndex.put(mapping.getColumnName(), mapping);
			attributeByNameIndex.put(mapping.getAttributeName(), mapping);
		}
		if (deactivationAttributeMapping != null)
		{
			attributeByColumnIndex.put(deactivationAttributeMapping.getColumnName(), deactivationAttributeMapping);
			attributeByNameIndex.put(deactivationAttributeMapping.getAttributeName(), deactivationAttributeMapping);
		}
		if (lockAttributeMapping != null)
		{
			attributeByColumnIndex.put(lockAttributeMapping.getColumnName(), lockAttributeMapping);
			attributeByNameIndex.put(lockAttributeMapping.getAttributeName(), lockAttributeMapping);
		}
	}

	public AttributeMapping getAttributeMappingForName(String name)
	{
		return (AttributeMapping) attributeByNameIndex.get(name);
	}

	public AttributeMapping getAttributeMappingForColumn(String columnName)
	{
		return (AttributeMapping) attributeByColumnIndex.get(columnName);
	}

	public String getInsertProcedureName()
	{
		return insertProcedureName;
	}

	public String getUpdateProcedureName()
	{
		return updateProcedureName;
	}

	public void setInsertProcedureName(String insertProcedureName)
	{
		this.insertProcedureName = insertProcedureName;
	}

	public void setUpdateProcedureName(String updateProcedureName)
	{
		this.updateProcedureName = updateProcedureName;
	}

	public String getDeleteProcedureName()
	{
		return deleteProcedureName;
	}

	public void setDeleteProcedureName(String deleteProcedureName)
	{
		this.deleteProcedureName = deleteProcedureName;
	}

	public boolean useProcedures()
	{
		if ((insertProcedureName != null) && (updateProcedureName != null) && (deleteProcedureName != null))
		{
			return true;
		}
		if ((insertProcedureName == null) && (updateProcedureName == null) && (deleteProcedureName == null))
		{
			return false;
		}
		throw new MappingException("Le mapping de la classe " + theClass.getName()
								   + " doit définir insertProcedureName ET updateProcedureName ET deleteProcedureName ou aucun des 3");
	}

	public List getSubClassMappings()
	{
		return subClassMappings;
	}

	public void addSubClassMapping(ClassMapping subClassMapping)
	{
		if ((subClassMapping.getFilteringValue() != null) && (codes.contains(subClassMapping.getFilteringValue())))
		{
			throw new MappingException("Le code " + subClassMapping.getFilteringValue() + " utilisé pour la colonne de filtrage pour la classe "
									   + subClassMapping.getClassName() + " est déjà utilisé dans le même graphe d'héritage");
		}
		subClassMappings.add(subClassMapping);
		codes.add(subClassMapping.getFilteringValue());
	}

	public String getHistorisationTableName()
	{
		if (historisationTableName != null)
		{
			return historisationTableName;
		}
		else
		{
			return "hist_" + tableName;
		}
	}

	public void setHistorisationTableName(String name)
	{
		historisationTableName = name;
	}

	public String getSnapshotDateColumnName()
	{
		return "snapshot_date";
	}

	public List getPkFieldMappings()
	{
		List result;
		if (superClassMapping != null)
		{
			result = new ArrayList();
			result.addAll(superClassMapping.getPkFieldMappings());
			Iterator pkFieldsMappingIt = pkFieldMappings.iterator();
			while (pkFieldsMappingIt.hasNext())
			{
				AttributeMapping pkFieldMapping = (AttributeMapping) pkFieldsMappingIt.next();
				if (!result.contains(pkFieldMapping))
				{
					result.add(pkFieldMapping);
				}
			}
			if (inheritanceMapping != null && inheritanceMapping.equals("vertical") && pkJoinColumnName != null)
			{
				if (result.size() != 1)
				{
					throw new MappingException("Les clefs composites ne sont pas supportées dans les classes utilisant le mapping vertical");
				}
				AttributeMapping superClassPkMapping = (AttributeMapping) result.get(0);
				AttributeMapping pkmapping = (AttributeMapping) superClassPkMapping.clone();
				pkmapping.setColumnName(pkJoinColumnName);
				result.set(0, pkmapping);
			}
		}
		else
		{
			result = pkFieldMappings;
		}
		return result;
	}

	public ClassMapping getSuperClassMapping()
	{
		return superClassMapping;
	}

	public void setSuperClassMapping(ClassMapping superClassMapping)
	{
		this.superClassMapping = superClassMapping;
	}

	public void setPkJoinColumnName(String string)
	{
		pkJoinColumnName = string;
	}
}