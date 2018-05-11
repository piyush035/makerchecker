
package com.rennover.client.framework.widget.combobox;

import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.text.Document;

/**
 * @author dattias
 */
class TextComboBoxEditor extends BasicComboBoxEditor
{
	private boolean m_locked;

	TextComboBoxEditor()
	{
		super();
	}

	Document getDocument()
	{
		return editor.getDocument();
	}

	boolean isLocked()
	{
		return m_locked;
	}

	void setLocked(boolean locked)
	{
		m_locked = locked;
	}

	public void setItem(Object object)
	{
		if (!m_locked)
		{
			super.setItem(object);
		}
	}
}