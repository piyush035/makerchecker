package com.rennover.client.framework.widget.base;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

public class ZButtonGroup extends ButtonGroup
{
	ButtonModel m_selection = null;

	/**
	 * 
	 */
	public ButtonModel getSelection()
	{
		return m_selection;
	}

	public void add(AbstractButton b)
	{
		if (b == null)
		{
			return;
		}
		buttons.addElement(b);

		if (b.isSelected())
		{
			if (m_selection == null)
			{
				m_selection = b.getModel();
			} else
			{
				b.setSelected(false);
			}
		}

		b.getModel().setGroup(this);
	}

	public void remove(AbstractButton b)
	{
		if (b == null)
		{
			return;
		}
		buttons.removeElement(b);
		if (b.getModel() == m_selection)
		{
			m_selection = null;
		}
		b.getModel().setGroup(null);
	}

	public boolean isSelected(ButtonModel m)
	{
		return (m == m_selection);
	}

	public void setSelected(ButtonModel m, boolean b)
	{
		boolean alreadySelected = b && m == m_selection;
		if (!alreadySelected && m != null)
		{
			boolean noSelection = ((m == m_selection) && !b);
			if (noSelection)
			{
				m_selection = null;
				m.setSelected(false);
			} else
			{
				if (b && m != m_selection)
				{
					ButtonModel oldSelection = m_selection;
					m_selection = m;
					if (oldSelection != null)
					{
						oldSelection.setSelected(false);
					}
					m.setSelected(true);
				}
			}
		}
	}
}
