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
 * ComboBox permettant de gérer un liste d'items sous la forme d'une List et
 * permettant de gérer un élément nul.
 * 
 * Cette ComboBox est associée au renderer NullListCellRenderer qui affiche les
 * objets null avec un chaîne de caractères particulière
 * 
 * @audit dattias 19/02/03
 * @change redéfinir setModel pour vérifier que le model passé est de type
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
	 * Constructeur par défaut
	 */
	public ComboBoxForList()
	{
		this(new ArrayList());
	}

	/**
	 * Constructeur à partir d'une liste d'items. La combobox utilisera
	 * directement la liste passée en paramètres
	 */
	public ComboBoxForList(List items)
	{
		this(items, false, NullListCellRenderer.DEFAULT_NULL_STRING);
	}

	/**
	 * Constructeur à partir d'une liste d'items et le libellé des valeurs
	 * nulles La combobox utilisera directement la liste passée en paramètres.
	 * et affichera le libellé pour les valeurs nulles
	 * 
	 * @param items
	 *            éléments de la liste
	 * @param useNullValue
	 *            activation de la possibilité de choisir la valeur nulle
	 * @param stringForNullValue
	 *            libellé (à afficher) de la valeur nulle
	 */
	public ComboBoxForList(List items, boolean useNullValue, String stringForNullValue)
	{
		super(new ComboBoxModelForList(items, useNullValue));
		this.setRenderer(new NullListCellRenderer(stringForNullValue));
	}

	/**
	 * Constructeur à partir d'une liste d'items et d'un objet représentant la
	 * valeur nulle La combobox utilisera directement la liste passée en
	 * paramètres. et utilisera l'objet de nullité en plus des éléments de la
	 * liste
	 * 
	 * @param items
	 *            éléments de la liste
	 * @param nullValue
	 *            objet représentant une valeur nulle
	 */
	public ComboBoxForList(List items, Object nullValue)
	{
		super(new ComboBoxModelForList(items, nullValue));
	}

	/**
	 * Constructeur à partir d'un algo de recherche. C'est l'algo qui détermine
	 * la liste des valeurs en fonction de la string saisie dans la combo. Combo
	 * est forcement éditable.
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

			// placé ici sinon le setEditor précédent ne marche pas
			m_useAutoCompletion = true;
		} else
		{
			// placé ici sinon le setEditor suivant ne marche pas
			m_useAutoCompletion = false;
			setEditor(new BasicComboBoxEditor());
		}
	}

	public void setSelectedItem(Object item)
	{
		if (!containsItem(item))
		{
			throw new IncoherenceException("setSelectedItem impossible car la valeur '" + item
			        + "' ne correspond à aucun élément de la liste ");
		}

		super.setSelectedItem(item);
		updateTooltip();
	}

	/**
	 * Indique l'élément sélectionné
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
	 * Active le tri automatique de la liste d'éléments
	 * 
	 * @param
	 */
	public void setSorted(boolean sorted)
	{
		m_sorted = sorted;
	}

	/**
	 * Permet de trier la liste d'éléments par ordre alphabètique
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
	 * Permet de mettre à jour la liste des élements de la liste.
	 * 
	 * @param list
	 *            nouvelle liste des éléments
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
	 * Permet de mettre à jour la liste des élements de la liste.
	 * 
	 * @param list
	 *            nouvelle liste des éléments
	 */
	public void setObjectList(List list, boolean useNullValue, boolean sort)
	{
		checkNoSearchAlgorithm();
		ComboBoxModelForList model = (ComboBoxModelForList) getModel();
		model.setItems(list, useNullValue, sort);
		setSorted(sort);
	}

	/**
	 * Permet de récupérer la liste des éléments afficher par la combo. Cette
	 * liste ne contient pas l'élément null.
	 * 
	 * @return liste des éléments
	 */
	public List getObjectList()
	{
		ComboBoxModelForList model = (ComboBoxModelForList) getModel();
		return model.getItems();
	}

	/**
	 * permet de modifier le libellé des éléments nuls
	 * 
	 * @param stringForNullValue
	 *            libellé utilisé pour les éléments nuls
	 */
	public void setStringForNullValue(String stringForNullValue)
	{
		((NullListCellRenderer) getRenderer()).setStringForNullValue(stringForNullValue);
		updateTooltip();
	}

	/**
	 * permet de modifier le libellé des éléments nuls
	 * 
	 * @param stringForNullValue
	 *            libellé utilisé pour les éléments nuls
	 */
	public String getStringForNullValue()
	{
		return ((NullListCellRenderer) getRenderer()).getStringForNullValue();
	}

	/**
	 * permet d'activer le mode permettant de sélectionner une valeur nulle
	 */
	public void setUseNullValue(boolean activate)
	{
		ComboBoxModelNew model = (ComboBoxModelNew) getModel();
		model.setUseNullValue(activate);
	}

	/**
	 * permet d'activer le mode permettant de sélectionner la première valeur
	 * plutôt qu'une valeur nulle
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
		// mise à jour de l'editor avant envoie de l'évènement de selection
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
