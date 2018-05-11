package com.rennover.client.framework.widget.combobox;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import com.rennover.client.framework.widget.base.ZComboBox;
import com.rennover.hotel.common.exception.IncoherenceException;

/**
 * @author tcaboste
 * 
 * ComboBox permettant de g�rer un liste d'items sous la forme d'une List et
 * permettant de g�rer un �l�ment nul.
 * 
 * Cette ComboBox est associ�e au renderer NullListCellRenderer qui affiche les
 * objets null avec un cha�ne de caract�res particuli�re
 * 
 * @audit dattias 19/02/03
 * @change red�finir setModel pour v�rifier que le model pass� est de type
 *         ComboBoxModelForList
 * 
 */
public class ComboBoxForList extends ZComboBox
{
	private boolean m_useSearchAlgorithm;

	private boolean m_useAutoCompletion;

	// indicateur de tri automatique
	private boolean m_sorted = true;

	/**
	 * Constructeur par d�faut
	 */
	public ComboBoxForList()
	{
		this(new ArrayList());
	}

	/**
	 * Constructeur � partir d'une liste d'items. La combobox utilisera
	 * directement la liste pass�e en param�tres
	 */
	public ComboBoxForList(List items)
	{
		this(items, false, NullListCellRenderer.DEFAULT_NULL_STRING);
	}

	/**
	 * Constructeur � partir d'une liste d'items et le libell� des valeurs
	 * nulles La combobox utilisera directement la liste pass�e en param�tres.
	 * et affichera le libell� pour les valeurs nulles
	 * 
	 * @param items
	 *            �l�ments de la liste
	 * @param useNullValue
	 *            activation de la possibilit� de choisir la valeur nulle
	 * @param stringForNullValue
	 *            libell� (� afficher) de la valeur nulle
	 */
	public ComboBoxForList(List items, boolean useNullValue, String stringForNullValue)
	{
		super(new ComboBoxModelForList(items, useNullValue));
		this.setRenderer(new NullListCellRenderer(stringForNullValue));
	}

	/**
	 * Constructeur � partir d'une liste d'items et d'un objet repr�sentant la
	 * valeur nulle La combobox utilisera directement la liste pass�e en
	 * param�tres. et utilisera l'objet de nullit� en plus des �l�ments de la
	 * liste
	 * 
	 * @param items
	 *            �l�ments de la liste
	 * @param nullValue
	 *            objet repr�sentant une valeur nulle
	 */
	public ComboBoxForList(List items, Object nullValue)
	{
		super(new ComboBoxModelForList(items, nullValue));
	}

	/**
	 * Constructeur � partir d'un algo de recherche. C'est l'algo qui d�termine
	 * la liste des valeurs en fonction de la string saisie dans la combo. Combo
	 * est forcement �ditable.
	 * 
	 * @param alogorithm
	 *            un alorithme de recherche
	 */
	public ComboBoxForList(SearchAlgorithm algorithm)
	{
		super(new ComboBoxModelForList(algorithm));
		TextComboBoxEditor editor = new TextComboBoxEditor();
		SearchAlgorithmAdapter adapter = new SearchAlgorithmAdapter(this);
		editor.getDocument().addDocumentListener(adapter);
		this.setEditor(editor);
		this.setEditable(true);
		m_useSearchAlgorithm = true;
	}

	public void setEditor(ComboBoxEditor editor)
	{
		if (m_useSearchAlgorithm || m_useAutoCompletion)
		{
			throw new IllegalStateException("Imcompatible avec un algotithme de recherche ou l'autocompletion");
		}

		super.setEditor(editor);
	}

	public void setAutoCompletion(boolean activate)
	{
		if (activate)
		{
			setEditor(new AutocompletionComboBoxEditor(this));

			// plac� ici sinon le setEditor pr�c�dent ne marche pas
			m_useAutoCompletion = true;
		} else
		{
			// plac� ici sinon le setEditor suivant ne marche pas
			m_useAutoCompletion = false;
			setEditor(new BasicComboBoxEditor());
		}
	}

	public void setSelectedItem(Object item)
	{
		if (!containsItem(item))
		{
			throw new IncoherenceException("setSelectedItem impossible car la valeur '" + item
			        + "' ne correspond � aucun �l�ment de la liste ");
		}

		super.setSelectedItem(item);
		updateTooltip();
	}

