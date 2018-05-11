package com.rennover.client.framework.widget.base;

import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ZFormattedTextField extends JFormattedTextField
{
	private int m_columnWidth = 0;

	/**
	 * Constructor for ZFormattedTextField.
	 */
	public ZFormattedTextField()
	{
		super();
		Dimension size = getPreferredSize();
		size.setSize(size.getWidth(), 22);
		setPreferredSize(size);

		// gestion du changement de tooltip en fonction de la saisie du texte
		getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				setToolTipText(getText());
			}

			public void removeUpdate(DocumentEvent e)
			{
				setToolTipText(getText());
			}

			public void changedUpdate(DocumentEvent e)
			{
				setToolTipText(getText());
			}
		});
	}

	public void setText(String t)
	{
		super.setText(t);

		// BUGFIX#25: permet de remettre le curseur en début de ligne
		// NB: utile lorsque le texte est plus grand que le textfield
		setCaretPosition(0);
	}

	public Dimension getPreferredSize()
	{
		Dimension prefSize = super.getPreferredSize();

		prefSize.width = (int) (Math.round(prefSize.width / 16.0) * 16) + 16;
		return prefSize;
	}

	public void setColumns(int columns)
	{
		super.setColumns(columns);

		Dimension preferredSize = getPreferredSize();
		preferredSize.width = columns * getColumnWidth();
		setPreferredSize(preferredSize);
		Dimension minimumSize = preferredSize;
		minimumSize.width = 2 * getColumnWidth();
		setMinimumSize(minimumSize);
	}

	protected int getColumnWidth()
	{
		if (m_columnWidth == 0)
		{
			FontMetrics metrics = getFontMetrics(getFont());
			m_columnWidth = metrics.charWidth('n');
		}
		return m_columnWidth;
	}

	/*
	 * removed the commented method isUseHelpToolTip(),
	 * setUseHelpToolTip(boolean useHelpToolTip),
	 * updateToolTip(),getToolTipLocation(MouseEvent event)
	 */
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		setEditable(enabled);
	}
}