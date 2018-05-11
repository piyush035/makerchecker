
package com.rennover.client.framework.widget.combobox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * Un mod�le pour combo box qui offre les services suivants, en plus des
 * services habituels d'un {@link javax.swing.ComboBoxModel}:
 * 
 * <ul>
 * <li> ajout d'�l�ments, {@link #addElement(Object) individuellement} ou {@link
 * #setObjectList(List) en bloc} </li>
 * <li> affiche optionnellement une
 * {@link #setEmptyValueAvailable(boolean) valeur "vide"} (utile si on veut
 * permettre � l'utilisateur de ne pas faire de s�lection du tout) </li>
 * <li> {@link #selectEmptyValue() remise � z�ro de la s�lection de la combo},
 * que le mod�le poss�de une valeur "vide" ou non </li>
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
	 * Indique si le model se trouve en mode "un �l�ment 'pas de s�lection' est
	 * disponible pour l'utilisateur".
	 */
	public boolean isEmptyValueAvailable()
	{
		return m_emptyValueAvailable;
	}

	/**
	 * Indique si l'utilisateur a s�lectionn� l'�l�ment vide.
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
	 * Positionne le model en mode "un �l�ment 'pas de s�lection' est disponible
	 * pour l'utilisateur". En pratique, un �l�ment vide sera ajout� au d�but du
	 * mod�le.
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
	 * Positionne la s�lection sur la valeur "vide" de la collection. S'il n'y a
	 * pas de valeur vide (c-a-d que {@link #isEmptyValueAvailable()} retourne
	 * false), alors la s�lection est annul�e (c-�-d que rien n'est s�lectionn�
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
	 * Ajoute un �l�ment dans le mod�le. Si on se trouve en mode {@link
	 * #isEmptyValueAvailable() empty} et que le model �tait vide auparavent,
	 * alors un �l�ment de type "pas de s�lection" est ajout� au d�but.
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

	/**
	 * Retire un objet de la combo box sans envoyer d'�v�nement.
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
	 * Cette classe est ins�r�e dans le model pour permettre � l'utilisateur de
	 * s�lectionner un �l�ment sp�cial du type "pas de s�lection" ou "toute la
	 * s�lection", etc.
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
		 * Indique si l'objet est de type "pas de s�lection".
		 */
		public boolean isEmptyValue()
		{
			return EMPTY_SELECTION == m_type;
		}
	}
}