	/**
	 * Indique l'�l�ment s�lectionn�
	 */
	public boolean containsItem(Object item)
	{
		ComboBoxModelNew model = (ComboBoxModelNew) getModel();

		if (item == null)
		{
			boolean contains = !model.getUseMandatoryValue();
			return contains;
		}

		for (int i = 0; i < model.getSize(); i++)
		{
			Object currentItem = (Object) model.getElementAt(i);

			if (currentItem != null && currentItem.equals(item))
			{
				return true;
			} else
			{
				if (item == null)
				{
					return true;
				}
			}
		}

		return false;
	}

	protected void updateTooltip()
	{
		Object item = getSelectedItem();

		String nullString = getStringForNullValue();

		setToolTipText(item != null ? item.toString() : nullString);
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
	 * Active le tri automatique de la liste d'�l�ments
	 * 
	 * @param
	 */
	public void setSorted(boolean sorted)
	{
		m_sorted = sorted;
	}

	/**
	 * Permet de trier la liste d'�l�ments par ordre alphab�tique
	 * 
	 */
	public void sort()
	{
		ComboBoxModel model = (ComboBoxModel) getModel();
		if (model instanceof SortableComboBoxModel)
		{
			((SortableComboBoxModel) model).sort();
		} else
		{
			throw new IllegalStateException("sort is not implemented");
		}
	}

	/**
	 * Permet de mettre � jour la liste des �lements de la liste.
	 * 
	 * @param list
	 *            nouvelle liste des �l�ments
	 */
	public void setObjectList(List list)
	{
		checkNoSearchAlgorithm();
		ComboBoxModelForList model = (ComboBoxModelForList) getModel();
		model.setItems(list);
		if (isSorted())
		{
			sort();
		}
	}

	/**
	 * Permet de mettre � jour la liste des �lements de la liste.
	 * 
	 * @param list
	 *            nouvelle liste des �l�ments
	 */
	public void setObjectList(List list, boolean useNullValue, boolean sort)
	{
		checkNoSearchAlgorithm();
		ComboBoxModelForList model = (ComboBoxModelForList) getModel();
		model.setItems(list, useNullValue, sort);
		setSorted(sort);
	}

	/**
	 * Permet de r�cup�rer la liste des �l�ments afficher par la combo. Cette
	 * liste ne contient pas l'�l�ment null.
	 * 
	 * @return liste des �l�ments
	 */
	public List getObjectList()
	{
		ComboBoxModelForList model = (ComboBoxModelForList) getModel();
		return model.getItems();
	}

	/**
	 * permet de modifier le libell� des �l�ments nuls
	 * 
	 * @param stringForNullValue
	 *            libell� utilis� pour les �l�ments nuls
	 */
	public void setStringForNullValue(String stringForNullValue)
	{
		((NullListCellRenderer) getRenderer()).setStringForNullValue(stringForNullValue);
		updateTooltip();
	}

	/**
	 * permet de modifier le libell� des �l�ments nuls
	 * 
	 * @param stringForNullValue
	 *            libell� utilis� pour les �l�ments nuls
	 */
	public String getStringForNullValue()
	{
		return ((NullListCellRenderer) getRenderer()).getStringForNullValue();
	}

	/**
	 * permet d'activer le mode permettant de s�lectionner une valeur nulle
	 */
	public void setUseNullValue(boolean activate)
	{
		ComboBoxModelNew model = (ComboBoxModelNew) getModel();
		model.setUseNullValue(activate);
	}

	/**
	 * permet d'activer le mode permettant de s�lectionner la premi�re valeur
	 * plut�t qu'une valeur nulle
	 */
	public void setUseMandatoryValue(boolean activate)
	{
		ComboBoxModelNew model = (ComboBoxModelNew) getModel();
		model.setUseMandatoryValue(activate);
		if (activate)
		{
			model.setUseNullValue(false);
		}
	}

	void useSearchAlgorithm()
	{
		TextComboBoxEditor editor = (TextComboBoxEditor) this.getEditor();
		ComboBoxModelForList model = (ComboBoxModelForList) getModel();

		editor.setLocked(true);
		model.useSearchAlgorithm(editor.getItem().toString());
		editor.setLocked(false);
	}

	public void fireActionEvent()
	{
		// mise � jour de l'editor avant envoie de l'�v�nement de selection
		this.configureEditor(this.getEditor(), this.getSelectedItem());
		super.fireActionEvent();
	}

	private void checkNoSearchAlgorithm()
	{
		if (m_useSearchAlgorithm)
		{
			throw new IllegalStateException("Incompatible avec l'utilisation d'un algorithme de recherche");
		}
	}

	// removed the commented method checkNoAutoCompletion()
}
