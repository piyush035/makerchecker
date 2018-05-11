package com.rennover.client.framework.widget.combobox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import com.rennover.hotel.common.helper.StringHelper;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * Une JTextField qui permet à l'utilisateur d'entrer à la main le nom de
 * l'élément qui l'intéresse. S'il existe un élément dont la méthode {@link
 * ValueObject#getDisplayString()} correspond au texte tapé, alors cet élément
 * est utilisé.
 * 
 * <p>
 * Si la String du ValueObject <i>commence</i> simplement avec le texte tapé
 * par l'utilisateur, alors on replace le texte entier par la String du
 * ValueObject. Cela ne pose pas de problème (pour l'instant!) parce que cette
 * action n'a lieu que lorsque le focus passe sur un autre widget.
 * </p>
 * 
 * <p>
 * Une autre caractéristique de cet éditeur est que la sélection dans la liste
 * se fait dès la saisie de l'utilisateur. Ainsi, si l'utilisateur tape 'i',
 * puis demande l'ouverture de la combo box, il sera positionné automatiquement
 * sur Inde. Le code est néanmoins assez lourd...
 * </p>
 */
public class AutocompletionComboBoxEditor extends BasicComboBoxEditor
{
	private JComboBox m_comboBox;

	private Object m_selectedItem;

	// private Object oldValue;
	public AutocompletionComboBoxEditor(JComboBox combo)
	{
		m_comboBox = combo;
		initialize();
	}

	private void initialize()
	{
		editor.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				updateSelection();
			}
		});
	}

	public void previousSelection()
	{
		int selIndex = m_comboBox.getSelectedIndex();
		if (selIndex > 0)
		{
			m_comboBox.setSelectedIndex(selIndex - 1);
		}
	}

	public void nextSelection()
	{
		int selIndex = m_comboBox.getSelectedIndex();
		if (selIndex < (m_comboBox.getItemCount() - 1))
		{
			m_comboBox.setSelectedIndex(selIndex + 1);
		}
	}

	public void updateSelection()
	{
		String searchedText = editor.getText();
		Object selectedObject = searchItem(searchedText);
		m_comboBox.getModel().setSelectedItem(selectedObject);
		m_selectedItem = selectedObject;
		if (selectedObject != null)
		{
			if (searchedText.length() != selectedObject.toString().length())
			{
				m_comboBox.showPopup();
			}
		}
	}

	public Object searchItem(String searchedStr)
	{
		if (searchedStr == null || "".equals(searchedStr))
		{
			return null;
		}

		searchedStr = StringHelper.toSimpleString(searchedStr);

		Object item = null;
		String currentStr;
		int itemCount = m_comboBox.getItemCount();
		for (int i = 0; i < itemCount; i++)
		{
			item = m_comboBox.getItemAt(i);
			if (item != null)
			{
				currentStr = StringHelper.toSimpleString(item.toString());

				if (currentStr.startsWith(searchedStr))
				{
					return item;
				}
			}
		}

		return null;
	}

	public Object getItem()
	{
		return m_selectedItem;
	}
}