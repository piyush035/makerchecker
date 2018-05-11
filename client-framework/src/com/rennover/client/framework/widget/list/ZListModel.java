package com.rennover.client.framework.widget.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import com.rennover.client.framework.widget.base.ComparatorHelper;

/**
 * Classe à utiliser pour tous les composants graphiques de type liste.
 * 
 * <p>
 * Si cette liste est amenée à afficher des {@link
 * com.rennover.hotel.common.valueobject.ValueObject}s, s'assurer que le widget
 * est bien une
 * {@link com.agefospme.nsicm.client.common.valueobjectpanel.VOList}. <br>
 * Dans ce cas, il faudra également se demander si le contenu de la liste doit
 * être placé dans un autre ValueObject. Si oui, alors il faudra implémenter un
 * VOListModel dans le package
 * {@link com.agefospme.nsicm.client.common.valueobjectpanel}.
 * </p>
 */
public class ZListModel extends AbstractListModel
{
	private List m_objectList = new ArrayList();

	/**
	 * Retourne le nombre d'éléments affichés dans la combo.
	 */
	public int getSize()
	{
		return m_objectList.size();
	}

	public Object getElementAt(int index)
	{
		return m_objectList.get(index);
	}

	public void addElement(Object element)
	{
		if (element != null)
		{
			getObjectList().add(element);
			int index = getObjectList().indexOf(element);
			fireIntervalAdded(this, index, index);
		}
	}

	public void addElementAt(Object element, int index)
	{
		if (element != null)
		{
			getObjectList().add(index, element);
			fireIntervalAdded(this, index, index);
		}
	}

	public void removeElement(Object element)
	{
		if (element != null)
		{
			int index = getObjectList().indexOf(element);
			getObjectList().remove(element);
			fireIntervalRemoved(this, index, index);
		}
	}

	public void moveElement(int indexFrom, int indexTo)
	{
		Object object = removeElement(indexFrom);
		addElementAt(object, indexTo);
	}

	public int moveElement(int indexFrom, boolean up)
	{
		int indexTo = indexFrom;
		if (up)
		{
			if (indexFrom > 0)
			{
				indexTo--;
			}
		} else
		{
			if (indexFrom < getSize() - 1)
			{
				indexTo++;
			}
		}

		if (indexFrom != indexTo)
		{
			Object object = removeElement(indexFrom);
			addElementAt(object, indexTo);
		}

		return indexTo;
	}

	public Object removeElement(int index)
	{
		Object object = getObjectList().get(index);
		getObjectList().remove(index);
		fireIntervalRemoved(this, index, index);
		return object;
	}

	public void addElements(List elements)
	{
		if (elements != null)
		{
			getObjectList().addAll(elements);
			fireContentsChanged(this, -1, -1);
		}
	}

	public void removeElements(List elements)
	{
		if (elements != null)
		{
			getObjectList().removeAll(elements);
			fireContentsChanged(this, -1, -1);
		}
	}

	public void sort()
	{
		List list = getObjectList();
		Collections.sort(list, ComparatorHelper.createComparator());
		fireContentsChanged(this, -1, -1);
	}

	/**
	 * Met à jour toutes les données de la combo. Toutes les données
	 * précédemment disponibles dans la combo sont perdues.
	 */
	public void setObjectList(List list)
	{
		m_objectList = list != null ? list : Collections.EMPTY_LIST;
		this.fireContentsChanged(this, -1, -1);
	}

	public List getObjectList()
	{
		return m_objectList;
	}

	public void fireContentsChanged(Object source, int index0, int index1)
	{
		super.fireContentsChanged(source, index0, index1);
	}
}