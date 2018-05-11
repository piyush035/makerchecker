package com.rennover.client.framework.widget.base;

import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.rennover.client.framework.widget.textfield.LimitedDocument;
import com.rennover.client.framework.widget.textfield.UpperCaseDocument;

/**
 * Un simple héritier de {@link javax.swing.JTextField}, créé au cas où il
 * s'avère nécessaire d'implémenter des fonctionnalités communes à tous les
 * textfields utilisés dans l'application.
 */
public class ZTextField extends JTextField
{
	private int m_columnWidth = 0;

	boolean m_editable2 = true;

	public ZTextField()
	{
		this(-1);
	}

	public ZTextField(int maxChars)
	{
		super();
		setMaxChars(maxChars);

		// removed the commented code which was commented to fix defect #29

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

	// removed the commented method getToolTipText()
	protected Document createDefaultModel()
	{
		return new UpperCaseDocument(false);
	}

	/**
	 * Indique si l'on utilise la saisie en majuscules
	 * 
	 * @return
	 */
	public boolean isUseUpperCase()
	{
		return ((UpperCaseDocument) getDocument()).isUseUpperCase();
	}

	/**
	 * Permet de passer dans le mode de saisie en majuscule
	 * 
	 * @param activate
	 */
	public void setUseUpperCase(boolean activate)
	{
		((UpperCaseDocument) getDocument()).setUseUpperCase(activate);
	}

	/**
	 * Limite le nombre de caractères saisie par l'utilisateur
	 * 
	 * @param maxChars
	 */
	public void setMaxChars(int maxChars)
	{
		if (maxChars != -1)
		{
			((LimitedDocument) getDocument()).setLimitedLength(maxChars);
			setColumns(maxChars);
		} else
		{
			setColumns(16);
		}
	}

	/**
	 * Réduit la taille de la widget
	 */
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

	public Dimension getPreferredSize()
	{
		Dimension prefSize = super.getPreferredSize();

		prefSize.width = (int) (Math.round(prefSize.width / 16.0) * 16) + 16;
		return prefSize;
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

	private boolean m_replaceMode = false;

	public void setText(String t)
	{
		setReplaceMode(true);
		try
		{
			super.setText(t);

			// BUGFIX#25: permet de remettre le curseur en début de ligne
			// NB: utile lorsque le texte est plus grand que le textfield
			setCaretPosition(0);
		} finally
		{
			setReplaceMode(false);
		}
	}

	/**
	 * utilisation interne
	 * 
	 * @return
	 */
	public boolean isReplaceMode()
	{
		return m_replaceMode;
	}

	/**
	 * utilisation interne
	 * 
	 * @param replace
	 */
	public void setReplaceMode(boolean replace)
	{
		m_replaceMode = replace;
	}

	/**
	 * surcharge utilisée en interne
	 */
	public void replaceSelection(String content)
	{
		setReplaceMode(true);
		try
		{
			super.replaceSelection(content);
		} finally
		{
			setReplaceMode(false);
		}
	}

	/*
	 * removed the commented method isUseHelpToolTip(),setUseHelpToolTip(boolean
	 * useHelpToolTip),updateToolTip(), getToolTipLocation(MouseEvent
	 * event),setEnabled(boolean enabled),setEditable(boolean b)
	 */
	public void setEnabled(boolean enabled)
	{
		if (enabled == isEnabled())
		{
			return;
		}

		super.setEditable(enabled ? m_editable2 : false);
		super.setEnabled(enabled);
	}

	public void setEditable(boolean b)
	{
		m_editable2 = b;

		if (isEnabled())
		{
			super.setEditable(m_editable2);
		}
	}
}