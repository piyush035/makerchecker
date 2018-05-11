package com.rennover.client.framework.widget.field;

import java.util.List;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZList;
import com.rennover.client.framework.widget.list.ZListModel;

public class ListField extends ZList implements InputField
{
	private boolean m_mandatoryField = false;

	private ZLabel m_associatedLabel;

	private boolean m_validField = true;

	private boolean m_sorted = true;

	public ListField()
	{
		super();
	}

	public List getObjectList()
	{
		return ((ZListModel) getModel()).getObjectList();
	}

	public void setObjectList(List objectList)
	{
		((ZListModel) getModel()).setObjectList((List) objectList);
		if (isSorted())
		{
			sort();
		}
	}

	/**
	 * Permet d'ajouter une liste d'éléments
	 * 
	 * @param elements
	 *            les élements à ajouter
	 */
	public void addElements(List elements)
	{
		ZListModel model = (ZListModel) getModel();
		model.addElements(elements);
		if (isSorted())
		{
			sort();
		}
	}

	/**
	 * Permet de supprimer une liste d'éléments
	 * 
	 * @param elements
	 *            les élements à supprimer
	 */
	public void removeElements(List elements)
	{
		ZListModel model = (ZListModel) getModel();
		model.removeElements(elements);
		if (isSorted())
		{
			sort();
		}
	}

	/**
	 * Déplace le premier élément sélectionné d'une position vers le haut
	 * 
	 */
	public void moveSelectedUp()
	{
		ZListModel model = (ZListModel) getModel();
		int newIndex = model.moveElement(getSelectedIndex(), true);
		setSelectedIndex(newIndex);
	}

	/**
	 * Déplace le premier élément sélectionné d'une position vers le bas
	 * 
	 */
	public void moveSelectedDown()
	{
		ZListModel model = (ZListModel) getModel();
		int newIndex = model.moveElement(getSelectedIndex(), false);
		setSelectedIndex(newIndex);
	}

	/**
	 * Permet de trier la liste d'éléments par ordre alphabètique
	 * 
	 */
	public void sort()
	{
		ZListModel model = (ZListModel) getModel();
		model.sort();
	}

	/**
	 * Affecte le label associé au composant
	 */
	public void setAssociatedLabel(ZLabel associatedLabel)
	{
		m_associatedLabel = associatedLabel;
		InputFieldHelper.doDisplayState(this);
	}

	/**
	 * Retourne le label associé au composant
	 */
	public ZLabel getAssociatedLabel()
	{
		return m_associatedLabel;
	}

	/**
	 * Permet d'indiquer que
	 */
	public void setMandatoryField(boolean mandatory)
	{
		m_mandatoryField = mandatory;
	}

	public boolean isMandatoryField()
	{
		return m_mandatoryField;
	}

	public void setValidField(boolean valid)
	{
		m_validField = valid;
		InputFieldHelper.doDisplayValid(this, valid);
	}

	public boolean isValidValue()
	{
		return true;
	}

	public boolean isValidField()
	{
		return m_validField;
	}

	public boolean isValueSetted()
	{
		return true;
	}

	public Object getValue()
	{
		return getObjectList();
	}

	public void setValue(Object value)
	{
		setObjectList((List) value);
	}

	public boolean isReadOnly()
	{
		return InputFieldHelper.isReadOnly(this);
	}

	public void setReadOnly(boolean readOnly)
	{
		InputFieldHelper.setReadOnly(this, readOnly);
	}

	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		InputFieldHelper.doDisplayState(this);
	}

	/**
	 * permet de rendre le composant et son label visible ou invisible
	 */
	public void setVisible(boolean aFlag)
	{
		super.setVisible(aFlag);
		if (m_associatedLabel != null)
		{
			m_associatedLabel.setVisible(aFlag);
		}
	}

	/**
	 * Indique si le tri automatique est actif
	 * 
	 * @return true si le tri automatique est actif
	 */
	public boolean isSorted()
	{
		return m_sorted;
	}

	/**
	 * Active le tri automatique de la liste d'éléments
	 * 
	 * @param
	 */
	public void setSorted(boolean sorted)
	{
		m_sorted = sorted;
	}
}