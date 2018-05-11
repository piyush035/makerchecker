package com.rennover.client.framework.widget.combobox;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * @author tcaboste
 * 
 * Cette classe g�re l'affichage des �l�ments d'une liste. Elle a la
 * particularit� d'afficher un libell� sp�cial pour les objets nuls de la liste.
 * Ce libell� peut-�tre pr�cis� � la construction ou apr�s la construction de
 * l'objet.
 */
public class NullListCellRenderer extends JLabel implements ListCellRenderer
{
	public static final String DEFAULT_NULL_STRING = " ";

	protected static Border s_noFocusBorder;

	private String m_nullString = DEFAULT_NULL_STRING;

	/**
	 * Constructeur prennant le libell� par d�faut pour les objets nuls
	 * 
	 * @param stringForNullObject
	 *            libell� des objets nuls
	 */
	public NullListCellRenderer(String stringForNullObject)
	{
		if (s_noFocusBorder == null)
		{
			s_noFocusBorder = new EmptyBorder(1, 1, 1, 1);
		}
		setOpaque(true);
		setBorder(s_noFocusBorder);

		m_nullString = stringForNullObject;
	}

	/**
	 * Retourne le composant d'affichage des items
	 */
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
	        boolean cellHasFocus)
	{
		if (null != value)
		{
			setText(value.toString());
		} else
		{
			setText(m_nullString);
		}

		if (isSelected)
		{
			setBackground(UIManager.getColor("ComboBox.selectionBackground"));
			setForeground(UIManager.getColor("ComboBox.selectionForeground"));
		} else
		{
			setBackground(UIManager.getColor("ComboBox.background"));
			setForeground(UIManager.getColor("ComboBox.foreground"));
		}

		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setBorder(cellHasFocus ? UIManager.getBorder("List.focusCellHighlightBorder") : s_noFocusBorder);
		setToolTipText(getText());

		return this;
	}

	/**
	 * Permet de changer le libell� utilis� pour les valeurs nulles
	 * 
	 * @param stringForNullValue
	 *            nouveau libell� des valeurs nulles
	 */
	public void setStringForNullValue(String stringForNullValue)
	{
		m_nullString = stringForNullValue;
	}

	/**
	 * Permet de changer le libell� utilis� pour les valeurs nulles
	 * 
	 * @param stringForNullValue
	 *            nouveau libell� des valeurs nulles
	 */
	public String getStringForNullValue()
	{
		return m_nullString;
	}

}