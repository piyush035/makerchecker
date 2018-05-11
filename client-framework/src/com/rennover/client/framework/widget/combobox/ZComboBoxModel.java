
package com.rennover.client.framework.widget.combobox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * Un modèle pour combo box qui offre les services suivants, en plus des
 * services habituels d'un {@link javax.swing.ComboBoxModel}:
 * 
 * <ul>
 * <li> ajout d'éléments, {@link #addElement(Object) individuellement} ou {@link
 * #setObjectList(List) en bloc} </li>
 * <li> affiche optionnellement une
 * {@link #setEmptyValueAvailable(boolean) valeur "vide"} (utile si on veut
 * permettre à l'utilisateur de ne pas faire de sélection du tout) </li>
 * <li> {@link #selectEmptyValue() remise à zéro de la sélection de la combo},
 * que le modèle possède une valeur "vide" ou non </li>
 * </ul>
 */
public class ZComboBoxModel extends AbstractListModel implements ComboBoxModel
{
	private ArrayList m_objects;

	private boolean m_emptyValueAvailable = false;

	private Object m_selectedObject = null;

	public ZComboBoxModel()
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
	 * Indique si le model se trouve en mode "un élément 'pas de sélection' est
	 * disponible pour l'utilisateur".
	 */
	public boolean isEmptyValueAvailable()
	{
		return m_emptyValueAvailable;
	}

	/**
	 * Indique si l'utilisateur a sélectionné l'élément vide.
	 */
	public boolean isEmptyValueSelected()
	{
		Object obj = getSelectedItem();
		if (obj instanceof SpecialValue)
		{
			SpecialValue value = (SpecialValue) obj;
			return value.isEmptyValue();
		}
		return false;
	}

	/**
	 * Positionne le model en mode "un élément 'pas de sélection' est disponible
	 * pour l'utilisateur". En pratique, un élément vide sera ajouté au début du
	 * modèle.
	 */
	public void setEmptyValueAvailable(boolean emptyValueAvailable)
	{
		if (m_emptyValueAvailable == emptyValueAvailable)
		{
			return;
		}
		if (m_emptyValueAvailable == true)
		{
			if (getSize() > 0)
			{
				removeElementAtInternal(0);
			}
		} else
		{
			// store all elements
			ArrayList list = new ArrayList(getSize());
			for (int i = 0; i < getSize(); i++)
			{
				list.add(getElementAt(i));
			}
			removeAllElements();
			for (int i = 0; i < list.size(); i++)
			{
				addElement(list.get(i));
			}
		}
		m_emptyValueAvailable = emptyValueAvailable;
	}

	/**
	 * Positionne la sélection sur la valeur "vide" de la collection. S'il n'y a
	 * pas de valeur vide (c-a-d que {@link #isEmptyValueAvailable()} retourne
	 * false), alors la sélection est annulée (c-à-d que rien n'est sélectionné
	 * et {@link #getSelectedItem()} retourne null).
	 */
	public void selectEmptyValue()
	{
		if (isEmptyValueAvailable())
		{
			setSelectedItem(SpecialValue.EMPTY_VALUE);
		} else
		{
			setSelectedItem(null);
		}
	}

	/**
	 * Ajoute un élément dans le modèle. Si on se trouve en mode {@link
	 * #isEmptyValueAvailable() empty} et que le model était vide auparavent,
	 * alors un élément de type "pas de sélection" est ajouté au début.
	 */
	public void addElement(Object anObject)
	{
		if (m_emptyValueAvailable)
		{
			if (getSize() == 0 && !(anObject instanceof SpecialValue))
			{
				addElementInternal(SpecialValue.EMPTY_VALUE);
			}
		}

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

	/**
	 * Retire un objet de la combo box sans envoyer d'évènement.
	 */
	private void removeElementAtInternal(int index)
	{
		if (getElementAt(index) == m_selectedObject)
		{
			if (index == 0)
			{
				setSelectedItem(getSize() == 1 ? null : getElementAt(index + 1));
			} else
			{
				setSelectedItem(getElementAt(index - 1));
			}
		}

		m_objects.remove(index);
	}

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

	/**
	 * Cette classe est insérée dans le model pour permettre à l'utilisateur de
	 * sélectionner un élément spécial du type "pas de sélection" ou "toute la
	 * sélection", etc.
	 */
	protected static class SpecialValue
	{
		static final int EMPTY_SELECTION = 0;

		private static final SpecialValue EMPTY_VALUE = new SpecialValue(EMPTY_SELECTION);

		private int m_type;

		SpecialValue(int type)
		{
			m_type = type;
		}

		public String toString()
		{
			return "";
		}

		/**
		 * Indique si l'objet est de type "pas de sélection".
		 */
		public boolean isEmptyValue()
		{
			return EMPTY_SELECTION == m_type;
		}
	}
}