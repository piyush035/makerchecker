
package com.rennover.client.framework.widget.combobox;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author dattias
 */
class SearchAlgorithmAdapter implements DocumentListener
{
	private ComboBoxForList m_combo;

	SearchAlgorithmAdapter(ComboBoxForList combo)
	{
		m_combo = combo;
	}

	public void changedUpdate(DocumentEvent e)
	{
	}

	public void insertUpdate(DocumentEvent e)
	{
		m_combo.useSearchAlgorithm();
	}

	public void removeUpdate(DocumentEvent e)
	{
		m_combo.useSearchAlgorithm();
	}
}