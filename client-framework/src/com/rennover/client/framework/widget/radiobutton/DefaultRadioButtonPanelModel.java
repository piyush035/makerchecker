package com.rennover.client.framework.widget.radiobutton;

/**
 * @audit TCA : pourquoi le modèle est-il dépendant de la widget ?
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class DefaultRadioButtonPanelModel extends AbstractListModel implements ComboBoxModel
{
	private ArrayList m_objects;

	private Object m_selectedObject = null;

	public DefaultRadioButtonPanelModel()
	{
		m_objects = new ArrayList(10);
	}

	/**
	 * Remplace toutes les données de la combo par les données spécifiées.
	 */
	public void setObjectList(List list)
	{
		removeAllElementsInternal();
		addObjectList(list);
	}

	/**
	 * Ajoute des données à la suite des données déjà présentes dans le modèle.
	 */
	public void addObjectList(List list)
	{
		Iterator iterator = list.iterator();
		while (iterator.hasNext())
		{
			Object element = iterator.next();
			addElementInternal(element);
		}
		this.fireContentsChanged(this, -1, -1);
	}

	/**
	 * Ajoute un élément dans le modèle. Si on se trouve en mode {@link
	 * #isEmptyValueAvailable() empty} et que le model était vide auparavent,
	 * alors un élément de type "pas de sélection" est ajouté au début.
	 */
	public void addElement(Object anObject)
	{
		addElementInternal(anObject);
		fireIntervalAdded(this, m_objects.size() - 1, m_objects.size() - 1);
		if (m_objects.size() == 1 && m_selectedObject == null && anObject != null)
		{
			setSelectedItem(anObject);
		}
	}

	/**
	 * Ajoute un élément sans envoyer d'évènement aux listeners.
	 */
	private void addElementInternal(Object anObject)
	{
		m_objects.add(anObject);
	}

	public void removeAllElements()
	{
		if (m_objects.size() > 0)
		{
			int firstIndex = 0;
			int lastIndex = m_objects.size() - 1;
			removeAllElementsInternal();
			fireIntervalRemoved(this, firstIndex, lastIndex);
		}
	}

	/**
	 * Retire tous les éléments de la liste sans envoyer d'évènement aux
	 * listeners.
	 */
	private void removeAllElementsInternal()
	{
		if (m_objects.size() > 0)
		{
			m_objects.clear();
			m_selectedObject = null;
		}
	}

	/**
	 * Retourne l'objet sélectionné, ou le dernier objet positionné avec {@link
	 * #setSelectedItem(Object)} si cette méthode a été appelée après la
	 * dernière fois qu'une sélection manuelle a été faite.
	 */
	public Object getSelectedItem()
	{
		return m_selectedObject;
	}

	/**
	 * Positionne la valeur de l'élément sélectionné. <br>
	 * Si cet élément fait partie des objets de la combo box, la sélection dans
	 * la liste change en fonction de cet élément. <br>
	 * Si l'élément n'est pas connu, alors aucun élément n'est sélectionné dans
	 * la liste d'objets; par contre, la méthode {@link #getSelectedItem()}
	 * retournera l'objet passé en paramètre de cette méthode-ci.
	 * 
	 * @param anObject
	 *            la valeur de la combo box ou null pour vider la sélection
	 */
	public void setSelectedItem(Object anObject)
	{
		if ((m_selectedObject != null && !m_selectedObject.equals(anObject)) || m_selectedObject == null
		        && anObject != null)
		{
			m_selectedObject = anObject;
			fireContentsChanged(this, -1, -1);
		}
	}

	// removed the commented method internalRemoveElementAt(int index)

	public Object getElementAt(int index)
	{
		if (index >= 0 && index < m_objects.size())
		{
			return m_objects.get(index);
		} else
		{
			return null;
		}
	}

	public int getSize()
	{
		return m_objects.size();
	}
}