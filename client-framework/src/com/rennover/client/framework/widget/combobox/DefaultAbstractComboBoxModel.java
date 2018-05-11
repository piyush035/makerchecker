package com.rennover.client.framework.widget.combobox;

import javax.swing.AbstractListModel;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
/**
 * Cette classe permet d'adapter une List à une ComboBox. De plus elle gère un
 * élément représentant une valeur nulle.
 * 
 * @audit dattias 19/02/03
 */
public abstract class DefaultAbstractComboBoxModel extends AbstractListModel implements ComboBoxModelNew
{
	private SearchAlgorithm m_algorithm = null;

	private boolean m_useNullValue = false;

	private boolean m_useMandatoryValue = false;

	private Object m_nullValue = null;

	protected Object m_selectedObject = null;

	public DefaultAbstractComboBoxModel()
	{
	}

	public DefaultAbstractComboBoxModel(boolean useNullValue)
	{
		m_useNullValue = useNullValue;
	}

	public DefaultAbstractComboBoxModel(Object nullValue)
	{
		setNullValue(nullValue);
	}

	public DefaultAbstractComboBoxModel(SearchAlgorithm algorithm)
	{
		m_algorithm = algorithm;
	}

	/**
	 * Permet de modifier l'élément utilisé comme objet null
	 * 
	 * @param nullValue
	 *            objet nul
	 */
	public void setNullValue(Object nullValue)
	{
		m_useNullValue = true;
		m_nullValue = nullValue;
	}

	/**
	 * 
	 */
	public Object getNullValue()
	{
		return m_nullValue;
	}

	/**
	 * Permet d'activer l'utilisation de la valeur nulle
	 * 
	 * @param useNullValue
	 *            vrai = utilisation de la valeur nulle en plus des valeurs de
	 *            la liste
	 */
	public void setUseNullValue(boolean useNullValue)
	{
		m_useNullValue = useNullValue;
	}

	/**
	 * 
	 */
	public boolean getUseNullValue()
	{
		return m_useNullValue;
	}

	public void setUseMandatoryValue(boolean useMandatoryValue)
	{
		m_useMandatoryValue = useMandatoryValue;
	}

	/**
	 * 
	 */
	public boolean getUseMandatoryValue()
	{
		return m_useMandatoryValue;
	}

	/**
	 * Retourne l'élément sélectionné
	 */
	public Object getSelectedItem()
	{
		if (m_useMandatoryValue && m_selectedObject == m_nullValue)
		{
			return getDefaultItem();
		}
		return m_selectedObject;
	}

	public Object getDefaultItem()
	{
		if (getSize() > 1)
		{
			return getElementAt(0);
		} else
		{
			return m_nullValue;
		}
	}

	/**
	 * Indique l'élément sélectionné
	 */
	public void setSelectedItem(Object itemToSelect)
	{
		m_selectedObject = m_useMandatoryValue ? getDefaultItem() : m_nullValue;

		if (itemToSelect == null)
		{
			fireContentsChanged(this, -1, -1);
			return;
		}

		for (int i = 0; i < this.getSize(); i++)
		{
			Object currentItem = (Object) this.getElementAt(i);

			if (currentItem != null)
			{
				if (itemToSelect.equals(currentItem))
				{
					m_selectedObject = currentItem;
					fireContentsChanged(this, -1, -1);
					return;
				}
			}
		}
	}
}
