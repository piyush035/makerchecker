package com.rennover.client.framework.widget.base;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.text.Document;

import com.rennover.client.framework.widget.textfield.LimitedDocument;

/**
 * Une zone de texte qui accepte du texte sur plusieurs lignes.
 * 
 * <p>
 * N'importe quel {@link javax.swing.text.Document} peut être utilisé, mais il
 * est recommandé de prendre un document redéfini, comme {@link
 * com.agefospme.nsicm.client.common.model.ZTextFieldModel}.
 * </p>
 */
public class ZTextArea extends JTextArea
{
	boolean m_editable2 = true;

	public ZTextArea()
	{
		this(-1);
	}

	public ZTextArea(int maxChars)
	{
		super();
		setMaxChars(maxChars);
		setMinimumSize(new Dimension(10, getPreferredHeight(3)));
	}

	public void setMaxChars(int maxChars)
	{
		((LimitedDocument) getDocument()).setLimitedLength(maxChars);
	}

	/**
	 * @see javax.swing.JTextArea#createDefaultModel()
	 */
	protected Document createDefaultModel()
	{
		return new LimitedDocument();
	}

	// removed the commented method setEnabled(boolean enabled),
	// setEditable(boolean b)
	public void setEnabled(boolean enabled)
	{
		if (enabled == isEnabled())
		{
			return;
		}

		super.setEditable(enabled ? m_editable2 : false);
		super.setEnabled(enabled);
		setBackground(UIManager.getColor(enabled ? "TextArea.background" : "TextArea.inactiveBackground"));
	}

	public void setEditable(boolean b)
	{
		m_editable2 = b;

		if (isEnabled())
		{
			super.setEditable(m_editable2);
		}
	}

	public int getPreferredHeight(int visibleRowCount)
	{
		Insets insets = getInsets();
		int dy = insets.top + insets.bottom;

		int fixedCellHeight = getRowHeight();
		fixedCellHeight = (fixedCellHeight > 0) ? fixedCellHeight : 16;
		return dy + fixedCellHeight * visibleRowCount;
	}
}