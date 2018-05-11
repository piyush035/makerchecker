
package com.rennover.client.framework.widget.combobox;

import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import com.rennover.client.framework.widget.base.ComparatorHelper;
import com.rennover.hotel.common.exception.IncoherenceException;

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
public class ComboBoxModelForList extends AbstractListModel implements SortableComboBoxModel
{
	private SearchAlgorithm m_algorithm = null;

	private boolean m_useNullValue = false;

	private boolean m_useMandatoryValue = false;

	protected Object m_nullValue = null;

	private List m_itemList = null;

	protected Object m_selectedObject = null;

	public ComboBoxModelForList(List items)
	{
		setItems(items);
	}

	public ComboBoxModelForList(List items, boolean useNullValue)
	{
		setItems(items);
		m_useNullValue = useNullValue;
	}

	public ComboBoxModelForList(List items, Object nullValue)
	{
		setItems(items);
		setNullValue(nullValue);
	}

	public ComboBoxModelForList(SearchAlgorithm algorithm)
	{
		m_algorithm = algorithm;
	}

	public void sort()
	{
		List list = getItems();
		if (list != null)
		{
			Collections.sort(list, ComparatorHelper.createComparator());
			fireContentsChanged(this, -1, -1);
		}
	}

	/**
	 * Permet de modifier l'élément utilisé comme objet null
	 * 
	 * @param nullValue
	 *            objet nul
	 */
	public void setNullValue(Object nullValue)
	{
		m_nullValue = nullValue;
		setUseNullValue(true);
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
		if (m_useNullValue != useNullValue)
		{
			m_useNullValue = useNullValue;
			fireContentsChanged(this, 0, 0);
		}
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
	 * Modification de la liste et de l'utilisation de la valeur nulle
	 */
	public void setItems(List items, boolean useNullValue)
	{
		setItems(items);
		setUseNullValue(useNullValue);
	}

	public void setItems(List items, boolean useNullValue, boolean sort)
	{
		if (sort && items != null)
		{
			Collections.sort(items);
		}
		setItems(items);
		setUseNullValue(useNullValue);
	}

	/**
	 * Modification de la liste des élements
	 * 
	 * @change faire un fireContentChanged, c'est plus rapide
	 * @change size ou size-1 ?
	 */
	public void setItems(List items)
	{
		int size = getSize();
		m_itemList = null;
		fireIntervalRemoved(this, 0, size);

		m_itemList = items;
		fireIntervalAdded(this, 0, getSize());
	}

	/**
	 * Retourne les éléments de la liste
	 */
	public List getItems()
	{
		return m_itemList;
	}

	void useSearchAlgorithm(String searchValue)
	{
		try
		{
			m_itemList = m_algorithm.search(searchValue);
		} catch (SearchAlgorithmException exc)
		{
			m_itemList = Collections.singletonList(exc.getMessage());
		}

		this.fireContentsChanged(this, 0, getSize() - 1);
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
		if (getSize() > 0)
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

		throw new IncoherenceException(
		        "setValue impossible car la valeur passé ne correspond à aucun élément de la liste ");
	}

	public Object getElementAt(int index)
	{
		// NB: la valeur nulle a toujours l'index 0, lorsqu'elle est active
		if ((index == 0) && m_useNullValue)
		{
			return m_nullValue;
		}

		index = m_useNullValue ? (index - 1) : index;

		return m_itemList.get(index);
	}

	public int getSize()
	{
		int nbItems = (m_itemList == null) ? 0 : m_itemList.size();
		return m_useNullValue ? (nbItems + 1) : nbItems;
	}
}
