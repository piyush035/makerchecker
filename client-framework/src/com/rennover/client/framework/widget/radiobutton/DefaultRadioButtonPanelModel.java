package com.rennover.client.framework.widget.radiobutton;

/**
 * @audit TCA : pourquoi le mod�le est-il d�pendant de la widget ?
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
	 * Remplace toutes les donn�es de la combo par les donn�es sp�cifi�es.
	 */
	public void setObjectList(List list)
	{
		removeAllElementsInternal();
		addObjectList(list);
	}

	/**
	 * Ajoute des donn�es � la suite des donn�es d�j� pr�sentes dans le mod�le.
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
	 * Ajoute un �l�ment dans le mod�le. Si on se trouve en mode {@link
	 * #isEmptyValueAvailable() empty} et que le model �tait vide auparavent,
	 * alors un �l�ment de type "pas de s�lection" est ajout� au d�but.
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
	 * Ajoute un �l�ment sans envoyer d'�v�nement aux listeners.
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
	 * Retire tous les �l�ments de la liste sans envoyer d'�v�nement aux
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
	 * Retourne l'objet s�lectionn�, ou le dernier objet positionn� avec {@link
	 * #setSelectedItem(Object)} si cette m�thode a �t� appel�e apr�s la
	 * derni�re fois qu'une s�lection manuelle a �t� faite.
	 */
	public Object getSelectedItem()
	{
		return m_selectedObject;
	}

	/**
	 * Positionne la valeur de l'�l�ment s�lectionn�. <br>
	 * Si cet �l�ment fait partie des objets de la combo box, la s�lection dans
	 * la liste change en fonction de cet �l�ment. <br>
	 * Si l'�l�ment n'est pas connu, alors aucun �l�ment n'est s�lectionn� dans
	 * la liste d'objets; par contre, la m�thode {@link #getSelectedItem()}
	 * retournera l'objet pass� en param�tre de cette m�thode-ci.
	 * 
	 * @param anObject
	 *            la valeur de la combo box ou null pour vider la s�lection
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