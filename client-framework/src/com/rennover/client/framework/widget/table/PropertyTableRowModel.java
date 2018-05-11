package com.rennover.client.framework.widget.table;

import java.util.Date;
import java.util.List;

import com.rennover.hotel.common.helper.ReflectionHelper;
import com.rennover.hotel.common.validation.ValidationRules;
import com.rennover.hotel.common.validation.ValidationService;

/**
 * Implementation de tableRowModel utilisée lorsque l'on spécifie une liste de
 * propriétés de VO correspondant aux colonnes de la table.
 */
public class PropertyTableRowModel extends TableRowModel
{
	private boolean m_DEBUG = false;

	private Class m_valueObjectClass;

	private String[] m_propertyNames;

	private String[] m_columnNames;

	private String[] m_tooltips;

	private Class[] m_columnTypes;

	private ColumnSize[] m_columnSizes = null;

	public PropertyTableRowModel(Class valueObjectClass, List propertyList, List columnList)
	{
		this(valueObjectClass, (String[]) columnList.toArray(new String[0]), (String[]) propertyList
		        .toArray(new String[0]), null, null);
	}

	public PropertyTableRowModel(Class valueObjectClass, List propertyList, List columnList, List widthList)
	{
		this(valueObjectClass, (String[]) columnList.toArray(new String[0]), (String[]) propertyList
		        .toArray(new String[0]),
		        widthList == null ? null : (ColumnSize[]) widthList.toArray(new ColumnSize[0]), null);
	}

	public PropertyTableRowModel(Class valueObjectClass, String[] columnNames, String[] propertyNames)
	{
		this(valueObjectClass, columnNames, propertyNames, null, null);
	}

	public PropertyTableRowModel(Class valueObjectClass, String[] columnNames, String[] propertyNames,
	        ColumnSize[] widthList)
	{
		this(valueObjectClass, columnNames, propertyNames, widthList, null);
	}

	public PropertyTableRowModel(Class valueObjectClass, String[] columnNames, String[] propertyNames,
	        String[] tooltipList)
	{
		this(valueObjectClass, columnNames, propertyNames, null, tooltipList);
	}

	public PropertyTableRowModel(Class valueObjectClass, String[] columnNames, String[] propertyNames,
	        ColumnSize[] widthList, String[] tooltipList)
	{
		m_valueObjectClass = valueObjectClass;
		m_columnNames = columnNames;
		m_propertyNames = propertyNames;

		m_columnTypes = getDefaultColumnTypes();

		if (widthList != null)
		{
			m_columnSizes = widthList;
		} else
		{
			m_columnSizes = getDefaultColumnsSize();
		}

		if (tooltipList != null)
		{
			m_tooltips = tooltipList;
		}
	}

	protected Class[] getDefaultColumnTypes()
	{
		int nbProperties = m_propertyNames.length;
		Class[] columnTypes = new Class[nbProperties];
		for (int i = 0; i < nbProperties; i++)
		{
			Class objectType = ReflectionHelper.getPropertyType(m_valueObjectClass, m_propertyNames[i]);
			columnTypes[i] = ReflectionHelper.getOnlyObjectType(objectType);
		}

		return columnTypes;
	}

	protected Class[] getColumnTypes()
	{
		return m_columnTypes;
	}

	public String[] getTitles()
	{
		return m_columnNames;
	}

	public ColumnSize[] getColumnsSize()
	{
		return m_columnSizes;
	}

	public Object getValueAt(int columnIndex, Object object)
	{
		return ReflectionHelper.getProperty(object, m_propertyNames[columnIndex]);
	}

	public void setValueAt(int columnIndex, Object object, Object value)
	{
		if (m_DEBUG)
		{
			System.out.println(" " + m_propertyNames[columnIndex] + " = " + value);
		}
		ReflectionHelper.setProperty(object, m_propertyNames[columnIndex], value);
	}

	protected ColumnSize[] getDefaultColumnsSize()
	{
		int charSize = 10;

		ColumnSize[] columnSizes = new ColumnSize[m_propertyNames.length];

		if (m_DEBUG)
		{
			System.out.println("valueObjectClass  " + m_valueObjectClass);
		}
		for (int i = 0; i < m_propertyNames.length; i++)
		{
			String propertyName = m_propertyNames[i];
			ValidationRules rules = ValidationService.getPropertyValidationRules(m_valueObjectClass, propertyName);

			Long sizeMaxLong = rules == null ? null : rules.getSizeMax();
			Long sizeMinLong = rules == null ? null : rules.getSizeMin();

			Integer sizeMax = sizeMaxLong == null ? null : new Integer(sizeMaxLong.intValue());
			Integer sizeMin = sizeMinLong == null ? null : new Integer(sizeMinLong.intValue());

			Class type = getColumnTypes()[i];

			if (sizeMax != null)
			{
				sizeMax = new Integer(sizeMax.intValue() * charSize);
			}

			if (sizeMin != null)
			{
				sizeMin = new Integer(sizeMin.intValue() * charSize);
			}

			boolean resizable = true;

			if (sizeMin == null && sizeMax == null && Date.class.isAssignableFrom(type))
			{
				int size = 80;
				sizeMin = new Integer(size);
				sizeMax = new Integer(size);
				resizable = false;
			}

			if (m_DEBUG)
			{
				System.out.println("propertyName  " + propertyName + " type = " + type + " sizeMin = " + sizeMin
				        + " sizeMax = " + sizeMax);
			}

			if (sizeMin != null || sizeMax != null)
			{
				if (sizeMax != null && sizeMax.intValue() <= 80)
				{
					resizable = false;
				}
				columnSizes[i] = new ColumnSize(sizeMin, sizeMax, resizable ? null : sizeMax);
			} else
			{
				columnSizes[i] = null;
			}
		}

		return columnSizes;
	}

	/**
	 * @return
	 */
	protected String[] getTooltips()
	{
		return m_tooltips;
	}

	/**
	 * @param strings
	 */
	public void setTooltips(String[] strings)
	{
		m_tooltips = strings;
	}
}
